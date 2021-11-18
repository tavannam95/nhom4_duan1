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
        private String sodt;
        private String fax;
        private String email;
        private String website;

    public NhaCungCap() {
    }

    public NhaCungCap(String maNcc, String tenNcc, String diaChi, String sodt, String fax, String email, String website) {
        this.maNcc = maNcc;
        this.tenNcc = tenNcc;
        this.diaChi = diaChi;
        this.sodt = sodt;
        this.fax = fax;
        this.email = email;
        this.website = website;
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

    public String getSodt() {
        return sodt;
    }

    public void setSodt(String sodt) {
        this.sodt = sodt;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    

    @Override
    public String toString() {
        return maNcc;
    }
        
        

   
         
        
}
