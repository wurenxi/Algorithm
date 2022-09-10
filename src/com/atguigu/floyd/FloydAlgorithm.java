package com.atguigu.floyd;

import java.util.Arrays;

/**
 * @author gxl
 * @description
 * @createDate 2022/9/10 10:06
 */
public class FloydAlgorithm {
    public static void main(String[] args) {
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        // 创建邻接矩阵
        final int N = 65535;
        int[][] matrix = {
                {0, 5, 7, N, N, N, 2},
                {5, 0, N, 9, N, N, 3},
                {7, N, 0, N, 8, N, N},
                {N, 9, N, 0, N, 4, N},
                {N, N, 8, N, 0, 5, 4},
                {N, N, N, 4, 5, 0, 6},
                {2, 3, N, N, 4, 6, 0}
        };

        Graph graph = new Graph(vertex.length, matrix, vertex);
//        graph.show();
        graph.floyd();
        graph.show();
    }
}

class Graph {
    private char[] vertex; // 存放顶点的数组

    private int[][] dis; // 保存从各个顶点出发到其他顶点的距离，最后的结果，也是保留在该数组

    private int[][] pre; // 保存到达目标顶点的前驱顶点

    /**
     *
     * @param length 大小
     * @param matrix 邻接矩阵
     * @param vertex 顶点数组
     */
    public Graph(int length, int[][] matrix, char[] vertex) {
        this.vertex = vertex;
        this.dis = matrix;
        this.pre = new int[length][length];
        // 对pre数组初始化，注意存放的是前驱顶点的下标
        for (int i = 0; i < length; i++) {
            Arrays.fill(pre[i], i);
        }
    }

    // 显示pre数组和dis数组
    public void show() {
        for (int k = 0; k < dis.length; k++) {
            // 输出pre数组一行的数据
            for (int i = 0; i < dis.length; i++) {
                System.out.print(vertex[pre[k][i]] + " ");
            }
            System.out.println();
            // 输出dis数组的一行数据
            for (int i = 0; i < dis.length; i++) {
                System.out.print("("+ vertex[k] +"到"+ vertex[i] +"的最短路径是" + dis[k][i] + ") ");
            }
            System.out.println();
        }
    }

    // 弗洛伊德算法
    public void floyd() {
        int len = 0; // 保存距离
        // 对中间顶点的遍历，k就是中间顶点的下标[A, B, C, D, E, F ,G]
        for (int k = 0; k < dis.length; k++) {
            // 从i顶点开始出发[A, B, C, D, E, F ,G]
            for (int i = 0; i < dis.length; i++) {
                // 到达j顶点
                for (int j = 0; j < dis.length; j++) {
                    len = dis[i][k] + dis[k][j]; // 求出从i顶点出发，经过k中间顶点，到达j顶点距离
                    if(len < dis[i][j]) {
                        dis[i][j] = len; // 更新距离
                        pre[i][j] = pre[k][j];
                    }
                }
            }
        }
    }
}
