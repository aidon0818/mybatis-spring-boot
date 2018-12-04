package tk.mybatis.springboot.util;

import tk.mybatis.springboot.dto.CityDTO;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dong_Liu
 * date：2017/9/14
 */
public class Test0001 {
    public static void main(String[] args) {
        String str = "000000030185";
        Long l = Long.parseLong(str);
        double f1 = Double.valueOf(l + "");
        DecimalFormat df = new DecimalFormat("0.00");
        String balaAmt = df.format(f1 / 100);
        System.out.println(balaAmt);
        List<CityDTO> cityDTO = new ArrayList<>();

        String card=getStarString2("123456789123456789",5,13);
        System.out.println(card);

    }
    private static String getStarString2(String content, int begin, int end) {

        if (begin >= content.length() || begin < 0) {
            return content;
        }
        if (end >= content.length() || end < 0) {
            return content;
        }
        if (begin >= end) {
            return content;
        }
        String starStr = "";
        for (int i = begin; i < end; i++) {
            starStr = starStr + "*";
        }
        return content.substring(0, begin) + starStr + content.substring(end, content.length());
    }
}
