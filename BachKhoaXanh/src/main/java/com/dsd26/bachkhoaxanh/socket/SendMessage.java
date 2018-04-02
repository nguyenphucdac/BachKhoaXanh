package com.dsd26.bachkhoaxanh.socket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class SendMessage extends Thread {
    protected List<Socket> lstClient;
    protected String userInput;
    protected BufferedReader console;
    protected ServerSocket socketServer;
    protected BufferedWriter os;

    public SendMessage(List<Socket> lstClient, ServerSocket socketServer) {
        this.lstClient = lstClient;
        this.socketServer = socketServer;
        this.userInput = null;
        this.start();
    }
    @Override
    public void run() {
        System.out.println("New Communication Thread Started");
       
        try {
            if (lstClient.size() > 0) {
                this.console = new BufferedReader(new InputStreamReader(
                        System.in));
                
				while ((this.userInput = console.readLine()) != null) {
					for (Socket client : lstClient) {
						os = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
						os.write(userInput);
						os.newLine();
						os.flush();
						Thread.currentThread();
						Thread.sleep(1 * 1000);
					}

				}
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
