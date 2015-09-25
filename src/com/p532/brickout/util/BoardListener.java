package com.p532.brickout.util;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import com.p532.brickout.gameui.Board;
import com.p532.brickout.gameui.GameFileChooser;
import com.p532.brickout.impl.GameLoad;
import com.p532.brickout.impl.GameSave;
import com.p532.brickout.intf.GameCommand;

public class BoardListener extends KeyAdapter implements ActionListener {

	private Board board;
	private GameCommand saveCommand;
	private GameCommand loadCommand;
	private GameFileChooser gameFileChooser;
	private String filePath;
	/*private Sound backgroundMusic = new Sound();*/

	public BoardListener(Board board) {
		super();
		this.board = board;
	}

	@Override
	public void keyPressed(KeyEvent ke) {
		int key = ke.getKeyCode();

		if (board.getGameParameter().getMode().equals(Mode.PLAY) && !board.getGameParameter().isPaused()
				&& key == KeyEvent.VK_LEFT || key == KeyEvent.VK_RIGHT) {
			board.getEvents().add(new GameEvent(board.getClock().getTime(), key));

			board.getEvents().peekLast().setEventObject(board.getPaddle().move(key, board.getWidth()));

		}
	}

	@Override
	public void actionPerformed(ActionEvent ae) {

		GameParameter gameParameter = board.getGameParameter();
		if (ae.getSource() == board.getLayoutPanel().getStartBtn()) {
			/*backgroundMusic.playMusic();*/
			if (gameParameter.getLives() > Constants.MIN_LIVES) {
				if (!gameParameter.isPaused()) {

					board.getPauseCommand().executeCommand();
					// To bring the focus back to the JPanel default
					board.requestFocus();
					board.getLayoutPanel().getStartBtn().setText("RESUME");
					/*backgroundMusic.stopMusic();*/
				} else {
					
					board.getResumeCommand().executeCommand();
					board.getLayoutPanel().getStartBtn().setText("PAUSE");
					// To bring the focus back to the JPanel default
					board.requestFocus();
					

					// If statement removed, PFTAE-5 fixed

					board.getLayoutPanel().getUndoBtn().setEnabled(true);
					board.getLayoutPanel().getReplayBtn().setEnabled(true);
					board.getLayoutPanel().getStopBtn().setEnabled(true);
				}
			} else {
				board.getPaddle().setWidth(board.getWidth() / 7);
				
				gameParameter.resetLives();
				
				gameParameter.resetScore();

				gameParameter.resetBricksLeft();
				
				gameParameter.resetLevel();
				
				CommonStructureUtility.makeBricks(board.getBricks());

				gameParameter.setPaused(true);
				for (int i = 0; i < 10; i++) {
					for (int j = 0; j < 5; j++) {
						board.getBricks()[i][j].setDestroyed(false);
					}
				}
			}
		} else if (ae.getSource() == board.getLayoutPanel().getStopBtn()) {
			// To bring the focus back to the JPanel default
			board.requestFocus();
			/*backgroundMusic.stopMusic();*/
			board.getStopCommand().executeCommand();
		}

		else if (ae.getSource() == board.getLayoutPanel().getUndoBtn() && gameParameter.getMode().equals(Mode.PLAY)) {
			// command
			if (!board.getEvents().isEmpty()) {
				// To bring the focus back to the JPanel default
				board.requestFocus();
				board.getLayoutPanel().getStartBtn().setText("RESUME");
				
				board.getUndoCommand().executeCommand();
			} else {
				board.getLayoutPanel().getUndoBtn().setEnabled(false);
				board.getLayoutPanel().getReplayBtn().setEnabled(false);
			}
		} else
			if (ae.getSource() == board.getLayoutPanel().getReplayBtn() && gameParameter.getMode().equals(Mode.PLAY)) {
			// command
			board.getEvents().add(new GameEvent(board.getClock().getTime(), Constants.GAME_END));
			// To bring the focus back to the JPanel default
			board.requestFocus();

			board.getReplayCommand().executeCommand();
		} else if (ae.getSource() == board.getLayerBtn()) {
			if (board.getLayoutPanel().getLayout() == board.getLayoutPanel().getFlowLayout()) {
				board.getLayoutPanel().setLayout(board.getLayoutPanel().getBorderLayout());
				board.getLayoutPanel().setBounds(Constants.BORDER_X, Constants.BORDER_Y,
						Constants.BORDER_WIDTH, Constants.BORDER_HEIGHT);
				board.getLayoutPanel().validate();

			} else {
				board.getLayoutPanel().setLayout(board.getLayoutPanel().getFlowLayout());
				board.getLayoutPanel().setBounds(Constants.FLOW_X, Constants.FLOW_Y,
						Constants.FLOW_WIDTH, Constants.FLOW_HEIGHT);
				board.getLayoutPanel().validate();

			}
			/*
			 * Game Save Command
			 * Called by User
			 * @Author: Abhijit Karanjkar
			 * Edited:  Andres Rivero
			 */
		} else if (ae.getSource() == board.getLayoutPanel().getSaveBtn()) {

			/*
			 * 
			 */
			board.getPauseCommand().executeCommand();
			
			filePath = null;
			this.gameFileChooser = new GameFileChooser(board, System.getProperty("user.home"),
					Constants.SAVE_DIALOGUE_TITLE, JFileChooser.DIRECTORIES_ONLY, "Save");

			filePath = this.gameFileChooser.getPath();

			if (null != filePath && !filePath.equalsIgnoreCase(Constants.NO_SELECTION_MSG)) {
				saveCommand = new GameSave(board, filePath);
				saveCommand.executeCommand();
				
				
				ImageIcon saveimg = new ImageIcon(this.getClass().getResource("/happy-icon.png"));
				JOptionPane.showMessageDialog(null, Constants.SUCCESSFUL_GAME_SAVE_MSG,"Save", JOptionPane.INFORMATION_MESSAGE, saveimg);
				board.getLayoutPanel().getStartBtn().setText("RESUME/START");
			} else {
				/*
				 * File is not selected properly
				 */
				ImageIcon failimg = new ImageIcon(this.getClass().getResource("/hmm-icon.png"));
				JOptionPane.showMessageDialog(null, Constants.FAILURE_GAME_SAVE_MSG,"Failure", JOptionPane.INFORMATION_MESSAGE, failimg);
				board.getLayoutPanel().getStartBtn().setText("RESUME/START");
				
			}

			/*
			 * Game Load Command
			 * Called by User
			 * @Author: Abhijit Karanjkar
			 * Edited:  Andres Rivero
			 */
		} else if (ae.getSource() == board.getLayoutPanel().getLoadBtn()) {

			board.getPauseCommand().executeCommand();
			filePath = null;
			this.gameFileChooser = new GameFileChooser(board, System.getProperty("user.home"),
					Constants.LOAD_DIALOGUE_TITLE, JFileChooser.FILES_AND_DIRECTORIES, "Load");

			filePath = this.gameFileChooser.getPath();

			if (null != filePath && !filePath.equalsIgnoreCase(Constants.NO_SELECTION_MSG)) {
				loadCommand = new GameLoad(board, filePath);
				loadCommand.executeCommand();
				board.repaint();
				ImageIcon saveimg = new ImageIcon(this.getClass().getResource("/happy-icon.png"));
				JOptionPane.showMessageDialog(null, Constants.SUCCESSFUL_GAME_LOAD_MSG,"Load", JOptionPane.INFORMATION_MESSAGE, saveimg);
				board.getLayoutPanel().getStartBtn().setText("RESUME/START");
				

			} else {
				/*
				 * File is not selected properly
				 */
				ImageIcon failimg = new ImageIcon(this.getClass().getResource("/hmm-icon.png"));
				JOptionPane.showMessageDialog(null, Constants.FAILURE_GAME_LOAD_MSG,"Failure", JOptionPane.INFORMATION_MESSAGE, failimg);
				board.getLayoutPanel().getStartBtn().setText("RESUME/START");
				
			}
		}

		board.requestFocus();
	}
}
