package com.dsd26.bachkhoaxanh.socket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class ServerProgram {
	
	public static void main(String[] args) {
		System.out.println("khoi tao server");
		Server server = new Server(9998);
	}
	
}

