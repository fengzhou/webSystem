package com.fz.service.impl;

import com.fz.dao.EquipmentDao;
import com.fz.dao.RepairDao;
import com.fz.model.Equipment;
import com.fz.model.PageBean;
import com.fz.model.Repair;
import com.fz.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2016/4/27.
 */
@Service
public class EquipmentServiceImpl implements EquipmentService {


    @Autowired
    private EquipmentDao equipmentDao;

    @Autowired
    private RepairDao repairDao;

    @Override
    public List<Equipment> find(PageBean pageBean, Equipment equipment) {
        return equipmentDao.find(pageBean, equipment);
    }

    @Override
    public int total(Equipment equipment) {
        return equipmentDao.total(equipment);
    }

    @Override
    public void delete(int id) {
        equipmentDao.delete(id);
    }

    @Override
    public Equipment loadById(int id) {
        return equipmentDao.loadById(id);
    }

    @Override
    public void add(Equipment equipment) {
        equipmentDao.add(equipment);
    }

    @Override
    public void update(Equipment equipment) {
        equipmentDao.update(equipment);
    }

    @Override
    public void addRepair(int id, String userMan) {
        Repair repair = new Repair();
        repair.setEquipmentId(id);
        repair.setUserMan(userMan);
        repairDao.add(repair);
        Equipment equipment = equipmentDao.loadById(id);
        equipment.setState(2);
        equipmentDao.update(equipment);
    }
}
