package tk.mybatis.springboot.util;

import java.util.concurrent.TimeUnit;

/**
 * @Auther: ld
 * @Date: 2019/1/15 15:02
 * @Param ${tags}
 * @Description:
 */
public class ThreadUtil {
    public String sendMsg(final String msg){
        System.out.println("客户端：发送的消息为：" + msg);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5*1000);

                    System.out.println("调用异步方法");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }).start();
        System.out.println("客户端：异步发送成功");
        return msg;
    }

    public static void main(String[] args) {
        ThreadUtil threadUtil=new ThreadUtil();
        String result=threadUtil.sendMsg("msg");
        System.out.println(result);
        System.out.println("主线程继续进行。。。");
    }
}
