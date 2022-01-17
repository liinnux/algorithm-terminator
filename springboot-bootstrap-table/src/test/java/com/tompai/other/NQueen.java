/**
 * 
 */
package com.tompai.other;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
N 皇后问题是指在 n * n 的棋盘上要摆 n 个皇后，
要求：任何两个皇后不同行，不同列也不在同一条斜线上，
求给一个整数 n ，返回 n 皇后的摆法数。
位运算
具体做法：
1.N个位置对应N个二进制位，0代表无皇后，1代表有皇后
2.例如对于col = 0100对应第二列已有皇后，那么下一行的第一列和第三列都不能选
对应pos = 0010，也就是col右移一位；对应neg = 1000，也就是col左移一位
pre = ~ (col | pos | neg) & ((1 << n) - 1) 代表可以放皇后的位置
~ (col | pos | neg)：col、pos、neg取或运算后0表示可以放皇后的位置，取反后1表示可以放皇后的位置
((1 << n) - 1) ：是为了保证pre不大于n位
3.然后对pre中所有的1进行遍历，从最后一个1开始往前遍历，在当前行放置了一个皇后之后进入下一行，对col、pos、neg做出相应的处理，其余操作与上一个方法相同
 */
public class NQueen {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	int res;
    public int Nqueen (int n) {
        // write code here
        backtrack(0, 0, 0, 0, n);
        return res;
    }
    public void backtrack(int i, int col, int pos, int neg, int n){
        if(i == n){
            res++;
            return;
        }
         //标记放皇后的位置
        int pre = ~(col | pos | neg) & ((1 << n) - 1);
        //遍历pre
        while(pre > 0){
            int cur = pre & (-pre);
            //当前行放置了一个皇后之后进入下一行
            backtrack(i + 1, col | cur, (pos | cur) >> 1, (neg | cur) << 1, n);
            pre &= pre - 1;
        }
    }
    
    //打印出NQueen问题的所有的解的集合
    public List<List<String>> solveNQueens(int n) {
        char[][] chess = new char[n][n];
        //初始化数组
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                chess[i][j] = '.';
        List<List<String>> res = new ArrayList<>();
        solve(res, chess, 0);
        return res;
    }
     
    private void solve(List<List<String>> res, char[][] chess, int row) {
        //终止条件，最后一行都走完了，说明找到了一组，把它加入到集合res中
        if (row == chess.length) {
            res.add(construct(chess));
            return;
        }
        //遍历每一行
        for (int col = 0; col < chess.length; col++) {
            //判断这个位置是否可以放皇后
            if (valid(chess, row, col)) {
                //数组复制一份
                char[][] temp = copy(chess);
                //在当前位置放个皇后
                temp[row][col] = 'Q';
                //递归到下一行继续
                solve(res, temp, row + 1);
            }
        }
    }
     
    //把二维数组chess中的数据测下copy一份
    private char[][] copy(char[][] chess) {
        char[][] temp = new char[chess.length][chess[0].length];
        for (int i = 0; i < chess.length; i++) {
            for (int j = 0; j < chess[0].length; j++) {
                temp[i][j] = chess[i][j];
            }
        }
        return temp;
    }
     
    //row表示第几行，col表示第几列
    private boolean valid(char[][] chess, int row, int col) {
        //判断当前列有没有皇后,因为他是一行一行往下走的，
        //我们只需要检查走过的行数即可，通俗一点就是判断当前
        //坐标位置的上面有没有皇后
        for (int i = 0; i < row; i++) {
            if (chess[i][col] == 'Q') {
                return false;
            }
        }
        //判断当前坐标的右上角有没有皇后
        for (int i = row - 1, j = col + 1; i >= 0 && j < chess.length; i--, j++) {
            if (chess[i][j] == 'Q') {
                return false;
            }
        }
        //判断当前坐标的左上角有没有皇后
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (chess[i][j] == 'Q') {
                return false;
            }
        }
        return true;
    }
     
    //把数组转为list
    private List<String> construct(char[][] chess) {
        List<String> path = new ArrayList<>();
        for (int i = 0; i < chess.length; i++) {
            path.add(new String(chess[i]));
        }
        return path;
    }
}
