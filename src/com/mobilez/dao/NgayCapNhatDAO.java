/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mobilez.dao;

import com.mobilez.models.NgayCapNhat;
import com.mobilez.utils.JdbcHelper;
import com.mobilez.utils.Msgbox;
import java.util.Date;
import java.util.List;
import java.sql.*;

/**
 *
 * @author uhtku
 */
public class NgayCapNhatDAO extends MainDAO<NgayCapNhat, Date>{
    String sql = "insert into NGAYCAPNHAT values(?)";
    @Override
    public void insert(NgayCapNhat entity) {
        try {
            boolean chk = true;
            String select = "select convert(nvarchar,NGAYCAPNHAT,103) from NGAYCAPNHAT where NGAYCAPNHAT = ?";
            ResultSet rs = JdbcHelper.query(select, entity.getNgayCapNhat());
            while (rs.next()) {                
                chk = false;
            }
            if (chk) {
                int s = JdbcHelper.update(sql, entity.getNgayCapNhat());
                if (s <= 0) {
                    Msgbox.alert(null, "Lỗi ngày cập nhật!");
                } 
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
    }

    @Override
    public void update(NgayCapNhat entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Date id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public NgayCapNhat selectById(Date id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<NgayCapNhat> selectAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected List<NgayCapNhat> selectBySql(String sql, Object... args) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
