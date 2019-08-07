package com.company;


public class NoVisibility {
    private volatile static boolean ready;
    private static int number;

    private static class ReaderThread extends Thread {
        public void run() {
            while (!ready) ;
            System.out.println(number);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new ReaderThread().start();
        Thread.sleep(1000);
        ready = true;
        number = 42;
        Thread.sleep(10000);
    }
}

