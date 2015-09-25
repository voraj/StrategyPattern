package com.p532.brickout.impl;

import com.p532.brickout.gameui.Board;
import com.p532.brickout.intf.GameCommand;

public class GameResume implements GameCommand {

	private Board board;
	
	
	public GameResume(Board board) {
		super();
		this.board = board;
	}


	@Override
	public void executeCommand() {
		
		board.getGameParameter().setPaused(false);
		synchronized (board) {

			board.notifyAll();
		}

	}


	@Override
	public void undo() {
		// TODO Auto-generated method stub
		
	}

}
