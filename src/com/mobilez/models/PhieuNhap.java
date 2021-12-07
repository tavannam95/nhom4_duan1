/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mobilez.models;

/**
 *
 * @author uhtku
 */
public class PhieuNhap {
    private String maMH , tenMh , soLuong , donGia , thanhTien ;

    public PhieuNhap() {
    }

    public PhieuNhap(String maMH, String tenMh, String soLuong, String donGia, String thanhTien) {
        this.maMH = maMH;
        this.tenMh = tenMh;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.thanhTien = thanhTien;
    }

    public String getMaMH() {
        return maMH;
    }

    public void setMaMH(String maMH) {
        this.maMH = maMH;
    }

    public String getTenMh() {
        return tenMh;
    }

    public void setTenMh(String tenMh) {
        this.tenMh = tenMh;
    }

    public String getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(String soLuong) {
        this.soLuong = soLuong;
    }

    public String getDonGia() {
        return donGia;
    }

    public void setDonGia(String donGia) {
        this.donGia = donGia;
    }

    public String getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(String thanhTien) {
        this.thanhTien = thanhTien;
    }
    
}
