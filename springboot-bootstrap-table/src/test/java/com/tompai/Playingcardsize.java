/**
 * 
 */
package com.tompai;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * @author Administrator
 *
 */
public class Playingcardsize {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		while (scan.hasNext()) {
			String str = scan.nextLine();
			printBigCards(str);
		} //endwhile
		scan.close();
	}

	/**
	 * 输出两手扑克牌中较大的那手 每手牌类型 个子、对子、顺子（连续五张）、三个、炸弹（四个）和对王 比较方式：
	 * 1、除了炸弹和对王可以和所有牌比较之外，其他类型的牌只能跟相同的类型的存在比较关系 2、个子、对子、三个比较牌面大小 3、顺子比较最小牌大小
	 * 4、炸弹大于前面所有的牌，炸弹之间比较牌面大小 5、对王是最大的牌 6、如果不存在比较关系则输出ERROR
	 */
	public static void printBigCards(String str) {
		String[] total_str = str.split("-");
		String[] left_str = total_str[0].split(" ");
		String[] right_str = total_str[1].split(" ");
		int left_str_length = left_str.length;
		int right_str_length = right_str.length;

		switch (left_str_length) {
		case 1:
			//如果左手牌是个子
			//1.右手牌也是个子，比较牌面大小
			if (right_str_length == 1) {
				if (getIndex(left_str[0]) > getIndex(right_str[0])) {
					System.out.println(total_str[0]);
				} else {
					System.out.println(total_str[1]);
				}
				//2.如果右手牌是普通炸弹或者王炸
			} else if (right_str_length == 4 || total_str[1].equals("joker JOKER")) {
				System.out.println(total_str[1]);
			} else {
				//3.如果不存在比较关系，输出ERROR
				System.out.println("ERROR");
			}
			break;
		case 2:
			//如果左手牌是对子
			//1.左手牌有可能是两个王组成的王炸
			if (total_str[0].equals("joker JOKER")) {
				System.out.println(total_str[0]);
			} else {
				//2.左手牌普通的对子
				//2.1如果右边也是对子
				if (right_str_length == 2) {
					//2.1.1 王炸
					if (total_str[1].equals("joker JOKER")) {
						System.out.println(total_str[1]);
					} else {
						//2.1.2普通对子
						if (getIndex(left_str[0]) > getIndex(right_str[0])) {
							System.out.println(total_str[0]);
						} else {
							System.out.println(total_str[1]);
						}
					}
				} else if (right_str_length == 4) {
					System.out.println(total_str[1]);
				} else {
					System.out.println("ERROR");
				}
			}
			break;
		case 3:
			//如果左手牌是3个
			//1.右手牌是3个
			if (right_str_length == 3) {
				if (getIndex(left_str[0]) > getIndex(right_str[0])) {
					System.out.println(total_str[0]);
				} else {
					System.out.println(total_str[1]);
				}
				//2.右手牌炸弹
			} else if (right_str_length == 4 || total_str[1].equals("joker JOKER")) {
				System.out.println(total_str[1]);
			} else {
				System.out.println("ERROR");
			}
			break;
		case 4:
			//如果左手牌是4个普通炸弹
			if (total_str[1].equals("joker JOKER")) {
				System.out.println(total_str[1]);
			} else if (right_str_length == 4) {
				if (getIndex(left_str[0]) > getIndex(right_str[0])) {
					System.out.println(total_str[0]);
				} else {
					System.out.println(total_str[1]);
				}
			} else {
				System.out.println(total_str[0]);
			}
			break;
		case 5:
			//如果左手牌是5张顺子
			if (right_str_length == 5) {
				if (getIndex(left_str[0]) > getIndex(right_str[1])) {
					System.out.println(total_str[0]);
				} else {
					System.out.println(total_str[1]);
				}
			} else if (right_str_length == 4 || total_str[1].equals("joker JOKER")) {
				System.out.println(total_str[1]);
			} else {
				System.out.println("ERROR");
			}
			break;
		default:
			break;
		}
	}

	/**
	 * 比较相同类型两手牌的牌面大小，返回在数组中的下标
	 * 
	 * @param str要查找的牌面字符
	 */
	public static int getIndex(String str) {
		String[] datas = { "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A", "2", "joker", "JOKER" };
		int length = datas.length;
		for (int i = 0; i < length; i++) {
			if (str.equals(datas[i])) {
				return i;
			}
		}
		return -1;
	}

	//数组转序列，获取下标的方法//
	public static int getIndex2(String str) {
		String[] datas = { "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A", "2", "joker", "JOKER" };
		List<String> resultList = new ArrayList<>(datas.length);
		Collections.addAll(resultList, datas);
		if (resultList.contains(str)) {
			return resultList.indexOf(str);
		} else {
			return -1;
		}
	}

}