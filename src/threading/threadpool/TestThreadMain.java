package threading.threadpool;

public class TestThreadMain {

	public static void main(String ...args) throws InterruptedException {
		// BlockingQueue size 3 and 4 worker threads
		CustomThreadPool tp = new CustomThreadPool(3, 4);
		
		// create some tasks and submit to threadpool
		for(int i=0; i<10; i++) {
			Task task = new Task(i);
			tp.execute(task);			// execute: Runnable
		}
	}
}
