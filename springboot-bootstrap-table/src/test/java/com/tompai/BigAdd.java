package com.tompai;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Scanner;

public class BigAdd {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//"1","99"
		try (Scanner scanner = new Scanner(System.in)) {
			while (scanner.hasNextLine()) {
				String lines = scanner.nextLine();
				if (lines.contains(",")) {
					if (lines.length()>100000) {
						continue;
					}
					String[] number = lines.split(",");
					String a = judgeNumber(number[0]);
					String b = judgeNumber(number[1]);
					if ((a != null) && (b != null)) {
						BigDecimal numA = new BigDecimal(a);
						BigDecimal numB = new BigDecimal(b);
						BigDecimal numC = numA.add(numB);
						String c="\""+numC.toPlainString()+"\"";
						System.out.println(c);
						//numA.toPlainString()
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.print(e.getMessage());
		}
	}

	public static String judgeNumber(String value) {
		if (value != null) {
			if (value.contains("\"")) {
				value = value.replaceAll("\"", "");
			}
			//System.out.println(value);
			
			char[] nums = value.toCharArray();
			
			int dotnum=0;
			int othernum=0;
			for (char c : nums) {
				if (!Character.isDigit(c)) {
					if ('.'==c) {
						dotnum++;
					}else {
						othernum++;
					}
				}
			}
			
			if(othernum>0) {
				return null;
			}else {
				if (dotnum>1) {
					return null;
				}else {
					return value;
				}
			}
		}
		return null;
	}
}
