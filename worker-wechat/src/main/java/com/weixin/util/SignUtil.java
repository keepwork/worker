package com.weixin.util;

import java.util.Random;
import java.util.UUID;
import java.util.Map;
import java.util.HashMap;
import java.util.Formatter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.io.UnsupportedEncodingException;  

public class SignUtil {
    public static void main(String[] args) {
        String jsapi_ticket = "sM4AOVdWfPE4DxkXGEs8VOb9JkXmsmahupkRGlekZNOzQ8DrVCli8aUTfV69L1JDBzxBh7sN0wlJ6p-Tj8yuZA";

        // 注意 URL 一定要动态获取，不能 hardcode
        String url = "http://www.xiaogongh.com";
        Map<String, String> ret = sign(jsapi_ticket, url);
        for (Map.Entry entry : ret.entrySet()) {
            System.out.println(entry.getKey() + ", " + entry.getValue());
        }
    };

    public static Map<String, String> sign(String jsapi_ticket, String url) {
        Map<String, String> ret = new HashMap<String, String>();
        String nonce_str = create_nonce_str(16);//必须是16位的字符串，否则会报 invalid signature
        String timestamp = create_timestamp();
        String string1;
        String signature = "";

        //注意这里参数名必须全部小写，且必须有序
        string1 = "jsapi_ticket=" + jsapi_ticket +
                  "&noncestr=" + nonce_str +
                  "&timestamp=" + timestamp +
                  "&url=" + url;
//        System.out.println(string1);

        try
        {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(string1.getBytes("UTF-8"));
            signature = byteToHex(crypt.digest());
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }

        ret.put("url", url);
        ret.put("jsapi_ticket", jsapi_ticket);
        ret.put("nonceStr", nonce_str);
        ret.put("timestamp", timestamp);
        ret.put("signature", signature);

        return ret;
    }

    private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash)
        {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }

    private static String create_nonce_str() {
        return UUID.randomUUID().toString();
    }
    
    public static final String allChar = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";   
    /**  
     * 返回一个定长的随机纯字母字符串(只包含大小写字母)  
     *  
     * @param length 随机字符串长度  
     * @return 随机字符串  
     */ 
    public static String create_nonce_str(int length) {   
        StringBuffer sb = new StringBuffer();   
        Random random = new Random();   
        for (int i = 0; i < length; i++) {   
             sb.append(allChar.charAt(random.nextInt(allChar.length())));   
        }   
        return sb.toString();   
}  
    

    private static String create_timestamp() {
        return Long.toString(System.currentTimeMillis() / 1000);
    }
}
