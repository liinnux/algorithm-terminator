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

public class Demo8_MergeTwoArray<K, V> {

	//给出一个有序的整数数组 A 和有序的整数数组 B ，请将数组 B 合并到数组 A 中，变成一个有序的升序数组
	//输入：[4,5,6],[1,2,3]
	//	[1,2,3,4,5,6]

	public static void main(String[] args) {

		try (Scanner scanner = new Scanner(System.in)) {
			{
				while (scanner.hasNext()) {

					String input = scanner.nextLine();
					String[] ss = input.split("],\\[", -1);
					String[] as = ss[0].substring(1).split(",", -1);
					String[] bs = ss[1].substring(0, ss[1].length() - 1).split(",", -1);

					int m = as.length;
					int n = bs.length;
					int[] A = new int[m + n];
					for (int i = 0; i < m; i++) {
						A[i] = Integer.parseInt(as[i]);
					}

					int[] B = new int[n];
					for (int i = 0; i < n; i++) {
						B[i] = Integer.parseInt(bs[i]);
					}

					merge(A, m, B, n);

					for (int i = 0; i < m + n; i++) {
						System.out.print(A[i] + " ");
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.print(e);
		}
	}


	public static void merge(int[] A, int m, int[] B, int n) {
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

}
