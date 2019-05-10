package tk.mybatis.springboot.util.factory;

/**
 * @Auther: ld
 * @Date: 2019/3/18 19:20
 * @Param ${tags}
 * @Description:
 */
public class SmsSender implements Sender {
    @Override
    public void Send() {
        System.out.println("this is sms sender!");
    }
}
