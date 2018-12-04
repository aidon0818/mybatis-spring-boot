package tk.mybatis.springboot.util;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author created by LiuDong
 * @date 2018年7月11日---上午10:07:24
 * @action RSA加密接口（备用）
 */
public class RSAUtilApi {
    final static Logger logger = LoggerFactory.getLogger(RSAUtilApi.class);
    private static final String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCOsjC4ZWRSoHkjgzwHJB/FxwvJFq7vwJx3qL4L7FQVVK164aAlqk63/VfnwqWvu/MnD5gzeJng16v5fuZwZgoL3qkcXI50uTizRbetEMcoGh1IKUExHp1ChUtD/hgxV3wVvo9xeV3WQeN7SfxuRhFiYqLrXYP9/gamnyiSWkFWVQIDAQAB";
    private static final String privateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAI6yMLhlZFKgeSODPAckH8XHC8kWru/AnHeovgvsVBVUrXrhoCWqTrf9V+fCpa+78ycPmDN4meDXq/l+5nBmCgveqRxcjnS5OLNFt60QxygaHUgpQTEenUKFS0P+GDFXfBW+j3F5XdZB43tJ/G5GEWJioutdg/3+BqafKJJaQVZVAgMBAAECgYAPqD+uS/nJEzCXk/PHONB/rAqAAwwo5HKGsq4/squ7N3JdlFo1J83BZHv7wyDY+n+AnrZtl2MTB7coBiOU261oW3Mhd1BIlEhGmZMTtbZeO1C5p5IcaJLQAa3rjuSxSELe+P+ThdVTR1FccaPXbYljKEttSXh9LLg1uoWXjuPq9QJBAOLn/WKF3QLBpdjZfaagTQ7kqEAnfna+S0eH3bEnKhHVaIN902xrgB96utu/4Yh4Auoh2vTylp15RPEjEeR0RYsCQQCg/hJAbH19sCi8X9SbPdHVriQ5tQ6oU22zY3Lx+XWZKdGnfK8NhYno21I2egdVcJ8rqI84Mgs8nj1yzqOmxQ+fAkAzpKFi/xyiN/jLNhUHC4z+SsSQdWyvEfiIO0AVxdgpXuz+oHCySHVMtN46jZ2DNe1T5Ii1H1FwXd13FIhJ+rypAkBbNngKzizvaD48NCNV7VmUsjxOAboTPml56I94bzjklV/2f9sCUzWGm7RhrxO2ob9c0fyK/zeEmbcA8/iehSnLAkEA0CnvcTVqxpaZf+p8XwXz1I32UY+uSs0x3a4o0FOl3fs+tmrNow7Ism1lQi/pEdMOzQjWERVBySgdcy0Djg5OUg==";

    /**
     * 加密
     */
    public String setRSAHelper(String msgType, String channel, String data) {
        String obj = "";
        Map<String, String> dataMap = new HashMap<>();
        RSAUtil rsaUtil = new RSAUtil();
        String jsonData = "";
        try {
            byte[] encryptStrByte = rsaUtil.encryptByPublicKey(data.getBytes(), publicKey);
            byte[] btt = Base64.encodeBase64(encryptStrByte);
            obj = new String(btt);
            dataMap.put("channel", channel);
            dataMap.put("msgType", msgType);
            dataMap.put("obj", obj);
            jsonData = JSONObject.toJSONString(dataMap);
            System.out.println("加密后：" + jsonData);
        } catch (Exception e) {
            logger.error("RSAHelperApi -- setRSAHelper is fail:" + e);
        }
        return jsonData;
    }

    /**
     * 解密
     */
    public String getRSAHelper(String data) {
        String jsonData = "";
        RSAUtil rsaUtil = new RSAUtil();
        try {
            JSONObject jsonObject = JSONObject.parseObject(data);
            String obj = jsonObject.getString("obj");
            byte[] decryptStrByte = rsaUtil.decryptByPrivateKey(Base64.decodeBase64(obj), privateKey);
            jsonData = new String(decryptStrByte);
            System.out.println("解密后：" + jsonData);
        } catch (Exception e) {
            logger.error("RSAHelperApi -- getRSAHelper is fail:" + e);
        }
        return jsonData;
    }

    public static void main(String[] args) {
        RSAUtilApi rsaHelperApi = new RSAUtilApi();
        Map<String, String> mapStr = new HashMap();
        mapStr.put("amount", "1000");
        mapStr.put("batchNo", "100002");
        mapStr.put("cardNo", "86610814500007562670");
        mapStr.put("merNo", "888161054111758");
        mapStr.put("mobile", "13415151515");
        mapStr.put("pwd", "123456");
        mapStr.put("terminalId", "58897140");
        mapStr.put("traceNo", "100130");
        String str = JSON.toJSONString(mapStr);
        String data = rsaHelperApi.setRSAHelper("GXYJ","10",str);
        String newData = rsaHelperApi.getRSAHelper(data);
        System.out.println(newData);
    }

}

