package cloud.autotests.tests._other_patterns.selenide_listener_as_text.en;

import com.codeborne.selenide.logevents.LogEvent;
import io.qameta.allure.Allure;
import io.qameta.allure.AllureLifecycle;
import io.qameta.allure.model.Status;
import io.qameta.allure.model.StatusDetails;
import io.qameta.allure.selenide.AllureSelenide;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.qameta.allure.util.ResultsUtils.getStatus;
import static io.qameta.allure.util.ResultsUtils.getStatusDetails;

public class AllureSelenideAsText extends AllureSelenide {

    private static final Logger LOGGER = LoggerFactory.getLogger(AllureSelenideAsText.class);


    private final AllureLifecycle lifecycle;

    public AllureSelenideAsText() {
        this(Allure.getLifecycle());
    }

    public AllureSelenideAsText(final AllureLifecycle lifecycle) {
        this.lifecycle = lifecycle;
    }

    @Override
    public void afterEvent(final LogEvent event) {
        String formatedEvent = formatEventToText(event);

        lifecycle.updateStep(stepResult -> {
            stepResult.setName(formatedEvent);
        });

        lifecycle.getCurrentTestCaseOrStep().ifPresent(parentUuid -> {
            switch (event.getStatus()) {
                case PASS:
                    lifecycle.updateStep(step -> step.setStatus(Status.PASSED));
                    break;
                case FAIL:
                    lifecycle.updateStep(stepResult -> {
                        stepResult.setStatus(getStatus(event.getError()).orElse(Status.BROKEN));
                        stepResult.setStatusDetails(getStatusDetails(event.getError()).orElse(new StatusDetails()));
                    });
                    break;
                default:
                    LOGGER.warn("Step finished with unsupported status {}", event.getStatus());
                    break;
            }
            lifecycle.stopStep();
        });
    }

    String formatEventToText(LogEvent event) {
        String element = event.getElement();
        String subject = event.getSubject();

        if(element.equals("open")) {
            return "I open url \"" + subject + "\"";
        }

        if(element.contains("data-testid")) {
            element = element.replace("[data-testid=", "")
                    .replace("]", "");

            if(subject.contains("should have(text '")) {
                subject = subject
                        .replace("should have(text '", "should have text \"")
                        .replace("')", "\"");
            }

            if(subject.contains("should not have(text '")) {
                subject = subject
                        .replace("should not have(text '", "should not have text \"")
                        .replace("')", "\"");
            }

            if(subject.contains("should (exist)")) {
                subject = subject
                        .replace("should (exist)", "should exist");
            }

            if(subject.contains("should not(exist)")) {
                subject = subject
                        .replace("should not(exist)", "should not exist");
            }

            if(subject.contains("should be(visible)")) {
                subject = subject
                        .replace("should be(visible)", "should be visible");
            }

            if(subject.contains("should not be(visible)")) {
                subject = subject
                        .replace("should not be(visible)", "should not be visible");
            }

            if(subject.contains("should be(enabled)")) {
                subject = subject
                        .replace("should be(enabled)", "should be enabled");
            }

            if(subject.contains("should not be(enabled)")) {
                subject = subject
                        .replace("should not be(enabled)", "should not be enabled");
            }

            if(subject.contains("should have(size(")) {
                subject = subject
                        .replace("should have(size(", "should have size \"")
                        .replace("))", "\"");
                element = "List of " + element;
            }

            if(subject.contains("should have(Texts [")) {
                subject = subject
                        .replace("should have(Texts [", "should have texts \"")
                        .replace("])", "\"");
                element = "List of " + element;
            }

            if(subject.contains("set value(")) {
                subject = subject
                        .replace("set value(", "I set value \"")
                        .replace(")", "\" to");

                return subject + " " + element;
            }

            if(subject.contains("click()")) {
                subject = subject
                        .replace("click()", "I click on");

                return subject + " " + element;
            }

            return element + " " + subject;
        }

        return event.toString();
    }
}
