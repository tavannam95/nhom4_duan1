/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mobilez.dao;

import com.mobilez.models.HangSanXuat;
import com.mobilez.utils.JdbcHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author duong
 */
public class HangSanXuatDao extends MainDAO<HangSanXuat, String> {

    String insertSQL = "INSERT INTO HANGSANXUAT (MAHSX, TENHSX) VALUES (?,?)";
    String updateSQL = "UPDATE HANGSANXUAT SET TENHSX = ? WHERE MAHSX = ?";
    String deleteSQL = "DELETE HANGSANXUAT WHERE MAHSX = ?";
    String selectAllSQL = "SELECT * FROM HANGSANXUAT";
    String selectByIdSQL = "SELECT * FROM HANGSANXUAT WHERE MAHSX = ?";

    @Override
    public void insert(HangSanXuat entity) {
        JdbcHelper.update(insertSQL, entity.getMaHSX(), entity.getTenHSX()); 
    }

    @Override
    public void update(HangSanXuat entity) {
        JdbcHelper.update(updateSQL, entity.getTenHSX(), entity.getMaHSX());
    }

    @Override
    public void delete(String maHSX) {
        JdbcHelper.update(deleteSQL, maHSX);
    }

    @Override
    public HangSanXuat selectById(String maHSX) {
        List<HangSanXuat> lstHSX = this.selectBySql(selectByIdSQL, maHSX);
        if (lstHSX.isEmpty()) {
            return null;
        } else {
            return lstHSX.get(0);
        }

    }

    @Override
    public List<HangSanXuat> selectAll() {
        return this.selectBySql(selectAllSQL);
    }

    @Override
    protected List<HangSanXuat> selectBySql(String sql, Object... args) {
        List<HangSanXuat> lstHSX = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.query(sql, args);
            while (rs.next()) {
                HangSanXuat entity = new HangSanXuat();
                entity.setMaHSX(rs.getString("MAHSX"));
                entity.setTenHSX(rs.getString("TENHSX"));
                lstHSX.add(entity);
            }
            rs.close();
            return lstHSX;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public List<HangSanXuat> selectByMAHSX(String keyword) {
        String sql = "SELECT * FROM HANGSANXUAT WHERE MAHSX LIKE ?";
        return selectBySql(sql, "%" + keyword + "%");
    }
}
