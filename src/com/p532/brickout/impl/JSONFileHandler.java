package com.p532.brickout.impl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.p532.brickout.bean.GameState;
import com.p532.brickout.intf.FileIOhandler;

public class JSONFileHandler implements FileIOhandler {

	final static Logger LOGGER = Logger.getLogger(JSONFileHandler.class);
	private GameState gameState;

	public JSONFileHandler(GameState gameState) {
		super();
		this.gameState = gameState;
	}

	public JSONFileHandler() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void write(String path) {

		Gson gson = new Gson();
		String jsonString = gson.toJson(gameState);
		FileWriter writer = null;

		try {
			writer = new FileWriter(path);
			writer.write(jsonString);
		} catch (IOException e) {
			
			LOGGER.error("Error Message : ", e);
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					LOGGER.error("Error Message : ", e);
				}
			}
		}

	}

	@Override
	public Object read(String path) {
		// TODO Auto-generated method stub

		Gson gson = new Gson();
		GameState savedGameState = null;

		try {

			BufferedReader br = new BufferedReader(new FileReader(path));

			savedGameState = gson.fromJson(br, GameState.class);

		} catch (IOException e) {
			LOGGER.error("Error Message : ", e);
		}

		return savedGameState;
	}

}
