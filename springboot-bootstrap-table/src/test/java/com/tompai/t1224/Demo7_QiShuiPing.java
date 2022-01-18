package com.tompai.t1224;

import java.util.Scanner;
/*
 * 汽水瓶
 * 有这样一道智力题：“某商店规定：三个空汽水瓶可以换一瓶汽水。
 * 小张手上有十个空汽水瓶，她最多可以换多少瓶汽水喝？”答案是 5 瓶，
 * 方法如下：先用 9 个空瓶子换3瓶汽水，喝掉 3 瓶满的，喝完以后 4 个空瓶子，
 * 用 3 个再换一瓶，喝掉这瓶满的，这时候剩 2 个空瓶子。
 * 然后你让老板先借给你一瓶汽水，喝掉这瓶满的，
 * 喝完以后用 3 个空瓶子换一瓶满的还给老板。
 * 如果小张手上有 n 个空汽水瓶，最多可以换多少瓶汽水喝？
 */
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
