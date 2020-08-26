package com.xiaxinyu.java.clazz.loader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class CustomClassLoader extends ClassLoader {
	private String loaderName; // 类加载器名称
	private String path = ""; // 加载类的路径
	private final String fileType = ".class";

	public CustomClassLoader(String name) {
		super(); // 应用类加载器为该类的父类
		this.loaderName = name;
	}

	public CustomClassLoader(ClassLoader parent, String name) {
		super(parent);
		this.loaderName = name;
	}

	public String getPath() {
		return this.path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Override
	public String toString() {
		return this.loaderName;
	}

	@Override
	public Class<?> findClass(String name) throws ClassNotFoundException {
		byte[] data = loaderClassData(name);
		return this.defineClass(name, data, 0, data.length);
	}

	// 读取.class文件
	private byte[] loaderClassData(String name) {
		InputStream is = null;
		byte[] data = null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			is = new FileInputStream(new File(path + name + fileType));
			int c = 0;
			while (-1 != (c = is.read())) {
				baos.write(c);
			}
			data = baos.toByteArray();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (is != null)
					is.close();
				if (baos != null)
					baos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return data;
	}
}
