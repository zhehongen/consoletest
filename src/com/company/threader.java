package com.company;

public class threader implements Runnable {
    public static void main(String[] args) {
        ThreadGroup tg = new ThreadGroup("PrintGroup");
        Thread t1 = new Thread(tg, new threader(), "T1");
        Thread t2 = new Thread(tg, new threader(), "T2");
        t1.start();
        t2.start();
        System.out.println(tg.activeCount());
        tg.list();
    }

    @Override
    public void run() {
        String groupAndName = Thread.currentThread().getThreadGroup().getName()
                + "-" + Thread.currentThread().getName();
        while (true) {
            System.out.println("I am " + groupAndName);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

