package com.zzp.interview;

/**
 * 对一个数组所有的非零值都放到左边，所有0的值都放在右边
 * 1、这里是受到快排中的所有都设置一个条件start < end ，而将所有不满足start < end 的情况
 * 都不做处理。
 * 2、我试了各种方法想直接求出非零数组长度，但是发现不行，只有最后遍历一遍数组才
 * 可以的。
 *
 */
public class FindNoZeroLength {
    public int findNoZeroLength(int[] array) {
        int start = 0, end = array.length - 1;
        while (start < end) {
            while (start < end && array[start] != 0)
                start++;
            while (start < end && array[end] == 0)
                end--;
            if (start < end) {
                array[start++] = array[end];
                array[end] = 0;
                end--;
            }
        }
        int i = 0;
        while (i < array.length && array[i] != 0)
            i++;
        return i;
    }

    public static void main(String[] args) {
        int[] array = {0, 0, 0, 0, 0, 0};
        FindNoZeroLength findNoZeroLength = new FindNoZeroLength();
        System.out.println(findNoZeroLength.findNoZeroLength(array));
    }
}
