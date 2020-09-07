package com.xiaxinyu.java.io.bio.single.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
	public void init() {
		try {
			Socket socket = new Socket("localhost", 9000);
			new ClientListener(socket).start();
			new ClientSender(socket).start();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new Client().init();
	}
}

class ClientListener extends Thread {
	private Socket socket = null;

	public ClientListener(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		BufferedReader bufferedReader = null;
		try {
			bufferedReader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
			String info = null;
			while ((socket != null && !socket.isClosed()) && (info = bufferedReader.readLine()) != null) {
				System.out.println("I am client,server say: " + info);
				if ("exit".equalsIgnoreCase(info)) {
					break;
				}
			}
			System.out.println("ClientListener exit!");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (bufferedReader != null) {
					bufferedReader.close();
				}
				if (this.socket != null && !this.socket.isClosed()) {
					this.socket.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}

class ClientSender extends Thread {
	private Socket socket = null;

	public ClientSender(Socket socket) {
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
			System.out.println("ClientSender exit!");
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
