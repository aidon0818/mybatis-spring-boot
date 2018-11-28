package tk.mybatis.springboot.util;

        import java.util.HashMap;
        import java.util.Map;

        import com.alibaba.fastjson.JSONObject;
        import com.lly835.bestpay.utils.JsonUtil;
        import org.apache.commons.codec.binary.Base64;
        import org.slf4j.Logger;
        import org.slf4j.LoggerFactory;
        import org.springframework.stereotype.Controller;
        import org.springframework.web.bind.annotation.RequestBody;
        import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.bind.annotation.RequestMethod;
        import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @author created by LiuDong
 * @date 2018年7月11日---上午10:07:24
 * @action RSA加密接口（备用）
 */
@Controller
@RequestMapping("/ssca_api")
public class RSAHelperApi {
    final static Logger logger = LoggerFactory.getLogger(RSAHelperApi.class);
    private static final String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCOsjC4ZWRSoHkjgzwHJB/FxwvJFq7vwJx3qL4L7FQVVK164aAlqk63/VfnwqWvu/MnD5gzeJng16v5fuZwZgoL3qkcXI50uTizRbetEMcoGh1IKUExHp1ChUtD/hgxV3wVvo9xeV3WQeN7SfxuRhFiYqLrXYP9/gamnyiSWkFWVQIDAQAB";
    private static final String privateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAI6yMLhlZFKgeSODPAckH8XHC8kWru/AnHeovgvsVBVUrXrhoCWqTrf9V+fCpa+78ycPmDN4meDXq/l+5nBmCgveqRxcjnS5OLNFt60QxygaHUgpQTEenUKFS0P+GDFXfBW+j3F5XdZB43tJ/G5GEWJioutdg/3+BqafKJJaQVZVAgMBAAECgYAPqD+uS/nJEzCXk/PHONB/rAqAAwwo5HKGsq4/squ7N3JdlFo1J83BZHv7wyDY+n+AnrZtl2MTB7coBiOU261oW3Mhd1BIlEhGmZMTtbZeO1C5p5IcaJLQAa3rjuSxSELe+P+ThdVTR1FccaPXbYljKEttSXh9LLg1uoWXjuPq9QJBAOLn/WKF3QLBpdjZfaagTQ7kqEAnfna+S0eH3bEnKhHVaIN902xrgB96utu/4Yh4Auoh2vTylp15RPEjEeR0RYsCQQCg/hJAbH19sCi8X9SbPdHVriQ5tQ6oU22zY3Lx+XWZKdGnfK8NhYno21I2egdVcJ8rqI84Mgs8nj1yzqOmxQ+fAkAzpKFi/xyiN/jLNhUHC4z+SsSQdWyvEfiIO0AVxdgpXuz+oHCySHVMtN46jZ2DNe1T5Ii1H1FwXd13FIhJ+rypAkBbNngKzizvaD48NCNV7VmUsjxOAboTPml56I94bzjklV/2f9sCUzWGm7RhrxO2ob9c0fyK/zeEmbcA8/iehSnLAkEA0CnvcTVqxpaZf+p8XwXz1I32UY+uSs0x3a4o0FOl3fs+tmrNow7Ism1lQi/pEdMOzQjWERVBySgdcy0Djg5OUg==";
    private static final String privateKeyApp="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCc+9tiXa9hexDy7OZ1xycEVm1ZRvx+ATvxnjC+V266lpKsLA3ZhEFPIjfqLSbitfxkhpoG2n1bB/Q74OOfZwFWdPFQRCbij+iZR10pmBjrHyC9TknVO+ykcJsfqrmqXibAZF+DFjOFKp5Z0jivPdSFbCK/zJpbmnhPoOaCsT1+TQIDAQAB";
    /**
     * 加密
     */
    @ResponseBody
    @RequestMapping(value = "/setRSAHelper", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    public String setRSAHelper(@RequestBody String str) {
        String jsonData = "";
        Map<String, String> map = new HashMap<>();
        RSAUtil rsaUtil = new RSAUtil();
        try {
            Object feedbackProcessInfo = feedbackProcessStateInfo.getBODY();
            JSONObject jsonobject = JSONObject.fromObject(feedbackProcessInfo);
            RequestApiInfo requestApiInfo = (RequestApiInfo) JSONObject.toBean(jsonobject, RequestApiInfo.class);

            String bizCOntent = jsonobject.getString("biz_content").replaceAll("\"", "\\\"");
            requestApiInfo.setBiz_content(bizCOntent);
            String jsonStr = JsonUtil.toJson(requestApiInfo);
            byte[] encryptStrByte = rsaUtil.encryptByPublicKey(jsonStr.getBytes(), publicKey);
            byte[] btt = Base64.encodeBase64(encryptStrByte);
            jsonData = new String(btt);
            System.out.println("加密后：" + jsonData);
        } catch (Exception e) {
            logger.error("RSAHelperApi -- setRSAHelper is fail:" + e.getMessage());
        }
        return jsonData;
    }

    /**
     * 解密
     */
    @ResponseBody
    @RequestMapping(value = "/getRSAHelper", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    public String getRSAHelper(@RequestBody String str) {
        String jsonData = "";
        RSAUtil rsaUtil = new RSAUtil();
        try {
            Object feedbackProcessInfo = str;
            JSONObject jsonobject = JSONObject.parseObject(feedbackProcessInfo);
            RequestApiInfo requestApiInfo = (RequestApiInfo) JSONObject.toBean(jsonobject, RequestApiInfo.class);
            byte[] jsonStr = String.valueOf(requestApiInfo.getSign()).getBytes();
            byte[] decryptStrByte = rsaUtil.decryptByPrivateKey(Base64.decodeBase64(jsonStr), privateKeyApp);
            jsonData = new String(decryptStrByte);
            System.out.println("解密后：" + jsonData);

        } catch (Exception e) {
            logger.error("RSAHelperApi -- getRSAHelper is fail:" + e.getMessage());
        }
        return jsonData;
    }

}

