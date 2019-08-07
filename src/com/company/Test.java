package com.company;

import java.util.*;
import java.util.concurrent.CountDownLatch;

@SuppressWarnings("Duplicates")
public class Test {
    public static void main(String[] args) {
        //HashMap
        //
        //
        //
        //
        //
        new WriteThread("d:/test/test_1.txt", "d:/test/test.txt", "write_thread-1").start();
        new WriteThread("d:/test/logs.txt", "d:/test/test.txt", "write_thread-2").start();

        new ReadThread("d:/test/test.txt", "read_thread-1").start();
        new ReadThread("d:/test/test.txt", "read_thread-2").start();

    }
}

