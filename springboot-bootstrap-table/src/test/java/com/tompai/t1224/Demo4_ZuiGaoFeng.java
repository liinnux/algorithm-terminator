package com.tompai.t1224;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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