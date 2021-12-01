/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mobilez.models;

/**
 *
 * @author uhtku
 */
public class Kho {

    String maK, tenK, diaChi;

    public Kho() {
    }

    public Kho(String maK, String tenK, String diaChi) {
        this.maK = maK;
        this.tenK = tenK;
        this.diaChi = diaChi;
    }

    @Override
    public String toString() {
        return this.tenK; //To change body of generated methods, choose Tools | Templates.
    }
    
    public String getMaK() {
        return maK;
    }

    public void setMaK(String maK) {
        this.maK = maK;
    }

    public String getTenK() {
        return tenK;
    }

    public void setTenK(String tenK) {
        this.tenK = tenK;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }
}

