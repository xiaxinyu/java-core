package com.xiaxinyu.java.lambda;

import org.junit.Test;

import java.util.function.Supplier;

public class SupplierTest {

    @Test
    public void test_supplier() {
        Supplier supplier = "Hello"::toLowerCase;
        System.out.println(supplier.get());
    }
}
