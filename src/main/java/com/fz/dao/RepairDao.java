package com.fz.dao;

import com.fz.model.PageBean;
import com.fz.model.Repair;

import java.util.List;

/**
 * Created by Administrator on 2016/4/28.
 */
public interface RepairDao {

    public void add(Repair repair);

    public List<Repair> find(PageBean pageBean,Repair repair);

    public int total(Repair repair);
}
