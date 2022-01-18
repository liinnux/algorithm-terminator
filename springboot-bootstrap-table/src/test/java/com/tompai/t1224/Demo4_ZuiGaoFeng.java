package com.tompai.t1224;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
最高分是多少
老师想知道从某某同学当中，分数最高的是多少，现在请你编程模拟老师的询问。当然，老师有时候需要更新某位同学的成绩.
每组输入第一行是两个正整数N和M（0 < N <= 30000,0 < M < 5000）,分别代表学生的数目和操作的数目。
学生ID编号从1编到N。
第二行包含N个整数，代表这N个学生的初始成绩，其中第i个数代表ID为i的学生的成绩
接下来又M行，每一行有一个字符C（只取‘Q’或‘U’），和两个正整数A,B,当C为'Q'的时候, 
表示这是一条询问操作，假设A<B，他询问ID从A到B（包括A,B）的学生当中，成绩最高的是多少
当C为‘U’的时候，表示这是一条更新操作，要求把ID为A的学生的成绩更改为B。

注意：输入包括多组测试数据。
 */
public class Demo4_ZuiGaoFeng {
	public static void main(String[] args) {
		Scanner sc1 = new Scanner(System.in);
		do {
			int[] num = new int[2];
			num[0] = sc1.nextInt();
			num[1] = sc1.nextInt();
			List<Integer> list = new ArrayList<Integer>();
			List<Integer> results = new ArrayList<Integer>();
			for (int i = 0; i < num[0]; i++) {
				list.add(sc1.nextInt());
			}
			char a;
			int b, c;
			for (int j = 0; j < num[1]; j++) {
				a = sc1.next().charAt(0);
				b = sc1.nextInt();
				c = sc1.nextInt();
				if ('Q' == a) {
					if (b >= c) {//交换
						int t = c;
						c = b;
						b = t;
					}
					//b-c中选择最大的数
					int max = list.get(b - 1);
					for (int m = b; m < c; m++) {
						if (max < list.get(m)) {
							max = list.get(m);
						}
					}
					results.add(max);//end
				}
				if ('U' == a) {
					list.set(b - 1, c);
				}
			}
			//输出
			for (int n = 0; n < results.size(); n++) {
				System.out.println(results.get(n));
			}
		} while (sc1.hasNext());
		sc1.close();
	}
}