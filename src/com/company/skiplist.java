package com.company;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.regex.Pattern;

public class skiplist {
    public static void main(String[] args) throws InterruptedException {
        Map<Integer, Integer> map = new ConcurrentSkipListMap<Integer, Integer>();
        for (int i = 0; i < 30; i++) {
            map.put(i, i);
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey());
        }
    }
}
