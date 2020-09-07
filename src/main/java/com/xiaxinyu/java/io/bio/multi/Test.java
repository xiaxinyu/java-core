package com.xiaxinyu.java.io.bio.multi;

import java.util.Scanner;

public class Test {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		while (scanner.hasNext()) {
			String msg = scanner.next();
			if(msg.equalsIgnoreCase("exit")) {
				break;
			}
			System.out.println(msg);
		}
	}
}
