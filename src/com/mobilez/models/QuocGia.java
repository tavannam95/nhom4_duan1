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
public class QuocGia {
    private String maQG , tenQG ;

    public QuocGia() {
    }

    public QuocGia(String maQG, String tenQG) {
        this.maQG = maQG;
        this.tenQG = tenQG;
    }

    @Override
    public String toString() {
        return tenQG; //To change body of generated methods, choose Tools | Templates.
    }

    public String getMaQG() {
        return maQG;
    }

    public void setMaQG(String maQG) {
        this.maQG = maQG;
    }

    public String getTenQG() {
        return tenQG;
    }

    public void setTenQG(String tenQG) {
        this.tenQG = tenQG;
    }
    
    
}
