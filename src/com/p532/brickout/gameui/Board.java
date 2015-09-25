package com.p532.brickout.gameui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

import org.apache.log4j.Logger;

import com.p532.brickout.impl.BrickCollisionChecker;
import com.p532.brickout.impl.GamePause;
import com.p532.brickout.impl.GameReplay;
import com.p532.brickout.impl.GameReset;
import com.p532.brickout.impl.GameResume;
import com.p532.brickout.impl.GameStop;
import com.p532.brickout.impl.GameUndo;
import com.p532.brickout.impl.LivesChecker;
import com.p532.brickout.impl.PaddleCollisionChecker;
import com.p532.brickout.impl.PlayerOutChecker;
import com.p532.brickout.impl.WallCollisionChecker;
import com.p532.brickout.intf.EventChecker;
import com.p532.brickout.intf.GameCommand;
import com.p532.brickout.intf.Observer;
import com.p532.brickout.intf.Subject;
import com.p532.brickout.shape.Ball;
import com.p532.brickout.shape.Brick;
import com.p532.brickout.shape.Clock;
import com.p532.brickout.shape.Paddle;
import com.p532.brickout.util.BoardListener;
import com.p532.brickout.util.Constants;
import com.p532.brickout.util.GameEvent;
import com.p532.brickout.util.GameParameter;
import com.p532.brickout.util.Mode;

public class Board extends JPanel implements Runnable, Constants, Subject {

	private static final long serialVersionUID = 1L;
	final static Logger LOGGER = Logger.getLogger(Board.class);

	private Paddle paddle;
	private Ball ball;
	private Clock clock;
	private Brick[][] bricks;
	private transient List<Observer> observers;
	private ArrayDeque<GameEvent> events;
	private transient Thread game;

	HashMap<Integer, Integer> records = new HashMap<Integer, Integer>();

	private GameParameter gameParameter;

	private GameCommand resetCommand;
	private GameCommand pauseCommand;
	private GameCommand resumeCommand;
	private GameCommand undoCommand;
	private GameCommand replayCommand;
	private GameCommand stopCommand;
	private GameCommand ballMoveCommand;
	private GameCommand clockTickCommand;
	private GameCommand commandBroker;

	private EventChecker brickCollisionChecker;
	private EventChecker wallCollisionChecker;
	private EventChecker paddleCollisionChecker;
	private EventChecker livesChecker;
	private EventChecker playerOutChecker;

	private LayoutPanel panelLayout;

	private JButton layerBtn;

	/* Constructor for initializing the Layout */
	public Board(int width, int height) {
		super.setSize(width, height);
		addKeyListener(new BoardListener(this));
		super.setLayout(null);
		this.panelLayout = new LayoutPanel(this);
		layerBtn = new JButton("CHANGE LAYOUT");

		layerBtn.setBounds(CLOCK_LOCATION_X - 5, CLOCK_LOCATION_Y + 170, 150, 120);
		this.add(layerBtn);
		this.add(panelLayout);

		layerBtn.addActionListener(new BoardListener(this));

		gameParameter = new GameParameter();
		pauseCommand = new GamePause(this);
		resetCommand = new GameReset(this);
		resumeCommand = new GameResume(this);
		undoCommand = new GameUndo(this);
		replayCommand = new GameReplay(this);
		stopCommand = new GameStop(this);

		wallCollisionChecker = new WallCollisionChecker(this);
		paddleCollisionChecker = new PaddleCollisionChecker(this);
		brickCollisionChecker = new BrickCollisionChecker(this);
		livesChecker = new LivesChecker(this);
		playerOutChecker = new PlayerOutChecker(this);

		resetCommand.executeCommand();

		setFocusable(true);
		game = new Thread(this);
		game.start();

	}

	// runs the game
	@Override
	public void run() {
		while (true) {

			/* In replay mode, re-apply stored events */
			while (gameParameter.getMode().equals(Mode.REPLAY) && clock.getTime() == events.peekFirst().getTime()) {
				GameEvent event = events.pollFirst();
				if (event.getAction() == KeyEvent.VK_LEFT || event.getAction() == KeyEvent.VK_RIGHT) {
					paddle.move(event.getAction(), getWidth());
				} else if (event.getAction() == GAME_END) {

					stopCommand.executeCommand();
				}

			}

			if (gameParameter.isPaused()
					|| gameParameter.getMode().equals(Mode.UNDO) && clock.getTime() <= events.peekLast().getTime()) {
				if (gameParameter.getMode().equals(Mode.UNDO)) {
					GameEvent event = events.pollLast();
					if (event.getAction() == KeyEvent.VK_RIGHT || event.getAction() == KeyEvent.VK_LEFT) {
						paddle.setX(paddle.getX() - (int) event.getEventObject());
					} else if (event.getAction() == BRICK_COLLISION) {
						((Brick) event.getEventObject()).setDestroyed(false);

						brickCollisionChecker.setX(ball.getX());
						brickCollisionChecker.setY(ball.getY());
						brickCollisionChecker.check();

						/*
						 * Call command broker to undo actions
						 */
						commandBroker.undo();

					}
					repaint();

					gameParameter.setMode(Mode.PLAY);

					pauseCommand.executeCommand();
				}
				synchronized (this) {
					try {
						this.wait();
					} catch (InterruptedException e) {

						LOGGER.error("Error Message : ", e);
					}
				}
			} else {

				int x1 = ball.getX();
				int y1 = ball.getY();

				paddleCollisionChecker.setX(x1);
				paddleCollisionChecker.setY(y1);
				paddleCollisionChecker.check();

				wallCollisionChecker.setX(x1);
				wallCollisionChecker.setY(y1);
				wallCollisionChecker.check();

				brickCollisionChecker.setX(x1);
				brickCollisionChecker.setY(y1);
				brickCollisionChecker.check();

				livesChecker.check();

				playerOutChecker.setY(y1);
				playerOutChecker.check();
				if (!gameParameter.getMode().equals(Mode.UNDO)) {

					commandBroker.executeCommand();
				} else {

					commandBroker.undo();
				}
				repaint();

				try {
					Thread.sleep(TIME_STEP);
				} catch (InterruptedException ie) {
					LOGGER.error("Error Message : ", ie);
				}
			}

		}
	}

