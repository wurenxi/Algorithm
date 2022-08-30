package com.atguigu.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author gxl
 * @description
 * @createDate 2022/8/30 12:45
 */
public class StreamTest {
    public static void main(String[] args) {
//        List<Integer> list = new ArrayList<>();
//        for (int i = 1; i <= 9 ; i++) {
//            list.add(i);
//        }
//
//        List<Integer> odd = list.stream().filter(item -> item % 2 == 0).collect(Collectors.toList());
//        System.out.println(odd);
//        List<Integer> js = list.stream().filter(item -> item % 2 == 1).collect(Collectors.toList());
//        System.out.println(js);
//
//        Integer oddSum = odd.stream().reduce(0, Integer::sum);
//        Integer jsSum = js.stream().reduce(0, Integer::sum);
//        System.out.println(oddSum);
//        System.out.println(jsSum);

        List<List<Integer>> list = new ArrayList<>();
        list.add(Arrays.asList(1, 3, 5, 7, 5, 3, 1));
        list.add(Arrays.asList(2, 4, 6, 8, 6, 4, 2));
        list.add(Arrays.asList(5, 8, 7));

        System.out.println(list);

//        List<String> newList = list.stream()
//                .flatMap(l -> l.stream().map(Object::toString))
//                .collect(Collectors.toList());

//        System.out.println(newList);

        List<String> newList2 = list.stream()
                .flatMap(l -> {
                    Integer sum = l.stream().reduce(0, Integer::sum);
                    return l.stream().map(item -> {
                        return String.valueOf((item * 1.0 / sum));
                    });
                })
                .collect(Collectors.toList());

        System.out.println(newList2);
    }
}
