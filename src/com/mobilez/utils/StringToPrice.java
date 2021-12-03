/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mobilez.utils;

import javax.swing.JTextField;

/**
 *
 * @author LAPTOP
 */
public class StringToPrice {
    public static String getPrice(String price){
        price = price.replace(" VND", "");
        price = price.replace(".", "");
        String tongtien2 = "";
        if (price.length() > 3) {
            int tongDauCham = (int) price.length() / 3; // Tổng số dấu chấm trong thành tiền
            if (price.length() % 3 == 1) {
                tongtien2 = price.substring(0, 1) + "." + price.substring(1, 4);
                int indexDauCham = 4;
                while (tongDauCham > 1) {
                    tongtien2 = tongtien2 + "." + price.substring(indexDauCham, indexDauCham + 3);
                    indexDauCham += 3;
                    tongDauCham--;
                }
            } else if (price.length() % 3 == 2) {
                tongtien2 = price.substring(0, 2) + "." + price.substring(2, 5);
                int indexDauCham = 5;
                while (tongDauCham > 1) {
                    tongtien2 = tongtien2 + "." + price.substring(indexDauCham, indexDauCham + 3);
                    indexDauCham += 3;
                    tongDauCham--;
                }
            } else if (price.length() % 3 == 0) {
                if (tongDauCham > 1) {
                    tongtien2 = price.substring(0, 3) + "." + price.substring(3, 6);
                }
                int indexDauCham = 6;
                while (tongDauCham > 2) {
                    tongtien2 = tongtien2 + "." + price.substring(indexDauCham, indexDauCham + 3);
                    indexDauCham += 3;
                    tongDauCham--;
                }
            }
            return tongtien2 + " VND";
        } else {
            return price + " VND";
        }
    }
    
    
    
    public static String getStringPriceToMoney(String priceString){
        String rs = priceString;
        rs = rs.replace(".", "");
        return rs;
    }
    
}
