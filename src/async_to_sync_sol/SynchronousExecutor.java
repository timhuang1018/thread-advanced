package async_to_sync_sol;

import async_to_sync.Callback;
import async_to_sync.Executor;

/**
 * This is complete code
 *
 * Note the main thread has its print-statement printed
 * after the asynchronous execution thread print its print-statement verifying that the execution is now synchronous.
 *
 * Follow-up Question: Is the method asynchronousExecution() thread-safe?
 *
 * The way we have constructed the logic,
 * all the variables in the overridden method will be created on the thread-stack
 * for each thread therefore the method is threadsafe and multiple threads can execute it in parallel.
 */
public class SynchronousExecutor extends Executor {

    @Override
    public void asynchronousExecution(Callback callback) throws Exception {

        Object signal = new Object();
        final boolean[] isDone = new boolean[1];

        Callback cb = new Callback() {

            @Override
            public void done() {
                callback.done();
                synchronized (signal) {
                    signal.notify();
                    isDone[0] = true;
                }
            }
        };

        // Call the asynchronous executor
        super.asynchronousExecution(cb);

        synchronized (signal) {
            while (!isDone[0]) {
                signal.wait();
            }
        }

    }



    public static void main(String[] args) throws Exception {
        Executor executor = new SynchronousExecutor();

        executor.asynchronousExecution(() -> {
            System.out.println("I am done");
        });

        System.out.println("main thread exiting...");
    }
}
