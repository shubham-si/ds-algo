package command;

public class TestMain {

	public static void main(String ...args) {

		// Receiver
		TV tv = new TV();
		TVOnCommand onTv = new TVOnCommand(tv);
		TVOffCommand offTv = new TVOffCommand(tv);

		// Invoker
		Invoker remote = new Invoker(onTv);
		remote.execute();
		remote.setCommand(offTv);
		remote.execute();
		
		
	}
}
