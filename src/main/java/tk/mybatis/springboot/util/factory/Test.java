package tk.mybatis.springboot.util.factory;

/**
 * @Auther: ld
 * @Date: 2019/3/18 19:26
 * @Param ${tags}
 * @Description:
 */
public class Test {
    public static void main(String[] args) {
        Provider provider = new SendMailFactory();
        Sender sender = provider.produce();
        sender.Send();
    }
}
