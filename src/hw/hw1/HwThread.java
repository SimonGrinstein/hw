package hw.hw1;

public class HwThread  extends Thread{

    @Override
    public void run() {
        for (int i = 0; i < 1_000_000; i++) {
            Hw1.incrementCounter();
        }
        System.out.println(Thread.currentThread().getName() + " end!");
    }
}