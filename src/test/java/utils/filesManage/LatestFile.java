package utils.filesManage;

import utils.Logs;

import java.io.File;

public class LatestFile {
    public static File getLatestFile(String dirPath){
        File dir = new File(dirPath);
        if(dirPath == null || !dir.exists() || !dir.isDirectory() ){
            Logs.error("dir <" + dirPath + "> is not found " );
            return null;
        }
        File[] files = dir.listFiles();
        if(files == null) {
            Logs.error(  "cannot read files of dir <"+dirPath+">");
            return null;
        }

        if(files.length == 0) {
            Logs.error(  "dir <"+dirPath +"> is empty");
            return null;
        }

        File latestFile = files[0];
        for (File file : files){
            if (file.lastModified() > latestFile.lastModified()){
                latestFile = file;
            }

        }
        return latestFile;
    }
}
