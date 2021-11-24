 
package com.mobilez.utils;
 
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class XDate {
    public static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY");
    public static Date toDate(String date, String pattern ){
        try {
            sdf.applyPattern(pattern);
            return sdf.parse(date);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static String toString (Date date, String pattern){
        sdf.applyPattern(pattern);
        return sdf.format(date);
    }
    public static Date addDay(Date date, long days){
        date.setTime(date.getTime()+ days * 24 * 60 *60 *1000);
        return date;
    }
    //Giang thêm code ngày để code phần nhân viên
    public static Date StringDate(String date,String...pattern){
        try {
            if(pattern.length>0)sdf.applyPattern(pattern[0]);
            return sdf.parse(date);
        } catch (ParseException ex) {
            throw new RuntimeException(ex); 
        }
    }
     public static String DateString(Date date, String...pattern){
        if(pattern.length>0)sdf.applyPattern(pattern[0]);
        return sdf.format(date);
    }
}
