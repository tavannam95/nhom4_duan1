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
public class QuayHang {
    private String maQH,tenQH;
    private  boolean trangThai;

    public QuayHang() {
    }

    public QuayHang(String maQH, String tenQH, boolean trangThai) {
        this.maQH = maQH;
        this.tenQH = tenQH;
        this.trangThai = trangThai;
    }

    public String getMaQH() {
        return maQH;
    }

    public void setMaQH(String maQH) {
        this.maQH = maQH;
    }

    public String getTenQH() {
        return tenQH;
    }

    public void setTenQH(String tenQH) {
        this.tenQH = tenQH;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    @Override
    public String toString() {
        return tenQH;
    }
    
    
}
