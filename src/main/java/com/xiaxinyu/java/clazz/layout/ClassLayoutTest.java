package com.xiaxinyu.java.clazz.layout;

import lombok.extern.slf4j.Slf4j;
import org.openjdk.jol.info.ClassLayout;
//import org.openjdk.jol.info.ClassLayout;

/**
 * Java 对象布局工具类
 *
 * @author XIAXINYU3
 * @date 2019.12.28
 */
@Slf4j
public class ClassLayoutTest {

    public static void print(Object object) {
        log.info("HasCode Hex: {}", Integer.toHexString(object.hashCode()));
        String layout = ClassLayout.parseInstance(object).toPrintable();
        log.info(layout);
    }

    public static void main(String[] args) {
        Object obj = new Object();
        print(obj);

        System.out.println("******************************************************");

        synchronized (obj) {
            print(obj);
        }
    }
}
