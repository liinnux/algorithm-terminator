/**
 * 
 */
package com.tompai.t1226;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Administrator
 */
public class Demo7_JumpStepProblem {

	//方法1：递归
    public int getFibonacci1(int n){
        if(n < 0)
            return -1;
        if(n <= 2)
            return n;
        return getFibonacci1(n-1)+getFibonacci1(n-2);
    }
    
    public int getFibonacci2(int n){
        if(n < 0)
            return -1;
        if(n <= 2)
            return n;
        if(n <= 3)
            return n+1;
        return getFibonacci2(n-1)+getFibonacci2(n-2)+getFibonacci2(n-3);
    }
    
  //解法2：迭代
    public int getRecursion(int n){
    	if(n < 0)
            return -1;
        if(n <= 2)
            return n;
        int temp1 = 1;
        int temp2 = 2;
        int result = 0;
        for(int i = 3;i <= n;i++){
            result = temp1 + temp2;
            temp1 = temp2;
            temp2 = result;
        }
        return result;
    }
    
    
    public static void main(String[] args){
    	Demo7_JumpStepProblem test = new Demo7_JumpStepProblem();
        System.out.println("使用递归法求解结果："+test.getFibonacci2(13));
    }

}