	// fills the board
	@Override
	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		paddle.draw(g);
		ball.draw(g);
		clock.draw(g);

		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 5; j++) {
				bricks[i][j].draw(g);
			}
		}
		g.setColor(Color.BLACK);
		g.setFont(new Font("TimesRoman",Font.BOLD, 20));
		g.drawString("Lives: " + gameParameter.getLives(), CLOCK_LOCATION_X, CLOCK_LOCATION_Y + 50);
		g.drawString("Score: " + gameParameter.getScore(), CLOCK_LOCATION_X, CLOCK_LOCATION_Y + 100);
		g.drawString("Level: " + gameParameter.getLevel(), CLOCK_LOCATION_X, CLOCK_LOCATION_Y + 150);

		if (gameParameter.getLives() == MIN_LIVES) {
			g.drawString("Nice try , wanna play again?", getWidth() / 3, getHeight() - 20);
		}
	}

	@Override
	public void register(Observer observer) {
		observers.add(observer);
	}

	@Override
	public void unregister(Observer observer) {
		observers.remove(observer);
	}

	@Override
	public void notifyObservers(int timeStep) {
		for (Observer observer : observers) {
			observer.update(timeStep);
		}
	}

	public Paddle getPaddle() {
		return paddle;
	}

	public void setPaddle(Paddle paddle) {
		this.paddle = paddle;
	}

	public Ball getBall() {
		return ball;
	}

	public void setBall(Ball ball) {
		this.ball = ball;
	}

	public Clock getClock() {
		return clock;
	}

	public void setClock(Clock clock) {
		this.clock = clock;
	}

	public Brick[][] getBricks() {
		return bricks;
	}

	public void setBricks(Brick[][] bricks) {
		this.bricks = bricks;
	}

	public List<Observer> getObservers() {
		return observers;
	}

	public void setObservers(List<Observer> observers) {
		this.observers = observers;
	}

	public ArrayDeque<GameEvent> getEvents() {
		return events;
	}

	public void setEvents(ArrayDeque<GameEvent> events) {
		this.events = events;
	}

	public Thread getGame() {
		return game;
	}

	public void setGame(Thread game) {
		this.game = game;
	}

	public int getTIME_STEP() {
		return TIME_STEP;
	}

	public GameParameter getGameParameter() {
		return gameParameter;
	}

	public void setGameParameter(GameParameter gameParameter) {
		this.gameParameter = gameParameter;
	}

	public GameCommand getResetCommand() {
		return resetCommand;
	}

	public void setResetCommand(GameCommand resetCommand) {
		this.resetCommand = resetCommand;
	}

	public GameCommand getPauseCommand() {
		return pauseCommand;
	}

	public void setPauseCommand(GameCommand pauseCommand) {
		this.pauseCommand = pauseCommand;
	}

	public GameCommand getResumeCommand() {
		return resumeCommand;
	}

	public void setResumeCommand(GameCommand resumeCommand) {
		this.resumeCommand = resumeCommand;
	}

	public GameCommand getUndoCommand() {
		return undoCommand;
	}

	public void setUndoCommand(GameCommand undoCommand) {
		this.undoCommand = undoCommand;
	}

	public GameCommand getReplayCommand() {
		return replayCommand;
	}

	public void setReplayCommand(GameCommand replayCommand) {
		this.replayCommand = replayCommand;
	}

	public GameCommand getStopCommand() {
		return stopCommand;
	}

	public void setStopCommand(GameCommand stopCommand) {
		this.stopCommand = stopCommand;
	}

	public JButton getLayerBtn() {
		return this.layerBtn;
	}

	public void setLayerBtn(JButton layerBtn) {
		this.layerBtn = layerBtn;
	}

	public GameCommand getBallMoveCommand() {
		return ballMoveCommand;
	}

	/*change board layout*/
	public LayoutPanel getLayoutPanel() {
		return this.panelLayout;
	}

	public void setBallMoveCommand(GameCommand ballMoveCommand) {
		this.ballMoveCommand = ballMoveCommand;
	}

	public GameCommand getClockTickCommand() {
		return clockTickCommand;
	}

	public void setClockTickCommand(GameCommand clockTickCommand) {
		this.clockTickCommand = clockTickCommand;
	}

	public GameCommand getCommandBroker() {
		return commandBroker;
	}

	public void setCommandBroker(GameCommand commandBroker) {
		this.commandBroker = commandBroker;
	}

}
