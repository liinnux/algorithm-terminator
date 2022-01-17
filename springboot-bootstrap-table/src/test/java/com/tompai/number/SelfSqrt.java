/**
 * 
 */
package com.tompai.number;

/**
 * @author Administrator
 *
 */
public class SelfSqrt {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	int sqrt(int x) {
        // write code here
        if(x==1) return 1;
        int left = 1,right = x; //左右边界
        while(right>=left){
            int mid = (right+left)/2;  //中间值
            if(mid<=x/mid&&(mid+1)>x/(mid+1)) return mid; // mid*mid可能溢出，所以用除法
            if(mid>x/mid) right = mid -1;
            else  left = mid +1;
        }
        return 0;
    }
	
	int sqrt2(int x) {
        if(x<=0) return 0;  //小于等于0 返回0
        int ans = 1;
        int num = 1;
        int  i = 3;
        while(num+i<=x){
            num+=i; 
            ans ++; // 每加一个奇数，ans+1
            i += 2;
        }
        return ans;
    }
}
