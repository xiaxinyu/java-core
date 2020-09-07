package com.xiaxinyu.java.io.bio.single;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public void init() {
		ServerSocket server = null;
		Socket socket = null;
		BufferedReader bufferedReader = null;
		PrintWriter printWriter = null;
		try {
			server = new ServerSocket(9000);
			//server.accept() blocking method
			socket = server.accept();
			bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String info = null;
			while ((info = bufferedReader.readLine()) != null) {
				System.out.println("I am server,client say: " + info);
			}
			socket.shutdownInput();

			printWriter = new PrintWriter(socket.getOutputStream());
			printWriter.write("Welcome!!!");
			printWriter.flush();
			socket.shutdownOutput();

			socket.close();
			server.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (printWriter != null) {
					printWriter.close();
				}
				if (bufferedReader != null) {
					bufferedReader.close();
				}
				if (socket != null) {
					socket.close();
				}
				if (server != null) {
					server.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		new Server().init();
	}
}
