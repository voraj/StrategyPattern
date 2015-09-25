package com.p532.brickout.impl;

import com.p532.brickout.bean.GameState;
import com.p532.brickout.gameui.Board;
import com.p532.brickout.intf.FileIOhandler;
import com.p532.brickout.intf.GameCommand;

public class GameSave implements GameCommand {

	private Board board;
	private String filePath;

	public GameSave(Board board, String filePath) {
		super();
		this.board = board;
		this.filePath = filePath;
	}


	public void executeCommand() {
		/*
		 * Save all Game parameters
		 * as a state in GameState
		 */
		GameState gameState = new GameState();
		
		gameState.setGameParameter(board.getGameParameter());
		gameState.setBall(board.getBall());
		gameState.setPaddle(board.getPaddle());
		gameState.setBricks(board.getBricks());
		gameState.setClock(board.getClock());
		
		FileIOhandler jsonFileIOhandler = new JSONFileHandler(gameState);
		
		
		jsonFileIOhandler.write(filePath);
	}

	@Override
	public void undo() {


	}

}
