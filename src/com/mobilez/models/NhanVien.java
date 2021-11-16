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
public class NhanVien {
    private String maNV,hoTen;
    private boolean gioiTinh;
    private Date ngaysinh;
    private String soCCCD,diaChi,soDienThoai;
    private boolean vaiTro;
    private String hinhNV;

    public NhanVien() {
    }

    public NhanVien(String maNV, String hoTen, boolean gioiTinh, Date ngaysinh, String soCCCD, String diaChi, String soDienThoai, boolean vaiTro, String hinhNV) {
        this.maNV = maNV;
        this.hoTen = hoTen;
        this.gioiTinh = gioiTinh;
        this.ngaysinh = ngaysinh;
        this.soCCCD = soCCCD;
        this.diaChi = diaChi;
        this.soDienThoai = soDienThoai;
        this.vaiTro = vaiTro;
        this.hinhNV = hinhNV;
    }
    

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public boolean isGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public Date getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(Date ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    public String getSoCCCD() {
        return soCCCD;
    }

    public void setSoCCCD(String soCCCD) {
        this.soCCCD = soCCCD;
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

    public boolean isVaiTro() {
        return vaiTro;
    }

    public void setVaiTro(boolean vaiTro) {
        this.vaiTro = vaiTro;
    }

    public String getHinhNV() {
        return hinhNV;
    }

    public void setHinhNV(String hinhNV) {
        this.hinhNV = hinhNV;
    }

    @Override
    public String toString() {
        return maNV;
    }
}
