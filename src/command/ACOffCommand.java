package command;

public class ACOffCommand implements Command{
	private AC ac;
	
	public ACOffCommand(AC ac) {
		this.ac = ac;
	}
	
	@Override
	public void execute() {
		ac.shutdown();
		
	}

	@Override
	public void undo() {


	}
}
