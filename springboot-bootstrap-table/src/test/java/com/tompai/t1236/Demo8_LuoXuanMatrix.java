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

public class Demo8_LuoXuanMatrix<K, V> {
	/*
	给定一个m x n大小的矩阵（m行，n列），按螺旋的顺序返回矩阵中的所有元素。
	 */

	public static void main(String[] args) {

		try (Scanner scanner = new Scanner(System.in)) {
			{
				while (scanner.hasNext()) {

					String input = scanner.nextLine();
					int last = Integer.parseInt(input);
					int[][] arr={
							{1,2,3,4},
							{5,6,7,8},
							{9,10,11,12}};
					
					System.out.println(spiralOrder(arr));
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
	}

	public static ArrayList<Integer> spiralOrder(int[][] matrix) {
		ArrayList<Integer> res = new ArrayList<>();
		if (matrix.length == 0)
			return res;
		int top = 0, bottom = matrix.length - 1;
		int left = 0, right = matrix[0].length - 1;
		while (top <= bottom && left <= right) {
			for (int i = left; i <= right; i++) {
				res.add(matrix[top][i]);
			}
			for (int i = top + 1; i <= bottom; i++) {
				res.add(matrix[i][right]);
			}
			for (int i = right - 1; i >= left && top != bottom; i--) {
				res.add(matrix[bottom][i]);
			}
			for (int i = bottom - 1; i >= top + 1 && left != right; i--) {
				res.add(matrix[i][left]);
			}
			left++;
			right--;
			top++;
			bottom--;
		}
		return res;
	}
}
