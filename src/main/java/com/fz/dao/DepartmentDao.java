package com.fz.dao;

import com.fz.model.Department;
import com.fz.model.PageBean;

import java.util.List;

/**
 * Created by Administrator on 2016/4/26.
 */
public interface DepartmentDao {

    /**
     * 获取部门列表
     * @param pageBean
     * @param s_department
     * @return
     */
    public List<Department> find(PageBean pageBean,Department s_department);

    /**
     * 获取部门总数
     * @param s_department
     * @return
     */
    public int count(Department s_department);

    /**
     * 新增部门
     * @param s_department
     */
    public void add(Department s_department);

    /**
     * 删除某部门
     * @param id
     */
    public void delete(int id);

    /**
     * 根据id获取department
     * @param id
     * @return
     */
    public Department loadById(int id);

    /**
     * 更新department
     * @param department
     */
    public void update(Department department);

}
