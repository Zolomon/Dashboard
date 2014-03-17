package com.zolomon.dashboard.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketImpl;
import java.util.List;

public class DashboardServer {

	private int port;
	private List<ServerThread> threads;
	
	public DashboardServer(int port) {
		this.port = port;
	}

	public static void main(String[] args) throws IOException {
		DashboardServer server = new DashboardServer(8765);
		server.run();
	}

	private void run() throws IOException {
		ServerSocket server = new ServerSocket(port);
		
		while(true) {
			System.out.println("SERVER> Waiting for new clients ...");
			Socket socket = server.accept();
			System.out.println("SERVER> Accepted connection: " + socket);
			ServerThread serverThread = new ServerThread(socket);
			serverThread.start();
		}
	}

}
