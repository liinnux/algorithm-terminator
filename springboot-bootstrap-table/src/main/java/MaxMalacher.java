import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MaxMalacher {
	
	public static int Palindrome(List<Character> list, int mid,int l,int r){
        //匹配得到回文串中点到一端的距离
        while(l>=0&&r<list.size()&&list.get(l).equals(list.get(r))){
            //集合里面是包装类，不要用==
            l--;
            r++;
        }
        if(r==mid+1)
            return 0;
        return r-mid-1;//中点右边元素个数，r最后++了一次，所以需要-1
    }
    public static String longestPalindrome(String s) {
        int len=s.length();
        List<Character> list=new ArrayList(len*2+1);
        for(int i=0 ;i<len ;i++){
            list.add('#');
            list.add(s.charAt(i));
        }
        list.add('#');
        int[] ln=new int [len*2+1];
        int r=-1; //最右回文串右端角标
        int mid=-1;//最右回文串中点坐标
        int maxMid=-1;//最长回文串中间坐标
        int maxLen=-1;//最长回文串一端长度；
        for(int j=0 ; j <len*2+1 ; j++){
            if(j<r){
                int leftLen=ln[2*mid-j];
                if(leftLen< r-j){ 
                    //对称左边回文串长度没有超出以r为右端点的回文串范围
                    ln[j]=leftLen;
                }else{ 
           //对称左边回文串长度超出范围不知道匹配不匹配，对超出部分进行匹配校验
                    ln[j]=Palindrome(list,j,j-(r-j),j+r-j);
                }
            }else{
                //完全没有左边堆成回文串需要无脑匹配
                ln[j]=Palindrome(list,j,j-1,j+1);//一半长度；
            }
            if(j+ln[j]>r){ //当前回文串右端与保存的右端取大
                r=j+ln[j];
                mid=j;
            }
            if(maxLen<ln[j]){ //得到目前最长回文子串
                maxLen=ln[j];
                maxMid=j;
            }
        }
        StringBuilder sb=new StringBuilder();
        for(int z=maxMid-maxLen ; z<= maxMid+maxLen ;z++){
            //去除#，得到最长回文子串
            char ch=list.get(z).charValue(); 
            if(ch!='#')
                sb.append(ch);
        }
        return sb.toString();
    }
    
    
	public static int getPalindromeLength(String str) {
		// 1.构造新的字符串
		// 为了避免奇数回文和偶数回文的不同处理问题，在原字符串中插入'#'，将所有回文变成奇数回文
		StringBuilder newStr = new StringBuilder();
		newStr.append('#');
		for (int i = 0; i < str.length(); i++) {
			newStr.append(str.charAt(i));
			newStr.append('#');
		}
		// rad[i]表示以i为中心的回文的最大半径，i至少为1，即该字符本身
		int[] rad = new int[newStr.length()];
		// right表示已知的回文中，最右的边界的坐标
		int right = -1;
		// id表示已知的回文中，拥有最右边界的回文的中点坐标
		int id = -1;
		// 2.计算所有的rad
		// 这个算法是O(n)的，因为right只会随着里层while的迭代而增长，不会减少。
		for (int i = 0; i < newStr.length(); i++) {
			// 2.1.确定一个最小的半径
			int r = 1;
			if (i <= right) {
				r = Math.min(rad[id] - i + id, rad[2 * id - i]);
			}
			// 2.2.尝试更大的半径
			while (i - r >= 0 && i + r < newStr.length() && newStr.charAt(i - r) == newStr.charAt(i + r)) {
				r++;
			}
			// 2.3.更新边界和回文中心坐标
			if (i + r - 1 > right) {
				right = i + r - 1;
				id = i;
			}
			rad[i] = r;
		}
		// 3.扫描一遍rad数组，找出最大的半径
		int maxLength = 0;
		for (int r : rad) {
			if (r > maxLength) {
				maxLength = r;
			}
		}
		return maxLength - 1;
	}

	public static void main(String[] args) throws FileNotFoundException {
		int caseNum = 0;
		Scanner sc = new Scanner(System.in);
		while (true) {
			String str = sc.nextLine();
			if (str.equals("END")) {
				break;
			} else {
				caseNum++;
				System.out.println("Case " + caseNum + ": " + longestPalindrome(str));
			}
		}
	}
}
