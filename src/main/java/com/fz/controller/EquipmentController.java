package com.fz.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fz.model.*;
import com.fz.service.EquipmentService;
import com.fz.service.EquipmentTypeService;
import com.fz.util.PageUtil;
import com.fz.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Administrator on 2016/4/27.
 */
@Controller
@RequestMapping("/equipment")
public class EquipmentController {

    @Autowired
    private EquipmentService equipmentService;

    @Autowired
    private EquipmentTypeService equipmentTypeService;

    @RequestMapping("/list")
    public ModelAndView list(@RequestParam(value = "page",required = false)String page,
                             Equipment equipment,
                             HttpServletRequest request){
        HttpSession session = request.getSession();
        if(StringUtil.isEmpty(page)){
            page = "1";
            session.setAttribute("equipment",equipment);
        }else{
            equipment = (Equipment) session.getAttribute("equipment");
        }
        PageBean pageBean = new PageBean(Integer.parseInt(page),3);
        List<Equipment> equipmentList = equipmentService.find(pageBean,equipment);
        int total = equipmentService.total(equipment);
        String pageCode = PageUtil.getPage(request.getContextPath()+"/equipment/list.do",total,Integer.parseInt(page),3);
        ModelAndView mav = new ModelAndView();
        mav.addObject("pageCode",pageCode);
        mav.addObject("mainPage","equipment/list.jsp");
        mav.addObject("equipmentList",equipmentList);
        mav.addObject("modeName","设备管理");
        mav.setViewName("main");
        return mav;
    }
    @RequestMapping("/delete")
    public @ResponseBody String delete(@RequestParam(value = "id")String id){
        JSONObject result = new JSONObject();
        equipmentService.delete(Integer.parseInt(id));
        result.put("success",true);
        return JSON.toJSONString(result);
    }
    @RequestMapping("/preSave")
    public ModelAndView preSave(@RequestParam(value = "id",required = false)String id){
        ModelAndView mav = new ModelAndView();
        if(StringUtil.isNotEmpty(id)){
            mav.addObject("actionName","更新设备");
            Equipment equipment = equipmentService.loadById(Integer.parseInt(id));
            mav.addObject("equipment",equipment);
        }else{
            mav.addObject("actionName","新增设备");
        }
        List<EquipmentType> equipmentTypeList = equipmentTypeService.find(null,null);
        mav.addObject("equipmentTypeList",equipmentTypeList);
        mav.addObject("mainPage","equipment/save.jsp");
        mav.addObject("modeName","设备管理");
        mav.setViewName("main");
        return mav;
    }
    @RequestMapping("/save")
    public String save(Equipment equipment){
        if(equipment.getId()==null){
            //新增
            equipmentService.add(equipment);
        }else{
            //更新
            equipmentService.update(equipment);
        }
        return "redirect:/equipment/list.do";
    }
    @RequestMapping("/userList")
    public ModelAndView userList(@RequestParam(value = "page",required = false)String page,
                                 HttpServletRequest request,
                                 Equipment equipment){
        ModelAndView mav = new ModelAndView();
        HttpSession session = request.getSession();
        if(StringUtil.isEmpty(page)){
            page = "1";
            session.setAttribute("equipment",equipment);
        }else{
            equipment = (Equipment) session.getAttribute("equipment");
        }
        PageBean pageBean = new PageBean(Integer.parseInt(page),3);
        int total = equipmentService.total(equipment);
        List<Equipment> equipmentList = equipmentService.find(pageBean,equipment);
        String pageCode = PageUtil.getPage(request.getContextPath()+"equipment/userList.do",total,Integer.parseInt(page),3);
        mav.addObject("pageCode",pageCode);
        mav.addObject("equipmentList",equipmentList);
        mav.addObject("modeName","使用设备管理");
        mav.addObject("mainPage","equipment/userList.jsp");
        mav.setViewName("main");
        return mav;
    }
    @RequestMapping("/repair")
    public @ResponseBody String repair(@RequestParam(value = "id")String id,
                                       HttpSession session){
        String uerMan = ((User)session.getAttribute("currentUser")).getUserName();
        equipmentService.addRepair(Integer.parseInt(id),uerMan);
        JSONObject result = new JSONObject();
        result.put("success",true);
        return JSON.toJSONString(result);
    }
    @RequestMapping("/repairList")
    public ModelAndView repairList(@RequestParam(value = "page",required = false)String page,
                                   Repair repair,
                                   HttpServletRequest request){
        ModelAndView mav = new ModelAndView();
        HttpSession session = request.getSession();
        if(StringUtil.isEmpty(page)){
            page = "1";
            session.setAttribute("s_repair",repair);
        }else{
            repair = (Repair) session.getAttribute("s_repair");
        }
        return mav;
    }
}
