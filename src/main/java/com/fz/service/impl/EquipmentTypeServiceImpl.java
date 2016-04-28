package com.fz.service.impl;

import com.fz.dao.EquipmentTypeDao;
import com.fz.model.Equipment;
import com.fz.model.EquipmentType;
import com.fz.model.PageBean;
import com.fz.service.EquipmentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2016/4/27.
 */
@Service
public class EquipmentTypeServiceImpl implements EquipmentTypeService{

    @Autowired
    private EquipmentTypeDao equipmentTypeDao;

    @Override
    public List<EquipmentType> find(PageBean pageBean, EquipmentType equipmentType) {
        return equipmentTypeDao.find(pageBean,equipmentType);
    }

    @Override
    public int total(EquipmentType equipmentType) {
        return equipmentTypeDao.total(equipmentType);
    }

    @Override
    public EquipmentType loadById(int id) {
        return equipmentTypeDao.loadById(id);
    }

    @Override
    public void update(EquipmentType equipmentType) {
        equipmentTypeDao.update(equipmentType);
    }

    @Override
    public void add(EquipmentType equipmentType) {
        equipmentTypeDao.add(equipmentType);
    }

    @Override
    public void delete( int id) {
        equipmentTypeDao.delete(id);
    }

    @Override
    public boolean existEquipmentByTypeId(int id) {
        return equipmentTypeDao.existEquipmentByTypeId(id);
    }


}
