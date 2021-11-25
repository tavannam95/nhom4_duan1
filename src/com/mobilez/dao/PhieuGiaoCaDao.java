/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mobilez.dao;

import com.mobilez.models.HangSanXuat;
import com.mobilez.models.PhieuGiaoCa;
import com.mobilez.utils.JdbcHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author duong
 */
public class PhieuGiaoCaDao extends MainDAO<PhieuGiaoCa, String> {
    
    String insertSQL = "INSERT INTO PHIEUGIAOCA (NGAYGC, MANV, MAQH, CA) VALUES (?,?,?,?)";
    String updateSQL = "UPDATE PHIEUGIAOCA SET NGAYGC = ?, MANV = ?, MAQH = ?, CA = ? WHERE MAPGC = ?";
    String deleteSQL = "DELETE PHIEUGIAOCA WHERE MAPGC = ?";
    String selectAllSQL = "SELECT * FROM PHIEUGIAOCA";
    String selectByIdSQL = "SELECT * FROM PHIEUGIAOCA WHERE MAPGC = ?";
    
    @Override
    public void insert(PhieuGiaoCa entity) {
        JdbcHelper.update(insertSQL, entity.getNgayGiaoCa(), entity.getMaNV(), entity.getMaQH(), entity.getCa()); 
    }

    @Override
    public void update(PhieuGiaoCa entity) {
        JdbcHelper.update(updateSQL, entity.getNgayGiaoCa(), entity.getMaNV(), entity.getMaQH(), entity.getCa(), entity.getMaPGC());
    }

    @Override
    public void delete(String id) {
        JdbcHelper.update(deleteSQL, id);
    }

    @Override
    public PhieuGiaoCa selectById(String id) {
        List<PhieuGiaoCa> lstPGC = this.selectBySql(selectByIdSQL, id);
        if (lstPGC.isEmpty()) {
            return null;
        } else {
            return lstPGC.get(0);
        }
    }
    
    public PhieuGiaoCa selectById(Integer id) {
        List<PhieuGiaoCa> lstPGC = this.selectBySql(selectByIdSQL, id);
        if (lstPGC.isEmpty()) {
            return null;
        } else {
            return lstPGC.get(0);
        }
    }

    @Override
    public List<PhieuGiaoCa> selectAll() {
        return this.selectBySql(selectAllSQL);
    }

    @Override
    protected List<PhieuGiaoCa> selectBySql(String sql, Object... args) {
        List<PhieuGiaoCa> lstPGC = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.query(sql, args);
            while (rs.next()) {
                PhieuGiaoCa entity = new PhieuGiaoCa();
                entity.setMaPGC(rs.getInt("MAPGC"));
                entity.setNgayGiaoCa(rs.getDate("NGAYGC"));
                entity.setMaNV(rs.getString("MANV"));
                entity.setMaQH(rs.getString("MAQH"));
                entity.setCa(rs.getInt("CA"));
                lstPGC.add(entity);
            }
            rs.close();
            return lstPGC;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
}
