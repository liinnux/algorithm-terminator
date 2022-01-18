package com.tompai.t1224;

import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

/*
 * 明明的随机数
明明想在学校中请一些同学一起做一项问卷调查，
为了实验的客观性，他先用计算机生成了 N 个 1 到 1000 之间的随机整数（ N≤1000 ），
对于其中重复的数字，只保留一个，把其余相同的数去掉，不同的数对应着不同的学生的学号。
然后再把这些数从小到大排序，按照排好的顺序去找同学做调查。请你协助明明完成“去重”与“排序”的工作
(同一个测试用例里可能会有多组数据(用于不同的调查)，希望大家能正确处理)。

注：测试用例保证输入参数的正确性，答题者无需验证。测试用例不止一组。

当没有新的输入时，说明输入结束。
 */
public class Demo8_MingRandomNumber {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while(sc.hasNextInt()) {
			int n=sc.nextInt();
			if (n>500 || n< 1) {
				return;
			}
			int[] aars=new int[n];
			for(int i=0;i<n;i++) {
				aars[i]=sc.nextInt();
			}
			quchong(aars);
		}
		sc.close();
	}

	
	private static void quchong(int[] aars) {
		Set<Integer> sets=new TreeSet<>();
		for(int i=0;i<aars.length;i++) {
			sets.add(aars[i]);
		}
		for (Integer ins : sets) {
			System.out.println(ins);
		}
	}
	
}
