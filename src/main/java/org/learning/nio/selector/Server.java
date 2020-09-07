package org.learning.nio.selector;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Vector;

public class Server {
	private Selector selector = null;
	private Vector<SocketChannel> channels = new Vector<SocketChannel>();
	private static final int TIMEOUT = 3000;

	public void init() throws IOException {
		selector = Selector.open();
		ServerSocketChannel server = ServerSocketChannel.open();
		server.socket().bind(new InetSocketAddress("127.0.0.1", 30000));
		server.configureBlocking(false);

		// 将 channel 注册到 selector 中.
		// 通常我们都是先注册一个 OP_ACCEPT 事件, 然后在 OP_ACCEPT 到来时, 再将这个 Channel 的 OP_READ
		// 注册到 Selector 中.
		server.register(selector, SelectionKey.OP_ACCEPT);

		new ServerSender(channels).start();

		while (true) {
			// 通过调用 select 方法, 阻塞地等待 channel I/O 可操作
			if (selector.select(TIMEOUT) == 0) {
				System.out.print(".");
				continue;
			}

			// 获取 I/O 操作就绪的 SelectionKey, 通过 SelectionKey 可以知道哪些 Channel 的哪类 I/O 操作已经就绪.
			Iterator<SelectionKey> keyIterator = selector.selectedKeys().iterator();

			while (keyIterator.hasNext()) {
				SelectionKey sk = keyIterator.next();

				/*
				 * 当获取一个 SelectionKey 后, 就要将它删除, 表示我们已经对这个 IO 事件进行了处理. 
				 * 注意, 在每次迭代时, 我们都调用
				 * "keyIterator.remove()" 将这个 key 从迭代器中删除, 因为 select() 方法仅仅是简单地将就绪的 IO 操作放到
				 * selectedKeys 集合中, 因此如果我们从 selectedKeys 获取到一个 key, 但是没有将它删除, 那么下一次 select 时,
				 * 这个 key 所对应的 IO 事件还在 selectedKeys 中.
				 */
				keyIterator.remove();

				if (sk.isAcceptable()) {
					doAccept(server);
				}

				if (sk.isReadable()) {
					doRead(sk);
				}
			}
		}
	}

	private void doAccept(ServerSocketChannel server) throws IOException {
		SocketChannel socketChannel = server.accept();
		socketChannel.configureBlocking(false);
		socketChannel.register(selector, SelectionKey.OP_READ);
		channels.add(socketChannel);
	}

	private void doRead(SelectionKey selectionKey) throws IOException {
		SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
		new Listener(socketChannel, Type.SERVER).start();
	}

	public static void main(String[] args) throws IOException {
		new Server().init();
	}
}
