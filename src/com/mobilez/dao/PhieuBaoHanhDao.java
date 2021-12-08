/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mobilez.dao;

import com.mobilez.models.PhieuBaoHanh;
import com.mobilez.utils.JdbcHelper;
import com.mobilez.utils.Msgbox;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class PhieuBaoHanhDao {

    String insert = " insert into PHIEUBAOHANH values (?, ?, ?, ?)";

    public void insert(PhieuBaoHanh ph) {
        int s = JdbcHelper.update(insert, ph.getSoIMEI(),
                ph.getMaMH(),
                ph.getNgayHetHan(),
                ph.isTrangThai());
    }
    String Update = "Update PHIEUBAOHANH set TRANGTHAI=? where SOIMEI=?";

    public void update(PhieuBaoHanh ph) {
        JdbcHelper.update(Update, ph.getSoIMEI(),
                ph.getNgayHetHan(),
                ph.isTrangThai(),
                ph.getMaMH());
    }
    String selectbyid = "Select * from PHIEUBAOHANH where SOIMEI =?";

    public PhieuBaoHanh selectbyID(String id) {
        List<PhieuBaoHanh> list = selectbysql(selectbyid, id);
        if (list.isEmpty()) {
            return null;

        }
        return list.get(0);

    }
    String SELECT_ALL="Select * from PHIEUBAOHANH";
    public List<PhieuBaoHanh> selectALL(){
        return selectbysql(SELECT_ALL);
    }

    protected List<PhieuBaoHanh> selectbysql(String sql, Object... args) {
        List<PhieuBaoHanh> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.query(sql, args);
            while (rs.next()) {
                PhieuBaoHanh ph = new PhieuBaoHanh();
                ph.setSoIMEI(rs.getString("SOIMEI"));
                ph.setMaMH(rs.getString("MAMH"));
                ph.setNgayHetHan(rs.getDate("NGAYHETHAN"));
                ph.setTrangThai(rs.getBoolean("TRANGTHAI"));
                list.add(ph);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public  boolean checkDuplicate(String maPBH) {
        PhieuBaoHanh pbh = selectbyID(maPBH);
        if (pbh == null) {
            return true;
        }
        return false;
    }
}
