package com.atguigu.dynamic;

import java.util.Arrays;

/**
 * @author gxl
 * @description
 * @createDate 2022/8/30 11:03
 */
public class KnapsackProblem {
    public static void main(String[] args) {
        int[] w = {1, 4, 3}; // 物品的重量
        int[] val = {1500, 3000, 2000}; // 物品的价值
        int m = 4; // 背包的容量
        int n = val.length; // 物品的个数

        // 创建二维数组
        // v[i][j] 表示在前i个物品中能够装入容量为j的背包中的最大价值
        int[][] v = new int[n+1][m+1];
        // 记录放入商品的情况，定一个二维数组
        int[][] path = new int[n+1][m+1];

        // 初始化第一行和第一列，这里在本程序中，可以不去处理
        for (int i = 0; i < v.length; i++) {
            v[i][0] = 0; // 将第一列设置为0
        }
        for (int i = 0; i < v[0].length; i++) {
            v[0][i] = 0; // 将第一行设置为0
        }

        // 根据公式动态规划处理
        for (int i = 1; i < v.length; i++) { // 不处理第一行
            for (int j = 1; j < v[0].length; j++) { // 不处理第一列
                if(w[i - 1] > j) { //
                    v[i][j] = v[i-1][j];
                }else{
//                    v[i][j] = Math.max(v[i - 1][j], val[i - 1] + v[i - 1][j - w[i - 1]]);
                    // 为了记录商品存放到背包的情况，不能简单的使用公式
                    if(v[i - 1][j] < val[i - 1] + v[i - 1][j - w[i - 1]]) {
                        v[i][j] = val[i - 1] + v[i - 1][j - w[i - 1]];
                        // 把当前的情况记录到path
                        path[i][j] = 1;
                    }else{
                        v[i][j] = v[i - 1][j];
                    }
                }
            }
        }

        for (int i = 0; i < v.length; i++) {
            System.out.println(Arrays.toString(v[i]));
        }

        // 输出最后放入的哪些商品
        // 遍历path，这样输出会把所有的放入情况都得到，其实只需要最后的放入的情况
//        for (int i = 0; i < path.length; i++) {
//            for (int j = 0; j < path[i].length; j++) {
//                if(path[i][j] == 1) {
//                    System.out.println("第" + i + "个商品放入到背包");
//                }
//            }
//        }

        int i = path.length - 1;
        int j = path[0].length - 1;
        while (i > 0 && j > 0) { // 从path的最后开始找
            if(path[i][j] == 1) {
                System.out.println("第" + i + "个商品放入到背包");
                j -= w[i - 1];
            }
            i--;
        }
    }
}
