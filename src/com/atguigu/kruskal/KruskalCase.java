package com.atguigu.kruskal;

import java.util.Arrays;

/**
 * @author gxl
 * @description
 * @createDate 2022/9/4 9:09
 */
public class KruskalCase {

    private int edgeNum; // 边的个数

    private char[] vertexs; // 顶点数组

    private int[][] matrix; // 邻接矩阵

    // 使用INF表示两个顶点不能连通
    private static final int INF = Integer.MAX_VALUE;

    // 构造器
    public KruskalCase(char[] vertexs, int[][] matrix) {
        // 拷贝
        this.vertexs = vertexs.clone();
        this.matrix = matrix.clone();
        // 统计边的条数
        for (int i = 0; i < matrix.length; i++) {
            for (int j = i + 1; j < matrix[i].length; j++) {
                if(this.matrix[i][j] != INF && this.matrix[i][j] != 0) {
                    edgeNum++;
                }
            }
        }
    }

    public void kruskal() {
        int index = 0; // 表示最后结果数组的索引
        int[] ends = new int[edgeNum]; // 保存"已有最小生成树"中的每个顶点在最小生成树中的终点
        // 创建结果数组，保存最后的最小生成树
        EData[] result = new EData[edgeNum];

        // 获取图中所有的边的集合，一共12条边
        EData[] edges = getEdges();

        // 按照边的权值大小进行排序（从小到大）
        sortEdges(edges, 0, edgeNum - 1);

        // 遍历edges数组，将边添加到最小生成树中，判断是准备加入的边是否形成回路，如果没有，就加入result，否则不能加入
        for (int i = 0; i < edgeNum; i++) {
            int p1 = getPosition(edges[i].start);
            int p2 = getPosition(edges[i].end);

            // 获取p1这个顶点在已有最小生成树中的终点
            int m = getEnd(ends, p1);
            int n = getEnd(ends, p2);
            // 判断是否构成回路
            if(m != n) { // 没有构成回路
                ends[m] = n; // 设置m在"已有最小生成树"中的终点
                result[index++] = edges[i];
            }
        }

        // 统计并打印"最小生成树"，输出result
        System.out.println("最小生成树为：");
        for (EData eData : result) {
            if(eData != null)
                System.out.println(eData);
        }
    }


    public void print() {
        System.out.println("邻接矩阵为：");
        for (int i = 0; i < vertexs.length; i++) {
            for (int j = 0; j < vertexs.length; j++) {
                System.out.printf("%11d", matrix[i][j]);
            }
            System.out.println();
        }
    }

    /**
     * 对边进行排序处理，快排
     * @param edges
     */
    public void sortEdges(EData[] edges, int left, int right) {
        int l = left;
        int r = right;
        EData mediumValue = edges[(left + right) / 2];
        while (l < r) {
            while (edges[l].weight < mediumValue.weight) {
                l++;
            }

            while (edges[r].weight > mediumValue.weight) {
                r--;
            }

            if(l >= r) {
                break;
            }

            EData tmp = edges[l];
            edges[l] = edges[r];
            edges[r] = tmp;

            if(edges[r].weight == mediumValue.weight) {
                l++;
            }

            if(edges[l].weight == mediumValue.weight) {
                r--;
            }
        }

        l++;
        r--;

        if(r > left) {
            sortEdges(edges, left, r);
        }

        if(l < right) {
            sortEdges(edges, l, right);
        }
    }

    /**
     *
     * @param ch 顶点的值，比如'A' 'B'
     * @return 返回ch顶点对应的下标，如果找不到，返回-1
     */
    private int getPosition(char ch) {
        for (int i = 0; i < vertexs.length; i++) {
            if(vertexs[i] == ch) {
                return i;
            }
        }

        return -1;
    }

    /**
     * 功能：获取图中边，放到EData[] 数组中
     * 通过matrix邻接矩阵来获取
     *
     * @return
     */
    private EData[] getEdges() {
        int index = 0;
        EData[] edges = new EData[edgeNum];
        for (int i = 0; i < vertexs.length; i++) {
            for (int j = i + 1; j < vertexs.length; j++) {
                if(matrix[i][j] != INF && matrix[i][j] != 0) {
                    edges[index++] = new EData(vertexs[i], vertexs[j], matrix[i][j]);
                }
            }
        }

        return edges;
    }

    /**
     * 获取下标为i的顶点的终点，用于后面判断两个顶点的终点是否相同
     * @param ends 数组就是记录各个顶点对应的终点是哪个，ends数组是在遍历过程中，逐步形成
     * @param i 表示传入的顶点对应的下标
     * @return 返回的就是下标为i的这个顶点对应的终点的下标
     */
    private int getEnd(int[] ends, int i) {
        while (ends[i] != 0) {
            i = ends[i];
        }

        return i;
    }

    public static void main(String[] args) {
        char[] vertexs = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[][] matrix = {
                /*A*//*B*//*C*//*D*//*E*//*F*//*G*/
                /*A*/{  0,  12, INF, INF, INF,  16,  14},
                /*B*/{ 12,   0,  10, INF, INF,   7, INF},
                /*C*/{INF,  10,   0,   3,   5,   6, INF},
                /*D*/{INF, INF,   3,   0,   4, INF, INF},
                /*E*/{INF, INF,   5,   4,   0,   2,   8},
                /*F*/{ 16,   7,   6, INF,   2,   0,   9},
                /*G*/{ 14, INF, INF, INF,   8,   9,   0}
        };

        KruskalCase kruskalCase = new KruskalCase(vertexs, matrix);
        kruskalCase.print();
        kruskalCase.kruskal();
    }
}

// 创建一个类EData，它的对象实例表示一条边
class EData {
    char start; // 边的一个点
    char end; // 边的另外一个点

    int weight; // 边的权值

    public EData(char start, char end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    // 输出边的信息
    @Override
    public String toString() {
        return "EData{" +
                "<" + start +
                ", " + end +
                ">= weight=" + weight +
                '}';
    }
}
