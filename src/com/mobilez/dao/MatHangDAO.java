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
  /*Kế thừa lớp MainDAO:
    Các phương thức:
    insert: thêm một dòng dữ liệu vào bảng MATHANG
    delete: xóa một dòng dữ liệu trong bảng MATHANG theo id tương ứng
    update: cập nhật một dòng MATHANG
    selectById: truy vấn một đối tượng theo ID tương ứng trong bảng MATHANG trả về MatHang
    selectAll: truy vấn tất cả đối tượng trong bảng MATHANG trả về list<MatHang>
    selectBySql: truy vấn đối tượng theo ID hỗ trợ phương thức selectById trả về list<MatHang>*/
//    Loại mặt hàng: Mã loại, tên loại
    
public class MatHangDAO extends MainDAO<MatHang, String>{

    String INSERT_SQL = "INSERT INTO MATHANG (MAMH ,MAQH ,MAHSX ,TENMH ,HINHMH ,DONVITINH ,SOLUONG ,TGBH ) VALUES(?,?,?,?,?,?,?,?)";
    String UPDATE_SQL = "UPDATE MATHANG SET MAMH=?, MAQH=?, MAHSX=?, TENMH=?, HINHMH=?, DONVITINH=?, SOLUONG=?, TGBH=? WHERE MaMH=?";
    String DELETE_SQL = "DELETE FROM ChuyenDe WHERE MAMH=?";
    String SELECT_ALL_SQL = "SELECT*FROM MATHANG";
    String SELECT_BY_ID_SQL = "SELECT*FROM MATHANG WHERE MAMH=?";
    
    @Override
    public void insert(MatHang entity) {
        JdbcHelper.update(INSERT_SQL, 
                entity.getMaMH(),entity.getMaQH(),entity.getMaHSX(),entity.getTenMH(),entity.getHinhMH(),entity.getDonViTinh(),entity.getSoluong(),entity.getThoiGIanBaoHanh());
    }

    @Override
    public void update(MatHang entity) {
        JdbcHelper.update(INSERT_SQL, 
                entity.getMaMH(),entity.getMaQH(),entity.getMaHSX(),entity.getTenMH(),entity.getHinhMH(),entity.getDonViTinh(),entity.getSoluong(),entity.getThoiGIanBaoHanh());
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
                mh.setThoiGIanBaoHanh(rs.getString("thoiGIanBaoHanh"));
                list.add(mh);
                
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
  
}
