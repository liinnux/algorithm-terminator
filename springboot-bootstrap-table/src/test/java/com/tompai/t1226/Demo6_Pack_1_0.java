/**
 * 
 */
package com.tompai.t1226;

import java.util.Scanner;

/**
 * @author Administrator
 * 降维
 */
public class Demo6_Pack_1_0 {

	 // N表示物体的个数，V表示背包的载重
    int N,V;
    //用于存储每个物体的重量，下标从1开始
    private int[] weight;
    //存储每个物体的收益，下标从1开始
    private int[] value;
    //降成一维数组，用来保存每种状态下的最大收益
    private int[] F;
    
    public static void main(String[] args) {
    	Demo6_Pack_1_0 zope1 = new Demo6_Pack_1_0();
        zope1.init();
        zope1.ZeroOnePackNonRecursive();
    }
 
    /**
     * 使用非递归方式
     */
    public void ZeroOnePackNonRecursive() {
        //对一维数组F进行初始化
        for(int i = 0; i <= V; i++) {
            F[i] = 0;
        }
 
        //注意边界问题，i是从1开始的
        for(int i = 1; i <= N; i++) {
            for(int j = V; j >= 0; j--) {
                //降序遍历
                if(j >= weight[i]) {
                    F[j] = Math.max(F[j - weight[i]] + value[i], F[j]);
                }else {
                    //可以省略
                    F[j]= F[j];
                }
            }
        }
 
        //打印所有结果，我们要求的是F[V]
        for(int i = 0; i <= V; i++) {
            System.out.print(F[i] + " ");
        }
        System.out.println();
    }
 
 
    /**
     * 输入格式：
     5 10
     2 2 6 5 4
     6 3 5 4 6
     * result:15
     * 第一行是物体个数、背包总空间；
     * 第二行是每个物体的空间；
     * 第三行是每个物体的收益。
     */
    public void init() {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        V = sc.nextInt();
 
        //下标从1开始，表示第1个物品
        weight = new int[N + 1];
        value = new int[N + 1];
        F= new int[V + 1];//注意是 V + 1
 
        for(int i = 1; i <= N; i++) {
            weight[i] = sc.nextInt();
        }
 
        for(int i = 1; i <= N; i++) {
            value[i] = sc.nextInt();
        }
    }

}
