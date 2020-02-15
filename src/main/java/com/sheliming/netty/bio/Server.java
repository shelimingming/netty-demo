package com.sheliming.netty.bio;

import javax.sql.DataSource;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    //默认端口号
    private static final int DEFAULT_PORT = 8888;

    private static ServerSocket serverSocket;


    public static void start() {
        try {
            serverSocket = new ServerSocket(DEFAULT_PORT);
            System.out.println("服务端已启动。。。port:" + DEFAULT_PORT);

            while (true) {
                Socket socket = serverSocket.accept();
                new Thread(new ServerHandler(socket)).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                serverSocket = null;
                System.out.println("服务端已关闭");
            }
        }
    }

    public static void main(String[] args) {
        Server.start();
    }
}
