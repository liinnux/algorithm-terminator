package com.tompai.t1224;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/*
简单错误记录
开发一个简单错误记录功能小模块，能够记录出错的代码所在的文件名称和行号。
处理:
1.记录最多8条错误记录，对相同的错误记录(即文件名称和行号完全匹配)只记录一条，
错误计数增加；(文件所在的目录不同，文件名和行号相同也要合并)
2.超过16个字符的文件名称，只记录文件的最后有效16个字符；
(如果文件名不同，而只是文件名的后16个字符和行号相同，也不要合并)
3.输入的文件可能带路径，记录文件名称不能带路径
 */
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
