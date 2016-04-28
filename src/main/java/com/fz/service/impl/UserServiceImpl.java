package com.fz.service.impl;

import com.fz.dao.UserDao;
import com.fz.model.PageBean;
import com.fz.model.User;
import com.fz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2016/4/26.
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User login(User user) {
        return userDao.login(user);
    }

    @Override
    public List<User> find(PageBean pageBean, User user) {
        return userDao.find(pageBean,user);
    }

    @Override
    public int count(User user) {
        return userDao.count(user);
    }

    @Override
    public void delete(int id) {
        userDao.delete(id);
    }

    @Override
    public User loadById(int id) {
        return userDao.loadById(id);
    }

    @Override
    public void add(User user) {
        userDao.add(user);
    }

    @Override
    public void update(User user) {
        userDao.update(user);
    }

    @Override
    public boolean existUserByDeptId(int depId) {
        return userDao.existUserByDeptId(depId);
    }
}
