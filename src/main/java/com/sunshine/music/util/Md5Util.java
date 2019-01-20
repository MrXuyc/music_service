package com.sunshine.music.util;


import com.google.common.base.Strings;

import org.springframework.beans.factory.annotation.Value;

import java.security.MessageDigest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Md5Util {

    @Value("password.salt")
    private static String salt;

    private static String byteArrayToHexString(byte[] b) {
        StringBuilder resultSb = new StringBuilder();
        for (byte bb:b) {
            resultSb.append(byteToHexString(bb));
        }
        return resultSb.toString();
    }

    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0) {
            n += 256;
        }
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

    /**
     * 返回大写MD5

     */
    private static String MD5Encode(String origin, String charsetname) {
        String resultString = origin;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            if (Strings.isNullOrEmpty(charsetname)) {
                resultString = byteArrayToHexString(md.digest(resultString.getBytes()));
            }else {
                resultString = byteArrayToHexString(md.digest(resultString.getBytes(charsetname)));
            }
        } catch (Exception e) {
            log.error("md5 error {}",e);
        }
        return resultString.toUpperCase();
    }

    public static String MD5EncodeUtf8(String origin) {
        origin = origin + salt;
        return MD5Encode(origin, "utf-8");
    }


    private static final String hexDigits[] = {"0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};

}
