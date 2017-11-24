package tk.mybatis.springboot.flogger;

import tk.mybatis.springboot.flogger.constants.Constant;

/**
 * 作    者 : DongLiu
 * 日    期 : 2017/11/24 10:28
 * 描    述 :
 */
public class FloggerTest {
    public static void main(String[] args) {
        //获取单例
        FLogger logger = FLogger.getInstance();
        //简便api,只需指定内容
        logger.info("Here is your message...");
        //指定日志级别和内容，文件名自动映射
        logger.writeLog(Constant.INFO, "Here is your customized level message...");
        //指定日志输出文件名、日志级别和内容
        logger.writeLog("error", Constant.ERROR, "Here is your customized log file and level message...");
    }
}
