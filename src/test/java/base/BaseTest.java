package base;

import utils.driverManager.NewDriver;
import org.testng.annotations.*;
import utils.Data.LoginData;
import utils.Listeners.TestngListeners;


@Listeners(TestngListeners.class)
public class BaseTest {


    @BeforeClass(alwaysRun = true)
    public void setUp(){
        NewDriver.setDriver();
        NewDriver.getDriver().get(LoginData.LoginUrl);
    }


    @AfterClass
    public void close()  {
        NewDriver.quitDriver();
    }



}
