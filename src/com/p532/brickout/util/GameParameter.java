package com.p532.brickout.util;

import java.io.Serializable;

public class GameParameter implements Serializable {

	// Initial Values for some important variables
	private int score, lives, bricksLeft, level;

	// Player's name
	private String playerName;

	// The game
	private transient Thread game;

	// Paused state
	private volatile boolean isPaused;

	private Mode mode;

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getLives() {
		return lives;
	}

	public void setLives(int lives) {
		this.lives = lives;
	}

	public int getBricksLeft() {
		return bricksLeft;
	}

	public void setBricksLeft(int bricksLeft) {
		this.bricksLeft = bricksLeft;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public Thread getGame() {
		return game;
	}

	public void setGame(Thread game) {
		this.game = game;
	}

	public boolean isPaused() {
		return isPaused;
	}

	public void setPaused(boolean isPaused) {
		this.isPaused = isPaused;
	}

	public Mode getMode() {
		return mode;
	}

	public void setMode(Mode mode) {
		this.mode = mode;
	}
	
	public void resetScore(){
		
		score = 0;
	}
	
	public void resetLives(){
		
		lives = Constants.MAX_LIVES;
	}
	
	public void resetBricksLeft(){
		
		bricksLeft = Constants.MAX_BRICKS;
	}
	
	public void resetLevel()	{
		
		level = 1;
	}
	
	public void incrementScore(int amt)	{
		
		score += amt;
	}
	
	public void decrementScore(int amt)	{
		
		score -= amt;
	}
	
	public void decrementBricks(int amt)	{
		
		bricksLeft -= amt;
	}
	
	public void incrementBricks(int amt){
		
		bricksLeft += amt;
	}
	
	public void incrementLives(int amt)	{
		lives += amt;
	}
	
	public void decrementLives(int amt)	{
		
		lives -= amt;
	}
	
	public void incrementLevel(int amt)	{
		level += amt;
	}
	
	public void decrementLevel(int amt)	{
		level -= amt;
	}
}
