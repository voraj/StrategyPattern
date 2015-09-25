package com.p532.brickout.impl;


import com.p532.brickout.intf.GameCommand;
import com.p532.brickout.shape.Ball;
import com.p532.brickout.util.Constants;

public class BallMove implements GameCommand {

	private Ball ball;
	
	
	public BallMove(Ball ball) {
		super();
		this.ball = ball;
	}


	@Override
	public void executeCommand() {
		
		ball.update(Constants.TIME_STEP);

	}


	@Override
	public void undo() {
		
		ball.update(-Constants.TIME_STEP);
		
	}

}
