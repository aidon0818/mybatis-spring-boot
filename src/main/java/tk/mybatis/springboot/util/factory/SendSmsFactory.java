package tk.mybatis.springboot.util.factory;

/**
 * @Auther: ld
 * @Date: 2019/3/18 19:25
 * @Param ${tags}
 * @Description:
 */
public class SendSmsFactory implements Provider{
    @Override
    public Sender produce() {
        return new SmsSender();
    }
}
