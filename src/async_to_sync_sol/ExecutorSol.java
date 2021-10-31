package async_to_sync_sol;

import async_to_sync.Callback;
import async_to_sync.Executor;

public class ExecutorSol extends Executor {
    public void asynchronousExecution(Callback callback) throws Exception {
        // Pass something to the base class's asynchronous
        // method implementation that the base class can notify on
        // Call the asynchronous executor
        super.asynchronousExecution(callback);
        // Wait on something that the base class's asynchronous
        // method implementation notifies for
    }

    //TODO Next, we can create an object for notification and waiting purposes
    //public void asynchronousExecution(Callback callback) throws Exception {
    //    Object signal = new Object();
    //    // Call the asynchronous executor
    //    super.asynchronousExecution(callback);
    //    synchronized (signal){
    //        signal();
    //    }
    //}

    //TODO Now we need to pass the signal object to the superclass’s asynchronousExecution() method
    // so that the asynchronous execution thread can notify() the signal variable once asynchronous execution is complete.
    // We pass in the callback object to the super class’s method. We can wrap the original callback in another callback object
    // and pass also in our signal variable to the super class. Let’s see how we can achieve that:

    //public void asynchronousExecution(Callback callback) throws Exception {
    //
    //    Object signal = new Object();
    //    Callback cb = new Callback() {
    //        @Override
    //        public void done() {
    //            callback.done();
    //            synchronized (signal) {
    //                signal.notify();
    //            }
    //        }
    //    };
    //    // Call the asynchronous executor
    //    super.asynchronousExecution(cb);
    //    synchronized (signal) {
    //            signal.wait();
    //    }
    //}

    //TODO Note that the variable signal gets captured in the scope of the new callback
    // that we define. However, the captured variable must be defined final or be effectively final.
    // Since we are assigning the variable only once, it is effectively final.
    // The code so far defines the basic structure of the solution and we need to add a few missing pieces for it to work.
    //
    //Remember we can’t use wait() method without enclosing it inside a while loop as supurious wakeups can occur.

    //public void asynchronousExecution(Callback callback) throws Exception {
    //    Object signal = new Object();
    //    boolean isDone = false;
    //    Callback cb = new Callback() {
    //        @Override
    //        public void done() {
    //            callback.done();
    //            synchronized (signal) {
    //                signal.notify();
    //                isDone = true;
    //            }
    //        }
    //    };
    //    // Call the asynchronous executor
    //    super.asynchronousExecution(cb);
    //    synchronized (signal) {
    //        while (!isDone) {
    //            signal.wait();
    //        }
    //    }
    //}

    //TODO Note that the invariant here is isDone which is set to true after the asynchronous execution is complete.
    // The last problem here is that isDone isn’t final. We can’t declare it final because isDone gets assigned to after initialization.
    // At this a slighly less elegant but workable solution is to use a boolean array of size 1 to represent our boolean.
    // The array can be final because it gets assigned memory at initialization but the contents of the array can be changed later without compromising the finality of the variable.


    //@Override
    //public void asynchronousExecution(Callback callback) throws Exception {
    //    Object signal = new Object();
    //    final boolean[] isDone = new boolean[1];
    //    Callback cb = new Callback() {
    //        @Override
    //        public void done() {
    //            callback.done();
    //            synchronized (signal) {
    //                signal.notify();
    //                isDone[0] = true;
    //            }
    //        }
    //    };
    //    // Call the asynchronous executor
    //    super.asynchronousExecution(cb);
    //    synchronized (signal) {
    //        while (!isDone[0]) {
    //            signal.wait();
    //        }
    //    }
    //}
}
