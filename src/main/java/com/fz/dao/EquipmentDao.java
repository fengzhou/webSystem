package com.fz.dao;

import com.fz.model.Equipment;
import com.fz.model.PageBean;
import com.fz.model.Repair;

import java.util.List;

/**
 * Created by Administrator on 2016/4/27.
 */
public interface EquipmentDao {

    public List<Equipment> find(PageBean pageBean,Equipment equipment);

    public int total(Equipment equipment);

    public void delete(int id);

    public Equipment loadById(int id);

    public void add(Equipment equipment);

    public void update(Equipment equipment);

}
