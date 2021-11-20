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
        private String soDT;
        private String fax;
        private String email;
        private String website;
        private String hinh;

    public NhaCungCap() {
    }

    public NhaCungCap(String maNcc, String tenNcc, String diaChi, String soDT, String fax, String email, String website, String hinh) {
        this.maNcc = maNcc;
        this.tenNcc = tenNcc;
        this.diaChi = diaChi;
        this.soDT = soDT;
        this.fax = fax;
        this.email = email;
        this.website = website;
        this.hinh = hinh;
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

    public String getSoDT() {
        return soDT;
    }

    public void setSoDT(String soDT) {
        this.soDT = soDT;
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

    public String getHinh() {
        return hinh;
    }

    public void setHinh(String hinh) {
        this.hinh = hinh;
    }

  

   

    @Override
    public String toString() {
        return maNcc;
    }
        
        

   
         
        
}
