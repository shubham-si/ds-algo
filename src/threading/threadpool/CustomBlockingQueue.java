package threading.threadpool;

import java.util.LinkedList;
import java.util.Queue;

public class CustomBlockingQueue<Type> {

	private Queue<Type> queue = new LinkedList<Type>();
	private int MAX_TASK;
	
	public CustomBlockingQueue(int size) {
		this.MAX_TASK = size;
	}
	
	public synchronized void put(Type task) throws InterruptedException {
		while(this.queue.size() == this.MAX_TASK) {
			this.wait();
		}
		queue.offer(task);
		this.notifyAll();
	}
	
	public synchronized Type take() throws InterruptedException {
		while(this.queue.isEmpty()) {
			this.wait();
		}
		this.notifyAll();
		return queue.poll();
	}
}
