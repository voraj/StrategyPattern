package com.p532.brickout.impl;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import com.p532.brickout.gameui.Board;
import com.p532.brickout.intf.EventChecker;
import com.p532.brickout.shape.Ball;
import com.p532.brickout.util.CommonStructureUtility;
import com.p532.brickout.util.Constants;
import com.p532.brickout.util.GameEvent;
import com.p532.brickout.util.GameParameter;
import com.p532.brickout.util.Mode;

public class LivesChecker implements EventChecker {

	private Board board;
	
	
	public LivesChecker(Board board) {
		super();
		this.board = board;
	}

	@Override
	public void check() {
		
		GameParameter gameParameter = board.getGameParameter();
		
		if (gameParameter.getBricksLeft() == Constants.NO_BRICKS) {
			if (gameParameter.getMode().equals(Mode.PLAY)) {
				board.getEvents().add(new GameEvent(board.getClock().getTime(), Constants.GAME_WIN));
			}
			board.getEvents().peekLast().setEventObject(new Ball(board.getBall()));
			board.getBall().reset();
			
			if (gameParameter.getMode().equals(Mode.PLAY)) {
				board.getEvents().add(new GameEvent(board.getClock().getTime(), Constants.GAME_BEGIN));
			}
			gameParameter.setBricksLeft(Constants.MAX_BRICKS);
			
			CommonStructureUtility.makeBricks(board.getBricks());
			
			gameParameter.incrementLives(1);
			
			gameParameter.incrementLevel(1);
			
			gameParameter.incrementScore(100);
			board.repaint();
			board.getPauseCommand().executeCommand();
		}
		if (gameParameter.getLives() == Constants.MIN_LIVES) {
			board.repaint();
			board.getPauseCommand().executeCommand();
			ImageIcon overimg = new ImageIcon(this.getClass().getResource("/Smiley-sad-icon.png"));
			JOptionPane.showMessageDialog(null, Constants.GAME_OVER_MSG,"Game Over!", JOptionPane.INFORMATION_MESSAGE, overimg);
		
			
		}


	}

	@Override
	public void setX(int x) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setY(int y) {
		// TODO Auto-generated method stub

	}

}
