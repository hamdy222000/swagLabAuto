package utils.filesManage;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.ThreadContext;
import utils.Logs;

import java.io.File;
import java.util.Arrays;

public class CleanDir {

    private static File dir;

    public static void clean(String dirName){
        dir = new File("testOut/"+dirName);
        if (dir.exists() && dir.isDirectory()) {
            File[] files = dir.listFiles();
            if (files != null) {
                Arrays.stream(files).forEach(FileUtils::deleteQuietly);
            }
            Logs.info("dir <"+dirName+"> is clean");
        }
        else {
            Logs.warn("cannot find dir <"+dirName+"> to clean.");

        }

    }

}
