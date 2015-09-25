package com.p532.brickout.impl;

import com.p532.brickout.gameui.Board;
import com.p532.brickout.intf.EventChecker;

public class WallCollisionChecker implements EventChecker {

	private int x;
	private int y;
	private Board board;

	public WallCollisionChecker() {
		// TODO Auto-generated constructor stub
	}

	public WallCollisionChecker(Board board) {
		super();
		
		this.board = board;
	}

	@Override
	public void check() {

		if (x <= 0 || x >= board.getWidth() - board.getBall().getWidth()) {
            board.getBall().reverseXDir();
        }

        if (y <= 0) {
            board.getBall().reverseYDir();
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
