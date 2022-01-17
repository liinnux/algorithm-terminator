/**
 * 
 */
package com.tompai.t1230;

/**
 * @author Administrator
 *
 */
public class Demo1_FindHalfDuplicateNumber {
	public static void main(String[] args) {
		Demo1_FindHalfDuplicateNumber test = new Demo1_FindHalfDuplicateNumber();
		int[] A = { 1, 2, 8, 9, 2, 2,  1, 1, 1 };
		int n = test.halfDuplicateNumber(A, A.length);

		System.out.println("数组中超过一半出现的元素：" + n);
	}

	public int halfDuplicateNumber(int[] a, int n) {
		int candidate = a[0];
		int nTimes = 1;

		for (int i = 1; i < n; i++) {
			if (nTimes == 0) {
				candidate = a[i];
				nTimes = 1;
			} else {

				if (candidate == a[i]) {
					nTimes++;
				} else {
					nTimes--;
				}
			}
		}
		System.out.println("数组中超过一半出现的元素 index：" + nTimes);
		return candidate;
	}

}
