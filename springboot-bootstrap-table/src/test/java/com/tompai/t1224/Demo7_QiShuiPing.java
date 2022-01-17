package com.tompai.t1224;

import java.util.Scanner;

public class Demo7_QiShuiPing {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while(sc.hasNext()) {
			int n=sc.nextInt();
			if (n>100 || n< 1) {
				return;
			}
			int m=0;
			int a=0;
			int b=0;
			int c=0;
			do {
				a=n/3;
				b=n%3;
				m+=a;
				c=a+b;
				if (c==2) {
					m+=1;
				}
				n=c;
			} while (n>=3);
			System.out.println(m);
		}
		sc.close();
	}

	
}
