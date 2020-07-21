package com.xiaxinyu.java.classloader;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ClientInvoke {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// MyClassLoader的父类加载器为系统默认的加载器AppClassLoader
		com.xiaxinyu.java.classloader.CustomClassLoader myCLoader = new com.xiaxinyu.java.classloader.CustomClassLoader("CustomClassLoader");
		// 指定MyClassLoader的父类加载器为ExtClassLoader
		// MyClassLoader myCLoader = new
		// MyClassLoader(ClassLoader.getSystemClassLoader().getParent(),"MyClassLoader");
		myCLoader.setPath("C:\\Users\\Administrator\\");
		Class<?> clazz;
		try {
			clazz = myCLoader.loadClass("Test");
			Field[] filed = clazz.getFields(); // 获取加载类的属性字段
			Method[] methods = clazz.getMethods(); // 获取加载类的方法字段
			System.out.println("该类的类加载器为：" + clazz.getClassLoader());
			System.out.println("该类的类加载器的父类为:" + clazz.getClassLoader().getParent());
			System.out.println("该类的名称为：" + clazz.getName());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
