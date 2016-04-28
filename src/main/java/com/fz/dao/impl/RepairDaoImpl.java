package com.fz.dao.impl;

import com.fz.dao.RepairDao;
import com.fz.model.PageBean;
import com.fz.model.Repair;
import com.fz.util.DateUtil;
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
 * Created by Administrator on 2016/4/28.
 */
@Repository
public class RepairDaoImpl implements RepairDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void add(Repair repair) {
        String sql = "insert into t_repair values(null,?,?,null,now(),null,0,1)";
        jdbcTemplate.update(sql,new Object[]{repair.getEquipmentId(),repair.getUserMan()});
    }

    @Override
    public List<Repair> find(PageBean pageBean, Repair s_repair) {
        StringBuffer sb=new StringBuffer("select * from t_repair t1,t_equipment t2 where t1.equipmentId=t2.id");
        if(s_repair!=null){
            if(StringUtil.isNotEmpty(s_repair.getEquipmentName())){
                sb.append(" and t2.name like '%"+s_repair.getEquipmentName()+"%'");
            }
            if(s_repair.getFinishState()!=null){
                sb.append(" and t1.finishState="+s_repair.getFinishState());
            }
        }
        if(pageBean!=null){
            sb.append(" limit "+pageBean.getStart()+","+pageBean.getPageSize());
        }
        final List<Repair> repairList=new ArrayList<Repair>();
        jdbcTemplate.query(sb.toString(), new Object[]{}, new RowCallbackHandler() {

            @Override
            public void processRow(ResultSet rs) throws SQLException {
                Repair repair=new Repair();
                repair.setId(rs.getInt("id"));
                repair.setEquipmentId(rs.getInt("equipmentId"));
                repair.setEquipmentName(rs.getString("name"));
                repair.setEquipmentNo(rs.getString("no"));
                repair.setUserMan(rs.getString("userMan"));
                repair.setRepairMan(rs.getString("repairMan"));
                repair.setRepairTime(DateUtil.formatString(rs.getString("repairTime"), "yyyy-MM-dd HH:mm:ss"));
                repair.setFinishTime(DateUtil.formatString(rs.getString("finishTime"), "yyyy-MM-dd HH:mm:ss"));
                repair.setState(rs.getInt("state"));
                repairList.add(repair);
            }
        });
        return repairList;
    }

    @Override
    public int total(Repair repair) {
        StringBuffer sb = new StringBuffer("select count(*) from t_repair");
        if(repair!=null){
            if(repair.getUserMan()!=null){
                sb.append(" where userman like '%"+repair.getUserMan()+"%'");
            }
        }
        return jdbcTemplate.queryForObject(sb.toString(),Integer.class);
    }
}
