package com.p532.brickout.impl;

import com.p532.brickout.gameui.Board;
import com.p532.brickout.intf.GameCommand;
import com.p532.brickout.shape.Ball;
import com.p532.brickout.util.Constants;
import com.p532.brickout.util.GameEvent;
import com.p532.brickout.util.GameParameter;
import com.p532.brickout.util.Mode;

public class GameUndo implements GameCommand {

	private Board board;
	
	
	public GameUndo(Board board) {
		super();
		this.board = board;
	}


	@Override
	public void executeCommand() {
		

		GameParameter gameParameter = board.getGameParameter();
		
		gameParameter.setMode(Mode.UNDO);
		board.getPauseCommand().executeCommand();
		
		GameEvent event = board.getEvents().peekLast();
		if (event.getAction() == Constants.GAME_WIN || event.getAction() == Constants.GAME_LOST) {
			if (event.getAction() == Constants.GAME_WIN) {
				gameParameter.setScore(gameParameter.getScore() - 100);
				gameParameter.setLives(gameParameter.getLives() - 1);
				
			} else {
				
				gameParameter.setScore(gameParameter.getScore() + 50);
				gameParameter.setLives(gameParameter.getLives() + 1);
				
			}

			board.getBall().set((Ball) event.getEventObject());

			board.getEvents().pollLast();
			event = board.getEvents().peekLast();
		}

				board.getResumeCommand().executeCommand();

		
	}


	@Override
	public void undo() {
		// TODO Auto-generated method stub
		
	}

}
