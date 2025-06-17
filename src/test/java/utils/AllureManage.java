package utils;


import utils.driverManager.NewDriver;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.nio.file.Files;


public class AllureManage {

    public static void addLogsToReport(String logfileName){
        File log = new File("testOut/logs/logfile_" + logfileName + ".txt");
        try {
            Allure.addAttachment("logs" , Files.readString(log.toPath()));
        }catch (Exception e){
            Logs.error("cannot add logs to allure report " + e.getMessage());
        }
    }


    public static void takeScreenshotAndAddToReport(String screenName){
        try {
            byte[] screenshot = ((TakesScreenshot) NewDriver.getDriver())
                    .getScreenshotAs(OutputType.BYTES);

            Allure.step("Attaching screenshot for: " + screenName);

            Allure.getLifecycle().addAttachment(
                    "Screenshot - " + screenName,
                    "image/png",
                    "png",
                    screenshot
            );
        } catch (Exception e) {
            Logs.error("cannot take screenshot of: " + screenName + " - " + e.getMessage());
        }
    }




}

