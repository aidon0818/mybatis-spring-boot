package tk.mybatis.springboot.controller;

import com.google.gson.Gson;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.misc.BASE64Encoder;
import tk.mybatis.springboot.model.AjaxMessage;
import tk.mybatis.springboot.model.Test;
import tk.mybatis.springboot.service.TestService;
import tk.mybatis.springboot.util.ParaUtils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/test")
public class TestController {
    private static final String MAC_NAME = "HmacSHA1";
    private static final String ENCODING = "UTF-8";
    @Autowired
    private TestService testService;

    @RequestMapping
    public AjaxMessage getTestList() {
        Map<String, Object> processResult = new HashMap<String, Object>();
        Test test = new Test();
        test.setId(1);
//        test.setName("123");
        List<Test> list = testService.getTestList(test);

        processResult.put("test", list);
        return AjaxMessage.succeed(processResult);
    }
/**
 * @description:
 * + 将这些数据（如果`value` 是对象则递归处理对象内 (的所有数据）按 `key` 值的 ASCII码 A-z排序，然后用&串联成字符串（如 ```key1=value1&key2=value2```）, `key` 和 `value` 均进行`urlencode` 处理
 * + 将结果字符串使用 `sha1` 算法进行 `hmac` 计算秘钥：123123)，将原始2进制结果进行 `base64` 编码获取`sign`值
 * @param request
 * @return:
 * @date: 2020-03-15 18:10
 * @auther: liudong
 *
*/
    @GetMapping("/get")
    public void getTest(HttpServletRequest request) {
        String inputLine;
        String notityXml = "";
        String orderStatus = "";
        long upStatus = 0;
        Gson gson = new Gson();
        Map res = new HashMap();
        try {
            request.setCharacterEncoding("UTF-8");
            while ((inputLine = request.getReader().readLine()) != null) {
                notityXml += inputLine;
            }
            JSONObject ResObject = new JSONObject(notityXml);
            JSONObject msgBody = new JSONObject(ResObject.getString("msgBody"));
            SortedMap<String, Object> req = new TreeMap<String, Object>();
            req.put("cmd_str", msgBody.getString("cmd_str"));
            req.put("nonce", msgBody.getString("nonce"));
            req.put("timestamp", msgBody.getString("timestamp"));
            JSONObject data = new JSONObject(msgBody.getString("data"));
            Iterator<String> it = data.keys();
            SortedMap<String, Object> dataMap = new TreeMap<String, Object>();
            while (it.hasNext()) {
                String key = (String) it.next();
                Object val = data.getString(key);
                //判断是否包含汉字
                Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
                Matcher m = p.matcher(val.toString());
                if (m.find()) {
                    String urlString = URLEncoder.encode(val.toString(), "UTF-8");
                    val = urlString.replace("+", "%20");
                }
                dataMap.put(key, val);
            }
            String datas = ParaUtils.setMapToStr(dataMap);
            req.put("data", datas);
            System.out.println(req);
            String ss = ParaUtils.setMapToStr(req);
            String urlString = URLEncoder.encode(ss, "UTF-8");
            System.out.println(urlString);
            byte[] ss1 = HmacSHA1Encrypt(ss, "123123");
            String b64 = getImageString(ss1);
            System.out.println(b64);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static byte[] HmacSHA1Encrypt(String src, String key) throws Exception {
        try {
            byte[] text = src.getBytes("UTF-8");
            byte[] keyData = key.getBytes("UTF-8");

            SecretKeySpec secretKey = new SecretKeySpec(keyData, "HmacSHA1");
            Mac mac = Mac.getInstance(secretKey.getAlgorithm());
            mac.init(secretKey);
            return mac.doFinal(text);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 二进制数据编码为BASE64字符串
     *
     * @return
     * @throws Exception
     */
    public static String getImageString(byte[] data) throws IOException {
        BASE64Encoder encoder = new BASE64Encoder();

        return data != null ? encoder.encode(data) : "";
    }

}
