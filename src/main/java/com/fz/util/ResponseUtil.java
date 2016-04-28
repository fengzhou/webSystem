package com.fz.util;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Administrator on 2016/4/26.
 */
public class ResponseUtil {


    public static void write(Object o, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter printWriter = response.getWriter();
        printWriter.print(o.toString());
        printWriter.flush();
        printWriter.close();
    }

}
