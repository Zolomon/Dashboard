package com.zolomon.dashboard.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class ServerThread extends Thread {
	private Socket socket;

	public ServerThread(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		try {
			String msg = null;
			PrintStream ps = null;
			BufferedReader br = null;

			ps = new PrintStream(this.socket.getOutputStream());
			br = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));

			while ((msg = br.readLine()) != null) {
				System.out.println("SERVER> " + msg);
				//ps.print("SERVER> " + msg);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println(this + " has disconnected ...");
	}
}
