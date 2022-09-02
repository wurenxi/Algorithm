package com.atguigu.greedy;

import java.util.*;

/**
 * @author gxl
 * @description
 * @createDate 2022/9/2 10:46
 */
public class GreedyAlgorithm {
    public static void main(String[] args) {
        // 创建广播电台，放入Map
        Map<String, Set<String>> broadcasts = new HashMap<>();
        // 将各个电台放入到broadcasts
        Set<String> hashSet1 = new HashSet<>();
        hashSet1.add("北京");
        hashSet1.add("上海");
        hashSet1.add("天津");

        Set<String> hashSet2 = new HashSet<>();
        hashSet2.add("广州");
        hashSet2.add("上海");
        hashSet2.add("深圳");

        Set<String> hashSet3 = new HashSet<>();
        hashSet3.add("成都");
        hashSet3.add("上海");
        hashSet3.add("杭州");

        Set<String> hashSet4 = new HashSet<>();
        hashSet4.add("天津");
        hashSet4.add("上海");

        Set<String> hashSet5 = new HashSet<>();
        hashSet5.add("杭州");
        hashSet5.add("大连");

        // 加入到map
        broadcasts.put("K1", hashSet1);
        broadcasts.put("K2", hashSet2);
        broadcasts.put("K3", hashSet3);
        broadcasts.put("K4", hashSet4);
        broadcasts.put("K5", hashSet5);

        // 存放所有的地区
        Set<String> allAreas = new HashSet<>();
        allAreas.add("北京");
        allAreas.add("上海");
        allAreas.add("天津");
        allAreas.add("广州");
        allAreas.add("深圳");
        allAreas.add("成都");
        allAreas.add("杭州");
        allAreas.add("大连");

        // 创建ArrayList，存放选择的电台集合
        List<String> selects = new LinkedList<>();
        // 定义一个临时的集合，在遍历过程中，存放遍历过程中的电台覆盖的地区和当前还没有覆盖的地区的交集
        Set<String> tmpSet = new HashSet<>();
        // 定义maxKey，保存在一次遍历过程中，能够覆盖最多未覆盖的地区对应的电台的key
        // 如果maxKey不为null，则会加入到selects
        String maxKey = null;
        while (allAreas.size() > 0) { // 如果allAreas不为0，则表示还没有覆盖到所有的地区
            // 每进行一次while，需要
            maxKey = null;
            int maxSize = 0;
            // 遍历 broadcasts，取出对应key
            for(String key : broadcasts.keySet()) {
                // 每进行一次for
                tmpSet.clear();
                // 当前这个key能覆盖的地区
                Set<String> areas = broadcasts.get(key);
                tmpSet.addAll(areas);
                // 求出tmpSet和allAreas集合的交集，交集会赋给tmpSet
                tmpSet.retainAll(allAreas);
                // 如果当前这个集合包含的未覆盖地区的数量，比maxKey指向的集合未覆盖的地区还多
                // 就需要重置maxKey
                // tmpSet.size() > broadcasts.get(maxKey).size() 贪心算法体现
                // !!! 感觉有点问题，这里应该是与所有城市交集的结果进行比较
//                if(tmpSet.size() > 0 && (maxKey == null || tmpSet.size() > broadcasts.get(maxKey).size())) {
//                    maxKey = key;
//                }
                if(tmpSet.size() > 0 && (maxKey == null || tmpSet.size() > maxSize)) {
                    maxKey = key;
                    maxSize = tmpSet.size();
                }
            }

            if(maxKey != null) {
                selects.add(maxKey);
                // 将maxKey指向的广播电台覆盖的地区，从allAreas去掉
                allAreas.removeAll(broadcasts.get(maxKey));
            }
        }

        System.out.println("得到的选择结果是" + selects);
    }
}
