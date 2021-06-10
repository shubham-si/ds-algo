package threading.interrupt;

public class ThreadInterrupt {
	public static void main(String[] args) {
        CustomThread customThread = new CustomThread();
        customThread.start();

        try {
        	// sleep the main thread for 300ms
			Thread.sleep(300);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        
        if(customThread.isAlive()){
            try {
            	// customThread.setShouldExit(true);
            	customThread.interrupt();
            	// blocks main thread while interrupted is handled or child thread finishes it's closing operations e.g., finally{ i/o close}
				customThread.join();			
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        }
        
        System.out.println("Main Thread Finished");
    }
}

class CustomThread extends Thread {

    private boolean shouldExit = false;

    public void setShouldExit(boolean shouldExit) {
        this.shouldExit = shouldExit;
    }
    
    @Override
    public void run() {
        try {
            for (int i = 0; i < 10; i++) {
            	// keep polling to check for shouldExit 
            	// if master timeout's setShouldExit(false), avoid expection's
//                if(shouldExit){			
//                    break;
//                }
                System.out.println("Thread is runnning!");
                Thread.sleep(200);
            }
            System.out.println("CustomThread thread is completed");
        } catch (InterruptedException e) {
        	System.out.println("CustomThread thread is interrupted");
        	/*
        	 * Always false (interrupted status) 
        	 * Once the exception is thrown, the thread is no longer in an interrupted state.
        	 * if any thread interrupted the current thread before or while the current thread was waiting/ sleeping/ joined(waiting)... 
        	 * The interrupted status of the current thread is cleared when this exception is thrown.
        	 * Javadoc's >
        	 *     if(Thread.interrupted()) 			 // Clears interrupted status!
     					throw new InterruptedException();     	 
        	 */          
        	System.out.println("interrupted status " + isInterrupted());		// Always false (interrupted status) 
        	
        	// Thread.currentThread().interrupt() <> will again set the status to true
        	
        } finally {
        	System.out.println("Closing i/o or streams ops if there");
        }
    }
}

/* when customThread.interrupt() is called
 * Thread.currentThread().isInterrupted() <> true
   and
   Thread.interrupted() <> returns true after that reset the jvm's interruption flag to false
 * 
 */
