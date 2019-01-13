package com.zzp.interview;

/**
 * 给定形如下 的矩阵， 1 1 1 1 1 1 1 1 0 0 0 1 1 0 0 0 1 0 1 1 0 1 1 1 0 1 0 1 0 0 1 1 1 1 1 1 上面矩阵的中的1代表海岸线，
 * 0代表小岛。求第二岛的面积(即 被1中包围的0的个数，如果只有一个小岛，输出最大岛的面积)。! 注意: 1. 仅求这样的0，该0所在行中被两个1包围，该0所在列中被 两个1包围; 2.
 * 输入矩阵中包含的小岛K >= 1; 样例输入 :  1 1 1 1 1 1 1 1 0 0 0 1 1 0 0 0 1 0 1 1 0 1 1 1 0 1 0 1 0 0 1 1 1 1 1 1
 * 样例输出: 8
 *
 * 设计方案：由于被1包围的数字才是有效数字，确定grid[i][j]是有效数字，当且仅当： 左边存在1 &&右边存在1 && 上边存在1 && 下边存在1
 * 所以遍历grid[i][j]四次，确定下来grid[i][j]是否是有效数字， 然后使用深度优先搜索找到最大面积的小岛 时间复杂度：O(M*N) 空间复杂度O(M*N)
 */
public class MaxIsland {

  public static void main(String[] args) {
    MaxIsland maxIsland = new MaxIsland();
    int[][] grid = {
        {1, 1, 1, 1, 1, 1},
        {1, 1, 0, 0, 0, 1},
        {1, 0, 0, 0, 1, 0},
        {1, 1, 0, 1, 1, 1},
        {0, 1, 0, 1, 0, 0},
        {1, 1, 1, 1, 1, 1}};
    System.out.println(maxIsland.maxAreaOfIsland(grid));
  }

  boolean[][] valid;
  boolean[][] seen;

  public int maxAreaOfIsland(int[][] grid) {
    boolean[][] isLeftValid = new boolean[grid.length][grid[0].length];
    boolean[][] isRightValid = new boolean[grid.length][grid[0].length];
    boolean[][] isTopValid = new boolean[grid.length][grid[0].length];
    boolean[][] isBottomValid = new boolean[grid.length][grid[0].length];
    boolean[][] isNodeValid = new boolean[grid.length][grid[0].length];

    //得到当前点是否是左边存在1，同时当前节点是0
    for (int i = 0; i < grid.length; i++) {
      boolean nextValid = false;
      for (int j = 0; j < grid[0].length; j++) {
        if (grid[i][j] == 0 && nextValid) {
          isLeftValid[i][j] = true;
        } else {
          nextValid = true;
        }
      }
    }

    //得到当前点是否右边存在1，同时当前节点是0
    for (int i = 0; i < grid.length; i++) {
      boolean nextValid = false;
      for (int j = grid[0].length - 1; j >= 0; j--) {
        if (grid[i][j] == 0 && nextValid) {
          isRightValid[i][j] = true;
        } else {
          nextValid = true;
        }
      }
    }

    //得到当前节点是否上方存在1，同时当前节点是0
    for (int j = 0; j < grid[0].length; j++) {
      boolean nextValid = false;
      for (int i = 0; i < grid.length; i++) {
        if (grid[i][j] == 0 && nextValid) {
          isTopValid[i][j] = true;
        } else {
          nextValid = true;
        }
      }
    }

    //得到当前节点是否下方存在1，同时当前节点是0
    for (int j = 0; j < grid[0].length; j++) {
      boolean nextValid = false;
      for (int i = grid.length - 1; i >= 0; i--) {
        if (grid[i][j] == 0 && nextValid) {
          isBottomValid[i][j] = true;
        } else {
          nextValid = true;
        }
      }
    }

    //得到当前节点是否是一个有效节点
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[0].length; j++) {
        isNodeValid[i][j] =
            isLeftValid[i][j] && isRightValid[i][j] && isTopValid[i][j] && isBottomValid[i][j];
      }
    }

    this.valid = isNodeValid;
    seen = new boolean[valid.length][valid[0].length];
    int ans = 0;
    //遍历所有的节点，查找最大的面积
    for (int r = 0; r < valid.length; r++) {
      for (int c = 0; c < valid[0].length; c++) {
        ans = Math.max(ans, area(r, c));
      }
    }
    return ans;
  }

  public int area(int r, int c) {
    if (r < 0 || r >= valid.length || c < 0 || c >= valid[0].length ||
        seen[r][c] || !valid[r][c]) {
      return 0;
    }
    seen[r][c] = true;
    return (1 + area(r + 1, c) + area(r - 1, c)
        + area(r, c - 1) + area(r, c + 1));
  }
}
