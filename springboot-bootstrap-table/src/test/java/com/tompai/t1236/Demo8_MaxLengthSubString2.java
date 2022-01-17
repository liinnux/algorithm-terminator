package com.tompai.t1236;

public class Demo8_MaxLengthSubString2<K, V> {

	/*
	求两字符序列的最长公共字符子序列
	
	问题描述：字符序列的子序列是指从给定字符序列中随意地（不一定连续）去掉若干个字符（可能一个也不去掉）后所形成的字符序列。
	令给定的字符序列X=“x0，x1，…，xm-1”，序列Y=“y0，y1，…，yk-1”是X的子序列，
	存在X的一个严格递增下标序列<i0，i1，…，ik-1>，使得对所有的j=0，1，…，k-1，
	有xij=yj。例如，X=“ABCBDAB”，Y=“BCDB”是X的一个子序列。
	 */

	//"1AB2345CD","12345EF"
	//"binghaven","jingseven"

	public static void main(String[] args) {
		//保留空字符串是为了getLength()方法的完整性也可以不保留  
		//但是在getLength()方法里面必须额外的初始化c[][]第一个行第一列  
		String[] x = { "", "A", "B", "C", "B", "D", "A", "B" };
		String[] y = { "", "B", "D", "C", "A", "B", "A" };

		int[][] b = getLength(x, y);

		Display(b, x, x.length - 1, y.length - 1);
	}

	/**
	 * @param x
	 * @param y
	 * @return 返回一个记录决定搜索的方向的数组
	 */
	public static int[][] getLength(String[] x, String[] y) {
		int[][] b = new int[x.length][y.length];
		int[][] c = new int[x.length][y.length];

		for (int i = 1; i < x.length; i++) {
			for (int j = 1; j < y.length; j++) {
				//对应第一个性质  
				if (x[i] == y[j]) {
					c[i][j] = c[i - 1][j - 1] + 1;
					b[i][j] = 1;
				}
				//对应第二或者第三个性质  
				else if (c[i - 1][j] >= c[i][j - 1]) {
					c[i][j] = c[i - 1][j];
					b[i][j] = 0;
				}
				//对应第二或者第三个性质  
				else {
					c[i][j] = c[i][j - 1];
					b[i][j] = -1;
				}
			}
		}

		return b;
	}

	//回溯的基本实现，采取递归的方式  
	public static void Display(int[][] b, String[] x, int i, int j) {
		if (i == 0 || j == 0)
			return;

		if (b[i][j] == 1) {
			Display(b, x, i - 1, j - 1);
			System.out.print(x[i] + " ");
		} else if (b[i][j] == 0) {
			Display(b, x, i - 1, j);
		} else if (b[i][j] == -1) {
			Display(b, x, i, j - 1);
		}
	}

}
