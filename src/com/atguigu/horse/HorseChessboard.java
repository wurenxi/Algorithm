package com.atguigu.horse;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author gxl
 * @description
 * @createDate 2022/9/11 10:13
 */
public class HorseChessboard {

    private static int X; // 棋盘的列

    private static int Y; // 棋盘的行

    // 创建一个数组，标记棋盘的各个位置是否被访问过
    private static boolean visited[];

    // 使用一个属性，标记
    private static boolean finished; // 如果为true，表示成功

    public static void main(String[] args) {
        X = 8;
        Y = 8;
        int row = 1; // 初始位置的行，从1开始编号
        int column = 1;
        // 创建棋盘
        int[][] chessboard = new int[X][Y];
        visited = new boolean[X * Y];

        long startTime = System.currentTimeMillis();
        traversalChessboard(chessboard, row - 1, column - 1, 1);
        long endTime = System.currentTimeMillis();
        // 未用贪心算法 ---costTime: 24156 毫秒
        // 贪心算法 ---costTime: 65 毫秒
        System.out.println("---costTime: "+(endTime - startTime)+" 毫秒");

        // 输出棋盘最后情况
        for(int[] rows : chessboard) {
            for (int step : rows) {
                System.out.print(step + "\t");
            }
            System.out.println();
        }
    }

    /**
     * 完成骑士周游问题的算法
     * @param chessboard 棋盘
     * @param row 当前位置的行
     * @param col 当前位置的列
     * @param step 是第几步，初始位置就是第1步
     */
    public static void traversalChessboard(int[][] chessboard, int row, int col, int step) {
        chessboard[row][col] = step;
        visited[row * X + col] = true; // 标记该位置已访问
        // 获取当前位置可以走的下一个位置的集合
        ArrayList<Point> ps = next(new Point(col, row));
        // 对ps进行排序，排序的规则就是堆ps的所有的Point对象的下一步的位置的数目，进行非递减排序
        sort(ps);
        // 遍历ps
        while (!ps.isEmpty()) {
            Point p = ps.remove(0); // 取出下一个可以走的位置
            // 判断该点是否已经访问过
            if(!visited[p.y * X + p.x]) { // 还没有访问过
                traversalChessboard(chessboard, p.y, p.x, step + 1);
            }
        }

        // 判断马儿是否完成了任务，使用step和应该走的步数比较
        // 说明：step < X * Y 成立的情况有两个
        // 1.棋盘到目前为止，仍然没有走完
        // 2.棋盘处于一个回溯过程
        if(step < X * Y && !finished) {
            chessboard[row][col] = 0;
            visited[row * X + col] = false;
        }else {
            finished = true;
        }
    }

    /**
     * 根据当前位置，计算马儿还能走到哪些位置(Point），并放入到一个集合中(ArrayList)，最多有8个位置
     * @param curPoint
     * @return
     */
    public static ArrayList<Point> next(Point curPoint) {
        ArrayList<Point> points = new ArrayList<>();
        // 创建一个Point
        Point p1 = new Point();
        if((p1.x = curPoint.x - 2) >= 0 && (p1.y = curPoint.y - 1) >= 0) {
            points.add(new Point(p1));
        }
        if((p1.x = curPoint.x - 1) >= 0 && (p1.y = curPoint.y - 2) >= 0) {
            points.add(new Point(p1));
        }
        if((p1.x = curPoint.x + 1) < X && (p1.y = curPoint.y - 2) >= 0) {
            points.add(new Point(p1));
        }
        if((p1.x = curPoint.x + 2) < X && (p1.y = curPoint.y - 1) >= 0) {
            points.add(new Point(p1));
        }
        if((p1.x = curPoint.x + 2) < X && (p1.y = curPoint.y + 1) < Y) {
            points.add(new Point(p1));
        }
        if((p1.x = curPoint.x + 1) < X && (p1.y = curPoint.y + 2) < Y) {
            points.add(new Point(p1));
        }
        if((p1.x = curPoint.x - 1) >= 0 && (p1.y = curPoint.y + 2) < Y) {
            points.add(new Point(p1));
        }
        if((p1.x = curPoint.x - 2) >= 0 && (p1.y = curPoint.y + 1) < Y) {
            points.add(new Point(p1));
        }

        return points;
    }

    // 根据当前这一步的所有的下一步的选择位置，进行非递减排序，减少回溯的次数
    public static void sort(ArrayList<Point> ps) {
        ps.sort((p1, p2) -> {
            int count1 = next(p1).size();
            int count2 = next(p2).size();
            if(count1 == count2) {
                return 0;
            }else {
                return count1 - count2;
            }
        });
    }
}
