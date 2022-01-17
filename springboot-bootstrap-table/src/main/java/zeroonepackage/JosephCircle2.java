package zeroonepackage;

import java.util.LinkedList;
import java.util.Scanner;

/*
约瑟夫环变种： 
输入一个由随机数组成的数列（数列中每个数均是大于0的整数，长度已知），和初始计数值m。从数列首位置开始计数，计数到m后，将数列该位置数值替换计数值m，并将数列该位置数值出列，然后从下一位置从新开始计数，直到数列所有数值出列为止。如果计数到达数列尾段，则返回数列首位置继续计数。请编程实现上述计数过程，同时输出数值出列的顺序

比如： 输入的随机数列为：3,1,2,4，初始计数值m=7，从数列首位置开始计数（数值3所在位置）
第一轮计数出列数字为2，计数值更新m=2，出列后数列为3,1,4，从数值4所在位置从新开始计数
第二轮计数出列数字为3，计数值更新m=3，出列后数列为1,4，从数值1所在位置开始计数
第三轮计数出列数字为1，计数值更新m=1，出列后数列为4，从数值4所在位置开始计数
最后一轮计数出列数字为4，计数过程完成。
输出数值出列顺序为：2,3,1,4。
 */
public class JosephCircle2 {
	
	static LinkedList<Integer> result = new LinkedList<>();

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (in.hasNext()) {
			result.clear();
			int len = in.nextInt();
			int[] input_aray = new int[len];
			for (int i = 0; i < len; i++) {
				input_aray[i] = in.nextInt();
			}
			int m = in.nextInt();
			josephIterate(len, input_aray, m);
			for (Integer o : result) {
				System.out.println(o);
			}
		}
		in.close();

	}

	private static void josephIterate(int len, int[] input_aray, int m) {
		LinkedList<Integer> array = new LinkedList<>();
		for (int i = 0; i < len; i++)
			array.add(input_aray[i]);
		int flag = 0;
		while (array.size() > 0) {
			flag = flag + m;
			int i = flag % array.size() - 1;

			if (i < 0) {
				m = array.get(array.size() - 1);
				result.add(array.get(array.size() - 1));
				array.remove(array.size() - 1);
				flag = 0;
			} else {
				m = array.get(i);
				flag = i; //这个很重要 要及时更新开始位置flag
				result.add(array.get(i));
				array.remove(array.get(i));
			}

		}
	}
}
