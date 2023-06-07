package com.wanris.business.common.utils;

import java.util.Calendar;
import java.util.TimeZone;

/**
 * Created by Gerry on 2020/12/12 12:00
 * 时间转换相关方法
 */
@SuppressWarnings("ALL")
public class TimeHelper {

    public static TimeZone china = TimeZone.getTimeZone("GMT+8");

    public static final long minute = 60 * 1000; //分钟
    public static final long hour = 60 * minute; //小时
    public static final long day = 24 * hour;    //天
    public static final long week = 7 * day;     //周
    public static final long month = 31 * day;   //月
    public static final long year = 12 * month;  //年

    public static long getCurrentTime() {
        return Calendar.getInstance().getTimeInMillis();
    }

}