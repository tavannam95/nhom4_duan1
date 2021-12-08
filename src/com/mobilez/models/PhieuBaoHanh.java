/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mobilez.models;

import java.util.Date;

/**
 *
 * @author ASUS
 */
public class PhieuBaoHanh {
    private  int soIMEI;
    private String maMH;
    private Date ngayHetHan;
    private boolean trangThai;

    public PhieuBaoHanh() {
    }

    public PhieuBaoHanh(int soIMEI, String maMH, Date ngayHetHan, boolean trangThai) {
        this.soIMEI = soIMEI;
        this.maMH = maMH;
        this.ngayHetHan = ngayHetHan;
        this.trangThai = trangThai;
    }
    
    
    public int getSoIMEI() {
        return soIMEI;
    }

    public void setSoIMEI(int soIMEI) {
        this.soIMEI = soIMEI;
    }

    

    public String getMaMH() {
        return maMH;
    }

    public void setMaMH(String maMH) {
        this.maMH = maMH;
    }

    public Date getNgayHetHan() {
        return ngayHetHan;
    }

    public void setNgayHetHan(Date ngayHetHan) {
        this.ngayHetHan = ngayHetHan;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    

    
}
