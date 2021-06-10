package threading.threadpool;

public class CustomThreadPool {

	private CustomBlockingQueue<Runnable> queue;

	public CustomThreadPool(int size, int nThreads) {
		// task queue
		queue = new CustomBlockingQueue<Runnable>(size);
		ThreadWorker threadWorker;
		for(int i=0; i<nThreads; i++) {
			String threadName = "ThreadWorker - "+i;
			threadWorker = new ThreadWorker(queue);
			Thread worker = new Thread(threadWorker, threadName);
			worker.start();
		}
	}
	
	public void execute(Runnable task) throws InterruptedException {
		queue.put(task);
	}
}
