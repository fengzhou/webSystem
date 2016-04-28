package com.fz.dao.impl;

import com.fz.dao.EquipmentDao;
import com.fz.model.Equipment;
import com.fz.model.PageBean;
import com.fz.model.Repair;
import com.fz.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/4/27.
 */
@Repository
public class EquipmentDaoImpl implements EquipmentDao{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Equipment> find(PageBean pageBean, Equipment s_equipment) {
        StringBuffer sb = new StringBuffer("SELECT * FROM t_equipment ,t_equipmenttype  WHERE t_equipment.`typeId` = t_equipmenttype.`id`");
        if(s_equipment!=null){
            if(StringUtil.isNotEmpty(s_equipment.getName())){
                sb.append(" and name like '%"+s_equipment.getName()+"%'");
            }
        }
        if(pageBean!=null){
            sb.append(" limit "+pageBean.getStart()+","+pageBean.getPageSize());
        }
        final List<Equipment> equipmentList = new ArrayList<>();
        jdbcTemplate.query(sb.toString(), new Object[]{}, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                Equipment equipment=new Equipment();
                equipment.setId(rs.getInt("id"));
                equipment.setName(rs.getString("name"));
                equipment.setNo(rs.getString("no"));
                equipment.setTypeId(rs.getInt("typeId"));
                equipment.setTypeName(rs.getString("typeName"));
                equipment.setState(rs.getInt("state"));
                equipment.setRemark(rs.getString("remark"));
                equipmentList.add(equipment);
            }
        });
        return equipmentList;
    }

    @Override
    public int total(Equipment equipment) {
        String sql = "select count(*) from t_equipment";
        return jdbcTemplate.queryForObject(sql,Integer.class);
    }

    @Override
    public void delete(int id) {
        String sql = "delete from t_equipment where id = ?";
        jdbcTemplate.update(sql,new Object[]{id});
    }

    @Override
    public Equipment loadById(int id) {
        String sql = "SELECT * FROM t_equipment ,t_equipmenttype  WHERE t_equipment.`typeId` = t_equipmenttype.`id`  AND t_equipment.`id` = ?";
        final Equipment equipment = new Equipment();
        jdbcTemplate.query(sql, new Object[]{id}, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                equipment.setId(rs.getInt("id"));
                equipment.setName(rs.getString("name"));
                equipment.setNo(rs.getString("no"));
                equipment.setTypeId(rs.getInt("typeId"));
                equipment.setTypeName(rs.getString("typeName"));
                equipment.setState(rs.getInt("state"));
                equipment.setRemark(rs.getString("remark"));
            }
        });
        return equipment;
    }

    @Override
    public void add(Equipment equipment) {
        String sql = "insert into t_equipment values(null,?,?,?,?,?)";
        jdbcTemplate.update(sql,new Object[]{equipment.getName(),equipment.getNo(),equipment.getTypeId(),equipment.getState(),equipment.getRemark()});
    }

    @Override
    public void update(Equipment equipment) {
        String sql = "update t_equipment set name=?,no=?,typeId=?,state=?,remark=? where id = ?";
        jdbcTemplate.update(sql,new Object[]{equipment.getName(),equipment.getNo(),equipment.getTypeId(),equipment.getState(),equipment.getRemark(),equipment.getId()});

    }
}
