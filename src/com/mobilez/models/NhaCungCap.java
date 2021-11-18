/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mobilez.models;

/**
 *
 * @author ASUS
 */
public class NhaCungCap {
        private String maNcc;
        private String tenNcc;
        private String diaChi;
        private String SODT;
        private String FAX;
        private String Email;
        private String WEBSITE;

    public NhaCungCap() {
    }

    public NhaCungCap(String maNcc, String tenNcc, String diaChi, String SODT, String FAX, String Email, String WEBSITE) {
        this.maNcc = maNcc;
        this.tenNcc = tenNcc;
        this.diaChi = diaChi;
        this.SODT = SODT;
        this.FAX = FAX;
        this.Email = Email;
        this.WEBSITE = WEBSITE;
    }

    public String getMaNcc() {
        return maNcc;
    }

    public void setMaNcc(String maNcc) {
        this.maNcc = maNcc;
    }

    public String getTenNcc() {
        return tenNcc;
    }

    public void setTenNcc(String tenNcc) {
        this.tenNcc = tenNcc;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSODT() {
        return SODT;
    }

    public void setSODT(String SODT) {
        this.SODT = SODT;
    }

    public String getFAX() {
        return FAX;
    }

    public void setFAX(String FAX) {
        this.FAX = FAX;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getWEBSITE() {
        return WEBSITE;
    }

    public void setWEBSITE(String WEBSITE) {
        this.WEBSITE = WEBSITE;
    }

    @Override
    public String toString() {
        return maNcc;
    }
        
        

   
         
        
}
