package com.fz.dao.impl;

import com.fz.dao.EquipmentTypeDao;
import com.fz.model.Equipment;
import com.fz.model.EquipmentType;
import com.fz.model.PageBean;
import com.fz.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/4/27.
 */
@Repository
public class EquipmentTypeDaoImpl implements EquipmentTypeDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<EquipmentType> find(PageBean pageBean, EquipmentType equipmentType) {

        StringBuffer sb = new StringBuffer("select * from t_equipmentType");
        if(equipmentType!=null){
            if(StringUtil.isNotEmpty(equipmentType.getTypeName())){
                sb.append(" and typeName like '%"+equipmentType.getTypeName()+"%'");
            }
        }
        if(pageBean!=null){
            sb.append(" limit "+pageBean.getStart()+","+pageBean.getPageSize());
        }
        final List<EquipmentType> equipmentTypeList = new ArrayList<>();
        jdbcTemplate.query(sb.toString().replace("and","where"), new Object[]{}, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                EquipmentType equipmentType=new EquipmentType();
                equipmentType.setId(rs.getInt("id"));
                equipmentType.setTypeName(rs.getString("typeName"));
                equipmentType.setRemark(rs.getString("remark"));
                equipmentTypeList.add(equipmentType);
            }
        });
        return equipmentTypeList;
    }

    @Override
    public int total(EquipmentType equipmentType) {
        String sql = "select count(*) from t_equipmentType";
        return jdbcTemplate.queryForObject(sql,Integer.class);
    }

    @Override
    public EquipmentType loadById(int id) {
        String sql = "select * from t_equipmentType where id = ?";
        final EquipmentType equipmentType = new EquipmentType();
        jdbcTemplate.query(sql, new Object[]{id}, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                equipmentType.setId(rs.getInt("id"));
                equipmentType.setTypeName(rs.getString("typeName"));
                equipmentType.setRemark(rs.getString("remark"));
            }
        });
        return equipmentType;
    }

    @Override
    public void update(EquipmentType equipmentType) {
        String sql = "update t_equipmentType set typeName = ?,remark = ? where id = ?";
        jdbcTemplate.update(sql,new Object[]{equipmentType.getTypeName(),
                                equipmentType.getRemark(),equipmentType.getId()});
    }

    @Override
    public void add(EquipmentType equipmentType) {
        String sql = "insert into t_equipmentType values(null,?,?)";
        jdbcTemplate.update(sql,new Object[]{equipmentType.getTypeName(),equipmentType.getRemark()});
    }

    @Override
    public void delete(int id ) {
        String sql = "delete from t_equipmentType where id = ?";
        jdbcTemplate.update(sql,new Object[]{id});
    }

    @Override
    public boolean existEquipmentByTypeId(int id) {
        String sql = "SELECT count(*) FROM t_equipment ,t_equipmenttype  WHERE t_equipment.`typeId` = t_equipmenttype.`id`  AND t_equipmenttype.`id` = ?";
        if(jdbcTemplate.queryForObject(sql,new Object[]{id},Integer.class)>0){
            return true;
        }else {
            return false;
        }
    }
}
