package com.tompai.t1224;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Demo1_DeleteNumber {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		while (scanner.hasNext()) {
			int n = scanner.nextInt();
			if (n > 1000 || n < 1) {
				continue;
			}
			int[] arrs = new int[n];
			for (int i = 0; i < n; i++) {
				arrs[i] = i;
			}
			System.out.println(delete(arrs));
		}

	}

	private static int delete(int[] arrs) {
		Queue<Integer> queue = new LinkedList<>();
		for (int i = 0; i < arrs.length; i++) {
			queue.add(arrs[i]);
		}
		while (queue.size() != 1) {
			int count = 0;
			while (count != 2) {
				queue.add(queue.poll());
				count++;
			}
			queue.poll();
		}
		return queue.element();
	}
}
