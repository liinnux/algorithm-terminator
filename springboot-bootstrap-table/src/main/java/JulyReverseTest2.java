import java.math.BigInteger;

public class JulyReverseTest2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//十六进制转成十进制
		String str="abcdeffedabcd";
		//int num=Integer.parseInt(str,16);
		
		//System.out.print(num);
		
		Manacher_ShortestEnd manacher=new Manacher_ShortestEnd();
		
		String t1=manacher.shortestEnd(str);
		
		System.out.print(t1);
		
	}

}
