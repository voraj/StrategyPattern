package com.p532.brickout.impl;

import java.awt.Color;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;

import com.p532.brickout.gameui.Board;
import com.p532.brickout.intf.GameCommand;
import com.p532.brickout.intf.Observer;
import com.p532.brickout.shape.Ball;
import com.p532.brickout.shape.Brick;
import com.p532.brickout.shape.Clock;
import com.p532.brickout.shape.Paddle;
import com.p532.brickout.util.CommandBroker;
import com.p532.brickout.util.CommonStructureUtility;
import com.p532.brickout.util.Constants;
import com.p532.brickout.util.GameEvent;
import com.p532.brickout.util.Mode;

public class GameReset implements GameCommand {

	private Board board;

	public GameReset(Board board) {
		super();
		this.board = board;
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	@Override
	public void executeCommand() {

		board.getGameParameter().resetScore();
		board.getGameParameter().resetBricksLeft();
		board.getGameParameter().resetLevel();
		board.getGameParameter().resetLives();

		board.setBricks(new Brick[Constants.BRICK_COLUMNS][Constants.BRICK_ROWS]);

		CommonStructureUtility.makeBricks(board.getBricks());
		board.setPaddle(new Paddle(Constants.PADDLE_X_START,
				Constants.PADDLE_Y_START, Constants.PADDLE_WIDTH,
				Constants.PADDLE_HEIGHT, Color.BLACK));
		board.setBall(new Ball(Constants.BALL_X_START, Constants.BALL_Y_START,
				Constants.BALL_WIDTH, Constants.BALL_HEIGHT, Color.BLACK));
		
		
		board.setClock(new Clock(Constants.CLOCK_LOCATION_X,
				board.getHeight() / 2, Constants.BALL_WIDTH,
				Constants.BALL_HEIGHT, Color.red));
		
		board.setBallMoveCommand(new BallMove(board.getBall()));
		board.setClockTickCommand(new ClockTick(board.getClock()));		
		board.setCommandBroker(new CommandBroker(Arrays.asList(board.getBallMoveCommand(),
                board.getClockTickCommand())));
		
		board.setEvents(new ArrayDeque<GameEvent>());

		board.getEvents()
				.add(new GameEvent(board.getClock().getTime(),
						Constants.GAME_BEGIN));

		board.getBall().reset();

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
