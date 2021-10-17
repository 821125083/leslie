package com.leslie.utils;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtils {


    public static Date formatDate(Date date,String format){
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String format1 = sdf.format(date);
        DateTimeFormatter.ofPattern(format);
        LocalDate parse = LocalDate.parse(format1, DateTimeFormatter.ofPattern(format));
        return null;
    }

    public static String nowDateString(){

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return sdf.format(new Date());

    }
}
