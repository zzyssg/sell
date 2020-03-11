package com.imooc.utils;

import java.util.Random;

public class KeyUtil {

    /**
     *
     * 时间 + 随机数
     *
     */


    public static synchronized String getUniqeKey() {
        Random random = new Random();
        Integer num = random.nextInt(900000) + 100000;
        return System.currentTimeMillis() + String.valueOf(num);
    }
}
