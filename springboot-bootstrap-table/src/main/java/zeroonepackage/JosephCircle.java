/**
 * 
 */
package zeroonepackage;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Administrator
 *
 */
public class JosephCircle {
	public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while(in.hasNext()){
            int peopleNum = in.nextInt();
            int k = in.nextInt();
            joseph(peopleNum, k);
        }
        in.close();

    }

    private static void joseph(int peopleNum, int k) {
        //初始化人的ID
        List<Integer> circle = new LinkedList<>();
        for(int i =1; i <= peopleNum; i++){
            circle.add(i);
        }

        //从第flag个开始计数
        int flag = 0;
        while(circle.size() > 0){
            flag = flag + k;

            //第k个人的索引位置
            flag = flag % (circle.size()) - 1; //这个可以实现循环！！！！

            //判断是否到队尾，处理特殊情况，把flag置0
            if(flag < 0){
                System.out.println(circle.get(circle.size()-1));
                circle.remove(circle.size() -1);
                flag = 0;
            }
            else{
                System.out.println(circle.get(flag));
                circle.remove(flag);
            }
        }
    }
}
