package com.company;


import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class paralizeshell {
    static int[] arr = {5, 52, 6, 3, 4, 90, 18, 46, 5, 52, 6, 3, 4, 90, 18, 46, 5, 52, 6, 3, 4, 90, 18, 46, 5, 52, 6, 3, 4, 90, 18, 46};
    static ExecutorService pool = Executors.newCachedThreadPool();

    public static class ShellSortTask implements Runnable {
        int i = 0;
        int h = 0;
        CountDownLatch l;

        public ShellSortTask(int i, int h, CountDownLatch latch) {
            this.i = i;
            this.h = h;
            this.l = latch;
        }

        @Override
        public void run() {
            if (arr[i] < arr[i - h]) {
                int tmp = arr[i];
                int j = i - h;
                while (j >= 0 && arr[j] > tmp) {
                    arr[j + h] = arr[j];
                    j -= h;
                }
                arr[j + h] = tmp;
            }
            l.countDown();
            //System.out.println("run:: i: "+i+";h: "+h);
        }
    }

    public static void pShellSort(int[] arr) throws InterruptedException {
        // 计算出最大的h值
        int h = 1;
        CountDownLatch latch = null;
        while (h <= arr.length / 3) {
            h = h * 3 + 1;
        }
        while (h > 0) {
            System.out.println("h=" + h);
            if (h >= 4)
                latch = new CountDownLatch(arr.length - h);
            for (int i = h; i < arr.length; i++) {
                // 控制线程数量
                if (h >= 4) {
                    System.out.println("wai:: i: "+i+";h: "+h);
                    pool.execute(new ShellSortTask(i, h, latch));
                } else {
                    if (arr[i] < arr[i - h]) {
                        int tmp = arr[i];
                        int j = i - h;
                        while (j >= 0 && arr[j] > tmp) {
                            arr[j + h] = arr[j];
                            j -= h;
                        }
                        arr[j + h] = tmp;
                    }
                    // System.out.println(Arrays.toString(arr));
                }
            }
            // 等待线程排序完成，进入下一次排序
            latch.await();
            // 计算出下一个h值
            h = (h - 1) / 3;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println(arr.length);
        pShellSort(arr);
        for (int a : arr) {
            System.out.println(a);
        }
    }
}