package com.fz.util;

/**
 * 分页工具类
 */
public class PageUtil {


    /**
     * 获取分页代码
     * @param targetUrl 目标地址
     * @param totalNum 总记录数
     * @param currentPage 当前页
     * @param pageSize 每页大小
     * @return
     */
    public static String getPage(String targetUrl,int totalNum,int currentPage,
                                     int pageSize){
        int totalPage=totalNum%pageSize==0?totalNum/pageSize:totalNum/pageSize+1;
        if(totalPage==0){
            return "<font color=red>this is no data！</font>";
        }
        StringBuffer pageCode=new StringBuffer();
        pageCode.append("<li><a href='"+targetUrl+"?page=1'>First</a></li>");
        if(currentPage==1){
            pageCode.append("<li class='disabled'><a href='#'>Previous</a></li>");
        }else{
            pageCode.append("<li><a href='"+targetUrl+"?page="+(currentPage-1)+"'>Previous</a></li>");
        }
        for(int i=currentPage-2;i<=currentPage+2;i++){
            if(i<1 || i>totalPage){
                continue;
            }
            if(i==currentPage){
                pageCode.append("<li class='active'><a href='#'>"+i+"</a></li>");
            }else{
                pageCode.append("<li><a href='"+targetUrl+"?page="+i+"'>"+i+"</a></li>");
            }
        }
        if(currentPage==totalPage){
            pageCode.append("<li class='disabled'><a href='#'>Next</a></li>");
        }else{
            pageCode.append("<li><a href='"+targetUrl+"?page="+(currentPage+1)+"'>Next</a></li>");
        }
        pageCode.append("<li><a href='"+targetUrl+"?page="+totalPage+"'>End</a></li>");
        return pageCode.toString();
    }

}
