package tk.mybatis.springboot.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * @Auther: ld
 * @Date: 2019/4/29 16:51
 * @Param ${tags}
 * @Description:
 */
public class Test0002 {
	public static void main(String[] args) {
		String str = "{\"links\":[{\"url\":\"https://b2b.umfintech.com/b2b_PayWeb/ebankPayOrder.htm?amount=1&retUrl=https%3A%2F%2Fb2b.umfintech.com%2FmerAccess%2Febankpay%2FfrontNotify&orderId=1547781143454&goodsId=&goodsInf=&sign=wz68Y2RZh9GrhA%2B0LxiZxS3Oj10rd%2Bm3kmCpnS%2FT7BD9TD7mGWWbYLHQ20Hv9w6277TaV6ihpTb3VxlZRM4DCOjpiQErQBInDUdGRnjoNyNPDi3hyzQTxPmqzVmBkR9%2FITDUjWP0ZRmBW94XsPRDASJgHFBe2H5a%2BOOKol4f4iM%3D&reqTime=164408&rpid=BSP164408fdedf99&userId=&instId=20000013&trace=1904291644082010&merCustId=&payType=B2CDEBITBANK&reqDate=20190429&gateId=CMB&notifyUrl=http%3A%2F%2F172.16.151.201%3A8080%2FmerAccess%2Febankpay%2FbackNotify&userIp=&merId=90000002&servType=02&pProductId=P15F00D1&orderDate=20190118\"}],\"requestUrl\":\"http://b2b.umfintech.com/merAccess/payment/ebank\",\"url\":\"/payment/ebank\",\"version\":\"1.0\"}";
		JSONObject object = JSONObject.parseObject(str);
		JSONArray array = JSONArray.parseArray(object.getString("links"));

		System.out.println(array.getJSONObject(0).getString("url"));
		String data = array.getJSONObject(0).getString("url");
		if(data.startsWith("https://b2b.umfintech.com/b2b_PayWeb/ebankPayOrder.htm?")){
			data=data.substring(55);
			System.out.println(data);
		}
		System.out.println(data);
		String da[] = data.split("&");
		for (String s : da) {
//			String rr[]=s.split("=");
//			System.out.println(rr[0]);
//			System.out.println(rr[1]);
			System.out.println(s);
		}

	}
}
