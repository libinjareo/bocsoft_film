package com.binsoft.film.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式工具类
 */
public final class RegexUtil {


    /**
     * 判断是否是数字
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {
        String regEx = "^-?[0-9]+$";
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()) {
            return true;
        } else {
            return false;
        }


    }

}

