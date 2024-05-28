package com.app.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;
public class Util {

    public static final SimpleDateFormat sqlDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    public static final SimpleDateFormat sqlDateTimeFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    public static Date getUtilDate(String expireOn) {
        try {
            return sqlDateFormat.parse(expireOn);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
