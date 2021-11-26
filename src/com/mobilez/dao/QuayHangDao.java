/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mobilez.dao;

import com.mobilez.models.QuayHang;
import com.mobilez.utils.JdbcHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author duong
 */
public class QuayHangDao extends MainDAO<QuayHang, String> {
    
    String selectByIdSQL = "select * from QUAYHANG where MAQH = ?";
    
    @Override
    public void insert(QuayHang entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(QuayHang entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public QuayHang selectById(String id) {
        List<QuayHang> lstQH = this.selectBySql(selectByIdSQL, id);
        if (lstQH.isEmpty()) {
            return null;
        } else {
            return lstQH.get(0);
        }
    }

    @Override
    public List<QuayHang> selectAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected List<QuayHang> selectBySql(String sql, Object... args) {
        List<QuayHang> lstQH = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.query(sql, args);
            while (rs.next()) {
                QuayHang qh = new QuayHang();
                qh.setMaQH(rs.getString("MAQH"));
                qh.setTenQH(rs.getString("TENQH"));
                lstQH.add(qh);
            }
            rs.close();
            return lstQH;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
}
