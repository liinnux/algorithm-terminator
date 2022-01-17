/**
 * 
 */
package com.tompai;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Administrator
 *
 */
public class ReverseOrderArray {
	public static long count = 0;//记录逆序对个数

	public static long mergeSort(int[] arr) {
		if (arr == null || arr.length == 1) { //空或一个数直接返回0
			return 0;
		}
		//初始跨度为1
		int span = 1;
		//例如：span=1 -> 数组分割为2个元素一组进行归并，span=2 -> 数组分割为4个元素一组来归并 ……
		while (span < arr.length) {
			//for循环里每2个span进行截取出left,mid,right,然后merge排序
			for (int i = 0; i < arr.length; i += 2 * span) {
				int left = i;
				int mid = left + span - 1; //2个跨度进行归并，mid为1个跨度的位置
				/*处理边界，当最后一个mid定位超出了长度，表示最后几个数凑不到一个span，
				本次span长度=上次while里span2倍，故也凑不齐上次while循环里的2*span，
				凑不齐上次循环2个span的几个数在上次while里一定归并排序好了，
				所以可以直接break
				 */
				if (mid >= arr.length - 1) {
					break;
				}
				int right = left + 2 * span - 1;
				if (right >= arr.length - 1) { //处理边界，右边界超出长度则直接为最后一位
					right = arr.length - 1;
				}
				merge(arr, left, mid, right); //每个范围归并排序
			}
			//span = span << 1;   //优化一下可以用位运算左移，等同于乘2
			span = span * 2; //跨度翻倍，翻倍变为2，4，8，16……从而以log（N）次循环整个数组长度
		}
		return count;
	}

	//对排完序的left ~ mid, mid+1 ~ right范围进行归并排序
	public static long merge(int[] arr, int left, int mid, int right) {
		int[] tmp = new int[right - left + 1]; //临时数组
		int l = left; //左指针初始位置
		int m = mid + 1; //右指针初始位置
		int i = 0; //tmp下标
		while (l <= mid && m <= right) { //任意左或右指针到边界则退出
			if (arr[l] > arr[m]) {
				// 只有当左大右小，产生逆序对，因为有序，l开始到mid每个数都比arr[m]大，
				// 逆序数就为（arr[l]arr[m]），（arr[l+1]arr[m]）……（arr[mid]arr[m]）
				count = count + (mid - l + 1); //逆序对个数累加
				
				tmp[i] = arr[m];
				m += 1;
			} else {
				tmp[i] = arr[l];
				l += 1;
			}
			i += 1;
		}
		while (l <= mid) { //如果左指针先循环完，剩下的数补到tmp上
			tmp[i] = arr[l];
			l += 1;
			i += 1;
		}
		while (m <= right) { //如果右指针先循环完，剩下的数补到tmp上
			tmp[i] = arr[m];
			m += 1;
			i += 1;
		}
		for (int j = 0; j < tmp.length; j++) { //tmp还原到原数组
			arr[left + j] = tmp[j];
		}
		return count;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try (Scanner sc = new Scanner(System.in)) {
			while (sc.hasNext()) {
				String line = sc.nextLine();
				//去掉非数字字符
				line = line.trim();
				int n = 1000000007;
				int mm=493330277;
				String[] numbers = line.split("\\D");
				if (numbers.length <= 0) {
					continue;
				} else {
					int t = 0;
					List<Integer> reverseList = new ArrayList<>();
					for (int i = 0; i < numbers.length; i++) {
						String num = numbers[i];
						if (num.length() > 0) {
							int tt = Integer.parseInt(num);
							/*for (int kk : reverseList) {
								if (kk > tt)
									t++;
							}*/
							reverseList.add(tt);
						}
					}

					int j=0;
					int[] ss=new int[reverseList.size()];
					for (int kk : reverseList) {
						ss[j++]=kk;
					}
					long count = mergeSort(ss);
					System.out.println(count % n);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}
}
