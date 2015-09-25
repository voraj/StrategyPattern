package com.p532.brickout.impl;

import com.p532.brickout.gameui.Board;
import com.p532.brickout.intf.EventChecker;
import com.p532.brickout.shape.Brick;
import com.p532.brickout.util.Constants;
import com.p532.brickout.util.GameEvent;
import com.p532.brickout.util.GameParameter;
import com.p532.brickout.util.Mode;

public class BrickCollisionChecker implements EventChecker {

	private int x;
	private int y;
	private Board board;
	
	
	public BrickCollisionChecker(Board board) {
		super();
		this.board = board;
	}

	@Override
	public void check() {
		
		GameParameter gameParameter = board.getGameParameter();
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 5; j++) {
				if (board.getBricks()[i][j].isDestroyed()) {
					continue;
				}
				if (board.getBricks()[i][j].hitBottom(x, y)
						|| board.getBricks()[i][j].hitTop(x, y)) {
					board.getBall().setYDir(-board.getBall().getYDir());
				} else if (board.getBricks()[i][j].hitLeft(x, y)
						|| board.getBricks()[i][j].hitRight(x, y)) {
					board.getBall().reverseXDir();
				} else {
					continue;
				}

				if (!gameParameter.getMode().equals(Mode.UNDO)) 
				{
					board.getBricks()[i][j].setDestroyed(true); //set the boolean as false for the brick that is hit
					
					gameParameter.decrementBricks(1); //destroy the brick(do not draw)
					
					gameParameter.incrementScore(50);
					if (gameParameter.getMode().equals(Mode.PLAY)) {
						board.getEvents().add(new GameEvent(board.getClock().getTime(),
								Constants.BRICK_COLLISION));
						board.getEvents().peekLast().setEventObject(board.getBricks()[i][j]);
					}
				} else {
					
					gameParameter.incrementBricks(1);
					
					gameParameter.decrementScore(50);
				}
			}
		}


	}

	@Override
	public void setX(int x) {
		
		this.x = x;

	}

	@Override
	public void setY(int y) {
		this.y = y;

	}


}
