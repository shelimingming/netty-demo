package com.sheliming.netty.bio;

import java.io.*;
import java.net.Socket;

public class Client {
    //默认端口号
    private static final String DEFAULT_IP = "127.0.0.1";
    //默认端口号
    private static final int DEFAULT_PORT = 8888;


    public static void send(String msg) {
        Socket socket = null;
        BufferedReader in = null;
        PrintWriter out = null;


        try {
            socket = new Socket(DEFAULT_IP, DEFAULT_PORT);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //必须加上autoFlush为true，不然要手动flush
            out = new PrintWriter(socket.getOutputStream(), true);

            out.println(msg);
            String s = in.readLine();
            System.out.println(s);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (out != null) {
                out.close();
            }
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {

        Client.send("hello");

    }
}
