package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static List<Integer> oldList = new ArrayList<>();
    public static List<Integer> newList = new ArrayList<>();

    public static void main(String[] args) {
        oldList.add(1);
        oldList.add(-1);
        oldList.add(4);
        oldList.add(1);
        oldList.add(5);
        oldList.add(1);
        oldList.add(0);

        System.out.println("oldList : " + oldList);

//        distinctMethod(oldList);
        distinctStreamMethod(oldList);

        System.out.println("newList : " + newList);
    }

    public static void distinctMethod(List<Integer> oldList) {
        newList.add(oldList.get(0));

        for (int i = 1; i < oldList.size(); i++) {
            if (!newList.contains(oldList.get(i))) {
                newList.add(oldList.get(i));
            }
        }
    }

    public static void distinctStreamMethod(List<Integer> oldList) {
        newList = oldList.stream()
                .distinct()
                .collect(Collectors.toList());
    }
}