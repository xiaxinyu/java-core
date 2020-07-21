package com.xiaxinyu.java.lambda;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Slf4j
public class FunctionTest {
    @Test
    public void test_filter() {
        List<Integer> data = Arrays.asList(1, 234, 823, 48, 20, 342, 304, 80, 2, 34, 82);

        List<Integer> data1 = data.stream().filter(new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) {
                return integer > 100;
            }
        }).collect(Collectors.toList());
        log.info("data1={}", data1);

        List<Integer> data2 = data.stream().filter(integer -> integer > 100).collect(Collectors.toList());
        log.info("data2={}", data2);
    }
}
