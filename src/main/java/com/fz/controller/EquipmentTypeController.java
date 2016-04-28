package com.fz.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fz.model.Equipment;
import com.fz.model.EquipmentType;
import com.fz.model.PageBean;
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
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Administrator on 2016/4/27.
 */
@Controller
@RequestMapping("/equipmentType")
public class EquipmentTypeController {

    @Autowired
    private EquipmentTypeService equipmentTypeService;

    @RequestMapping("/list")
    public ModelAndView list(@RequestParam(value = "page",required = false)String page,
                             EquipmentType equipmentType,
                             HttpServletRequest request){
        HttpSession session = request.getSession();
        if(StringUtil.isEmpty(page)){
            page = "1";
            session.setAttribute("equipmentType",equipmentType);
        }else{
            equipmentType = (EquipmentType) session.getAttribute("equipmentType");
        }
        PageBean pageBean = new PageBean(Integer.parseInt(page),3);
        List<EquipmentType> list = equipmentTypeService.find(pageBean,equipmentType);
        int total = equipmentTypeService.total(equipmentType);
        String pageCode = PageUtil.getPage(request.getContextPath()+"/equipmentType/list.do",total,Integer.parseInt(page),3);
        ModelAndView mav = new ModelAndView();
        mav.addObject("pageCode",pageCode);
        mav.addObject("modeName","设备类型管理");
        mav.addObject("equipmentTypeList",list);
        mav.addObject("mainPage","equipmentType/list.jsp");
        mav.setViewName("main");
        return mav;
    }

    @RequestMapping("/preSave")
    public ModelAndView preSave(@RequestParam(value = "id",required = false)String id){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("main");
        mav.addObject("mainPage","equipmentType/save.jsp");
        mav.addObject("modeName","设备管理");
        if(StringUtil.isNotEmpty(id)){
            mav.addObject("actionName","设备类型修改");
            EquipmentType equipmentType = equipmentTypeService.loadById(Integer.parseInt(id));
            mav.addObject("equipmentType",equipmentType);
        }else{
            mav.addObject("actionName","设备类型添加");
        }
        return mav;
    }
    @RequestMapping("/save")
    public String save(EquipmentType equipmentType){
        if(equipmentType.getId()!=null) {
            equipmentTypeService.update(equipmentType);
        }else{
            equipmentTypeService.add(equipmentType);
        }
        return "redirect:/equipmentType/list.do";
    }
    @RequestMapping("/delete")
    public @ResponseBody String delete(@RequestParam(value = "id")String id){
        JSONObject result = new JSONObject();
        if(equipmentTypeService.existEquipmentByTypeId(Integer.parseInt(id))){
            //表示有关联，不能删除
            result.put("message",false);
        }else{
            //没关联可以删除
            equipmentTypeService.delete(Integer.parseInt(id));
            result.put("message",true);
        }
        return JSON.toJSONString(result);
    }
}
