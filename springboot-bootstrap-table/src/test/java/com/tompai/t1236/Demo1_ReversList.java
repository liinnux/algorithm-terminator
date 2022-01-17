/**
 * 
 */
package com.tompai.t1236;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Administrator
 *
 */
public class Demo1_ReversList {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		 BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	        String str = null;
	        while(true) {
	            str = bf.readLine();
	            if(str==null||str.length()==0) {
	                break;
	            }
	            StringBuilder sb=new StringBuilder();
	            sb.append("{");
	            for(int i = str.length()-2; i>=1; i-- ){
	                sb.append(str.charAt(i));
	            }
	            sb.append("}");
	            System.out.println(sb.toString());
	        }
	}

}
