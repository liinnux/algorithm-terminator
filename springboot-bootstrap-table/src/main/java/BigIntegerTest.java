import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class BigIntegerTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try(Scanner scanner = new Scanner(System.in)) {
			String str;
			int base = 16;
			while (scanner.hasNext()) {
				str = scanner.nextLine();
				if (str.startsWith("0X")||str.startsWith("0x")) {
					str = str.substring(2);
					if (checkNum(str)) {
						BigInteger num=new BigInteger(str, base);
						System.out.println(num.bitCount());
					}else {
						//System.out.println(str);
					}
				}
				
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
		}
		
		
	}

	public static boolean checkNum(String str) {
		// TODO Auto-generated method stub
		char[] base= {'0','1','2','3',
		              '4','5','6','7',
		              '7','8','9','a',
		              'b','c','d','e','f'};
		
		if (str.length()>0) {
			char[] nums=str.toLowerCase().toCharArray();
			int k=nums.length;
			List<Character> list=new ArrayList<>();
			for(int i=0;i<base.length;i++) {
				list.add(base[i]);
			}
			
			int i=0;
			for(i=0;i<k;i++) {
				char t=nums[i];
				if (!list.contains(t)) {
					break;
				}
			}
			if (i==k) {
				return true;
			}
		}
		return false;
	}
}
