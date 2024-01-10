package com.buyer.controller;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.time.temporal.TemporalAdjusters;
import java.util.*;


/**
 * @Description:
 * @Author: lin
 * @Date: 2024/1/3 19:43
 */
public class TestController {

    public static void main(String[] args) {

        String timeString = "2023-08-29 00:00:00"; // 时间字符串

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); // 指定日期格式

        LocalDate startDate = LocalDate.parse(timeString, formatter); // 将时间字符串解析为 LocalDate 对象

        LocalDate endDate = LocalDate.now();
        int count = 0;
        LocalDate sunday = startDate.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
        while (sunday.isBefore(endDate)) {
            count++;
            sunday = sunday.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
        }

        if(count != 0){
            //说明开始时间与结束时间不在同一周,则要把结束时间的当周加上
            count ++;
        }
        System.out.println(count);


        LocalDate today = LocalDate.now();

        DayOfWeek dayOfWeek = today.getDayOfWeek();
        //星期
        String dayOfWeekStr = dayOfWeek.getDisplayName(TextStyle.FULL, Locale.ENGLISH);

        Map<String,String> map = new HashMap<>();
        map.put("Monday","星期一");
        map.put("Tuesday","星期二");
        map.put("Wednesday","星期三");
        map.put("Thursday","星期四");
        map.put("Friday","星期五");
        map.put("Saturday","星期六");
        map.put("Sunday","星期日");

        System.err.println(map.get(dayOfWeekStr));
        }
    }
