package com.tompai.t1224;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Demo5_ErrorRecord {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		List<String> names = new ArrayList<>();
		List<Integer> sum = new ArrayList<>();
		while (in.hasNext()) {
			String[] strs = (in.next()).split("\\\\");
			String name = strs[strs.length - 1];
			name = name + " " + in.next();
			int index = names.indexOf(name);
			if (index != -1) {//存在
				sum.set(index, sum.get(index) + 1);
			} else {
				names.add(name);
				sum.add(1);
			}
		}
		String[] namestmp = new String[names.size()];
		names.toArray(namestmp);
		Integer[] sumtmp = new Integer[sum.size()];
		sum.toArray(sumtmp);
		//排序
		for (int i = 0; i < namestmp.length; i++) {
			int maxIndex = i;
			for (int j = i + 1; j < namestmp.length; j++) {
				if (sumtmp[j] > sumtmp[maxIndex]) {
					maxIndex = j;
				}
			}
			String name1 = namestmp[maxIndex];
			System.arraycopy(namestmp, i, namestmp, i + 1, maxIndex - i);
			namestmp[i] = name1;
			Integer sum1 = sumtmp[maxIndex];
			System.arraycopy(sumtmp, i, sumtmp, i + 1, maxIndex - i);
			sumtmp[i] = sum1;
		}
		//输出
		for (int i = 0; i < Math.min(namestmp.length, 8); i++) {
			String name = namestmp[i];
			String a = name.split(" ")[0];
			String b = name.split(" ")[1];
			if (a.length() > 16) {
				name = a.substring(a.length() - 16) + " " + b;
			}
			System.out.println(name + " " + sumtmp[i]);
		}
	}
}
