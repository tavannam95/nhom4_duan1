/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mobilez.models;

import java.util.Date;

/**
 *
 * @author uhtku
 */
public class CapNhatGia {
    private Date ngayCapNhat;
    private String maMH;
    private double giaMua,giaBan;

    public CapNhatGia() {
    }

    public CapNhatGia(Date ngayCapNhat, String maMH, double giaMua, double giaBan) {
        this.ngayCapNhat = ngayCapNhat;
        this.maMH = maMH;
        this.giaMua = giaMua;
        this.giaBan = giaBan;
    }

    public Date getNgayCapNhat() {
        return ngayCapNhat;
    }

    public void setNgayCapNhat(Date ngayCapNhat) {
        this.ngayCapNhat = ngayCapNhat;
    }

    public String getMaMH() {
        return maMH;
    }

    public void setMaMH(String maMH) {
        this.maMH = maMH;
    }

    public double getGiaMua() {
        return giaMua;
    }

    public void setGiaMua(double giaMua) {
        this.giaMua = giaMua;
    }

    public double getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(double giaBan) {
        this.giaBan = giaBan;
    }
    
    
}
