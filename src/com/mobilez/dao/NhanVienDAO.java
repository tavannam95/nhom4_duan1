/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mobilez.dao;

import com.mobilez.models.NhanVien;
import com.mobilez.utils.JdbcHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author uhtku
 */
public class NhanVienDAO extends MainDAO<NhanVien, String> {

    String insertSQL = "INSERT INTO NHANVIEN\n"
            + "VALUES (?,?,?,?,?,?,?,?,?)";
    String updateSQL = "UPDATE NHANVIEN SET HOTEN = ?,\n"
            + "GIOITINH = ?, NGAYSINH = ?, SOCCCD = ?,\n"
            + "DIACHI = ?, SODT = ?, VAITRO = ?, \n"
            + "HINHNV  = ? WHERE MANV = ?";
    String deleteSQL = "delete NHANVIEN where MANV like ?";
    String selectAllSQL = "select * from NHANVIEN";
    String selectByIdSQL = "select * from NHANVIEN where MANV like ?";

    @Override
    public void insert(NhanVien entity) {
        int s = JdbcHelper.update(insertSQL, entity.getMaNV(),entity.getHoTen(),entity.isGioiTinh(),
                entity.getSoCCCD(),entity.getDiaChi(),entity.getSoDienThoai(),
                entity.isVaiTro(),entity.getHinhNV());
        if (s <= 0) {
            JOptionPane.showMessageDialog(null, "Thêm thất bại!");
            return;
        }
    }

    @Override
    public void update(NhanVien entity) {
        int s = JdbcHelper.update(updateSQL, entity);
        if (s <= 0) {
            JOptionPane.showMessageDialog(null, "Cập nhật thất bại!");
            return;
        }
    }

    @Override
    public void delete(String id) {
        int s = JdbcHelper.update(deleteSQL, id);
        if (s <= 0) {
            JOptionPane.showMessageDialog(null, "Xó thất bại!");
            return;
        }
    }

    @Override
    public NhanVien selectById(String id) {
        List<NhanVien> lstNV = this.selectBySql(selectByIdSQL, id);
        if (lstNV.isEmpty()) {
            return null;
        } else {
            return lstNV.get(0);
        }

    }

    @Override
    public List<NhanVien> selectAll() {
        return this.selectBySql(selectAllSQL);
    }

    @Override
    protected List<NhanVien> selectBySql(String sql, Object... args) {
        List<NhanVien> lstNV = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.query(sql, args);
            while (rs.next()) {
                NhanVien cd = new NhanVien(
                        rs.getString(1), rs.getString(2), rs.getBoolean(3), rs.getDate(4),
                        rs.getString(5), rs.getString(6), rs.getString(7), rs.getBoolean(8), rs.getString(9)
                );
                lstNV.add(cd);
            }
            rs.close();
            return lstNV;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
