package com.common.util;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * 汉语转拼音工具类
 */
public class Pinyin4jUtil {

    private static final HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();

    public Pinyin4jUtil() {
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        format.setVCharType(HanyuPinyinVCharType.WITH_V);
    }

    /**
     * 汉语转小写拼音全拼
     * 只实现了纯汉字转纯拼音
     * @param inputHanyu
     * @return
     */
    public static String toPinyinLowerCase(String inputHanyu){
        StringBuilder output = new StringBuilder("");
        char[] input = inputHanyu.replaceAll("\\s","").toCharArray();
        try {
            for (int i = 0; i < input.length; i++) {
                String[] temp = PinyinHelper.toHanyuPinyinStringArray(input[i], format);
                output.append(temp[0]);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return output.toString();
    }


    public static void main(String[] args) {
        LocalDate today = LocalDate.of(2023, 6, 29);
        DayOfWeek dayOfWeek = today.getDayOfWeek();
        LocalTime now = LocalTime.now();

        if (dayOfWeek == DayOfWeek.THURSDAY) {
            calculateTime(today, now);
        } else {
            LocalDate nextThursday = today.with(DayOfWeek.THURSDAY);
            if (nextThursday.isBefore(today)) {
                nextThursday = nextThursday.plusWeeks(1);
            }
            calculateTime(nextThursday, now);
        }
    }

    private static void calculateTime(LocalDate startDate, LocalTime now) {
        int count = 0;
        while (count < 7) {
            LocalDate date = startDate.plusDays(count);
            DayOfWeek week = date.getDayOfWeek();
            if (week == DayOfWeek.THURSDAY || week == DayOfWeek.FRIDAY) {
                System.out.println(date + " " + now);
                count++;
            }
        }
    }

}
