package utils.Data;

import utils.filesManage.ReadJsonFile;

public class DriverData {

    public static final String DriverName = ReadJsonFile.getKey("driver","driver_name");
    private static String headless = ReadJsonFile.getKey("driver" , "headless");
    public static final boolean IsHeadless = Boolean.parseBoolean(System.getProperty("headless", headless));




}
