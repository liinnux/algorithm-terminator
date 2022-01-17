package zeroonepackage;

public class HSSFTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Knapsack[] bags = new Knapsack[] { 
				new Knapsack(2, 13),
	            new Knapsack(1, 10), 
	            new Knapsack(3, 24), 
	            new Knapsack(2, 15),
	            new Knapsack(4, 28), 
	            new Knapsack(5, 33), 
	            new Knapsack(3, 20),
	            new Knapsack(1, 8) };
	    int totalWeight = 12;
	    HSSFProblem problem = new HSSFProblem(bags, totalWeight);

	    System.out.println(problem.solve(0));
	}

}
