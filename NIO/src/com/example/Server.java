package com.example;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

//参考 https://blog.csdn.net/difffate/article/details/69660666
public class Server {
    private static String Hello(String input) {
        String message = String.format("<h1>Hello</h1>" +
                "<div>your request is: <div>" +
                "<pre><code>%s</code></pre><div></div>", input);

        var HTTPFormat = "HTTP/1.1 200 OK\r\n" +
                "Content-Type: text/html\r\n" +
                "Content-Length: %d\r\n\r\n" +
                "%s";
        return String.format(HTTPFormat, message.length(), message);
    }

    public static void main(String[] args) throws IOException {
        //打开事件监听
        Selector selector = Selector.open();
        //打开通道
        ServerSocketChannel ssc = ServerSocketChannel.open();
        //绑定监听端口，通过socket
        System.out.println("Listen to http://localhost:8900");
        ssc.socket().bind(new InetSocketAddress(8900));
        //设置为非阻塞
        ssc.configureBlocking(false);
        //注册连接事件
        ssc.register(selector, SelectionKey.OP_ACCEPT);
        while (true) {
            int keys = selector.select(1000);
            if (keys > 0) { //有新的事件
                for (var key : selector.selectedKeys()) {
                    if (key.isAcceptable()) {
                        //获取ServerSocketChannel
                        ServerSocketChannel channel = (ServerSocketChannel) key.channel();
                        //获取SocketChannel
                        SocketChannel socketChannel = channel.accept();
                        if (socketChannel == null) {
                            continue;
                        }
                        socketChannel.configureBlocking(false);
                        socketChannel.register(selector, SelectionKey.OP_READ);
                    } else if (key.isReadable()) {
                        //定义Buffer
                        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                        //获取SocketChannel
                        SocketChannel socketChannel = (SocketChannel) key.channel();
                        //将数据读入buffer
                        socketChannel.read(byteBuffer);
                        //将buffer转成byte数组
                        byte[] array = byteBuffer.array();
                        String msg = new String(array).trim();
                        System.out.println("Receive Msg：" + msg);
                        /*
                        if ("exit".equalsIgnoreCase(msg)) {
                            socketChannel.close();
                            ssc.close();
                            selector.close();
                            System.exit(0);
                        }
                        */
                        //获取发送消息用的buffer，wrap方法
                        ByteBuffer out = ByteBuffer.wrap(Server.Hello(msg).getBytes());
                        //写入channel
                        socketChannel.write(out);
                    }
                }
                //注意这里要移除键
                selector.selectedKeys().clear();
            }
        }
    }
}
