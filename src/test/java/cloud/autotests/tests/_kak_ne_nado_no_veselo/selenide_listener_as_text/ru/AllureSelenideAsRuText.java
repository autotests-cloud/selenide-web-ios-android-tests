package cloud.autotests.tests._kak_ne_nado_no_veselo.selenide_listener_as_text.ru;

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

public class AllureSelenideAsRuText extends AllureSelenide {

    private static final Logger LOGGER = LoggerFactory.getLogger(AllureSelenideAsRuText.class);


    private final AllureLifecycle lifecycle;

    public AllureSelenideAsRuText() {
        this(Allure.getLifecycle());
    }

    public AllureSelenideAsRuText(final AllureLifecycle lifecycle) {
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
            return "Открытие адреса \"" + subject + "\"";
        }

        if(element.contains("data-testid")) {
            element = element.replace("[data-testid=", "")
                    .replace("]", "");

            if(subject.contains("should have(text '")) {
                subject = subject
                        .replace("should have(text '", "должен(а) иметь текст \"")
                        .replace("')", "\"");
            }

            if(subject.contains("should not have(text '")) {
                subject = subject
                        .replace("should have(text '", "нет должен(а) иметь текст \"")
                        .replace("')", "\"");
            }

            if(subject.contains("should (exist)")) {
                subject = subject
                        .replace("should (exist)", "должен(а) существовать");
            }

            if(subject.contains("should not(exist)")) {
                subject = subject
                        .replace("should not(exist)", "не должен(а) существовать");
            }

            if(subject.contains("should be(visible)")) {
                subject = subject
                        .replace("should be(visible)", "должен(а) быть виден");
            }

            if(subject.contains("should not be(visible)")) {
                subject = subject
                        .replace("should not be(visible)", "не должен(а) быть виден");
            }

            if(subject.contains("should be(enabled)")) {
                subject = subject
                        .replace("should be(enabled)", "должен(а) быть активен");
            }

            if(subject.contains("should not be(enabled)")) {
                subject = subject
                        .replace("should not be(enabled)", "не должен(а) быть активен");
            }

            if(subject.contains("should have(size(")) {
                subject = subject
                        .replace("should have(size(", "должен(а) иметь размер \"")
                        .replace("))", "\"");
                element = "Список " + element;
            }


            if(subject.contains("should have(Texts [")) {
                subject = subject
                        .replace("should have(Texts [", "должен(а) содержать тексты \"")
                        .replace("])", "\"");
                element = "Список " + element;
            }

            if(subject.contains("set value(")) {
                subject = subject
                        .replace("set value(", "Ввод значения \"")
                        .replace(")", "\" в");

                return subject + " " + element;
            }

            if(subject.contains("click()")) {
                subject = subject
                        .replace("click()", "Клик на");

                return subject + " " + element;
            }

            return element + " " + subject;
        }

        return event.toString();
    }



}
