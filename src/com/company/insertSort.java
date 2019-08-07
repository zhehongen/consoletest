package com.company;

public class insertSort {

    public static void inserSort(int[] arr) {
        int length = arr.length;
        int j, i, key;
        for (i = 1; i < length; i++) {
            //key为要准备插入的元素
            key = arr[i];
            j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            //找到合适的位置 插入key
            arr[j + 1] = key;
        }
    }

    public static void main(String[] args) {
        int[] arr = {5, 52, 6, 3, 4, 90, 18, 46};
        inserSort(arr);
        for (int a : arr) {
            System.out.println(a);
        }
    }
}
