package com.tompai.t1224;

import java.util.Scanner;

public class Demo3_ShuDu {

	/*
	0 6 0 5 9 3 0 0 0
	9 0 1 0 0 0 5 0 0
	0 3 0 4 0 0 0 9 0
	1 0 8 0 2 0 0 0 4
	4 0 0 3 0 9 0 0 1
	2 0 0 0 1 0 6 0 9
	0 8 0 0 0 6 0 2 0
	0 0 4 0 0 0 8 0 7
	0 0 0 7 8 5 0 1 0
	
	0 0 0 1 7 0 0 0 5
	0 0 3 5 0 0 9 0 7
	0 6 0 0 3 0 0 0 0
	0 9 0 2 8 0 6 0 0
	0 0 8 0 0 0 1 0 0
	0 4 0 0 0 0 0 2 0
	0 0 0 8 0 0 2 0 0
	8 7 0 0 9 0 0 4 0
	6 0 0 3 5 0 0 0 0
	
	 */

	/*
	7 6 2 5 9 3 1 4 8
	9 4 1 2 7 8 5 3 6
	8 3 5 4 6 1 7 9 2
	1 9 8 6 2 7 3 5 4
	4 7 6 3 5 9 2 8 1
	2 5 3 8 1 4 6 7 9
	3 8 7 1 4 6 9 2 5
	5 1 4 9 3 2 8 6 7
	6 2 9 7 8 5 4 1 3
	
9 8 2 1 7 6 4 3 5
4 1 3 5 2 8 9 6 7
5 6 7 4 3 9 8 1 2
3 9 1 2 8 5 6 7 4
2 5 8 7 6 4 1 9 3
7 4 6 9 1 3 5 2 8
1 3 9 8 4 7 2 5 6
8 7 5 6 9 2 3 4 1
6 2 4 3 5 1 7 8 9
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int rank=9;
		while (sc.hasNextInt()) {
			int[][] a = new int[rank][rank];
			boolean[][] cols = new boolean[rank][rank];
			boolean[][] rows = new boolean[rank][rank];
			boolean[][] blocks = new boolean[rank][rank];//九大宫的九个数字  

			for (int i = 0; i < a.length; i++) {
				for (int j = 0; j < a.length; j++) {
					a[i][j] = sc.nextInt();
					if (a[i][j] != 0) {
						int k = i / 3 * 3 + j / 3;//划分九宫格,这里以行优先，自己也可以列优先  
						int val = a[i][j] - 1;
						rows[i][val] = true;
						cols[j][val] = true;
						blocks[k][val] = true;
					}
				}
			} //数据装载完毕     
			DFS(a, cols, rows, blocks);
			for (int i = 0; i < rank; i++) {
				for (int j = 0; j < rank-1; j++) {
					System.out.print(a[i][j] + " ");
				}
				System.out.println(a[i][rank-1]);
			}
		}
	}

	public static boolean DFS(int[][] a, boolean[][] cols, boolean[][] rows, boolean[][] blocks) {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (a[i][j] == 0) {
					int k = i / 3 * 3 + j / 3;
					for (int l = 0; l < 9; l++) {
						if (!cols[j][l] && !rows[i][l] && !blocks[k][l]) {//l对于的数字l+1没有在行列块中出现  
							rows[i][l] = cols[j][l] = blocks[k][l] = true;
							a[i][j] = 1 + l;//下标加1  
							if (DFS(a, cols, rows, blocks))
								return true;//递进则返回true  
							rows[i][l] = cols[j][l] = blocks[k][l] = false;//递进失败则回溯  
							a[i][j] = 0;
						}
					}
					return false;//a[i][j]==0时，l发现都不能填进去  
				} //the end of a[i][j]==0  
			}
		}
		return true;//没有a[i][j]==0,则返回true  
	}
}