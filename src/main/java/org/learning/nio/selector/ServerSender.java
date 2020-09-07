package org.learning.nio.selector;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;
import java.util.Vector;

public class ServerSender extends Thread {
	private Vector<SocketChannel> channels = null;

	public ServerSender(Vector<SocketChannel> channels) {
		this.channels = channels;
	}

	@Override
	public void run() {
		Scanner scanner = new Scanner(System.in);
		try {
			String line = "";
			while ((line = scanner.nextLine()) != null) {
				for (SocketChannel channel : this.channels) {
					channel.write(ByteBuffer.wrap(line.getBytes()));
				}
				if ("exit".equalsIgnoreCase(line)) {
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (scanner != null) {
				scanner.close();
			}
		}
	}
}