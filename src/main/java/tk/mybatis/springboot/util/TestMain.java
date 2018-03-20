package tk.mybatis.springboot.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * 作    者 : DongLiu
 * 日    期 : 2018/3/14 10:44
 * 描    述 : 获取今天的前一天
 */
public class TestMain {
    public static void main(String[] args) throws IOException {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        cal.add(Calendar.DATE, -1);
        System.out.println(format.format(cal.getTime()));
    }
}
