package com.p532.brickout.main;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.UIManager;

import org.apache.log4j.Logger;



import com.p532.brickout.gameui.Board;
import com.p532.brickout.impl.JSONFileHandler;
import com.p532.brickout.util.Constants;

//Class definition
public final class Main implements Constants {
    // Variables
	final static Logger LOGGER = Logger.getLogger(Main.class);
    private static JFrame frame;
    private static Board board;
    private static Dimension dim;
    
    private Main() {
		// TODO Auto-generated constructor stub
	}
   
    public static void main(String[] args) {
        // Set look and feel to that of OS
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
        	LOGGER.error("Error Message : ", e);
        }
        frame = new JFrame("Brick Breaker 3.0");
        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        board = new Board(WINDOW_WIDTH, WINDOW_HEIGHT);

        frame.getContentPane().add(board);
        
        /*Setting the application window image icon*/
		java.net.URL url = ClassLoader.getSystemResource("Folders-OS-Games-alt-Metro-icon.png");
        Toolkit kit = Toolkit.getDefaultToolkit();
        Image img = kit.createImage(url);
        frame.setIconImage(img);
        
        /*Place frame in the middle of the screen*/
        try {
    		dim = Toolkit.getDefaultToolkit().getScreenSize();
    		frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
        } catch (Exception e) {
            LOGGER.error("Error Message : ", e);
        
        }

        frame.setVisible(true);
    }
}