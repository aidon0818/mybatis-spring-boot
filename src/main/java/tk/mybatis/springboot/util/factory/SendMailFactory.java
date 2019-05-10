package tk.mybatis.springboot.util.factory;

/**
 * @Auther: ld
 * @Date: 2019/3/18 19:21
 * @Param ${tags}
 * @Description:
 */
public class SendMailFactory  implements Provider{
    @Override
    public Sender produce(){
        return new MailSender();
    }
}
