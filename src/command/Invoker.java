package command;

import java.util.Stack;

public class Invoker {
	
	private final String invokerName = "REMOTE_CONTROL";
	private Command command;
	
	private Stack<Command> undo;
	private Stack<Command> redo;

	public Invoker(Command command) {
		this.command = command;
		this.undo = new Stack<Command>();
		this.redo = new Stack<Command>();
		this.undo.push(command);
	}
	
	public void setCommand(Command command) {
		this.command = command;
		this.undo.push(command);
	}
	
	public void execute() {
		this.command.execute();
	}
	
	public void undo() {
		
	}

}
