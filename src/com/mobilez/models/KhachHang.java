/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mobilez.models;

/**
 *
 * @author Admin
 */
public class KhachHang {
    private String MaKH, TenKH, diaChi, soDienThoai ;

    public KhachHang(String MaKH, String TenKH, String diaChi, String soDienThoai) {
        this.MaKH = MaKH;
        this.TenKH = TenKH;
        this.diaChi = diaChi;
        this.soDienThoai = soDienThoai;
    }

    public KhachHang() {
    }
     @Override
    public String toString() {
        return this.MaKH;
    }

    public String getMaKH() {
        return MaKH;
    }

    public void setMaKH(String MaKH) {
        this.MaKH = MaKH;
    }

    public String getTenKH() {
        return TenKH;
    }

    public void setTenKH(String TenKH) {
        this.TenKH = TenKH;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }
    
}
