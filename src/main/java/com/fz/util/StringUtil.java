package com.fz.util;

import java.util.Random;

/**
 * Created by Administrator on 2016/4/26.
 */
public class StringUtil {

    /**
     * 判断字符串是否为空
     * @param str
     * @return
     */
    public static boolean isEmpty(String str){
        if(str==null || "".equals(str.trim())){
            return true;
        }else {
            return false;
        }
    }

    /**
     * 判断是否不是空
     * @param str
     * @return
     */
    public static boolean isNotEmpty(String str){
        if((str!=null) && !"".equals(str.trim())){
            return true;
        }else{
            return false;
        }
    }

    /**
     * 生成tokenId
     * @return
     */
    public static String getTokenId(){
        String[] strings = new String[]{"0","+","a","b","1","A","D","2","8","9","g","G","n","Z","c","p","q","w"};
        StringBuffer sb = new StringBuffer();
        for (int i=0;i<15;i++){
            Random random = new Random();
            int temp = random.nextInt(strings.length);
            sb.append(strings[temp]);
        }
        return sb.toString();
    }

}
