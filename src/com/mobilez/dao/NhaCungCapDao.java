/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mobilez.dao;

import com.mobilez.models.NhaCungCap;
import com.mobilez.utils.JdbcHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class NhaCungCapDao extends MainDAO<NhaCungCap, String>{
    String INSERT = "INSERT INTO NHACUNGCAP(MANCC,TENNCC,DIACHI,SODT,FAX,EMAIL,WEBSITE) VALUES (?,?,?,?,?,?,?)";
    String UPDATE ="Update NHACUNGCAP set TENNCC= ?,DIACHI=?,SODT=?,FAX=?,EMAIL=?,WEBSITE=? where MANCC=?";
    String Delete ="Delete from NHACUNGCAP where MANCC=?";
    String SELECT_ALL_SQL="Select * from NHACUNGCAP";
    String SELECT_BY_ID = "Select * from NHACUNGCAP where MANCC=?";

    @Override
    public void insert(NhaCungCap ncc) {
        JdbcHelper.update(INSERT, ncc.getMaNcc(),ncc.getTenNcc(),ncc.getDiaChi(),ncc.getSodt(),ncc.getFax(),ncc.getEmail(),ncc.getWebsite());
    }

    @Override
    public void update(NhaCungCap ncc) {
       JdbcHelper.update(UPDATE, ncc.getTenNcc(),ncc.getDiaChi(),ncc.getSodt(),ncc.getFax(),ncc.getEmail(),ncc.getWebsite(),ncc.getMaNcc());
    }

    @Override
    public void delete(String id) {
        JdbcHelper.update(Delete, id);
    }

    @Override
    public NhaCungCap selectById(String id) {
        List<NhaCungCap> list = selectBySql(SELECT_BY_ID, id);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<NhaCungCap> selectAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    protected List<NhaCungCap> selectBySql(String sql, Object... args) {
        List<NhaCungCap> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.query(sql, args);
            while(rs.next()){
                NhaCungCap ncc = new NhaCungCap();
                ncc.setMaNcc(rs.getString("MANCC"));
                ncc.setTenNcc(rs.getString("TENNCC"));
                ncc.setDiaChi(rs.getString("DIACHI"));
                ncc.setSodt(rs.getString("SODT"));
                ncc.setFax(rs.getString("FAX"));
                ncc.setEmail(rs.getString("EMAIL"));
                ncc.setWebsite(rs.getString("WEBSITE"));
                
                list.add(ncc);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        return list;
    }
    
    public  boolean checkDuplicate(String maNCC) {
        NhaCungCap nhaCC = selectById(maNCC);
        if (nhaCC == null) {
            return true;
        }
        return false;
    }
    
}
