package com.company;

public class shell {

    public static void shellSort(int[] arr) {
        // 计算出最大的h值
        int h = 1;
        while (h <= arr.length / 3) {
            h = h * 3 + 1;
        }
        while (h > 0) {
            for (int i = h; i < arr.length; i++) {
                if (arr[i] < arr[i - h]) {
                    int tmp = arr[i];
                    int j = i - h;
                    while (j >= 0 && arr[j] > tmp) {
                        arr[j + h] = arr[j];
                        j -= h;
                    }
                    arr[j + h] = tmp;
                }
            }
            // 计算出下一个h值
            h = (h - 1) / 3;
        }
    }
    public static void main(String[] args) {
        int[] arr = {5, 52, 6, 3, 4, 90, 18, 46};
        shellSort(arr);
        for (int a : arr) {
            System.out.println(a);
        }
    }
}
