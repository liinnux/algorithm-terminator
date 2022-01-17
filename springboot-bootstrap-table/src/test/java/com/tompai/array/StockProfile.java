/**
 * 
 */
package com.tompai.array;

import java.util.Arrays;

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
	 假设你有一个数组prices，长度为n，其中prices[i]是股票在第i天的价格，请根据这个价格数组，返回买卖股票能获得的最大收益
	1.你可以买入一次股票和卖出一次股票，并非每天都可以买入或卖出一次，总共只能买入和卖出一次，且买入必须在卖出的前面的某一天
	2.如果不能获取到任何利润，请返回0
	3.假设买入卖出均无手续费
	 */
	public int maxProfit2(int[] prices) {
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
	 假设你有一个数组prices，长度为n，其中prices[i]是股票在第i天的价格，请根据这个价格数组，返回买卖股票能获得的最大收益
	1.你可以买入一次股票和卖出一次股票，并非每天都可以买入或卖出一次，总共只能买入和卖出一次，且买入必须在卖出的前面的某一天
	2.如果不能获取到任何利润，请返回0
	3.假设买入卖出均无手续费
	 */
	public int maxProfit(int[] prices) {
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

	/*假设你有一个数组prices，长度为n，其中prices[i]是某只股票在第i天的价格，请根据这个价格数组，返回买卖股票能获得的最大收益
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
		int ans = 0;
		for (int i = 1; i < prices.length; i++) {
			if (prices[i] > prices[i - 1]) {
				ans += prices[i] - prices[i - 1];
			}

		}
		return ans;
	}
	
	/*买卖股票的最好时机(三)
	假设你有一个数组prices，长度为n，其中prices[i]是某只股票在第i天的价格，请根据这个价格数组，返回买卖股票能获得的最大收益
	1. 你最多可以对该股票有两笔交易操作，一笔交易代表着一次买入与一次卖出，但是再次购买前必须卖出之前的股票
	2. 如果不能获取收益，请返回0
	3. 假设买入卖出均无手续费*/
	public int maxProfit03 (int[] prices) {
        // write code here
        if (prices.length == 0) return 0;
        /*
        5个状态标记：
        1）不操作
        2）第一次购买
        3）第一次卖出
        4）第二次购买
        5）第二次卖出
        dp[i][j]代表第i天状态为j时产生的最大收益
        */
        int [][]dp = new int[prices.length][5];
        //初始化
        dp[0][1] = -prices[0];
        dp[0][3] = -prices[0];
        for (int i = 1; i <= prices.length; i++) {
            dp[i][0] = dp[i - 1][0];
            //其中dp[i][1]有两个操作1）第i天没有操作2）第i天买入股票，所以此时最大收益，应该为这两个操作比大小
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
            //其中dp[i][2]有两个操作1）第i天没有操作2）第i天卖出股票，所以此时最大收益，应该为这两个操作比大小
            dp[i][2] = Math.max(dp[i - 1][2], dp[i - 1][1] + prices[i]);
            //其中dp[i][3]有两个操作1）第i天没有操作2）第i天买入股票，所以此时最大收益，应该为这两个操作比大小
            dp[i][3] = Math.max(dp[i - 1][3], dp[i - 1][2] - prices[i]);
            //其中dp[i][4]有两个操作1）第i天没有操作2）第i天卖出股票，所以此时最大收益，应该为这两个操作比大小
            dp[i][4] = Math.max(dp[i - 1][4], dp[i - 1][3] + prices[i]);
        }
        return dp[prices.length - 1][4];
    }
	
	public int maxProfit03_1 (int[] prices) {
        // write code here
        int fstBuy=Integer.MAX_VALUE,fstSell=0;
        int secBuy=Integer.MAX_VALUE,secSell=0;
        for(int price:prices){
            fstBuy=Math.min(fstBuy,price);
            fstSell=Math.max(fstSell,price-fstBuy);
            secBuy=Math.min(secBuy,price-fstSell);
            secSell=Math.max(secSell,price-secBuy);
             
        }
        return secSell;
    }

	/*买卖股票的最好时机(四)
	假设你有一个数组pricesprices，长度为nn，其中prices[i]prices[i]是某只股票在第i天的价格，请根据这个价格数组，返回买卖股票能获得的最大收益
	1. 你最多可以对该股票有kk笔交易操作，一笔交易代表着一次买入与一次卖出，但是再次购买前必须卖出之前的股票
	2. 如果不能获取收益，请返回0
	3. 假设买入卖出均无手续费
	数据范围:
	1. 0 <= prices.length <= 1000
	2. 0 <= prices[i] <= 1000
	3. 0 <= k <= 100
	*/
	public int maxProfit05 (int[] prices, int k) {
        // write code here
        if(prices.length <=1 || k == 0){
            return 0;
        }
        int len = prices.length;
        int [] buy = new int[len+1];
        int [] sell = new int[len+1];
        Arrays.fill(buy,-prices[0]);
        Arrays.fill(sell,0);
        for(int p : prices){
            for(int i=1;i<=k;i++){
                buy[i] = Math.max(buy[i],sell[i-1] - p);
                sell[i] = Math.max(sell[i],buy[i] + p);
            }
        }
        return sell[k];
    }
}
