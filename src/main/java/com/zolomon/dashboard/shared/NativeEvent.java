package com.zolomon.dashboard.shared;

import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseWheelEvent;

public class NativeEvent {
	private NativeKeyEvent keyEvent;
	private NativeMouseEvent mouseEvent;
	private NativeMouseWheelEvent mouseWheelEvent;

	public NativeEvent() {
	}

	public NativeEvent(NativeKeyEvent e) {
		keyEvent = e;
	}

	public NativeEvent(NativeMouseEvent e) {
		mouseEvent = e;
	}

	public NativeEvent(NativeMouseWheelEvent e) {
		mouseWheelEvent = e;
	}

	public NativeKeyEvent getKeyEvent() {
		return keyEvent;
	}

	public void setKeyEvent(NativeKeyEvent keyEvent) {
		this.keyEvent = keyEvent;
	}

	public NativeMouseEvent getMouseEvent() {
		return mouseEvent;
	}

	public void setMouseEvent(NativeMouseEvent mouseEvent) {
		this.mouseEvent = mouseEvent;
	}

	public NativeMouseWheelEvent getMouseWheelEvent() {
		return mouseWheelEvent;
	}

	public void setMouseWheelEvent(NativeMouseWheelEvent mouseWheelEvent) {
		this.mouseWheelEvent = mouseWheelEvent;
	}

	@Override
	public String toString() {
		return this.keyEvent != null ? "KeyEvent:" + this.keyEvent.getKeyChar()
				: this.mouseEvent != null ? "MouseEvent:["
						+ (this.mouseEvent.getButton() + ",("
								+ this.mouseEvent.getX() + ","
								+ this.mouseEvent.getY() + ")]")
						: this.mouseWheelEvent != null ? "MouseWheelEvent:["
								+ (this.mouseEvent.getButton() + ",("
										+ this.mouseEvent.getX() + ","
										+ this.mouseEvent.getY() + ")]")
								: "NONE";
	}
}
