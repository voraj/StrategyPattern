package com.p532.brickout.shape;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

import com.p532.brickout.util.Constants;

//Class definition
public abstract class Structure implements Constants, Serializable {
    // Variables
    protected int x, y, width, height;
    protected Color color;

    public Structure() {
		// TODO Auto-generated constructor stub
	}
    // Constructor
    public Structure(int x, int y, int width, int height, Color color) {
        setX(x);
        setY(y);
        setWidth(width);
        setHeight(height);
        setColor(color);
    }
    public Structure(Structure structure) {
        this(structure.x, structure.y, structure.width, structure.height, structure.color);
    }
    public void set(Structure struc) {
        setX(struc.x);
        setY(struc.y);
        setWidth(struc.width);
        setHeight(struc.height);
        setColor(struc.color);
    }

    // Draw a structure
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, width, height);
    }

    // Mutator methods
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    // Accessor methods
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Color getColor() {
        return color;
    }
}