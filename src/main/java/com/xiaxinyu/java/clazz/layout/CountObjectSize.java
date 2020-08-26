package com.xiaxinyu.java.clazz.layout;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.info.GraphLayout;

/**
 * 查看对象内存 空对象一般16b(没有普通属性的类生成的对象)
 * -XX:+UseCompressedOops开启指针压缩（默认） oop对象指针
 * -XX:-UseCompressedOops关闭指针压缩
 * 指针压缩的原理：
 * 指针不再表示对象在内存中的精确位置，而是表示偏移量。这意味着32位的指针可以引用40亿个对象，
 * 而不是40亿个字节。最终，也就是说内存增长到32GB的物理内存，也可以用32位的指针表示。也就是说，我们只需要
 * 知道JVM内存的开始位置，知道偏移量，就可以算出我想要找的实际物理位置。

---------------对象头区域-----------实例数据区域---------对齐填充区域（保证8的倍数）
markword( 32位 4byte ---64位 8byte)
class指针（开启指针压缩 4byte 关闭 8byte）
数组对象（开启指针压缩 4byte 关闭 8byte）
 */
public class CountObjectSize {
    int a = 10;
    int b = 20;
    String[] aa =new String[]{"a","b","c"};

    public static void main(String[] args) {
        Object object = new Object();
        String s1 = ClassLayout.parseInstance(object).toPrintable();
        System.out.println("空对象"+ s1);
        //空Object 开启指针压缩:  markword 8bytes + class地址 4bytes + 成员变量 0bytes + 对齐填充 4bytes = 16bytes
        //空Object 关闭指针压缩:  markword 8bytes + class地址 8bytes + 成员变量 0bytes + 对齐填充 0bytes = 16bytes
        CountObjectSize countObjectSize = new CountObjectSize();
        String s = ClassLayout.parseInstance(countObjectSize).toPrintable();
        System.out.println(s);
        //非空Object 开启指针压缩:  markword 8bytes + class地址 4bytes + 成员变量 4 + 4 + 4 bytes + 对齐填充 0bytes = 24bytes
        //非空Object 关闭指针压缩:  markword 8bytes + class地址 8bytes + 成员变量 4 + 4 + 8 bytes + 对齐填充 0bytes = 32bytes

        //数组对象在关闭指针压缩的情况下 8byte 开启4byte
        //开启指针压缩，提升jvm运行效率
        long l = GraphLayout.parseInstance(countObjectSize).totalSize();
        System.out.println(l);
    }
}