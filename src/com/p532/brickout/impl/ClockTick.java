package com.p532.brickout.impl;

import com.p532.brickout.intf.GameCommand;
import com.p532.brickout.shape.Clock;
import com.p532.brickout.util.Constants;

public class ClockTick implements GameCommand {

	private Clock clock;
	
	
	public ClockTick(Clock clock) {
		super();
		this.clock = clock;
	}

	@Override
	public void executeCommand() {
		
		 clock.update(Constants.TIME_STEP);

	}

	@Override
	public void undo() {
		 clock.update(-Constants.TIME_STEP);
		
	}

}
