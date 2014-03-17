/* JNativeHook: Global keyboard and mouse hooking for Java.
 * Copyright (C) 2006-2014 Alexander Barker.  All Rights Received.
 * http://code.google.com/p/jnativehook/
 *
 * JNativeHook is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * JNativeHook is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.zolomon.dashboard.client.desktop;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Semaphore;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;
import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseInputListener;
import org.jnativehook.mouse.NativeMouseWheelEvent;
import org.jnativehook.mouse.NativeMouseWheelListener;

import com.zolomon.dashboard.shared.NativeEvent;

public class NativeHook implements NativeKeyListener, NativeMouseInputListener,
		NativeMouseWheelListener {

	private Semaphore semaphore;
	private ConcurrentLinkedQueue<NativeEvent> queue;

	public NativeHook(Semaphore semaphore, ConcurrentLinkedQueue<NativeEvent> workQueue)
			throws NativeHookException, InterruptedException {
		this.semaphore = semaphore;
		this.queue = workQueue;
		
		GlobalScreen.registerNativeHook();
		GlobalScreen.getInstance().addNativeKeyListener(this);
		GlobalScreen.getInstance().addNativeMouseListener(this);
		GlobalScreen.getInstance().addNativeMouseMotionListener(this);
		GlobalScreen.getInstance().addNativeMouseWheelListener(this);
		

		// GlobalScreen.unregisterNativeHook();
	}
	
	private void addEvent(NativeEvent e) {
		this.queue.add(e);
		System.out.println(e);
		this.semaphore.release(1);
	}

	public void nativeKeyPressed(NativeKeyEvent e) {
		addEvent(new NativeEvent(e));
		
	}

	public void nativeKeyReleased(NativeKeyEvent e) {
		addEvent(new NativeEvent(e));
		
	}

	public void nativeKeyTyped(NativeKeyEvent e) {
		addEvent(new NativeEvent(e));
	}

	public void nativeMouseClicked(NativeMouseEvent e) {
		addEvent(new NativeEvent(e));
	}

	public void nativeMousePressed(NativeMouseEvent e) {
		addEvent(new NativeEvent(e));
	}

	public void nativeMouseReleased(NativeMouseEvent e) {
		addEvent(new NativeEvent(e));
	}

	public void nativeMouseMoved(NativeMouseEvent e) {
		addEvent(new NativeEvent(e));
	}

	public void nativeMouseDragged(NativeMouseEvent e) {
		addEvent(new NativeEvent(e));
	}

	public void nativeMouseWheelMoved(NativeMouseWheelEvent e) {
		addEvent(new NativeEvent(e));
	}
}