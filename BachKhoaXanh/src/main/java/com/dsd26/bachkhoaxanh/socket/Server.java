package com.dsd26.bachkhoaxanh.socket;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Server extends Thread {
    public static ServerSocket socketServer;
    public static List<Socket> lstClient;
    private int port;

    public Server(int port) {
        try {
            this.socketServer = new ServerSocket(port);
            this.port = port;
            System.out.println("New server initialized!");
            lstClient = Collections.synchronizedList(new ArrayList<Socket>());
            this.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void run() {
        while (true) {
            try {
            	
                Socket client = socketServer.accept();
                System.out.println(client + "connected");
                lstClient.add(client);
                new SendMessage(lstClient, socketServer);
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
