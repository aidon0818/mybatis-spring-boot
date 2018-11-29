package tk.mybatis.springboot.util;
import java.io.ByteArrayOutputStream;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.Cipher;
import org.apache.commons.codec.binary.Base64;


/**
 * @author created by LiuDong
 * @date 2018年7月9日---下午2:02:09
 * @action
 */
public class RSAUtil {
    private static final String ALGORITHM = "RSA";
    private static final String PUBLICK_KEY = "PUBLICK_KEY";
    private static final String PRIVATE_KEY = "PRIVATE_KEY";
    /**
     * 加密算法
     */
    private static final String CIPHER_DE = "RSA";
    /**
     * 解密算法
     */
    private static final String CIPHER_EN = "RSA";
    /**
     * 密钥长度
     */
    private static final Integer KEY_LENGTH = 1024;

    /**
     * RSA最大加密明文大小
     */
    private static final int MAX_ENCRYPT_BLOCK = 117;
    /**
     * RSA最大解密密文大小
     */
    private static final int MAX_DECRYPT_BLOCK = 128;

    /**
     * 生成秘钥对，公钥和私钥
     *
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static Map<String, Object> genKeyPair() throws NoSuchAlgorithmException {
        Map<String, Object> keyMap = new HashMap<String, Object>();
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(ALGORITHM);
        keyPairGenerator.initialize(KEY_LENGTH); // 秘钥字节数
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();
        keyMap.put(PUBLICK_KEY, publicKey);
        keyMap.put(PRIVATE_KEY, privateKey);
        return keyMap;
    }

    /**
     * 公钥加密
     *
     * @param data
     * @param publicKey
     * @return
     * @throws InvalidKeySpecException
     */
    public static byte[] encryptByPublicKey(byte[] data, String publicKey) throws Exception {
        // 得到公钥
        byte[] keyBytes = Base64.decodeBase64(publicKey.getBytes());
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
        Key key = keyFactory.generatePublic(x509EncodedKeySpec);
        // 加密数据，分段加密
        Cipher cipher = Cipher.getInstance(CIPHER_EN);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        int inputLength = data.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offset = 0;
        byte[] cache;
        int i = 0;
        while (inputLength - offset > 0) {
            if (inputLength - offset > MAX_ENCRYPT_BLOCK) {
                cache = cipher.doFinal(data, offset, MAX_ENCRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(data, offset, inputLength - offset);
            }
            out.write(cache, 0, cache.length);
            i++;
            offset = i * MAX_ENCRYPT_BLOCK;
        }
        byte[] encryptedData = out.toByteArray();
        out.close();
        return encryptedData;
    }

    /**
     * 私钥解密
     *
     * @param data
     * @param privateKey
     * @return
     * @throws Exception
     */
    public static byte[] decryptByPrivateKey(byte[] data, String privateKey) throws Exception {
        // 得到私钥
        byte[] keyBytes = Base64.decodeBase64(privateKey.getBytes());
        PKCS8EncodedKeySpec pKCS8EncodedKeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
        Key key = keyFactory.generatePrivate(pKCS8EncodedKeySpec);
        // 解密数据，分段解密
        Cipher cipher = Cipher.getInstance(CIPHER_DE);
        cipher.init(Cipher.DECRYPT_MODE, key);
        int inputLength = data.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offset = 0;
        byte[] cache;
        int i = 0;
        byte[] tmp;
        while (inputLength - offset > 0) {
            if (inputLength - offset > MAX_DECRYPT_BLOCK) {
                cache = cipher.doFinal(data, offset, MAX_DECRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(data, offset, inputLength - offset);
            }
            out.write(cache);
            i++;
            offset = i * MAX_DECRYPT_BLOCK;
        }
        byte[] decryptedData = out.toByteArray();
        out.close();
        return decryptedData;
    }

    /**
     * 获取公钥
     *
     * @param keyMap
     * @return
     */
    public static String getPublicKey(Map<String, Object> keyMap) {
        Key key = (Key) keyMap.get(PUBLICK_KEY);
        String str = new String(Base64.encodeBase64(key.getEncoded()));
        return str;
    }

    /**
     * 获取私钥
     *
     * @param keyMap
     * @return
     */
    public static String getPrivateKey(Map<String, Object> keyMap) {
        Key key = (Key) keyMap.get(PRIVATE_KEY);
        String str = new String(Base64.encodeBase64(key.getEncoded()));
        return str;
    }

    public static void main(String[] args) throws Exception {
//        Map<String, Object> keyMap = RSAUtil.genKeyPair();
////        System.out.println(RSAUtil.getPublicKey(keyMap));
//        System.out.println(keyMap.get("PUBLICK_KEY"));
//        System.out.println(keyMap.get("PRIVATE_KEY"));
//        // 公钥加密
//        String str="asdasdasda";
//        String jsonStr = JSONObject.toJSONString(str);
//        System.out.println("加密前：" + jsonStr);
//        byte[] encryptStrByte = RSAUtil.encryptByPublicKey(jsonStr.getBytes(), publicKey);
//        byte[] btt = Base64.encodeBase64(encryptStrByte);
//        String encryptStr = new String(btt);
//        System.out.println("加密后：" + encryptStr);
//        System.out.println("长度：" + encryptStr.length());
//        // 私钥解密
//        byte[] decryptStrByte = RSAUtil.decryptByPrivateKey(Base64.decodeBase64(Base64.encodeBase64(encryptStrByte)), privateKey);
//        String sourceStr_1 = new String(decryptStrByte);
//        System.out.println("解密后：" + sourceStr_1);
    }

}

