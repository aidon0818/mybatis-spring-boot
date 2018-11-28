package tk.mybatis.springboot.util;



        import com.alibaba.fastjson.JSONObject;
        import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
        import com.sun.org.apache.xml.internal.security.utils.Base64;


        import org.apache.commons.io.output.ByteArrayOutputStream;
        import org.springframework.util.Base64Utils;

        import javax.crypto.BadPaddingException;
        import javax.crypto.Cipher;
        import javax.crypto.IllegalBlockSizeException;
        import java.io.IOException;
        import java.security.*;
        import java.security.interfaces.RSAPrivateKey;
        import java.security.interfaces.RSAPublicKey;
        import java.security.spec.InvalidKeySpecException;
        import java.security.spec.PKCS8EncodedKeySpec;
        import java.security.spec.X509EncodedKeySpec;

/**
 * @author created by LiuDong
 * @date 2018年7月9日---下午2:33:50
 * @action
 */
public class RSAHelper {
//    public static final String PUBLIC_KEY_VALUES = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDpv"
//            + "c7rMFV21naBBVQ7cB4RnKntHquDJzWDaIMoUFwCfpX1pVVX1ut/ry+IZQDkvVk4MHA"
//            + "rXpelcufmCGix8eznE6a3KOIhxnXjt7mMXu6296vKdndQdEJd0"
//            + "V1g5A/L4G2Xq8+tawfH0p+q1hd3qpi4CRN3Ya7E756yOHmnlI+ObQIDAQAB";
//    public static final String PRIVATE_KEY_VALUES = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwg"
//            + "gJcAgEAAoGBAOm9zuswVXbWdoEFVDtwHhGcqe0eq4MnNYNogyhQXAJ+lfWlVVfW63+vL4h"
//            + "lAOS9WTgwcCtel6Vy5+YIaLHx7OcTprco4iHGdeO3uYxe7rb3q8p2d1B0Ql3RXWDkD8vgbZ"
//            + "erz61rB8fSn6rWF3eqmLgJE3dhrsTvnrI4eaeUj45tAgMBAAECgYBEtiT5M1y0UghFFk10KZn"
//            + "VQJIdYAo4RFLxXcxMkg7yNscbjq4/Y7EX+GZHOLXiCB2NrIOU5Do9C0HLeAZa5QzoMx9ac/E4K1"
//            + "aTTTM8RemMOd7xBbIFA7yWZkxpB5ZbX3UZXpyYuFzjhRXhnfjceZhAw9ohEYdhECtIsPfY1Mr6W"
//            + "wJBAPnEbziM+w//slx4tC7mo5i46dpRZApBT9CoNasKsL/VxG8NHqsG3mTLR6fmE/un9KNs1+qUPv"
//            + "q6zUmO0l4h1BsCQQDvkv9E+ZNSSyGUdtxOEJdogCZzKtm9tDHxEfsuFCsfNHCZAyw6Bp0rZcg0VQ/N"
//            + "reL5gFCNZs1EGh4pTeXH6IAXAkEA1yTBuTCXQpy6grTmYFADM5Z0ub3KMps3qUB7mi3HOjdk0VO3yxJ"
//            + "Llv8TZijlpaxzKiKjikjQRyGJm4cO5k61aQJAEo8rsVc4P3bMiijPFkkYwaKz19Yo+hY8jDVdZQtw7"
//            + "8DrPB9PzMr4YAVJhixUxhVDcRqsotek7TKsxXxgYaU0TwJALk0+oMphVfdbyuoYtfVr4+LtFRSSxlyL"
//            + "QePR9igJbaB4oKFVI6m5eD2eg45HDnXl2G9hrXmqbShHERKuN1pqzQ==";
    /**
     * RSA密钥长度必须是64的倍数，在512~65536之间。默认是1024
     */
    public static final int KEY_SIZE = 2048;

    /**
     * 生成公钥、私钥对(keysize=1024)
     */
    public static RSAHelper.KeyPairInfo getKeyPair() {
        return getKeyPair(KEY_SIZE);
    }

