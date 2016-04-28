package com.fz.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2016/4/28.
 */
public class DateUtil {
    public static String formatDate(Date date,String format){
        String result = "";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        if(date!=null){
            result = sdf.format(date);
        }
        return result;
    }

    public static Date formatString(String str,String format){
        if(StringUtil.isEmpty(str)){
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            return sdf.parse(str);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public static String getCurrentDateStr(){
        Date date=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddhhmmss");
        return sdf.format(date);
    }
}
