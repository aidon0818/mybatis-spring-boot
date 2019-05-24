package tk.mybatis.springboot.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import tk.mybatis.springboot.dto.CartDTO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 作    者 : DongLiu
 * 日    期 : 2017/12/5 10:35
 * 描    述 :
 */
public class JsonUtils {

	/**
	 * 转换器
	 */
	private final static ObjectMapper objectMapper = new ObjectMapper();

	protected static ObjectMapper getMapper() {
		return objectMapper;
	}

	// 定义jackson对象
	private static final ObjectMapper MAPPER = new ObjectMapper();

	/**
	 * 把java对象转换为json字符串
	 */
	public static String obj2Json(Object obj) {
		String json = null;
		try {
			json = objectMapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			json = null;
		}
		return json;
	}

	/**
	 * 把json字符串转换为java对象
	 */
	public static <T> T json2Obj(String json, Class<T> clazz) {
		T obj = null;
		try {
			obj = objectMapper.readValue(json, clazz);
		} catch (IOException e) {
			obj = null;
		}
		return obj;
	}

	/**
	 * 将对象转换成json字符串。
	 * <p>Title: pojoToJson</p>
	 * <p>Description: </p>
	 *
	 * @param data
	 * @return
	 */
	public static String objectToJson(Object data) {
		try {
			String string = MAPPER.writeValueAsString(data);
			return string;
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 将json结果集转化为对象
	 *
	 * @param jsonData json数据
	 * @param beanType 对象中的object类型
	 * @return
	 */
	public static <T> T jsonToPojo(String jsonData, Class<T> beanType) {
		try {
			T t = MAPPER.readValue(jsonData, beanType);
			return t;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 将json数据转换成pojo对象list
	 * <p>Title: jsonToList</p>
	 * <p>Description: </p>
	 *
	 * @param jsonData
	 * @param beanType
	 * @return
	 */
	public static <T> List<T> jsonToList(String jsonData, Class<T> beanType) {
		JavaType javaType = MAPPER.getTypeFactory().constructParametricType(List.class, beanType);
		try {
			List<T> list = MAPPER.readValue(jsonData, javaType);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public static void main(String[] args) {
		List<CartDTO> ls = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			CartDTO cartDTO = new CartDTO(i + "", i);
			ls.add(cartDTO);
		}

		JSONArray array = new JSONArray();
		JSONObject object = new JSONObject();
		object.put("car", ls);
		array.add(object);

		JSONObject object1 = new JSONObject();
		JSONArray jsonArray = object.getJSONArray("car");
		for (int i = 0; i < jsonArray.size(); i++) {
			JSONObject jsonObject2 = jsonArray.getJSONObject(i);
			CartDTO car = new CartDTO(jsonObject2.getString("productId"), i);
			System.out.println(car);
		}
	}
}
