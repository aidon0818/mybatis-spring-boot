package tk.mybatis.springboot.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tk.mybatis.springboot.dto.CartDTO;
import tk.mybatis.springboot.dto.CityDTO;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Dong_Liu
 * date：2017/9/14
 */
public class Test0001 {
	private static Logger logger = LoggerFactory.getLogger(Test0001.class);
	public static final String KEY_RMB = "156";

	public static void main(String[] args) {

//        String str = "000000030185";
//        Long l = Long.parseLong(str);
//        double f1 = Double.valueOf(l + "");
//        DecimalFormat df = new DecimalFormat("0.00");
//        String balaAmt = df.format(f1 / 100);
//        System.out.println(balaAmt);
//        List<CityDTO> cityDTO = new ArrayList<>();
//
//        String card=getStarString2("123456789123456789",5,13);
//        System.out.println(card);
//        String res="{\"code\":\"F\",\"balance\":0,\"message\":\"余额查询失败！\",\"balanceUrl\":null,\"cardNo\":null,\"tracks\":null}";
////        JSONObject s=JSONObject.parseObject(res);
////        System.out.println(s.getString("code"));
//        Map<String,String> m=new HashMap<>();
////        m.put("a","a");
//        System.out.println(StringUtils.isEmpty(m.get("b")));
//        Date d = new Date();
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MMddHHmmss");
//        System.out.println(formatter.format(d));
//        String dd = "20181215161027";
//        Date a = null;
//        try {
//            a = formatter.parse(dd);
//        } catch (Exception e) {
//            logger.error(String.valueOf(e),e);
//        }
//      System.out.println(a);
//        for (int i = 0; i <10 ; i++) {
////            getStarString2(i);
////            for (int j = 0; j <10 ; j++) {
////                System.out.println("a"+i);
////            }
////        }
//        Date d = new Date();
//        CartDTO cartDTO = new CartDTO("", 1);
//        cartDTO.setCreateTime(new Date());
//        getStarString2(cartDTO);
//        System.out.println(cartDTO.getProductId());
//        Timestamp transTime = new Timestamp(cartDTO.getCreateTime().getTime());
//        System.out.println(transTime.toString());
//        System.out.println(KEY_RMB);
//        CityDTO cityDTO=new CityDTO();
//        cityDTO.setName(cartDTO.getProductId());
//        System.out.println(cityDTO.getName());
//        System.out.println(JSONObject.toJSONString(cartDTO));
//        logger.info("data {}"+ JSONObject.toJSONString(cartDTO));
//        System.out.println(String.valueOf(""));
//        try{
//            getStarString2(null);
//        }catch (Exception e){
//            System.out.println(e);
//        }
//        System.out.println("异常过后");
//        String msg= "{\"trxDevcInf\":\"125.39.23.1||399540335204406|||B4D3E2F00102||\",\"pyerMrchntShrtNm\":\"\",\"pyerAcctIssrId\":\"Z2007933000010\",\"pyeeAcctTp\":\"00\",\"pyerBkId\":\"C1010511003703\",\"pyeeBkId\":\"C1010411000013\",\"pyerBkNo\":\"1001278619005510977\",\"trxDtTm\":\"2017-05-19T01:18:23\",\"trxTrmTp\":\"01\",\"trxSmmry\":\"收入-工资\",\"trxAmt\":\"110.00\",\"trxTrmNo\":\"718868315366\",\"pyerAcctTp\":\"04\",\"pyerMrchntNo\":\"\",\"pyerAcctNo\":\"282704424720582705\",\"pyerAcctNm\":\"xxy@mxm\",\"pyerBkNm\":\"客户备付金\",\"trxCtgy\":\"0120\",\"pyeeBkNm\":\"李周吴\",\"trxId\":\"2017103064144389383678671085000\",\"pyeeBkNo\":\"6222020200102591212\"}";
//        JSONObject jsonObject=JSONObject.parseObject(msg);
//        Object name=jsonObject.getString("pyeeAcctTp");
//        System.out.println(name);
//          if(!"e".equals("e")||!"a".equals("a1")){
//              System.out.println("ok");
//          }
//        String str="{\"rtnCode\":\"000000\",\"rtnMsg\":\"处理成功\"}";
//        JSONObject object = new JSONObject();
//        try {
//            object = JSONObject.parseObject(str);
//            System.out.println("rtnCode===" + object.getString("rtnCode"));
//            System.out.println("rtnMsg===" + object.getString("rtnMsg"));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        System.out.println("调用预付卡系统退汇 msg======" + str);
//        List<CartDTO> ls = new ArrayList<>();
//        ls.add(cartDTO);
////        JSONArray objects = new JSONArray();
////        objects.add(ls);
//        BigDecimal b=new BigDecimal("1.00");
//        System.out.println(b.toPlainString());
//        System.out.println(JSONObject.toJSONString(ls));
//        String msg = "{\"acctDate\": \"20180525\",\"acqInsCode\": \"49981621\",\"respCode\": \"00\",\"respMsg\": \"成功[00000000000]\"}";
//        Object ob=msg;
//        JSONObject object=JSONObject.parseObject(ob.toString());
//        System.out.println(object.getString("respMsg"));
//         JSONObject objects=JSONObject.parseObject("{ \"content\": [{\"orderNo\": \"tt1553244445315\"}],\"totalElements\": 281,\"totalPages\": 29}");
//        String content=objects.getString("content");
//        System.out.println(objects.getString("content"));
//        JSONArray array=JSONArray.parseArray(content);
//        System.out.println(array);
//        System.out.println(Long.parseLong(null!=objects.getString("totalElements")?objects.getString("totalElements"):"0"));
//        System.out.println(Math.rint(1.5));
		String str = "{\"meta\":{\"ret_code\":\"0000\",\"ret_msg\":\"交易成功\"},\"data\":{\"reg_date\":\"20190228\",\"order_id\":\"GkRd2XOYNVqQH8h3QHx\",\"mer_date\":\"20190228\",\"mer_cust_id\":\"163126686862361\",\"user_id\":\"00047810\",\"acc_type\":\"208\",\"mer_id\":\"51095\",\"version\":\"1.0\"}}";
		JSONObject object=JSONObject.parseObject(str);
		JSONObject meta=JSONObject.parseObject(object.getString("meta"));
		if(null!=meta){
			System.out.println(meta.getString("ret_code1"));
		}
		meta.put("ret_code","9999");
		object.put("meta",meta);
		System.out.println(object);
	}

	private static void getStarString2(CartDTO cartDTO) {
		cartDTO.setProductId("11");

	}
}
