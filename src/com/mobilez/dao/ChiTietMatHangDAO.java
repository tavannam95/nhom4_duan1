/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mobilez.dao;

import com.mobilez.models.ChiTietMatHang;
import com.mobilez.utils.JdbcHelper;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

/**
 *
 * @author Admin
 */
public class ChiTietMatHangDAO extends MainDAO<ChiTietMatHang, String>{
    
    String INSERT_SQL = "INSERT INTO CHITIETMATHANG (RAM,DUNGLUONG,MAUSAC,NGAYCAPNHAT,MAQG,MAMH,GIAMUA,GIABAN ) VALUES(?,?,?,?,?,?,?,?)";
    String UPDATE_SQL = "UPDATE CHITIETMATHANG SET DUNGLUONG=?, MAUSAC=?, NGAYCAPNHAT=?, MAQG=?, MAMH=?, GIAMUA=?, GIABAN=? WHERE RAM=?";
    String DELETE_SQL = "DELETE FROM CHITIETMATHANG WHERE RAM=?";
    String SELECT_ALL_SQL = "SELECT*FROM CHITIETMATHANG";
    String SELECT_BY_ID_SQL = "SELECT*FROM CHITIETMATHANG WHERE RAM=?";
    
    @Override
    public void insert(ChiTietMatHang entity) {
        JdbcHelper.update(INSERT_SQL, 
                entity.getRam(),entity.getDungluong(),entity.getMausac(),entity.getNgaycapnhat(),entity.getMaQG(),entity.getMaMH(),entity.getGiamua(),entity.getGiaban());
    }

    @Override
    public void update(ChiTietMatHang entity) {
        JdbcHelper.update(UPDATE_SQL, 
                entity.getDungluong(),entity.getMausac(),entity.getNgaycapnhat(),entity.getMaQG(),entity.getMaMH(),entity.getGiamua(),entity.getGiaban(),entity.getRam());
    }

    @Override
    public void delete(String id) {
        JdbcHelper.update(DELETE_SQL, id);
    }

    @Override
    public ChiTietMatHang selectById(String id) {
        List<ChiTietMatHang> list = this.selectBySql(SELECT_BY_ID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<ChiTietMatHang> selectAll() {
        return this.selectBySql(SELECT_ALL_SQL);
    }

    @Override
    protected List<ChiTietMatHang> selectBySql(String sql, Object... args) {
        List<ChiTietMatHang> list = new ArrayList<ChiTietMatHang>();
        try {
            ResultSet rs = JdbcHelper.query(sql, args);
            while (rs.next()) {
                ChiTietMatHang ct = new ChiTietMatHang();
                ct.setRam(rs.getString("RAM"));
                ct.setDungluong(rs.getString("DUNGLUONG"));
                ct.setMausac(rs.getString("MAUSAC"));
                ct.setNgaycapnhat(rs.getDate("NGAYCAPNHAT"));
                ct.setMaQG(rs.getString("MAQG"));
                ct.setMaMH(rs.getString("MAMH"));
                ct.setGiamua(rs.getInt("GIAMUA"));
                ct.setGiaban(rs.getInt("GIABAN"));
                list.add(ct);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
     public ChiTietMatHang findById(String ram) {
            String sql = "SELECT * FROM CHITIETMATHANG WHERE RAM=?";
            List<ChiTietMatHang> list = selectBySql(sql, ram);
            return list.size() > 0 ? list.get(0) : null;
        }
    
}
