package com.fz.controller;

import com.alibaba.fastjson.JSONObject;
import com.fz.model.Department;
import com.fz.model.PageBean;
import com.fz.service.DepartmentService;
import com.fz.service.UserService;
import com.fz.util.PageUtil;
import com.fz.util.ResponseUtil;
import com.fz.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 2016/4/26.
 */

@Controller
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private UserService userService;

    @RequestMapping("/list")
    public ModelAndView list(@RequestParam(value = "page",required = false)String page,
                             Department s_department, HttpServletRequest request){
        ModelAndView mav = new ModelAndView();
        HttpSession session = request.getSession();
        if(StringUtil.isEmpty(page)){
            page = "1";
            session.setAttribute("s_department",s_department);
        }else{
            s_department = (Department) session.getAttribute("s_department");
        }
        PageBean pageBean = new PageBean(Integer.parseInt(page),3);
        List<Department> departmentList = departmentService.find(pageBean,s_department);
        int total = departmentService.count(s_department);
        String pageCode = PageUtil.getPage(request.getContextPath()+"/department/list.do",
                total,Integer.parseInt(page),3);
        mav.addObject("pageCode",pageCode);
        mav.addObject("modeName","部门管理");
        mav.addObject("departmentList",departmentList);
        mav.addObject("mainPage","department/list.jsp");
        mav.setViewName("main");
        return mav;
    }
    @RequestMapping("/preSave")
    public ModelAndView preSave(@RequestParam(value = "id",required = false)String id,@RequestParam(value = "deptName",defaultValue = "")String deptName){
        ModelAndView mav = new ModelAndView();
        mav.addObject("mainPage","department/save.jsp");
        mav.addObject("modeName","部门管理");
        mav.setViewName("main");
        if(StringUtil.isNotEmpty(id)){
            mav.addObject("actionName","部门修改");
            Department department = departmentService.loadById(Integer.parseInt(id));
            mav.addObject("department",department);
        }else{
            mav.addObject("actionName","部门添加");
        }
        return mav;
    }
    @RequestMapping("/save")
    public String save(Department department){
        if(department.getId() == null) {
            departmentService.add(department);
        }else{
            departmentService.update(department);
        }
        return "redirect:/department/list.do";
    }

    @RequestMapping("/delete")
    public void delete(@RequestParam(value = "id")String id,HttpServletResponse response) throws IOException {
        JSONObject result = new JSONObject();
        boolean flag = userService.existUserByDeptId(Integer.parseInt(id));
        if(flag){
            //表示该部门下有人员
            result.put("errorInfo","该部门下存在用户，不能删除!");
        }else {
            //表示该部门下没有人员
            departmentService.delete(Integer.parseInt(id));
            result.put("successInfo",true);
        }
        ResponseUtil.write(result,response);
    }
}
