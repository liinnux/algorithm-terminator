/**
 *
 */
package com.tompai.array;

import java.util.*;

/**
 * @author Administrator
 *
 */
public class CommonArray {

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub

        int[] arrs = {4, 5, 1, 3, 2};
        CommonArray test = new CommonArray();
        System.out.print(test.maxWater(arrs));
    }

    /*
     给定一个整形数组arr，已知其中所有的值都是非负的，将这个数组看作一个柱子高度图，
     计算按此排列的柱子，下雨之后能接多少雨水。(数组以外的区域高度视为0)
     */
    public long maxWater(int[] arr) {
        int len = arr.length;
        if (len < 3)
            return 0;
        long res = 0;
        int left = 0, right = len - 1;
        int lm = arr[0], rm = arr[right];
        while (left < right) {
            if (arr[left] <= arr[right]) {
                if (arr[left] >= lm) {
                    lm = arr[left];
                } else {
                    res += lm - arr[left];
                }
                left++;
            } else {
                if (arr[right] >= rm) {
                    rm = arr[right];
                } else {
                    res += rm - arr[right];
                }
                right--;
            }
        }
        return res;
    }

    /*
     给一个长度为 n 的数组，数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。
    例如输入一个长度为9的数组[1,2,3,2,2,2,5,4,2]。由于数字2在数组中出现了5次，超过数组长度的一半，因此输出2。

    数据范围：n \le 50000n≤50000，数组中元素的值 0 \le val \le 100000≤val≤10000
    要求：空间复杂度：O(1)O(1)，时间复杂度 O(n)O(n)
     */
    public int moreThanHalfNum(int[] array) {
        int voted = 1;
        int res = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] == res) {
                voted++;
            } else {
                voted--;
                if (voted == 0) {
                    voted = 1;
                    res = array[i];
                }
            }
        }
        return res;
    }

    public int moreThanHalfNum2(int[] array) {
        // 数组排序
        Arrays.sort(array);
        // 返回数组中间的数字
        return array[array.length / 2];
    }

    /*
     给定两个递增数组arr1和arr2，已知两个数组的长度都为N，求两个数组中所有数的上中位数。
    上中位数：假设递增序列长度为n，为第n/2个数
     */
    public int findMedianinTwoSortedAray(int[] arr1, int[] arr2) {
        // write code here
        int n = arr1.length;
        int left = 0;
        int right = n;
        while (left < right) {
            int i = left + ((right - left) >> 1);
            int j = n - i;
            if (arr1[i] < arr2[j - 1]) {
                left = i + 1;
            } else {
                right = i;
            }
        }
        int bound1 = left;
        int bound2 = n - left;
        if (bound1 == n) {
            return arr1[n - 1];
        }
        if (bound2 == n) {
            return arr2[n - 1];
        }
        return Math.max(arr1[bound1 - 1], arr2[bound2 - 1]);
    }

    /* 给定一个长度为 n 的数组 num 和滑动窗口的大小 size ，找出所有滑动窗口里数值的最大值。
     例如，如果输入数组{2,3,4,2,6,2,5,1}及滑动窗口的大小3，
     那么一共存在6个滑动窗口，他们的最大值分别为{4,4,6,6,6,5}；
     针对数组{2,3,4,2,6,2,5,1}的滑动窗口有以下6个：
      {[2,3,4],2,6,2,5,1}， {2,[3,4,2],6,2,5,1}，
      {2,3,[4,2,6],2,5,1}， {2,3,4,[2,6,2],5,1}，
      {2,3,4,2,[6,2,5],1}， {2,3,4,2,6,[2,5,1]}。
     窗口大于数组长度或窗口长度为0的时候，返回空。*/
    public ArrayList<Integer> maxInWindows(int[] num, int size) {
        ArrayList<Integer> list = new ArrayList<>();
        if (size <= 0 || size > num.length)
            return list;
        int left = 0;
        int right = size - 1;
        while (right < num.length) {
            list.add(getMax(num, left, right));
            left++;
            right++;
        }
        return list;
    }

    private int getMax(int[] num, int left, int right) {
        if (num.length == 0 || left < 0 || right > num.length)
            return -1;
        int max = Integer.MIN_VALUE;
        for (int i = left; i <= right; i++) {
            max = max > num[i] ? max : num[i];
        }
        return max;
    }

    /*
     请实现有重复数字的升序数组的二分查找
    给定一个 元素有序的（升序）长度为n的整型数组 nums 和一个目标值 target
    写一个函数搜索 nums 中的第一个出现的target，如果目标值存在返回下标，否则返回 -1
     */
    public int search(int[] nums, int target) {
        //边界条件判断
        if (nums == null || nums.length == 0)
            return -1;
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return nums[left] == target ? left : -1;
    }

    /*
     https://blog.csdn.net/qq249356520/article/details/89207891
     https://blog.csdn.net/zuochao_2013/article/details/82186101?spm=1001.2101.3001.6661.1&utm_medium=distribute.pc_relevant_t0.none-task-blog-2%7Edefault%7ECTRLIST%7Edefault-1.pc_relevant_paycolumn_v2&depth_1-utm_source=distribute.pc_relevant_t0.none-task-blog-2%7Edefault%7ECTRLIST%7Edefault-1.pc_relevant_paycolumn_v2&utm_relevant_index=1
    一座大楼有 n+1 层，地面算作第0层，最高的一层为第 n 层。
    已知棋子从第0层掉落肯定不会摔碎，从第 i 层掉落可能会摔碎，也可能不会摔碎。
    给定整数 n 作为楼层数，再给定整数 k个棋子数，
    返回如果想找到棋子不会摔碎的最高层数，即使在最差的情况下扔的最小次数。一次只能扔一个棋子。

    将设i个棋子仍j次最多搞定m层楼，“搞定最多”说明每次仍的位置都是最优的且在棋子肯定够用的情况下，若第1个棋子仍在a层楼是最优的。
      1. 如果第1个棋子以碎，那么就向下，看i-1个棋子扔j-1次最多搞定多少层楼；
      2. 如果第1个棋子没碎，那么就向上，看i个棋子扔j-1次最多搞定多少层楼；
      3. a层楼本身也是被搞定的1层；
      1、2、3的总楼数就是i个棋子扔j次最多搞定的楼数，map表的生成过程极为简单，同时数值增长的极快。原始问题可以通过map表得到很好的解决。
      例如，想求5个棋子搞定200层楼最少扔多少次的问题，注意到第5行第8列对应的值为218，是第5行的所有值中第一次超过200的值，
    则可以知道5个棋子搞定200层楼最少扔8次。同时在map表中其实9列10列的值也完全可以不需要计算，因为算到第8列就已经搞定，
    那么时间复杂度得到进一步的优化。另外还有一个特别重要的优化，我们知道N层楼完全用二分的方式扔logN+1次就直接可以确定哪层楼是会碎的最低层楼，
    所以当棋子数大于logN+1时，我们就可以返回logN+1.
      如果棋子数位K，楼数位N, 最终的结果位M次，那么最优解的时间复杂度为O(KxM),
    在棋子数大于logN+1时，时间复杂度为O(logN). 在只要1个棋子的时候， K x M等于N,
    在其他情况下 KxM要比N得多。
     */
    public int throwEggsOptTimes(int n, int k) {
        if (n < 1 || k < 1)
            return 0;
        if (k == 1)
            return n;
        int bsTime = (int) (Math.log((double) n) / Math.log((double) 2) + 1);
        if (k >= bsTime)
            return bsTime;
        int[] dp = new int[k];
        int res = 0;
        while (true) {
            ++res;
            int previous = 0;
            for (int i = 0; i < dp.length; i++) {
                int tmp = dp[i];
                dp[i] = dp[i] + previous + 1;
                previous = tmp;
                if (dp[i] >= n)
                    return res;
            }
        }
    }

    /*数组中的最长连续子序列
    给定无序数组arr，返回其中最长的连续序列的长度(要求值连续，位置可以不连续,例如 3,4,5,6为连续的自然数）*/
    public int MLS(int[] arr) {
        if (arr == null || arr.length == 0)
            return 0;
        int longest = 1;//记录最长的有序序列
        int current = 1;//目前有序序列的长度
        //先对数组进行排序
        Arrays.sort(arr);
        for (int i = 1; i < arr.length; i++) {
            //跳过重复的
            int tmp = arr[i] - arr[i - 1];
            if (tmp == 0)
                continue;
            //比前一个大1，可以构成连续的序列，count++
            if (tmp == 1) {
                current++;
            } else {
                //没有比前一个大1，不可能构成连续的，
                //count重置为1
                current = 1;
            }
            //记录最长的序列长度
            longest = Math.max(longest, current);
        }
        return longest;
    }

    /*兑换零钱(一)
    给定数组arr，arr中所有的值都为正整数且不重复。
    每个值代表一种面值的货币，每种面值的货币可以使用任意张，再给定一个aim，代表要找的钱数，求组成aim的最少货币数。
    如果无解，请返回-1.*/
    public int minMoney(int[] arr, int aim) {
        // write code here
        int Max = aim + 1;//定一个全局最大值
        int[] dp = new int[aim + 1];//dp[i]的含义是目标值为i的时候最少钱币数是多少。
        Arrays.fill(dp, Max);//把dp数组全部定为最大值
        dp[0] = 0;//总金额为0的时候所需钱币数一定是0
        for (int i = 1; i <= aim; i++) {// 遍历目标值
            for (int j = 0; j < arr.length; j++) {// 遍历钱币
                if (arr[j] <= i) {//如果当前的钱币比目标值小就可以兑换
                    dp[i] = Math.min(dp[i], dp[i - arr[j]] + 1);
                }
            }
        }
        return dp[aim] > aim ? -1 : dp[aim];
    }

    /*NC203 兑换零钱(二)
    给定一个整数数组 nums 表示不同数额的硬币和一个正整数 target 表示总金额，
    请你计算并返回可以凑出总金额的的组合数。如果凑不出 target 则返回 0。*/
    public int change(int target, int[] nums) {
        // write code here
        int dp[] = new int[target + 1];
        dp[0] = 1;
        for (int num : nums) {
            for (int i = num; i <= target; i++) {
                dp[i] += dp[i - num];
            }
        }
        return dp[target];
    }

    /*调整数组顺序使奇数位于偶数前面(一)
    输入一个长度为 n 整数数组，数组里面不含有相同的元素，实现一个函数来调整该数组中数字的顺序，
    使得所有的奇数位于数组的前面部分，所有的偶数位于数组的后面部分，并保证奇数和奇数，偶数和偶数之间的相对位置不变。
    输入：[1,3,5,6,7]
    返回值：[1,3,5,7,6]*/
    public int[] reOrderArray(int[] array) {
        // write code here
        int i = 0;
        for (int j = 0; j < array.length; ++j) {
            // 遇到奇数时
            if (array[j] % 2 == 1) {
                // 先将 array[j] 赋值
                int tmp = array[j];
                // 将 【i, j-1】数组后移动
                for (int k = j - 1; k >= i; --k) {
                    array[k + 1] = array[k];
                }
                // 将array[j]插入到 i++ 的位置
                array[i++] = tmp;
            }
        }
        return array;
    }

    /*不相邻最大子序列和
    给你一个数组，其长度为 n  ，在其中选出一个子序列，子序列中任意两个数不能有相邻的下标（子序列可以为空）
    本题中子序列指在数组中任意挑选若干个数组成的数组。*/
    public long subsequence(int n, int[] array) {
        // write code here
        long pre = 0;
        long cur = Math.max(array[0], 0);
        if (array.length == 1) {
            return cur;
        }
        long temp = 0;
        for (int i = 2; i <= array.length; i++) {
            temp = cur;
            cur = Math.max(pre + array[i - 1], cur);
            pre = temp;
        }
        return cur;
    }

    /*数独
    请编写一个程序，给数独中的剩余的空格填写上数字
    空格用字符'.'表示
    假设给定的数独只有唯一的解法*/
    public void solveSudoku(char[][] board) {
        if (board == null || board.length == 0)//特殊情况
            return;
        solve(board);
    }

    public boolean solve(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == '.') {
                    for (char c = '1'; c <= '9'; c++) {//尝试1-9的情况
                        if (isValid(board, i, j, c)) {
                            board[i][j] = c; //将c的值放进去
                            if (solve(board))
                                return true; //如果解决了返回true
                            else
                                board[i][j] = '.'; //其他的返回.
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isValid(char[][] board, int row, int col, char c) {
        for (int i = 0; i < 9; i++) {
            if (board[i][col] != '.' && board[i][col] == c)//检查行
                return false;
            if (board[row][i] != '.' && board[row][i] == c)//检查列
                return false;
            if (board[3 * (row / 3) + i / 3][3 * (col / 3) + i % 3] != '.'
                    && board[3 * (row / 3) + i / 3][3 * (col / 3) + i % 3] == c)//检查子宫格
                return false;
        }
        return true;
    }

    /*
    扑克牌顺子：
    现在有2副扑克牌，从扑克牌中随机五张扑克牌，我们需要来判断一下是不是顺子。
    有如下规则：
    1. A为1，J为11，Q为12，K为13，A不能视为14
    2. 大、小王为 0，0可以看作任意牌
    3. 如果给出的五张牌能组成顺子（即这五张牌是连续的）就输出true，否则就输出false。
    4.数据保证每组5个数字，每组最多含有4个零，数组的数取值为 [0, 13]
    */
    public boolean isContinuous(int[] numbers) {
        int joker = 0;
        Arrays.sort(numbers); // 数组排序
        for (int i = 0; i < 4; i++) {
            if (numbers[i] == 0)
                joker++; // 统计大小王数量
            else if (numbers[i] == numbers[i + 1])
                return false; // 若有重复，提前返回 false
        }
        return numbers[4] - numbers[joker] < 5; // 最大牌 - 最小牌 < 5 则可构成顺子
    }

	/* 旋转数组的最小数字
	有一个长度为 n 的非降序数组，比如[1,2,3,4,5]，将它进行旋转，
	即把一个数组最开始的若干个元素搬到数组的末尾，变成一个旋转数组，
	比如变成了[3,4,5,1,2]，或者[4,5,1,2,3]这样的。
	请问，给定这样一个旋转数组，求数组中的最小值。*/

    public int minNumberInRotateArray(int[] array) {
        Arrays.sort(array);
        // 特殊情况判断
        if (array.length == 0) {
            return 0;
        }
        // 左右指针i j
        int i = 0, j = array.length - 1;
        // 循环
        while (i < j) {
            // 找到数组的中点 m
            int m = (i + j) / 2;
            // m在左排序数组中，旋转点在 [m+1, j] 中
            if (array[m] > array[j])
                i = m + 1;
                // m 在右排序数组中，旋转点在 [i, m]中
            else if (array[m] < array[j])
                j = m;
                // 缩小范围继续判断
            else
                j--;
        }
        // 返回旋转点
        return array[i];
    }

    /*数字在升序数组中出现的次数
    给定一个长度为 n 的非降序数组和一个非负数整数 k ，要求统计 k 在数组中出现的次数
    输入：[1,2,3,3,3,3,4,5],3
    返回值：4
    */
    public int getNumberOfK(int[] array, int k) {
        int count = 0;
        for (int e : array) {
            if (k == e)
                count++;
        }
        return count;
    }

    /*和为K的连续子数组
    给定一个无序数组 arr , 其中元素可正、可负、可0。给定一个整数 k ，
    求 arr 所有连续子数组中累加和为k的最长连续子数组长度。保证至少存在一个合法的连续子数组。
    [1,2,3]的连续子数组有[1,2]，[2,3]，[1,2,3] ，但是[1,3]不是*/
    public static int maxLengthEqualK(int[] arr, int k) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int len = 0;
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            if (map.containsKey(sum - k)) {
                len = Math.max(len, i - map.get(sum - k));
            }
            if (!map.containsKey(sum)) {
                map.put(sum, i);
            }
        }
        return len;
    }

    public int maxlenEqualK(int[] arr, int k) {
        int[] sum = new int[arr.length];
        sum[0] = arr[0];
        for (int i = 1; i < sum.length; ++i) {
            sum[i] = sum[i - 1] + arr[i];
        }
        for (int s = arr.length - 1; s >= 1; --s) {
            for (int i = 0; i + s < arr.length; ++i) {
                if (sum[i + s] - sum[i] + arr[i] == k) {
                    return s + 1;
                }
            }
        }
        return 0;
    }

    /*分糖果问题：
    一群孩子做游戏，现在请你根据游戏得分来发糖果，要求如下：
    1. 每个孩子不管得分多少，起码分到一个糖果。
    2. 任意两个相邻的孩子之间，得分较多的孩子必须拿多一些糖果。(若相同则无此限制)
    给定一个数组 arrarr 代表得分数组，请返回最少需要多少糖果。*/
    public int candy(int[] arr) {
        // write code here
        int[] tmp = new int[arr.length];
        Arrays.fill(tmp, 1);
        int count = 0;

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > arr[i - 1]) {
                tmp[i] = tmp[i - 1] + 1;
            }
        }
        for (int i = arr.length - 1; i > 0; i--) {
            if (arr[i - 1] > arr[i]) {
                tmp[i - 1] = Math.max(tmp[i - 1], tmp[i] + 1);
            }
        }
        for (int i : tmp)
            count += i;
        return count;
    }

    /*
    比较版本号：
    牛客项目发布项目版本时会有版本号，比如1.02.11，2.14.4等等
    现在给你2个版本号version1和version2，请你比较他们的大小
    版本号是由修订号组成，修订号与修订号之间由一个"."连接。1个修订号可能有多位数字组成，修订号可能包含前导0，且是合法的。例如，1.02.11，0.1，0.2都是合法的版本号
    每个版本号至少包含1个修订号。
    修订号从左到右编号，下标从0开始，最左边的修订号下标为0，下一个修订号下标为1，以此类推。

    比较规则：
    一. 比较版本号时，请按从左到右的顺序依次比较它们的修订号。比较修订号时，只需比较忽略任何前导零后的整数值。比如"0.1"和"0.01"的版本号是相等的
    二. 如果版本号没有指定某个下标处的修订号，则该修订号视为0。例如，"1.1"的版本号小于"1.1.1"。因为"1.1"的版本号相当于"1.1.0"，第3位修订号的下标为0，小于1
    三. version1 > version2 返回1，如果 version1 < version2 返回-1，不然返回0.*/
    public int compareVersionNumber(String version1, String version2) {
        // write code here
        // 两个字符串的长度
        String[] v1 = version1.split("\\.");
        String[] v2 = version2.split("\\.");
        for (int i = 0; i < Math.max(v1.length, v2.length); i++) {
            String a = i < v1.length ? v1[i] : "0";
            String b = i < v2.length ? v2[i] : "0";
            for (int j = 0; j < a.length(); j++) {
                if (j == a.length() - 1 || a.charAt(j) != '0') {
                    a = a.substring(j);
                    break;
                }
            }
            for (int k = 0; k < b.length(); k++) {
                if (k == b.length() - 1 || b.charAt(k) != '0') {
                    b = b.substring(k);
                    break;
                }
            }
            if (a.length() > b.length()) {
                return 1;
            } else if (b.length() > a.length()) {
                return -1;
            } else {
                if (a.compareTo(b) > 0) {
                    return 1;
                } else if (a.compareTo(b) < 0) {
                    return -1;
                }
            }
        }
        return 0;
    }

    /*三个数的最大乘积
    给定一个长度为 nn 的无序数组 AA ，包含正数、负数和 0 ，请从中找出 3 个数，使得乘积最大，返回这个乘积。*/
    public long maxTreeNumberMultisolve(int[] A) {
        // write code here
        int max1 = Integer.MIN_VALUE, max2 = Integer.MIN_VALUE, max3 = Integer.MIN_VALUE;
        int min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;
        for (int num : A) {
            if (num > max1) {
                max3 = max2;
                max2 = max1;
                max1 = num;
            } else if (num > max2) {
                max3 = max2;
                max2 = num;
            } else if (num > max3) {
                max3 = num;
            }
            if (num < min1) {
                min2 = min1;
                min1 = num;
            } else if (num < min2) {
                min2 = num;
            }
        }
        return Math.max((long) max1 * max2 * max3, (long) max1 * min1 * min2);
    }

    public long maxTreeNumberMultisolve02(int[] A) {
        // write code here
        int n = A.length;
        if (n == 0) {
            return n;
        }
        PriorityQueue<Integer> queu = new PriorityQueue<Integer>((o1, o2) -> o2.compareTo(o1));
        for (int i : A) {
            queu.offer(i);
        }
        return queu.poll() * queu.poll() * queu.poll();
    }

    /*数组中只出现一次的两个数字
    一个整型数组里除了两个数字只出现一次，其他的数字都出现了两次。请写程序找出这两个只出现一次的数字。*/
    public int[] FindNumsAppearOnce(int[] array) {

        // 先将全部数进行异或运算，得出最终结果
        int tmp = 0;
        for (int num : array) {
            tmp ^= num;
        }

        // 找到那个可以充当分组去进行与运算的数
        // 从最低位开始找起
        int mask = 1;
        while ((tmp & mask) == 0) {
            mask <<= 1;
        }

        // 进行分组，分成两组，转换为两组 求出现一次的数字 去求
        int a = 0;
        int b = 0;
        for (int num : array) {
            if ((num & mask) == 0) {
                a ^= num;
            } else {
                b ^= num;
            }
        }
        // 因为题目要求小的数放前面，所以这一做个判断
        if (a > b) {
            int c = a;
            a = b;
            b = c;
        }
        return new int[]{a, b};
    }

    /*栈和排序
    给你一个 1 到 n 的排列和一个栈，并按照排列顺序入栈
    你要在不打乱入栈顺序的情况下，仅利用入栈和出栈两种操作，输出字典序最大的出栈序列
    排列：指 1 到 n 每个数字出现且仅出现一次*/
    public int[] stackAndSort(int[] a) {
        int n = a.length;
        int[] maxs = new int[n + 1];
        int max = Integer.MIN_VALUE;

        //找出i到n中最大的那个值
        for (int i = n - 1; i >= 0; i--) {
            max = Math.max(max, a[i]);
            maxs[i] = max;
        }
        maxs[n] = Integer.MIN_VALUE;

        Stack<Integer> stack = new Stack<>();
        ArrayList<Integer> list = new ArrayList<>();

        //如果当前入栈的值 大于i+1到n之间的最大值 那么出栈
        //maxs[i+1]最后一定为0,所有栈内元素可以全部出栈
        for (int i = 0; i < n; i++) {
            stack.push(a[i]);
            while (!stack.isEmpty() && stack.peek() > maxs[i + 1]) {
                list.add(stack.pop());
            }
        }

        int[] res = new int[n];
        for (int i = 0; i < res.length; i++) {
            res[i] = list.get(i);
        }
        return res;
    }

    /*数组中只出现一次的数（其它数出现k次）
    给定一个长度为 n 的整型数组 arr 和一个整数 k(k>1) 。
    已知 arr 中只有 1 个数出现一次，其他的数都出现 k 次。
    请返回只出现了 1 次的数。*/
    public int foundOnceNumber(int[] arr, int k) {
        Arrays.sort(arr);
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] == arr[i + 1]) {
                i += k - 1;
            } else {
                return arr[i];
            }

        }
        return arr[arr.length - 1];
    }

    /*
    出现k次就不能再用异或的方法了，因为k(奇数)个相同的数异或还是得到本身。
    但是还是可以采用位运算的思想，因为出现k(奇数)次的数字每个位（0或者1）也是出现k(奇数)次，
    因此可以每一位的和能够被k整除（对k取余为0）。所以如果把每个数的二进制表示的每一位加起来，
    对于每一位的和，如果能被k整除，那对应那个只出现一次的数字的那一位就是0，否则对应的那一位是1。
    我们需要用一个长度为32（int型二进制表示最多为32位，4字节）的数组bitSum保存每一位的和
    具体来讲实现过程是，先初始化为0，然后对于每个数字，遍历它二进制表示的每一位，如果这一位是1，bitSum对应的那一位就加1。*/
    public int foundOnceNumber02(int[] arr, int k) {
        // 每个二进制位求和，如果某个二进制位不能被k整除，那么只出现一次的那个数字在这个二进制位上为1。
        int[] binarySum = new int[32];
        for (int i = 0; i < 32; i++) {//求每个二进制位的和
            int sum = 0;
            for (int num : arr) {
                sum += (num >> i & 1);//依次右移num，同1相与，计算每一位上1的个数
            }
            binarySum[i] = sum;
        }
        int res = 0;
        for (int i = 0; i < 32; i++) {
            if (binarySum[i] % k != 0) {
                res += 1 << i;//左移恢复
            }
        }
        return res;
    }

    public int foundOnceNumber03(int[] arr, int k) {
        // write code here
        HashMap<Integer, Integer> map = new HashMap<>();//key存数字，value表示是否出现过
        for (int num : arr) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }
        Set<Integer> set = map.keySet();
        for (int num : set) {
            if (map.get(num) == 1) {
                return num;
            }
        }
        return 0;
    }

    /*集合的所有子集(二)
    给定一个整数数组 nums ，其中可能包含重复元素，请你返回这个数组的所有可能子集。
    返回的答案中不能包含重复的子集，将答案按字典序进行排序。*/
    ArrayList<ArrayList<Integer>> ans;

    public ArrayList<ArrayList<Integer>> subsets(int[] nums) {
        ans = new ArrayList<>();
        Arrays.sort(nums);
        ans.add(new ArrayList<>());
        backTrace(nums, 0, new ArrayList<>());
        return ans;
    }

    public void backTrace(int[] nums, int index, ArrayList<Integer> list) {
        if (index == nums.length) {
            return;
        }
        ArrayList<Integer> li = new ArrayList<>(list);
        for (int i = index; i < nums.length; i++) {
            if (i > index && nums[i - 1] == nums[i]) {
                continue;
            }
            li.add(nums[i]);
            ans.add(new ArrayList<>(li));
            backTrace(nums, i + 1, li);
            li.remove(li.size() - 1);
        }
    }

    /*插入区间
    给定一个无重叠的，按照区间起点升序排列的区间列表，在列表中插入一个新区间，如果有原区间有重合，则合并，请返回插入后的区间列表。*/
    public Interval[] insertInterval(Interval[] Intervals, Interval newInterval) {
        if (Intervals == null)
            return new Interval[1];
        int n = Intervals.length;
        if (n == 0 || (newInterval.start <= Intervals[0].start && newInterval.end >= Intervals[n - 1].end))
            return new Interval[]{newInterval};
        ArrayList<Interval> list = new ArrayList();
        for (Interval current : Intervals) {
            if (current.end < newInterval.start) { //前置区间
                list.add(current);
            } else if (current.start > newInterval.end) { //后置区间
                list.add(newInterval);
                newInterval = current;
            } else { //重叠区间
                newInterval.start = Math.min(newInterval.start, current.start);
                newInterval.end = Math.max(newInterval.end, current.end);
            }
        }
        list.add(newInterval);
        Interval[] res = new Interval[list.size()];
        return list.toArray(res);
    }

    /*三角形最小路径和
    给定一个正三角形数组，自顶到底分别有 1，2，3，4，5...，n 个元素，找出自顶向下的最小路径和。
    每一步只能移动到下一行的相邻节点上，相邻节点指下行种下标与之相同或下标加一的两个节点。*/
    public int minTrace(int[][] triangle) {
        int length = triangle.length;
        for (int i = length - 2; i >= 0; i--) {
            int size = triangle[i].length;
            for (int j = 0; j < size; j++) {
                triangle[i][j] += Math.min(triangle[i + 1][j], triangle[i + 1][j + 1]);
            }
        }
        return triangle[0][0];
    }

    /*只出现一次的数字(二)
    给定一个整数数组，数组中有一个数出现了一次，其他数出现了三次，请找出只出现了一次的数。*/
    public int singleNumber(int[] nums) {
        int a = 0, b = 0;
        for (int num : nums) {
            a = ~b & (a ^ num);
            b = ~a & (b ^ num);
        }
        return a;
    }

    /*只出现一次的数字
    给定一个整数数组，数组中有一个数出现了一次，其他数出现了两次，请找出只出现了一次的数。*/
    public int singleNumber02(int[] nums) {
        // write code here
        if (nums == null || nums.length <= 0) {
            return 0;
        }
        int res = nums[0];
        for (int i = 1; i < nums.length; i++) {
            res = res ^ nums[i];
        }
        return res;

    }

    /*
    给出一组候选数 c 和一个目标数 t ，找出候选数中起来和等于 t 的所有组合。
    c 中的每个数字在一个组合中只能使用一次。
    注意：
    1. 题目中所有的数字（包括目标数 t ）都是正整数
    2. 按非递减排序
    3. 结果中不能包含重复的组合
    4. 组合之间的排序按照索引从小到大依次比较，小的排在前面，如果索引相同的情况下数值相同，则比较下一个索引。
    */
    public ArrayList<ArrayList<Integer>> combinationSum2(int[] num, int target) {
        int len = num.length;
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        if (len == 0) {
            return res;
        }
        //    先要对数组进行排序，以满足题目对搜索结果的要求
        Arrays.sort(num);
        Deque<Integer> path = new ArrayDeque<>(len);
        //    给它个初始大小，防止添加过程中的扩容操作按相应规则进行，导致内存浪费
        dfs3(num, target, 0, path, res);
        return res;
    }

    //    通过深度优先搜索解决
    public void dfs3(int[] num, int target, int begin, Deque<Integer> path, ArrayList<ArrayList<Integer>> res) {
        if (target == 0) {
            res.add(new ArrayList<>(path));
            return; //    剪枝，因为已经满足一个组合的要求了，继续往下加不可能满足组合
        }
        for (int i = begin; i < num.length; ++i) {
            //    剪枝，因为数组已排好序，如果当前元素不可能是组合中的元素，那么 num[i+1]、num[i+2]、...更不可能是了，退出此次无意义的搜索
            if (target - num[i] < 0) {
                break;
            }
            //    剪枝，满足结果不可重复
            //    原理：同一层节点如果相同，则保留第一个，其它相同的都不需要了
            if (i > begin && num[i] == num[i - 1]) {
                continue;
            }
            path.addLast(num[i]);
            //    i + 1：保证不选取重复的元素
            dfs3(num, target - num[i], i + 1, path, res);
            //    回溯
            path.removeLast();
        }
    }

    /*NC238 加起来和为目标值的组合
    给定一个无重复元素的正整数数组 nums 和一个正整数 target ，
    找出 nums 中所有可以使数字之和为目标数 target 的组合，nums 中的数可以重复选取，只要选出的组合中有一个数不同则视为是不同组合。*/
    public ArrayList<ArrayList<Integer>> combinationCount01(int target, int[] nums) {
        // write code here
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        ArrayList<Integer> temp = new ArrayList<>();
        dfs01(target, 0, nums, res, temp);
        return res;
    }

    public void dfs01(int target, int index, int[] nums, ArrayList<ArrayList<Integer>> res, ArrayList<Integer> temp) {
        if (target == 0) {
            res.add(new ArrayList<>(temp));
            return;
        }
        for (int i = index; i < nums.length; i++) {
            if (target >= nums[i]) {
                temp.add(nums[i]);
                dfs01(target - nums[i], i, nums, res, temp);
                temp.remove(temp.size() - 1);
            }
        }
    }

    /* NC46 加起来和为目标值的组合(二)
    给出一组候选数 c 和一个目标数 t ，找出候选数中起来和等于 t 的所有组合。
    c 中的每个数字在一个组合中只能使用一次。
    注意：
    1. 题目中所有的数字（包括目标数 t ）都是正整数
    2. 组合中的数字 ( a_1, a_2, … , a_ka
    3. 结果中不能包含重复的组合
    4. 组合之间的排序按照索引从小到大依次比较，小的排在前面，如果索引相同的情况下数值相同，则比较下一个索引。*/
    public ArrayList<ArrayList<Integer>> combinationSum22(int[] num, int target) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> arr = new ArrayList<Integer>();
        if (num == null || num.length == 0 || target < 0) return res;
        Arrays.sort(num);//对候选数组进行排序 方便后续处理
        dfs(num, target, res, arr, 0);
        return res;
    }

    void dfs(int[] num, int target, ArrayList<ArrayList<Integer>> res, ArrayList<Integer> arr, int start) {
        if (target == 0) {
            //已找到一组 存储进res
            res.add(new ArrayList<Integer>(arr));
            return;
        }
        if (start >= num.length) return;
        for (int i = start; i < num.length; i++) {
            if (i > start && num[i] == num[i - 1]) continue;
            //回溯操作
            if (num[i] <= target) {
                arr.add(num[i]);
                dfs(num, target - num[i], res, arr, i + 1);
                arr.remove(arr.size() - 1);
            }
        }
        return;
    }

    /*NC232 加起来和为目标值的组合(三)
    找出所有相加之和是 n 的 k 个数的组合。组合中只含有 1~9的正整数，且保证每种组合中不含有相同的数字。
    保证一定有解。结果按字典序升序输出。*/
    public int[][] combination(int k, int n) {
        // write code here
        int[] nums = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        helper(res, new ArrayList<Integer>(), 0, nums, k, n, 0);
        int[][] arr = new int[res.size()][k];
        for (int i = 0; i < res.size(); i++) {
            for (int j = 0; j < k; j++) {
                arr[i][j] = res.get(i).get(j);
            }
        }
        return arr;
    }

    public void helper(ArrayList<ArrayList<Integer>> res, ArrayList<Integer> list, int index, int[] nums, int k, int n, int sum) {
        if (list.size() == k) {
            if (sum == n) {
                res.add(new ArrayList(list));
                return;
            }
        }
        for (int i = index; i < nums.length; i++) {
            list.add(nums[i]);
            sum += nums[i];
            helper(res, list, i + 1, nums, k, n, sum);
            sum -= nums[i];
            list.remove(list.size() - 1);
        }
    }

    /*加起来和为目标值的组合(四)
    给定一个由不同整数构成的数组 nums 和一个整数 target ，请你从 nums 找出总和是 target 的组合的个数。
    解集中可以重复使用 nums 中的元素。且解集中数字顺序不同视为不同的组合。
    输入：[1,2,3],4
    返回值：7
    说明：
    所有可能的组合为：
    (1, 1, 1, 1)
    (1, 1, 2)
    (1, 2, 1)
    (1, 3)
    (2, 1, 1)
    (2, 2)
    (3, 1)*/
    public int combination(int[] nums, int target) {
        // write code here
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int i = 1; i <= target; i++) {
            for (int num : nums) {
                if (num <= i) {
                    dp[i] += dp[i - num];
                }
            }
        }
        return dp[target];
    }

    /*加起来和为目标值的组合
    给定一个无重复元素的正整数数组 nums 和一个正整数 target ，
    找出 nums 中所有可以使数字之和为目标数 target 的组合，
    nums 中的数可以重复选取，只要选出的组合中有一个数不同则视为是不同组合。*/
    public ArrayList<ArrayList<Integer>> combinationCount(int target, int[] nums) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        ArrayList<Integer> temp = new ArrayList<>();
        dfs(target, 0, nums, res, temp);
        return res;
    }

    public void dfs(int target, int index, int[] nums, ArrayList<ArrayList<Integer>> res, ArrayList<Integer> temp) {
        if (target == 0) {
            res.add(new ArrayList<>(temp));
            return;
        }
        for (int i = index; i < nums.length; i++) {
            if (target >= nums[i]) {
                temp.add(nums[i]);
                dfs(target - nums[i], i, nums, res, temp);
                temp.remove(temp.size() - 1);
            }
        }
    }

    /*加油站
    在一条环路上有 n 个加油站，其中第 i 个加油站有 gas[i] 升油，假设汽车油箱容量无限，
    从第 i 个加油站驶往第 (i+1)%n 个加油站需要花费 cost[i] 升油。
    请问能否绕环路行驶一周，如果可以则返回出发的加油站编号，如果不能，则返回 -1。
    题目数据可以保证最多有一个答案。*/
    public int gasStation(int[] gas, int[] cost) {
        // write code here
        int n = cost.length;
        int sum = 0;
        int remain = 0;
        int index = 0;
        for (int i = 0; i < n; i++) {
            remain += gas[i] - cost[i];
            sum += gas[i] - cost[i];
            if (remain < 0) {
                remain = 0;
                index = (i + 1) % n;
            }
        }
        return sum >= 0 ? index : -1;
    }

    /*NC236 最大差值
    有一个长为 n 的数组 A ，求满足 0 ≤ a ≤ b < n 的 A[b] - A[a] 的最大值。
    给定数组 A 及它的大小 n ，请返回最大差值。*/
    public int getDis(int[] A, int n) {
        // write code here
        int min_pos = 0, res = 0;
        for (int i = 0; i < n; i++) {
            if (A[i] < A[min_pos])
                //min_pos记录i之前的最小值
                //不断更新，求出每个值作为被减数的最小减数
                min_pos = i;
            else
                res = Math.max(res, A[i] - A[min_pos]);
        }
        return res;
    }

    /*最长上升子序列(一)
    给定一个长度为 n 的数组 arr，求它的最长严格上升子序列的长度。
    所谓子序列，指一个数组删掉一些数（也可以不删）之后，形成的新数组。
    例如 [1,5,3,7,3] 数组，其子序列有：[1,3,3]、[7] 等。但 [1,6]、[1,3,5] 则不是它的子序列。
    我们定义一个序列是 严格上升 的*/
    public int LIS(int[] arr) {
        if (arr.length == 0) {
            return 0;
        }
        int[] end = new int[arr.length];
        int len = 0;
        end[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (end[len] < arr[i]) {
                end[++len] = arr[i];
            } else {
                int left = 0, right = len;
                while (left < right) {
                    int mid = left + (right - left) / 2;
                    if (end[mid] < arr[i]) {
                        left = mid + 1;
                    } else {
                        right = mid;
                    }
                }
                end[left] = arr[i];
            }
        }
        return len + 1;
    }

    /*最长上升子序列(二)
    给定一个长度为 n 的数组a，求它的最长严格上升子序列的长度。
    所谓子序列，指一个数组删掉一些数（也可以不删）之后，形成的新数组。
    例如 [1,5,3,7,3] 数组，其子序列有：[1,3,3]、[7] 等。
    但 [1,6]、[1,3,5] 则不是它的子序列。
     */
    public int LIS02(int[] nums) {
        int n = nums.length;
        if (n <= 1)
            return n;

        int[] d = new int[n + 1];
        int len = 1;
        d[len] = nums[0];
        // 利用贪心 + 二分查找进行更新
        for (int i = 1; i < n; ++i) {
            if (d[len] < nums[i])
                d[++len] = nums[i];
            else {
                int l = 1, r = len;
                int pos = 0;
                while (l <= r) {
                    int mid = (l + r) >> 1;
                    if (d[mid] < nums[i]) {
                        pos = mid;
                        l = mid + 1;
                    } else {
                        r = mid - 1;
                    }
                }
                d[pos + 1] = nums[i];
            }
        }
        return len;
    }

    /*NC91 最长上升子序列(三)
    给定数组 arr ，设长度为 n ，输出 arr 的最长上升子序列。
    （如果有多个答案，请输出其中 按数值(注：区别于按单个字符的ASCII码值)进行比较的 字典序最小的那个）*/
    public int[] LIS03(int[] arr) {
        if (arr.length <= 1 || arr == null)
            return arr;
        // 序列
        int[] end = new int[arr.length];
        // 最长子序列的长度len
        int[] indlen = new int[arr.length];
        end[0] = arr[0];
        // 序列的长度
        int len = 1;
        indlen[0] = len;
        for (int i = 1; i < arr.length; i++) {
            if (end[(len - 1)] < arr[i]) {
                // 如果大于就放在end后
                end[len++] = arr[i];
                indlen[i] = len;
            } else if (end[len - 1] == arr[i]) {
                indlen[i] = len;
            } else {
                // 替换第一个大于元素位置
                int index = findFirstIndex(end, len, arr[i]);
                end[index] = arr[i];
                indlen[i] = (index + 1);
            }
        }

        int[] res = new int[len];
        for (int i = arr.length - 1; i >= 0; i--) {
            if (indlen[i] == len) {
                res[--len] = arr[i];
            }
        }
        return res;
    }

    private int findFirstIndex(int[] end, int len, int key) {
        int left = 0;
        int right = len - 1;
        while (left <= right) {
            int mid = (left + right) >> 1;
            if (end[mid] < key) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        //return end[left]<key ? (left+1):left;
        return left;
    }

    /*NC67 连续子数组的最大和
    输入一个长度为n的整型数组array，数组中的一个或连续多个整数组成一个子数组。求所有子数组的和的最大值。*/
    public int findGreatestSumOfSubArray01(int[] array) {
        int pre = 0;
        int max = Integer.MIN_VALUE;
        int len = array.length;
        for (int i = 0; i < len; i++) {
            if (pre > 0) pre += array[i];
            else pre = array[i];
            max = Math.max(pre, max);
        }
        return max;
    }

    /*连续子数组的最大和(二)
    输入一个长度为n的整型数组array，数组中的一个或连续多个整数组成一个子数组，找到一个具有最大和的连续子数组。
    1.子数组是连续的，比如[1,3,5,7,9]的子数组有[1,3]，[3,5,7]等等，但是[1,3,7]不是子数组
    2.如果存在多个最大和的连续子数组，那么返回其中长度最长的，该题数据保证这个最长的只存在一个
    3.该题定义的子数组的最小长度为1，不存在为空的子数组，即不存在[]是某个数组的子数组
    4.返回的数组不计入空间复杂度计算
    输入：[1,-2,3,10,-4,7,2,-5]
    返回值：[3,10,-4,7,2]
    说明：
    经分析可知，输入数组的子数组[3,10,-4,7,2]可以求得最大和为18，故返回[3,10,-4,7,2]  */
    public int[] findGreatestSumOfSubArray(int[] array) {
        // write code here
        int[] dp = new int[array.length];
        dp[0] = array[0];
        // 该题定义的子数组的最小长度为1；
        int maxLength = 1, maxSum = array[0], left = 0, right = 0, snapLeft = 0, snapRight = 0;
        for (int i = 1; i <= array.length - 1; ++i) {
            right++;
            dp[i] = Math.max(array[i] + dp[i - 1], array[i]);
            if (array[i] + dp[i - 1] < array[i])
                left = right;
            if (dp[i] > maxSum || dp[i] == maxSum && (right - left + 1) > maxLength) {
                snapLeft = left;
                snapRight = right;
                maxLength = right - left + 1;
                maxSum = dp[i];
            }
        }
        int[] res = new int[maxLength];
        int idx = 0;
        for (int i = snapLeft; i <= snapRight; ++i)
            res[idx++] = array[i];
        return res;
    }

    /*盛水最多的容器
    给定一个数组height，长度为n，每个数代表坐标轴中的一个点的高度，height[i]是在第i点的高度，请问，从中选2个高度与x轴组成的容器最多能容纳多少水
    1.你不能倾斜容器
    2.当n小于2时，视为不能形成容器，请返回0
    3.数据保证能容纳最多的水不会超过整形范围，即不会超过231-1*/
    public int maxArea(int[] height) {
        int left = 0, right = height.length - 1;
        int max = 0;
        while (left < right) {
            if (height[left] < height[right]) {
                max = Math.max(max, height[left] * (right - left));
                left++;
            } else {
                max = Math.max(max, height[right] * (right - left));
                right--;
            }
        }
        return max;
    }

    /*NC157 单调栈
    给定一个长度为 n 的可能含有重复值的数组 arr ，找到每一个 i 位置左边和右边离 i 位置最近且值比 arri 小的位置。
    请设计算法，返回一个二维数组，表示所有位置相应的信息。位置信息包括：两个数字 l 和 r，如果不存在，则值为 -1，下标从 0 开始。*/
    public int[][] foundMonotoneStack(int[] nums) {
        // write code here
        int len = nums.length;
        int ans[][] = new int[len][2];
        for (int i = 0; i < len; i++) {
            int l = -1, r = -1;
            for (int j = i - 1; j >= 0; j--) {
                if (nums[j] < nums[i]) {
                    l = j;
                    break;
                }
            }
            for (int k = i + 1; k < len; k++) {
                if (nums[k] < nums[i]) {
                    r = k;
                    break;
                }
            }
            ans[i][0] = l;
            ans[i][1] = r;
        }
        return ans;
    }

    /*NC171 直方图内最大矩形
    给定一个数组heights，长度为n，height[i]是在第i点的高度，那么height[i]表示的直方图，能够形成的最大矩形是多少?
            1.每个直方图宽度都为1
            2.直方图都是相邻的
            3.如果不能形成矩形，返回0即可
            4.保证返回的结果不会超过2^31-1*/
    public int largestRectangleArea(int[] heights) {
        int[] tmp = new int[heights.length + 2];
        System.arraycopy(heights, 0, tmp, 1, heights.length);
        Deque<Integer> stack = new ArrayDeque<>();
        int area = 0;
        for (int i = 0; i < tmp.length; i++) {
            while (!stack.isEmpty() && tmp[i] < tmp[stack.peek()]) {
                int h = tmp[stack.pop()];
                area = Math.max(area, (i - stack.peek() - 1) * h);
            }
            stack.push(i);
        }
        return area;
    }

	/*求最大子矩阵的大小
	给出一个矩形统计图，它的每个矩形的宽度都为1，高度是题目所给。要你求出这个矩形图中最大面积的长方形。
	矩形统计图的数据为 [4, 3, 2, 5, 6]*/

    /**
     * 给定一个数组[4,3,2,5,6]，每一个数代表一个矩形的高度，组成的一个二维数组，求其中的最大矩形
     * 解法，用最大单调栈的结构来求解，用来求解一个连续的无规则面积中最大的矩形面积
     *
     * @return
     */
    public static int maxRecFromBottom(int[] height) {
        int maxArea = 0;
        if (height.length <= 0)
            return 0;
        //从小到大的单调栈
        Stack<Integer> stack = new Stack<>();
        //这一步是在求每次遇到不是单调递增的时候那个柱子的面积
        for (int i = 0; i < height.length; i++) {
            //如果栈不为空，且当前元素小于栈顶元素
            while (!stack.isEmpty() && height[i] <= height[stack.peek()]) {
                int j = stack.pop();
                //左边界
                int k = stack.isEmpty() ? -1 : stack.peek();
                //(右边界 - 左边界)*高度
                int curArea = (i - k - 1) * height[j];
                maxArea = Math.max(maxArea, curArea);
            }
            stack.push(i);
        }
        //求整个单调递增的面积
        while (!stack.isEmpty()) {
            int j = stack.pop();
            int k = stack.isEmpty() ? -1 : stack.peek();
            //当前的右边界就是数组长度
            int curArea = (height.length - k - 1) * height[j];
            maxArea = Math.max(maxArea, curArea);
        }
        return maxArea;
    }

    public int maxRecSize(int[][] map) {
        int maxArea = 0;
        if (map.length <= 0)
            return 0;
        int[] height = new int[map[0].length];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                //如果当前行不是0，则累加高度
                if (map[i][j] != 0)
                    height[j] += map[i][j];
                else//如果当前行的值为0，则高度为0
                    height[j] = 0;
            }
            //求出每一行的最大矩形面积
            maxArea = Math.max(maxRecFromBottom(height), maxArea);
        }

        return maxArea;
    }

    /**
     * NC173 填充数组 牛妹给了牛牛一个长度为 nn 的下标从00开始的正整型数组 aa ，粗心的牛牛不小心把其中的一些数字删除了。 假如a_{i}a
     * 被删除了，则a_{i}=0a =0。对于所有被删除的数字，牛牛必须选择一个正整数填充上。现在牛牛想知道有多少种填充方案使得：
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * 输入：[0,0,0,0,0,67,0,0],100 返回值：746845806
     *
     * @param a int整型一维数组
     * @param k int整型
     * @return int整型
     */
    public int FillArray(int[] a, int k) {
        // write code here
        int start = 0;
        int end = 1;
        long res = 1;
        int mod = 1000000007;
        for (int i = 0; i < a.length; i++) {
            if (a[i] == 0) {
                start = end;
                int size = 0;
                while (i < a.length && a[i] == 0) {
                    i++;
                    size++;
                }
                end = i < a.length ? a[i] : k;
                if (size != 0) {
                    res = (res * (helper(start, end, size) % mod) % mod);
                }
            } else {
                end = a[i];
            }
        }
        return (int) res;
    }

    public long helper(int min, int max, int size) {
        if (size == 1) {
            return max - min + 1;
        }
        if (max == min) {
            return 1;
        }
        int n = max - min + 1;
        int[][] cache = new int[size + 1][n + 1];
        int sum = 0;
        for (int prev = min; prev <= max; prev++) {
            cache[1][prev - min + 1] = max - prev + 1;
            sum += max - prev + 1;
        }
        for (int sz = 2; sz <= size; sz++) {
            sum = 0;
            for (int prev = max; prev >= min; prev--) {
                int cl = prev - min + 1;
                sum = (sum + cache[sz - 1][cl]) % 1000000007;
                cache[sz][cl] = sum;
            }
        }
        return sum;
    }

    /*NC176 打家劫舍(一)
    你是一个经验丰富的小偷，准备偷沿街的一排房间，每个房间都存有一定的现金，为了防止被发现，
    你不能偷相邻的两家，即，如果偷了第一家，就不能再偷第二家；如果偷了第二家，那么就不能偷第一家和第三家。
    给定一个整数数组nums，数组中的元素表示每个房间存有的现金数额，请你计算在不被发现的前提下最多的偷窃金额。*/
    public int rob(int[] nums) {
        // write code here
        if (nums == null)
            return 0;
        if (nums.length == 1)
            return nums[0];
        if (nums.length == 2) {
            if (nums[0] > nums[1])
                return nums[0];
            else
                return nums[1];
        }
        int first = 0;
        int second = 0;
        int temp = 0;
        for (int i = 0; i < nums.length; i++) {
            if (first + nums[i] > second) {
                temp = second;
                second = first + nums[i];
                first = Math.max(first, temp);
            } else
                first = second;
        }
        return Math.max(first, second);
    }

    /*NC177 打家劫舍(二)
    你是一个经验丰富的小偷，准备偷沿湖的一排房间，每个房间都存有一定的现金，
    为了防止被发现，你不能偷相邻的两家，即，如果偷了第一家，就不能再偷第二家，如果偷了第二家，
    那么就不能偷第一家和第三家。沿湖的房间组成一个闭合的圆形，即第一个房间和最后一个房间视为相邻。
    给定一个长度为n的整数数组nums，数组中的元素表示每个房间存有的现金数额，请你计算在不被发现的前提下最多的偷窃金额。*/
    public int rob02(int[] nums) {
        // write code here
        int len = nums.length;
        if (len == 0)
            return 0;
        if (len == 1)
            return nums[0];
        if (len == 2)
            return Math.max(nums[0], nums[1]);
        if (len == 3)
            return Math.max(nums[0], Math.max(nums[1], nums[2]));
        int[] dp = new int[len];
        dp[0] = nums[0];
        dp[1] = Math.max(dp[0], nums[1]);
        for (int i = 2; i < len - 1; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }
        int max1 = dp[len - 2];

        Arrays.fill(dp, 0);
        dp[1] = nums[1];
        dp[2] = Math.max(dp[1], nums[2]);
        for (int i = 3; i < len; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }
        int max2 = dp[len - 1];
        return Math.max(max1, max2);
    }

    /*NC243 目标和
    给定一个整数数组nums和一个整数target，请你返回该数组能构成多少种不同的表达式等于target。
    规则如下：
    1.将数组里每个整数前面可以添加"+"或者"-"符号，组成一个表达式，例如[1,2]，可以变成”+1+2","+1-2","-1+2","-1-2"，这四种
    2.只能添加"+"与"-"符号，不能添加其他的符号
    3.如果构不成等于target的表达式，请返回0
    4.保证返回的结果个数在整数范围内*/
    public int findTargetSumWays(int[] nums, int target) {
        if (nums.length == 0)
            return 0;
        int sum = 0, index = 0;
        for (int num : nums)
            sum += num;
        if ((target + sum) % 2 != 0)
            return 0;
        index = (target + sum) / 2;
        int[] dp = new int[index + 1];
        dp[0] = 1;
        for (int i = 0; i < nums.length; i++) {
            for (int j = index; nums[i] <= j; j--) {
                dp[j] += dp[j - nums[i]];
            }
        }
        return dp[index];
    }

    /*NC180 给数组加一
    给定一个用数组表示的数字，即数组中每个数表示一个数位上的数，
    例如 [1,2,3]，表示 123 ，请问给这个数字加一后得到的结果（结果同样以数组的形式返回）。
    注意：数组中不可能出现负数，且保证数组的首位即数字的首位不可能是 0 。*/
    public int[] plusOne(int[] nums) {
        // write code here
        for (int i = nums.length - 1; i >= 0; i--) {
            if (nums[i] != 9) {
                nums[i] = nums[i] + 1;
                return nums;
            } else {
                nums[i] = 0;
            }
        }
        int[] tmp = new int[nums.length + 1];
        tmp[0] = 1;
        return tmp;
    }

    /*NC183 最长公共子数组
    给定两个整数数组，求两个数组的最长的公共子数组的长度。子数组是连续的，
    比如[1,3,5,7,9]的子数组有[1,3]，[3,5,7]等等，但是[1,3,7]不是子数组*/
    public int longestCommonSubarry(int[] A, int[] B) {
        int maxLen = 0;
        int[][] dp = new int[A.length + 1][B.length + 1];
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B.length; j++) {
                if (A[i] == B[j]) {
                    dp[i + 1][j + 1] = dp[i][j] + 1;
                    maxLen = Math.max(maxLen, dp[i + 1][j + 1]);
                } else {
                    dp[i + 1][j + 1] = 0;
                }
            }
        }
        return maxLen;
    }

    /*NC216 逆波兰表达式求值
    输入：["2","1","+","4","*"]
    返回值： 12*/
    public int evalRPN(String[] tokens) {
        //新建一个栈
        LinkedList<String> stack = new LinkedList<>();
        //遍历tokens数组
        for (String token : tokens) {
            //如果是数字，直接压入栈中
            if (!token.equals("+") && !token.equals("-") && !token.equals("*") && !token.equals("/")) {
                stack.push(token);
            } else {
                //如果是加减乘除，均需要两次弹出栈顶元素，再进行相应计算
                int val1 = Integer.parseInt(stack.pop());
                int val2 = Integer.parseInt(stack.pop());
                //如果是加号，将两个弹出的数相加，再压入栈中
                if (token.equals("+")) {
                    stack.push(String.valueOf(val2 + val1));
                }
                //如果是减号，将两个弹出的数相减，再压入栈中
                else if (token.equals("-")) {
                    stack.push(String.valueOf(val2 - val1));
                }
                //如果是乘号，将两个弹出的数相乘，再压入栈中
                else if (token.equals("*")) {
                    stack.push(String.valueOf(val2 * val1));
                }
                //如果是除号，将两个弹出的数相除，再压入栈中
                else {
                    stack.push(String.valueOf(val2 / val1));
                }
            }

        }
        return Integer.parseInt(stack.peek());
    }

    public int evalRPN02(String[] tokens) {
        // write code here
        int n = tokens.length;
        int[] stack = new int[(n + 1) / 2];
        int index = -1;
        for (int i = 0; i < n; i++) {
            String token = tokens[i];
            switch (token) {
                case "+":
                    index--;
                    stack[index] += stack[index + 1];
                    break;
                case "-":
                    index--;
                    stack[index] -= stack[index + 1];
                    break;
                case "*":
                    index--;
                    stack[index] *= stack[index + 1];
                    break;
                case "/":
                    index--;
                    stack[index] /= stack[index + 1];
                    break;
                default:
                    index++;
                    stack[index] = Integer.parseInt(token);
            }
        }
        return stack[index];
    }

    /*最长严格上升子数组(二)
    给定一个长度为n的正整数数组nums，可以任意改变数组的其中一个元素，然后返回nums的最长"严格上升"子数组的长度。*/
    public int maxSubArrayLengthTwo(int[] nums) {
        if (nums.length == 1) {
            return 1;
        }
        int n = nums.length;
        int[] l = new int[n], r = new int[n];
        l[0] = 1;
        for (int i = 1; i < n; i++) {
            if (nums[i] > nums[i - 1]) {
                l[i] = l[i - 1] + 1;
            } else {
                l[i] = 1;
            }
        }
        //从右向左遍历
        r[n - 1] = 1;
        for (int i = n - 2; i >= 0; i--) {
            if (nums[i + 1] > nums[i]) {
                r[i] = r[i + 1] + 1;
            } else {
                r[i] = 1;
            }
        }
        int maxLen = 1;
        for (int i = 0; i < n; i++) {
            if (i < n - 1 && nums[i + 1] > 1) {
                maxLen = Math.max(r[i + 1] + 1, maxLen);
            }
            if (i > 0) {
                maxLen = Math.max(l[i - 1] + 1, maxLen);
            }
            if (i > 0 && i < n - 1 && nums[i + 1] - nums[i - 1] >= 2) {
                maxLen = Math.max(l[i - 1] + r[i + 1] + 1, maxLen);
            }
        }
        return maxLen;
    }

    /*NC209 最短无序连续子数组
    给定一个整数数组，你需要找出一个连续子数组，将这个子数组升序排列后整个数组都将是升序数组。
    请你找出满足题设的最短的子数组。*/
    public int findUnsortedSubarray(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return 0;
        }
        int start = 0, end = -1;
        int startNum = Integer.MAX_VALUE, endNum = Integer.MIN_VALUE;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < nums[i - 1]) {
                startNum = startNum < nums[i] ? startNum : nums[i];
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (startNum < nums[i]) {
                start = i;
                break;
            }
        }
        for (int i = nums.length - 1; i > 0; i--) {
            if (nums[i - 1] > nums[i]) {
                endNum = endNum > nums[i - 1] ? endNum : nums[i - 1];
            }
        }
        for (int i = nums.length - 1; i > 0; i--) {
            if (endNum > nums[i]) {
                end = i;
                break;
            }
        }
        return end - start + 1;
    }

    /*NC148 几步可以从头跳到尾-跳格子
    给你一个长度为 n 的数组 a。
    ai 表示从 i 这个位置开始最多能往后跳多少格。
    求从 1 开始最少需要跳几次就能到达第 n 个格子。
    最少需要跳跃几次能跳到末尾
     * @param n int整型 数组A的长度
     * @param A int整型一维数组 数组A
     * @return int整型
    */
    public int jumpSteps(int n, int[] A) {
        int end = 0;
        int count = 0;
        int i = 0;
        while (i < n - 1) {
            int step = A[i];
            int max = 0;
            int next = 0;
            for (int j = i + 1; j <= i + step; j++) {
                if (j >= A.length - 1) {
                    next = A.length - 1;
                    break;
                }
                if (j + A[j] > max) {
                    max = j + A[j];
                    next = j;
                }
            }
            count++;
            i = next;
        }
        return count;
    }

    public int jumpSteps02(int n, int[] A) {
        // 步数
        int step = 0;
        // 数组为空，或者长度为1，返回0
        if (A == null || A.length == 0 || A.length == 1) {
            return 0;
        }
        // 每走一步能够到达的最远位置
        int right = 0;
        // 当前这一步所处的位置
        int index = 0;
        // 遍历数组的指针
        int i = 0;
        int len = n - 1;
        while (index < n && i < len) {
            // 找出最远能够到达的位置
            right = Math.max(right, i + A[i]);
            // 遍历完了从index处能够走到的所有的位置
            // 走一步
            if (index == i) {
                index = right;
                ++step;
            }
            ++i;
        }
        return step;
    }

    /*NC202 长度最小的连续子数组
    给定一个数组 nums 和一个正整数 target ,
    找出满足和大于等于 target 的长度最短的连续子数组并返回其长度，如果不存在这种子数组则返回 0。*/
    public int minSubarray(int[] nums, int target) {
        // write code here
        int n = nums.length;

        int min = Integer.MAX_VALUE;
        if (n == 0) {
            return 0;
        }
        int[] presum = new int[n + 1];
        presum[0] = 0;
        for (int i = 0; i < n; i++) {
            presum[i + 1] = presum[i] + nums[i];
            if (nums[i] >= target) {
                return 1;
            }
        }
        int i = 0;
        int j = 1;
        while (j <= n) {
            if (presum[j] - presum[i] < target) {
                j++;
            } else {
                //大于target ，且要求最小的，所以i++，j-i 就变小
                min = Math.min(min, j - i);
                i++;
            }
        }
        return min;
    }

    /*NC160 二分查找-I
    请实现无重复数字的升序数组的二分查找
    给定一个 元素升序的、无重复数字的整型数组 nums 和一个目标值 target ，
    写一个函数搜索 nums 中的 target，如果目标值存在返回下标（下标从 0 开始），否则返回 -1*/
    public int binarySearch(int[] nums, int target) {
        // write code here
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        if (nums[left] == target) {
            return left;
        }
        return -1;
    }

    /*NC105 二分查找-II
    请实现有重复数字的升序数组的二分查找
    给定一个 元素有序的（升序）长度为n的整型数组 nums 和一个目标值 target
    ，写一个函数搜索 nums 中的第一个出现的target，如果目标值存在返回下标，否则返回 -1*/
    public int binarySearch02(int[] nums, int target) {
        //边界条件判断
        if (nums == null || nums.length == 0)
            return -1;
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return nums[left] == target ? left : -1;
    }

    /*NC147 主持人调度
    有 n 个活动即将举办，每个活动都有开始时间与活动的结束时间，第 i 个活动的开始时间是 starti ,
    第 i 个活动的结束时间是 endi ,举办某个活动就需要为该活动准备一个活动主持人。
    一位活动主持人在同一时间只能参与一个活动。并且活动主持人需要全程参与活动，
    换句话说，一个主持人参与了第 i 个活动，那么该主持人在 (starti,endi) 这个时间段不能参与其他任何活动。
    求为了成功举办这 n 个活动，最少需要多少名主持人。
    */
    public int minmumNumberOfHost(int n, int[][] startEnd) {
        int start[] = new int[n];
        int end[] = new int[n];
        for (int i = 0; i < n; i++) {
            start[i] = startEnd[i][0];
            end[i] = startEnd[i][1];
        }
        Arrays.sort(start);
        Arrays.sort(end);
        int count = 0, endindex = 0;
        for (int i = 0; i < n; i++) {
            if (start[i] < end[endindex]) {
                count++;
            } else {
                endindex++;
            }
        }
        return count;
    }

    /*NC197 跳跃游戏(一)
    给定一个非负整数数组nums，假定最开始处于下标为0的位置，
    数组里面的每个元素代表下一跳能够跳跃的最大长度。如果能够跳到数组最后一个位置，则返回true，否则返回false。
    输入：[2,1,3,3,0,0,100]
    返回值：true
    说明：
    首先位于nums[0]=2，然后可以跳2步，到nums[2]=3的位置，再跳到nums[3]=3的位置，
    再直接跳到nums[6]=100，可以跳到最后，返回true
    */
    public boolean canJump01(int[] nums) {
        if (nums.length == 0)
            return true;
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i <= max) {
                max = Math.max(max, nums[i] + i);
                if (max >= nums.length - 1)
                    return true;
            }
        }
        return false;
    }

    /*NC206 跳跃游戏(二)
    给定一个非负整数数组nums，假定最开始处于下标为0的位置，数组里面的每个元素代表下一跳能够跳跃的最大长度，
    如果可以跳到数组最后一个位置，请你求出跳跃路径中所能获得的最多的积分。
    1.如果能够跳到数组最后一个位置，才能计算所获得的积分，否则积分值为-1
    2.如果无法跳跃(即数组长度为0)，也请返回-1
    3.数据保证返回的结果不会超过整形范围，即不会超过2^{31}-12 31
    输入：[2,4,2,1,0,100]
    返回值：106
    说明：
    首先位于nums[0]=2，然后可以跳1步，到nums[1]=4的位置，积分=2+4=6，再跳到nums[5]=100的位置，积分=6+100=106
    这样保证既能跳到数组最后一个元素，又能保证获取的积分最多  */
    public int maxJumpGrade02(int[] nums) {
        if (nums.length == 0) {
            return -1;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int[] partNum = new int[nums.length];
        int[] sum = new int[nums.length];
        sum[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            for (int j = i - 1; ; ) {
                if (j + nums[j] >= i) {//能到达
                    partNum[i] = j + 1;
                    sum[i] = nums[i] + sum[j];
                    break;
                } else {
                    if (partNum[j] != 0) {
                        j = partNum[j] - 1;
                    } else {
                        return -1;
                    }
                }
            }
        }
        return sum[nums.length - 1];
    }

    /*NC205 跳跃游戏(三)
    给定一个非负整数数组nums，假定最开始处于下标为0的位置，数组里面的每个元素代表下一跳能够跳跃的最大长度。
    请你判断最少跳几次能跳到数组最后一个位置。
    1.如果跳不到数组最后一个位置或者无法跳跃(即数组长度为0)，请返回-1
    2.数据保证返回的结果不会超过整形范围，即不会超过2^{31}-12
    输入：[2,1,3,3,0,0,100]
    返回值：3
    说明：
    首先位于nums[0]=2，然后可以跳2步，到nums[2]=3的位置，step=1
    再跳到nums[3]=3的位置，step=2
    再直接跳到nums[6]=100，可以跳到最后，step=3，返回3 */
    public int minJumpStep(int[] nums) {
        // write code here
        if (nums.length == 0)
            return -1;
        int cnt = 0, pos = 0, reach = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            reach = Math.max(reach, i + nums[i]);
            if (reach < i + 1)
                return -1;
            if (pos >= nums.length - 1)
                break;
            if (i == pos) {
                cnt++;
                pos = reach;
            }
        }
        return cnt;
    }

    /*NC41 最长无重复子数组
    给定一个长度为n的数组arr，返回arr的最长无重复元素子数组的长度，无重复指的是所有数字都不相同。
    子数组是连续的，比如[1,3,5,7,9]的子数组有[1,3]，[3,5,7]等等，但是[1,3,7]不是子数组*/
    public int maxLength(int[] arr) {
        if (arr.length < 2) {
            return arr.length;
        }
        HashMap<Integer, Integer> windows = new HashMap<>();
        int res = 0;
        //用双指针来模拟一个滑动窗口
        int left = -1;
        //窗口向右滑动
        for (int right = 0; right < arr.length; right++) {
            //遇到重复数字
            if (windows.containsKey(arr[right])) {
                //因为有可能遇到的重复数字的位置 比 left还要前
                //所以不能把left置于该位置前一位， 而是比较哪个最大，目的还是为了缩小窗口
                //确保窗口内全是不重复的数字
                left = Math.max(left, windows.get(arr[right]));
            }
            //每次更新窗口大小
            res = Math.max(res, right - left);
            //将数字位置更新到windows中
            windows.put(arr[right], right);
        }
        return res;
    }

    /*NC37 合并区间
    给出一组区间，请合并所有重叠的区间。
    请保证合并后的区间按区间起点升序排列。
    输入：[[10,30],[20,60],[80,100],[150,180]]
    返回值：[[10,60],[80,100],[150,180]]*/
    public ArrayList<Interval> mergeInterval(ArrayList<Interval> intervals) {
        Collections.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval a, Interval b) {
                return a.start - b.start;
            }
        });
        ArrayList<Interval> ans = new ArrayList<Interval>();
        int len = intervals.size();
        if (len == 0)
            return ans;
        for (int i = 1; i < len; i++) {
            if (intervals.get(i).start <= intervals.get(i - 1).end) {
                intervals.get(i).start = Math.min(intervals.get(i - 1).start, intervals.get(i).start);
                intervals.get(i).end = Math.max(intervals.get(i - 1).end, intervals.get(i).end);
            } else {
                ans.add(intervals.get(i - 1));
            }
        }
        ans.add(intervals.get(len - 1));
        return ans;
    }

    /*NC213 除自身以外数组的乘积
    给定一个长度为 n 的数组 nums ，返回一个数组 res，res[i]是nums数组中除了nums[i]本身以外其余所有元素的乘积，即：
    1.请不要使用除法，并且在 O(n) 时间复杂度内完成此题。
    2.题目数据保证res数组的元素都在 32 位整数范围内
    3.有O(1)空间复杂度的做法，返回的res数组不计入空间复杂度计算*/
    public int[] timesExceptSelf(int[] nums) {
        int n = nums.length;
        int preTime[] = new int[n];
        int postTime[] = new int[n];
        postTime[n - 1] = 1;
        preTime[0] = 1;
        int result[] = new int[n];
        //倒着各元素的乘积
        for (int i = n - 2; i >= 0; i--) {
            postTime[i] = postTime[i + 1] * nums[i + 1];
        }
        //顺着各元素的乘积
        for (int i = 1; i < n; i++) {
            preTime[i] = preTime[i - 1] * nums[i - 1];
            result[i] = preTime[i] * postTime[i];
        }
        result[0] = postTime[0];
        return result;
    }

    //超时
    public int[] timesExceptSelf02(int[] nums) {
        int n = nums.length;
        int result[] = new int[n];
        for (int i = 0; i < nums.length; i++) {
            result[i] = excludeSelfMulti(nums, i);
        }
        return result;
    }

    private int excludeSelfMulti(int[] nums, int k) {
        int len = nums.length;
        if (k > len) {
            return 0;
        }
        int m = 1;
        for (int i = 0; i < nums.length; i++) {
            int tmp = nums[i];
            if (i == k) {
                tmp = 1;
            }
            m *= tmp;
        }
        return m;
    }

    /*NC212 颜色分类
    给定一个包含红色，白色，蓝色，一同 n 个元素的数组，对其进行排序使得相同的颜色相邻并且按照 红色，白色，蓝色的顺序排序。
    数组中 0 代表红色，1 代表白色，2 代表蓝色。*/
    public int[] sortColor(int[] colors) {
        // write code here
        int r = 0, w = 0, b = 0, n = colors.length;
        for (int i = 0; i < n; i++) {
            if (colors[i] == 0) {
                colors[b++] = 2;
                colors[w++] = 1;
                colors[r++] = 0;
            } else if (colors[i] == 1) {
                colors[b++] = 2;
                colors[w++] = 1;
            } else {
                colors[b++] = 2;
            }
        }
        return colors;
    }

    public int[] sortColor02(int[] colors) {
        int i1 = 0, i2 = 0;
        for (int i : colors) {
            if (i == 0) i1++;
            if (i == 1) i2++;
        }
        for (int i = 0; i < colors.length; i++) {
            if (i < i1) colors[i] = 0;
            if (i >= i1 && i < i1 + i2) colors[i] = 1;
            if (i >= i1 + i2) colors[i] = 2;
        }
        return colors;
    }

    /*NC194 下一个排列
    给定一个数组，将数组重新排列，得到一系列数组排列S，请你从S中，找出恰好比当前数组排列字典序大于1的数组排列。
            1.[1,2,3]的得到的数组排列S有:[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]。
            2.该题数组排列的字典序大小排序规则：2个数组排列的元素按顺序比较，直到数组元素不相等为止，不相等的第一个元素，谁的元素大，谁的字典序比较大，比如数组a=[1,2,3]与数组b=[1,3,2]比较：a[0]=b[0]，a[1]<b[1]，此时出现了第一个不相同的，且a[1]<b[1]，则a的字典序小于b的字典序。且[1,3,2]的字典序在排列S中，正好在[1,2,3]的后面，视为[1,3,2]的字典序比[1,2,3]的字典序大于1。
            3.如果不存在更大的数组排列，则返回最小的数组排列。*/
    public int[] nextPermutation(int[] nums) {
        int l = 0;
        int r = nums.length - 1;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] < nums[i + 1]) {
                l = i;
            }
        }
        for (int i = l + 1; i < nums.length; i++) {
            if (nums[i] > nums[l]) {
                r = i;
            }
        }
        int temp = nums[l];
        nums[l] = nums[r];
        nums[r] = temp;
        Arrays.sort(nums, l + 1, nums.length);
        return nums;
    }

    /*NC200 移动 0
    给定一个数组，请你实现将所有 0 移动到数组末尾并且不改变其他数字的相对顺序。*/
    public int[] moveZeroes(int[] nums) {
        if (nums.length == 1) return nums;
        int low = 0, high = 0;
        while (low < nums.length) {
            if (nums[low] == 0) {
                high = low + 1;
                while (high < nums.length && nums[high] == 0) high++;
                if (high < nums.length) {
                    nums[low] = nums[high];
                    nums[high] = 0;
                    low++;
                } else {
                    break;
                }
            } else {
                low++;
            }
        }
        return nums;
    }

    public int[] moveZeroes02 (int[] nums) {
        // write code here
        int[] nums2=new int[nums.length];
        int j=0;
        for(int i=0;i<nums.length;i++){
            if(nums[i]!=0){
                nums2[j++]=nums[i];
            }
        }
        return nums2;
    }

    public int[] moveZeroes03 (int[] nums) {
        // write code here
        int index = 0;
        for(int i = 0;i<nums.length;i++){
            if(nums[i]!=0)nums[index++]=nums[i];
        }
        for(int i=index;i<nums.length;i++){
            nums[i]=0;
        }
        return nums;
    }
    /*NC214 分割等和子集
    给定一个只包含正整数的数组 nums ，
    请问能否把这个数组取出若干个数使得取出的数之和和剩下的数之和相同。*/
    boolean partition(int[] nums) {
        int s = 0;
        for (int i = 0; i < nums.length; i++) {
            s += nums[i];
        }
        if (s % 2 == 1) return false; // 奇数
        //考虑用aval[i][j]=true/false表示，前i个值，能否选取一部分，使得它们的和为j
        boolean[] aval = new boolean[s + 1]; // 复用数组
        aval[0] = true;
        for (int i = 0; i < nums.length; i++) { // 枚举每个位置
            for (int v = s; v >= 0; v--) { // 逆序枚举
                if (!aval[v]) continue; // 不可达
                aval[v + nums[i]] = true; // 因为复用，只用更新变化
            }
        }
        return aval[s / 2];
    }

    public boolean partition02 (int[] nums) {
        //sum记录累加和，max记录最大值
        int sum=0;
        int max=0;
        //遍历nums数组
        for(int num:nums){
            sum+=num;
            max=Math.max(max,num);
        }
        //如果累加和为奇数，直接返回false，如果最大值大于累加和的一半，也直接返回false
        if(sum%2==1) return false;
        if(max>sum/2) return false;
        int n=nums.length;
        //定义dp数组，dp[i][j]表示i个元素内，是否能凑成目标和为j
        boolean[][] dp=new boolean[n+1][sum/2+1];

        //初始化
        dp[0][0]=true;
        for(int i=0;i<n;i++){
            for(int j=0;j<=sum/2;j++){
                //如果大于当前元素，要么选，要么不选
                if(j>=nums[i]){
                    dp[i+1][j]=dp[i][j]|dp[i][j-nums[i]];
                }
                //否则，默认不选
                else{
                    dp[i+1][j]=dp[i][j];
                }

            }
        }
        return dp[n][sum/2];
    }

    public boolean partition03 (int[] nums) {
        //分别记录累加和以及最大值
        int sum=0,max=0;
        //遍历nums数组
        for(int num:nums){
            sum+=num;
            max=Math.max(max,num);
        }
        //如果累加和为奇数，直接返回false，如果最大值大于累加和的一半，也直接返回false
        if(sum%2==1) return false;
        if(max>sum/2) return false;
        //定义dp数组,dp[i]表示数组中的数字能否凑成目标和为i的情况
        boolean[] dp=new boolean[sum/2+1];
        int n=nums.length;
        //初始化
        dp[0]=true;
        for(int i=0;i<n;i++){
            //每个数只能选一次，所以从后往前更新
            for(int j=sum/2;j>=nums[i];j--){
                dp[j]|=dp[j-nums[i]];
            }
        }
        return dp[sum/2];
    }
    /*NC208 每日温度
    根据往后 n 天的天气预报，计算每一天需要等待几天才会出现一次更高的气温，如果往后都没有更高的气温，则用 0 补位。
    例如往后三天的气温是 [1,2,3] ， 则输出 [1,1,0]*/
    public int[] temperatures (int[] temperatures) {
        // write code here
        int n = temperatures.length;
        int[] res= new int[n];
        for(int i=0;i<n;i++){
            if(temperatures[i]<100){
                for(int j=i+1;j<n;j++){
                    if(temperatures[j]>temperatures[i]){
                        res[i]=j-i;
                        break;
                    }
                }
            }
        }
        return res;
    }

    // 单调栈，栈空或不满足预期条件（即出现大的温度）的入栈，满足的出栈处理，即构建单调减栈
    public int[] dailyTemperatures(int[] T){
        int[] ans = new int[T.length];
        Stack<Integer> stack = new Stack<>();
        // 遍历
        for (int i = 0; i < T.length; i++) {
            // 当栈非空且达到预期条件，则出栈处理，使用while循环判断,直到没达到预期条件入栈
            while(!stack.isEmpty() && T[i]>T[stack.peek()]){
                ans[stack.peek()] = i - stack.pop();
            }
            // 栈空或不满足预期条件的入栈
            stack.push(i);
        }
        return ans;
    }

    /*给定两个没有重复元素的数组 nums1 和 nums2 ，其中nums1 是 nums2 的子集。找到 nums1 中每个元素在 nums2 中的下一个比其大的值。
    nums1 中数字 x 的下一个更大元素是指 x 在 nums2 中对应位置的右边的第一个比 x 大的元素。如果不存在，对应位置输出-1。
    示例 1:
    输入: nums1 = [4,1,2], nums2 = [1,3,4,2].
    输出: [-1,3,-1]
    解释:
    对于num1中的数字4，你无法在第二个数组中找到下一个更大的数字，因此输出 -1。
    对于num1中的数字1，第二个数组中数字1右边的下一个较大数字是 3。
    对于num1中的数字2，第二个数组中没有下一个更大的数字，因此输出 -1。*/
    /**
     * 我们可以忽略数组 nums1，先对将 nums2 中的每一个元素，求出其下一个更大的元素。
     * 随后对于将这些答案放入哈希映射（HashMap）中，再遍历数组 nums1，并直接找出答案。
     * 对于 nums2，我们可以使用单调栈来解决这个问题。
     * */
    public int[] nextGreaterElement(int[] nums1, int[] nums2){
        HashMap<Integer, Integer> map = new HashMap<>();
        Stack<Integer> stack = new Stack<>();
        int[] ans = new int[nums1.length];
        for (int i = 0; i < nums2.length; i++) {
            while(!stack.isEmpty() && stack.peek()<nums2[i]){
                map.put(stack.pop(),nums2[i]);
            }
            stack.push(nums2[i]);
        }
        while (!stack.isEmpty()){
            map.put(stack.pop(),-1);
        }
        for (int i = 0; i < nums1.length; i++) {
            ans[i] = map.get(nums1[i]);
        }
        return ans;
    }

    /*下一个更大元素 II
    给定一个循环数组（最后一个元素的下一个元素是数组的第一个元素），输出每个元素的下一个更大元素。数字 x 的下一个更大的元素是按数组遍历顺序，这个数字之后的第一个比它更大的数，这意味着你应该循环地搜索它的下一个更大的数。如果不存在，则输出 -1。
    示例 1:

    输入: [1,2,1]
    输出: [2,-1,2]
    解释: 第一个 1 的下一个更大的数是 2；
    数字 2 找不到下一个更大的数；
    第二个 1 的下一个最大的数需要循环搜索，结果也是 2。
    注意: 输入数组的长度不会超过 10000。*/
    public int[] nextGreaterElements(int[] nums) {
        int[] nextGretEleArr = new int[nums.length];
        List<Integer> stack = new LinkedList();
        Arrays.fill(nextGretEleArr,-1);//初始化结果数组
        for(int i=0;i<2*nums.length-1;i++){
            if(stack.size()==0){
                stack.add(i%nums.length);
            }else{
                while(stack.size()>0&&nums[stack.get(stack.size()-1)]<nums[i%nums.length]){
                    //当前元素大于栈顶索引对应的元素，说明找到了栈顶索引对应元素的下一个更大元素，即为当前元素
                    nextGretEleArr[stack.remove(stack.size()-1)] = nums[i%nums.length];
                }
                stack.add(i%nums.length);
            }
        }
        return nextGretEleArr;
    }

}

class Interval {
    int start;
    int end;

    public Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }
}
