package tk.mybatis.springboot.util;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

public class ToolUtil {
	private static DecimalFormat decimalFormator = null;
	private static DecimalFormat decimalFormatorForFour = null;

	/**
	 * 字符串是否为空
	 */
	public static boolean isNull(String str) {
		if (null == str || str.trim().length() == 0) {
			return true;
		}
		return false;
	}

	/**
	 * 字符串是否不为空
	 * 
	 * @param str
	 * @return
	 * @auther weifei
	 * @add time:2012-12-5 下午3:35:33
	 */
	public static boolean isNotNull(String str) {
		return !isNull(str);
	}

	/**
	 * 集合是否为空
	 */
	public static boolean isNull(List list) {
		if (null == list || list.size() == 0) {
			return true;
		}
		return false;
	}

	/**
	 * 集合是否为空
	 */
	public static boolean isNull(Vector vector) {
		if (null == vector || vector.size() == 0) {
			return true;
		}
		return false;
	}

	/**
	 * 获取字符串
	 * */
	public static String getString(String str) {
		if (null == str || str.trim().length() == 0) {
			return "";
		} else {
			return str.trim();
		}
	}

	/**
	 * 将Date日期转换为String 返回格式为：yyyy-MM-dd，如2010-12-14
	 * 
	 * @param date
	 * @return
	 */
	public static String DateToString(Date date) {
		if (date == null) {
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);
	}

	/**
	 * 将Date日期转换为String 返回格式为：yyyy-MM-dd HH:mm:ss，如2010-12-14 11:20:30
	 * 
	 * @param date
	 * @return
	 */
	public static String DateTimeToString(Date date) {
		if (date == null) {
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
	}

	/**
	 * 将String日期转换为Date 输入格式为：yyyy-MM-dd HH:mm:ss，如2010-12-14 11:20:30
	 * 
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static Date StringToDateTime(String dateStr) throws ParseException {
		if (dateStr == null) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.parse(dateStr);
	}

	/**
	 * 将String日期转换为Date 输入格式为：yyyy-MM-dd，如2010-12-14
	 * 
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static Date StringToDate(String dateStr) throws ParseException {
		if (dateStr == null) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.parse(dateStr);
	}

	/**
	 * 将Date日期转换为String 返回格式为：yyyy年MM月dd日，如2010年12月14日
	 * 
	 * @param date
	 * @return
	 */
	public static String DateToStringCn(Date date) {
		if (date == null) {
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
		return sdf.format(date);
	}

	/**
	 * 将数组转换为List
	 * 
	 * @param obj
	 * @return
	 * @author
	 */
	public static List arrayToList(Object[] obj) {
		List list = new ArrayList();
		if (obj != null && obj.length > 0) {
			for (int i = 0; i < obj.length; i++) {
				list.add(obj[i]);
			}
		}
		return list;
	}

	/**
	 * 
	 * @return： String
	 * @Description: 将时间转成时刻，返回如12:28
	 */
	public static String DateTimeToMomentCn(Date date) {
		if (date == null) {
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
		return sdf.format(date);
	}

	/**
	 * 保存后二位小数
	 * 
	 * */
	public static String getDoubleData(double divisor, double dividend) {
		String percentage = "0";
		DecimalFormat df = new DecimalFormat("#.00");
		try {
			if (divisor > 0 & dividend > 0 & divisor > dividend) {
				percentage = "100";
			} else {
				percentage = df.format(((divisor / dividend) * 100));
			}
		} catch (Exception e) {
			percentage = "0";
		}
		return percentage;

	}

}
