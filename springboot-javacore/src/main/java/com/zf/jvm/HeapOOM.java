package com.zf.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 堆内存溢出  ---Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
 *
 * 知识点：
     Java堆是被所有线程共享的一块内存区域，在虚拟机启动时创建。
     此内存区域的唯一目的就是存放对象实例，几乎所有的对象实例都在这里分配内存。
 *
 * jvm参数：-Xms50m -Xmx50m -Xmn20m -XX:NewSize=10m
 *
 * @author zf
 **/
public class HeapOOM {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>(10) ;
        //不断创建对象就会发生 OutOfMemoryError
        while (true){
            list.add("1") ;
        }
    }
}
