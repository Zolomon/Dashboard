package com.zolomon.dashboard.client.desktop;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Semaphore;
import java.util.logging.Handler;
import java.util.logging.Logger;

import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;

import com.zolomon.dashboard.shared.NativeEvent;

class DesktopClient {
	private NativeHook hook;
	private ConcurrentLinkedQueue<NativeEvent> workQueue;
	private ClientThread clientThread;
	private Semaphore semamphore;

	public DesktopClient() throws NativeHookException, InterruptedException {
		workQueue = new ConcurrentLinkedQueue<NativeEvent>();
		semamphore = new Semaphore(0);
		hook = new NativeHook(semamphore, workQueue);
		clientThread = new ClientThread(8765, "127.0.0.1", semamphore,
				workQueue);
	}

	public static void main(String[] args) throws NativeHookException,
			InterruptedException {

		System.out.println("CLIENT> ");

		DesktopClient client = new DesktopClient();
		client.start();

	}

	// public void run() throws InterruptedException {
	// long time = System.currentTimeMillis();
	// long duration = 10000;
	// long next_tick = time + duration;
	// while (true) {
	// if (time >= next_tick) {
	// push(new NativeEvent(new NativeKeyEvent(0, time, 0, 0, 0, 'a')));
	// next_tick = time + duration;
	// semamphore.release(1);
	// }
	// time = System.currentTimeMillis();
	// Thread.sleep(duration);
	// }
	// }
	//
	// private void push(NativeEvent nativeEvent) {
	// this.workQueue.add(nativeEvent);
	// System.out.println("Pushing");
	// }

	private void start() {
		this.clientThread.start();
	}
}