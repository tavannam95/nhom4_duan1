/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mobilez.models;

/**
 *
 * @author Admin
 */
public class KhachHang {
//    - Mã khách hàng (MaKH)
//- Tên khách hàng (TenKH)
//- Điạ chỉ (DiaChi)
//- Điện thoại (Phone)
    private String MaKH, TenKH, diaChi, soDienThoai ;

    public KhachHang(String MaKH, String TenKH, String diaChi, String soDienThoai) {
        this.MaKH = MaKH;
        this.TenKH = TenKH;
        this.diaChi = diaChi;
        this.soDienThoai = soDienThoai;
    }

    public KhachHang() {
    }

    @Override
    public String toString() {
        return this.MaKH;
    }

   
}
