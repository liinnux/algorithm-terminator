import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JulyReverseTest1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//=================================
		String st1="Heello Word.";
		String st2="Come on."; 
		//=================================
		char[] arr1=st1.toCharArray();
		char[] arr2=st2.toCharArray();
		//=================================
		List<Character> list1=new ArrayList<>();
		for(int i=0;i<arr1.length;i++) {
			list1.add(arr1[i]);
		}
		//=================================
		List<Character> list2=new ArrayList<>();
		for(int i=0;i<arr2.length;i++) {
			list2.add(arr2[i]);
		}
		
		list2.retainAll(list1);
		for(int i=0;i<list2.size();i++) {
			System.out.print(list2.get(i));
		}
		System.out.println("----------");
		//================================
		Collections.reverse(list1);
		for(int i=0;i<arr1.length;i++) {
			System.out.print(list1.get(i));
		}
		//=================================
		System.out.println("----------");
		Collections.swap(list1,1,3);
		for(int i=0;i<arr1.length;i++) {
			System.out.print(list1.get(i));
		}
		//===============================
		System.out.println("----------");
		Collections.rotate(list1, -1);
		for(int i=0;i<arr1.length;i++) {
			System.out.print(list1.get(i));
		}
		System.out.println("----------");
		//===============================
		
	}

}
