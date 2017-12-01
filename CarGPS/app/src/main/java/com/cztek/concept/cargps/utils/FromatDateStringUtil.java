package com.cztek.concept.cargps.utils;

/**
 * Created by HH
 * Date: 2017/12/1
 */

public class FromatDateStringUtil {
    public static String defaultFormatDate(String dateStr){
        String newString;
        if (dateStr.length() >= 20){
            newString = dateStr.substring(0,19).replace("T"," ");
        }else {
            newString = dateStr;
        }
        return newString;
    }
}
