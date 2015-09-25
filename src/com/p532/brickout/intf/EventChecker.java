package com.p532.brickout.intf;

import java.io.Serializable;

public interface EventChecker extends Serializable{

	public void check();
	public void setX(int x);
	public void setY(int y);
}
