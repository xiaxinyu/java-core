package com.xiaxinyu.java.core;


import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.lang.reflect.Field;

@Slf4j
public class StringTest {

    @Test
    public void test_set_final_values_reflection() throws NoSuchFieldException, IllegalAccessException {
        //new value
        char value[] = new char[6];
        value[0] = 's';
        value[1] = 's';
        value[2] = 's';
        value[3] = 's';
        value[4] = 's';
        value[5] = 's';

        String cat = new String("cat");
        log.info("orginal value: {}", String.valueOf(cat.toCharArray()));

        //set new value with reflection
        Field field = cat.getClass().getDeclaredField("value");
        field.setAccessible(true);
        field.set(cat, value);

        log.info("new value: {}", String.valueOf(cat.toCharArray()));
    }
}
