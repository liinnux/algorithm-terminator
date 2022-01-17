package com.tompai.t1224;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

/*

n		a(n)
0		1
1		1
2		0
3		0
4		2
5		10
6		4
7		40
8		92
9		352
10		724
11		2680
12		14200
13		73712
14		365596
15		2279184
16		14772512
17		95815104
18		666090624
19		4968057848
20		39029188884
21		314666222712
22		2691008701644
23		24233937684440
24		227514171973736
25		2207893435808352
26		22317699616364044
27		234907967154122528
 */
public class Demo9_NQueen_0 {
	static char qQueue='Q';//表示有皇后的位置
	static char nQueue='N';//表示没有皇后的位置
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		while(sc.hasNextInt()) {
			int n=sc.nextInt();
			if (n>500 || n< 1) {
				return;
			}
			
			//List<List<String>> results=solveNQueens(n);
			List<List<String>> res = solveNQueens(n);
			int index=0;
			for (List<String> list : res) {
				for (String s : list) {
					System.out.print(s);
					System.out.println();
				}
				index++;
				System.out.println(index+"------------");
			}
		}
		sc.close();
	}

	
	private static List<List<String>> solveNQueens(int n) {
        char[][] chs=new char[n][n];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                chs[i][j]=nQueue;
            }
        }
        List<List<String>> res=new ArrayList<>();
        backTracing(chs,0,n,res);
        return res;
    } 
	private static void  backTracing(char[][] chs,int row,int n,List<List<String>> res){
    	//每行都摆满皇后时，则产生了一种解法
        if(row==n){
            res.add(chsToList(chs));
            return;
        }
        //一行一行地摆放，在确定一行中的那个皇后应该摆在哪一列时，需要当前列是否合法。
        //如果合法，则将皇后放置在当前位置，并进行递归，回溯。
        for(int col=0;col<chs[0].length;col++){
            if(isValid(chs,row,col)){
                chs[row][col]=qQueue;
                //递归
                backTracing(chs,row+1,n,res);
                //回溯
                chs[row][col]=nQueue;
            }
        }
    }
	private static List<String> chsToList(char[][] chs){
        List<String> list=new ArrayList<>();
        for(int i=0;i<chs.length;i++){
            list.add(new String(chs[i]));
        }
        return list;
    }
    //判断合法：当前将要摆放'Q'的位置和其他已摆放‘Q’的位置不能在同一列，且不能在同一条45度斜线或135度斜线上。
    //这里判断是否在同一条斜线上可通过当前将要摆放'Q'的位置和其他已摆放‘Q’的位置横坐标之差和纵坐标之差的绝对值是否相等来判断。
	private static boolean isValid(char[][] chs,int x,int y){
        for(int i=0;i<=x;i++){
            for(int j=0;j<chs[0].length;j++){
                if(chs[i][j]=='Q'&&(j==y||Math.abs(x-i)==Math.abs(y-j))){
                    return false;
                }
            }
        }
        return true;
    }
	
}



