package task03;

public class MyThread extends Thread{

    @Override
    public void run() {
        for (int i = 0; i < 1_000_000; i++) {
            Task3.incrementCounter();

        }
        System.out.println(Thread.currentThread().getName() + " end!");
    }
}