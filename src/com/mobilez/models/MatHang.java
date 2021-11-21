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
    private String maMH,maHSX,tenMH,ram,dungLuong,mauSac,maQG,hinhMH,donViTinh;
    private int soluong;
    private String thoiGianBaoHanh;

    public MatHang() {
    }

    public MatHang(String maMH, String maHSX, String tenMH, String ram, String dungLuong, String mauSac, String maQG, String hinhMH, String donViTinh, int soluong, String thoiGianBaoHanh) {
        this.maMH = maMH;
        this.maHSX = maHSX;
        this.tenMH = tenMH;
        this.ram = ram;
        this.dungLuong = dungLuong;
        this.mauSac = mauSac;
        this.maQG = maQG;
        this.hinhMH = hinhMH;
        this.donViTinh = donViTinh;
        this.soluong = soluong;
        this.thoiGianBaoHanh = thoiGianBaoHanh;
    }

    
    
    public String getMaMH() {
        return maMH;
    }

    public void setMaMH(String maMH) {
        this.maMH = maMH;
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

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getDungLuong() {
        return dungLuong;
    }

    public void setDungLuong(String dungLuong) {
        this.dungLuong = dungLuong;
    }

    public String getMauSac() {
        return mauSac;
    }

    public void setMauSac(String mauSac) {
        this.mauSac = mauSac;
    }

    public String getMaQG() {
        return maQG;
    }

    public void setMaQG(String maQG) {
        this.maQG = maQG;
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

    public String getThoiGianBaoHanh() {
        return thoiGianBaoHanh;
    }

    public void setThoiGianBaoHanh(String thoiGianBaoHanh) {
        this.thoiGianBaoHanh = thoiGianBaoHanh;
    }

    

    @Override
    public String toString() {
        return maMH;
    }
    
    
}
