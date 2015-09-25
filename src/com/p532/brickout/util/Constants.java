package com.p532.brickout.util;

public interface Constants {
    // Window Size
    public static final int WINDOW_WIDTH = 505;
    // Increased window height to make room for buttons
    public static final int WINDOW_HEIGHT = 650;	
    
    //Border Layout Location and Dimensions
    public static final int BORDER_X = 158;
    public static final int BORDER_Y = 495;
    public static final int BORDER_WIDTH = WINDOW_WIDTH - BORDER_X;
    public static final int BORDER_HEIGHT = 120;
    
    //Flow Layout Location and Dimensions
    public static final int FLOW_X = 156;
    public static final int FLOW_Y = 515;
    public static final int FLOW_WIDTH = 329;
    public static final int FLOW_HEIGHT = 200;

    // Lives
    public static final int MAX_LIVES = 5;
    public static final int MIN_LIVES = 0;

    // Ball
    public static final int BALL_WIDTH = 10;
    public static final int BALL_HEIGHT = 10;
    public static final int BALL_RIGHT_BOUND = 490;
    public static final int BALL_X_START = 245;
    public static final int BALL_Y_START = 245;

    // Paddle
    public static final int PADDLE_WIDTH = 70;
    public static final int PADDLE_HEIGHT = 10;
    public static final int PADDLE_RIGHT_BOUND = 430;
    public static final int PADDLE_X_START = 225;
    public static final int PADDLE_Y_START = 450;
    public static final int PADDLE_MIN = 35;
    public static final int PADDLE_MAX = 140;

    // Bricks
    public static final int BRICK_WIDTH = 50;
    public static final int BRICK_HEIGHT = 25;
    public static final int MAX_BRICKS = 50;
    public static final int NO_BRICKS = 0;
    public static final int BRICK_ROWS = 5;
    public static final int BRICK_COLUMNS = 10;
    
    //Directions
    String X_DIRECTION ="X";
    String Y_DIRECTION ="Y";

    int CLOCK_LOCATION_X = 10, TIME_STEP = 5;
	// code of action
	int GAME_END = -1, BRICK_COLLISION = -2, GAME_BEGIN = -3,
			GAME_LOST = -4, GAME_WIN = -5;
	
	
	int CLOCK_LOCATION_Y = WINDOW_HEIGHT / 2;
	
	String NO_SELECTION_MSG = "none";
	String FILE_CHOOSER_ROOT_PATH = "C:/";
	String SAVE_DIALOGUE_TITLE = "Select path & Enter File name";
	String LOAD_DIALOGUE_TITLE = "Select path & File name";
	
	String SUCCESSFUL_GAME_SAVE_MSG = "Game Saved Successfully! :)";
	String FAILURE_GAME_SAVE_MSG = "Oops! Could not save the game! :(\nPlease check given file path.";
	
	String SUCCESSFUL_GAME_LOAD_MSG = "Game Loaded Successfully!";
	String FAILURE_GAME_LOAD_MSG = "Oops! Could not load the game! \nPlease check given file path.";
	
	String GAME_OVER_MSG = "No Lives left! Game Over!!";
	
}