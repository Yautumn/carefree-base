package com.yautumn.common.utils;


import java.util.UUID;

public class GenerateUtil {

    /**
     * 生成32位UUID
     * @return
     */
    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-","");
    }

}
