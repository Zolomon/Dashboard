package com.zolomon.dashboard.client.desktop;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Semaphore;

import com.google.gson.Gson;
import com.zolomon.dashboard.shared.NativeEvent;

public class ClientThread extends Thread {

	private ConcurrentLinkedQueue<NativeEvent> queue;
	private int port;
	private String hostname;
	private Socket socket;
	private Semaphore semaphore;

	public ClientThread(int port, String hostname, Semaphore semaphore,
			ConcurrentLinkedQueue<NativeEvent> queue) {
		this.port = port;
		this.hostname = hostname;
		this.semaphore = semaphore;
		this.queue = queue;
	}

	@Override
	public void run() {
		try {
			socket = new Socket(this.hostname, this.port);
			System.out.println("CLIENT> " + socket);

			String msg = null;
			PrintStream ps = new PrintStream(socket.getOutputStream());
			BufferedReader br = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));

			while (true) {
//				while (queue.isEmpty()) {
//					Thread.sleep(50);
//				}
				semaphore.acquire(1);				
				Gson gson = new Gson();
				String obj = gson.toJson(queue);
				ps.print(obj + "\n");
				ps.flush();
				queue.clear();
			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
