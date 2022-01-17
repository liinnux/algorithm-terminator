package com.tompai.t1236;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Demo6_LastKNmubers<K, V> {

	public static int[] GetLeastNumbers_Solution(int [] input, int k) {
        if(input.length==0||k>input.length){
            int a[] = {};
            return a;
        }
        int res[] = new int[k];
        for(int i=0;i<k;i++){
            int min = input[i];
            int minIndex = i;
            for(int j=i+1;j<input.length;j++){
                if(min>input[j]){
                    min = input[j];
                    minIndex = j;
                }
            }
            input[minIndex] = input[i];
            input[i] = min;
        }
        for(int i=0;i<k;i++){
            res[i] = input[i];
        }
        return res;
    }
     
    public static void main(String args[]) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = "";
        while((str=br.readLine())!=null){
            String arr[] = str.split(",");
            int k = Integer.parseInt(arr[arr.length-1]);
             
            int input[] = new int[arr.length-1];
            if(!arr[0].equals("[]")){
                for(int i=0;i<input.length;i++){
                    if(arr[i].startsWith("[")){
                        input[i] = Integer.parseInt(arr[i].substring(1,arr[i].length()));
                    }else if(arr[i].endsWith("]")){
                        input[i] = Integer.parseInt(arr[i].substring(0,arr[i].length()-1));
                    }else{
                        input[i] = Integer.parseInt(arr[i]);
                    }
                }
                int res[] = GetLeastNumbers_Solution(input,k);
                String s = "";
                if(res.length>0){
                    for(int i=0;i<res.length;i++){
                        s+=res[i]+",";
                    }
                    System.out.println("["+s.substring(0,s.length()-1)+"]");
                }else{
                    System.out.println("[]");
                }
                 
            }else{
                System.out.println("[]");
            }
             
        }
    }
}
