/**
 * 
 */
package com.tompai.number;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

/**
 * @author Administrator
 *
 */
public class CommonNumber {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CommonNumber test = new CommonNumber();
		String s = "123";
		String t = "653";
		System.out.print(test.bigNumberAdd(s, t));

	}

	/*
	以字符串的形式读入两个数字，编写一个函数计算它们的和，以字符串形式返回。
	数据范围：len(s),len(t) \le 100000len(s),len(t)≤100000，字符串仅由'0'~‘9’构成
	要求：时间复杂度 O(n)O(n)
	 */
	public String add(String s, String t) {
		// write code here
		Stack<Integer> stack = new Stack<>();
		StringBuilder stringBuilder = new StringBuilder();
		int i = s.length() - 1, j = t.length() - 1, carry = 0;
		while (i >= 0 || j >= 0 || carry != 0) {
			carry += i >= 0 ? s.charAt(i--) - '0' : 0;
			carry += j >= 0 ? t.charAt(j--) - '0' : 0;
			stack.push(carry % 10);
			carry = carry / 10;
		}
		while (!stack.isEmpty())
			stringBuilder.append(stack.pop());
		return stringBuilder.toString();
	}

	/*
	给定一个十进制数 M ，以及需要转换的进制数 N 。将十进制数 M 转化为 N 进制数。
	当 N 大于 10 以后， 应在结果中使用大写字母表示大于 10 的一位，如 'A' 表示此位为 10 ， 'B' 表示此位为 11 。
	若 M 为负数，应在结果中保留负号。
	 */
	public String binaryConversion(int M, int N) {
		// write code here
		if (M == 0)
			return "0";
		String s = "0123456789ABCDEF";
		StringBuffer sb = new StringBuffer();
		boolean f = false;
		if (M < 0) {
			f = true;
			M = -M;
		}
		while (M != 0) {
			sb.append(s.charAt(M % N));
			M /= N;
		}
		if (f)
			sb.append("-");
		return sb.reverse().toString();
	}

	static Map<String, Long> map = new HashMap<String, Long>();

	//计算组合数
	private static long comb(int m, int n) {
		String key = m + "," + n;
		if (n == 0)
			return 1;
		if (n == 1)
			return m;
		if (n > m / 2)
			return comb(m, m - n);
		if (n > 1) {
			if (!map.containsKey(key))
				map.put(key, comb(m - 1, n - 1) + comb(m - 1, n));
			return map.get(key);
		}
		return -1;
	}

	/*
	 给出n对括号，请编写一个函数来生成所有的由n对括号组成的合法组合。
	例如，给出n=3，解集为：
	"((()))", "(()())", "(())()", "()()()", "()(())"
	数据范围：0 \le n \le 100≤n≤10
	要求：空间复杂度 O(n!)O(n!)，时间复杂度 O(n!)O(n!)
	 */
	//n表示括号的对数
	//curStr表示递归得到的结果
	//left表示左括号已经用了几个
	//right表示右括号已经用了几个
	public ArrayList<String> generateParenthesis(int n) {
		// write code here
		ArrayList<String> res = new ArrayList<>();

		//特判
		if (n == 0) {
			return res;
		}

		dfs("", 0, 0, n, res);
		return res;
	}

	private void dfs(String curStr, int left, int right, int n, List<String> res) {
		if (left == n && right == n) {
			res.add(curStr);
			return;
		}

		//减枝
		if (left < right) {
			return;
		}

		if (left < n) {
			dfs(curStr + "(", left + 1, right, n, res);
		}

		if (right < n) {
			dfs(curStr + ")", left, right + 1, n, res);
		}
	}

	/*
	有一个NxN整数矩阵，请编写一个算法，将矩阵顺时针旋转90度。
	给定一个NxN的矩阵，和矩阵的阶数N,请返回旋转后的NxN矩阵。
	 */
	public int[][] rotateMatrix(int[][] mat, int n) {
		// write code here
		int[][] temp = new int[n][n];//新建temp对象，作为最终返回的对象
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				temp[j][n - 1 - i] = mat[i][j];//直接交换
			}
		}
		return temp;
	}

	/*环形链表的约瑟夫问题
	编号为 1 到 n 的 n 个人围成一圈。从编号为 1 的人开始报数，报到 m 的人离开。
	下一个人继续从 1 开始报数。
	n-1 轮结束以后，只剩下一个人，问最后留下的这个人编号是多少？
	f(N,M)=(f(N−1,M)+M)%N
	*/
	public int ysf3(int n, int m) {
		LinkedList<Integer> list = new LinkedList<>();
		if (m < 1 || n < 1) {
			return -1;
		}
		for (int i = 0; i < n; i++) {
			list.add(i);
		}
		int bt = 0;
		while (list.size() > 1) {
			bt = (bt + m - 1) % list.size();
			list.remove(bt);
		}
		return list.get(0) + 1;
	}

	public int ysf1(int n, int m) {
		if (m < 1 || n < 1)
			return -1;
		int last = 0;
		// i代表有目前有个人
		//最后一轮剩下2个人，所以从2开始反推
		for (int i = 2; i <= n; i++)
			last = (last + m) % i;
		return last + 1;
	}

	public int ysf2(int n, int m) {
		if (m < 1 || n < 1)
			return -1;
		// i代表有目前有个人
		//最后一轮剩下2个人，所以从2开始反推
		Queue<Integer> queue = new ArrayDeque<Integer>();
		for (int i = 1; i <= n; i++) {
			queue.offer(i);
		}
		int index = 0;
		int k = 0;
		do {
			index++;
			if (index % m == 0) {
				k = queue.poll();
				//System.out.print(k+" ");
				index = 0;
			} else {
				queue.offer(queue.poll());
			}
		} while (queue.size() > 1);

		return queue.poll();
	}

	public int ysf(int n, int m) {
		// write code here
		if (n < 1 || m < 1)
			return 0;
		if (n == 1)
			return 1;
		return (ysf(n - 1, m) + m - 1) % n + 1;
	}

	/*最大数
	给定一个长度为n的数组nums，数组由一些非负整数组成，现需要将他们进行排列并拼接，每个数不可拆分，
	使得最后的结果最大，返回值需要是string类型，否则可能会溢出。
	输入：[2,20,23,4,8]
	返回值："8423220"
	*/
	public String maxStringNumber(int[] nums) {
		// write code here
		ArrayList<String> list = new ArrayList<>();
		//将整型的数字转化为字符串
		for (int i = 0; i < nums.length; i++) {
			list.add(String.valueOf(nums[i]));
		}
		//排序
		Collections.sort(list, new Comparator<String>() {
			@Override
			public int compare(String a, String b) {
				return (b + a).compareTo(a + b);
			}
		});
		//这个地方需要注意如果第一个字符串已经是0了，那么直接输出0
		if (list.get(0).equals("0"))
			return "0";

		StringBuilder res = new StringBuilder(); //结果字符串
		for (int i = 0; i < list.size(); i++) {//将排序好后的字符串一次相加就是最终的结果
			res.append(list.get(i));
		}
		return res.toString();
	}

	/*大数乘法
	以字符串的形式读入两个数字，编写一个函数计算它们的乘积，以字符串形式返回。
	输入：
	"11","99"
	返回值：
	"1089"
	说明：
	11*99=1089 */
	public String bigNumberMultiplication(String s, String t) {
		// 判断不合法的输入
		if (s == null || s.length() == 0 || t == null || t.length() == 0) {
			return null;
		}
		// 如果有一个字符串为 0 ，结果就是 0
		if ("0".equals(s) || "0".equals(t)) {
			return "0";
		}

		// 最后相乘的结果，长度最多就是 2 个字符串的长度相加
		int[] num = new int[s.length() + t.length()];

		// num1 的每一个数字， 和 num2 的每一个数字相乘；  放到 num 数组的对应位置上
		for (int i = s.length() - 1; i >= 0; i--) {
			for (int j = t.length() - 1; j >= 0; j--) {
				// 同一个位置可能有多种情况映射过来， 所以结果位置上的结果取个累加和即可
				num[i + j + 1] += (s.charAt(i) - '0') * (t.charAt(j) - '0');
			}
		}
		// 如果最后的结果有进位，那么 num 数组的第一位数不为零；
		// 如果没进位，则为零。这种情况需要注意不要这个零。
		int end = num[0] == 0 ? 1 : 0;
		StringBuilder res = new StringBuilder();
		// 保存进位的值
		int carry = 0;
		// 从后往前遍历 num 数组，最后的结果有进位则遍历到 0 结束，没进位就遍历到 1 结束。
		for (int i = num.length - 1; i >= end; i--) {
			// 数组上对应位置的数 和 进位相加的结果
			int temp = num[i] + carry;
			// temp的个位数，才是属于对应位置上的数
			res.append(temp % 10);
			// temp的十位数，是属于对应位置的前一个位置上的数，即是进位的值
			carry = temp / 10;
		}
		// 最后的进位不为零，也是计算的结果，往前进 carry 位即可。
		if (carry != 0) {
			res.append(carry);
		}
		// 根据上述的流程，把 res 倒序一下，就是最后 2 个字符串相乘的结果
		return res.reverse().toString();
	}

	public String bigNumberMultiplication2(String s, String t) {
		int len1 = s.length();
		int len2 = t.length();

		int[] nums1 = new int[len1];
		int[] nums2 = new int[len2];

		for (int i = 0; i < len1; i++) {
			nums1[i] = s.charAt(i) - '0';
		}
		for (int i = 0; i < len2; i++) {
			nums2[i] = t.charAt(i) - '0';
		}

		int[] result = new int[len1 + len2];

		// 1. 逐位相乘
		for (int i = 0; i < len1; i++) {
			for (int j = 0; j < len2; j++) {
				result[i + j] += nums1[i] * nums2[j];
			}
		}

		// 2. 从后往前进位
		for (int k = result.length - 1; k > 0; k--) {
			result[k - 1] += result[k] / 10;
			result[k] = result[k] % 10;
		}

		// 输出字符串
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < result.length - 1; i++) {
			stringBuilder.append(result[i]);
		}
		return stringBuilder.toString();
	}

	public String bigNumberAdd(String num1, String num2) {
		// 判断不合法输入
		if (num1 == null || num1.length() == 0 || num2 == null || num2.length() == 0) {
			return null;
		}
		// num1 字符串的下标， 从右往左遍历
		int index1 = num1.length() - 1;
		// num2 字符串的下标， 从右往左遍历
		int index2 = num2.length() - 1;

		// 最后相加的结果
		StringBuilder res = new StringBuilder();
		// 保存进位
		int carry = 0;

		// num1 或 num2 只要有一个字符串还有值，就继续循环
		while (index1 >= 0 || index2 >= 0) {
			// 拿到 num1 的一个数字
			int x = index1 >= 0 ? num1.charAt(index1--) - '0' : 0;
			// 拿到 num2 的一个数字
			int y = index2 >= 0 ? num2.charAt(index2--) - '0' : 0;
			// 拿到的 2 个数字相加，要考虑进位的情况
			int num = x + y + carry;
			// 该位只保存个位数
			res.append(num % 10);
			// 十位数要往前进一位
			carry = num / 10;
		}
		// 如果最后进位不为 0 ， 则还需要往前进 carry 位
		if (carry != 0) {
			res.append(carry);
		}
		return res.reverse().toString();
	}

	/*二进制中1的个数*/
	public int numberOfOne(int n) {
		int count = 0;
		int flag = 1;
		while (flag != 0) {
			if ((flag & n) != 0)
				count++;

			flag = flag << 1;
		}
		return count;
	}

	/*阶乘末尾0的数量
	给定一个非负整数 n ，返回 n! 结果的末尾为 0 的数量。*/
	public long thenumberof0(long n) {
		long ans = 0;
		long d = 5;
		while (n >= d) {
			ans += n / d;
			d = d * 5;
		}
		return ans;
	}

	/*孩子们的游戏(圆圈中最后剩下的数)
	每年六一儿童节，牛客都会准备一些小礼物和小游戏去看望孤儿院的孩子们。
	其中，有个游戏是这样的：
	首先，让 n 个小朋友们围成一个大圈，小朋友们的编号是0~n-1。然后，随机指定一个数 m ，让编号为0的小朋友开始报数。
	每次喊到 m-1 的那个小朋友要出列唱首歌，然后可以在礼品箱中任意的挑选礼物，并且不再回到圈中，
	从他的下一个小朋友开始，继续0... m-1报数....这样下去....
	直到剩下最后一个小朋友，可以不用表演，并且拿到牛客礼品，请你试着想下，哪个小朋友会得到这份礼品呢？*/
	int lastRemaining_Solution(int n, int m) {
		if (n <= 0)
			return -1;
		int index = 0;
		for (int i = 2; i <= n; ++i) {
			index = (index + m) % i;
		}
		return index;
	}

	/*丑数
	把只包含质因子2、3和5的数称作丑数（Ugly Number）。
	例如6、8都是丑数，但14不是，因为它包含质因子7。 
	习惯上我们把1当做是第一个丑数。求按从小到大的顺序的第 n个丑数。*/
	public int getUglyNumber_Solution(int index) {
		//1 2 3 4 5 6 8
		if (index <= 6)
			return index; // 加快程序输出

		// 三个变量 后面有大作用！
		int i2 = 0, i3 = 0, i5 = 0;
		int[] res = new int[index];
		res[0] = 1; // 第一个丑数为 1

		for (int i = 1; i < index; i++) {
			// 得到下一个丑数，三者中最小的
			res[i] = Math.min(res[i2] * 2, Math.min(res[i3] * 3, res[i5] * 5));
			/*第一次是 2、3、5比较，得到最小的是2*/
			/*第二次是 4、3、5比较，为什么是4了呢？因为上次2已经乘了一次了，所以接下去可以放的丑数在4、3、5之间*/
			// 所以开头的三个指针就是来标记2 3 5 乘的次数的 
			if (res[i] == res[i2] * 2)
				i2++;
			if (res[i] == res[i3] * 3)
				i3++;
			if (res[i] == res[i5] * 5)
				i5++;
		}
		return res[index - 1];
	}

	/**
	 * 如果有一个自然数 a 能被自然数 b 整除，则称 a 为 b 的倍数， b 为 a 的约数。
	 * 几个自然数公有的约数，叫做这几个自然数的公约数。公约数中最大的一个公约数，称为这几个自然数的最大公约数。 输入 a 和 b , 请返回 a 和 b
	 * 的最大公约数。
	 *
	 * 求最大公约数 辗转相除法(欧几里德算法) 例如，求（319，377）： ∵ 319÷377=0（余319） ∴（319，377）=（377，319）； ∵
	 * 377÷319=1（余58） ∴（377，319）=（319，58）； ∵ 319÷58=5（余29） ∴ （319，58）=（58，29）； ∵
	 * 58÷29=2（余0） ∴ （58，29）= 29； ∴ （319，377）=29。 可以写成右边的格式。
	 * 用辗转相除法求几个数的最大公约数，可以先求出其中任意两个数的最大公约数，再求这个最大公约数与第三个数的最大公约数，依次求下去，直到最后一个数为止。
	 * 最后所得的那个最大公约数，就是所有这些数的最大公约数。
	 *
	 * @param m
	 * @param n
	 * @return
	 */
	public int GCD(int m, int n) {
		int result = 0;
		while (n != 0) {
			result = m % n;
			m = n;
			n = result;
		}
		return m;
	}

	/**
	 * 质因数分解法：把每个数分别分解质因数，再把各数中的全部公有质因数提取出来连乘，所得的积就是这几个数的最大公约数。 (小学学的方法)
	 *
	 * @param m
	 * @param n
	 * @return
	 */
	public int PrimeGCD(int m, int n) {
		int result = 1;
		Set<Integer> set1 = getFactor(m);
		Set<Integer> set2 = getFactor(n);
		// 取交集
		set1.retainAll(set2);
		// 取最大
		result = Collections.max(set1);
		return result;
	}

	/**
	 * 更相减损术”,即“可半者半之，不可半者，副置分母、子之数，以少减多，更相减损，求其等也。以等数约之。”
	 * 
	 * @param m
	 * @param n
	 * @return
	 */
	public int equalGCD(int m, int n) {
		while (m != n) {
			if (m > n)
				m -= n;
			else
				n -= m;
		}
		return m;
	}

	/**
	 * 获取某一数值的所有因数
	 *
	 * @param m
	 * @return
	 */
	private Set<Integer> getFactor(int m) {
		Set<Integer> set = new HashSet<Integer>();
		for (int i = 2; i <= m; i++) {

			if ((isPrime(i)) & (m % i == 0)) {
				set.add(i);
			}
		}
		return set;
	}

	private boolean isPrime(int num) {
		if (num == 1)
			return false;
		if (num == 2)
			return true;
		int max = (int) Math.sqrt(num);
		for (int i = 2; i <= max; i++)
			if (num % i == 0)
				return false;
		return true;
	}

	/*Nim游戏
	这是一个经典的博弈。
	你和你的朋友，两个人玩一个游戏。
	1.桌子上有 n 个石头
	2.你和你的朋友轮流取石头，你先手。
	3.每一回合可以取 1~3 个石头。
	4.轮到你的朋友时桌上没有石头则你获胜，则你的朋友获胜。
	你和你的朋友都尽力让自己获胜，如果你有方法必胜，则返回 true ，如果你的朋友有方法必胜，则返回 false*/
	public boolean nimGame(int n) {
		if (n <= 4) {
			return n == 1 || n == 2 || n == 3;
		}
		return n % 4 != 0;
	}

	/*加起来和为目标值的组合(三)
	找出所有相加之和是 n 的 k 个数的组合。组合中只含有 1~9的正整数，且保证每种组合中不含有相同的数字。
	保证一定有解。结果按字典序升序输出。*/
	public int[][] combination(int k, int n) {
		int[] arr = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		ArrayList<ArrayList<Integer>> res = new ArrayList<>();
		ArrayList<Integer> temp = new ArrayList<>();
		dfs(arr, k, n, 0, res, temp);
		int[][] result = new int[res.size()][k];
		for (int i = 0; i < res.size(); i++) {
			for (int j = 0; j < k; j++) {
				result[i][j] = res.get(i).get(j);
			}
		}
		return result;
	}

	public void dfs(int[] arr, int k, int total, int index, ArrayList<ArrayList<Integer>> res,
			ArrayList<Integer> temp) {
		if (temp.size() == k && total == 0) {
			res.add(new ArrayList<>(temp));
			return;
		}
		for (int i = index; i < arr.length; i++) {
			if (total >= arr[i]) {
				temp.add(arr[i]);
				dfs(arr, k, total - arr[i], i + 1, res, temp);
				temp.remove(temp.size() - 1);
			}
		}
	}

	/*NC152 数的划分
	将整数 n 分成 k 份，且每份不能为空，任意两个方案不能相同(不考虑顺序)。
	例如： n=7,k=3 ，下面三种分法被认为是相同的。
	
	1，1，5;1，1，5;
	1，5，1;1，5，1;
	5，1，1;5，1，1;
	
	问有多少种不同的分法, 答案对 10^9 + 7 取模。*/
	public int divideNumber(int n, int k) {
		// write code here
		int[][] dp = new int[n + 1][k + 1];
		dp[0][0] = 1;
		int mod = 1000000007;
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= k; j++) {
				//由于每份不能为空，所以划分数肯定大于总份数
				if (i >= j) {
					//分为至少存在一份是1，和所有份数大于1两种情况
					dp[i][j] = (dp[i - 1][j - 1] + dp[i - j][j]) % mod;
				}
			}
		}
		return dp[n][k];
	}

	/*NC56 回文数字
	在不使用额外的内存空间的条件下判断一个整数是否是回文。
	回文指逆序和正序完全相同。*/
	public boolean isPalindrome(int x) {
		if (x < 0)
			return false;
		if (x < 10)
			return true;
		if (x % 10 == 0)
			return false;
		int l = 0;
		int y = x;
		while (y > 10) {
			l++;
			y /= 10;
		}
		if (l % 2 == 0) {
			int d = (int) Math.pow(10, l);
			while (x / d == x % 10) {
				l -= 2;
				if (l <= 0)
					return true;
				x = (x - x / d * d - x % 10) / 10;
				d = (int) Math.pow(10, l);
			}
			return false;
		} else {
			int d = (int) Math.pow(10, l);
			while (x / d == x % 10) {
				l -= 2;
				if (l <= 0)
					return true;
				x = (x - x / d * d - x % 10) / 10;
				d = (int) Math.pow(10, l);
			}
			return false;
		}
	}
	
	/*NC57 反转数字
	给定一个32位的有符号整数num，将num中的数字部分反转，最后返回反转的结果
	1.只反转数字部分，符号位部分不反转
	2.反转后整数num超过 32 位的有符号整数的范围 [−231,  231 − 1] ，返回 0
	3.假设本题不允许存储 64 位整数(有符号或无符号，即C++不能使用long long ，Java不能使用long等)*/
	public int reverseNumber (int x){
        // write code here
        long ans = 0;
        while (x != 0){
            ans = ans*10+(x%10);
            x/=10;
        }
        if(ans<Integer.MIN_VALUE||ans>Integer.MAX_VALUE){
            ans = 0;
            //System.out.println("反转后数字溢出");
        }
        return (int)ans;
    }

	/*NC68 跳台阶
	一只青蛙一次可以跳上1级台阶，也可以跳上2级。求该青蛙跳上一个 n 级的台阶总共有多少种跳法（先后次序不同算不同的结果）。*/
	public int jumpFloor(int number) {
	    if (number<=1) return 1;
	        return jumpFloor(number-1)+jumpFloor(number-2);
	    }

}
