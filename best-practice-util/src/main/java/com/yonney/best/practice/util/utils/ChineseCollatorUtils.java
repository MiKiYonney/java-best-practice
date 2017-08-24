package com.yonney.best.practice.util.utils;

import java.text.Collator;
import java.text.ParseException;
import java.text.RuleBasedCollator;
import java.util.Comparator;
import java.util.Locale;

/**
 * 大写的数字排序，可自定义排序
 */
public class ChineseCollatorUtils implements Comparator<String>, Comparable<String> {

    public static final String GB2312Chars = "<一<二<三<四<五<六<七<八<九<十<十一";
    private String str1;

    public ChineseCollatorUtils(String str1) {
        this.str1 = str1;
    }

    public ChineseCollatorUtils() {

    }

    public static final RuleBasedCollator getFixedGB2312Collator() {
        RuleBasedCollator fixedGB2312Collator = null;
        try {
            fixedGB2312Collator = new RuleBasedCollator(ChineseCollatorUtils.getGB2312SpecialChars() + GB2312Chars);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return fixedGB2312Collator;
    }

    public static final String getGB2312SpecialChars() {
        RuleBasedCollator zh_CNCollator = (RuleBasedCollator) Collator.getInstance(Locale.CHINA);
        return zh_CNCollator.getRules().substring(0, 2125);
    }

    public int compare(String str1, String str2) {
        return this.getFixedGB2312Collator().compare(str1, str2);
    }

    public int compareTo(String str2) {
        return this.getFixedGB2312Collator().compare(str1, str2);
    }
}
