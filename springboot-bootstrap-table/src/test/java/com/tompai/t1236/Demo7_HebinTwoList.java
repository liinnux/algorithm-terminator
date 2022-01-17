package com.tompai.t1236;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

public class Demo7_HebinTwoList<K, V> {

	/*
	 给出一个有序的整数数组 A 和有序的整数数组 B ，请将数组 B 合并到数组 A 中，变成一个有序的升序数组
	输入：{-1,2,4},{1,3,4}
	
	返回值：{-1,1,2,3,4,4}*/

	public static void main(String[] args) {

		try (Scanner scanner = new Scanner(System.in);) {
			while (scanner.hasNext()) {
				String str = scanner.nextLine();
				if (str == null || str.length() == 0) {
					System.out.println("输入不合法");
					return;
				} else {
					str = str.replace("},{", ",");
					if (str.length() <= 3) {
						System.out.println("{}");
						return;
					}
					int n = str.length() - 2;
					Queue<Integer> queue = new PriorityQueue<>();
					String[] res = str.substring(1, str.length() - 1).split(",", -1);
					for (int i = 0; i < res.length; i++) {
						int k = Integer.parseInt(res[i]);
						queue.add(k);
					}
					System.out.print("{");
					while (!queue.isEmpty()) {
						if (queue.size() == 1) {
							System.out.print(queue.poll() + "}");
						} else {
							System.out.print(queue.poll() + ",");
						}
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void merge(int A[], int m, int B[], int n) {
        if (m == 0) {
            System.arraycopy(B, 0, A, 0, n);
        }
        int im = m - 1;
        int in = n - 1;
        while (in >= 0) {
            if (im < 0) {
                System.arraycopy(B, 0, A, 0, in + 1);
                break;
            }
            if (A[im] >= B[in]) {
                A[im + in + 1] = A[im--];
            } else {
                A[im + in + 1] = B[in--];
            }
 
        }
    }
	
	public void merge2(int A[], int m, int B[], int n) {
        int len=m+n-1;
        int i=m-1;
        int j=n-1;
        while(i>=0&&j>=0){
            if(A[i]>B[j]){
                A[len--]=A[i--];
            }else{
                A[len--]=B[j--];
            }
        }
        while(i>=0){
            A[len--]=A[i--];
        }
        while(j>=0){
            A[len--]=B[j--];
        }
    }
}