    /**
     * 生成公钥、私钥对
     *
     * @param keySize
     * @return
     */
    public static RSAHelper.KeyPairInfo getKeyPair(int keySize) {
        try {
            KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
            keyPairGen.initialize(keySize);
            // 生成一个密钥对，保存在keyPair中
            KeyPair keyPair = keyPairGen.generateKeyPair();
            // 得到私钥
            RSAPrivateKey oraprivateKey = (RSAPrivateKey) keyPair.getPrivate();
            // 得到公钥
            RSAPublicKey orapublicKey = (RSAPublicKey) keyPair.getPublic();

            RSAHelper.KeyPairInfo pairInfo = new RSAHelper.KeyPairInfo(keySize);
            // 公钥
            byte[] publicKeybyte = orapublicKey.getEncoded();
            String publicKeyString = Base64.encode(publicKeybyte);
            pairInfo.setPublicKey(publicKeyString);
            // 私钥
            byte[] privateKeybyte = oraprivateKey.getEncoded();
            String privateKeyString = Base64.encode(privateKeybyte);
            pairInfo.setPrivateKey(privateKeyString);

            return pairInfo;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取公钥对象
     *
     * @param publicKeyBase64
     * @return
     * @throws InvalidKeySpecException
     * @throws NoSuchAlgorithmException
     */
    public static PublicKey getPublicKey(String publicKeyBase64)
            throws InvalidKeySpecException, NoSuchAlgorithmException, Base64DecodingException {

        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        X509EncodedKeySpec publicpkcs8KeySpec = new X509EncodedKeySpec(Base64.decode(publicKeyBase64));
        PublicKey publicKey = keyFactory.generatePublic(publicpkcs8KeySpec);
        return publicKey;
    }

    /**
     * 获取私钥对象
     *
     * @param privateKeyBase64
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    public static PrivateKey getPrivateKey(String privateKeyBase64)
            throws NoSuchAlgorithmException, InvalidKeySpecException, Base64DecodingException {
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PKCS8EncodedKeySpec privatekcs8KeySpec = new PKCS8EncodedKeySpec(Base64.decode(privateKeyBase64));
        PrivateKey privateKey = keyFactory.generatePrivate(privatekcs8KeySpec);
        return privateKey;
    }

    /**
     * 使用工钥加密
     *
     * @param content
     *            待加密内容
     * @param publicKeyBase64
     *            公钥 base64 编码
     * @return 经过 base64 编码后的字符串
     */
    public static String encipher(String content, String publicKeyBase64) {
        return encipher(content, publicKeyBase64, KEY_SIZE / 8 - 11);
    }

    /**
     * 使用公司钥加密（分段加密）
     *
     * @param content
     *            待加密内容
     * @param publicKeyBase64
     *            公钥 base64 编码
     * @param segmentSize
     *            分段大小,一般小于 keySize/8（段小于等于0时，将不使用分段加密）
     * @return 经过 base64 编码后的字符串
     */
    public static String encipher(String content, String publicKeyBase64, int segmentSize) {
        try {
            PublicKey publicKey = getPublicKey(publicKeyBase64);
            return encipher(content, publicKey, segmentSize);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 分段加密
     *
     * @param ciphertext
     *            密文
     * @param key
     *            加密秘钥
     * @param segmentSize
     *            分段大小，<=0 不分段
     * @return
     */
    public static String encipher(String ciphertext, java.security.Key key, int segmentSize) {
        try {
            // 用公钥加密
            byte[] srcBytes = ciphertext.getBytes();

            // Cipher负责完成加密或解密工作，基于RSA
            Cipher cipher = Cipher.getInstance("RSA");
            // 根据公钥，对Cipher对象进行初始化
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] resultBytes = null;

            if (segmentSize > 0)
                resultBytes = cipherDoFinal(cipher, srcBytes, segmentSize); // 分段加密
            else
                resultBytes = cipher.doFinal(srcBytes);
            String base64Str = Base64Utils.encodeToString(resultBytes);
            return base64Str;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 分段大小
     *
     * @param cipher
     * @param srcBytes
     * @param segmentSize
     * @return
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     * @throws IOException
     */
    public static byte[] cipherDoFinal(Cipher cipher, byte[] srcBytes, int segmentSize)
            throws IllegalBlockSizeException, BadPaddingException, IOException {
        if (segmentSize <= 0)
            throw new RuntimeException("分段大小必须大于0");
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int inputLen = srcBytes.length;
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段解密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > segmentSize) {
                cache = cipher.doFinal(srcBytes, offSet, segmentSize);
            } else {
                cache = cipher.doFinal(srcBytes, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * segmentSize;
        }
        byte[] data = out.toByteArray();
        out.close();
        return data;
    }

    /**
     * 使用私钥解密
     *
     * @param contentBase64
     *            待加密内容,base64 编码
     * @param privateKeyBase64
     *            私钥 base64 编码
     * @return
     * @segmentSize 分段大小
     */
    public static String decipher(String contentBase64, String privateKeyBase64) {
        return decipher(contentBase64, privateKeyBase64, KEY_SIZE / 8);
    }

    /**
     * 使用私钥解密（分段解密）
     *
     * @param contentBase64
     *            待加密内容,base64 编码
     * @param privateKeyBase64
     *            私钥 base64 编码
     * @return
     * @segmentSize 分段大小
     */
    public static String decipher(String contentBase64, String privateKeyBase64, int segmentSize) {
        try {
            PrivateKey privateKey = getPrivateKey(privateKeyBase64);
            return decipher(contentBase64, privateKey, segmentSize);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 分段解密
     *
     * @param contentBase64
     *            密文
     * @param key
     *            解密秘钥
     * @param segmentSize
     *            分段大小（小于等于0不分段）
     * @return
     */
    public static String decipher(String contentBase64, java.security.Key key, int segmentSize) {
        try {
            // 用私钥解密
            byte[] srcBytes = Base64Utils.decodeFromString(contentBase64);
            // Cipher负责完成加密或解密工作，基于RSA
            Cipher deCipher = Cipher.getInstance("RSA");
            // 根据公钥，对Cipher对象进行初始化
            deCipher.init(Cipher.DECRYPT_MODE, key);
            byte[] decBytes = null;// deCipher.doFinal(srcBytes);
            if (segmentSize > 0)
                decBytes = cipherDoFinal(deCipher, srcBytes, segmentSize); // 分段加密
            else
                decBytes = deCipher.doFinal(srcBytes);

            String decrytStr = new String(decBytes);
            return decrytStr;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 秘钥对
     */
    public static class KeyPairInfo {
        String privateKey;
        String publicKey;
        int keySize = 0;

        public KeyPairInfo(int keySize) {
            setKeySize(keySize);
        }

        public KeyPairInfo(String publicKey, String privateKey) {
            setPrivateKey(privateKey);
            setPublicKey(publicKey);
        }

        public String getPrivateKey() {
            return privateKey;
        }

        public void setPrivateKey(String privateKey) {
            this.privateKey = privateKey;
        }

        public String getPublicKey() {
            return publicKey;
        }

        public void setPublicKey(String publicKey) {
            this.publicKey = publicKey;
        }

        public int getKeySize() {
            return keySize;
        }

        public void setKeySize(int keySize) {
            this.keySize = keySize;
        }
    }

    public static void main(String[] args) {
        String arrayInfo="1233123123333333333333333333312312312312312312312312312312312312123";
        String jsonStr = JSONObject.toJSONString(arrayInfo).toString();
        RSAHelper helper = new RSAHelper();
        RSAHelper.KeyPairInfo pairInfo = helper.getKeyPair();

        String mi = helper.encipher(jsonStr, pairInfo.getPublicKey());
        System.out.println(pairInfo.getPublicKey());
        System.out.println("加密后："+mi);
        String jm = decipher(mi, pairInfo.getPrivateKey());
        System.out.println("解密后："+jm);
    }
}

