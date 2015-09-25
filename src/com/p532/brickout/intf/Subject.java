package com.p532.brickout.intf;

public interface Subject {
	
	public void register(Observer o);
	public void unregister(Observer o);
	public void notifyObservers(int timeStep);
}
