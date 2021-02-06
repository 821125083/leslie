//package com.leslie;
//
//import com.leslie.utils.DateUtils;
//import org.junit.Test;
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
//import java.util.Date;
//
//public class TestDate {
//
//    @Test
//    public void test1(){
//
//        Date date = DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
////        System.out.println(date);
//
//    }
//
//    @Test
//    public void test2() throws ParseException {
//        String format = "yyyy-MM-dd HH:mm:ss";
//        SimpleDateFormat sdf = new SimpleDateFormat(format);
//        String format1 = sdf.format(new Date());
//        DateTimeFormatter.ofPattern(format);
//        LocalDate parse = LocalDate.parse(format1, DateTimeFormatter.ofPattern(format));
//    }
//}
