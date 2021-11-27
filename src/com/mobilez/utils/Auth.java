
package com.mobilez.utils;

import com.mobilez.models.NhanVien;
public class Auth {
    public static NhanVien user = null;
    public static boolean login = false; 
    public static boolean giaoCa = true;
    public static int caLam = 0;
    public static String maQuay;
    public static int maPGC;
    public static void clear(){
        Auth.user = null;
        Auth.login = true;
    }
    public static boolean isLogin(){
        return Auth.user !=null;
    }
    public static boolean isManager(){
        return Auth.isLogin()&&user.isVaiTro();
    }
    public static void giaoCa(){
        giaoCa = true;
        caLam = 0;
        maQuay = null;
        maPGC = 0;
    }
}
