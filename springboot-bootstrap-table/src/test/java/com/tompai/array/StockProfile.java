/**
 * 
 */
package com.tompai.array;

import java.util.Arrays;
import java.util.Stack;

/**
 * @author Administrator
 *
 */
public class StockProfile {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/*
	NC7 买卖股票的最好时机(一)-可以买入一次股票和卖出一次股票
	 假设你有一个数组prices，长度为n，其中prices[i]是股票在第i天的价格，请根据这个价格数组，返回买卖股票能获得的最大收益
	1.你可以买入一次股票和卖出一次股票，并非每天都可以买入或卖出一次，总共只能买入和卖出一次，且买入必须在卖出的前面的某一天
	2.如果不能获取到任何利润，请返回0
	3.假设买入卖出均无手续费
	 */
	public int maxProfit01(int[] prices) {
		// write code here
		int len = prices.length;
		// 特殊判断
		if (len < 2) {
			return 0;
		}
		int[][] dp = new int[len][2];
		// dp[i][0] 下标为 i 这天结束的时候，不持股，手上拥有的现金数
		// dp[i][1] 下标为 i 这天结束的时候，持股，手上拥有的现金数
		// 初始化：不持股显然为 0，持股就需要减去第 1 天（下标为 0）的股价
		dp[0][0] = 0;
		dp[0][1] = -prices[0];
		// 从第 2 天开始遍历
		for (int i = 1; i < len; i++) {
			dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
			dp[i][1] = Math.max(dp[i - 1][1], -prices[i]);
		}
		return dp[len - 1][0];
	}

	/*
	NC7 买卖股票的最好时机(一)
	 假设你有一个数组prices，长度为n，其中prices[i]是股票在第i天的价格，请根据这个价格数组，返回买卖股票能获得的最大收益
	1.你可以买入一次股票和卖出一次股票，并非每天都可以买入或卖出一次，总共只能买入和卖出一次，且买入必须在卖出的前面的某一天
	2.如果不能获取到任何利润，请返回0
	3.假设买入卖出均无手续费
	 */
	public int maxProfit01_1(int[] prices) {
		//贪心
		int min = prices[0], max = 0;
		for (int i = 1; i < prices.length; i++) {
			if (prices[i] < min)
				min = prices[i];
			else
				max = Math.max(max, prices[i] - min);
		}
		return max;
	}

	/*
	NC7 买卖股票的最好时机(一)
	 假设你有一个数组prices，长度为n，其中prices[i]是股票在第i天的价格，请根据这个价格数组，返回买卖股票能获得的最大收益
	1.你可以买入一次股票和卖出一次股票，并非每天都可以买入或卖出一次，总共只能买入和卖出一次，且买入必须在卖出的前面的某一天
	2.如果不能获取到任何利润，请返回0
	3.假设买入卖出均无手续费
	 */
	/*单调栈： 维护着一个最具竞争力的递增序列*/
	public int maxProfit01_2(int[] prices) {
		int len = prices.length;
		Stack<Integer> s = new Stack<>();
		// 可以在 prices[len] 位置放一个 -1 的哨兵
		// 这样可以让单调递增的数组中所有的元素逼出去
		int ans = 0;
		for (int i = 0; i < prices.length; i++) {
			while (s.size() > 0 && s.peek() > prices[i]) {
				ans = Math.max(ans, s.peek() - s.firstElement());
				s.pop();
			}
			s.add(prices[i]);
		}
		// 模拟哨兵行为
		if (s.size() > 0)
			ans = Math.max(ans, s.peek() - s.firstElement());

		return ans;
	}

	/*
	NC134 买卖股票的最好时机(二)可以多次买卖该只股票
	假设你有一个数组prices，长度为n，其中prices[i]是某只股票在第i天的价格，请根据这个价格数组，返回买卖股票能获得的最大收益
	1. 你可以多次买卖该只股票，但是再次购买前必须卖出之前的股票
	2. 如果不能获取收益，请返回0
	3. 假设买入卖出均无手续费
	输入：[8,9,2,5,4,7,1]
	返回值：7
	说明：
	在第1天(股票价格=8)买入，第2天(股票价格=9)卖出，获利9-8=1
	在第3天(股票价格=2)买入，第4天(股票价格=5)卖出，获利5-2=3
	在第5天(股票价格=4)买入，第6天(股票价格=7)卖出，获利7-4=3
	总获利1+3+3=7，返回7
	*/
	public int maxProfit02(int[] prices) {
		int ans = 0, tmp = 0;
		for (int i = 1; i < prices.length; i++) {
			tmp = prices[i] - prices[i - 1];
			if (tmp > 0) {
				ans += tmp;
			}
		}
		return ans;
	}

	public int maxProfit02_1(int[] prices) {
		if (prices == null || prices.length < 2)
			return 0;
		int length = prices.length;
		//初始条件
		int hold = -prices[0];//持有股票
		int noHold = 0;//没持有股票
		for (int i = 1; i < length; i++) {
			//递推公式转化的
			noHold = Math.max(noHold, hold + prices[i]);
			hold = Math.max(hold, noHold - prices[i]);
		}
		//最后一天肯定是手里没有股票的时候利润才会最大，
		//所以这里返回的是noHold
		return noHold;
	}

	/*买卖股票的最好时机(三)-最多可以对该股票有两笔交易
	假设你有一个数组prices，长度为n，其中prices[i]是某只股票在第i天的价格，请根据这个价格数组，返回买卖股票能获得的最大收益
	1. 你最多可以对该股票有两笔交易操作，一笔交易代表着一次买入与一次卖出，但是再次购买前必须卖出之前的股票
	2. 如果不能获取收益，请返回0
	3. 假设买入卖出均无手续费*/
	public int maxProfit03(int[] prices) {
		if (prices.length == 0)
			return 0;
		int first_hold = -prices[0], second_hold = -prices[0];
		int first_sell = 0, second_sell = 0;
		for (int i = 1; i < prices.length; i++) {
			first_hold = Math.max(first_hold, -prices[i]);
			first_sell = Math.max(first_sell, first_hold + prices[i]);

			second_hold = Math.max(second_hold, first_sell - prices[i]);
			second_sell = Math.max(second_sell, second_hold + prices[i]);
		}
		return second_sell;
	}

	/*买卖股票的最好时机(四)--多可以对该股票有k笔交易操作
	假设你有一个数组pricesprices，长度为nn，其中prices[i]prices[i]是某只股票在第i天的价格，
	请根据这个价格数组，返回买卖股票能获得的最大收益
	1. 你最多可以对该股票有k笔交易操作，一笔交易代表着一次买入与一次卖出，但是再次购买前必须卖出之前的股票
	2. 如果不能获取收益，请返回0
	3. 假设买入卖出均无手续费
	数据范围:
	1. 0 <= prices.length <= 1000
	2. 0 <= prices[i] <= 1000
	3. 0 <= k <= 100
	*/
	public int maxProfit04(int[] prices, int k) {
		if (prices.length <= 1 || k == 0) {
			return 0;
		}
		int len = prices.length;
		int[] buy = new int[len + 1];
		int[] sell = new int[len + 1];
		Arrays.fill(buy, -prices[0]);
		Arrays.fill(sell, 0);
		for (int p : prices) {
			for (int i = 1; i <= k; i++) {
				buy[i] = Math.max(buy[i], sell[i - 1] - p);
				sell[i] = Math.max(sell[i], buy[i] + p);
			}
		}
		return sell[k];
	}
}
