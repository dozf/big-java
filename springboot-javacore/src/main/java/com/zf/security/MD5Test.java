package com.zf.security;


import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

import java.security.MessageDigest;

/**
 * 消息摘要算法MD5
 * @author zf
 *
 **/
public class MD5Test {

    public static final String src = "md5 test";

    public static void main(String[] args){
        jdkMD5();
        jdkMD2();

        ccMD5();
        ccMD2();
    }

    /**
     * 用jdk实现:MD5
     */
    public static void jdkMD5(){
        try{
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] md5Bytes = md.digest(src.getBytes());
            //将密文转为十六进制显示
            System.out.println("JDK MD5:" + Hex.encodeHexString(md5Bytes));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 用jdk实现:MD2
     */
    public static void jdkMD2(){
        try{
            MessageDigest md = MessageDigest.getInstance("MD2");
            byte[] md2Bytes = md.digest(src.getBytes());
            System.out.println("JDK MD2:" + Hex.encodeHexString(md2Bytes));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 用common codes实现实现:MD5
     */
    public static void ccMD5(){
        System.out.println("common codes MD5:" + DigestUtils.md5Hex(src.getBytes()));
    }

    /**
     * 用common codes实现实现:MD2
     */
    public static void ccMD2(){
        System.out.println("common codes MD2:" + DigestUtils.md2Hex(src.getBytes()));
    }
}
