package com.xiaxinyu.java.io.bio.multi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public void init() {
		ServerSocket server = null;
		try {
			server = new ServerSocket(9000);
			while (true) {
				try {
					//server.accept() blocking method
					new SocketHandler(server.accept()).start();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (server != null) {
				try {
					server.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) {
		new Server().init();
	}
}

class SocketHandler extends Thread {
	private Socket socket;

	public SocketHandler(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		BufferedReader bufferedReader = null;
		PrintWriter printWriter = null;

		try {
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
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
