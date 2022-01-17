/**
 * 
 */

/**
 * @author Administrator
 *
 */
public class JumpFloorII {
	
	public int jumpFloor(int target) {
		if (target == 0 || target == 1) {
			return 1;
		}
		return 2 * jumpFloor(target - 1);
	}

	//  2^(n-1)
	public int jumpFloorII(int target) {
		if (target == 0) {
			return 1;
		}
		// return (int)Math.pow(2, target - 1);
		// 1左移target位 = 2^target
		return 1 << (target - 1);
	}

	public static void main(String[] args) {
		JumpFloorII instance = new JumpFloorII();
		System.out.println(instance.jumpFloor(3));
		System.out.println(instance.jumpFloorII(3));
	}
}
