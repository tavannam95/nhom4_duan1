/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mobilez.models;

import java.util.Date;

/**
 *
 * @author duong
 */
public class PhieuGiaoCa {
    private int maPGC;
    private Date ngayGiaoCa;
    private String maNV;
    private String maQH;
    private int ca;

    public PhieuGiaoCa() {
    }

    public PhieuGiaoCa(int maPGC, Date ngayGiaoCa, String maNV, String maQH, int ca) {
        this.maPGC = maPGC;
        this.ngayGiaoCa = ngayGiaoCa;
        this.maNV = maNV;
        this.maQH = maQH;
        this.ca = ca;
    }
    
    public PhieuGiaoCa(Date ngayGiaoCa, String maNV, String maQH, int ca) {
        this.ngayGiaoCa = ngayGiaoCa;
        this.maNV = maNV;
        this.maQH = maQH;
        this.ca = ca;
    }

    public int getMaPGC() {
        return maPGC;
    }

    public void setMaPGC(int maPGC) {
        this.maPGC = maPGC;
    }

    public Date getNgayGiaoCa() {
        return ngayGiaoCa;
    }

    public void setNgayGiaoCa(Date ngayGiaoCa) {
        this.ngayGiaoCa = ngayGiaoCa;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getMaQH() {
        return maQH;
    }

    public void setMaQH(String maQH) {
        this.maQH = maQH;
    }

    public int getCa() {
        return ca;
    }

    public void setCa(int ca) {
        this.ca = ca;
    }
    
}
