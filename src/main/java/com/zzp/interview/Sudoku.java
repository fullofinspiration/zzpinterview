package com.zzp.interview;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 给定一组数字， 一组有9个数字，将这9个数字填写到3*3 的九宫格内; 使得横， 竖，斜对角一条线上的三个数字之和相 等;如果无解则打印无解
 *
 * 设计：尝试在每个节点放置所有的未使用过的值，直到所有的节点都放置了值，然后看 横竖斜的和是否相等，如果相等，就将结果保存，遍历出所有可能的结果
 * 使用boolean[used]标记是否使用过该节点，使用 List<List<Integer>> result保存结果，如果有多个结果，从里面选取第一个打印出来
 *
 * 时间复杂度：O(n!) 空间复杂度：O(n)
 */
public class Sudoku {

  //如果无解则输出无解
  public static void main(String args[]) {
    int[] nums = {4, 9, 8, 11, 7, 3, 6, 5, 10};
    Sudoku sudoku = new Sudoku();
    int[][] result = sudoku.getSudoku(nums);
    if (result == null) {
      System.out.println("无解");
    }
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        System.out.println(result[i][j]);
      }
    }
  }

  public int[][] getSudoku(int[] nums) {
    if (nums == null || nums.length == 0) {
      return null;
    }
    boolean[] used = new boolean[9];
    List<List<Integer>> result = new LinkedList<>();
    backtrack(nums, used, result, new LinkedList<>());
    if (result.size() == 0) {
      return null;
    }
    int[][] resultNums = new int[3][3];
    int k = 0;
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        resultNums[i][j] = result.get(0).get(k);
        k++;
      }
    }
    return resultNums;
  }

  //递归查找
  private void backtrack(int[] nums, boolean[] used, List<List<Integer>> result,
      List<Integer> list) {
    if (list.size() == nums.length) {
      if (qualifySum(list)) {
        result.add(new ArrayList<>(list));
      }
    }
    for (int i = 0; i < nums.length; i++) {
      if (used[i]) {
        continue;
      }
      used[i] = true;
      list.add(nums[i]);
      backtrack(nums, used, result, list);
      list.remove(list.size() - 1);
      used[i] = false;
    }
  }

  //将list转化为二维数组
  public int[][] constructResult(List<Integer> list) {
    int[][] nums = new int[3][3];
    int k = 0;
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        nums[i][j] = list.get(k++);
      }
    }
    return nums;
  }

  //验证横竖斜相加的值是否相等
  public boolean qualifySum(List<Integer> list) {
    int nums[][] = constructResult(list);
    int sum = nums[0][0] + nums[0][1] + nums[0][2];
    for (int i = 0; i < 3; i++) {
      if (nums[i][0] + nums[i][1] + nums[i][2] != sum) {
        return false;
      }
    }
    for (int j = 0; j < 3; j++) {
      if (nums[0][j] + nums[1][j] + nums[2][j] != sum) {
        return false;
      }
    }
    if (nums[0][0] + nums[1][1] + nums[2][2] != sum
        || nums[0][2] + nums[1][1] + nums[2][0] != sum) {
      return false;
    }
    return true;
  }

}
