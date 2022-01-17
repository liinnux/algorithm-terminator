package com.tompai.sort;

public class MergeSort {

	
    public int[] MySort(int[] arr) {
        int i = 1;
        while (i < arr.length) {
            //原理很简单，就是先两个两个合并，然后4个，然后8个……
            for (int j = 0; j + i < arr.length; j += 2 * i) {
                merge(arr, j, j + i - 1, Math.min(j + 2 * i - 1, arr.length - 1));
            }
            i = i << 1;
        }
        return arr;
    }

    private void merge(int[] data, int left, int center, int right) {
        int length = right - left + 1;
        int[] tmp = new int[length];
        int tempIndex = 0;
        //_left是前半部分开始的位置，_right是后半部分开始的位置
        int _left = left;
        int _right = center + 1;
        while (_left <= center && _right <= right) {
            if (data[_left] <= data[_right]) {
                tmp[tempIndex++] = data[_left++];
            } else {
                tmp[tempIndex++] = data[_right++];
            }
        }
        while (_right <= right) {
            tmp[tempIndex++] = data[_right++];
        }
        while (_left <= center) {
            tmp[tempIndex++] = data[_left++];
        }
        tempIndex = 0;
        while (tempIndex < length) {
            data[left + tempIndex] = tmp[tempIndex++];
        }
    }
}
