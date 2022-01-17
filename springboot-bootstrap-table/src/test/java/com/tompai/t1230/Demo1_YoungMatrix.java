/**
 * 
 */
package com.tompai.t1230;

/**
 * @author Administrator
 *
 */
public class Demo1_YoungMatrix {
	public static void main(String[] args) {
		Demo1_YoungMatrix test = new Demo1_YoungMatrix();
		int[][] A = { { 1, 2, 8, 9 }, { 2, 4, 9, 12 }, { 4, 7, 10, 13 }, { 6, 8, 11, 15 } };
		if (test.YoungMatrix(A, 6))
			System.out.println("矩阵A中包含元素6");
		else
			System.out.println("矩阵A中不包含元素6");
		if (test.YoungMatrix(A, 5))
			System.out.println("矩阵A中包含元素5");
		else
			System.out.println("矩阵A中不包含元素5");
	}

	public boolean YoungMatrix(int[][] A, int key) {
		int i = 0, j = A[0].length - 1;
		int temp = A[i][j];
		while (true) {
			if (temp == key) {
				return true;
			} else if (temp < key && i < A.length - 1) {
				temp = A[++i][j];
			} else if (temp > key && j > 0) {
				temp = A[i][--j];
			} else {
				return false;
			}
		}
	}

}
