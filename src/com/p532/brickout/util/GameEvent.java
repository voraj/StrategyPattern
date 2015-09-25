package com.p532.brickout.util;

public class GameEvent {

	private int time, action;
	Object eventObject;

	
	public Object getEventObject() {
		return eventObject;
	}
	public void setEventObject(Object eventObject) {
		this.eventObject = eventObject;
	}
	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public int getAction() {
		return action;
	}

	public void setAction(int action) {
		this.action = action;
	}

	
	public GameEvent(int time, int action) {
		this.time = time;
		this.action = action;
	}

	
}
