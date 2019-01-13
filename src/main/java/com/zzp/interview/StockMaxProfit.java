package com.zzp.interview;

public class StockMaxProfit {

  public static void main(String[] args) {
    int nums[] = {4,3,6,7};
    System.out.println(stockMaxProfit(nums));
  }
  /**
   * *题目3: 设计- 一个股票模拟交易系统。假设我们有一一个很牛叉的AI系统，已经预测到未来一段时间内给定股票的价格，以数组来表示，它的第i个元素是一支给定的股票在第i天的价格。 假设:
   * 1.如果你最多只允许完成两次交易(一次交易是指: 买入和卖出); 2.你有本金K单位(K>=1)，一单位本金可以购买1股票; 这意味着你寻找的是K单位本金条件下最大利润。 提示:
   * K=1的时候最简单，可以先考虑。 设计一个算法来找出最大利润。
   *
   * 从左到右遍历，求得第i点之前的最大利润，再从右到左遍历，得到第i点之后的最大利润 时间复杂度O(3n) = O(n) 空间复杂度O(2n) = O(n)
   */
  public static int stockMaxProfit(int[] nums) {
    if (nums == null || nums.length == 0) {
      return 0;
    }
    int len = nums.length;
    if (len == 0) {
      return 0;
    }

    int[] pre = new int[nums.length], post = new int[nums.length];
    int minPrice = nums[0];
    //计算第i点之前的最大利润pre
    for (int i = 1; i < len; i++) {
      minPrice = Math.min(minPrice, nums[i]);
      pre[i] = Math.max(pre[i - 1], nums[i] - minPrice);
    }
    //计算第i点之后的最大利润post
    int maxPrice = nums[len - 1];
    for (int i = len - 2; i >= 0; i--) {
      maxPrice = Math.max(maxPrice, nums[i]);
      post[i] = Math.max(post[i + 1], maxPrice - nums[i]);
    }
    int maxProfit = 0;
    //计算第i点的，pre[i]与post[i]之和的最大值，赋值给maxProfit
    for (int i = 0; i < nums.length; i++) {
      maxProfit = Math.max(maxProfit, pre[i] + post[i]);
    }
    return maxProfit;
  }
}
