package token_bucket_sol;

import token_bucket.TokenBucketFilter;

import java.util.HashSet;
import java.util.Set;

public class TokenBucketFilterCom2 {

        private int MAX_TOKENS;
        private long lastRequestTime = System.currentTimeMillis();
        long possibleTokens = 0;

        public TokenBucketFilterCom2(int maxTokens) {
            MAX_TOKENS = maxTokens;
        }

        synchronized void getToken() throws InterruptedException {

            possibleTokens += (System.currentTimeMillis() - lastRequestTime) / 1000;

            if (possibleTokens > MAX_TOKENS) {
                possibleTokens = MAX_TOKENS;
            }

            if (possibleTokens == 0) {
                Thread.sleep(1000);
            } else {
                possibleTokens--;
            }
            lastRequestTime = System.currentTimeMillis();

            System.out.println("Granting " + Thread.currentThread().getName() + " token at " + (System.currentTimeMillis() / 1000));
        }

        public static void runTestMaxTokenIsTen() throws InterruptedException {

            Set<Thread> allThreads = new HashSet<Thread>();
            final TokenBucketFilterCom2 tokenBucketFilter = new TokenBucketFilterCom2(5);

            // Sleep for 10 seconds.
            Thread.sleep(10000);

            // Generate 12 threads requesting tokens almost all at once.
            for (int i = 0; i < 12; i++) {

                Thread thread = new Thread(new Runnable() {
                    public void run() {
                        try {
                            tokenBucketFilter.getToken();
                        } catch (InterruptedException ie) {
                            System.out.println("We have a problem");
                        }
                    }
                });
                thread.setName("Thread_" + (i + 1));
                allThreads.add(thread);
            }

            for (Thread t : allThreads) {
                t.start();
            }

            for (Thread t : allThreads) {
                t.join();
            }
        }

    public static void main(String[] args) {
        try {
            runTestMaxTokenIsTen();
        }catch (InterruptedException e){
            System.out.println(e);
        }
    }
}
