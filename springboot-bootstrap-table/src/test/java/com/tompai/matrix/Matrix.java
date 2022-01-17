/**
 * 
 */
package com.tompai.matrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Administrator
 *
 */
public class Matrix {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Matrix testMatrix = new Matrix();
		int[] a = { 1, 2, 3, 4, 5, 6, 7 };
		/*for(int i:testMatrix.rotateArray(7,3, a)) {
			System.out.print(i+" ");
		}*/
		double[] b = { 1, 2, 0, -4, -5, -1, 7 };

		System.out.print(testMatrix.maxProduct(b));

	}

	/*
	给定一个m x n大小的矩阵（m行，n列），按螺旋的顺序返回矩阵中的所有元素。
	
	数据范围：0 \le n,m \le 100≤n,m≤10，矩阵中任意元素都满足 |val| \le 100∣val∣≤100
	要求：空间复杂度 O(nm)O(nm) ，时间复杂度 O(nm)O(nm)
	示例1
	输入：[[1,2,3],[4,5,6],[7,8,9]]
	
	返回值：[1,2,3,6,9,8,7,4,5]
	 */
	public ArrayList<Integer> spiralOrder(int[][] matrix) {
		ArrayList<Integer> res = new ArrayList<>();
		if (matrix.length == 0)
			return res;
		// 定义四个指针，并且充当边界限制的作用
		int top = 0, bottom = matrix.length - 1;
		int left = 0, right = matrix[0].length - 1;

		while (top < (matrix.length + 1) / 2 && left < (matrix[0].length + 1) / 2) {
			//上面  左到右
			for (int i = left; i <= right; i++) {
				res.add(matrix[top][i]);
			}

			//右边 上到下
			for (int i = top + 1; i <= bottom; i++) {
				res.add(matrix[i][right]);
			}

			//下面  右到左
			for (int i = right - 1; top != bottom && i >= left; i--) {
				res.add(matrix[bottom][i]);
			}

			//左边 下到上
			for (int i = bottom - 1; left != right && i >= top + 1; i--) {
				res.add(matrix[i][left]);
			}
			// 遍历完一圈之后，所有往里面靠
			++top;
			--bottom;
			++left;
			--right;
		}
		return res;
	}

	/*NC210 螺旋矩阵(二)
	给定一个正整数 n ，生成一个包含 1 到 n*n 所有元素的矩阵，且元素按顺时针方向螺旋排列成一个正方形。
	例如：
	n = 3 时，矩阵是：
	[[1,2,3]
	[8,9,4]
	[7,6,5]]*/
	public int[][] spiralOrderMatrix01(int n) {
		// write code here
		int maxNum = n * n;
		int curNum = 1;
		int[][] matrix = new int[n][n];
		int row = 0, column = 0;
		// 定义方向
		int[][] directions = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } }; // 右下左上
		int directionIndex = 0;
		while (curNum <= maxNum) {
			matrix[row][column] = curNum;
			curNum++;
			int nextRow = row + directions[directionIndex][0], nextColumn = column + directions[directionIndex][1];
			if (nextRow < 0 || nextRow >= n || nextColumn < 0 || nextColumn >= n || matrix[nextRow][nextColumn] != 0) {
				directionIndex = (directionIndex + 1) % 4; // 顺时针旋转至下一个方向
			}
			row = row + directions[directionIndex][0];
			column = column + directions[directionIndex][1];
		}
		return matrix;
	}

	public int[][] spiralOrderMatrix(int n) {
		int[][] matrix = new int[n][n];
		int number = 1;
		int left = 0;
		int right = n - 1;
		int top = 0;
		int bottom = n - 1;
		while (left <= right && top <= bottom) {
			for (int i = left; i <= right; i++) {
				matrix[top][i] = number++;
			}
			top++;
			for (int i = top; i <= bottom; i++) {
				matrix[i][right] = number++;
			}
			right--;
			if (bottom >= top) {
				for (int i = right; i >= left; i--) {
					matrix[bottom][i] = number++;
				}
			}
			bottom--;
			if (right >= left) {
				for (int i = bottom; i >= top; i--) {
					matrix[i][left] = number++;
				}
			}
			left++;
		}
		return matrix;
	}

	/*NC201 对角线遍历矩阵
	给定一个大小为 n*m 的矩阵，请以对角线遍历并返回遍历结果*/
	public int[] diagonalOrder (int[][] mat) {
        // write code here
        int row = mat.length;
        int col = mat[0].length;
        int[] res = new int[row*col];
        int k=0,i=0,j=0;
        while(k<res.length){
            res[k++] = mat[i][j];
            if((i+j)%2==0){
                if(j==col-1){
                    i++;
                }else if(i==0){
                    j++;
                }else{
                    i--;
                    j++;
                }
            }else{
                if(i == row-1){
                    j++;
                }else if(j == 0){
                    i++;
                }else{
                    i++;
                    j--;
                }
            }
        }
        return res;
    }

	/**
	 * 给一个01矩阵，1代表是陆地，0代表海洋， 如果两个1相邻，那么这两个1属于同一个岛。我们只考虑上下左右为相邻。 岛屿:
	 * 相邻陆地可以组成一个岛屿（相邻:上下左右） 判断岛屿个数。 例如： 输入 [ [1,1,0,0,0], [0,1,0,1,1], [0,0,0,1,1],
	 * [0,0,0,0,0], [0,0,1,1,1] ] 对应的输出为3
	 */

	/**
	 * 判断岛屿数量
	 * 
	 * @param grid char字符型二维数组
	 * @return int整型
	 */
	public int islandOfNumberDfs(char[][] grid) {
		// write code here
		if (grid == null || grid.length == 0)
			return 0;
		int row = grid.length;
		int col = grid[0].length;
		int islandNum = 0;
		for (int r = 0; r < row; r++) {
			for (int c = 0; c < col; c++) {
				if (grid[r][c] == '1') {
					islandNum++;
					dfs(grid, r, c);
				}
			}
		}
		return islandNum;
	}

	private void dfs(char[][] grid, int r, int c) {
		int row = grid.length;
		int col = grid[0].length;
		if (r < 0 || c < 0 || r >= row || c >= col || grid[r][c] == '0') {
			return;
		}
		grid[r][c] = '0';
		dfs(grid, r - 1, c);
		dfs(grid, r + 1, c);
		dfs(grid, r, c - 1);
		dfs(grid, r, c + 1);
	}

	public int islandOfNumberBfs(char[][] grid) {
		//边界条件判断
		if (grid == null || grid.length == 0)
			return 0;
		//统计岛屿的个数
		int count = 0;
		//两个for循环遍历每一个格子
		for (int i = 0; i < grid.length; i++)
			for (int j = 0; j < grid[0].length; j++) {
				//只有当前格子是1才开始计算
				if (grid[i][j] == '1') {
					//如果当前格子是1，岛屿的数量加1
					count++;
					//然后通过bfs把当前格子的上下左右4
					//个位置为1的都要置为0，因为他们是连着
					//一起的算一个岛屿，
					bfs(grid, i, j);
				}
			}
		return count;
	}

	private void bfs(char[][] grid, int x, int y) {
		//把当前格子先置为0
		grid[x][y] = '0';
		int n = grid.length;
		int m = grid[0].length;
		//使用队列，存储的是格子坐标转化的值
		Queue<Integer> queue = new LinkedList<>();
		//我们知道平面坐标是两位数字，但队列中存储的是一位数字，
		//所以这里是把两位数字转化为一位数字
		int code = x * m + y;
		//坐标转化的值存放到队列中
		queue.add(code);
		while (!queue.isEmpty()) {
			//出队
			code = queue.poll();
			//在反转成坐标值（i，j）
			int i = code / m;
			int j = code % m;
			if (i > 0 && grid[i - 1][j] == '1') {//上
				//如果上边格子为1，把它置为0，然后加入到队列中
				//下面同理
				grid[i - 1][j] = '0';
				queue.add((i - 1) * m + j);
			}
			if (i < n - 1 && grid[i + 1][j] == '1') {//下
				grid[i + 1][j] = '0';
				queue.add((i + 1) * m + j);
			}
			if (j > 0 && grid[i][j - 1] == '1') { //左
				grid[i][j - 1] = '0';
				queue.add(i * m + j - 1);
			}
			if (j < m - 1 && grid[i][j + 1] == '1') {//右
				grid[i][j + 1] = '0';
				queue.add(i * m + j + 1);
			}
		}
	}

	/*
	给定一个 n * m 的矩阵 a，从左上角开始每次只能向右或者向下走，最后到达右下角的位置，路径上所有的数字累加起来就是路径和，输出所有的路径中最小的路径和。
	数据范围: 0 < n,m\le 500<n,m≤50，矩阵中任意值都满足 0 < a_{i,j} \le 1000<a 
	i,j ≤100
	要求：空间复杂度 O(1)O(1)，时间复杂度 O(nm)O(nm)
	 */
	public int minPathSum(int[][] matrix) {
		// write code here
		int row = matrix.length;
		int line = matrix[0].length;
		int[][] distance = new int[row][line];
		distance[0][0] = matrix[0][0];
		for (int i = 1; i < line; i++) {
			distance[0][i] = distance[0][i - 1] + matrix[0][i];
		}
		for (int i = 1; i < row; i++) {
			distance[i][0] = distance[i - 1][0] + matrix[i][0];
			for (int j = 1; j < line; j++) {
				distance[i][j] = matrix[i][j] + Math.min(distance[i - 1][j], distance[i][j - 1]);
			}
		}
		return distance[row - 1][line - 1];
	}

	public int minPathSum2(int[][] matrix) {
		// write code here
		for (int i = matrix[0].length - 2; i >= 0; i--) {
			matrix[matrix.length - 1][i] = matrix[matrix.length - 1][i + 1] + matrix[matrix.length - 1][i];
		}
		for (int i = matrix.length - 2; i >= 0; i--) {
			matrix[i][matrix[0].length - 1] = matrix[i][matrix[0].length - 1] + matrix[i + 1][matrix[0].length - 1];
		}
		for (int i = matrix.length - 2; i >= 0; i--) {
			for (int j = matrix[0].length - 2; j >= 0; j--) {
				matrix[i][j] = Math.min(matrix[i + 1][j], matrix[i][j + 1]) + matrix[i][j];
			}
		}
		return matrix[0][0];
	}

	public int minPathSum3(int[][] matrix) {
		int m = matrix.length, n = matrix[0].length;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (i == 0 && j == 0)
					continue;
				if (i == 0) {
					//第一行只能从左边走过来
					matrix[i][j] += matrix[i][j - 1];
				} else if (j == 0) {
					//第一列只能从上面走下来
					matrix[i][j] += matrix[i - 1][j];
				} else {
					//递推公式，取从上面走下来和从左边走过来的最小值+当前坐标的值
					matrix[i][j] += Math.min(matrix[i - 1][j], matrix[i][j - 1]);
				}
			}
		}
		return matrix[m - 1][n - 1];
	}

	public int minPathSum(int matrix[][], int i, int j) {
		// 如果(i,j)就是右下角的元素
		if (i == matrix.length - 1 && j == matrix[0].length - 1) {
			return matrix[i][j];
		}
		// 如果(i,j)在右边界上，只能向下走
		if (j == matrix[0].length - 1) {
			return matrix[i][j] + minPathSum(matrix, i + 1, j);
		}
		// 如果(i,j)在下边界上，只能向右走
		if (i == matrix.length - 1) {
			return matrix[i][j] + minPathSum(matrix, i, j + 1);
		}
		// 不是上述三种情况，那么(i,j)就有向下和向右两种决策，取决策结果最小的那个
		int left = minPathSum(matrix, i, j + 1);
		int down = minPathSum(matrix, i + 1, j);
		return matrix[i][j] + Math.min(left, down);
	}

	public int minPathSum5(int[][] matrix) {
		if (matrix == null) {
			return 0;
		}
		int rowLen = matrix.length;
		int colLen = matrix[0].length;
		int[] res = new int[colLen + 1];
		Arrays.fill(res, Integer.MAX_VALUE);
		res[1] = 0;
		for (int i = 1; i <= rowLen; i++) {
			for (int j = 1; j <= colLen; j++) {
				//当前点的最小路径和为 : 从左边和上边选择最小的路径和再加上当前点的值
				//res[j]没更新之前就表示i-1行到第j个元素的最小路径和
				//因为是从左往右更新,res[j-1]表示i行第j-1个元素的最小路径和
				res[j] = Math.min(res[j], res[j - 1]) + matrix[i - 1][j - 1];
			}
		}
		return res[colLen];
	}

	/*
	 * NC34 求路径
	一个机器人在m×n大小的地图的左上角（起点）。
	机器人每次可以向下或向右移动。机器人要到达地图的右下角（终点）。
	可以有多少种不同的路径从起点走到终点？
	备注：m和n小于等于100,并保证计算结果在int范围内
	 */
	public int uniquePaths2(int m, int n) {
		// write code here
		int[][] dp = new int[m][n];
		for (int i = 0, j = 0; i < m; i++) {
			dp[i][j] = 1;
		}
		for (int i = 0, j = 0; j < n; j++) {
			dp[i][j] = 1;
		}
		for (int i = 1; i < m; i++) {
			for (int j = 1; j < n; j++) {
				dp[i][j] = dp[i][j - 1] + dp[i - 1][j];
			}
		}
		return dp[m - 1][n - 1];

	}

	public int uniquePaths(int m, int n) {
		// 起点（1，1） 这里为什么是（1，1）呢？其实是一样的，我们上面的方法定义了（0，0）为起点，那么终点就为（m-1，n-1）
		// 这里起点为（1，1）那么终点就为 （m，n）
		if (m == 1 || n == 1)
			return 1;
		// 终点（m，n）
		return uniquePaths(m, n - 1) + uniquePaths(m - 1, n);
	}

	public long uniquePaths3(int m, int n) {
		long ret = 1;
		for (int x = n, y = 1; y < m; ++x, ++y) {
			//组合公式的循环求解
			ret = ret * x / y;
		}
		return ret;
	}

	/**
	 * Definition for an interval.
	 */
	class Interval {
		int start;
		int end;

		Interval() {
			start = 0;
			end = 0;
		}

		Interval(int s, int e) {
			start = s;
			end = e;
		}
	}

	/*
	描述
	给出一组区间，请合并所有重叠的区间。
	请保证合并后的区间按区间起点升序排列。
	数据范围：区间组数 0 \le n \le 10000≤n≤1000，区间内 的值都满足 0 \le val \le 100000≤val≤10000
	要求：空间复杂度 O(n)O(n)，时间复杂度 O(nlogn)O(nlogn)
	进阶：空间复杂度 O(val)O(val)，时间复杂度O(val)O(val)
	示例1
	输入：[[10,30],[20,60],[80,100],[150,180]]
	返回值：[[10,60],[80,100],[150,180]]
	 */
	public ArrayList<Interval> merge(ArrayList<Interval> intervals) {
		Collections.sort(intervals, new Comparator<Interval>() {
			@Override
			public int compare(Interval a, Interval b) {
				return a.start - b.start;
			}
		});
		ArrayList<Interval> ans = new ArrayList<Interval>();
		int len = intervals.size();
		if (len == 0)
			return ans;
		for (int i = 1; i < len; i++) {
			if (intervals.get(i).start <= intervals.get(i - 1).end) {
				intervals.get(i).start = Math.min(intervals.get(i - 1).start, intervals.get(i).start);
				intervals.get(i).end = Math.max(intervals.get(i - 1).end, intervals.get(i).end);
			} else {
				ans.add(intervals.get(i - 1));
			}
		}
		ans.add(intervals.get(len - 1));
		return ans;
	}

	/*
	 已知int一个有序矩阵mat，同时给定矩阵的大小n和m以及需要查找的元素x，
	 且矩阵的行和列都是从小到大有序的。
	 设计查找算法返回所查找元素的二元数组，代表该元素的行号和列号(均从零开始)。保证元素互异。
	 输入：[[1,2,3],[4,5,6]],2,3,6
	 返回值：[1,2]
	 */
	public int[] findElement(int[][] mat, int n, int m, int x) {
		// write code here
		int[] result = new int[2];
		int startN = 0;
		int startM = m - 1;
		int count = 0;
		while (startN < n && startM >= 0) {
			count++;
			if (mat[startN][startM] < x) {
				++startN;
			} else if (mat[startN][startM] > x) {
				--startM;
			} else {
				return new int[] { startN, startM };
			}
		}
		System.out.println("count >>> " + count);
		return result;
	}

	/*
	 给定一个无重复元素的整数数组nums，请你找出其中没有出现的最小的正整数
	 */
	public int minNumberDisappeared(int[] nums) {
		if (nums == null || nums.length == 0)
			return 1;
		int len = nums.length;
		for (int i = 0; i < len; i++) {
			while (nums[i] > 0 && nums[i] <= len && i != nums[i] - 1) {
				swap(nums, i, nums[i] - 1);
			}
		}
		for (int i = 0; i < len; i++) {
			if (i + 1 != nums[i]) {
				return i + 1;
			}
		}
		return len + 1;
	}

	public void swap(int[] nums, int a, int b) {
		int temp = nums[a];
		nums[a] = nums[b];
		nums[b] = temp;
	}

	/*一个机器人在m×n大小的地图的左上角（起点）。
	机器人每次可以向下或向右移动。机器人要到达地图的右下角（终点）。
	可以有多少种不同的路径从起点走到终点？*/
	public int uniquePaths5(int m, int n) {
		// write code here
		int[][] dp = new int[m][n];
		for (int i = 0, j = 0; i < m; i++) {
			dp[i][j] = 1;
		}
		for (int i = 0, j = 0; j < n; j++) {
			dp[i][j] = 1;
		}
		for (int i = 1; i < m; i++) {
			for (int j = 1; j < n; j++) {
				dp[i][j] = dp[i][j - 1] + dp[i - 1][j];
			}
		}
		return dp[m - 1][n - 1];

	}

	/*给定数组arr，arr中所有的值都为正整数且不重复。
	 * 每个值代表一种面值的货币，每种面值的货币可以使用任意张，
	 * 再给定一个aim，代表要找的钱数，求组成aim的最少货币数。
	如果无解，请返回-1.*/
	public int minMoney(int[] arr, int aim) {
		// write code here
		int Max = aim + 1;//定一个全局最大值
		int[] dp = new int[aim + 1];//dp[i]的含义是目标值为i的时候最少钱币数是多少。
		Arrays.fill(dp, Max);//把dp数组全部定为最大值
		dp[0] = 0;//总金额为0的时候所需钱币数一定是0
		for (int i = 1; i <= aim; i++) {// 遍历目标值
			for (int j = 0; j < arr.length; j++) {// 遍历钱币
				if (arr[j] <= i) {//如果当前的钱币比目标值小就可以兑换
					dp[i] = Math.min(dp[i], dp[i - arr[j]] + 1);
				}
			}
		}
		return dp[aim] > aim ? -1 : dp[aim];

	}

	/*给出两个字符串 s 和 t，要求在 s 中找出最短的包含 t 中所有字符的连续子串。
	数据范围：0 > |S|,|T| \le100000>∣S∣,∣T∣≤10000，保证s和t字符串中仅包含大小写英文字母
	要求：进阶：空间复杂度 O(n)O(n) ， 时间复杂度 O(n)O(n)
	例如：
	S ="XDOYEZODEYXNZ"S="XDOYEZODEYXNZ"
	T ="XYZ"T="XYZ"
	找出的最短子串为"YXNZ""YXNZ".
	注意：
	如果 s 中没有包含 t 中所有字符的子串，返回空字符串 “”；
	满足条件的子串可能有很多，但是题目保证满足条件的最短的子串唯一。*/
	public String minWindow(String S, String T) {
		int[] map = new int[128];
		//init map, 记录T中每个元素出现的次数
		for (int i = 0; i < T.length(); i++) {
			map[T.charAt(i)]++;
		}

		// begin end两个指针指向窗口的首位，d记录窗口的长度， counter记录T中还有几个字符没被窗口包含
		int begin = 0, end = 0, d = Integer.MAX_VALUE, counter = T.length(), head = 0;
		// end指针一直向后遍历
		while (end < S.length()) {
			// map[] > 0 说明该字符在T中出现，counter-- 表示对应的字符被包含在了窗口，counter--, 如果s中的字符没有在T中出现，则map[]中对应的字符-1后变为负值
			if (map[S.charAt(end++)]-- > 0) {
				counter--;
			}
			// 当counter==0时，说明窗口已经包含了T中的所有字符
			while (counter == 0) {
				if (end - begin < d) {
					d = end - (head = begin);
				}
				if (map[S.charAt(begin++)]++ == 0) { // begin开始后移，继续向后寻找。如果begin后移后指向的字符在map中==0，表示是在T中出现的，如果没有出现，map[]中的值会是负值。
					counter++; // 在T中的某个字符从窗口中移除，所以counter++。
				}
			}
		}
		return d == Integer.MAX_VALUE ? "" : S.substring(head, head + d);
	}

	public String minWindow2(String S, String T) {
		// write code here
		if (S == null || S.length() == 0)//特殊情况，返回空字符串
			return "";
		if (T == null || T.length() == 0)
			return "";
		int[] td = new int[256];//设置td，保存T中所有字符出现的次数
		for (char tc : T.toCharArray())//字符串转换为字符数组
			td[tc]++;
		int[] sd = new int[256];
		int slen = S.length();
		int start = 0, first = -1, end = 0;//距离标志
		int found = 0;//在S中发现T元素的数目
		for (int i = 0; i < S.length(); i++) {
			sd[S.charAt(i)]++;//charAt(i)为第i个字符在字符串S中所占的位置
			if (sd[S.charAt(i)] <= td[S.charAt(i)]) {//计算S的位置是否与T的位置相等
				found++;
			}
			if (found == T.length()) {//满足条件
				while (start <= i && sd[S.charAt(start)] > td[S.charAt(start)]) {
					sd[S.charAt(start)]--;
					start++;
				}
				//如果比当前最小子串小，则更新
				if (i + 1 - start <= slen) {
					slen = i + 1 - start;
					first = start;
					end = i + 1;
				}
				sd[S.charAt(start)]--;
				start++;
				found--;
			}
		}
		if (first == -1) {
			return "";
		} else {
			return S.substring(first, end);//返回最终子串的范围
		}
	}

	public String minWindow3(String S, String T) {
		int length = 256;
		int[] srcHash = new int[length];
		// 记录目标字符串每个字母出现次数
		for (int i = 0; i < T.length(); i++) {
			srcHash[T.charAt(i)]++;
		}
		int start = 0, i = 0;
		// 用于记录窗口内每个字母出现次数
		int[] destHash = new int[length];
		int found = 0;
		int begin = -1, end = S.length(), minLength = S.length();
		for (start = i = 0; i < S.length(); i++) {
			// 每来一个字符给它的出现次数加1
			destHash[S.charAt(i)]++;
			// 如果加1后这个字符的数量不超过目标串中该字符的数量，则找到了一个匹配字符
			if (destHash[S.charAt(i)] <= srcHash[S.charAt(i)])
				found++;
			// 如果找到的匹配字符数等于目标串长度，说明找到了一个符合要求的子串   
			if (found == T.length()) {
				// 将开头没用的都跳过，没用是指该字符出现次数超过了目标串中出现的次数，并把它们出现次数都减1
				while (start < i && destHash[S.charAt(start)] > srcHash[S.charAt(start)]) {
					destHash[S.charAt(start)]--;
					start++;
				}
				// 这时候start指向该子串开头的字母，判断该子串长度
				if (i - start < minLength) {
					minLength = i - start;
					begin = start;
					end = i;
				}
				// 把开头的这个匹配字符跳过，并将匹配字符数减1
				destHash[S.charAt(start)]--;
				found--;
				// 子串起始位置加1，我们开始看下一个子串了
				start++;
			}
		}
		// 如果begin没有修改过，返回空
		return begin == -1 ? "" : S.substring(begin, end + 1);
	}

	/*二维数组中的查找
	在一个二维数组array中（每个一维数组的长度相同），
	每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
	请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
	[
	[1,2,8,9],
	[2,4,9,12],
	[4,7,10,13],
	[6,8,11,15]
	]
	给定 target = 7，返回 true。
	给定 target = 3，返回 false。*/
	public boolean findNumberInUpMatrix(int[][] array, int target) {
		if (array.length == 0) {
			return false;
		}
		for (int i = 0; i < array.length; i++) {
			if (array[i].length == 0) {
				return false;
			}
			int rMin = array[i][0];
			int rMax = array[i][array[i].length - 1];
			if (target < rMin) {
				return false;
			} else if (target == rMax || target == rMin) {
				return true;
			} else if (target > rMin && target < rMax) {
				for (int j = 1; j < array[i].length - 1; j++) {
					if (array[i][j] == target) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public boolean findNumberInUpMatrix2(int target, int[][] array) {
		if (array.length == 0) {
			return false;
		}
		for (int i = 0; i < array.length; i++) {
			if (array[i].length == 0) {
				return false;
			}
			int rMin = array[i][0];
			int rMax = array[i][array[i].length - 1];
			if (target < rMin) {
				return false;
			} else if (target == rMax || target == rMin) {
				return true;
			} else if (target > rMin && target < rMax) {
				for (int j = 1; j < array[i].length - 1; j++) {
					if (array[i][j] == target) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public boolean findNumberInUpMatrix3(int[][] array, int target) {
		if (array.length == 0) {
			return false;
		}
		return spiralOrder(array).contains(target) ? true : false;
	}

	/*数组中的逆序对
	在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组,
	求出这个数组中的逆序对的总数P。并将P对1000000007取模的结果输出。 即输出P mod 1000000007*/

	private int cnt;

	public int reverseOrderPairs(int[] array) {
		mergeSort(array, 0, array.length - 1);
		return cnt;
	}

	private void mergeSort(int[] array, int start, int end) {
		if (start >= end)
			return;
		int mid = (start + end) / 2;
		mergeSort(array, start, mid);
		mergeSort(array, mid + 1, end);
		mergeOne(array, start, mid, end);
	}

	private void mergeOne(int[] array, int start, int mid, int end) {
		int[] temp = new int[end - start + 1];
		int k = 0, i = start, j = mid + 1;
		while (i <= mid && j <= end) {
			//如果前面的元素小于后面的不能构成逆序对
			if (array[i] <= array[j])
				temp[k++] = array[i++];
			else {
				//如果前面的元素大于后面的，那么在前面元素之后的元素都能和后面的元素构成逆序对
				temp[k++] = array[j++];
				cnt = (cnt + (mid - i + 1)) % 1000000007;
			}
		}
		while (i <= mid)
			temp[k++] = array[i++];
		while (j <= end)
			temp[k++] = array[j++];
		for (int l = 0; l < k; l++) {
			array[start + l] = temp[l];
		}
	}

	/*给定一个由'0'和'1'组成的2维矩阵，
	返回该矩阵中最大的由'1'组成的正方形的面积，输入的矩阵是字符形式而非数字形式。
	输入：[[1,0,1,0,0],[1,0,1,1,1],[1,1,1,1,1],[1,0,0,1,0]]
	返回值：4
	*/
	public int solve(char[][] matrix) {
		//二维矩阵的宽和高
		int maxSide = 0;
		// 特殊情况
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
			return maxSide;
		}
		int rows = matrix.length, columns = matrix[0].length;
		// dp 数组
		int[][] dp = new int[rows][columns];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				if (matrix[i][j] == '1') {
					if (i == 0 || j == 0) {
						// dp 数组初始数据
						dp[i][j] = 1;
					} else {
						// 转移方程
						dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
					}
					maxSide = Math.max(maxSide, dp[i][j]);
				}
			}
		}
		// 返回结果
		return maxSide * maxSide;
	}

	/*一个数组A中存有 n 个整数，在不允许使用另外数组的前提下，将每个整数循环向右移 M（ M >=0）个位置
	 输入：6,2,[1,2,3,4,5,6]
	 返回值：[5,6,1,2,3,4]
	 * */
	/**
	 * 旋转数组
	 * 
	 * @param n int整型 数组长度
	 * @param m int整型 右移距离
	 * @param a int整型一维数组 给定数组
	 * @return int整型一维数组
	 */
	public int[] rotateArray(int n, int m, int[] a) {
		// 三次反转
		// 1 2 3 4 5 6
		// 4 3 2 1 5 6
		// 4 3 2 1 6 5
		// 5 6 1 2 3 4
		m %= n;
		// n-m至n-1的数据反转
		reverse(a, n - m, n - 1);
		// 0至n-m-1的数据反转
		reverse(a, 0, n - m - 1);
		// 全员反转
		reverse(a, 0, n - 1);
		return a;
	}

	// 反转数组
	private void reverse(int[] a, int start, int end) {
		while (start < end) {
			int temp = a[start];
			a[start] = a[end];
			a[end] = temp;
			++start;
			--end;
		}
	}

	/*子数组最大乘积
	给定一个double类型的数组arr，其中的元素可正可负可0，返回连续子数组累乘的最大乘积。
	输入：[-2.5,4,0,3,0.5,8,-1]
	返回值：12.00000
	说明：取连续子数组[3,0.5,8]可得累乘的最大乘积为12.00000 */
	public double maxProduct(double[] arr) {
		double maxVal = arr[0];
		double minVal = arr[0];
		double res = arr[0];
		for (int i = 1; i < arr.length; i++) {
			if (arr[i] < 0) {
				double tmp = maxVal;
				maxVal = minVal;
				minVal = tmp;
			}
			maxVal = Math.max(arr[i], arr[i] * maxVal);
			minVal = Math.min(arr[i], arr[i] * minVal);
			res = Math.max(res, maxVal);
		}
		return res;
	}

	public double maxProduct02(double[] arr) {
		double max = Double.MIN_VALUE;
		for (int i = 0; i < arr.length; i++) {
			double sum = 1;
			for (int j = i; j < arr.length; j++) {
				sum *= arr[j];
				// 求得子数组arr[i]~arr[j]的积，更新最大值
				max = Math.max(max, sum);
			}
		}
		return max;
	}

	public double maxProduct03(double[] arr) {
		double min = arr[0];
		double max = min;
		double res = min;
		for (int i = 1; i < arr.length; i++) {
			double t_max = max;
			//局部最大值可以从哪些地方产生：1. arr[i]  2. min*arr[i]  3. max*arr[i]
			max = Math.max(Math.max(arr[i], arr[i] * max), min * arr[i]);
			//局部最小值可以从哪些地方产生：1. arr[i]  2. max*arr[i]  3. min*arr[i]
			min = Math.min(Math.min(arr[i], arr[i] * min), t_max * arr[i]);
			//更新全局最大值
			res = Math.max(res, max);
		}
		return res;
	}

	/*矩阵最长递增路径
	给定一个 n 行 m 列矩阵 matrix ，矩阵内所有数均为非负整数。 你需要在矩阵中找到一条最长路径，使这条路径上的元素是递增的。并输出这条最长路径的长度。
	这个路径必须满足以下条件：
	1. 对于每个单元格，你可以往上，下，左，右四个方向移动。 你不能在对角线方向上移动或移动到边界外。
	2. 你不能走重复的单元格。即每个格子最多只能走一次。*/
	// 记忆表
	private int[][] paths;

	public int matrixLongestIncrementPath(int[][] matrix) {
		int max = 0;
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
			return max;
		}
		// 初始化
		paths = new int[matrix.length][matrix[0].length];
		// 以所有的节点为路径的开始节点
		for (int i = 0; i < matrix.length; ++i) {
			for (int j = 0; j < matrix[0].length; ++j) {
				// 计算从（i，j）开始的最长递增路径
				int temp = searchMaxPath(matrix, i, j, -1);
				max = Math.max(temp, max);
			}
		}
		return max;
	}

	private int searchMaxPath(int[][] matrix, int row, int col, int pre) {
		if (row < 0 || col < 0 || row >= matrix.length || col >= matrix.length) {
			return 0;
		}
		if (matrix[row][col] <= pre) {
			return 0;
		}
		// 表中存在，直接返回
		if (paths[row][col] > 0) {
			return paths[row][col];
		}
		int max = 0;
		int temp = 0;
		// 点（row，col）往上走的最长路径
		temp = searchMaxPath(matrix, row - 1, col, matrix[row][col]);
		max = Math.max(temp, max);
		// 点（row，col）往下走的最长路径
		temp = searchMaxPath(matrix, row + 1, col, matrix[row][col]);
		max = Math.max(temp, max);
		// 点（row，col）往左走的最长路径
		temp = searchMaxPath(matrix, row, col - 1, matrix[row][col]);
		max = Math.max(temp, max);
		// 点（row，col）往右走的最长路径
		temp = searchMaxPath(matrix, row, col + 1, matrix[row][col]);
		max = Math.max(temp, max);
		// 记录
		paths[row][col] = max + 1;
		return paths[row][col];
	}

	/*矩阵乘法
	给定两个 n*n 的矩阵 A 和 B ，求 A*B 。*/
	public int[][] solve(int[][] a, int[][] b) {
		// write code here
		int m = a.length;//A的行数
		int p = a[0].length;//A的列数=B的行数
		int n = b[0].length;//B的行数
		int[][] ans = new int[m][n];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				int t = 0;
				for (int k = 0; k < p; k++) {
					t += a[i][k] * b[k][j];
				}
				ans[i][j] = t;
			}
		}
		return ans;
	}

	/*01背包
	已知一个背包最多能容纳体积之和为v的物品
	现有 n 个物品，第 i 个物品的体积为 vi , 重量为 wi
	求当前背包最多能装多大重量的物品?
	 * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
	 * 计算01背包问题的结果
	 * @param V int整型 背包的体积
	 * @param n int整型 物品的个数
	 * @param vw int整型二维数组 第一维度为n,第二维度为2的二维数组,vw[i][0],vw[i][1]分别描述i+1个物品的vi,wi
	 * @return int整型
	 */
	public int knapsack(int V, int n, int[][] vw) {
		// write code here
		if (V == 0 || n == 0 || vw == null) {
			return 0;
		}
		int[][] dp = new int[n + 1][V + 1];
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= V; j++) {
				if (j < vw[i - 1][0]) {
					dp[i][j] = dp[i - 1][j];
				} else {
					dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - vw[i - 1][0]] + vw[i - 1][1]);
				}
			}
		}
		return dp[n][V];
	}

	/*被围绕的区域
	给定一个 n*m 大小的的矩阵，矩阵中由 ‘X' 和 'O' 构成，找到所有被 'X' 围绕的区域，并将其用 'X' 填充。
	例如：
	[['X','X','X','X'],
	['X','O','O','X'],
	['X','O','X','X'],
	['X','X','O','X']]
	中间的三个 ‘O’ 被 'X'围绕，因此将其填充为 'X' ，但第四行的 'O' 下方没有被 'X' 围绕，因此不改变，结果为
	[['X','X','X','X'],
	['X','X','X','X'],
	['X','X','X','X'],
	['X','X','O','X']]*/
	public char[][] surroundedArea(char[][] board) {
		// write code here
		int n = board.length; // n行
		int m = board[0].length; // m列
		// 最外层的'O'先标记为'A'，能和最外层直接相连的'O'也标记为'A'
		for (int i = 0; i < n; i++) {
			dfs3(board, i, 0); // 左边界
			dfs3(board, i, m - 1); // 右边界
		}
		for (int j = 0; j < m; j++) {
			dfs3(board, 0, j); // 上边界
			dfs3(board, n - 1, j); // 下边界
		}
		// 遍历整个矩阵，把'O'变为'X', 把'A'变为'O'
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (board[i][j] == 'O')
					board[i][j] = 'X';
				if (board[i][j] == 'A')
					board[i][j] = 'O';
			}
		}
		return board;

	}

	void dfs3(char[][] board, int x, int y) {
		// 不能超出边界
		// 注意最后一个条件不能写成board[x][y]=='X'，会进入死循环
		if (x < 0 || x >= board.length || y < 0 || y >= board[0].length || board[x][y] != 'O')
			return;
		// 遇到O就改为A
		if (board[x][y] == 'O')
			board[x][y] = 'A'; // 标记为A
		dfs3(board, x - 1, y); //上
		dfs3(board, x, y + 1); //右
		dfs3(board, x + 1, y); //下
		dfs3(board, x, y - 1); //左
	}

	/* 最大矩形
	给定一个仅包含 0 和 1 ，大小为 n*m 的二维二进制矩阵，找出仅包含 1 的最大矩形并返回面积。
	数据范围： 保证输入的矩形中仅含有 0 和 1
	例如输入[[1,0,1,0,0],[1,1,1,1,0],[1,1,1,1,1],[1,0,0,1,0]]时，对应的输出为8，所形成的最大矩形如下图所*/
	public int maximalRectangle(int[][] matrix) {
		// write code here
		int m = matrix.length, n = matrix[0].length;
		int[] h = new int[n + 2];
		int max = 0;
		for (int i = 0; i < m; i++) {
			for (int j = 1; j < n + 2; j++) {
				if (j < n + 1 && matrix[i][j - 1] != 0) {
					h[j] = h[j] + 1;
				} else {
					h[j] = 0;
				}
			}
			max = Math.max(max, maxArea(h));
		}
		return max;
	}

	private int maxArea(int[] a) {
		Deque<Integer> q = new LinkedList<>();
		int[] h = Arrays.copyOf(a, a.length);
		int max = 0, area = 0, k = 0;
		q.offer(0);
		for (int i = 1; i < a.length; i++) {
			if (h[q.peekLast()] <= h[i]) {
				q.offer(i);
				continue;
			}
			while (!q.isEmpty() && h[q.peekLast()] > h[i]) {
				k = q.peekLast();
				q.removeLast();
				max = Math.max(max, h[k] * (i - k));
			}
			h[k] = h[i];
			q.offer(k);
		}
		return max;
	}

	/*棋子翻转
	在 4x4 的棋盘上摆满了黑白棋子，黑白两色棋子的位置和数目随机，其中0代表白色，1代表黑色；左上角坐标为 (1,1) ，右下角坐标为 (4,4) 。
	现在依次有一些翻转操作，要对以给定翻转坐标(x,y)（也即第x行第y列）为中心的上下左右四个棋子的颜色进行翻转。
	给定两个数组 A 和 f ，分别代表 初始棋盘 和 哪些要进行翻转的位置(x,y) ，请返回经过所有翻转操作后的棋盘。*/
	public int[][] flipChess(int[][] A, int[][] f) {
		// write code here
		int[][] direction = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

		for (int i = 0; i < f.length; i++) {
			int x = f[i][0] - 1;
			int y = f[i][1] - 1;

			for (int j = 0; j < direction.length; j++) {
				int x_ = x + direction[j][0];
				int y_ = y + direction[j][1];

				if (x_ >= 0 && x_ < A.length && y_ >= 0 && y_ < A[0].length) {
					A[x_][y_] = A[x_][y_] == 0 ? 1 : 0;
				}
			}
		}

		return A;
	}

	/*NC245 杨辉三角(一)
	给定一个非负整数 num ，生成杨辉三角的前 num 行。
	杨辉三角中，每个数是左上方和右上方的数之和。
	数据范围： 
	例如当输入为4时，对应的返回值为[[1],[1,1],[1,2,1],[1,3,3,1]]，打印结果如下图所示：*/
	public int[][] generateYangHui(int num) {
		// write code here
		int[][] dp = new int[num][];
		for (int i = 0; i < num; ++i) {
			int[] temp = new int[i + 1];
			for (int j = 0; j <= i; j++) {
				if (j == 0) {
					temp[j] = 1;
				} else if (j == i) {
					temp[j] = 1;
				} else {
					temp[j] = dp[i - 1][j - 1] + dp[i - 1][j];
				}
				dp[i] = temp;
			}
		}
		return dp;
	}

	/*NC185 岛屿的最大面积
	给定一个用 n*m 矩阵表示的群岛的地图，其中 1 表示岛屿， 0 表示海洋，
	每个岛屿的水平或竖直方向相邻的岛屿可以视为连在一起的岛屿，每一块岛屿视为面积为 1 ，请问面积最大的岛屿是多少。*/
	public int maxAreaIsland(int[][] grid) {
		// write code here
		if (grid == null || grid[0] == null) {
			return 0;
		}
		int m = grid.length, n = grid[0].length;
		int res = 0;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (grid[i][j] == 1) {
					res = Math.max(res, infect(m, n, i, j, grid));
				}
			}
		}
		return res;
	}

	private int infect(int m, int n, int i, int j, int[][] grid) {
		if (i < 0 || i >= m || j < 0 || j >= n || grid[i][j] != 1) {
			return 0;
		}
		grid[i][j] = 2;
		return 1 + infect(m, n, i + 1, j, grid) + infect(m, n, i - 1, j, grid) + infect(m, n, i, j + 1, grid)
				+ infect(m, n, i, j - 1, grid);
	}

	/*NC158 单源最短路
	在一个有 n 个点， m 个边的有向图中，已知每条边长，求出 1 到 n 的最短路径，返回 1 到 n 的最短路径值。如果 1 无法到 n ，输出 -1
	图中可能有重边，无自环。*/
	public int findShortestPath(int n, int m, int[][] graph) {
		int[][] paths = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++)
				paths[i][j] = Integer.MAX_VALUE;
		}
		// 从点1到点i + 1的最小路径为minPath[i]
		int[] minPath = new int[n];
		// 索引是顶点编号，值是是否已经找到最小路径
		boolean[] found = new boolean[n + 1];

		for (int[] edge : graph) {
			int from = edge[0] - 1, to = edge[1] - 1;
			paths[from][to] = Math.min(paths[from][to], edge[2]);
		}

		int from = 0;
		// 初始化最小路径表为点1为起点的路径信息
		for (int i = 0; i < n; i++)
			minPath[i] = paths[from][i];
		found[0] = true;
		while (true) {
			// 将当前最小路径表中尚未找到最小路径的点中，到点1的路径最短的点赋予from
			from = findMinPathTo(minPath, found, n);
			if (from == -1)
				break;
			found[from] = true;
			// 更新最小路径表
			updateMinPath(minPath, from, paths, found, n);
		}

		return minPath[n - 1] == Integer.MAX_VALUE ? -1 : minPath[n - 1];
	}

	private void updateMinPath(int[] minPath, int minTo, int[][] paths, boolean[] found, int n) {
		int[] fromPath = paths[minTo];
		for (int i = 1; i < n; i++) {
			if (found[i] || fromPath[i] == Integer.MAX_VALUE)
				continue;
			// 从点1到点i + 1的最小路径为minPath[i]
			minPath[i] = Math.min(minPath[i], minPath[minTo] + fromPath[i]);
		}
	}

	private int findMinPathTo(int[] fromPath, boolean[] found, int n) {
		int min = -1;
		for (int i = 1; i < n; i++) {
			if (found[i] || fromPath[i] == Integer.MAX_VALUE)
				continue;

			if (min == -1)
				min = i;
			else if (fromPath[i] < fromPath[min])
				min = i;
		}
		return min;
	}

	/*NC157 单调栈
	给定一个长度为 n 的可能含有重复值的数组 arr ，找到每一个 i 位置左边和右边离 i 位置最近且值比 arri 小的位置。
	请设计算法，返回一个二维数组，表示所有位置相应的信息。位置信息包括：两个数字 l 和 r，如果不存在，则值为 -1，下标从 0 开始。*/
	public int[][] foundMonotoneStack (int[] nums) {
        // write code here
        int [][] result = new int[nums.length][2];
        for(int i = 0;i<nums.length;i++){
            if(i == 0){
                result[i][0] = -1;  
            }else{
                int tmp = i-1;
                while(tmp !=-1 && nums[i]<nums[tmp]){
                    tmp = result[tmp][0];
                }
                result[i][0] = tmp;
            }
        }
 
        for(int i = nums.length-1;i>=0;i--){
            if(i == nums.length-1){
                result[i][1] = -1;
            }else{
                int tmp = i+1;
                while(tmp !=-1 && nums[i]<nums[tmp]){
                    tmp = result[tmp][1];
                }
                result[i][1] = tmp;
            }
        }
        return result;
    }
}
