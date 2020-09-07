package org.learning.nio.selector;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Client {
	public void init() {
		try {
			SocketChannel socketChannel = SocketChannel.open();
			socketChannel.configureBlocking(false);
			socketChannel.connect(new InetSocketAddress("127.0.0.1", 30000));
			if (socketChannel.finishConnect()) {
				new Listener(socketChannel, Type.CLIENT).start();

				Scanner scanner = new Scanner(System.in);
				String line = null;
				while ((line = scanner.nextLine()) != null) {
					try {
						socketChannel.write(StandardCharsets.UTF_8.encode(line));
					} catch (IOException e) {
						e.printStackTrace();
					}
					if ("exit".equals(line)) {
						break;
					}
				}
				scanner.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception {
		new Client().init();
	}
}