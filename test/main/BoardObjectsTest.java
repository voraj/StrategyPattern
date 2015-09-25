package main;

import java.awt.Color;
import javax.swing.JButton;
import junit.framework.TestCase;

import com.p532.brickout.gameui.Board;
import com.p532.brickout.shape.Ball;
import com.p532.brickout.shape.Brick;
import com.p532.brickout.shape.Clock;
import com.p532.brickout.shape.Paddle;
import com.p532.brickout.util.CommonStructureUtility;
import com.p532.brickout.util.Constants;

public class BoardObjectsTest extends TestCase{
	private Board board;
	private Ball ball;
	private Paddle paddle;
	private Clock clock;
	private Brick[][] bricks;
	
	protected void setUp() throws Exception {
		this.board = new Board(Constants.WINDOW_WIDTH,Constants.WINDOW_HEIGHT);
		this.paddle = new Paddle(Constants.PADDLE_X_START,
				Constants.PADDLE_Y_START, Constants.PADDLE_WIDTH,
				Constants.PADDLE_HEIGHT, Color.BLACK);
		this.ball = new Ball(Constants.BALL_X_START, Constants.BALL_Y_START,
				Constants.BALL_WIDTH, Constants.BALL_HEIGHT, Color.BLACK);
		this.clock = new Clock(Constants.CLOCK_LOCATION_X,
				board.getHeight() / 2, Constants.BALL_WIDTH,
				Constants.BALL_HEIGHT, Color.red);
		this.bricks = new Brick[Constants.BRICK_COLUMNS][Constants.BRICK_ROWS];
		CommonStructureUtility.makeBricks(this.bricks);
	}
	
	public void testBoardDimensions() {
		assertNotNull(this.board);
		assertEquals("Board's width", Constants.WINDOW_WIDTH, this.board.getWidth());
		assertEquals("Board's height", Constants.WINDOW_HEIGHT, this.board.getHeight());
	}

	public void testBallInitialLocation() {
		assertNotNull(this.ball);
		assertEquals("Ball's initial x ", Constants.BALL_X_START,this.ball.getX() );
		assertEquals("Ball's initial y ", Constants.BALL_Y_START,this.ball.getY() );
	}
	
	public void testPaddleInitialLocation() {
		assertNotNull(this.paddle);
		assertEquals("Paddle's initial x ", Constants.PADDLE_X_START,this.paddle.getX() );
		assertEquals("Paddle's initial y ", Constants.PADDLE_Y_START,this.paddle.getY() );
	}
	
	public void testClockInitialValues() {
		assertNotNull(this.clock);
		assertEquals("Clock's initial x ", Constants.CLOCK_LOCATION_X, this.clock.getX());
		assertEquals("Clock's initiail y ", Constants.CLOCK_LOCATION_Y, this.clock.getY());
		assertEquals("Clock's initial time", 0, this.clock.getTime());
	}
	
	public void testBricksInitialValues() {
		assertNotNull(this.bricks);
		assertEquals("Total number of bricks", Constants.MAX_BRICKS, this.bricks.length*this.bricks[0].length);
	}
	
	public void testLayerButtonFunctionality() {
		JButton btn= new JButton("CHANGE LAYOUT");
		assertNotNull(this.board);
		assertNotNull(this.board.getLayerBtn());
		assertEquals("Change layer button text", btn.getText(), this.board.getLayerBtn().getText());
		this.board.getLayerBtn().doClick();
		assertEquals("Layer should be flow", this.board.getLayoutPanel().getFlowLayout(),this.board.getLayoutPanel().getLayout());
	}
}
