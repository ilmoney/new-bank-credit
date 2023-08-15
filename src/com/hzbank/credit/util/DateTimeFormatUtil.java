package com.hzbank.credit.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeFormatUtil {

    static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static String dateToStr(Date date){
        String dateFormat = simpleDateFormat.format(date);
        return dateFormat;
    }
}
