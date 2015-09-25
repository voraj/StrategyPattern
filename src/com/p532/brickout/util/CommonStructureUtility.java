package com.p532.brickout.util;

import java.awt.Color;

import com.p532.brickout.shape.Brick;

public class CommonStructureUtility {

	private CommonStructureUtility() {
		// TODO Auto-generated constructor stub
	}
	
	public static void makeBricks(Brick bricks[][])	{
		
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 5; j++) {
				bricks[i][j] = new Brick((i * Constants.BRICK_WIDTH) + 5,
						(j * Constants.BRICK_HEIGHT) + (Constants.BRICK_HEIGHT / 2),
						Constants.BRICK_WIDTH - 5, Constants.BRICK_HEIGHT - 5, Color.blue);
			}
		}
	}
	
	public static Brick[][] copyBricks(Brick[][] bricks) {
		Brick[][] copy = new Brick[bricks.length][bricks[0].length];
		for (int i = 0; i < bricks.length; i++) {
			for (int j = 0; j < bricks[0].length; j++) {
				copy[i][j] = new Brick(bricks[i][j]);
			}
		}
		return copy;
	}

}
