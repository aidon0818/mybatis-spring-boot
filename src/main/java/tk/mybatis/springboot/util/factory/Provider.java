package tk.mybatis.springboot.util.factory;

/**
 * @Auther: ld
 * @Date: 2019/3/18 19:25
 * @Param ${tags}
 * @Description:
 */
public interface Provider {
    public Sender produce();
}
