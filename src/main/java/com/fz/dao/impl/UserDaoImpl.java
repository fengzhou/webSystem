package com.fz.dao.impl;

import com.fz.dao.UserDao;
import com.fz.model.PageBean;
import com.fz.model.User;
import com.fz.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/4/26.
 */
@Repository("userDao")
public class UserDaoImpl implements UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public User login(User user) {
        String sql = "select * from t_user where userName = ? and password = ?";
        final User resultUser = new User();
        jdbcTemplate.query(sql, new Object[]{user.getUserName(), user.getPassword()}, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                resultUser.setId(rs.getInt("id"));
                resultUser.setUserName(rs.getString("userName"));
                resultUser.setPassword(rs.getString("password"));
                resultUser.setRoleName(rs.getString("roleName"));
            }
        });
        return resultUser;
    }

    @Override
    public List<User> find(PageBean pageBean, User s_user) {
        StringBuffer sb = new StringBuffer("select * from t_user t1,t_department t2 where t1.deptId = t2.id");
        if(s_user != null){
            if(StringUtil.isNotEmpty(s_user.getUserName())){
                sb.append("and t1.userName like % "+s_user.getDeptName()+"%'");
            }
        }if(pageBean!=null){
            sb.append(" limit "+pageBean.getStart()+" , "+pageBean.getPageSize());
        }
        final List<User> userList = new ArrayList<>();
        jdbcTemplate.query(sb.toString(), new Object[]{}, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                User user=new User();
                user.setId(rs.getInt("id"));
                user.setUserName(rs.getString("userName"));
                user.setPassword(rs.getString("password"));
                user.setTrueName(rs.getString("trueName"));
                user.setRoleName(rs.getString("roleName"));
                user.setDeptId(rs.getInt("deptId"));
                user.setDeptName(rs.getString("deptName"));
                userList.add(user);
            }
        });
        return userList;
    }

    @Override
    public int count(User u_user) {
        StringBuffer sb = new StringBuffer("select count(*) from t_user t1,t_department t2 where t1.deptId = t2.id");
        if(u_user!=null){
            if(StringUtil.isNotEmpty(u_user.getUserName())) {
                sb.append(" and userName like %" + u_user.getDeptName() + "%'");
            }
        }
        return jdbcTemplate.queryForObject(sb.toString(),Integer.class);
    }

    @Override
    public void delete(int id) {
        String sql = "delete from t_user where id = ?";
        jdbcTemplate.update(sql,new Object[]{id});
    }

    @Override
    public User loadById(int id) {
        String sql = "select * from t_user t1,t_department t2 where t1.deptId=t2.id and t1.id=?";
        final User user = new User();
        jdbcTemplate.query(sql, new Object[]{id}, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                user.setId(rs.getInt("id"));
                user.setUserName(rs.getString("userName"));
                user.setPassword(rs.getString("password"));
                user.setTrueName(rs.getString("trueName"));
                user.setRoleName(rs.getString("roleName"));
                user.setDeptId(rs.getInt("deptId"));
                user.setDeptName(rs.getString("deptName"));
            }
        });
        return user;
    }

    @Override
    public void add(User user) {
        String sql = "insert into t_user values(null,?,?,?,?,?)";
        jdbcTemplate.update(sql,new Object[]{user.getUserName(),
                    user.getPassword(),user.getTrueName(),user.getRoleName(),user.getDeptId()});
    }

    @Override
    public void update(User user) {
        String sql="update t_user set userName=?,password=?,trueName=?,roleName=?,deptId=? where id=?";
        jdbcTemplate.update(sql, new Object[]{user.getUserName(),user.getPassword(),user.getTrueName(),user.getRoleName(),user.getDeptId(),user.getId()});
    }

    @Override
    public boolean existUserByDeptId(int depId) {
        String sql = "select count(*) from t_user where deptId = ?";
        int result = jdbcTemplate.queryForObject(sql,new Object[]{depId},Integer.class);
        if(result>0){
            return true;
        }else {
            return false;
        }
    }
}
