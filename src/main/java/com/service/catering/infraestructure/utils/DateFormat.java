package com.service.catering.infraestructure.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormat {

    private static java.text.DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static String toDate(){
        return dateFormat.format(new Date() );
    }

}
