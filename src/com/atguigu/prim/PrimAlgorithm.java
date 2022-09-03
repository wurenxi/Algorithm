package com.atguigu.prim;

import java.util.Arrays;

/**
 * @author gxl
 * @description
 * @createDate 2022/9/3 10:25
 */
public class PrimAlgorithm {
    public static void main(String[] args) {
        // 测试图是否创建成功
        char[] data = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int verxs = data.length;
        // 邻接矩阵的关系使用二维数组描述，10000表示两个点不连通
        int[][] weight = {
                {10000, 5, 7, 10000, 10000, 10000, 2},
                {5, 10000, 10000, 9, 10000, 10000, 3},
                {7, 10000, 10000, 10000, 8, 10000, 10000},
                {10000, 9, 10000, 10000, 10000, 4, 10000},
                {10000, 10000, 8, 10000, 10000, 5, 4},
                {10000, 10000, 10000, 4, 5, 10000, 6},
                {2, 3, 10000, 10000, 4, 6, 10000}
        };

        MGraph graph = new MGraph(verxs);
        // 创建一个MinTree对象
        MinTree minTree = new MinTree();
        minTree.createGraph(graph,verxs, data, weight);
        // 输出
        minTree.showGraph(graph);

        // 测试prim算法
        minTree.prim(graph, 1);
    }
}

// 创建最小生成树->村庄的图
class MinTree {
    /**
     * 创建图的邻接矩阵
     * @param graph 图对象
     * @param verxs 顶点个数
     * @param data 顶点的值
     * @param weight 图的邻接矩阵
     */
    public void createGraph(MGraph graph, int verxs, char[] data, int[][] weight) {
        int i, j;
        for (i = 0; i < verxs; i++) {
            graph.data[i] = data[i];
            for(j = 0; j < verxs; j++) {
                graph.weight[i][j] = weight[i][j];
            }
        }
    }

    public void showGraph(MGraph graph) {
        for (int[] link : graph.weight) {
            System.out.println(Arrays.toString(link));
        }
    }

    /**
     * 编写Prim算法，得到最小生成树
     *
     * @param graph 图
     * @param v 表示从树的第几个订单开始生成
     */
    public void prim(MGraph graph, int v) {
        // 标记顶点是否被访问过
        int[] visited = new int[graph.verxs];
        // visited[] 默认元素的值都是0，表示没有访问过
//        for (int i = 0; i < graph.verxs; i++) {
//            visited[i] = 0;
//        }

        // 把当前这个节点标记为已访问
        visited[v] = 1;
        // h1 和 h2 记录两个顶点的下标
        int h1 = -1;
        int h2 = -1;
        int minWeight = 10000; // 将minWeight初始成大数，在遍历过程中，会被替换
        for (int k = 1; k < graph.verxs; k++) { // prim算法结束后，有graph.verxs - 1 边
            // 确定每一次生成的子图，和哪个顶点距离最近
            for (int i = 0; i < graph.verxs; i++) {
                for (int j = 0; j < graph.verxs; j++) {
                    if(visited[i] == 1 && visited[j] == 0 && graph.weight[i][j] < minWeight) {
                        minWeight = graph.weight[i][j];
                        h1 = i;
                        h2 = j;
                    }
                }
            }

            // 找到一条边是最小
            System.out.println("边<" + graph.data[h1] + "," + graph.data[h2] + "> 权值：" + minWeight);
            // 将当前这个顶点标记为已访问
            visited[h2] = 1;
            // minWeight重新设置为最大值
            minWeight = 10000;
        }
    }
}

class MGraph {
    int verxs; // 表示图的节点个数

    char[] data; // 保存节点数据

    int[][] weight; // 存放边。邻接矩阵

    public MGraph(int verxs) {
        this.verxs = verxs;
        data = new char[verxs];
        weight = new int[verxs][verxs];
    }
}
