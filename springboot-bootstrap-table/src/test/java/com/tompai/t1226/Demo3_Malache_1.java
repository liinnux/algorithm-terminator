/**
 * 
 */
package com.tompai.t1226;

import java.util.Arrays;

/**
 * 最长回文子串判断
 * 
 * @author Administrator
 */
public class Demo3_Malache_1 {

	public static void main(String[] args) {
		// 生成实例
		testing();
	}

	static String longestPalindrome(String s) {
		// 填充
		String newS = fillStr(s);

		// center是中心，right是中心的最远覆盖范围，max_center是最长回文字串的中心
		int right = 0, center = 0, max_center = 0;

		// 记录每个位置的最远覆盖范围
		int[] radius = new int[newS.length()];
		int lenOfNewS = newS.length();

		// 算法主体
		for (int idx = 1; idx < lenOfNewS; idx++) {
			if (idx < right) {
				int mirror = 2 * center - idx;
				radius[idx] = Math.min(right - idx, radius[mirror]);
			} else
				radius[idx] = 1;

			while ((idx + radius[idx] < lenOfNewS) && (idx - radius[idx] >= 0)
					&& (newS.charAt(idx + radius[idx]) == newS.charAt(idx - radius[idx])))
				radius[idx]++;

			if (idx + radius[idx] > right) {
				right = idx + radius[idx];
				center = idx;
			}

			if (radius[idx] > radius[max_center]) {
				max_center = idx;
			}
		}

		// 返回最长回文字串
		int l = max_center - radius[max_center];
		int r = max_center + radius[max_center];
		return s.substring((l + 1) / 2, r / 2);
	}

	// 填充
	static private String fillStr(String s) {
		StringBuilder retS = new StringBuilder("#");
		for (int i = 0; i < s.length(); i++) {
			retS.append(s.charAt(i)).append("#");
		}

		return retS.toString();
	}

	// 测试
	public static void testing() {
		System.out.println(longestPalindrome("abababababaab"));
		System.out.println(longestPalindrome("aba"));
		System.out.println(longestPalindrome("aa"));
	}
}
