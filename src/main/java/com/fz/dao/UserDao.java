package com.fz.dao;

import com.fz.model.PageBean;
import com.fz.model.User;

import java.util.List;

/**
 * Created by Administrator on 2016/4/26.
 */
public interface UserDao {

    public User login(User user);

    public List<User> find(PageBean pageBean,User s_user);

    public int count(User u_user);

    public void delete(int id);

    public User loadById(int id);

    public void add(User user);

    public void update(User user);

    public boolean existUserByDeptId(int depId);

}
