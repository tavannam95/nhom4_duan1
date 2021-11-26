/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mobilez.dao;

import com.mobilez.models.CTPhieuGiaoCa;
import com.mobilez.models.PhieuGiaoCa;
import com.mobilez.utils.JdbcHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author duong
 */
public class CTPhieuGiaoCaDao extends MainDAO<CTPhieuGiaoCa, String> {
    
    String insertSQL = "INSERT INTO CHITIETPHIEUGIAOCA (MAPGC, MAMH, SOLUONG) VALUES (?,?,?)";
    String updateSQL = "UPDATE CHITIETPHIEUGIAOCA SET MAMH = ?, SOLUONG = ? WHERE MAPGC = ? AND MAMH = ?";
    String selectAllSQL = "SELECT * FROM CHITIETPHIEUGIAOCA";
    String selectByIdSQL = "SELECT * FROM CHITIETPHIEUGIAOCA WHERE MAPGC = ? AND MAMH = ?";
    String deleteSQL = "DELETE CHITIETPHIEUGIAOCA WHERE MAPGC = ? AND MAMH = ?";
    
    @Override
    public void insert(CTPhieuGiaoCa entity) {
        JdbcHelper.update(insertSQL, entity.getMaPGC(), entity.getMaMH(), entity.getSoLuong());
    }

    @Override
    public void update(CTPhieuGiaoCa entity) {
        JdbcHelper.update(updateSQL, entity.getMaMH(), entity.getSoLuong(), entity.getMaPGC(), entity.getMaMH());
    }

    @Override
    public void delete(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void delete(Integer maPGC, String maMH) {
        JdbcHelper.update(deleteSQL, maPGC, maMH);
    }
    
    public List<CTPhieuGiaoCa> selectByMAPGC(Integer ma) {
        String sql = "SELECT * FROM CHITIETPHIEUGIAOCA WHERE MAPGC = ?";
        return selectBySql(sql, ma);
    }
    
    @Override
    public CTPhieuGiaoCa selectById(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<CTPhieuGiaoCa> selectAll() {
        return this.selectBySql(selectAllSQL);
    }

    @Override
    protected List<CTPhieuGiaoCa> selectBySql(String sql, Object... args) {
        List<CTPhieuGiaoCa> lstCTPGC = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.query(sql, args);
            while (rs.next()) {
                CTPhieuGiaoCa entity = new CTPhieuGiaoCa();
                entity.setMaPGC(rs.getInt("MAPGC"));
                entity.setMaMH(rs.getString("MAMH"));
                entity.setSoLuong(rs.getInt("SOLUONG"));
                lstCTPGC.add(entity);
            }
            rs.close();
            return lstCTPGC;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public CTPhieuGiaoCa selectById(Integer id, String mamh) {
        List<CTPhieuGiaoCa> lstCTPGC = this.selectBySql(selectByIdSQL, id, mamh);
        if (lstCTPGC.isEmpty()) {
            return null;
        } else {
            return lstCTPGC.get(0);
        }
    }
    
}
