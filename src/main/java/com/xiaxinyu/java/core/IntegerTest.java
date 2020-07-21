package com.xiaxinyu.java.core;


import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Field;

@Slf4j
public class IntegerTest {

    @Test
    public void test_integer_cache() throws NoSuchFieldException, IllegalAccessException {
        //Get value from cache
        Integer i1 = Integer.valueOf(100);
        Integer i2 = Integer.valueOf(100);
        System.out.println(i1 == i2);
        System.out.println(i1.equals(i2));

        //beyond limit -128 to 128
        Integer i3 = Integer.valueOf(250);
        Integer i4 = Integer.valueOf(250);
        System.out.println(i3 == i4);
        System.out.println(i3.equals(i4));
    }
}
