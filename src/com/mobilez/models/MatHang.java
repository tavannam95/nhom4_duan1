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
    private String maMH,maHSX,tenMH;
    private int rAM,dungLuong;
    private String mauSac,maQG,hinhMH;
    private int soLuong,tGBH;
    private double giaMua,giaBanLe;
    private boolean trangThai;

    public MatHang() {
    }

    public MatHang(String maMH, String maHSX, String tenMH, int rAM, int dungLuong, String mauSac, String maQG, String hinhMH, int soLuong, int tGBH, double giaMua, double giaBanLe, boolean trangThai) {
        this.maMH = maMH;
        this.maHSX = maHSX;
        this.tenMH = tenMH;
        this.rAM = rAM;
        this.dungLuong = dungLuong;
        this.mauSac = mauSac;
        this.maQG = maQG;
        this.hinhMH = hinhMH;
        this.soLuong = soLuong;
        this.tGBH = tGBH;
        this.giaMua = giaMua;
        this.giaBanLe = giaBanLe;
        this.trangThai = trangThai;
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

    public int getrAM() {
        return rAM;
    }

    public void setrAM(int rAM) {
        this.rAM = rAM;
    }

    public int getDungLuong() {
        return dungLuong;
    }

    public void setDungLuong(int dungLuong) {
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

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int gettGBH() {
        return tGBH;
    }

    public void settGBH(int tGBH) {
        this.tGBH = tGBH;
    }

    public double getGiaMua() {
        return giaMua;
    }

    public void setGiaMua(double giaMua) {
        this.giaMua = giaMua;
    }

    public double getGiaBanLe() {
        return giaBanLe;
    }

    public void setGiaBanLe(double giaBanLe) {
        this.giaBanLe = giaBanLe;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

   

    

    

    @Override
    public String toString() {
        return tenMH;
    }
    
    
}
