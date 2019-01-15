package tk.mybatis.springboot.util;

/**
 * @Auther: ld
 * @Date: 2019/1/15 15:02
 * @Param ${tags}
 * @Description:
 */
public class ThreadUtil {
    public void sendMsg(final String msg){
        System.out.println("客户端：发送的消息为：" + msg);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    System.out.println("异步方法");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }).start();
        System.out.println("客户端：异步发送成功");
    }

    public static void main(String[] args) {
        ThreadUtil threadUtil=new ThreadUtil();
        threadUtil.sendMsg("msg");
    }
}
