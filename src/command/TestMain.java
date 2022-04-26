package command;

public class TestMain {

	public static void main(String ...args) {

		// Receiver
		TV tv = new TV();
		// Commands
		TVOnCommand onTv = new TVOnCommand(tv);
		TVOffCommand offTv = new TVOffCommand(tv);

		// Invoker -> Receiver
		Invoker remote = new Invoker(onTv);
		remote.execute();
		remote.setCommand(offTv);
		remote.execute();

	}
}
