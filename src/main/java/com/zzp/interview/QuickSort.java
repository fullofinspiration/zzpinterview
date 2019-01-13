package com.zzp.interview;

/**
 * 快排
 * 1、很巧妙，三个while循环，要注意的是所有的循环处理的结果都要添加start < end,即要保证
 * start == end 的情况只留着作为处理array[start] = pivot这一步，即只要出现start < end,就最后处理
 * 2、左边遍历完了右边遍历，左一遍右一遍
 * 3、很巧妙，首先将最低处的值保存在pivot中，然后只需将右边第一个最小的值赋值到该位置，这时候右边
 * 这个位置就空出来了，从左边找到第一个比pivot大的值放入到右边，最后的最后，pivot赋值
 * 4、要注意startIndex有用，还要新建一个变量start
 * 5、要注意这是一个递归算法，要有退出条件，及第一步的startIndex >= endIndex
 *
 */
public class QuickSort {
    public void quickSort(int[] array, int startIndex, int endIndex) {
        if (startIndex >= endIndex)
            return;
        int start = startIndex, end = endIndex;
        int pivot = array[start];
        while (start < end) {
            while (array[end] >= pivot && start < end)
                end--;
            if (start < end) {
                array[start++] = array[end];
            }
            while (array[start] <= pivot && start < end)
                start++;
            if (start < end) {
                array[end--] = array[start];
            }
        }
        array[start] = pivot;
        quickSort(array, startIndex, start - 1);
        quickSort(array, start + 1, endIndex);
    }

    public void quickSortAll(int[] array) {
        quickSort(array, 0, array.length - 1);
    }

    public static void main(String[] args) {
        int array[] = {5, 4, 5, 6, 2, 8, 9};
        QuickSort quickSort = new QuickSort();
        quickSort.quickSortAll(array);
        for (int i : array) {
            System.out.println(i);
        }
    }
}
