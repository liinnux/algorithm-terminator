/**
 * 
 */
package com.tompai.t1226;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Administrator
 */
public class Demo8_OddEveArray {

	public static void main(String[] args) {
		Demo8_OddEveArray test = new Demo8_OddEveArray();
		int[] A = { 2, 1, 4, 7, 1, 4, 7, 1, 2, 8, 4, 3, 6, 7, 2, 14, 3, 7, 4, 3, 2, 4, 3, 2, 7 };
		test.getOddEvenSort1(A);
		System.out.println("使用方法1：一头一尾指针往中间扫描结果：");
		for (int i = 0; i < A.length; i++)
			System.out.print(A[i] + " ");
	}

	//解法1：一头一尾指针往中间扫描
	public void getOddEvenSort1(int[] A) {
		if (A.length == 1)
			return;
		int begin = 0;
		int end = A.length - 1;
		while (begin < end) {
			if (A[begin] % 2 == 1) //当A[begin]为奇数时
				begin++;
			else if (A[end] % 2 == 0) //当A[end] 为偶数时
				end--;
			else //当A[begin]不是奇数且A[end]不是偶数时
				swap(A, begin, end);
		}
	}
	 //解法2：一前一后两指针往后扫描
    public void getOddEvenSort2(int[] A){
        if(A.length == 1)
            return;
        int origin = 0;   //定义标准元素位置，最终结果是在该元素值的左边都是奇数，在该元素值的右边都是偶数
        int i = 0;
        for(int j = 1;j < A.length;j++){
            if(A[j] % 2 == 1){   //当A[j]为奇数时，右移一位，并交换A[i]和A[j]值，表明在i的左边均为奇数
                i++;        
                swap(A,i,j);
            }
        }
        swap(A,i,origin);
    }
    
	//交换数组A的m位置和n位置上的值
	public void swap(int[] A, int m, int n) {
		int temp = A[m];
		A[m] = A[n];
		A[n] = temp;
	}

}
