/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mobilez.dao;

import com.mobilez.models.CapNhatGia;
import com.mobilez.models.NgayCapNhat;
import com.mobilez.utils.JdbcHelper;
import com.mobilez.utils.Msgbox;
import java.util.List;

/**
 *
 * @author uhtku
 */
public class CapNhatGiaDAO extends MainDAO<CapNhatGia, String>{

    String insertSql = "insert into CAPNHATGIA values (?,?,?,?)";
    String updateSql = "update CAPNHATGIA set GIAMUA = ?, GIABAN = ? where NGAYCAPNHAT = ? and MAMH like ?";
    @Override
    public void insert(CapNhatGia entity) {
        NgayCapNhatDAO dao = new NgayCapNhatDAO();
        NgayCapNhat ncn = new NgayCapNhat(entity.getNgayCapNhat());
        dao.insert(ncn);
        int s = JdbcHelper.update(insertSql, entity.getNgayCapNhat(),entity.getMaMH(),
                entity.getGiaMua(),entity.getGiaBan());
        if (s<=0) {
            Msgbox.alert(null, "Lỗi thêm cập nhật giá!");
            return;
        }
    }

    @Override
    public void update(CapNhatGia entity) {
        int s = JdbcHelper.update(updateSql,entity.getGiaMua(),entity.getGiaBan(),
                entity.getNgayCapNhat(),entity.getMaMH());
        if (s<=0) {
            Msgbox.alert(null, "Lỗi cập nhật giá!");
            return;
        }
    }

    @Override
    public void delete(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CapNhatGia selectById(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<CapNhatGia> selectAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected List<CapNhatGia> selectBySql(String sql, Object... args) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
