package threading.threadpool;

public class ThreadWorker implements Runnable {

	private CustomBlockingQueue<Runnable> queue;

	public ThreadWorker(CustomBlockingQueue<Runnable> queue) {
		this.queue = queue;
	}

	@Override
	public void run() {

		try {
			while (true) {
				String workerName = Thread.currentThread().getName();
				System.out.println(workerName + "started");
				Runnable task = queue.take();
				// since run() is called from a runnable environment it works! i.e., no need to wrap inside Thread(task) and do (start())
				task.run();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}

//simply runnable.run() doesn't' work, when we do start(new Runnable(){}) > it send to ThreadSchedular to make it runnable
//in executors, threadpool's <since run() is called itself from a runnable environment> 