/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mobilez.dao;

import com.mobilez.models.KhachHang;
import com.mobilez.utils.JdbcHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author LAPTOP
 */
public class KhachHangDAO extends MainDAO<KhachHang, String>{
    String INSERT_SQL = "INSERT INTO KHACHHANG(MAKH,TENKH,DIACHI,SODT) VALUES (?,?,?,?)";
    String UPDATE_SQL = "UPDATE KHACHHANG SET TENKH = ?, DIACHI = ? , SODT = ? WHERE MAKH = ?";
    String DELETE_SQL = "DELETE KHACHHANG WHERE MAKH = ?";
    String SELECT_ALL_SQL = "SELECT * FROM KHACHHANG";
    String SELECT_BY_ID_SQl = "SELECT * FROM KHACHHANG WHERE MAKH = ?";

    @Override
    public void insert(KhachHang entity) {
            JdbcHelper.update(INSERT_SQL, entity.getMaKH(), entity.getTenKH(), entity.getDiaChi(), entity.getSoDienThoai()); 
    }

    @Override
    public void update(KhachHang entity) {
        JdbcHelper.update(UPDATE_SQL, entity.getTenKH(), entity.getDiaChi(), entity.getSoDienThoai(), entity.getMaKH());
    }

    @Override
    public void delete(String id) {
        JdbcHelper.update(DELETE_SQL, id);
    }

    @Override
    public List<KhachHang> selectAll() {
        return this.selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public KhachHang selectById(String id) {
        List<KhachHang> list = this.selectBySql(SELECT_BY_ID_SQl, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    protected List<KhachHang> selectBySql(String sql, Object... args) {
        List<KhachHang> list = new ArrayList<KhachHang>();
        try {
            ResultSet rs = JdbcHelper.query(sql, args);
            while (rs.next()) {
                KhachHang entity = new KhachHang();
                entity.setMaKH(rs.getString("MAKH"));
                entity.setTenKH(rs.getString("TENKH"));
                entity.setDiaChi(rs.getString("DIACHI"));
                entity.setSoDienThoai(rs.getString("SODT"));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
