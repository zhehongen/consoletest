package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class parallelsearch {
    static int[] arr = {1, 2, 7, 4, 23, 43, 56, 78, 1, 2, 3, 4, 23, 43, 56, 78, 1, 2, 3, 4, 23, 43, 56, 78};
    static ExecutorService pool = Executors.newCachedThreadPool();
    static final int Thread_Num = 3;
    static AtomicInteger result = new AtomicInteger(-1);

    public static int search(int searchValue, int beginPos, int endPos) {
        int i = 0;
        for (i = beginPos; i < endPos; i++) {
            if (result.get() >= 0) {
                System.out.println("找到");
                return result.get();
            }
            if (arr[i] == searchValue) {
                //如果设置失败，表示其他线程已经先找到了
                if (!result.compareAndSet(-1, i)) {
                    System.out.println("其他线程先找到");
                    return result.get();
                }
                return i;
            }
        }
        return -1;
    }

    public static class SearchTask implements Callable<Integer> {
        int begin, end, searchValue;

        public SearchTask(int searchValue, int begin, int end) {
            this.begin = begin;
            this.end = end;
            this.searchValue = searchValue;
        }

        public Integer call() {
            int re = search(searchValue, begin, end);
            return re;
        }
    }

    public static int pSearch(int searchValue) throws InterruptedException, ExecutionException {
        int subArrSize = arr.length / Thread_Num + 1;
        List<Future<Integer>> re = new ArrayList<Future<Integer>>();
        for (int i = 0; i < arr.length; i += subArrSize) {
            int end = i + subArrSize;
            if (end >= arr.length) end = arr.length;
            re.add(pool.submit(new SearchTask(searchValue, i, end)));
        }
        for (Future<Integer> fu : re) {
            System.out.println(fu.get());
            if (fu.get() >= 0)
                return fu.get();
        }
        return -1;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println(pSearch(3));
    }
}
