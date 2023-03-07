package com.yautumn.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    /**
     * 日期转yyyy-MM-dd字符串
     * @param date
     * @return
     */
    public static String dateToString(Date date){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        String str=sdf.format(date);
        return str;
    }

    /**
     * 日期转yyyy-MM-dd HH:mm:SS字符串
     * @param date
     * @return
     */
    public static String dateTimeToString(Date date){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:SS");
        String str=sdf.format(date);
        return str;
    }
}
