package com.fz.service.impl;

import com.fz.dao.DepartmentDao;
import com.fz.model.Department;
import com.fz.model.PageBean;
import com.fz.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2016/4/26.
 */
@Service
public class DepartmentServiceImpl implements DepartmentService{

    @Autowired
    private DepartmentDao departmentDao;

    @Override
    public List<Department> find(PageBean pageBean, Department s_department) {
        return departmentDao.find(pageBean,s_department);
    }

    @Override
    public int count(Department s_department) {
        return departmentDao.count(s_department);
    }

    @Override
    public void add(Department s_department) {
        departmentDao.add(s_department);
    }

    @Override
    public void delete(int id) {
        departmentDao.delete(id);
    }

    @Override
    public Department loadById(int id) {
        return departmentDao.loadById(id);
    }

    @Override
    public void update(Department department) {
        departmentDao.update(department);
    }
}
