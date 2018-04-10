package com.dsd26.bachkhoaxanh.socket;

import java.io.IOException;
import java.util.ArrayList;

import com.dsd26.bachkhoaxanh.socket.Network.ChatMessage;
import com.dsd26.bachkhoaxanh.socket.Network.RegisterName;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;


public class ServerConnection {
	Server server;

	public ServerConnection(){
		
		System.out.println("Khoi tao server");
		
		server = new Server() {
			protected Connection newConnection() {
				// By providing our own connection implementation, we can store per
				// connection state without a connection ID to state look up.
				return new ClientConnection();
			}
		};

		// For consistency, the classes to be sent over the network are
		// registered by the same method for both the client and server.
		Network.register(server);

		
		server.addListener(new Listener() {
			public void received(Connection c, Object object) {
				ClientConnection clientConnection = (ClientConnection) c;
				if(object instanceof RegisterName) {
					if(clientConnection.name != null) return;
					
					String name = ((RegisterName)object).name;
					
					if(name == null) return;
					name = name.trim();
					if(name.length() == 0) return;
					
					clientConnection.name = name;
					
					ChatMessage chatMessage = new ChatMessage();
					chatMessage.text = name + " connected ";
					
					System.out.println(chatMessage.text);
					
					//server.sendToAllExceptTCP(clientConnection.getID(), chatMessage);
					
					updateNames();
					
					return;
				}
				
				if(object instanceof ChatMessage) {
					if(clientConnection.name == null) return;
					
					ChatMessage chatMessage = (ChatMessage) object;
					
					String message = chatMessage.text;
					if(message == null) return;
					message = message.trim();
					if(message == "") return;
					if(message.length() == 0) return;
					
					chatMessage.text = clientConnection.name + ": " + message;
					server.sendToAllTCP(chatMessage);
					return;
				}
			}
			
			public void disconnected (Connection c) {
				ClientConnection clientConnection = (ClientConnection)c;
				if(clientConnection.name != null) {
					ChatMessage chatMessage = new ChatMessage();
					chatMessage.text = clientConnection.name + ": disconnected";
					server.sendToAllTCP(chatMessage);
					updateNames();
				}
			}
			
		});
		try {
			server.bind(Network.port);
			server.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	void updateNames () {
		// Collect the names for each connection.
		Connection[] connections = server.getConnections();
		ArrayList names = new ArrayList(connections.length);
		for (int i = connections.length - 1; i >= 0; i--) {
			ClientConnection connection = (ClientConnection)connections[i];
			names.add(connection.name);
		}
		// Send the names to everyone.
		com.dsd26.bachkhoaxanh.socket.Network.UpdateNames updateNames = new com.dsd26.bachkhoaxanh.socket.Network.UpdateNames();
		updateNames.names = (String[])names.toArray(new String[names.size()]);
		server.sendToAllTCP(updateNames);
	}
	
	static class ClientConnection extends Connection {
		public String name;
	}

//	public static void main(String[] args) throws IOException {
//		new ServerConnection();
//	}
}
