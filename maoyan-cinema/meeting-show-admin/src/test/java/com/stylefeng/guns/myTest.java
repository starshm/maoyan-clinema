package com.stylefeng.guns;

import java.util.Arrays;

/**
 * @author minghai
 * @description
 * @date 2020/5/5
 */
public class myTest {

    public static void main(String[] args) {

//        String cats = "#2#4#22#";
//        cats = cats.substring(1);
//        String[] split = cats.split("#");
//        System.out.println(split.length);
//        for (String s : split) {
//            System.out.print(s+ " ");
//        }
//        System.out.println(Arrays.toString(split));

        String[] actors = {"x","y","z"};
        String names = actors[0];
        for (int i = 1; i < actors.length; i++) {
            names+=","+actors[i];
        }
        System.out.println(names);

    }
}
