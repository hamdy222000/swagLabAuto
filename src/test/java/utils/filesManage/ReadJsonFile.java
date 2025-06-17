package utils.filesManage;

import com.jayway.jsonpath.JsonPath;
import utils.Logs;

import java.nio.file.Files;
import java.nio.file.Paths;

public class ReadJsonFile {
    private static String filePath ;

    public static String getKey(String jsonFile, String key) {
        filePath = "src/main/resources/"+jsonFile+".json";
        try {
            String jsonContent = new String(Files.readAllBytes(Paths.get(filePath)));
            return JsonPath.read(jsonContent, "$." + key);

        } catch (Exception e) {
            Logs.error("Cannot read JsonPath key: " + key ,  "from <"+jsonFile+"> - " + e.getMessage());
        }
        return "";
    }

}
