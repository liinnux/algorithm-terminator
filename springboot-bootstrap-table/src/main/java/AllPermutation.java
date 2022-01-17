
public class AllPermutation {

	//方法1：递归实现
	/*
	 * 参数arrayA:给定字符串的字符数组
	 * 参数start:开始遍历字符与其后面各个字符将要进行交换的位置
	 * 参数end:字符串数组的最后一位
	 * 函数功能：输出字符串数字的各个字符全排列
	 */
	public void recursionArrange(char[] arrayA, int start, int end) {
		if (end <= 1)
			return;
		if (start == end) {
			for (int i = 0; i < arrayA.length; i++)
				System.out.print(arrayA[i]);
			System.out.println();
		} else {
			for (int i = start; i <= end; i++) {
				swap(arrayA, i, start);
				recursionArrange(arrayA, start + 1, end);
				swap(arrayA, i, start);
			}
		}

	}

	//方法2：字典序排列
	/*
	 * 参数arrayA：给定字符串的字符数组
	 * 函数功能：输出字符串数组的所有字符的字典序全排列
	 */
	public void dictionaryArrange(char[] arrayA) {
		System.out.println(String.valueOf(arrayA));
		while (allArrange(arrayA))
			System.out.println(String.valueOf(arrayA));
	}

	//判断当前数组arrayA序列是否可以进行字典序排列，如可以则进行排列并返回true，否则返回false
	public boolean allArrange(char[] arrayA) {
		int i;
		for (i = arrayA.length - 2; (i >= 0) && arrayA[i] > arrayA[i + 1]; --i)
			;
		if (i < 0)
			return false;
		int k;
		for (k = arrayA.length - 1; (k > i) && arrayA[i] >= arrayA[k]; --k)
			;
		swap(arrayA, i, k);
		reverseArray(arrayA, i + 1, arrayA.length - 1);
		return true;
	}

	//将数组中a[m]到a[n]一段元素反序排列
	private void reverseArray(char[] arrayN, int m, int n) {
		while (m < n) {
			char temp = arrayN[m];
			arrayN[m++] = arrayN[n];
			arrayN[n--] = temp;
		}
	}

	//交换数组m位置和n位置上的值
	private void swap(char[] arrayA, int m, int n) {
		char temp = arrayA[m];
		arrayA[m] = arrayA[n];
		arrayA[n] = temp;
	}

	//所有字符串的组合
	public  void combine(String str) {
		char[] in = str.toCharArray();
		StringBuffer out = new StringBuffer();
		allCombine(in, out, 0);
	}

	private  void allCombine(char[] in, StringBuffer out, int start) {
		for (int i = start; i < in.length; i++) {
			out.append(in[i]);
			//组合结果输出
			System.out.println(out);
			if (i < in.length - 1) { //如果有下个元素，则递归折行
				allCombine(in, out, i + 1);
			}
			// 每一次将上次最后一位单词移除
			out.deleteCharAt(out.length() - 1);
		}
	}

	public static void main(String[] args) {
		AllPermutation test = new AllPermutation();
		String A = "abc";
		char[] arrayA = A.toCharArray();
		System.out.println("排列1-递归");
		test.recursionArrange(arrayA, 0, arrayA.length - 1);
		System.out.println("排列2-字典能");
		test.dictionaryArrange(arrayA);
		System.out.println("组合1");
		test.combine(A);
	}
}
