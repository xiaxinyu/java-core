package org.learning.nio.channel;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class Listener extends Component {
	private SocketChannel socketChannel;
	private Type type;

	public Listener(SocketChannel socketChannel, Type type) {
		this.socketChannel = socketChannel;
		this.type = type;
	}

	@Override
	public void run() {
		try {
			ByteBuffer buf = ByteBuffer.allocate(1024);
			int numBytesRead;
			while ((numBytesRead = socketChannel.read(buf)) != -1) {
				if (numBytesRead == 0) {
					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					continue;
				}

				buf.flip();
				String message = "";
				while (buf.hasRemaining()) {
					message += (char) buf.get();
				}
				if (!"".equals(message)) {
					System.out.println(getReverseTitle(this.type) + message);
					buf.clear();
					if ("exit".equals(message)) {
						break;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (this.socketChannel != null) {
				try {
					this.socketChannel.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			System.out.println(getFrontalTitle(this.type) + " I'm gonna quit.");
		}
	}
}