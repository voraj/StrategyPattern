package com.p532.brickout.util;

import java.util.List;

import com.p532.brickout.intf.GameCommand;

public class CommandBroker implements GameCommand {

	private transient List<GameCommand> gameCommands;
	
	
	public CommandBroker(List<GameCommand> gameCommands) {
		super();
		this.gameCommands = gameCommands;
	}

	@Override
	public void executeCommand() {
		
		for(GameCommand gameCommand : gameCommands)	{
			
			gameCommand.executeCommand();
		}
	}

	@Override
	public void undo() {
		
for(GameCommand gameCommand : gameCommands)	{
			
			gameCommand.undo();
		}
	}

}
