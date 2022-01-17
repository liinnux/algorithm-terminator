/**
 * 
 */
package com.tompai.string;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;
/**
 * @author Administrator
 *
 */
public class Editor {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        for(int i=0;i<n;i++){
            String s=sc.next();
            StringBuffer sb=new StringBuffer();
            for(int j=0;j<s.length();j++){
                if(sb.length()<2){
                    sb.append(s.charAt(j));
                    continue;
                }
                if(sb.length()>=2){
                    if(s.charAt(j)==sb.charAt(sb.length()-1) &&s.charAt(j)==sb.charAt(sb.length()-2))
                        continue;
                     
                }
                if(sb.length()>=3){
                    if(s.charAt(j)==sb.charAt(sb.length()-1) &&sb.charAt(sb.length()-3)==sb.charAt(sb.length()-2))
                          continue;
                }
                sb.append(s.charAt(j));
            }
            System.out.println(sb.toString());
        }
	}
	
	public static void test1main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String inStr1 = scanner.nextLine();
        String inStr2 = scanner.nextLine();
        String[] arr = inStr1.split(" ");
        int buildingNums = Integer.parseInt(arr[0]);
        int distance = Integer.parseInt(arr[1]);
        List<Integer> buildingSites = Stream.of(inStr2.split(" ")).map(Integer::parseInt).collect(Collectors.toList());
        long result = 0;
        int j = 2;
        int i = 0;
        while (i != buildingNums - 2) {
            long nums = 0;// 第i个数字开始，符合的数字数
            while (true) {
                if (buildingSites.get(j) - buildingSites.get(i) <= distance) {// 符合
                    nums = j - i;
                    if (j < buildingNums - 1) {// 如果不是最后一个就加1
                        j++;
                    } else {
                        break;
                    }
                } else {// 不符合
                    if (nums == 0 && j > i + 2) {// 上一个肯定符合
                        nums = j - i - 1;
                    }
                    break;
                }
            }
            if (nums > 0) {
                result += (nums * (nums - 1) / 2) % 99997867;
            }
            i++;
        }
        System.out.println(result % 99997867);
        scanner.close();
    }

}


