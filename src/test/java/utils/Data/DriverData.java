package utils.Data;

import utils.filesManage.ReadJsonFile;

public class DriverData {

    public static final String DriverName = ReadJsonFile.getKey("driver","driver_name");
    public static final boolean IsHeadless = Boolean.parseBoolean(ReadJsonFile.getKey("driver" , "headless"));




}
