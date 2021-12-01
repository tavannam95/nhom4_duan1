/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mobilez.utils;

import java.awt.Color;
import java.util.Random;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author Nobi
 */
public class TextAffect {
    
   public static String convertToPhoneDot(JTextField phoneNumber){
       if (phoneNumber.getText().length()==4) {
            phoneNumber.setText(phoneNumber.getText()+".");
        }
        if (phoneNumber.getText().length()==8) {
            phoneNumber.setText(phoneNumber.getText()+".");
        }
        return phoneNumber.getText();
   }
    
    public static String randomString(Random rng, int length) {

        String characters = "abcd_efghij_klm_nopqrst_uvwxyz_";

        char[] text = new char[length];

        for (int i = 0; i < length; i++) {
            text[i] = characters.charAt(rng.nextInt(characters.length()));
        }

        return new String(text);
    }
    public static void colorText(JLabel s) {
        
        Thread t = new Thread() {
            @Override
            public void run() {
                for (int i = 0; true; i++) {
                    //
                    Random random = new Random();
                    
                    //float
                    float r, g, b;
                    
                    //random giá trị bất kì
                    r = random.nextFloat();
                    g = random.nextFloat();
                    b = random.nextFloat();
                    
                    
                    Color color = new Color(r, g, b);
                    
                    s.setForeground(color);
                    
                    try {
                        Thread.sleep(100);
                    } catch (Exception e) {
                    }
                }

            }

        };
        t.start();
    }
    public static void textRun(JLabel lbl , String str) {
        Thread t = new Thread() {
            @Override
            public void run() {
                String text =str;
                for (int i = 0; true; i++) {
                    lbl.setText(text);
                    text = text.substring(1, text.length()) + text.charAt(0);
                    try {
                        if (text.equalsIgnoreCase(str)) {
                            Thread.sleep(1000);
                        } else {
                            Thread.sleep(50);
                        }
                    } catch (Exception e) {
                    }
                }
            }

        };
        t.start();
    }
}
