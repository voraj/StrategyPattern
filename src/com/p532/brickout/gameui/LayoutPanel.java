package com.p532.brickout.gameui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.p532.brickout.util.BoardListener;
import com.p532.brickout.util.Constants;

public class LayoutPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Board board;
	private FlowLayout flow = new FlowLayout(FlowLayout.CENTER);
	private BorderLayout border = new BorderLayout();
	private JButton startBtn, stopBtn, undoBtn, replayBtn, loadBtn, saveBtn;
	
	/*Constructor for initializing the frame*/
	public LayoutPanel(Board board) {
		this.board = board;
		this.setLayout(border);
		this.setBounds(Constants.WINDOW_WIDTH / 3 - 10, 
				Constants.WINDOW_HEIGHT- 155, 
				Constants.WINDOW_WIDTH - Constants.WINDOW_WIDTH / 3, 
				120);
		this.startBtn = new JButton("START");
        this.stopBtn = new JButton("STOP");
        this.replayBtn = new JButton("REPLAY");
        this.undoBtn = new JButton("UNDO");
        this.loadBtn = new JButton("LOAD");
        this.saveBtn = new JButton("SAVE");
        
		JPanel centerPanel = new JPanel(new BorderLayout());
		centerPanel.add(this.loadBtn, BorderLayout.EAST);
		centerPanel.add(this.saveBtn, BorderLayout.WEST);
		this.add(this.startBtn, BorderLayout.NORTH);
		this.add(this.stopBtn, BorderLayout.SOUTH);
		this.add(this.undoBtn, BorderLayout.WEST);
		this.add(this.replayBtn, BorderLayout.EAST);
		this.add(centerPanel, BorderLayout.CENTER);	
        
        this.startBtn.addActionListener(new BoardListener(this.board));
        this.stopBtn.addActionListener(new BoardListener(this.board));
        this.undoBtn.addActionListener(new BoardListener(this.board));
        this.replayBtn.addActionListener(new BoardListener(this.board));
        this.loadBtn.addActionListener(new BoardListener(this.board));
        this.saveBtn.addActionListener(new BoardListener(this.board));
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}

	//Accessors
	public JButton getStartBtn() {
		return this.startBtn;
	}
	
	public JButton getStopBtn() {
		return this.stopBtn;
	}
	
	public JButton getUndoBtn() {
		return this.undoBtn;
	}
	
	public JButton getReplayBtn() {
		return this.replayBtn;
	}
	
	public JButton getLoadBtn() {
		return this.loadBtn;
	}
	
	public JButton getSaveBtn() {
		return this.saveBtn;
	}
	
	public FlowLayout getFlowLayout() {
		return this.flow;
	}
	
	public BorderLayout getBorderLayout() {
		return this.border;
	}
	
	//Mutators
	public void setStartBtn(JButton start){
		this.startBtn = start;
	}
	
	public void setStopBtn(JButton stop){
		this.stopBtn = stop;
	}
	
	public void setUndoBtn(JButton undo){
		this.undoBtn = undo;
	}
	
	public void setReplayBtn(JButton replay){
		this.replayBtn = replay;
	}
	
	public void setLoadBtn(JButton load){
		this.loadBtn = load;
	}
	
	public void setSaveBtn(JButton save){
		this.saveBtn = save;
	}
}
