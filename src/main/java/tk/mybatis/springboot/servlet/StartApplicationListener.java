package tk.mybatis.springboot.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * Created by Dong_Liu
 * date：2017/10/13
 * 启动监听
 */
@Component
public class StartApplicationListener implements ApplicationListener<ContextRefreshedEvent> {
    protected Logger log = LoggerFactory.getLogger(StartApplicationListener.class);
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        log.info("加载系统配置...");

        log.info("系统配置加载完成...");
    }
}
