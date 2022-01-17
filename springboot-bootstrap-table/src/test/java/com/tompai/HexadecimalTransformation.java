/**
 * 
 */
package com.tompai;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * @author Administrator
 *
 */
public class HexadecimalTransformation {
	public static void main(String[] args)throws Exception{
		//1读取数据的数值和进制
		InputStreamReader irs = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(irs);
		String mString = br.readLine();
		String nString = br.readLine();
		int m = Integer.parseInt(mString);
		int n = Integer.parseInt(nString);
		
		int i = 0;
		char c = 'k';
		ArrayList<String> result = new ArrayList<String>();
		
		//2循环换算，提供了2到62进制
		while(m != 0){
			i = m % n;
			if(i<10){
				result.add(i+"");
			}
			if(i>=10 && i<=35){
				c = (char)( (i-10) + 65);
				result.add(c+"");
			}
			if(i>=36 && i<=61){
				c = (char)((i-36) + 97);
				result.add(c+"");
			}
			m = m/n;
		}
		//3逆循环输出余数
		for(int j =result.size()-1;j>=0;j--){
			System.out.print(result.get(j));
		}
	}
}
