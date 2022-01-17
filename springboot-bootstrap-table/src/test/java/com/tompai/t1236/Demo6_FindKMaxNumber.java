package com.tompai.t1236;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Set;

public class Demo6_FindKMaxNumber<K, V> {

	 public int findKth(int[] a, int n, int K) {
	        // write code here
	        quickSort(a,0,n-1);
	        int res = a[n-K];
	        return res;
	    }
	     
	    private void swap(int[] arr,int i,int j){
	        int temp = arr[i];
	        arr[i] =  arr[j];
	        arr[j] = temp;
	    }
	     
	    private void quickSort(int[] arr,int l,int r){
	        if(l >= r)
	            return;
	        int i = l;
	        int j = r;
	        while(i < j){
	            while(i<j && arr[l] <= arr[j]) j--;
	            while(i<j && arr[l] >= arr[i]) i++;
	            swap(arr,i,j);
	        }
	        swap(arr,i,l);
	        quickSort(arr,l,i-1);
	        quickSort(arr,i+1,r);
	    }
}
