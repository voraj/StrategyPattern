package com.p532.brickout.bean;

import com.p532.brickout.shape.Ball;
import com.p532.brickout.shape.Brick;
import com.p532.brickout.shape.Clock;
import com.p532.brickout.shape.Paddle;
import com.p532.brickout.util.GameParameter;

public class GameState {

	private GameParameter gameParameter;
	private Ball ball;
	private Paddle paddle;
	private Brick bricks[][];
	private Clock clock;
	
	public GameParameter getGameParameter() {
		return gameParameter;
	}
	public void setGameParameter(GameParameter gameParameter) {
		this.gameParameter = gameParameter;
	}
	public Ball getBall() {
		return ball;
	}
	public void setBall(Ball ball) {
		this.ball = ball;
	}
	public Paddle getPaddle() {
		return paddle;
	}
	public void setPaddle(Paddle paddle) {
		this.paddle = paddle;
	}
	public Brick[][] getBricks() {
		return bricks;
	}
	public void setBricks(Brick[][] bricks) {
		this.bricks = bricks;
	}
	public Clock getClock() {
		return clock;
	}
	public void setClock(Clock clock) {
		this.clock = clock;
	}
	
	
	
}
