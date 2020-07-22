package com.xiaxinyu.java.map;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * A performance tuning inherited HashMap.
 **/
public class InstrumentedHashMap<K, V> extends HashMap<K, V> {

    private int addCount = 0; //总数

    public InstrumentedHashMap() {

    }

    public InstrumentedHashMap(int initialCapacity, float loadFactor) {
        super(initialCapacity, loadFactor);
    }

    public InstrumentedHashMap(int initialCapacity) {
        super(initialCapacity);
    }

    @Override
    public V put(K key, V value) {
        addCount++;
        return super.put(key, value);
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        addCount += m.size();
        super.putAll(m);
    }

    public void getByKey() {

    }


    public void checkEntry() {

    }


    @Override
    public String toString() {
        return "InstrumentedHashMap{" +
                "m=" + super.toString() +
                ", addCount=" + addCount +
                '}';
    }

    public int getAddCount() {
        return this.addCount;
    }

    public static void testPutMapPerformance(InstrumentedHashMap m) {
        long start, end;
        for (int i = 0; i < 10000000; i++) {
            FixedBond fixedBond = new FixedBond();
            start = System.currentTimeMillis();
            m.put("key" + i, fixedBond);
            end = System.currentTimeMillis();
            System.out.println("key" + i + " put time consuming: " + (end - start));
        }
    }

    public static void testGetMapPerformance(InstrumentedHashMap m) {
        long start, end;
        for (int i = 10000; i < 10000000; i++) {
            start = System.currentTimeMillis();
            m.get("key" + i);
            end = System.currentTimeMillis();
            System.out.println(" get time consuming: " + (end - start));
        }
    }

    static class TestPutMapThread extends Thread {
        private InstrumentedHashMap m;

        public TestPutMapThread(InstrumentedHashMap m) {
            this.m = m;
        }

        @Override
        public void run() {
            testPutMapPerformance(m);
        }
    }


    static class TestGetMapThread extends Thread {
        private InstrumentedHashMap m;

        public TestGetMapThread(InstrumentedHashMap m) {
            this.m = m;
        }

        @Override
        public void run() {
            testGetMapPerformance(m);
        }
    }

    // BasePaymentObject
    static class BasePaymentObject {
        private Long id;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }
    }

    //BO  PO
    // BackAccount   FixedBond  Coupon  Contract  Marketing

    // FixedBondKey Definition
    static class FixedBondKey {

        private Long id;
        private String name;
        // 4~10

        public FixedBondKey(Long id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof FixedBondKey)) return false;
            FixedBondKey that = (FixedBondKey) o;
            return id.equals(that.id) &&
                    name.equals(that.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, name);
        }
    }


    // FixedBond Coupon优惠券 ... Contract  Marketing   BO PO
    static class FixedBond extends BasePaymentObject {
        private Long id;
        private String name;
        private Date startDate;
        private Date endDate;
        //rate
        // calculation
        // ... > 100 attributes >200 fields

        @Override
        public Long getId() {
            return id;
        }

        @Override
        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Date getStartDate() {
            return startDate;
        }

        public void setStartDate(Date startDate) {
            this.startDate = startDate;
        }

        public Date getEndDate() {
            return endDate;
        }

        public void setEndDate(Date endDate) {
            this.endDate = endDate;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof FixedBond)) return false;
            FixedBond fixedBond = (FixedBond) o;
            return id.equals(fixedBond.id) &&
                    name.equals(fixedBond.name) &&
                    startDate.equals(fixedBond.startDate) &&
                    endDate.equals(fixedBond.endDate);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, name, startDate, endDate);
        }
    }

    public static void main(String[] args) {
        InstrumentedHashMap instrumentedHashMap = new InstrumentedHashMap(16, 0.75f);

//        instrumentedHashMap.put("key1", "value1");
//        instrumentedHashMap.put("key2", "value2");
//        instrumentedHashMap.put("key3", "value3");
//
//        instrumentedHashMap.putAll(instrumentedHashMap);
//
//        System.out.println(instrumentedHashMap.toString());
//
//        System.out.println(" getAddCount "+ instrumentedHashMap.getAddCount());


        // 自定义Key值的实现, 没有重写equals()和 hashcode()方法 1

        instrumentedHashMap.put(new FixedBondKey(1000l, "bfbm-oo"), "bfbm-value-oo");
        instrumentedHashMap.put(new FixedBondKey(2000l, "bfbm-java"), "bfbm-value-java");
        instrumentedHashMap.put(new FixedBondKey(3000l, "bfbm-python"), "bfbm-value-python");
        instrumentedHashMap.put(new FixedBondKey(3000l, "bfbm-python"), "bfbm-value-midi");

        System.out.println(instrumentedHashMap.toString());
//
//        System.out.println(instrumentedHashMap.get(new FixedBondKey(1000l, "bfbm-oo")));
//        System.out.println(instrumentedHashMap.get(new FixedBondKey(2000l, "bfbm-java")));
//        System.out.println(instrumentedHashMap.get(new FixedBondKey(3000l, "bfbm-python")));


        System.out.println("===========================================");

        // 自定义Key值的实现, 没有重写equals()和 hashcode()方法  2

//        FixedBondKey key1 = new FixedBondKey(1000l, "bfbm-oo");
//        FixedBondKey key2 = new FixedBondKey(2000l, "bfbm-java");
//        FixedBondKey key3 = new FixedBondKey(3000l, "bfbm-python");
//        instrumentedHashMap.put(key1, "bfbm-value-oo");
//        instrumentedHashMap.put(key2, "bfbm-value-java");
//        instrumentedHashMap.put(key3, "bfbm-value-python");
//        instrumentedHashMap.put(key3, "bfbm-value-midi");
//
//        System.out.println(instrumentedHashMap.toString());
//
//        System.out.println(instrumentedHashMap.get(key1));
//        System.out.println(instrumentedHashMap.get(key2));
//        System.out.println(instrumentedHashMap.get(key3));


        // 自定义Key Object的实现, Value为Object
//        FixedBond value = new FixedBond();
//        map.put(new FixedBondKey(1000l, "bfbm-oo"), value);


        TestPutMapThread t1 = new TestPutMapThread(instrumentedHashMap);
        t1.start();


        TestGetMapThread t2 = new TestGetMapThread(instrumentedHashMap);
        t2.start();
        try {
            t2.wait(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }

//
//        TestGetMapThread t3 = new TestGetMapThread(instrumentedHashMap);
//        t3.start();

    }

}
