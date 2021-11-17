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
        private String MANCC;
        private String TENNCC;
        private String DIACHI;
        private String SODT;
        private String FAX;
        private String EMAIL;
        private String WEBSITE;

    public NhaCungCap() {
    }

    public NhaCungCap(String MANCC, String TENNCC, String DIACHI, String SODT, String FAX, String EMAIL, String WEBSITE) {
        this.MANCC = MANCC;
        this.TENNCC = TENNCC;
        this.DIACHI = DIACHI;
        this.SODT = SODT;
        this.FAX = FAX;
        this.EMAIL = EMAIL;
        this.WEBSITE = WEBSITE;
    }

    public String getMANCC() {
        return MANCC;
    }

    public void setMANCC(String MANCC) {
        this.MANCC = MANCC;
    }

    public String getTENNCC() {
        return TENNCC;
    }

    public void setTENNCC(String TENNCC) {
        this.TENNCC = TENNCC;
    }

    public String getDIACHI() {
        return DIACHI;
    }

    public void setDIACHI(String DIACHI) {
        this.DIACHI = DIACHI;
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

    public String getEMAIL() {
        return EMAIL;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }

    public String getWEBSITE() {
        return WEBSITE;
    }

    public void setWEBSITE(String WEBSITE) {
        this.WEBSITE = WEBSITE;
    }

    @Override
    public String toString() {
        return MANCC;
    }
         
        
}
