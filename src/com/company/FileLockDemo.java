package com.company;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

public class FileLockDemo {


    public static void main(String[] args) {
        File file = new File("d:/test.txt");
        RandomAccessFile raf = null;
        FileChannel fc = null;
        FileLock fl = null;
        try {
            raf = new RandomAccessFile(file, "rw");
            fc = raf.getChannel();
            //此处主要是针对多线程获取文件锁时轮询锁的状态。
            // 如果只是单纯获得锁的话，直接fl = fc.tryLock();即可
            while (true) {
                try {
                    //无参独占锁
                    fl = fc.tryLock();
                    //采用共享锁
                    //fl = fc.tryLock(0,Long.MAX_VALUE,true);
                    if (fl != null) {
                        System.out.println("get the lock");
                        break;
                    }
                } catch (Exception e) {
                    //如果是同一进程的多线程，重复请求tryLock()会抛出OverlappingFileLockException异常
                    System.out.println("current thread is block");
                }
            }
            //获得文件锁权限后，进行相应的操作
            fl.release();
            fc.close();
            raf.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fl != null && fl.isValid()) {
                try {
                    fl.release();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

    }
}
