package zeroonepackage;

/*
 * 用回溯法实现0-1背包问题。 
 * 在回溯开始之前，首先对于背包中的物品按照单位重量价值进行排序，方便于后面右子树的剪枝操作。 
 * 在初始化物品的重量和价值时，已经按照单位重量的价值排好了序。 
 * 一个典型的子集树问题，对于背包中的每一个物品，可以选择放入（左子树）或者不放入（右子树）。
 * 依次对每个节点进行搜索，得到最优解。
 * */

public class Knapsack2 {
	/*
	 * 物品 A B C D E F G 
	 * 重量 35 30 60 50 40 10 25 
	 * 价值  10  40  30  50  35  40  30
	 * 
	 * */

	//物品数量
	int n = 7;
	//背包容量
	int capacity = 150;
	//物品重量数组
	double weight[] = { 35, 30, 60, 50, 40, 10, 25 };
	//物品价值数组
	double value[] = { 10, 40, 30, 50, 35, 40, 30 };

	//最大价值
	int maxValue = 0;
	//当前最大价值
	int currentValue;
	//当前重量
	int currentWeight;
	// 装入方法数组
	int[] way = new int[n];
	//最佳装入方法数组
	int[] bestWay = new int[n];

	/*
	 * 回溯算法设计
	 * */
	public void backTrack(int t) {
		// 已经搜索到根节点
		if (t > n - 1) {
			if (currentValue > maxValue) {
				maxValue = currentValue;
				for (int i = 0; i < n; i++)
					bestWay[i] = way[i];
			}
			return;
		}
		// 搜索左边节点
		if (currentWeight + weight[t] <= capacity) {
			currentWeight += weight[t];
			currentValue += value[t];
			way[t] = 1;
			//回溯
			backTrack(t + 1);
			currentWeight -= weight[t];
			currentValue -= value[t];
			way[t] = 0;
		}
		// 不装入这个物品，直接搜索右边的节点
		if (bound(t + 1) >= maxValue) {
			backTrack(t + 1);
		}
	}

	// 用于计算剩余物品的最高价值上界
	public double bound(int k) {
		double maxLeft = currentValue;
		int leftWeight = capacity - currentWeight;
		// 尽力依照单位重量价值次序装剩余的物品
		while (k <= n - 1 && leftWeight > weight[k]) {
			leftWeight -= weight[k];
			maxLeft += value[k];
			k++;
		}
		// 不能装时，用下一个物品的单位重量价值折算到剩余空间。
		if (k <= n - 1) {
			maxLeft += value[k] / weight[k] * leftWeight;
		}
		return maxLeft;
	}

	public static void main(String[] args) {
		Knapsack2 knspsack2 = new Knapsack2();
		knspsack2.backTrack(0);
		System.out.println("该背包能够取到的最大价值为:" + knspsack2.maxValue);
		System.out.println("当前背包的重量为:" + knspsack2.capacity);
		System.out.println("物品的取出方法为取出的方法为:");
		for (int i : knspsack2.bestWay)
			System.out.print(i + "  ");
	}
}
