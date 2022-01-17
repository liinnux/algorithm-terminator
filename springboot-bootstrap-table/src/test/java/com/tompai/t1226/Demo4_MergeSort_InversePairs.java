/**
 * 
 */
package com.tompai.t1226;

import java.util.Arrays;

/**
 * @author Administrator
 *
 */
public class Demo4_MergeSort_InversePairs {
	static int count;    //统计逆序对的数目：写在外层是因为下面多个函数会用到
	
	public static void main(String[] args) {
		int[] arr = { 110, 9, 8, 7, 6, 5, 4, 3, 2, 1 };
		InversePairs(arr);
		System.out.println(Arrays.toString(arr));
		System.out.println(count);
	}

	
    public static int InversePairs(int [] array) {
        //归并排序
        count=0;
        if(array!=null){
            MergeSort(array,0,array.length-1);
        }
        return count;
        
    }
    
    //归并排序---将数组分成多个小数组，本题使用二路归并，递归实现两个数组的排序
    public static void MergeSort(int[] a,int start,int end){
        //递归出口
        if(start>=end){
            return;
        }
        int mid=(start+end)>>1;
        MergeSort(a,start,mid);
        MergeSort(a,mid+1,end);
        //最终再合并成一个数组
        Merge(a,start,mid,end);
    }
    
    //合并:将一个数组中的两个相邻有序区间合并成一个
    public static void Merge(int[] a,int start,int mid,int end){
        //1.定义一个辅助数组装合并后的元素
        int[] tmp=new int[end-start+1];
        //2.定义三个指针：两个分别指向子数组的开始元素，一个指向辅助数组的开始元素
        int p1=start, p2=mid+1, p=0;
        //3.比较p1,p2指向元素的大小，移动指针,直到某一个序列遍历完！
        while(p1<=mid && p2<=end){
            if(a[p1]<=a[p2]){    //前面比后面小：说明没有逆序对,把p1装进辅助数组，并后移p1和p
                tmp[p++]=a[p1++];
            }
            else{                //前面比后面大：说明有逆序对,把p2装进辅助数组，并后移p2和p
                tmp[p++]=a[p2++];
                count=(count+mid+1-p1)%1000000007; //逆序对个数等于第二个数组开始下标-p1
            }
        }
        //4.如果某个序列未检测完，直接将后面所有元素加到合并的序列中
        while(p1<=mid){
            tmp[p++]=a[p1++];
        }
        while(p2<=end){
            tmp[p++]=a[p2++];
        }
        //5.最终把辅助数组里的元素再赋回a[]
        for(p=0;p<tmp.length;p++){
            a[start+p]=tmp[p];
        }
    }

}
