/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mobilez.dao;

import com.mobilez.models.ChiTietMatHang;
import com.mobilez.models.MatHang;
import com.mobilez.utils.JdbcHelper;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;



/**
 *
 * @author Admin
 */

    
public class MatHangDAO extends MainDAO<MatHang, String>{

    String INSERT_SQL = "INSERT INTO MATHANG (MAMH ,MAQH ,MAHSX ,TENMH ,HINHMH ,DONVITINH ,SOLUONG ,TGBH ) VALUES(?,?,?,?,?,?,?,?)";
    String UPDATE_SQL = "UPDATE MATHANG SET MAQH=?, MAHSX=?, TENMH=?, HINHMH=?, DONVITINH=?, SOLUONG=?, TGBH=? WHERE MaMH=?";
    String DELETE_SQL = "DELETE FROM MATHANG WHERE MAMH=?";
    String SELECT_ALL_SQL = "SELECT*FROM MATHANG";
    String SELECT_BY_ID_SQL = "SELECT*FROM MATHANG WHERE MAMH=?";
    
    @Override
    public void insert(MatHang entity) {
        JdbcHelper.update(INSERT_SQL, 
                entity.getMaMH(),entity.getMaQH(),entity.getMaHSX(),entity.getTenMH(),entity.getHinhMH(),entity.getDonViTinh(),entity.getSoluong(),entity.getThoiGIanBaoHanh());
    }

    @Override
    public void update(MatHang entity) {
        JdbcHelper.update(UPDATE_SQL, 
                entity.getMaQH(),entity.getMaHSX(),entity.getTenMH(),entity.getHinhMH(),entity.getDonViTinh(),entity.getSoluong(),entity.getThoiGIanBaoHanh(),entity.getMaMH());
    }

    @Override
    public void delete(String id) {
        JdbcHelper.update(DELETE_SQL, id);
    }

    @Override
    public MatHang selectById(String id) {
        List<MatHang> list = this.selectBySql(SELECT_BY_ID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<MatHang> selectAll() {
        return this.selectBySql(SELECT_ALL_SQL);
    }
//maMH,maQH,maHSX,tenMH,hinhMH,donViTinh;
    @Override
    protected List<MatHang> selectBySql(String sql, Object... args) {
        List<MatHang> list =  new ArrayList<MatHang>();
        try {
            ResultSet rs = JdbcHelper.query(sql, args);
            while (rs.next()) {
                MatHang mh = new MatHang();
                mh.setMaMH(rs.getString("maMH"));
                mh.setMaQH(rs.getString("maQH"));
                mh.setMaHSX(rs.getString("maHSX"));
                mh.setTenMH(rs.getString("tenMH"));
                mh.setHinhMH(rs.getString("hinhMH"));
                mh.setDonViTinh(rs.getString("donViTinh"));
                mh.setSoluong(rs.getInt("soluong"));
                mh.setThoiGIanBaoHanh(rs.getString("TGBH"));
                list.add(mh);
                
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
   public MatHang findById(String mamh) {
            String sql = "SELECT * FROM MATHANG WHERE MAMH=?";
            List<MatHang> list = selectBySql(sql, mamh);
            return list.size() > 0 ? list.get(0) : null;
        }
}
