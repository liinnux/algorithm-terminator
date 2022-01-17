package zeroonepackage;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ManySumN1 {

	static List<List<Integer>> lists = new ArrayList<>();
    static List<Integer> result = new LinkedList<>();

    public static void main(String[] args) {
    	
    	int N=100;
        int arr[] = new int[N];
        for(int i=0;i<N;i++){
            arr[i] = i+1;
        }
        //100是查找和为100的数字，
        //0是数组开始位置，
        //arr.length-1是数组结束位置
        findList(N,arr,0,arr.length-1);
        //
        for(List<Integer> list :lists){
            System.out.println(list);
        }
    }


    /*
    sun要找的和为定值的数，
    arr数组是找数的范围，
    start是标记当前递归的下标，
    end是用来跟start判断的如果小于start就结束递归
    */
    public static void findList(int sum, int[] arr, int start, int end) {
        if (start >= end) {
            return;
        }
        if (sum == arr[start]) {
            ArrayList<Integer> list = new ArrayList<>();
            for(Integer num :result){
                list.add(num);
            }
            list.add(arr[start]);
            lists.add(list);
        } else {
            if (sum > arr[start]) {
                result.add(arr[start]);
                findList(sum - arr[start], arr, start + 1, end);
                result.remove(result.size()-1);
            }
            findList(sum, arr, start + 1, end);
        }
    }
}
