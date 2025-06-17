package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Logs {

    private Logs(){};

    public static Logger logs(){
        return LogManager.getLogger(Thread.currentThread().getStackTrace()[3].getClassName());
    }

    public static void trace(String... message){
        logs().trace(String.join(" " , message));
    }
    public static void debug(String... message){
        logs().debug(String.join(" " , message));
    }
    public static void info(String... message){
        logs().info(String.join(" " , message));
    }
    public static void warn(String... message){
        logs().warn(String.join(" " , message));
    }
    public static void error(String... message){
        logs().error(String.join(" " , message));
    }
    public static void fatal(String... message){
        logs().fatal(String.join(" " , message));
    }
}
