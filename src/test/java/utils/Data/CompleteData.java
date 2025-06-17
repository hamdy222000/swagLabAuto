package utils.Data;

import utils.filesManage.ReadJsonFile;

public class CompleteData {

    public static final String CompleteUrl = ReadJsonFile.getKey("pages", "complete_data.url");
    public static final String CompleteSuccessfullyMessage = ReadJsonFile.getKey("pages", "complete_data.completeSuccessfullyMessage");
    public static final String SrcImg = ReadJsonFile.getKey("pages", "complete_data.srcImg");


}
