package zeroonepackage;

/*
 输入两个整数n和sum，要求从数列1,2,3,...,n中随意取出几个数，使得它们的和等于sum，请将其中所有可能的组合列出来。
 */
public class ManySumN {
	/*
	 * 函数功能：以字符串形式返回1~n个数的所有子集，其中0代表不包含其中数字i，1代表 包含其中数字i
	 * 此段代码是运用反射格雷码的思想，具体解释详见：算法笔记_019:背包问题（Java）
	*/
	public String[] getAllGroup(int n) {
		int len = (int) Math.pow(2, n);
		String[] result = new String[len];
		if (n == 1) {
			result[0] = "0";
			result[1] = "1";
			return result;
		}
		String[] temp = getAllGroup(n - 1);
		for (int i = 0; i < temp.length; i++) {
			result[i] = "0" + temp[i];
			result[len - 1 - i] = "1" + temp[i];
		}
		return result;
	}

	/*
	 * 参数n:代表有1~n的n个不同整数
	 * 函数功能：打印出1~n中所有随机组合的几个数，其相加的和等于sum
	 */
	public void printManySumN(int n, int sum) {
		System.out.println("1~" + n + "个数中，相加之和等于" + sum + "的所有组合数为：");
		String[] allGroup = getAllGroup(n);
		for (int i = 0; i < allGroup.length; i++) {
			char[] temp = allGroup[i].toCharArray();
			int tempSum = 0;
			for (int j = 0; j < temp.length; j++) {
				if (temp[j] == '1')
					tempSum += (j + 1);
			}
			if (tempSum == sum) {
				for (int j = 0; j < temp.length; j++) {
					if (temp[j] == '1')
						System.out.print((j + 1) + " ");
				}
				System.out.println();
			}
		}
	}

	public static void main(String[] args) {
		ManySumN test = new ManySumN();
		test.printManySumN(10, 16);
	}
}
