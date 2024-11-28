package hw.hw1;

public class Hw1 {
    private static int counter = 0;

    public static synchronized void incrementCounter() {
        counter++;
    }

    public static void main(String[] args) {

        //HwThread hwThread = new HwThread();
        //hwThread.start();

        for (int i = 0; i <= 2_000_000; i++) {

            if(i % 21 == 0 && String.valueOf(i).contains("3")){
                //System.out.println("i = " + i);
                incrementCounter();
            }

        }

        //try {
        //    hwThread.join();
        //} catch (InterruptedException e) {
        //    throw new RuntimeException(e);
        //}

        System.out.println("counter = " + counter);
    }
}
