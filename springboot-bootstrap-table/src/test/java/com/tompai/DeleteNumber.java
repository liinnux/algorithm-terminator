/**
 * 
 */
package com.tompai;

import java.util.Scanner;

/**
 * @author Administrator
 *
 */
public class DeleteNumber {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		while (scan.hasNext()) {
			int n = scan.nextInt();
			deleteNumber(n);
		} //endwhile 
		scan.close();
	}

	/**
	 * 每隔两个数删掉一个数，到末尾时循环至开头继续进行，输出最后一个被删除的数的原始下标位置
	 */
	public static void deleteNumber(int n) {
		int index = 0;//保存元素下标
		int[] flag = new int[n];//标记数组中的元素是否已经被删除过 
		int inteval = 0;//记录间隔的元素个数，当inteval=3时候更新删除标记为flag
		int count_delete = 0;//统计删除的元素个数
		//总共删除n个元素
		while (count_delete < n) {
			//当i=n时候，相当于从0继续循环遍历
			for (int i = 0; i != n; i++) {
				//当前数组元素i还没有被删除
				if (flag[i] == 0) {
					inteval++;
					if (inteval == 3) {
						flag[i] = 1;//更新删除标记位
						inteval = 0;//更新间隔元素个数
						index = i;//保存删除元素的下标
						count_delete++;
					}
				} //endif out
			} //endfor
		}
		System.out.println(index);
	}
}
