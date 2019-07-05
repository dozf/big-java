package com.zf.java8;

import java.util.Optional;

/**
 * @author zf
 * @create 2019-05-01 10:12
 */
public class OptionalT {

    public static void main(String[] args){
        test();
    }


    /**
     * 使用Optional给实例设置值
     */
    public static void test(){

        ComBean cb = new ComBean();
        Optional.ofNullable("jack")
                .ifPresent(cb::setName);
        Optional.ofNullable(12)
                .ifPresent(cb::setAge);
        System.out.println("ComBean 设值后："+cb.toString());


    }
}
