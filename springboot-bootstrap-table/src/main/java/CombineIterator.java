
import java.util.Iterator;
import java.util.LinkedList;

//java组合算法（非递归）

@SuppressWarnings("rawtypes")
public class CombineIterator implements Iterator {
	//源数据
	private int[] source;
	//结果数组大小
	private int resultSize;
	//结果数组个数
	private int size;
	//当前元素索引
	private int[] index;
	//当前序列索引
	private int offset = 0;
	
	public CombineIterator(int[] source , int resultSize){
		if(source == null) throw new NullPointerException();
		int n = source.length;
		if(n < resultSize || resultSize <= 0) throw new IllegalArgumentException("size : " + n + ", m : " + resultSize);
		this.source = source;
		this.size = clacSize(n, resultSize);
		this.resultSize = resultSize;
		this.index = new int[resultSize];
		for(int i=0;i<resultSize;i++){
			this.index[i] = i;
		}
		this.index[resultSize-1] -= 1;
	}
	 
	
	/**
	 * n中选m
	 * @param n
	 * @param m
	 * @return
	 */
	private int clacSize(int n ,int m){
		return Factorial.factorial(n-m+1,n).divide(Factorial.factorial(m)).intValue();
	}
	
	/**
	 * 获取迭代器内元素总数
	 * @return
	 */
	public int size(){
		return size;
	}
	
	public boolean hasNext() {
		return offset < size;
	}

	@Override
	public int[] next() {
		int idx = resultSize - 1;
		int n = source.length;
		if(index[idx] < n - 1){
			index[idx] += 1;
		}else{
			idx -= 1;
			while(idx > 0 && index[idx] == index[idx + 1] -1){
				idx -= 1;
			}
			index[idx] += 1;
			for(int i = idx + 1;i<= resultSize -1;i++){
				index[i] = index[idx] + (i - idx);
			}
		}
		int[] result = new int[resultSize];
		for(int i=0;i<=resultSize-1;i++){
			result[i] = source[index[i]];
		}
		offset++;
		return result;
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}

	
	public static void main(String[] args) {
		long t1 = System.currentTimeMillis();
		//N中取M进行组合的算法//
		int N=1000;
		int[] source = new int[N];
		for(int i= 1;i<=N;i++){
			source[i-1] = i;
		}
		int M = 3;
		CombineIterator itr = new CombineIterator(source, M);
		LinkedList<int[]> list = new LinkedList<int[]>();
 		while(itr.hasNext()){
			int [] a = itr.next();
			for(int e:a) {
				System.out.print(e+"-");
			}
			System.out.println();
			list.add(a);
		}
		long t2 = System.currentTimeMillis();
		System.out.println("耗时:" + (t2 - t1));
		//44~48ms
		System.out.println("总计:" + list.size());
	}
}

