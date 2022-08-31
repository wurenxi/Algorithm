package com.atguigu.kmp;

/**
 * @author gxl
 * @description
 * @createDate 2022/8/31 8:19
 */
public class ViolenceMatch {
    public static void main(String[] args) {
        // 测试暴力匹配算法
        String str1 = "硅硅谷 尚硅谷你尚硅 尚硅谷你尚硅谷你尚硅你好";
        String str2 = "尚硅谷你尚硅你~";
        int index = violenceMatch(str1, str2);
        System.out.println("index = " + index); // 15
    }

    // 暴力匹配算法
    public static int violenceMatch(String str1, String str2) {
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();

        int s1Len = s1.length;
        int s2Len = s2.length;

        int i = 0; // i索引指向s1
        int j = 0; // j索引指向s2
        while (i < s1Len && j < s2Len) { // 保证匹配时，不越界
            if(s1[i] == s2[j]) {
                i++; j++;
            }else {
                i = i - (j - 1);
                j = 0;
            }
        }

        if(j == 0) {
            return -1;
        }

        return i - j;
    }
}
