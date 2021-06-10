package threading.semaphores;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


public class App {

    public static void main(String[] args) throws Exception {
        
        ExecutorService executor = Executors.newCachedThreadPool();
        
        for(int i=0; i < 200; i++) {
            executor.submit(new Runnable() {
                public void run() {
                    Connection.getInstance().connect();
                }
            });
        }
        // wait for all threads to complete but didn't accept new tasks to run
        executor.shutdown();
        // current thread blocked till the time elapsed or interrupt whichever occurs first
        executor.awaitTermination(1, TimeUnit.DAYS);
    }
}

