/**
 * 
 */
package com.tompai.t1235;

import static org.hamcrest.CoreMatchers.nullValue;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * @author Administrator
 *
 */
public class Demo1_MostKmaxNumbers {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//[10,10,9,9,8,7,5,6,4,3,4,2],12,3

		Scanner scanner = new Scanner(System.in);

		while (scanner.hasNextLine()) {
			String s = scanner.next();
			System.out.println(s);

			String[] str = s.replace("[", "").replace("]", "").split(",");

			int[] data = new int[str.length];

			if(data.length<3) {
				return;
			}
			for (int i = 0; i < data.length; i++) {
				data[i] = Integer.parseInt(str[i].trim());
			}
			int[] nums=new int[data.length-2];
			int k=data[data.length-1];
			int total=data[data.length-2];
			if (k>total) {
				return;
			}
			System.arraycopy(data, 0, nums, 0, nums.length);
			int m=finfKmaxNumber(nums,k);
			System.out.println(m);
		}
	}

	
	public static int finfKmaxNumber(int[] a,int K){
		
		PriorityQueue<Integer> heap=new PriorityQueue<>((o1,o2)->o1.compareTo(o2));
		for(int i:a) {
			heap.add(a[i]);
			if (heap.size()>K) {
				heap.poll();
			}
		}
		return heap.peek();
	}
	
	public static int findK(int[] a, int start, int end, int k){

        // 比第一个元素大的放左边，小的放右边
        int p = partition(a, start, end);

        if(p+1 < k){
            return findK(a, p+1,end, k);
        }

        if(p+1 > k){
            return findK(a, start, p-1, k);
        }

        return a[p];
    }

    public static int partition(int[] a, int start, int end){

        int i = start;
        int j = end + 1;
        int V = a[start];

        while(true){
            while(a[++i] > V){ // 直到比V小
                if(i>=end) break;
            }

            while(a[--j] < V){// 直到比V大
                if(j<=start) break;
            }

            if(i >= j)break;

            // 交换i，j位置的值
            exchange(a,i,j);
        }

        exchange(a,start,j);

        return j;
    }

    private static void exchange(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
