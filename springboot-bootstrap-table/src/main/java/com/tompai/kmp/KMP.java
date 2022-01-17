package com.tompai.kmp;

import java.util.Random;

public class KMP {
    /**
     * @param s1
     * 主串
     * @param s2
     * 模式串
     * @return 如果匹配成功，返回下标，否则返回-1
     * 
     * 返回s1在s2中下标的位置//
     */
 
    public static int KMP(String s1,String s2){
        if(s1.length()<s2.length()||s2.length()==0||null==s2) return -1;
        //求模式串的next数组
        int[] next=new int[s2.length()];
        next[0]=-1;
        for(int i=1,k=-1;i<s2.length();i++){
            if(k==-1||s2.charAt(i-1)==s2.charAt(k)){
                k++;
                next[i]=k;
            }
            else {
                next[i]=0;
                k=0;
            }
        }
        //进行匹配
        for(int i=0,j=0;i<s1.length();){
            if(s1.charAt(i)!=s2.charAt(j)){
                if(next[j]==-1) i++;
                else j=next[j];
            }
            else{
                if(j==s2.length()-1) return i-s2.length()+1;
                i++;j++;
            }
        }
        return -1;
    }
    public static String getRandomString(int length) {//length表示生成字符串的长度
        String base = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }
    
    public static void main(String args[]){
       boolean falg=true;
       int n=0;
        while(n++ <10){
            String s1=getRandomString(new Random().nextInt(30));
            String s2=getRandomString(new Random().nextInt(20)+1);
            String s3=getRandomString(new Random().nextInt(10));
            String s=s1+s3+s2+s1+s2;
            System.out.println(s);
            System.out.println(s2);
            System.out.println("--------");
            if(KMP(s,s2)!=s.indexOf(s2)) {
                System.out.println("s:" + s + " ,s2:" + s2);
                falg = false;
            }
        }
        if(falg) {
        	System.out.println("方法无误！");
        }
    }
}