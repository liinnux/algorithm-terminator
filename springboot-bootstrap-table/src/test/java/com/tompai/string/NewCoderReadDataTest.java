/**
 * 
 */
package com.tompai.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * @author Administrator
 *
 */
public class NewCoderReadDataTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	//翻转数组
	public static void test(String[] args) {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		String str = null;
		try {
			while (true) {
				str = bf.readLine();
				if (str == null || str.length() == 0) {
					break;
				}
				StringBuffer sb = new StringBuffer();
				sb.append("{");
				for (int i = str.length() - 2; i >= 1; i--) {
					sb.append(str.charAt(i));
				}
				sb.append("}");
				System.out.println(sb.toString());
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public static void test0(String[] args) {
		Scanner sc = new Scanner(System.in);
		while (sc.hasNext()) { // 注意，若是下面是nextInt，则这里必须是hasNext
			int num = sc.nextInt();
			///......
		}
	}

	//输入已知大小的二维数组
	public static void test1(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();//定义需要的阶层数n
		int[][] array = new int[n][n];//定义一个n*n的数组array
		System.out.println("输入数组的各个元素：");
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++)
				array[i][j] = scan.nextInt();
		}
	}

	//每组测试数据有多行
	public static void test2(String[] args) {
		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		while (T > 0) {
			T--;//为了保证输出的第一行会换行
			//a和b分别放每组测试用例的第二行和第三行
			int[] a = new int[10000];
			int[] b = new int[10000];
			int n = in.nextInt();
			for (int i = 1; i <= n; i++) {
				a[i] = in.nextInt();
			}
			for (int i = 1; i <= n; i++) {
				b[i] = in.nextInt();
			}
		} //将输入带到function()方法里去运算
			//System.out.println(function(a,b));
	}

	//每行测试数据的数据量在开头给出
	public static void test3(String[] args) {
		Scanner input = new Scanner(System.in);
		int T = input.nextInt();
		for (int i = 0; i < T; i++) {
			int N = input.nextInt();
			String s = input.next();//注意用的是next()，不换行
			char[] ch = new char[10000];
			for (int j = 0; j < N; j++) {
				ch[j] = s.charAt(j);
			}
			//System.out.println(function(ch));
		}
	}

	//测试数据位置且结果取模
	public static void test4(String[] args) {
		Scanner input = new Scanner(System.in);
		Long mod = 1000000007l;
		while (input.hasNext()) {
			Long n = input.nextLong();
			Long l = input.nextLong();
			if (n == 0 && l == 0) {
				break;
			} //将输入带到function()方法里去运算
				//System.out.println(function(n,l)%mod);
		}
	}

	//判断子串是否主串的某段
	public static void test5(String[] args) {
		//输入主串和子串
		Scanner input = new Scanner(System.in);
		String mainString = input.nextLine();
		String subString = input.nextLine();
		//判断方法调用
		boolean result = jugdeExist(mainString, subString);
		System.out.println(result);

	}

	//判断子串是否与主串的某段相等
	public static boolean jugdeExist(String mainString, String subString) {
		if (null == mainString)
			return false;
		String[] forArray = mainString.split(",");
		for (String Id : forArray) {
			if (Id.equals(subString))
				return true;
		}
		return false;
	}

}
