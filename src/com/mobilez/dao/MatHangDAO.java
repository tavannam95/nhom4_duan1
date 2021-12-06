/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mobilez.dao;

import com.mobilez.models.MatHang;
import com.mobilez.models.NhanVien;
import java.sql.*;
import com.mobilez.utils.JdbcHelper;
import com.mobilez.utils.Msgbox;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author uhtku
 */
public class MatHangDAO extends MainDAO<MatHang, String> {

    String insertSQL = "insert into MATHANG values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
    String updateSQL = "update MATHANG set MAHSX = ?,TENMH = ?,RAM = ?,\n"
            + "DUNGLUONG = ?,MAUSAC = ?,MAQG = ?,HINHMH = ?,\n"
            + "SOLUONG = ?,TGBH = ?,GIAMUA = ?,\n"
            + "GIABAN = ?,TRANGTHAI = ? where MAMH like ?";
    String deleteSQL = "delete MATHANG where MAMH like ?";
    String selectAllSQL = "select * from MATHANG";
    String selectByIdSQL = "select * from MATHANG where MAMH like ?";
    String insertKhoHang = "insert into KHOHANG values(?,?,?)";
    String selectAllKho = "select * from kho";
    String selectAllQH = "select * from QuayHang";
    String insertCTQuayHang ="insert into CHITIETQUAYHANG values (?,?,?)";
    
    @Override
    public void insert(MatHang entity) {
        int s = JdbcHelper.update(insertSQL, entity.getMaMH(),entity.getMaHSX(),entity.getTenMH(),
                entity.getrAM(), entity.getDungLuong(),entity.getMauSac(),entity.getMaQG(),
                entity.getHinhMH(),entity.getSoLuong(),entity.gettGBH(),entity.getGiaMua(),
                entity.getGiaBanLe(),entity.isTrangThai());
        try {
            ResultSet rs = JdbcHelper.query(selectAllKho);
            while (rs.next()) {                
                JdbcHelper.update(insertKhoHang, entity.getMaMH(),rs.getString("MAK"),0);
                
            }
            ResultSet rs2 = JdbcHelper.query(selectAllQH);
            while (rs2.next()) {                
                JdbcHelper.update(insertCTQuayHang,rs2.getString("MaQH"), entity.getMaMH(),0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (s <= 0) {
            Msgbox.alert(null, "Thêm thất bại!");
            return;
        }
        
    }

    @Override
    public void update(MatHang entity) {
        int s = JdbcHelper.update(updateSQL, entity.getMaHSX(),entity.getTenMH(),
                entity.getrAM(), entity.getDungLuong(),entity.getMauSac(),entity.getMaQG(),
                entity.getHinhMH(),entity.getSoLuong(),entity.gettGBH(),entity.getGiaMua(),
                entity.getGiaBanLe(),entity.isTrangThai(), entity.getMaMH());
        if (s <= 0) {
            Msgbox.alert(null, "Cập nhật thất bại!");
            return;
        }
    }

    @Override
    public void delete(String id) {
        int s = JdbcHelper.update(deleteSQL, id);
        if (s <= 0) {
            Msgbox.alert(null, "Xóa thất bại!");
            return;
        }
    }

    @Override
    public MatHang selectById(String id) {
        List<MatHang> nv = this.selectBySql(selectByIdSQL, id);
        if (nv.isEmpty()) {
            return null;
        } else {
            return nv.get(0);
        }
    }

    @Override
    public List<MatHang> selectAll() {
        return this.selectBySql(selectAllSQL);
    }

    @Override
    protected List<MatHang> selectBySql(String sql, Object... args) {
        List<MatHang> lstMH = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.query(sql, args);
            while (rs.next()) {
                MatHang mh = new MatHang(rs.getString(1), rs.getString(2), rs.getString(3),
                        rs.getInt(4), rs.getInt(5), rs.getString(6), rs.getString(7),
                        rs.getString(8), rs.getInt(9), rs.getInt(10), rs.getDouble(11),
                        rs.getDouble(12),rs.getBoolean(13));
                lstMH.add(mh);
            }
            rs.close();
            return lstMH;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public  boolean checkDuplicate(String maMH) {
        MatHang matHang = selectById(maMH);
        if (matHang == null) {
            return true;
        }
        return false;
    }
}
