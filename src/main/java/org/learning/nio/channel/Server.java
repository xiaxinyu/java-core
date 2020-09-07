package org.learning.nio.channel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class Server {
	private static final int PORT = 9090;

	public void init() {
		ServerSocketChannel serverSocketChannel = null;
		try {
			serverSocketChannel = ServerSocketChannel.open();
			serverSocketChannel.socket().bind(new InetSocketAddress(PORT));
			serverSocketChannel.configureBlocking(false);

			while (true) {
				// accept()在非阻塞模式中，若建立连接，则返回SocketChannel；否则返回null
				SocketChannel socketChannel = serverSocketChannel.accept();
				if (socketChannel != null) {
					new Listener(socketChannel, Type.SERVER).start();
					new Sender(socketChannel).start();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			try {
				if (serverSocketChannel != null) {
					serverSocketChannel.close();
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		new Server().init();
	}
}