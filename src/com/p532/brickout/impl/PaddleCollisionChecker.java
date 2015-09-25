package com.p532.brickout.impl;

import com.p532.brickout.gameui.Board;
import com.p532.brickout.intf.EventChecker;
import com.p532.brickout.util.Mode;

public class PaddleCollisionChecker implements EventChecker {

	private int x;
	private int y;
	private Board board;

	
	public PaddleCollisionChecker(Board board) {
		super();
		this.board = board;
	}
	


	@Override
	public void check() {
		
		if (board.getPaddle().hitPaddle(x, y)) {
            if (board.getGameParameter().getMode().equals(Mode.UNDO)) {
                board.getBall().setYDir(-board.getBall().getINITIAL_Y_DIR());
            } else {
                board.getBall().setYDir(board.getBall().getINITIAL_Y_DIR());
            }
        }
	}


	@Override
	public void setX(int x) {
		
		this.x = x;
		
	}

	@Override
	public void setY(int y) {
		// TODO Auto-generated method stub
		this.y = y;
	}

}
