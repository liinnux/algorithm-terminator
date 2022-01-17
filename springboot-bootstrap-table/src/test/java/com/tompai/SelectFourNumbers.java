package com.tompai;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class SelectFourNumbers {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int N = 5000, i = 0, m = 4, M = 500;
		Random random = new Random(System.currentTimeMillis());
		List<Integer> list = new ArrayList<>();
		int[] num1 = new int[N];
		int[] num2 = new int[N*N];
		do {
			int t = random.nextInt(100);
			t = Math.abs(t);
			num1[i] = t;
			if (!list.contains(t)) {
				i++;
			}
		} while (i < N);

		i=0;
		for(int j=0;j<N;j++) {
			for(int k=0;k<N;k++) {
				num2[i++]=num1[j]+num1[k];
			}
		}
		i=0;
		/*for(int k=0;k<N;k++) {
			System.out.print(num1[k]+"-");
		}*/
		//1 use time:13685 ms
		//2 use time:14 ms
		//3 use time:4 ms
		boolean flag=false;
		//Arrays.sort(num1);
		Arrays.sort(num2);
		long t1=System.currentTimeMillis();
		for (int a = 0; a < N; a++) {
			if (flag) {
				break;
			}
			System.out.println(a+"-");
			for (int b = 0; b < N; b++) {
				int tmp=M-(num1[a] + num1[b]);
				if (check(num2, tmp)) {
					System.out.println(true);
					flag=true;
					break;
				}
			}
		}
		
		long t=System.currentTimeMillis()-t1;
		System.out.println("use time:"+t+" ms");
	}

	
	public static boolean check(int[] num,int k) {
		
		int p=0;
		p=Arrays.binarySearch(num, k);
		if (p>=0) {
			return true;
		}else {
			return false;
		}
	}
}
