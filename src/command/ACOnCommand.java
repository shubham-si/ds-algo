package command;

public class ACOnCommand implements Command{
	private AC ac;
	
	public ACOnCommand(AC ac) {
		this.ac = ac;
	}
	
	@Override
	public void execute() {
		ac.start();
		
	}

	@Override
	public void undo() {
		
		
	}
}
