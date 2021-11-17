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
public class HangSanXuat {
    private String maHSX,maLMH, tenHSX;

    public HangSanXuat() {
    }

    public HangSanXuat(String maHSX, String maLMH, String tenHSX) {
        this.maHSX = maHSX;
        this.maLMH = maLMH;
        this.tenHSX = tenHSX;
    }

    public String getMaHSX() {
        return maHSX;
    }

    public void setMaHSX(String maHSX) {
        this.maHSX = maHSX;
    }

    public String getMaLMH() {
        return maLMH;
    }

    public void setMaLMH(String maLMH) {
        this.maLMH = maLMH;
    }

    public String getTenHSX() {
        return tenHSX;
    }

    public void setTenHSX(String tenHSX) {
        this.tenHSX = tenHSX;
    }
    
    @Override
    public String toString() {
        return maHSX;
    }
    
}
