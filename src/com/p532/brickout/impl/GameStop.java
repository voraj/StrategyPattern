package com.p532.brickout.impl;

import com.p532.brickout.gameui.Board;
import com.p532.brickout.intf.GameCommand;

public class GameStop implements GameCommand {

	private Board board;
	
	
	public GameStop(Board board) {
		super();
		this.board = board;
	}


	@Override
	public void executeCommand() {
		
		board.getResetCommand().executeCommand();
		board.repaint();

	}


	@Override
	public void undo() {
		// TODO Auto-generated method stub
		
	}

}
