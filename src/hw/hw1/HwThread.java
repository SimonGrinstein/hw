package hw.hw1;

public class HwThread  extends Thread{

    @Override
    public void run() {
        for (int i = 1_000_000; i <= 2_000_000; i++) {
            if(i % 21 == 0 && String.valueOf(i).contains("3")){
                Hw1.incrementCounterInThread();
            }
        }
    }
}