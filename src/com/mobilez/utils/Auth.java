
package com.mobilez.utils;

import com.mobilez.models.NhanVien;
public class Auth {
    public static NhanVien user = null;
    public static boolean login = false; 
    
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
}
