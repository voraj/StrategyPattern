package com.p532.brickout.gameui;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.p532.brickout.util.Constants;

public class GameFileChooser {

	private JPanel mainPanel;
	private String rootDirectory;
	private String dialogueTitle;
	private String buttonTxt;
	private int selectionMode;
	
	
	
	public GameFileChooser(JPanel mainPanel, String rootDirectory, String dialogueTitle, int selectionMode, String buttonTxt) {
		super();
		this.mainPanel = mainPanel;
		this.rootDirectory = rootDirectory;
		this.dialogueTitle = dialogueTitle;
		this.selectionMode = selectionMode;
		this.buttonTxt = buttonTxt;
	}


	public JPanel getMainPanel() {
		return mainPanel;
	}


	public void setMainPanel(JPanel mainPanel) {
		this.mainPanel = mainPanel;
	}


	public String getRootDirectory() {
		return rootDirectory;
	}


	public void setRootDirectory(String rootDirectory) {
		this.rootDirectory = rootDirectory;
	}


	public String getDialogueTitle() {
		return dialogueTitle;
	}


	public void setDialogueTitle(String dialogueTitle) {
		this.dialogueTitle = dialogueTitle;
	}


	public int getSelectionMode() {
		return selectionMode;
	}


	public void setSelectionMode(int selectionMode) {
		this.selectionMode = selectionMode;
	}

	public String getPath()	{
		FileFilter filter = new FileNameExtensionFilter("JSON", "json");
		String selectedPath = null;
		JFileChooser chooser = new JFileChooser();
		mainPanel.add(chooser);
		chooser.setCurrentDirectory(new File(this.rootDirectory));
		chooser.setDialogTitle(this.dialogueTitle);
		chooser.setFileFilter(filter);
		chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		/*
		  disable the "All files" option.
		*/
		chooser.setAcceptAllFileFilterUsed(false);
		//   
		if (chooser.showDialog(mainPanel, this.buttonTxt) == JFileChooser.APPROVE_OPTION) {
			selectedPath = chooser.getSelectedFile().getAbsolutePath();
			if(!selectedPath.endsWith(".json")) {
				selectedPath += ".json";
			}
		} else {
			selectedPath = Constants.NO_SELECTION_MSG;
		} 
		return selectedPath;
	}
}
