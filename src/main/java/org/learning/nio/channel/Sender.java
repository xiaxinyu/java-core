package org.learning.nio.channel;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

public class Sender extends Component {
	private SocketChannel socketChannel;

	public Sender(SocketChannel socketChannel) {
		this.socketChannel = socketChannel;
	}

	@Override
	public void run() {
		Scanner scanner = new Scanner(System.in);
		try {
			String line = "";
			while ((line = scanner.nextLine()) != null) {
				this.socketChannel.write(ByteBuffer.wrap(line.getBytes()));
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