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
public class ChiTietMatHang {

    private String ram;
    private String dungluong;
    private String mausac;
    private Date ngaycapnhat;
    private String maQG;
    private String maMH;
    private double giamua;
    private double giaban;
    private int soluong;

    public ChiTietMatHang() {
    }

    public ChiTietMatHang(String ram, String dungluong, String mausac, Date ngaycapnhat, String maQG, String maMH, double giamua, double giaban, int soluong) {
        this.ram = ram;
        this.dungluong = dungluong;
        this.mausac = mausac;
        this.ngaycapnhat = ngaycapnhat;
        this.maQG = maQG;
        this.maMH = maMH;
        this.giamua = giamua;
        this.giaban = giaban;
        this.soluong = soluong;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getDungluong() {
        return dungluong;
    }

    public void setDungluong(String dungluong) {
        this.dungluong = dungluong;
    }

    public String getMausac() {
        return mausac;
    }

    public void setMausac(String mausac) {
        this.mausac = mausac;
    }

    public Date getNgaycapnhat() {
        return ngaycapnhat;
    }

    public void setNgaycapnhat(Date ngaycapnhat) {
        this.ngaycapnhat = ngaycapnhat;
    }

    public String getMaQG() {
        return maQG;
    }

    public void setMaQG(String maQG) {
        this.maQG = maQG;
    }

    public String getMaMH() {
        return maMH;
    }

    public void setMaMH(String maMH) {
        this.maMH = maMH;
    }

    public double getGiamua() {
        return giamua;
    }

    public void setGiamua(double giamua) {
        this.giamua = giamua;
    }

    public double getGiaban() {
        return giaban;
    }

    public void setGiaban(double giaban) {
        this.giaban = giaban;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    @Override
    public String toString() {
        return "ChiTietMatHang{" + "ram=" + ram + ", dungluong=" 
                + dungluong + ", mausac=" 
                + mausac + ", ngaycapnhat=" + ngaycapnhat 
                + ", maQG=" + maQG + ", maMH=" + maMH + '}';
    }
    
}
