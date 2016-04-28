package com.fz.service;

import com.fz.model.PageBean;
import com.fz.model.User;

import java.util.List;

/**
 * Created by Administrator on 2016/4/26.
 */
public interface UserService {

    public User login(User user);

    public List<User> find(PageBean pageBean,User user);

    public int count(User user);

    public void delete(int id);

    public User loadById(int id);

    public void add(User user);

    public void update(User user);

    public boolean existUserByDeptId(int depId);

}
