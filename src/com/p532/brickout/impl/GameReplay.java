package com.p532.brickout.impl;

import java.util.ArrayDeque;

import com.p532.brickout.gameui.Board;
import com.p532.brickout.intf.GameCommand;
import com.p532.brickout.util.GameEvent;
import com.p532.brickout.util.Mode;

public class GameReplay implements GameCommand {

	private Board board;
	
	
	public GameReplay(Board board) {
		super();
		this.board = board;
	}


	@Override
	public void executeCommand() {
		
		ArrayDeque<GameEvent> tmp = board.getEvents();
		board.getResetCommand().executeCommand();
		board.setEvents(tmp);
		board.getGameParameter().setMode(Mode.REPLAY);

		board.getResumeCommand().executeCommand();

	}


	@Override
	public void undo() {
		// TODO Auto-generated method stub
		
	}

}
