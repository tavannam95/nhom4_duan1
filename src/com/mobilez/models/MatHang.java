/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mobilez.models;

/**
 *
 * @author LAPTOP
 */
public class MatHang {
    private String maMH,maQH,maHSX,tenMH,hinhMH,donViTinh;
    private int soluong;
    private String thoiGIanBaoHanh;

    public MatHang() {
    }

    public MatHang(String maMH, String maQH, String maHSX, String tenMH, String hinhMH, String donViTinh, int soluong, String thoiGIanBaoHanh) {
        this.maMH = maMH;
        this.maQH = maQH;
        this.maHSX = maHSX;
        this.tenMH = tenMH;
        this.hinhMH = hinhMH;
        this.donViTinh = donViTinh;
        this.soluong = soluong;
        this.thoiGIanBaoHanh = thoiGIanBaoHanh;
    }

    
    
    public String getMaMH() {
        return maMH;
    }

    public void setMaMH(String maMH) {
        this.maMH = maMH;
    }

    public String getMaQH() {
        return maQH;
    }

    public void setMaQH(String maQH) {
        this.maQH = maQH;
    }

    public String getMaHSX() {
        return maHSX;
    }

    public void setMaHSX(String maHSX) {
        this.maHSX = maHSX;
    }

    public String getTenMH() {
        return tenMH;
    }

    public void setTenMH(String tenMH) {
        this.tenMH = tenMH;
    }

    public String getHinhMH() {
        return hinhMH;
    }

    public void setHinhMH(String hinhMH) {
        this.hinhMH = hinhMH;
    }

    public String getDonViTinh() {
        return donViTinh;
    }

    public void setDonViTinh(String donViTinh) {
        this.donViTinh = donViTinh;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public String getThoiGIanBaoHanh() {
        return thoiGIanBaoHanh;
    }

    public void setThoiGIanBaoHanh(String thoiGIanBaoHanh) {
        this.thoiGIanBaoHanh = thoiGIanBaoHanh;
    }

    @Override
    public String toString() {
        return maMH;
    }
    
    
}
