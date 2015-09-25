package com.p532.brickout.intf;

import java.io.Serializable;

public interface GameCommand extends Serializable {

	void executeCommand();
	void undo();
}
