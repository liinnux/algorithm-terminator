/**
 * 
 */
package com.tompai.string;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Administrator
 *
 */
public class CommonString {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CommonString tets = new CommonString();
		//String s = "(2*(3-4))*5x3-1+(2-3x7)";

		//System.out.println(tets.expressionEvaluation(s));
		//System.out.println(tets.expressionEvaluation2(s));
		//System.out.println(tets.expressionEvaluation3(s));
		String s = "aaaaaaaaaaaaaaaaaaaa";
		String[] wordDic = { "a", "aa", "aaa", "aaaa", "aaaaa", "aaaaaa", "aaaaaaa", "aaaaaaaa", "aaaaaaaaa",
				"aaaaaaaaaa" };
		tets.wordBreak02(s, wordDic);
	}

	//写出一个程序，接受一个字符串，然后输出该字符串反转后的字符串。（字符串长度不超过1000）
	public String reversString(String str) {
		char[] cstr = str.toCharArray();
		int len = str.length();
		for (int i = 0; i < len / 2; i++) {
			char t = cstr[i];
			cstr[i] = cstr[len - 1 - i];
			cstr[len - 1 - i] = t;
		}
		return new String(cstr);
	}

	/*NC17 最长回文子串
	对于长度为n的一个字符串A（仅包含数字，大小写英文字母），请设计一个高效算法，计算其中最长回文子串的长度。*/
	public int getLongestPalindrome(String A, int n) {
		// 动态规划：i到j的子串是否是回文子串
		boolean[][] dp = new boolean[n][n];
		int max = 0;
		// 字符串长度差 c = j-i，即当前要比较的字符串长度，这里可以 c <= n / 2 + 1，减少判断次数
		for (int c = 0; c <= n + 1; c++) {
			// 起始下标，范围取决于要判断的字符串长度c
			// i 和 j 分别为要比较的字符串的左右边界指针
			for (int i = 0; i < n - c; i++) {
				// 终点下标
				int j = c + i;
				// 左右边界的字符相等
				if (A.charAt(i) == A.charAt(j)) {
					// c <= 1表示只有两个字符的字符串，一个或两个字符肯定是回文串
					if (c <= 1) {
						dp[i][j] = true;
					} else {
						// 对于两个字符以上的字符串
						// 因为最左右的字符已经相等，因此取决于内层的子串是否是回文子串
						dp[i][j] = dp[i + 1][j - 1];
					}
					// 更新回文串的最大长度，c代表判断的子串长度，越来越大
					if (dp[i][j]) {
						max = c + 1;
					}
				}
			}
		}
		return max;
	}

	//对于长度为n的一个字符串A（仅包含数字，大小写英文字母），请设计一个高效算法，计算其中最长回文子串的长度。
	public int getLongestPalindrome(String A) {
		int max = 0;
		int lengthA = A.length();

		for (int i = 0; i < lengthA;) {
			int begin = i;
			int end = i;
			while (end < lengthA - 1 && A.charAt(end) == A.charAt(end + 1)) {
				end++;
			}
			i = end + 1;
			while (begin > 0 && end < lengthA - 1 && A.charAt(begin - 1) == A.charAt(end + 1)) {
				begin--;
				end++;
			}
			max = Math.max(max, end - begin + 1);
		}

		return max;
	}

	/*
	给定一个长度为 n 的字符串，请编写一个函数判断该字符串是否回文。如果是回文请返回true，否则返回false。
	字符串回文指该字符串正序与其逆序逐字符一致。
	数据范围：0 < n \le 10000000<n≤1000000
	要求：空间复杂度 O(1)O(1)，时间复杂度 O(n)O(n)
	 */
	public boolean judgePalindrome(String str) {
		if (str.length() == 0)
			return true;
		//两个指针，一个从左边开始，一个从右边开始，每次两个
		//指针都同时往中间挪，只要两个指针指向的字符不一样就返回false
		int left = 0;
		int right = str.length() - 1;
		while (left < right) {
			if (str.charAt(left++) != str.charAt(right--))
				return false;
		}
		return true;
	}

	/*
	请写一个整数计算器，支持加减乘三种运算和括号。
	数据范围：0\le |s| \le 1000≤∣s∣≤100，保证计算结果始终在整型范围内
	要求：空间复杂度： O(n)O(n)，时间复杂度 O(n)O(n)
	示例  输入："1+2"
	 返回值：3
	 */

	public int expressionEvaluation(String s) {
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		map.put('-', 1);
		map.put('+', 1);
		map.put('*', 2);
		map.put('x', 2);
		map.put('/', 2);
		map.put('%', 2);
		map.put('^', 3);
		// 将所有的空格去掉
		s = s.replaceAll(" ", "");
		char[] cs = s.toCharArray();
		int n = s.length();
		// 存放所有的数字
		Deque<Integer> nums = new ArrayDeque<>();
		// 为了防止第一个数为负数，先往 nums 加个 0
		nums.addLast(0);
		// 存放所有「非数字以外」的操作
		Deque<Character> ops = new ArrayDeque<>();

		for (int i = 0; i < n; i++) {
			char c = cs[i];
			if (c == '(') {
				ops.addLast(c);
			} else if (c == ')') {
				// 计算到最近一个左括号为止
				while (!ops.isEmpty()) {
					if (ops.peekLast() != '(') {
						calc(nums, ops);
					} else {
						ops.pollLast();
						break;
					}
				}
			} else {
				if (isNumber(c)) {
					int u = 0;
					int j = i;
					// 将从 i 位置开始后面的连续数字整体取出，加入 nums
					while (j < n && isNumber(cs[j]))
						u = u * 10 + (cs[j++] - '0');
					nums.addLast(u);
					i = j - 1;
				} else {
					if (i > 0 && (cs[i - 1] == '(' || cs[i - 1] == '+' || cs[i - 1] == '-')) {
						nums.addLast(0);
					}
					// 有一个新操作要入栈时，先把栈内可以算的都算了
					// 只有满足「栈内运算符」比「当前运算符」优先级高/同等，才进行运算
					while (!ops.isEmpty() && ops.peekLast() != '(') {
						char prev = ops.peekLast();
						if (map.get(prev) >= map.get(c)) {
							calc(nums, ops);
						} else {
							break;
						}
					}
					ops.addLast(c);
				}
			}
		}
		// 将剩余的计算完
		while (!ops.isEmpty() && ops.peekLast() != '(')
			calc(nums, ops);
		return nums.peekLast();
	}

	// 计算逻辑：从 nums 中取出两个操作数，从 ops 中取出运算符，然后根据运算符进行计算即可
	void calc(Deque<Integer> nums, Deque<Character> ops) {
		if (nums.isEmpty() || nums.size() < 2)
			return;
		if (ops.isEmpty())
			return;
		int b = nums.pollLast(), a = nums.pollLast();
		char op = ops.pollLast();
		int ans = 0;
		switch (op) {
		case '+':
			ans = a + b;
			break;
		case '-':
			ans = a - b;
			break;
		case '*':
		case 'x':
			ans = a * b;
			break;
		case '/':
			ans = a / b;
			break;
		case '%':
			ans = a % b;
			break;
		case '^':
			ans = (int) Math.pow(a, b);
			break;
		default:
			break;
		}
		nums.addLast(ans);
	}

	boolean isNumber(char c) {
		return Character.isDigit(c);
	}

	public int expressionEvaluation2(String s) {
		s = s.trim();
		Deque<Integer> stack = new ArrayDeque<>();
		int number = 0;
		char sign = '+';
		char[] charArray = s.toCharArray();
		for (int i = 0, n = charArray.length; i < n; i++) {
			char c = charArray[i];
			if (c == ' ') {
				continue;
			}
			if (Character.isDigit(c)) {
				number = number * 10 + c - '0';
			}
			if (c == '(') {
				int j = i + 1;
				int counterPartition = 1;
				while (counterPartition > 0) {
					if (charArray[j] == '(') {
						counterPartition++;
					}
					if (charArray[j] == ')') {
						counterPartition--;
					}
					j++;
				}
				number = expressionEvaluation2(s.substring(i + 1, j - 1));
				i = j - 1;
			}
			if (!Character.isDigit(c) || i == n - 1) {
				if (sign == '+') {
					stack.push(number);
				} else if (sign == '-') {
					stack.push(-1 * number);
				} else if (sign == '*') {
					stack.push(stack.pop() * number);
				} else if (sign == '/') {
					stack.push(stack.pop() / number);
				}
				number = 0;
				sign = c;
			}
		}
		int ans = 0;
		while (!stack.isEmpty()) {
			ans += stack.pop();
		}
		return ans;
	}

	public int expressionEvaluation3(String s) {
		// 操作数
		Stack<Integer> opnd = new Stack<>();
		Stack<Character> optr = new Stack<>();
		// 结束符
		optr.push('#');
		s += "#";
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			// 当前字符是操作数，则入栈opnd
			if (c >= '0' && c <= '9') {
				if (i == 0) {
					// 第一位数
					int num = c - '0';
					opnd.push(num);
					continue;
				} else {
					// 判断前一个是否是操作数，即判断是否是两位数
					char pre = s.charAt(i - 1);
					if (pre >= '0' && pre <= '9') {
						int num = opnd.pop() * 10 + (c - '0');
						opnd.push(num);
					} else {
						int num = c - '0';
						opnd.push(num);
					}
					continue;
				}
			} else { // 当前字符是运算符
				// 处理特殊运算符
				if (c == '-' || c == '+' || c == '#') {
					// 处理负数
					if (c == '-' && (i == 0 || s.charAt(i - 1) == '(')) {
						opnd.push(0);
						optr.push('-');
						continue;
					}
					// 处理'+'的特殊情况
					if (c == '+' && (i == 0 || s.charAt(i - 1) == '(')) {
						continue;
					}
					// 如果当前运算符比optr栈顶运算符优先级低或者相同
					// 则opnd出栈两个操作数，optr出栈一个运算符，并将结果入栈opnd
					// 继续处理当前字符串c，直到找到optr栈顶不是+,-,*才将当前字符压入
					while (optr.peek() == '*' || optr.peek() == '+' || optr.peek() == '-') {
						int b = opnd.pop();
						int a = opnd.pop();
						char op = optr.pop();
						switch (op) {
						case '*':
							opnd.push(a * b);
							break;
						case '+':
							opnd.push(a + b);
							break;
						case '-':
							opnd.push(a - b);
							break;
						}
					}
					// 最后将该运算符入栈
					optr.push(c);
				}

				if (c == '(') {
					optr.push(c);
				}
				if (c == ')') {
					// optr栈内一般是从栈底到栈顶为优先级低到高，使用while循环
					while (optr.peek() != '(') {
						int b = opnd.pop();
						int a = opnd.pop();
						char op = optr.pop();
						switch (op) {
						case '*':
							opnd.push(a * b);
							break;
						case '+':
							opnd.push(a + b);
							break;
						case '-':
							opnd.push(a - b);
							break;
						}
					}
					// '('出栈
					optr.pop();
				}
				if (c == '*') {
					optr.push(c);
				}
			}
		}
		return opnd.peek();
	}

	/*NC97 字符串出现次数的TopK问题
	给定一个字符串数组，再给定整数 k ，请返回出现次数前k名的字符串和对应的次数。
	返回的答案应该按字符串出现频率由高到低排序。如果不同的字符串有相同出现频率，按字典序排序。
	对于两个字符串，大小关系取决于两个字符串从左到右第一个不同字符的 ASCII 值的大小关系。
	比如"ah1x"小于"ahb"，"231"<”32“
	字符仅包含数字和字母
	 */
	/**
	 * return topK string
	 * 
	 * @param strings string字符串一维数组 strings
	 * @param k       int整型 the k
	 * @return string字符串二维数组
	 */
	public String[][] topKstrings(String[] strings, int k) {
		// write code here
		if (k == 0) {
			return new String[][] {};
		}
		String[][] res = new String[k][2];
		TreeMap<String, Integer> map = new TreeMap<>();
		// 统计各个字符串出现的次数
		for (int i = 0; i < strings.length; ++i) {
			String s = strings[i];
			map.merge(s, 1, (o1, o2) -> o1 + o2);
		}

		ArrayList<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());
		// 先是按出现次数降序比较，相同则再按照字符ASCII码降序比较
		Collections.sort(list,
				(o1, o2) -> (o1.getValue().compareTo(o2.getValue()) == 0 ? o1.getKey().compareTo(o2.getKey())
						: o2.getValue().compareTo(o1.getValue())));
		// 返回topK
		for (int i = 0; i < k; i++) {
			res[i][0] = list.get(i).getKey();
			res[i][1] = String.valueOf(list.get(i).getValue());
		}
		return res;
	}

	/*NC196 编辑距离(一)
	描述
	给定两个字符串 str1 和 str2 ，请你算出将 str1 转为 str2 的最少操作数。
	你可以对字符串进行3种操作：
	1.插入一个字符
	2.删除一个字符
	3.修改一个字符。*/
	public int editDistance(String str1, String str2) {
		// write code here
		int len1 = str1.length();
		int len2 = str2.length();
		if (len1 == 0)
			return len2;
		if (len2 == 0)
			return len1;
		int[][] dp = new int[len1 + 1][len2 + 1];
		for (int i = 0; i <= len1; i++) //str2 长度为0
			dp[i][0] = i;
		for (int i = 0; i <= len2; i++) //str1 长度为0
			dp[0][i] = i;
		for (int i = 1; i <= len1; i++) {
			for (int j = 1; j <= len2; j++) {
				if (str1.charAt(i - 1) == str2.charAt(j - 1))
					dp[i][j] = dp[i - 1][j - 1];
				else {
					dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
				}
			}
		}
		return dp[len1][len2];
	}

	/**
	 * 编辑距离(二) 给定两个字符串str1和str2，再给定三个整数ic，dc和rc，
	 * 分别代表插入、删除和替换一个字符的代价，请输出将str1编辑成str2的最小代价。 min edit cost
	 * 
	 * @param str1 string字符串 the string
	 * @param str2 string字符串 the string
	 * @param ic   int整型 insert cost
	 * @param dc   int整型 delete cost
	 * @param rc   int整型 replace cost
	 * @return int整型
	 */
	public int minEditCost(String str1, String str2, int ic, int dc, int rc) {
		// write code here
		int[] res = new int[str2.length() + 1];
		int i, j, minres = Math.min(ic + dc, rc), leftTop, t;
		char[] c1 = str1.toCharArray(), c2 = str2.toCharArray();
		res[0] = 0;
		for (i = 1; i <= c2.length; i++) {
			res[i] = i * ic;
		}
		for (i = 1; i <= c1.length; i++) {
			leftTop = res[0];
			res[0] = i * dc;
			for (j = 1; j <= c2.length; j++) {
				t = res[j];
				if (c1[i - 1] == c2[j - 1]) {
					res[j] = leftTop;
				} else {
					res[j] = Math.min(Math.min(res[j] + dc, res[j - 1] + ic), leftTop + minres);
				}
				leftTop = t;
			}
		}
		return res[c2.length];
	}

	/**
	 * 给定两个字符串str1和str2，输出两个字符串的最长公共子序列。 如果最长公共子序列为空，则返回"-1"。目前给出的数据，仅仅会存在一个最长的公共子序列
	 * 数据范围：0 \le |str1|,|str2| \le 20000≤∣str1∣,∣str2∣≤2000
	 * 
	 * 示例1输入： "1A2C3D4B56","B1D23A456A" 返回值："123456" longest common subsequence
	 * 
	 * @param s1 string字符串 the string
	 * @param s2 string字符串 the string
	 * @return string字符串
	 */
	public String LCS(String s1, String s2) {
		int len1 = s1.length();
		int len2 = s2.length();
		if (len1 == 0 || len2 == 0)
			return "-1";
		int[][] dp = new int[len1 + 1][len2 + 1];
		for (int i = 0; i < len1 + 1; i++) {
			for (int j = 0; j < len2 + 1; j++) {
				//初始化行列第一个元素
				if (i == 0 || j == 0) {
					dp[i][j] = 0;
					continue;
				}
				if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
					dp[i][j] = dp[i - 1][j - 1] + 1;
				} else {
					dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
				}
			}
		}
		//找出一个最长的公共子序列
		StringBuilder sb = new StringBuilder();
		int s1L = len1, s2L = len2;
		while (s1L != 0 && s2L != 0) {
			if (s1.charAt(s1L - 1) == s2.charAt(s2L - 1)) {
				sb.append(s1.charAt(s1L - 1));
				s1L--;
				s2L--;
			} else {
				if (dp[s1L - 1][s2L] > dp[s1L][s2L - 1]) {
					s1L--;
				} else {
					s2L--;
				}
			}
		}
		if (sb.length() == 0)
			return "-1";
		return sb.reverse().toString();
	}

	/*
	写一个函数 StrToInt，实现把字符串转换成整数这个功能。不能使用 atoi 或者其他类似的库函数。传入的字符串可能有以下部分组成:
	1.若干空格
	2.（可选）一个符号字符（'+' 或 '-'）
	3. 数字，字母，符号，空格组成的字符串表达式
	4. 若干空格
	
	转换算法如下:
	1.去掉无用的前导空格
	2.第一个非空字符为+或者-号时，作为该整数的正负号，如果没有符号，默认为正数
	3.判断整数的有效部分：
	3.1 确定符号位之后，与之后面尽可能多的连续数字组合起来成为有效整数数字，如果没有有效的整数部分，那么直接返回0
	3.2 将字符串前面的整数部分取出，后面可能会存在存在多余的字符(字母，符号，空格等)，这些字符可以被忽略，它们对于函数不应该造成影响
	3.3  整数超过 32 位有符号整数范围 [−231,  231 − 1] ，需要截断这个整数，使其保持在这个范围内。具体来说，小于 −231的整数应该被调整为 −231 ，大于 231 − 1 的整数应该被调整为 231 − 1
	4.去掉无用的后导空格
	数据范围:
	1.0 <=字符串长度<= 100
	2.字符串由英文字母（大写和小写）、数字（0-9）、' '、'+'、'-' 和 '.' 组成
	 */
	public int StrToInt(String s) {

		// write code here
		int res = 0, val = Integer.MAX_VALUE / 10;
		int i = 0, sign = 1, len = s.length();
		if (len == 0) {//字符串为空则直接返回
			return 0;
		}

		while (s.charAt(i) == ' ') {
			if (++i == len) {//删除首尾空格
				return 0;
			}
		}

		if (s.charAt(i) == '-') {//保存负号
			sign = -1;
		}
		if (s.charAt(i) == '-' || s.charAt(i) == '+') {//若无符号位，则需从 i = 0 开始数字拼接
			i++;
		}
		for (int j = i; j < len; j++) {
			if (s.charAt(j) < '0' || s.charAt(j) > '9') {//遇到非数字的字符则跳出
				break;
			}
			if (res > val || res == val && s.charAt(j) > Integer.MAX_VALUE % 10 + '0') {//s.charAt(j) > '7'
				return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;//数字越界处理
			}
			res = res * 10 + (s.charAt(j) - '0');//数字拼接
		}
		return sign * res;
	}

	/*现在有一个只包含数字的字符串，将该字符串转化成IP地址的形式，返回所有可能的情况。
	例如：
	给出的字符串为"25525522135",
	返回["255.255.22.135", "255.255.221.35"]. (顺序没有关系)*/
	public ArrayList<String> restoreIpAddresses(String s) {
		// write code here
		ArrayList<String> resultList = new ArrayList<>();
		if (s == null || s.length() < 4) {
			return resultList;
		}
		f(s, 1, new StringBuffer(), resultList);
		return resultList;

	}

	public static void f(String s, int deep, StringBuffer stringBuffer, ArrayList<String> resultArrayList) {
		if (deep > 3) {
			//出口
			if ("".equals(s)) {
				return;
			}
			if (s.length() > 1 && s.startsWith("0")) {
				return;
			}
			int val = Integer.valueOf(s);
			if (val >= 0 && val < 256) {
				stringBuffer.append(s);
				resultArrayList.add(stringBuffer.toString());
			}
		} else {
			for (int i = 1; i <= 3 && i <= s.length(); i++) {
				String curr = s.substring(0, i);
				if (curr.length() > 1 && curr.startsWith("0")) {
					continue;
				}
				String next = s.substring(i);
				int val = Integer.valueOf(curr);
				if (val >= 0 && val < 256) {
					StringBuffer sb = new StringBuffer(stringBuffer);
					sb.append(curr).append(".");
					f(next, deep + 1, sb, resultArrayList);
				}
			}
		}
	}

	/*
	NC49 最长的括号子串
	给出一个长度为 n 的，仅包含字符 '(' 和 ')' 的字符串，计算最长的格式正确的括号子串的长度。
	例1: 对于字符串 "(()" 来说，最长的格式正确的子串是 "()" ，长度为 2 .
	例2：对于字符串 ")()())" , 来说, 最长的格式正确的子串是 "()()" ，长度为 4 .
	 */
	public int longestValidParentheses(String s) {
		// write code here
		int maxans = 0;
		int[] dp = new int[s.length()];
		for (int i = 1; i < s.length(); i++) {
			if (s.charAt(i) == ')') {
				if (s.charAt(i - 1) == '(') {
					dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
				} else if (i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
					dp[i] = dp[i - 1] + ((i - dp[i - 1]) >= 2 ? dp[i - dp[i - 1] - 2] : 0) + 2;
				}
				maxans = Math.max(maxans, dp[i]);
			}
		}
		return maxans;
	}

	/*
	 给你一个大小为 n 的字符串数组 strs ，其中包含n个字符串 ,
	  编写一个函数来查找字符串数组中的最长公共前缀，返回这个公共前缀。
	 */
	public String longestCommonPrefix(String[] strs) {
		// //纵向扫描
		if (strs.length == 0 || strs == null) {
			return "";
		}

		int rows = strs.length;
		int cols = strs[0].length();
		//开始扫描
		for (int i = 0; i < cols; i++) {
			char currentChar = strs[0].charAt(i);
			for (int j = 1; j < rows; j++) {
				if (strs[j].length() == i || strs[j].charAt(i) != currentChar) {
					return strs[0].substring(0, i);
				}
			}
		}
		return strs[0];
	}

	/*
	对于一个长度为 n 字符串，我们需要对它做一些变形。
	首先这个字符串中包含着一些空格，就像"Hello World"一样，
	然后我们要做的是把这个字符串中由空格隔开的单词反序，同时反转每个字符的大小写。
	比如"Hello World"变形后就变成了"wORLD hELLO"。
	 */
	public String trans(String s, int n) {
		StringBuffer str = new StringBuffer();
		int start = 0;
		for (int i = n - 1; i >= 0; i--) {
			if (s.charAt(i) == ' ') {
				str.append(" ");
				start = n - i;
			} else {

				char sss = upLowChar(s.charAt(i));
				//char sss = (char) (s.charAt(i) < 97 ? s.charAt(i) + 32 : s.charAt(i) - 32);
				str.insert(start, sss);
			}
		}
		return str.toString();
	}

	private Character upLowChar(Character c) {
		if (Character.isLowerCase(c)) {
			return Character.toUpperCase(c);
		}
		return Character.toLowerCase(c);
	}

	/*验证IP地址
	编写一个函数来验证输入的字符串是否是有效的 IPv4 或 IPv6 地址
	IPv4 地址由十进制数和点来表示，每个地址包含4个十进制数，其范围为 0 - 255， 用(".")分割。比如，172.16.254.1；
	同时，IPv4 地址内的数不会以 0 开头。比如，地址 172.16.254.01 是不合法的。
	IPv6 地址由8组16进制的数字来表示，每组表示 16 比特。这些组数字通过 (":")分割。比如,  2001:0db8:85a3:0000:0000:8a2e:0370:7334 是一个有效的地址。而且，我们可以加入一些以 0 开头的数字，字母可以使用大写，也可以是小写。所以， 2001:db8:85a3:0:0:8A2E:0370:7334 也是一个有效的 IPv6 address地址 (即，忽略 0 开头，忽略大小写)。
	然而，我们不能因为某个组的值为 0，而使用一个空的组，以至于出现 (::) 的情况。 比如， 2001:0db8:85a3::8A2E:0370:7334 是无效的 IPv6 地址。
	同时，在 IPv6 地址中，多余的 0 也是不被允许的。比如， 02001:0db8:85a3:0000:0000:8a2e:0370:7334 是无效的。
	说明: 你可以认为给定的字符串里没有空格或者其他特殊字符。*/
	public String validIPAddress(String IP) {
		return validIPv4(IP) ? "IPv4" : (validIPv6(IP) ? "IPv6" : "Neither");
	}

	private boolean validIPv4(String IP) {
		String[] strs = IP.split("\\.", -1);
		if (strs.length != 4) {
			return false;
		}

		for (String str : strs) {
			if (str.length() > 1 && str.startsWith("0")) {
				return false;
			}
			try {
				int val = Integer.parseInt(str);
				if (!(val >= 0 && val <= 255)) {
					return false;
				}
			} catch (NumberFormatException numberFormatException) {
				return false;
			}
		}
		return true;
	}

	private boolean validIPv6(String IP) {
		String[] strs = IP.split(":", -1);
		if (strs.length != 8) {
			return false;
		}

		for (String str : strs) {
			if (str.length() > 4 || str.length() == 0) {
				return false;
			}
			try {
				int val = Integer.parseInt(str, 16);
			} catch (NumberFormatException numberFormatException) {
				return false;
			}
		}
		return true;
	}

	/*正则表达式匹配
	请实现一个函数用来匹配包括'.'和'*'的正则表达式。模式中的字符'.'
	表示任意一个字符，而'*'表示它前面的字符可以出现任意次（包含0次）。 
	在本题中，匹配是指字符串的所有字符匹配整个模式。例如，字符串"aaa"与模式"a.a"和"ab*ac*a"匹配，但是与"aa.a"和"ab*a"均不匹配*/
	public boolean match(String str, String pattern) {
		int n = str.length();
		int m = pattern.length();
		boolean[][] f = new boolean[n + 1][m + 1];

		for (int i = 0; i <= n; i++) {
			for (int j = 0; j <= m; j++) {
				//分成空正则和非空正则两种
				if (j == 0) {
					f[i][j] = i == 0;
				} else {
					//非空正则分为两种情况 * 和 非*
					if (pattern.charAt(j - 1) != '*') {
						if (i > 0 && (str.charAt(i - 1) == pattern.charAt(j - 1) || pattern.charAt(j - 1) == '.')) {
							f[i][j] = f[i - 1][j - 1];
						}
					} else {
						//碰到 * 了，分为看和不看两种情况
						//不看
						if (j >= 2) {
							f[i][j] |= f[i][j - 2];
						}
						//看
						if (i >= 1 && j >= 2
								&& (str.charAt(i - 1) == pattern.charAt(j - 2) || pattern.charAt(j - 2) == '.')) {
							f[i][j] |= f[i - 1][j];
						}
					}
				}
			}
		}
		return f[n][m];
	}

	public boolean match2(String str, String pattern) {
		return Pattern.matches(pattern, str);
	}

	/*请实现支持'?'and'*'.的通配符模式匹配
	'?' 可以匹配任何单个字符。
	'*' 可以匹配任何字符序列（包括空序列）。
	下面给出一些样例：
	isMatch("aa","a") → false
	isMatch("aa","aa") → true
	isMatch("aaa","aa") → false
	isMatch("aa", "*") → true
	isMatch("aa", "a*") → true
	isMatch("ab", "?*") → true
	isMatch("aab", "d*a*b") → false*/
	public boolean isMatch(String s, String p) {
		if (p == null || p.isEmpty())
			return s == null || s.isEmpty();
		int slen = s.length(), plen = p.length();
		boolean[][] dp = new boolean[slen + 1][plen + 1];
		//初始化dp数组,dp[1][0]~dp[s.length][0]默认值flase不需要显式初始化为false
		dp[0][0] = true;
		//dp[0][1]~dp[0][p.length]只有p的j字符以及前面所有字符都为'*'才为true
		for (int j = 1; j <= plen; j++)
			dp[0][j] = p.charAt(j - 1) == '*' && dp[0][j - 1];
		//填写dp数组剩余部分
		for (int i = 1; i <= slen; i++) {
			for (int j = 1; j <= plen; j++) {
				char si = s.charAt(i - 1), pj = p.charAt(j - 1);
				if (si == pj || pj == '?') {
					dp[i][j] = dp[i - 1][j - 1];
				} else if (pj == '*') {
					dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
				}
			}
		}
		return dp[slen][plen];
	}

	//未通过
	public boolean isMatch02(String s, String p) {
		if (p == null || p.isEmpty())
			return s == null || s.isEmpty();
		int slen = s.length(), plen = p.length();
		if (p.equals("?")) {
			if (slen > 1) {
				return true;
			}
		}
		// 创建 Pattern 对象
		Pattern r = Pattern.compile(p);
		// 现在创建 matcher 对象
		Matcher m = r.matcher(s);
		return m.find();
	}

	/*把数字翻译成字符串
	有一种将字母编码成数字的方式：'a'->1, 'b->2', ... , 'z->26'。
	现在给一串数字，返回有多少种可能的译码结果
	输入："31717126241541717"
	返回值：192
	说明：192种可能的译码结果  */
	public int translateNumbersIntoStrings(String nums) {
		if (nums.length() == 0 || nums.charAt(0) == '0')
			return 0;
		int[] dp = new int[nums.length()];
		dp[0] = 1;
		for (int i = 1; i < dp.length; i++) {
			if (nums.charAt(i) != '0') {
				dp[i] = dp[i - 1];
			}
			//  3 2 4
			int num = (nums.charAt(i - 1) - '0') * 10 + (nums.charAt(i) - '0');
			if (num >= 10 && num <= 26) {
				if (i == 1) {
					dp[i] += 1;
				} else {
					dp[i] += dp[i - 2];
				}
			}
		}
		return dp[nums.length() - 1];
	}

	public int translateNumbersIntoStrings01(String nums) {
		return back(nums.toCharArray(), 0);
	}

	// 递归函数
	public int back(char[] nums, int start) {
		//当start走到终点时，证明已经翻译完毕，直接返回1
		if (start == nums.length) {
			return 1;
		}
		//当字符为0的时候，0没对应的翻译，所以直接返回0 (此路翻译废掉)
		if (nums[start] == '0')
			return 0;
		//每次翻译一个字符
		int res1 = back(nums, start + 1);
		int res2 = 0;

		//如果当前字符等于1 或者 当前字符加上下一个字符合起来小于等于26 则可以一次翻译两个字符
		if ((start < nums.length - 1) && (nums[start] == '1' || (nums[start] == '2' && nums[start + 1] <= '6'))) {
			res2 = back(nums, start + 2);
		}
		//返回结果
		return res1 + res2;
	}

	/*最长重复子串
	定义重复字符串是由两个相同的字符串首尾拼接而成，例如 abcabcabcabc 便是长度为6的一个重复字符串，而 abcbaabcba 则不存在重复字符串。
	给定一个字符串，请返回其最长重复子串的长度。
	若不存在任何重复字符子串，则返回 0 。
	本题中子串的定义是字符串中一段连续的区间。*/
	public int maxLengthDuplicateSubList(String a) {
		// write code here
		char[] s = a.toCharArray();
		for (int len = s.length >> 1; len > 0; --len) {
			int cnt = 0;
			for (int i = 0; i + len < s.length; ++i) {
				if (s[i] == s[i + len]) {
					if (len == ++cnt) {
						return len << 1;
					}
				} else {
					cnt = 0;
				}
			}
		}
		return 0;
	}

	public int maxLengthDuplicateSubList02(String a) {
		// write code here
		if (a == null || a.length() <= 1)
			return 0;
		char[] chars = a.toCharArray();
		int len = chars.length;
		int maxLen = chars.length / 2;
		for (int i = maxLen; i >= 1; --i) {
			for (int j = 0; j <= len - 2 * i; ++j) {
				if (check(chars, j, i))
					return i << 1;
			}
		}
		return 0;
	}

	public boolean check(char[] chars, int start, int len) {
		for (int i = start; i < start + len; ++i) {
			if (chars[i] != chars[i + len])
				return false;
		}
		return true;
	}

	/*
	第一个只出现一次的字符:
	在一个字符串中找到第一个只出现一次的字符,并返回它的位置, 如果没有则返回 -1（需要区分大小写）.（从0开始计数）
	输入："google"
	返回值：4*/
	public int firstNotRepeatingChar(String str) {
		HashMap<Character, Boolean> map = new HashMap<>();
		char[] chars = str.toCharArray();
		// 初始化哈希表，重复出现的置为false，否则为true
		for (char c : chars) {
			if (map.containsKey(c)) {
				map.put(c, false);
			} else {
				map.put(c, true);
			}
		}
		// 二次遍历字符数组，若其在哈希表中的value为true，则返回下标
		for (int i = 0; i < chars.length; i++) {
			if (map.get(chars[i])) {
				return i;
			}
		}
		// 没有符合条件的则返回-1
		return -1;
	}

	/*kmp算法
	给你一个文本串 T ，一个非空模板串 S ，问 S 在 T 中出现了多少次*/
	public int kmp(String S, String T) {
		//特殊情况判断
		int m = S.length(), n = T.length();
		if (m > n || n == 0)
			return 0;

		//初始化计数，获取next数组
		int cnt = 0;
		int[] next = getNext(S);

		//遍历主串和模式串
		for (int i = 0, j = 0; i < n; i++) {
			//只要不相等，回退到next数组记录的下一位
			while (j > 0 && T.charAt(i) != S.charAt(j)) {
				j = next[j - 1];
			}
			if (T.charAt(i) == S.charAt(j))
				j++;
			//如果j为m，说明完全匹配一次
			if (j == m) {
				//计数加一，索引回退到next数组记录的下一位
				cnt++;
				j = next[j - 1];
			}
		}
		return cnt;
	}

	//确定next数组
	private int[] getNext(String S) {
		int m = S.length();
		int[] next = new int[m];
		for (int i = 1, j = 0; i < m; i++) {
			//只要不相等，回退到next数组记录的下一位
			while (j > 0 && S.charAt(i) != S.charAt(j)) {
				j = next[j - 1];
			}
			//前缀索引后移
			if (S.charAt(i) == S.charAt(j))
				j++;
			//确定应该回退到的下一个索引
			next[i] = j;
		}
		return next;
	}

	/* 旋转字符串
		给定两字符串A和B，如果能将A从中间某个位置分割为左右两部分字符串（可以为空串），并将左边的字符串移动到右边字符串后面组成新的字符串可以变为字符串B时返回true。
		例如：如果A=‘youzan’，B=‘zanyou’，A按‘you’‘zan’切割换位后得到‘zanyou’和B相同，返回true。
		再如：如果A=‘abcd’，B=‘abcd’，A切成‘abcd’和''（空串），换位后可以得到B，返回true。
		输入："youzan","zanyou"
		返回值：true*/
	public boolean solve(String A, String B) {
		//特殊情况处理
		if (A == null || B == null || A.length() < 2 || B.length() < 2 || A.length() != B.length()) {
			return false;
		}
		//日   这么巧妙
		//youzanyouzan//
		return (A + A).contains(B);
	}

	/*重复的DNA序列
	所有的 DNA 序列都是由 'A' , ‘C’ , 'G' , 'T' 字符串组成的，例如 'ACTGGGC' 。
	请你实现一个函数找出所有的目标子串，目标连续子串的定义是，长度等于 10 ，
	且在 DNA 序列中出现次数超过 1 次的连续子串（允许两个连续子串有重合的部分，如下面的示例2所示）。
	（注：返回的所有目标子串的顺序必须与原DNA序列的顺序一致，如下面的示例1所示）*/
	public String[] repeatedDNA(String DNA) {
		// write code here
		if (DNA == null || DNA.length() <= 10) {
			return new String[] { DNA };
		}
		int len = DNA.length();
		HashMap<String, Integer> map = new HashMap<>();
		TreeMap<Integer, String> dupMap = new TreeMap<>();
		String subStr;
		for (int i = 0; i <= len - 10; i++) {
			subStr = DNA.substring(i, i + 10);
			if (map.containsKey(subStr)) {
				if (!dupMap.containsKey(map.get(subStr))) {
					dupMap.put(map.get(subStr), subStr);
				}
			} else {
				map.put(subStr, i);
			}
		}
		String[] ans = new String[dupMap.size()];
		int index = 0;
		for (Map.Entry<Integer, String> entry : dupMap.entrySet()) {
			ans[index++] = entry.getValue();
		}
		return ans;
	}

	/*NC228 判断子序列
	给定两个字符串 S 和 T ，判断 S 是否是 T 的子序列。
	即是否可以从 T 删除一些字符转换成 S。*/
	public boolean isSubsequence(String S, String T) {
		if (S == null || T == null || S.length() > T.length()) {
			return false;
		}
		int s = 0, t = 0;
		while (t < T.length()) {
			if (S.charAt(s) == T.charAt(t)) {
				s++;
				if (s >= S.length()) {
					return true;
				}
			}
			t++;
		}
		return false;
	}

	/*判断字符是否唯一
	给定一个字符串，请你判断其中每个字符是否全都不同。*/
	public boolean isUnique(String str) {
		long cur = 0L;
		long accumunation = 0L;
		for (int i = 0; i < str.length(); i++) {
			cur = 1L << (str.charAt(i) - 'A');
			if ((accumunation & cur) != 0) {
				return false;
			}
			accumunation |= cur;
		}
		return true;
	}

	public boolean isUnique02(String str) {
		// write code here
		Map<Character, Integer> map = new HashMap<>();
		for (int i = 0; i < str.length(); i++) {
			if (map.containsKey(str.charAt(i)))
				return false;
			map.put(str.charAt(i), 1);
		}
		return true;
	}

	/*最长公共子序列(一)
	给定两个字符串 s1 和 s2，长度为m和n 。求两个字符串最长公共子序列的长度。
	所谓子序列，指一个字符串删掉部分字符（也可以不删）形成的字符串。例如：字符串 "arcaea" 的子序列有 "ara" 、 "rcaa" 等。但 "car" 、 "aaae" 则不是它的子序列。
	所谓 s1 和 s2 的最长公共子序列，即一个最长的字符串，它既是 s1 的子序列，也是 s2 的子序列。*/
	public int LCS01(String s1, String s2) {
		// write code here
		int mLength = s1.length();
		int nLength = s2.length();
		int[][] dp = new int[mLength + 1][nLength + 1];
		char[] c1 = s1.toCharArray();
		char[] c2 = s2.toCharArray();
		for (int i = 0; i <= mLength; i++) {
			dp[i][0] = 0;
		}
		for (int j = 0; j <= nLength; j++) {
			dp[0][j] = 0;
		}
		for (int i = 1; i <= mLength; i++) {
			for (int j = 1; j <= nLength; j++) {
				if (c1[i - 1] != c2[j - 1]) {
					dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
				} else {
					dp[i][j] = dp[i - 1][j - 1] + 1;
				}
			}
		}
		return dp[mLength][nLength];
	}

	/*NC92 最长公共子序列(二)
	给定两个字符串str1和str2，输出两个字符串的最长公共子序列。
	如果最长公共子序列为空，则返回"-1"。目前给出的数据，仅仅会存在一个最长的公共子序列*/
	public String LCS02(String s1, String s2) {
		// write code here
		if (s1 == null || s2 == null || s1.length() == 0 || s2.length() == 0) {
			return "-1";
		}
		char[] char1 = s1.toCharArray();
		char[] char2 = s2.toCharArray();
		int[][] dp = getDp(char1, char2);
		int m = char1.length - 1;
		int n = char2.length - 1;
		char[] res = new char[dp[m][n]];
		int index = res.length - 1;

		while (index >= 0) {
			if (n > 0 && dp[m][n] == dp[m][n - 1]) {
				n--;
			} else if (m > 0 && dp[m - 1][n] == dp[m][n]) {
				m--;
			} else {
				res[index--] = char1[m];
				m--;
				n--;
			}
		}
		String resStr = String.valueOf(res);
		return resStr.equals("") ? "-1" : resStr;
	}

	public int[][] getDp(char[] char1, char[] char2) {
		int[][] dp = new int[char1.length][char2.length];

		dp[0][0] = char1[0] == char2[0] ? 1 : 0;

		for (int i = 1; i < char1.length; i++) {
			dp[i][0] = Math.max(dp[i - 1][0], char1[i] == char2[0] ? 1 : 0);
		}
		for (int i = 1; i < char2.length; i++) {
			dp[0][i] = Math.max(dp[0][i - 1], char1[0] == char2[i] ? 1 : 0);
		}

		for (int i = 1; i < char1.length; i++) {
			for (int j = 1; j < char2.length; j++) {
				int max = Math.max(dp[i - 1][j], dp[i][j - 1]);
				if (char1[i] == char2[j]) {
					max = Math.max(dp[i - 1][j - 1] + 1, max);
				}
				dp[i][j] = max;
			}
		}
		return dp;
	}

	/*最长不含重复字符的子字符串
	请从字符串中找出一个最长的不包含重复字符的子字符串，计算该最长子字符串的长度。
	数据范围:text{s.length}\le 40000 s.length≤40000*/
	public int lengthOfLongestSubstring(String s) {
		// write code here
		if (s == null)
			return 0;
		if (s.length() == 1)
			return 1;
		int maxLength = 0;
		int curLength = 0;
		int[] positions = new int[100];
		// 字符位置初始化为-1，负数表示没出现过
		Arrays.fill(positions, -1);

		for (int i = 0; i < s.length(); i++) {
			int curChar = s.charAt(i) - ' ';
			int prePosition = positions[curChar];
			//当前字符与它上次出现位置之间的距离
			int distance = i - prePosition;
			//当前字符第一次出现，或者前一个非重复子字符串中没有包含当前字符
			if (prePosition < 0 || distance > curLength) {
				curLength++;
			} else {
				//更新最长非重复字符串长度
				if (curLength > maxLength) {
					maxLength = curLength;
				}
				curLength = distance;
			}
			//更新字符出现的位置
			positions[curChar] = i;
		}
		if (curLength > maxLength) {
			maxLength = curLength;
		}
		return maxLength;
	}

	/*压缩字符串(一)
	利用字符重复出现的次数，编写一种方法，实现基本的字符串压缩功能。比如，字符串aabcccccaaa会变为a2bc5a3。
	1.如果只有一个字符，1不用写
	2.字符串中只包含大小写英文字母（a至z）。*/
	public String compressString(String param) {
		//先把字符串转换为字符串数组
		if (param.length() == 0) {
			return "";
		}
		char[] c = param.toCharArray();
		StringBuilder result = new StringBuilder();//存放结果
		int count = 1;
		for (int i = 0; i < c.length - 1; i++) {
			if (c[i] == c[i + 1]) {
				count++;
			} else {
				result.append(c[i]);
				if (count > 1) {
					result.append(count);
				}
				count = 1;//将count重新置为1
			}
		}
		//字符串中最后一个字符
		result.append(c[c.length - 1]);
		if (count > 1) {
			result.append(count);
		}
		return result.toString();

	}

	/*NC187 压缩字符串(二)
	利用字符重复出现的次数，编写一种方法，最多可以先删掉k个字符，
	再实现字符串压缩，返回压缩过后字符串的最小长度。比如，字符串aabcccccaaa，
	k=0时，会压缩变为a2bc5a3，返回7。
	1.如果只有一个字符，1不用写
	2.新增一个先删除k个字符的处理，也可以不删除，也可以删除少于k个字符，要达到压缩过后字符串的长度为最小
	3.字符串中只包含大小写英文字母（a至z)
	
	数据范围:
	0<=字符串长度<=100
	0<=k<=字符串长度*/
	public int compressString02(String s, int k) {
		// write code here
		int n = s.length();
		int[][] f = new int[n + 1][k + 1];
		for (int i = 0; i <= n; i++) {
			Arrays.fill(f[i], Integer.MAX_VALUE >> 1);
		}
		f[0][0] = 0;
		for (int i = 1; i <= n; ++i) {
			for (int j = 0; j <= k && j <= i; ++j) {
				if (j > 0) {
					f[i][j] = f[i - 1][j - 1];
				}
				int same = 0, diff = 0;
				for (int i0 = i; i0 >= 1 && diff <= j; --i0) {
					if (s.charAt(i0 - 1) == s.charAt(i - 1)) {
						++same;
						f[i][j] = Math.min(f[i][j], f[i0 - 1][j - diff] + calc(same));
					} else {
						++diff;
					}
				}
			}
		}
		return f[n][k];
	}

	private int calc(int x) {
		if (x == 1) {
			return 1;
		}
		if (x < 10) {
			return 2;
		}
		if (x < 100) {
			return 3;
		}
		return 4;
	}

	/*NC67 汉诺塔问题
	我们有由底至上为从大到小放置的 n 个圆盘，和三个柱子（分别为左/中/右即left/mid/right），
	开始时所有圆盘都放在左边的柱子上，按照汉诺塔游戏的要求我们要把所有的圆盘都移到右边的柱子上，要求一次只能移动一个圆盘，
	而且大的圆盘不可以放到小的上面。
	请实现一个函数打印最优移动轨迹。
	给定一个 `int n` ，表示有 n 个圆盘。请返回一个 `string` 数组，
	其中的元素依次为每次移动的描述。描述格式为： `move from [left/mid/right] to [left/mid/right]`。*/
	ArrayList<String> ans = new ArrayList<>();

	public ArrayList<String> getSolution(int n) {
		// write code here
		Hanoi(n, "left", "mid", "right");
		return ans;
	}

	private void Hanoi(int n, String Left, String Mid, String Right) {
		if (n == 0)
			return;
		Hanoi(n - 1, Left, Right, Mid);
		String t = "move from " + Left + " to " + Right;
		ans.add(t);
		Hanoi(n - 1, Mid, Left, Right);

	}

	/*NC172 二进制取反
	有一个二进制字符串numnum，可以选择该串中的任意一段区间进行取反(可以进行一次或不进行)，
	取反指将00变为11，将11变为00。那么取反之后的numnum可能的最大的字典序是多少呢*/
	public String maxLexicographical(String num) {
		int l = -1, r = num.length() - 1;
		//第一步把我们的左边界变成-1，右边界变成最后那个单词
		for (int i = 0; i < num.length(); i++) {
			//遍历我们的字符产
			if (num.charAt(i) == '0' && l == -1)
				l = i;
			//如果找到0，并且左边界未确定，这时候确定左边界
			if (num.charAt(i) == '1' && l != -1) {
				//找到1，并且左边界确定了，那么更新有边界跳出循环
				r = i;
				break;
			}
		}
		if (l == -1)
			return num;
		//如果已经是全1的状态，直接返回原来的字符串就可以了
		StringBuffer res = new StringBuffer();
		for (int i = l; i <= r; i++)
			res.append('1');
		//把这段区间内的1加进来
		return num.substring(0, l) + res.toString() + num.substring(r + 1, num.length());
		//执行一个字符串的拼接操作
	}

	/*NC174 最大值
	有一个只由字符'1'到'9'组成的长度为 nn 的字符串 ss ，
	现在可以截取其中一段长度为 kk 的子串并且将该子串当作十进制的正整数，
	如对于子串"123"，其对应的十进制数字就是123123 。
	如果想让这个正整数尽可能的大的话，问该正整数最大能是多少。
	函数传入一个长度为 nn 的字符串 ss 和一个正整数 kk ，请你返回答案。*/
	public int maxValue(String s, int k) {
		if (k > s.length())
			return -1;
		int cl = 1;
		int num = 0;
		int max = 0;
		for (int i = 0; i < k; i++) {
			num = num * 10 + s.charAt(i) - '0';
			cl *= 10;
		}
		max = num;
		cl /= 10;
		for (int i = k; i < s.length(); i++) {
			num %= cl;
			num = num * 10 + s.charAt(i) - '0';
			max = num > max ? num : max;
		}
		return max;
	}

	/*NC175 合法的括号字符串
	给定一个字符串s，字符串s只包含以下三种字符: (，*，)，请你判断 s是不是一个合法的括号字符串。合法括号字符串有如下规则:
		1.左括号'('必须有对应的右括号')'
		2.右括号')'必须有对应的左括号'('
		3.左括号必须在对应的右括号前面
		4.*可以视为单个左括号，也可以视为单个右括号，或者视为一个空字符
		5.空字符串也视为合法的括号字符串*/

	public boolean isValidString(String s) {
		// write code here
		int cnt = 0, n = s.length();
		for (int i = 0; i < n; i++) {
			if (s.charAt(i) == ')') {
				if (cnt < 1)
					return false;
				cnt--;
			} else
				cnt++;
		}
		cnt = 0;
		for (int i = n - 1; i >= 0; i--) {
			if (s.charAt(i) == '(') {
				if (cnt < 1)
					return false;
				cnt--;
			} else
				cnt++;
		}
		return true;
	}

	/*NC242 单词搜索
	例如：
	给出的数组为["XYZE","SFZS","XDEE"]时，
	对应的二维字符数组为：
	若单词为"XYZZED"时，应该返回 true，
	也即：
	若单词为"SEE"时，应该返回 true，*/
	private boolean res = false;

	public boolean wordSearcherExist(String[] board, String word) {
		// write code here
		if (board == null || board.length == 0 || word == null)
			return false;
		if (board[0] == null || board[0].length() == 0)
			return false;
		int rows = board.length;
		int cols = board[0].length();
		boolean[][] visited = new boolean[rows][cols];
		char[][] nums = new char[rows][cols];
		for (int i = 0; i < board.length; i++) {
			nums[i] = board[i].toCharArray();
		}

		char[] kword = word.toCharArray();
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (nums[i][j] == kword[0]) {
					dfs(nums, kword, i, j, rows, cols, visited, 0);
				}
			}
			if (res)
				break;
		}

		return res;
	}

	public void dfs(char[][] nums, char[] word, int i, int j, int rows, int cols, boolean[][] visited, int count) {
		if (count > word.length || res) {
			return;
		}
		if (count == word.length) {
			res = true;
			return;
		}

		if (i < 0 || i >= rows || j < 0 || j >= cols || visited[i][j]) {
			return;
		}

		if (nums[i][j] != word[count]) {
			return;
		}

		visited[i][j] = true;
		count += 1;

		// top
		//if(i - 1 >= 0)
		{
			dfs(nums, word, i - 1, j, rows, cols, visited, count);
		}

		// right
		//if(j + 1 < cols)
		{
			dfs(nums, word, i, j + 1, rows, cols, visited, count);
		}

		// bottom
		//if(i + 1 < rows)
		{
			dfs(nums, word, i + 1, j, rows, cols, visited, count);
		}

		// left
		//if(j - 1 >= 0)
		{
			dfs(nums, word, i, j - 1, rows, cols, visited, count);
		}
		// 后面不匹配 就要清除当前标志
		visited[i][j] = false; // 要充分理解回溯
	}

	/*NC179 长度为 K 的重复字符子串
	给你一个由小写字母组成的长度为n的字符串 S ，找出所有长度为 k 且包含重复字符的子串，请你返回全部满足要求的子串的数目。*/
	public int numKLenSubstrRepeats(String s, int k) {
		// write code here
		int count = 0;
		if (s.length() < k)
			k = s.length();
		for (int i = 0, length = s.length(); i < length - k + 1; i++) {
			String sub = s.substring(i, i + k);
			int repeat = 0;
			for (int j = 0; j < k - 1; j++) {
				if (sub.substring(j + 1).contains(String.valueOf(sub.charAt(j)))) {
					repeat++;
				}
			}
			if (repeat != 0)
				count++;
		}
		return count;
	}

	/*NC181 单词拆分(一)
	给定一个字符串和一个字符串数组，在字符串的任意位置拆分任意次后得到的字符串集合是否是给定字符串数组的子集。*/
	public boolean wordDiv(String s, String[] dic) {
		if (s == null || dic == null || dic.length == 0) {
			return false;
		}
		HashSet<String> set = new HashSet<>();
		for (String str : dic) {
			set.add(str);
		}
		int pre = 0;
		int n = s.length();
		int[] dp = new int[n];
		boolean res = false;
		for (int i = 0; i < n; i++) {
			if (set.contains(s.substring(pre, i + 1))) {
				pre = i + 1;
				res = true;
			} else {
				res = false;
			}
		}
		return res;
	}

	public static boolean wordBreak(String s, List<String> wordDict) {
		char[] arr = s.toCharArray();
		boolean[] dp = new boolean[arr.length + 1];
		dp[0] = true;
		for (int i = 0; i <= arr.length; i++) {
			for (int j = 0; j < i; j++) {
				if (dp[j] && wordDict.contains(s.substring(j, i))) {
					dp[i] = true;
					break;
				}
			}
		}

		return dp[arr.length];

	}

	/*NC182 单词拆分(二)
	给定一个字符串和一个字符串数组，在字符串的任意位置添加空格后得到的字符串集合是给定字符串数组的子集，请输出可能的拆分方案。
	对答案按字典序排序后输出，空格视为一个字符。*/
	String path;
	List<String> resList = new ArrayList<>();

	public String[] wordBreak(String s, String[] dicts) {

		Set<String> wordDict = new HashSet<>();
		for (int i = 0; i < dicts.length; i++) {
			wordDict.add(dicts[i]);
		}
		// 而不再需要遍历单词列表。
		backtrack(s, wordDict, 0);
		int len = resList.size(), index = 0;
		String[] result = new String[len];
		for (String wordBreak : resList) {
			result[index++] = String.join(" ", wordBreak);
		}
		return result;
	}

	private void backtrack(String s, Set<String> dicSet, int startIndex) {
		if (startIndex >= s.length()) {
			resList.add(path);
			return;
		}
		for (int i = startIndex; i < s.length(); i++) {
			String word = s.substring(startIndex, i - startIndex + 1);
			if (dicSet.contains(word)) {
				if (path == "") {
					String tmpPath = path;
					path += word;
					backtrack(s, dicSet, i + 1);
					path = tmpPath;
				} else {
					String tmpPath = path;
					path += " " + word;
					backtrack(s, dicSet, i + 1);
					path = tmpPath;
				}
			}
		}
	}

	public boolean wordBreak03(String s, List<String> wordDict) {
		Set<String> wordDictSet = new HashSet(wordDict);
		boolean[] dp = new boolean[s.length() + 1];
		dp[0] = true;
		for (int i = 1; i <= s.length(); i++) {
			for (int j = 0; j < i; j++) {
				if (dp[j] && wordDictSet.contains(s.substring(j, i))) {
					dp[i] = true;
					break;
				}
			}
		}
		return dp[s.length()];
	}

	//==============
	public String[] wordBreak02(String s, String[] wordDict) {
		List<String>[] dp = new LinkedList[s.length() + 1];
		List<String> initial = new LinkedList<>();
		initial.add("");
		Set<String> dics = new HashSet<>();
		for (int i = 0; i < wordDict.length; i++) {
			dics.add(wordDict[i]);
		}
		dp[0] = initial;
		for (int i = 1; i <= s.length(); i++) {
			List<String> list = new LinkedList<>();
			for (int j = 0; j < i; j++) {
				String suffix = s.substring(j, i);
				if (dp[j].size() > 0 && dics.contains(suffix)) {
					for (String l : dp[j]) {
						list.add(l + (l.equals("") ? "" : " ") + suffix);
					}
				}
			}
			dp[i] = list;
		}
		int len = dp[s.length()].size();
		String[] results = new String[len];
		int index = 0;
		for (String wword : dp[s.length()]) {
			results[index++] = wword;
		}
		//字典序排列
		Arrays.sort(results);
		return results;
	}

	/*NC85 拼接所有的字符串产生字典序最小的字符串
	给定一个长度为 n 的字符串数组 strs ，
	请找到一种拼接顺序，使得数组中所有的字符串拼接起来组成的字符串是所有拼接方案中字典序最小的，并返回这个拼接后的字符串。*/
	public String minString(String[] strings) {
		if (strings == null) {
			return null;
		}
		//设计字符串比较的字典序
		PriorityQueue<String> minPriority = new PriorityQueue<>(new Comparator<String>() {
			@Override
			public int compare(String s1, String s2) {
				return (s1 + s2).compareTo(s2 + s1);
			}
		});
		Collections.addAll(minPriority, strings);
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < strings.length; i++) {
			stringBuilder.append(minPriority.poll());
		}
		return stringBuilder.toString();
	}

	/*NC188 二进制求和
	给定两个用字符串表示的二进制数，返回他们的和。*/
	public String binaryAdd(String A, String B) {
		// write code here
		StringBuilder s = new StringBuilder();
		int carry = 0;
		int i = A.length() - 1, j = B.length() - 1;
		int m = 0, n = 0;
		while (i >= 0 && j >= 0) {
			m = A.charAt(i) - '0';
			n = B.charAt(j) - '0';
			s.insert(0, (char) ('0' + (m + n + carry) % 2));
			carry = (m + n + carry) / 2;
			i = i - 1;
			j = j - 1;
		}
		while (i >= 0) {
			m = A.charAt(i) - '0';
			s.insert(0, (char) ('0' + (m + carry) % 2));
			carry = (m + carry) / 2;
			i = i - 1;
		}
		while (j >= 0) {
			n = B.charAt(j) - '0';
			s.insert(0, (char) ('0' + (n + carry) % 2));
			carry = (n + carry) / 2;
			j = j - 1;
		}
		if (carry == 1) {
			s.insert(0, '1');
		}

		return s.toString();
	}

	/*NC190 字符串的全部子序列
	给定一个字符串s，长度为n，求s的所有子序列
	1.子序列: 指一个字符串删掉部分字符（也可以不删）形成的字符串，可以是不连续的，比如"abcde"的子序列可以有"ace","ad"等等
	2.将所有的子序列的结果返回为一个字符串数组
	3.字符串里面可能有重复字符，但是返回的子序列不能有重复的子序列，比如"aab"的子序列只有"","a","aa","aab","ab","b"，不能存在2个相同的"ab"
	4.返回字符串数组里面的顺序可以不唯一*/
	public String[] generatePermutation(String s) {
		HashSet<String> set = new HashSet<>();
		// write code here
		int n = s.length();
		//        HashSet<String> set = new HashSet<>();
		char[] chars = s.toCharArray();
		for (int i = 0; i < 1 << n; i++) {
			String temp = "";
			for (int j = 0; j < n; j++) {
				//用二进制思想生成子集
				//i取值为0~2^n-1
				//表示子集种类有2^n个
				//j取值为0~n
				//表示集合中元素的取值范围
				//当且仅当 i==2^j 的时候，i 按位与 1<<j 得到的结果不为 0
				if (((1 << j) & i) == 0) {
					temp += chars[j];
				}
			}
			set.add(temp);
		}
		String[] arr = new String[set.size()];
		int idx = 0;
		for (String str : set) {
			arr[idx++] = str;
		}
		return arr;
	}

	HashSet<String> pets;

	public String[] generatePermutation02(String s) {
		// write code here
		pets = new HashSet<>();
		dfs(s, 0, "");
		return pets.toArray(new String[0]);
	}

	public void dfs(String s, int index, String str) {
		if (index == s.length()) {
			pets.add(str);
			return;
		}
		dfs(s, index + 1, str + s.charAt(index));
		dfs(s, index + 1, str);
	}

	/*NC219 移掉 K 位数字
	给定一个以字符串表示的数字 num 和一个数字 k ，从 num 中移除 k 位数字，使得剩下的数字最小。如果可以删除全部数字则剩下 0
	
	输入："1432219",3
	返回值："1219"
	*/
	public String removeKnums(String num, int k) {
		if (num.length() == k)
			return "0";
		StringBuilder s = new StringBuilder(num);
		for (int i = 0; i < k; i++) {
			int idx = 0;
			for (int j = 1; j < s.length() && s.charAt(j) >= s.charAt(j - 1); j++)
				idx = j;
			s.delete(idx, idx + 1);
			while (s.length() > 1 && s.charAt(0) == '0')
				s.delete(0, 1);
		}
		return s.toString();
	}

	/*NC154 最长回文子序列
	给定一个字符串，找到其中最长的回文子序列，并返回该序列的长度。
	注：回文序列是指这个序列无论从左读还是从右读都是一样的。
	本题中子序列字符串任意位置删除k（len(s)>=k>=0）个字符后留下的子串。*/
	public int longestPalindromeSubSeq(String s) {
		// write code here
		int n = s.length();
		int[] dp = new int[n];
		Arrays.fill(dp, 1);
		for (int i = n - 2; i >= 0; i--) {
			int pre = 0;
			for (int j = i + 1; j < n; j++) {
				int temp = dp[j];
				if (s.charAt(i) == s.charAt(j))
					dp[j] = pre + 2;
				else
					dp[j] = Math.max(dp[j], dp[j - 1]);
				pre = temp;
			}
		}
		return dp[n - 1];
	}

	/*NC199 字符串解码
	给一个加密过的字符串解码，返回解码后的字符串。
	加密方法是：k[c] ，表示中括号中的 c 字符串重复 k 次，
	例如 3[a] 解码结果是 aaa ，保证输入字符串符合规则。不会出现类似 3a , 3[3] 这样的输入。
	输入："3[3[b]]"
	返回值："bbbbbbbbb"
	*/
	public String decodeString(String s) {
		StringBuilder sb = new StringBuilder();
		Stack<String> stack = new Stack<>();
		for (int i = 0; i < s.length(); i++) {
			String c = "";
			if (Character.isDigit(s.charAt(i))) {
				c = s.charAt(i) + "";
				stack.add(c);
				continue;
			} else if (s.charAt(i) == '[') {
				int temp = 1;
				for (int j = i + 1; j < s.length(); j++) {
					if (s.charAt(j) == '[') {
						temp++;
					} else if (s.charAt(j) == ']') {
						temp--;
					}
					if (temp == 0) {
						c = decodeString(s.substring(i + 1, j));
						i = j;
						break;
					}
				}
			} else {
				c = s.charAt(i) + "";
				stack.push(c);
			}

			if (!stack.isEmpty() && stack.peek().length() != 0 && Character.isDigit(stack.peek().charAt(0))) {
				int curr = Integer.parseInt(stack.pop());
				for (int j = 0; j < curr; j++) {
					stack.push(c);
				}
			}
		}
		for (int i = 0; i < stack.size(); i++) {
			sb.append(stack.get(i));
		}
		return sb.toString();
	}

	/*NC52 有效括号序列
	给出一个仅包含字符'(',')','{','}','['和']',的字符串，判断给出的字符串是否是合法的括号序列
	括号必须以正确的顺序关闭，"()"和"()[]{}"都是合法的括号序列，但"(]"和"([)]"不合法。*/
	public boolean isValid(String s) {
		// write code here
		Stack<Character> stack = new Stack<Character>();
		for (char c : s.toCharArray()) {
			// 碰到左括号，就把相应的右括号入栈
			if (c == '(')
				stack.push(')');
			else if (c == '[')
				stack.push(']');
			else if (c == '{')
				stack.push('}');
			// 如果是右括号判断是否和栈顶元素匹配
			else if (stack.isEmpty() || c != stack.pop())
				return false;
		}
		// 最后判断栈中元素是否匹配
		return stack.isEmpty();
	}

	/*NC127 最长公共子串
	给定两个字符串str1和str2,输出两个字符串的最长公共子串
	题目保证str1和str2的最长公共子串存在且唯一。 */
	public String LCS2(String str1, String str2) {
		// write code here
		String result = "";
		int start = 0;
		int end = 1;
		while (end <= str2.length()) {
			String subStr = str2.substring(start, end);
			if (str1.contains(subStr)) {
				result = subStr;
			} else {
				start++;
			}
			end++;
		}
		return result;
	}

	/* NC97 字符串出现次数的TopK问题
	 给定一个字符串数组，再给定整数 k ，请返回出现次数前k名的字符串和对应的次数。
	 返回的答案应该按字符串出现频率由高到低排序。如果不同的字符串有相同出现频率，按字典序排序。
	 对于两个字符串，大小关系取决于两个字符串从左到右第一个不同字符的 ASCII 值的大小关系。
	 比如"ah1x"小于"ahb"，"231"<”32“
	 字符仅包含数字和字母*/
	public String[][] topKstrings02(String[] strings, int k) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		HashMap<String, Integer> strTimesMap = new HashMap<>();
		for (int i = 0; i < strings.length; i++) {
			strTimesMap.put(strings[i], strTimesMap.getOrDefault(strings[i], 0) + 1);
		}
		for (Map.Entry<String, Integer> entry : strTimesMap.entrySet()) {
			Node cur = new Node(entry.getKey(), entry.getValue());
			if (pq.size() < k) {
				pq.add(cur);
			} else {
				if (pq.peek().compareTo(cur) < 0) {
					pq.poll();
					pq.add(cur);
				}
			}
		}
		String[][] res = new String[k][2];
		for (int i = k - 1; i >= 0; i--) {
			Node e = pq.poll();
			res[i][0] = e.value;
			res[i][1] = String.valueOf(e.times);
		}
		return res;
	}
	
	public String[][] topKstrings03 (String[] strings, int k) {
		   // write code here
			if (k == 0) {
				return new String[][] {};
			}
			String[][] res = new String[k][2];
			TreeMap<String, Integer> map = new TreeMap<>();
			// 统计各个字符串出现的次数
			for (int i = 0; i < strings.length; ++i) {
				String s = strings[i];
				if (!map.containsKey(s)) {
					map.put(s, 1);
				} else {
					map.put(s, map.get(s) + 1);
				}
			}

			ArrayList<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());
			// 先是按出现次数降序比较，相同则再按照字符ASCII码降序比较
			Collections.sort(list,
					(o1, o2) -> (o1.getValue().compareTo(o2.getValue()) == 0 ? o1.getKey().compareTo(o2.getKey())
							: o2.getValue().compareTo(o1.getValue())));
			// 返回topK
			for (int i = 0; i < k; i++) {
				res[i][0] = list.get(i).getKey();
				res[i][1] = String.valueOf(list.get(i).getValue());
			}
			return res;
		}
}

class Node implements Comparable<Node> {
	String value;
	int times;

	public Node(String value, int times) {
		this.value = value;
		this.times = times;
	}

	@Override
	public int compareTo(Node o) {
		if (this.times == o.times) {
			return o.value.compareTo(this.value);
		}
		return this.times - o.times;
	}
}
