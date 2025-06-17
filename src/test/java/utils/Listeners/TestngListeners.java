package utils.Listeners;

import io.qameta.allure.Allure;
import org.apache.logging.log4j.ThreadContext;
import org.testng.*;
import utils.AllureManage;
import utils.Logs;
import utils.filesManage.CleanDir;

public class TestngListeners implements ITestListener , IExecutionListener , IInvokedMethodListener {

    @Override
    public void onExecutionStart() {
        CleanDir.clean("logs");
        CleanDir.clean("allure-results");
    }
    @Override
    public void onExecutionFinish() {
        ThreadContext.clearAll();
    }

    @Override
    public void onStart(ITestContext context) {
        Logs.info("Beginning of the test " +  context.getName());

    }

    @Override
    public void onFinish(ITestContext context) {
        ThreadContext.clearAll();
        ThreadContext.put("MethodName", "onFinish");
        Logs.info("End of test " + context.getName());

    }


    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
        if (method.isTestMethod()){
            String className = testResult.getTestClass().getName();
            String methodName = testResult.getMethod().getMethodName();
            ThreadContext.clearAll();
            ThreadContext.put("MethodName", methodName);
            Allure.step("test method '" + className + "." + methodName + "'.");
            Logs.info("test method '" + className + "." + methodName + "'.");
        }
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        if (method.isTestMethod()){
            String className = testResult.getTestClass().getName();
            String methodName = testResult.getMethod().getMethodName();
            int status = testResult.getStatus();

            switch (status){
                case ITestResult.SUCCESS -> {
                    Allure.step( "'" + className + "." + methodName + "' passed.");
                    Logs.info( "'" + className + "." + methodName + "' passed.");

                }
                case ITestResult.FAILURE -> {
                    Throwable  throwable = testResult.getThrowable();
                    Allure.step( "'" + className + "." + methodName + "' failed.\n" + "reason : " + throwable.getMessage());
                    Logs.warn( "'" + className + "." + methodName + "' failed.\n" + "reason : " + throwable.getMessage());
                    AllureManage.takeScreenshotAndAddToReport(methodName);
                    AllureManage.addLogsToReport(methodName);
                }
                case ITestResult.SKIP -> {
                    Throwable throwable = testResult.getThrowable();
                    Allure.step( "'" + className + "." + methodName + "' skipped.\n" + "reason : " + throwable.getMessage());
                    Logs.warn( "'" + className + "." + methodName + "' skipped.\n" + "reason : " + throwable.getMessage());
                    AllureManage.addLogsToReport(methodName);
                }

            }

        }
    }


}
