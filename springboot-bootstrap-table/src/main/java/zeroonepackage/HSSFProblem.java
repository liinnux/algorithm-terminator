package zeroonepackage;

import java.util.Arrays;
import java.util.Collections;

public class HSSFProblem {

    // 待选择的物品
    private Knapsack[] bags;
    // 背包的总承重
    private int totalWeight;
    // 背包的当前承重
    private int currWeight;
    // 待选择物品数量
    private int n;
    // 放入物品后背包的最优价值
    private int bestValue;
    // 放入物品和背包的当前价值
    private int currValue;

    public HSSFProblem(Knapsack[] bags, int totalWeight) {
        this.bags = bags;
        this.totalWeight = totalWeight;
        this.n = bags.length;

        // 物品依据单位重量价值从大到小进行排序
        Arrays.sort(bags, Collections.reverseOrder());
    }

    public int solve(int i) {

        // 当没有物品可以放入背包时，当前价值为最优价值
        if (i >= n) {
            bestValue = currValue;
            return bestValue;
        }

        // 首要条件：放入当前物品，判断物品放入背包后是否小于背包的总承重
        if (currWeight + bags[i].getWeight() <= totalWeight) {
            // 将物品放入背包中的状态
            currWeight += bags[i].getWeight();
            currValue += bags[i].getValue();

            // 选择下一个物品进行判断
            bestValue = solve(i + 1);

            // 将物品从背包中取出的状态
            currWeight -= bags[i].getWeight();
            currValue -= bags[i].getValue();
        }

        // 次要条件：不放入当前物品，放入下一个物品可能会产生更优的价值，则对下一个物品进行判断
        // 当前价值+剩余价值<=最优价值，不需考虑右子树情况，由于最优价值的结果是由小往上逐层返回，
        // 为了防止错误的将单位重量价值大的物品错误的剔除，需要将物品按照单位重量价值从大到小进行排序
        if (currValue + getSurplusValue(i + 1) > bestValue) {
            // 选择下一个物品进行判断
            bestValue = solve(i + 1);
        }
        return bestValue;
    }

    // 获得物品的剩余总价值
    public int getSurplusValue(int i) {
        int surplusValue = 0;
        for (int j = i; j < n; j++)
            surplusValue += bags[i].getValue();
        return surplusValue;
    }

}
