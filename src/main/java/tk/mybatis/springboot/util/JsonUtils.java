package tk.mybatis.springboot.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * 作    者 : DongLiu
 * 日    期 : 2017/12/5 10:35
 * 描    述 :
 */
public class JsonUtils {

    /** 转换器 */
    private final static ObjectMapper objectMapper = new ObjectMapper();

    protected static ObjectMapper getMapper() {
        return objectMapper;
    }

    /** 把java对象转换为json字符串 */
    public static String obj2Json(Object obj){
        String json = null;
        try {
            json = objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            json = null;
        }
        return json;
    }

    /** 把json字符串转换为java对象 */
    public static <T> T json2Obj(String json, Class<T> clazz) {
        T obj = null;
        try {
            obj = objectMapper.readValue(json, clazz);
        } catch (IOException e) {
            obj = null;
        }
        return obj;
    }
}
