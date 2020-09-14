package com.xiaxinyu.java.io.nio.async;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Objects;

public class Server {

    public void init() throws IOException {
        ServerSocketChannel server = ServerSocketChannel.open();
        server.socket().bind(new InetSocketAddress("127.0.0.1", 30000));
        server.configureBlocking(false);

        while (true) {
            SocketChannel socketChannel = server.accept();
            if (Objects.nonNull(socketChannel)) {
                SocketAddress remoteAddress = socketChannel.getRemoteAddress();
                System.out.println("RemoteAddress = " + remoteAddress);
            } else {
                System.out.println("未连接......");
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new Server().init();
    }
}
