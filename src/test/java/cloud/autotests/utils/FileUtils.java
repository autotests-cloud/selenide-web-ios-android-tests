package cloud.autotests.utils;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;


public class FileUtils {

    public void saveFile(String content, String filePath)  {
        File file = new File(filePath);
        try {
            file.getParentFile().mkdirs();
            PrintWriter writer = new PrintWriter(file, "UTF-8");
            writer.write(content);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getRootPath() {
        return "file:///" + new File("").getAbsolutePath();
    }

    public static String getResourcesPath() {
        return getRootPath() + "/src/test/resources/";
    }
}
