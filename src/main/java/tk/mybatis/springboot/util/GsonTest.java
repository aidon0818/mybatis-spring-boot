package tk.mybatis.springboot.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import tk.mybatis.springboot.dto.CityDTO;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Dong_Liu
 * date：2017/9/27
 */
public class GsonTest {
    public static void main(String[] args) throws IOException {
        Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create(); //开启复杂处理Map方法
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("id",1);
        map.put("name","tom");
// TODO 向map中添加数据
        String jsonStr = gson.toJson(map);  //toJson
        Map<String,Object> resultMap = gson.fromJson(jsonStr, new TypeToken<Map<String,Object>>() {
        }.getType()); //fromJson
        System.out.println(resultMap);
    }
}
