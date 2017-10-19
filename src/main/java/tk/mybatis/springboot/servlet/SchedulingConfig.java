package tk.mybatis.springboot.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * Created by Dong_Liu
 * date：2017/10/19
 */
@Configuration
@EnableScheduling // 启用定时任务
public class SchedulingConfig {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    /*    // 每天早八点到晚八点，间隔2分钟执行任务
        @Scheduled(cron="0 0/2 8-20 * * ?")
    // 每天早八点到晚八点，间隔3分钟执行任务
        @Scheduled(cron="0 0/3 8-20 * * ?")
    // 每天早八点到晚八点，间隔1分钟执行任务
        @Scheduled(cron="0 0/1 8-20 * * ?")*/
    @Scheduled(cron = "0/20 * * * * ?") // 每20秒执行一次
    public void scheduler() {
        System.out.println(">>>>>>>>>>>>> scheduled ... ");
    }
}
