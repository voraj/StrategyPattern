package com.p532.brickout.shape;

import java.awt.*;
import java.awt.event.*;

public class Paddle extends Structure {

    // Constructor
    public Paddle(int x, int y, int width, int height, Color color) {
        super(x, y, width, height, color);
    }
    
    public Paddle(Paddle paddle) {
        super(paddle);
    }

    // Draws the paddle
    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, width, height);
    }

    // Places the paddle back in starting position at center of screen
    public void reset() {
        x = PADDLE_X_START;
        y = PADDLE_Y_START;
    }

    // Checks if the ball hit the paddle
    public boolean hitPaddle(int ballX, int ballY) {
        if ((ballX >= x) && (ballX <= x + width)
                && ((ballY >= y) && (ballY <= y + height))) {
            return true;
        }
        return false;
    }
    
    public Integer move(int key, int windowWidth) {
        Integer distance = 0;
        if (key == KeyEvent.VK_LEFT) {
            if (getX() - 50 >= 0) {
                setX(getX() - 50);
                distance =  -50;
            } else {
                distance = -getX();
                setX(0);
            }
        } else if (key == KeyEvent.VK_RIGHT) {
            if (getX() + width + 50 <= windowWidth) {
            setX(getX() + 50);
            distance = 50;
            } else {
                distance = windowWidth - width - getX();
                setX(windowWidth - width);
            }
        }
        return distance;
    }
    
    public void moveBack(int key) {
        if (key == KeyEvent.VK_LEFT) {
            setX(getX() + 50);
        } else if (key == KeyEvent.VK_RIGHT) {

            setX(getX() - 50);
        }
    }

}