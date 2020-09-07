package com.xiaxinyu.java.io.bio.single;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	public void init() {
		Socket socket = null;
		PrintWriter printWriter = null;
		BufferedReader bufferedReader = null;
		try {
			socket = new Socket("localhost", 9000);

			printWriter = new PrintWriter(socket.getOutputStream());
			printWriter.write("I am summer!!!");
			printWriter.flush();
			socket.shutdownOutput();

			bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String info = null;
			while ((info = bufferedReader.readLine()) != null) {
				System.out.println("I am client,server say: " + info);
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bufferedReader != null) {
					bufferedReader.close();
				}
				if (printWriter != null) {
					printWriter.close();
				}
				if (socket != null) {
					socket.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		new Client().init();
	}
}
