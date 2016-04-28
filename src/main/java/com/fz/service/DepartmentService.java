package com.fz.service;

import com.fz.model.Department;
import com.fz.model.PageBean;

import java.util.List;

/**
 * Created by Administrator on 2016/4/26.
 */
public interface DepartmentService {

    public List<Department> find(PageBean pageBean,Department s_department);

    public int count(Department s_department);

    public void add(Department s_department);

    public void delete(int id);

    public Department loadById(int id);

    public void update(Department department);

}
