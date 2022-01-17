package com.tompai.t1236;

import java.math.BigInteger;
import java.util.Scanner;

public class Demo8_BigNumber<K, V> {

	//以字符串的形式读入两个数字，编写一个函数计算它们的和，以字符串形式返回。
	//"1","99"
	

	public static void main(String[] args) {

		try (Scanner scanner = new Scanner(System.in)) {
			{
				while (scanner.hasNext()) {

					String input = scanner.nextLine();
					input=input.replaceAll("\\\"", "");
					String[] ss = input.split(",",-1);
					String res=add(ss[0], ss[1]);
					System.out.println(res);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
	}
	
    
    /**
     * 思路：
     * 字符串逐位相加，需要进位则进位处理，考虑两个问题：
     * 1、char怎么转换为integer, 减去'0'即可
     * 2、怎么处理对应位相加?反转字符串相加，正确处理进位即可，
     *    这样个位对应个位，十位对应十位，剩余的直接追加
     */
    public static String add(String s, String t) {
        // 一个字符串为空 直接返回另外一个
        if (s == null || "".equals(s)) {
            return t;
        }
        if (t == null || "".equals(t)) {
            return s;
        }

        StringBuilder sb = new StringBuilder();

        // 字符串都不为空时
        char[] a = s.toCharArray();
        char[] b = t.toCharArray();
        int ida = a.length - 1;
        int idb = b.length - 1;

        // 缓存是否需要进位
        boolean carry = false;
        // 遍历两个字符串 处理元素相加
        while (ida>=0 && idb >=0) {
            char cur1 = a[ida];
            char cur2 = b[idb];
            // 相加
            int sum = cur1 - '0' + cur2 - '0';
            // 上一次运算是否有进位
            sum = carry ? sum+1: sum;
            // 是否需要进位
            carry = sum >= 10 ? true : false;
            // 相加结果 取个位数->字符类型
            sb.append((char)((sum%10) + '0'));
            // 索引递减
            ida--;
            idb--;
        }

        // 处理剩余的元素
        while (carry || ida >= 0 || idb >= 0) {
            if (ida >= 0) {
                // 当前元素处理
                int sum = a[ida] - '0' + (carry ? 1 : 0);
                // 下次是否需要进位
                carry = sum >= 10 ? true : false;
                // 添加到结果
                sb.append((char)((sum%10) + '0'));
                ida--;
            } else if (idb >= 0) {
                // 当前元素处理
                int sum = b[idb] - '0' + (carry ? 1 : 0);
                // 下次是否需要进位
                carry = sum >= 10 ? true : false;
                // 添加到结果
                sb.append((char)((sum%10) + '0'));
                idb--;
            } else {
                sb.append('1');
                carry = false;
            }
        }

        // 反转sb 后返回
        return sb.reverse().toString();
    }
    
}
