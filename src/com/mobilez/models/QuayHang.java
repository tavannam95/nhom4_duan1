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
public class QuayHang {
    private String maQH;
    private String tenQH;

    public QuayHang() {
    }

    public QuayHang(String maQH, String tenQH) {
        this.maQH = maQH;
        this.tenQH = tenQH;
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

    @Override
    public String toString() {
        return tenQH;
    }
    
    
}
