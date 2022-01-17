package com.tompai.t1224;

import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

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
