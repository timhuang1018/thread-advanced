package async_to_sync;

/**
 * Note how the main thread exits before the asynchronous execution is completed.
 *
 * Your task is to make the execution synchronous without changing the original classes
 * (imagine, you are given the binaries and not the source code)
 * so that main thread waits till asynchronous execution is complete. In other words,
 * the line#19 only executes once the asynchronous task is complete.
 */
public class DemoProblem {
    public static void main( String args[] ) throws Exception{
        Executor executor = new Executor();

        executor.asynchronousExecution(() -> {
            System.out.println("I am done");
        });


        System.out.println("main thread exiting...");
    }
}
