package hw.hw1;
/*
    Дан диапазон чисел: от 1 до 2_000_000 включительно.
    Задача: найти, сколько чисел из этого диапазона делятся нацело на 21 и при этом содержат цифру 3.
    Решить данную задачу в один поток.
    Решить данную задачу в два потока, разделив между потоками заданный диапазон чисел пополам.
    Сравнить результаты двух решений - они должны совпадать.
*/

public class Hw1 {


    private static int counterWithThread = 0;

    public static synchronized void incrementCounterInThread() {
        counterWithThread++;
    }

    public static void main(String[] args) {

        int counterNoThread = 0;

        for (int i = 0; i <= 2_000_000; i++) {
            if(i % 21 == 0 && String.valueOf(i).contains("3")){
                //System.out.println("i = " + i);
                counterNoThread++;
            }
        }
        System.out.println("counterNoThread = " + counterNoThread);

        HwThread hwThread = new HwThread();
        hwThread.start();

        for (int i = 0; i < 1_000_000; i++) {
            if(i % 21 == 0 && String.valueOf(i).contains("3")){
                //System.out.println("i = " + i);
                incrementCounterInThread();
            }
        }

        try {
            hwThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("counterWithThread = " + counterWithThread);
    }
}
