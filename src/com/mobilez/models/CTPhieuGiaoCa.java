/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mobilez.models;

/**
 *
 * @author duong
 */
public class CTPhieuGiaoCa {
    private int maPGC;
    private String maMH;
    private int soLuong;

    public CTPhieuGiaoCa() {
    }

    public CTPhieuGiaoCa(int maPGC, String maMH, int soLuong) {
        this.maPGC = maPGC;
        this.maMH = maMH;
        this.soLuong = soLuong;
    }

    public int getMaPGC() {
        return maPGC;
    }

    public void setMaPGC(int maPGC) {
        this.maPGC = maPGC;
    }

    public String getMaMH() {
        return maMH;
    }

    public void setMaMH(String maMH) {
        this.maMH = maMH;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
    
}
