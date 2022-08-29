package com.atguigu.dac;

/**
 * @author gxl
 * @description
 * @createDate 2022/8/29 8:41
 */
public class Hanoitower {
    public static void main(String[] args) {
        hanoiTower(3, 'A', 'B', 'C');
    }

    // 汉诺塔移动的方法
    // 分治算法
    public static void hanoiTower(int num, char a, char b, char c) {
        // 如果只有一个盘
        if(num == 1) {
            System.out.println("第1个盘从 " + a + "->" + c);
        }else{
            // num >= 2情况，总是看成两个盘，最下面的盘和上面所有的盘
            // 1.上面所有盘A->B
            hanoiTower(num - 1, a, c, b);
            // 2.把最下面的盘 A -> C
            System.out.println("第" + num + "个盘从 " + a + "->" + c);
            // 3.把B塔的所有盘移动到C塔，移动过程使用到A塔
            hanoiTower(num - 1, b, a, c);
        }
    }
}
