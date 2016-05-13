package com.etone.atm;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Maggie on 2016/5/13.
 */
public class Utils {

    private static final String DATE_FORMATE = "yyyy-MM-dd";

    public static String dateToString(Date date){
        return new SimpleDateFormat(DATE_FORMATE).format(date);
    }

    public static Date stringToDate(String cdate){
        Date date = null;
        try {
            date = new SimpleDateFormat(DATE_FORMATE).parse(cdate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }


}
