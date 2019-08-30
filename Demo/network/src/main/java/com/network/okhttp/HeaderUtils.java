package com.network.okhttp;

import java.security.PublicKey;

import okhttp3.Headers;

/**
 * Created by luo.j on 2019/7/2.
 */

public class HeaderUtils {


    public static Headers getHead(){
//        String uid=(System.currentTimeMillis()+"").substring(0,8);//"dc:09:4c:d6:f9:63"
       String imei=(System.currentTimeMillis()+"").substring(2);//"860962035785914"
        String uid="dc:09:4c:d6:f9:63";
    //    String imei="86096203";
        String tokenStr =imei+"_"+uid; //"860962035785914_dc:09:4c:d6:f9:63";
        String ran = System.currentTimeMillis() + "";
        String token = RSAEncryptionUtil.sign(tokenStr + "_" + ran);
        //   print(tokenStr + "  -- " + ran + "  " + token);
        //  print("test==useragin==" + getUserAgent());
        Headers.Builder builder =new Headers.Builder()
                .add("User-Agent", getUserAgent())
                .add("Udid", uid)//Udid=dc:09:4c:d6:f9:63
                .add("Ver", "1.0")//Ver=1.0
                .add("Os", "android")//Os=android
                .add("screenlevel", "1")//screenlevel=1
                .add("Sourceid", "YgYougouwebA59")//Sourceid
                .add("nodeCode", "")//Udid=dc:09:4c:d6:f9:63
                .add("modelType", "")//Ver=1.0
                .add("screenh", "1184")//Udid=dc:09:4c:d6:f9:63
                .add("Osversion", "5.1")//Udid=dc:09:4c:d6:f9:63
                .add("mac", "dc:09:4c:d6:f9:63")//Ver=1.0
                .add("token", token)//Udid=dc:09:4c:d6:f9:63
                .add("viewPath", "")//Ver=1.0
                .add("productLine", "vm8")//Udid=dc:09:4c:d6:f9:63
                .add("random", ran)//Ver=1.0
                .add("topicId", "")//Udid=dc:09:4c:d6:f9:63
                .add("Appkey", "9980998")//Ver=1.0
                .add("Appversion", "4.2.4")//Udid=dc:09:4c:d6:f9:63
                .add("commodityCode", "")//Ver=1.0
                .add("screenw", "720")//Udid=dc:09:4c:d6:f9:63
                .add("imei", imei)//Ver=1.0
                .add("topicName", "")//Udid=dc:09:4c:d6:f9:63
                .add("Unique", "ed00c4efa746e354f67f7eaa1ef0f20b");
        return builder.build();
    }

    public static String sign(String content) {
        byte[] temp = content.getBytes();
        PublicKey publicKey = RSAEncryptionUtil.loadPublicKeyWithPEM(RSAEncryptionUtil.RSA_PUBLIC);
        return RSAEncryptionUtil.encrypt2Base64String(temp, publicKey);

    }

    private static String getUserAgent() {
        String userAgent = "优购商城 4.2.4 (HUAWEI TAG-AL00;android 5.1;zh)";
        StringBuffer sb = new StringBuffer();
        for (int i = 0, length = userAgent.length(); i < length; i++) {
            char c = userAgent.charAt(i);
            if (c <= '\u001f' || c >= '\u007f') {
                sb.append(String.format("\\u%04x", (int) c));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
