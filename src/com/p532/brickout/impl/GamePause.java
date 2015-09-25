package com.p532.brickout.impl;

import com.p532.brickout.gameui.Board;
import com.p532.brickout.intf.GameCommand;

public class GamePause implements GameCommand {

	private Board board;
	
	
	public GamePause(Board board) {
		super();
		this.board = board;
	}


	@Override
	public void executeCommand() {
		
		board.getGameParameter().setPaused(true);

	}


	@Override
	public void undo() {
		// TODO Auto-generated method stub
		
	}

}
