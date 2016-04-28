package com.fz.service;

import com.fz.model.Equipment;
import com.fz.model.EquipmentType;
import com.fz.model.PageBean;

import java.util.List;

/**
 * Created by Administrator on 2016/4/27.
 */
public interface EquipmentTypeService {

    public List<EquipmentType> find(PageBean pageBean,EquipmentType equipmentType);

    public int total(EquipmentType equipmentType);

    public EquipmentType loadById(int id);

    public void update(EquipmentType equipmentType);

    public void add(EquipmentType equipmentType);

    public void delete(int id );

    public boolean existEquipmentByTypeId(int id);
}
