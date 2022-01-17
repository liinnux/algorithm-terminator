/**
 * 
 */
package com.tompai;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Administrator
 *
 */
public class TwoSum {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner scan = new Scanner(System.in);
		while (scan.hasNext()) {
			String line = scan.nextLine();
			//[-3,4,3,90],0
			//[2,3]

			line = line.trim();
			//String[] numbers = line.split("\\D");
			String[] numbers = line.split(",");
			if (numbers.length <= 2) {
				System.out.print("[]");
				continue;
			} else {
				List<Integer> lists = new ArrayList<>();
				for (String s : numbers) {
					if (s.length() > 0) {
						//System.out.println(s);
						s=fixNumber(s);
						//System.out.println(s);
						lists.add(Integer.parseInt(s));
					}
				}
				int size=lists.size();
				int sum=lists.get(size-1);
				//System.out.println("sum="+sum);
				int m=0,n=0;
				for(int i=0;i<size-1;i++) {
					int t=sum-lists.get(i);
					//System.out.println("t="+t);
					if (lists.contains(t)) {
						n=lists.indexOf(t);
						if(n!=i) {
							m=i;
							break;
						}
					}
				}
				m++;
				n++;
				if(m>n) {
					n = (m + n) - (m = n); 
				}
				System.out.println("[" + m + "," + n + "]");
			}
		}

		scan.close();
	}

	private static String fixNumber(String s) {
		if(s.contains("[")) {
			s=s.replace("[", "");
		}
		
		if(s.contains("]")) {
			s=s.replace("]", "");
		}
		return s;
	}
}
