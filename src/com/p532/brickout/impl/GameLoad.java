package com.p532.brickout.impl;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;

import com.p532.brickout.bean.GameState;
import com.p532.brickout.gameui.Board;
import com.p532.brickout.intf.FileIOhandler;
import com.p532.brickout.intf.GameCommand;
import com.p532.brickout.intf.Observer;
import com.p532.brickout.util.CommandBroker;
import com.p532.brickout.util.Constants;
import com.p532.brickout.util.GameEvent;
import com.p532.brickout.util.Mode;

public class GameLoad implements GameCommand {

	private Board board;
	private String filePath;

	public GameLoad(Board board, String filePath) {
		super();
		this.board = board;
		this.filePath = filePath;
	}

	@Override
	public void executeCommand() {

		FileIOhandler jsonFileIOhandler = new JSONFileHandler();

		GameState gameState = (GameState) jsonFileIOhandler.read(filePath);

		/*
		 * Resetting the game With Saved game parameters
		 */

		board.setBall(gameState.getBall());
		board.setPaddle(gameState.getPaddle());
		board.setGameParameter(gameState.getGameParameter());
		board.setClock(gameState.getClock());
		board.setBricks(gameState.getBricks());

		/*
		 * Regestering Ball & Clock move commands
		 * to Command broker
		 */
		board.setBallMoveCommand(new BallMove(board.getBall()));
		board.setClockTickCommand(new ClockTick(board.getClock()));
		board.setCommandBroker(
				new CommandBroker(Arrays.asList(board.getBallMoveCommand(), board.getClockTickCommand())));
		
		/*
		 * Resetting Game Events data structure
		 */
		board.setEvents(new ArrayDeque<GameEvent>());
		board.getEvents().add(new GameEvent(board.getClock().getTime(), Constants.GAME_BEGIN));

		board.getGameParameter().setPaused(true);
		board.getGameParameter().setMode(Mode.PLAY);

		board.setObservers(new ArrayList<Observer>());
		board.register(board.getBall());
		board.register(board.getClock());

		board.getLayoutPanel().getStartBtn().setText("START");

		board.getLayoutPanel().getUndoBtn().setEnabled(false);
		board.getLayoutPanel().getReplayBtn().setEnabled(false);
		board.getLayoutPanel().getStopBtn().setEnabled(false);

	}

	@Override
	public void undo() {
		// TODO Auto-generated method stub

	}

}
