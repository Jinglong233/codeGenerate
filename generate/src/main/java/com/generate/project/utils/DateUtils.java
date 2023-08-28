package com.generate.project.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy_MM_dd HH:mm:ss";

    public static final String YYYY_MM_DD = "yyyy_MM_dd";
    public static final String _YYYYMMDD = "yyyy/MM/dd";
    public static final String YYYYMMDD = "yyyyMMdd";

    public static String format(Date date, String patten) {
        return new SimpleDateFormat(patten).format(date);
    }

    public static Date parse(String date, String patten) {
        try {
            return new SimpleDateFormat(patten).parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
