package com.p532.brickout.shape;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

import com.p532.brickout.intf.Observer;

public class Ball extends Structure implements Observer {
    // Variables
    private final int INITIAL_X_DIR = 1, INITIAL_Y_DIR = -1;
    private boolean onScreen;
    private int xDir = INITIAL_X_DIR, yDir = INITIAL_Y_DIR;

    
    // Constructor
    public Ball(int x, int y, int width, int height, Color color) {
        super(x, y, width, height, color);
        setOnScreen(true);
    }

    public Ball(Ball ball) {
        super(ball);
        xDir = ball.xDir;
        yDir = ball.yDir;
    }

    
    public void set(Ball ball) {
        super.set(ball);
        xDir = ball.xDir;
        yDir = ball.yDir;
    }

     /*Draw the ball*/
    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval(x, y, width, height);
    }

    /*Resets the ball to original position at center of screen*/
    public void reset() {
        x = BALL_X_START;
        y = BALL_Y_START;
        xDir = INITIAL_X_DIR;
        yDir = INITIAL_Y_DIR;
    }

    // Mutator methods
    public void setXDir(int xDir) {
        this.xDir = xDir;
    }

    public void setYDir(int yDir) {
        this.yDir = yDir;
    }

    public void setOnScreen(boolean onScreen) {
        this.onScreen = onScreen;
    }

    // Accessor methods
    public int getXDir() {
        return xDir;
    }

    public int getYDir() {
        return yDir;
    }

    public boolean isOnScreen() {
        return onScreen;
    }

    @Override
    public void update(int timeStep) {
        if (timeStep > 0) {
            x += xDir;
            y += yDir;
        } else if (timeStep < 0) {
            x -= xDir;
            y -= yDir;
        }

    }

    public void reverseMove() {
        reverseXDir();
        reverseYDir();
    }

    public void reverseXDir() {
        setXDir(-getXDir());
    }

    public void reverseYDir() {
        setYDir(-getYDir());
    }

	public int getINITIAL_X_DIR() {
		return INITIAL_X_DIR;
	}

	public int getINITIAL_Y_DIR() {
		return INITIAL_Y_DIR;
	}
    
    
}