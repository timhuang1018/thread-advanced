package token_bucket;

public class MultithreadedTokenBucketFilter {

    private long possibleTokens = 0;
    private final int MAX_TOKENS;
    private final int ONE_SECOND = 1000;

    public MultithreadedTokenBucketFilter(int maxTokens) {

        MAX_TOKENS = maxTokens;
    }

    private void daemonThread() {
    }

    void getToken() throws InterruptedException {
    }








    //void getToken() throws InterruptedException {
    //
    //        synchronized (this) {
    //            while (possibleTokens == 0) {
    //                this.wait();
    //            }
    //            possibleTokens--;
    //        }
    //
    //        System.out.println(
    //                "Granting " + Thread.currentThread().getName() + " token at " + System.currentTimeMillis() / 1000);
    //    }




    // private void daemonThread() {
    //
    //        while (true) {
    //            synchronized (this) {
    //                if (possibleTokens < MAX_TOKENS) {
    //                    possibleTokens++;
    //                }
    //                this.notify();
    //            }
    //            try {
    //                Thread.sleep(ONE_SECOND);
    //            } catch (InterruptedException ie) {
    //                // swallow exception
    //            }
    //        }
    //    }
}
