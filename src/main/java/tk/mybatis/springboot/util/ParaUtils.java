package tk.mybatis.springboot.util;

import org.apache.commons.lang3.StringUtils;

import java.util.Iterator;
import java.util.Map;

/**
 * @Auther: ld
 * @Date: 2020/3/10 09:29
 * @Param ${tags}
 * @Description: 拼装米大师sign参数
 */
public class ParaUtils {

	public static String setMapToStr(Map map) {
		Object val = null;
		String key = "";
		Iterator iter = map.entrySet().iterator();
		StringBuilder str = new StringBuilder();
		String res = "";
		while (iter.hasNext()) {
			Map.Entry entry = (Map.Entry) iter.next();
			// 获取key
			key = (String) entry.getKey();
			// 获取value
			val = entry.getValue();
			if (null == val|| StringUtils.isBlank(val.toString())||"null".equals(val)) {
				val = "";
			}
			str.append(key).append("=").append(val).append("&");
		}
		if (str.length() > 0) {
			res = str.toString().substring(0, str.toString().length() - 1);
		}
		return res;
	}

}
