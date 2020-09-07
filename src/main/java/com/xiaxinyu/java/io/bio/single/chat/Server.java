package com.xiaxinyu.java.io.bio.single.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
	public void init() {
		try {
			ServerSocket serverSocket = new ServerSocket(9000);
			// server.accept() blocking method
			Socket socket = serverSocket.accept();
			new ServerListener(socket).start();
			new ServerSender(socket).start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new Server().init();
	}
}

class ServerListener extends Thread {
	private Socket socket = null;

	public ServerListener(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		BufferedReader bufferedReader = null;
		try {
			bufferedReader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
			String info = null;
			while ((info = bufferedReader.readLine()) != null && (socket != null && !socket.isClosed())) {
				System.out.println("I am server,client say: " + info);
				if ("exit".equalsIgnoreCase(info)) {
					break;
				}
			}
			System.out.println("ServerListener exit!");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (bufferedReader != null) {
					bufferedReader.close();
				}
				if (this.socket != null) {
					this.socket.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}

class ServerSender extends Thread {
	private Socket socket = null;

	public ServerSender(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		Scanner scanner = null;
		OutputStream outputStream = null;
		try {
			scanner = new Scanner(System.in);
			outputStream = socket.getOutputStream();
			String line = "";
			while ((line = scanner.nextLine()) != null && (this.socket != null && !this.socket.isClosed())) {
				String message = line + "\n";
				outputStream.write(message.getBytes());
				outputStream.flush();
				if ("exit".equalsIgnoreCase(line)) {
					break;
				}
			}
			System.out.println("ServerSender exit!");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (scanner != null) {
					scanner.close();
				}
				if (this.socket != null && !this.socket.isClosed()) {
					outputStream.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
