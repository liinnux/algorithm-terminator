/**
 * 
 */
package zeroonepackage;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Administrator
 *
F(1)=0 N=1
F(N)=[F(N-1)+M]%n （N>1）
 */
/*
 约瑟夫环描述：已知n个人（以编号1，2，3...n分别表示）围坐在一张圆桌周围。从编号为k的人开始报数，数到m的那个人出列；他的下一个人又从1开始报数，数到m的那个人又出列；依此规律重复下去，直到圆桌周围的人全部出列。

举例：　n = 9, k = 1, m = 5

【解答】出局人的顺序为5, 1, 7, 4, 3, 6, 9, 2, 8。
 */
public class Josephus {
	public static void main(String[] args) {
	    int a=11;
	    int b=3;
	    LinkedList<Integer> list = new LinkedList<>();
	    for (int i = 0; i < a; i++) {
	      list.add(i+1);
	    }
	    while (list.size()>1){
	      for (int i = 0; i < b-1; i++) {
	    	  
	        list.add(list.remove());
	      }
	      System.out.print("->"+list.getFirst());
	      //remve head
	      list.remove();
	    }
	    System.out.println();
	    System.out.println(list.getFirst());
	  }
	
	public static void Josephus2(String[] args) {
		List alist = new ArrayList();
		
		System.out.println("请输入约瑟夫环  中 总个数数：");
		Scanner sca = new Scanner(System.in);
		int N = sca.nextInt();
		// 提示输入要出圈的数值
		System.out.println("请输入要出圈的数值：");
		int X = sca.nextInt();
		System.out.println("按出圈的次序输出序号：");
		
		for(int i=0 ;i<N;i++){
			alist.add(i+1);
		}
		
		int i=-1;
		int countX=0;
		while(alist.size()!=0){
			++i;
			if(i== alist.size()){
				i=0;
			}
			++countX;
			if(countX==X){//输出
				System.out.print(alist.get(i)+ " ");
				alist.remove(i);
				countX=0;
				i--;//修正
			}
		}
		
	}
	
	/**
	   * 递归式:
	   * f(1)=0; 第一个位置永远为0
	   * f(i)=f(i-1)+m%n; i>1
	   */
	  public static int yuesefu(int n,int m){
	    if(n==1){
	      return 0;
	    }else {
	      return (yuesefu(n-1,m) + m) % n;
	    }
	  }
	  
	  public static void yuesefu2(String[] args) {
	    System.out.println(yuesefu(41,3)+1);
	    vailCode(41,3);
	  }
	 
	  //逆推验证代码
	  public static void vailCode(int a,int b){
	    System.out.print(b);
	    int reslut;
	    for (int i = a; i >=2 ; i--) {
	       reslut=2;
	      for (int j = i; j <=a ; j++) {
	        reslut=(reslut+b)%j;
	      }
	      System.out.printf("->%d",reslut+1);
	    }
	  }
}
