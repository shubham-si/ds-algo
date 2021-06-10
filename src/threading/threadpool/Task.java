package threading.threadpool;

public class Task implements Runnable{

	private int id;
	public Task(int id) {
		this.id = id;
	}
	@Override
	public void run() {
		try {
			System.out.println("Task started - "+ id);
			Thread.sleep(1500);
			System.out.println("Task done - "+id);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
}

// simply runnable.run() doesn't' work, when we do .start(new Runnable(){}) > it send to ThreadSchedular to make it runnable
// in executors, threadpool's <since run() is called itself from a runnable environment> 