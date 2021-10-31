package unisex_bathroom;

class Try {

    static String WOMEN = "women";
    static String MEN = "men";
    static String NONE = "none";

    String inUseBy = NONE;
    int empsInBathroom = 0;

    void useBathroom(String name) throws InterruptedException {
        System.out.println(name + " using bathroom. Current employees in bathroom = " + empsInBathroom);
        Thread.sleep(10000);
        System.out.println(name + " done using bathroom");
    }

    void maleUseBathroom(String name) throws InterruptedException {

        synchronized (this) {
            while (inUseBy.equals(WOMEN)) {
                // The wait call will give up the monitor associated
                // with the object, giving other threads a chance to
                // acquire it.
                this.wait();
            }
            empsInBathroom++;
            inUseBy = MEN;
        }

        useBathroom(name);

        synchronized (this) {
            empsInBathroom--;

            if (empsInBathroom == 0) inUseBy = NONE;
            // Since we might have just updateded the value of
            // inUseBy, we should notifyAll waiting threads
            this.notifyAll();
        }
    }

    void femaleUseBathroom(String name) throws InterruptedException {

        synchronized (this) {
            while (inUseBy.equals(MEN)) {
                this.wait();
            }
            empsInBathroom++;
            inUseBy = WOMEN;
        }

        useBathroom(name);

        synchronized (this) {
            empsInBathroom--;

            if (empsInBathroom == 0) inUseBy = NONE;
            // Since we might have just updateded the value of
            // inUseBy, we should notifyAll waiting threads
            this.notifyAll();
        }
    }
}
