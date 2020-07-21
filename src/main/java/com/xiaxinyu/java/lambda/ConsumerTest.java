package com.xiaxinyu.java.lambda;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.function.Consumer;

@Slf4j
public class ConsumerTest {
    @Test
    public void test_consumer() {
        Consumer<NameInfo> consumer = t -> {
            String infoString = t.name + t.age;
            System.out.println("consumer process:" + infoString);
        };

        NameInfo info = new NameInfo("abc", 123);
        consumer.accept(info);
    }

    @Setter
    @Getter
    @AllArgsConstructor
    class NameInfo{
        private String name;
        private Integer age;
    }
}
