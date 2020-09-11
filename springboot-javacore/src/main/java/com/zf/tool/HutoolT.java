package com.zf.tool;

import cn.hutool.core.date.ChineseDate;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RuntimeUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.AES;
import cn.hutool.crypto.symmetric.DES;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;

/**
 * @author zf
 * @create 2020-09-11 11:09
 */
public class HutoolT {


    public static void main(String[] args){
        //testIdUtil();
        //testRuntimeUtil();
        testSecureUtil();
        //testChineseDate();
    }


    /**
     * 唯一ID工具
     */
    public static void testIdUtil(){
        //生成的UUID是带-的字符串，类似于：a5c8a5e8-df2b-4706-bea4-08d0939410e3
        String uuid = IdUtil.randomUUID();
        System.out.println(uuid);
        //生成的是不带-的字符串，类似于：b17f24ff026d40949c85a24f4f375d42
        String simpleUUID = IdUtil.simpleUUID();
        System.out.println(simpleUUID);


        //ObjectId是MongoDB数据库的一种唯一ID生成策略，是UUID version1的变种
        String id2 = IdUtil.objectId();
        System.out.println(id2);


        //Twitter的Snowflake 算法
        Snowflake snowflake = IdUtil.getSnowflake(1, 1); //参数1为终端ID,参数2为数据中心ID
        long id = snowflake.nextId();
        System.out.println(id);
    }

    /**
     * 命令行工具
     */
    public static void testRuntimeUtil(){
        //在Windows下可以获取网卡信息
        String str = RuntimeUtil.execForStr("ipconfig");
        System.out.println(str);
    }


    //加密解密工具-SecureUtil
    public static void testSecureUtil(){
        String content = "sdsds你好d1223ggf";

        String md5 = SecureUtil.md5(content);
        System.out.println(md5);


        // 对于Java中AES的默认模式是：AES/ECB/PKCS5Padding
        // 随机生成密钥
        /*byte[] key = SecureUtil.generateKey(SymmetricAlgorithm.AES.getValue()).getEncoded();
        System.out.println("key:"+ new String(key));*/
        // key的长度需要8的n(n>1)倍
        byte[] key = "1234567812345678".getBytes();
        // 构建
        AES aes = SecureUtil.aes(key);
/*

        // 加密
        byte[] encrypt = aes.encrypt(content);
        // 解密
        byte[] decrypt = aes.decrypt(encrypt);
*/

        // 加密为16进制表示
        String encryptHex = aes.encryptHex(content);
        System.out.println(encryptHex);
        // 解密为字符串
        String decryptStr = aes.decryptStr(encryptHex, CharsetUtil.CHARSET_UTF_8);
        System.out.println(decryptStr);


        //byte[] key2 = SecureUtil.generateKey(SymmetricAlgorithm.DES.getValue()).getEncoded();
        byte[] key2 = "1234567812345678".getBytes();
        DES des = SecureUtil.des(key2);
        // 加密为16进制表示
        String encryptHex2 = des.encryptHex(content);
        System.out.println(encryptHex2);
        // 解密为字符串
        String decryptStr2 = des.decryptStr(encryptHex2, CharsetUtil.CHARSET_UTF_8);
        System.out.println(decryptStr2);
    }

    /**
     * 农历日期-ChineseDate测试
     * 农历日期，提供了生肖、天干地支、传统节日等方法。
     */
    public static void testChineseDate(){
        //通过公历构建
        ChineseDate date = new ChineseDate(DateUtil.parseDate("2020-01-25"));
        // 一月
        System.out.println(date.getChineseMonth());
        // 正月
        System.out.println(date.getChineseMonthName());
        // 初一
        System.out.println(date.getChineseDay());
        // 庚子
        System.out.println(date.getCyclical());
        // 生肖：鼠
        System.out.println(date.getChineseZodiac());
        // 传统节日（部分支持，逗号分隔）：春节
        System.out.println(date.getFestivals());
        // 庚子鼠年 正月初一
        System.out.println(date.toString());

        //获取天干地支
        // 庚子年丁丑月丁卯日
        System.out.println(date.getCyclicalYMD());
    }

}
