/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mobilez.ui;

import com.mobilez.dao.HangSanXuatDao;
import com.mobilez.dao.MatHangDAO;
import com.mobilez.models.HangSanXuat;
import com.mobilez.models.MatHang;
import com.mobilez.models.NhanVien;
import com.mobilez.models.QuocGia;
import com.mobilez.utils.JdbcHelper;
import com.mobilez.utils.Msgbox;
import com.mobilez.utils.StringToPrice;
import com.mobilez.utils.XImage;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.io.File;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author uhtku
 */
public class FrmHangHoa extends javax.swing.JPanel {

    /**
     * Creates new form FrmHangHoa
     */
    DefaultTableModel modelTbl;
    DefaultComboBoxModel<HangSanXuat> modelCboHSX;
    DefaultComboBoxModel<QuocGia> modelCboQG;
    int index = -1;

    public FrmHangHoa() {
        initComponents();
        modelTbl = (DefaultTableModel) tblList.getModel();
        modelCboHSX = (DefaultComboBoxModel) cboHSX.getModel();
        modelCboQG = (DefaultComboBoxModel) cboQuocGia.getModel();
        this.fillCboHSX();
        this.fillCboQG();
        this.fillTable();
        this.showBtnThem();
    }
    //My method

    private void chkThem() {
        //validate txtMaMH
        if (txtMaMH.getText().trim().equals("")) {
            Msgbox.alert(null, "Không được để trống mã mặt hàng!");
            txtMaMH.setText("");
            txtMaMH.requestFocus();
            lblMaMH.setForeground(Color.red);
            return;
        } else {
            lblMaMH.setForeground(Color.white);
        }
        for (int i = 0; i < tblList.getRowCount(); i++) {
            if (tblList.getValueAt(i, 0).toString().equalsIgnoreCase(txtMaMH.getText())) {
                Msgbox.alert(null, "Mã mặt hàng đã tồn tại!");
                lblMaMH.setForeground(Color.red);
                return;
            } else {
                lblMaMH.setForeground(Color.white);
            }
        }
        //validate txtTenMH
        if (txtTenMH.getText().trim().equals("")) {
            Msgbox.alert(null, "Không được để trống tên mặt hàng!");
            txtTenMH.setText("");
            txtTenMH.requestFocus();
            lblTenMH.setForeground(Color.red);
            return;
        } else {
            lblTenMH.setForeground(Color.white);
        }
        //validate RAm
        if (txtRAM.getText().trim().equals("")) {
            Msgbox.alert(null, "Không được để trống RAM!");
            txtRAM.setText("");
            txtRAM.requestFocus();
            lblRAM.setForeground(Color.red);
            return;
        } else {
            lblRAM.setForeground(Color.white);
        }
        try {
            Integer.parseInt(txtRAM.getText());
        } catch (Exception e) {
            Msgbox.alert(null, "RAM phải là số!");
            txtRAM.setText("");
            txtRAM.requestFocus();
            lblRAM.setForeground(Color.red);
            return;
        }
        //validate dung luong
        if (txtDungLuong.getText().trim().equals("")) {
            Msgbox.alert(null, "Không được để trống dung lượng!");
            txtDungLuong.setText("");
            txtDungLuong.requestFocus();
            lblDL.setForeground(Color.red);
            return;
        } else {
            lblDL.setForeground(Color.white);
        }
        try {
            Integer.parseInt(txtDungLuong.getText());
        } catch (Exception e) {
            Msgbox.alert(null, "Số lượng phải là số!");
            txtDungLuong.setText("");
            txtDungLuong.requestFocus();
            lblDL.setForeground(Color.red);
            return;
        }
        //validate mau sac
        if (txtMauSac.getText().trim().equals("")) {
            Msgbox.alert(null, "Không được để trống màu sắc!");
            txtMauSac.setText("");
            txtMauSac.requestFocus();
            lblMS.setForeground(Color.red);
            return;
        } else {
            lblMS.setForeground(Color.white);
        }

        
        //validate gia mua
        if (txtGiaMua.getText().trim().equals("")) {
            Msgbox.alert(null, "Không được để trống giá mua!");
            txtGiaMua.setText("");
            txtGiaMua.requestFocus();
            lblGM.setForeground(Color.red);
            return;
        } else {
            lblGM.setForeground(Color.white);
        }

        //validate gia si
//        if (txtGiaBanSi.getText().trim().equals("")) {
//            Msgbox.alert(null, "Không được để trống giá bán sỉ!");
//            txtGiaBanSi.setText("");
//            txtGiaBanSi.requestFocus();
//            lblGBS.setForeground(Color.red);
//            return;
//        } else {
//            lblGBS.setForeground(Color.white);
//        }
//        try {
//            Integer.parseInt(txtGiaBanSi.getText());
//        } catch (Exception e) {
//            Msgbox.alert(null, "Giá bán sỉ phải là số!");
//            txtGiaBanSi.setText("");
//            txtGiaBanSi.requestFocus();
//            lblGBS.setForeground(Color.red);
//            return;
//        }
//        lblGBS.setForeground(Color.white);
//        if (Integer.parseInt(txtGiaBanSi.getText()) <= 0) {
//            Msgbox.alert(null, "Giá bán sỉ phải lơn hơn 0!");
//            txtGiaBanSi.setText("");
//            txtGiaBanSi.requestFocus();
//            lblGBS.setForeground(Color.red);
//            return;
//        } else {
//            lblGBS.setForeground(Color.white);
//        }
        //validate gia ban le
        if (txtGiaBanLe.getText().trim().equals("")) {
            Msgbox.alert(null, "Không được để trống giá bán lẻ!");
            txtGiaBanLe.setText("");
            txtGiaBanLe.requestFocus();
            lblGBL.setForeground(Color.red);
            return;
        } else {
            lblGBL.setForeground(Color.white);
        }
        try {
            Integer.parseInt(txtGiaBanLe.getText());
        } catch (Exception e) {
            Msgbox.alert(null, "Giá bán lẻ phải là số!");
            txtGiaBanLe.setText("");
            txtGiaBanLe.requestFocus();
            lblGBL.setForeground(Color.red);
            return;
        }
        lblGBL.setForeground(Color.white);
        if (Integer.parseInt(txtGiaBanLe.getText()) <= 0) {
            Msgbox.alert(null, "Giá mua phải lơn hơn 0!");
            txtGiaBanLe.setText("");
            txtGiaBanLe.requestFocus();
            lblGBL.setForeground(Color.red);
            return;
        } else {
            lblGBL.setForeground(Color.white);
        }
        //validate bao hanh
        if (txtTGBH.getText().trim().equals("")) {
            Msgbox.alert(null, "Không được để trống thời gian bảo hành!");
            txtTGBH.setText("");
            txtTGBH.requestFocus();
            lblBH.setForeground(Color.red);
            return;
        } else {
            lblBH.setForeground(Color.white);
        }
        try {
            Integer.parseInt(txtTGBH.getText());
        } catch (Exception e) {
            Msgbox.alert(null, "Thời gian bảo hành phải là số!");
            txtTGBH.setText("");
            txtTGBH.requestFocus();
            lblBH.setForeground(Color.red);
            return;
        }
        lblBH.setForeground(Color.white);
        if (Integer.parseInt(txtTGBH.getText()) <= 0) {
            Msgbox.alert(null, "Thời gian bảo hành phải lơn hơn 0!");
            txtTGBH.setText("");
            txtTGBH.requestFocus();
            lblBH.setForeground(Color.red);
            return;
        } else {
            lblBH.setForeground(Color.white);
        }
        //validate hinh mh
        if (lblHinhMH.getToolTipText() == null) {
            Msgbox.alert(null, "Bạn chưa chọn ảnh!");
            lblHMH.setForeground(Color.red);
            return;
        } else {
            lblHMH.setForeground(Color.white);
        }
    }
//------------------------------------------------------------------------------------------------------------//

    private void chkSua() {

        //validate txtTenMH
        if (txtTenMH.getText().trim().equals("")) {
            Msgbox.alert(null, "Không được để trống tên mặt hàng!");
            txtTenMH.setText("");
            txtTenMH.requestFocus();
            lblTenMH.setForeground(Color.red);
            return;
        } else {
            lblTenMH.setForeground(Color.white);
        }
        //validate RAm
        if (txtRAM.getText().trim().equals("")) {
            Msgbox.alert(null, "Không được để trống RAM!");
            txtRAM.setText("");
            txtRAM.requestFocus();
            lblRAM.setForeground(Color.red);
            return;
        } else {
            lblRAM.setForeground(Color.white);
        }
        //validate dung luong
        if (txtDungLuong.getText().trim().equals("")) {
            Msgbox.alert(null, "Không được để trống dung lượng!");
            txtDungLuong.setText("");
            txtDungLuong.requestFocus();
            lblDL.setForeground(Color.red);
            return;
        } else {
            lblDL.setForeground(Color.white);
        }
        //validate mau sac
        if (txtDungLuong.getText().trim().equals("")) {
            Msgbox.alert(null, "Không được để trống màu sắc!");
            txtDungLuong.setText("");
            txtDungLuong.requestFocus();
            lblDL.setForeground(Color.red);
            return;
        } else {
            lblDL.setForeground(Color.white);
        }
        //validate don vi
        if (txtGiaMua.getText().trim().equals("")) {
            Msgbox.alert(null, "Không được để trống màu sắc!");
            txtGiaMua.setText("");
            txtGiaMua.requestFocus();
            lblGM.setForeground(Color.red);
            return;
        } else {
            lblGM.setForeground(Color.white);
        }

        //validate bao hanh
        if (txtTGBH.getText().trim().equals("")) {
            Msgbox.alert(null, "Không được để trống thời gian bảo hành!");
            txtTGBH.setText("");
            txtTGBH.requestFocus();
            lblBH.setForeground(Color.red);
            return;
        } else {
            lblBH.setForeground(Color.white);
        }
        try {
            Integer.parseInt(txtTGBH.getText());
        } catch (Exception e) {
            Msgbox.alert(null, "Thời gian bảo hành phải là số!");
            txtTGBH.setText("");
            txtTGBH.requestFocus();
            lblBH.setForeground(Color.red);
            return;
        }
        lblBH.setForeground(Color.white);
        if (Integer.parseInt(txtTGBH.getText()) <= 0) {
            Msgbox.alert(null, "Thời gian bảo hành phải lơn hơn 0!");
            txtTGBH.setText("");
            txtTGBH.requestFocus();
            lblBH.setForeground(Color.red);
            return;
        } else {
            lblBH.setForeground(Color.white);
        }

        //validate hinh mh
        if (lblHinhMH.getToolTipText().trim().equals("")) {
            Msgbox.alert(null, "Bạn chưa chọn ảnh!");
            lblHMH.setForeground(Color.red);
            return;
        } else {
            lblHMH.setForeground(Color.white);
        }
    }

    private void showBtnThem() {
        btnThemMH.setEnabled(true);
        btnSuaMH.setEnabled(false);
//        btnXoaMH.setEnabled(false);
        txtMaMH.setEditable(true);
//        txtGiaBanSi.setEditable(true);
        txtGiaBanLe.setEditable(true);
        txtMaMH.setForeground(Color.WHITE);
//        txtGiaBanSi.setForeground(Color.WHITE);
        txtGiaBanLe.setForeground(Color.WHITE);
    }

    private void hideBtnThem() {
        btnThemMH.setEnabled(false);
        btnSuaMH.setEnabled(true);
//        btnXoaMH.setEnabled(true);
        txtMaMH.setEditable(false);
//        txtGiaBanSi.setEditable(false);
        txtGiaBanLe.setEditable(false);
        txtMaMH.setForeground(Color.ORANGE);
//        txtGiaBanSi.setForeground(Color.ORANGE);
        txtGiaBanLe.setForeground(Color.ORANGE);
    }

    private void them() {
        try {
//            this.chkThem();
            //validate txtMaMH
            if (txtMaMH.getText().trim().equals("")) {
                Msgbox.alert(null, "Không được để trống mã mặt hàng!");
                txtMaMH.setText("");
                txtMaMH.requestFocus();
                lblMaMH.setForeground(Color.red);
                return;
            } else {
                lblMaMH.setForeground(Color.white);
            }
            for (int i = 0; i < tblList.getRowCount(); i++) {
                if (tblList.getValueAt(i, 0).toString().equalsIgnoreCase(txtMaMH.getText())) {
                    Msgbox.alert(null, "Mã mặt hàng đã tồn tại!");
                    lblMaMH.setForeground(Color.red);
                    return;
                } else {
                    lblMaMH.setForeground(Color.white);
                }
            }
            if (txtMaMH.getText().trim().length() < 4 || txtMaMH.getText().trim().length() > 25) {
                Msgbox.alert(this, "Mã mặt hàng từ 4 đến 25 ký tự!");
                this.txtMaMH.requestFocus();
                txtMaMH.setForeground(Color.red);
                return;
            }else{
                txtMaMH.setForeground(Color.white);
            }
            //validate txtTenMH
            if (txtTenMH.getText().trim().equals("")) {
                Msgbox.alert(null, "Không được để trống tên mặt hàng!");
                txtTenMH.setText("");
                txtTenMH.requestFocus();
                lblTenMH.setForeground(Color.red);
                return;
            } else {
                lblTenMH.setForeground(Color.white);
            }
            //validate RAm
            if (txtRAM.getText().trim().equals("")) {
                Msgbox.alert(null, "Không được để trống RAM!");
                txtRAM.setText("");
                txtRAM.requestFocus();
                lblRAM.setForeground(Color.red);
                return;
            } else {
                lblRAM.setForeground(Color.white);
            }
            try {
                Integer.parseInt(txtRAM.getText());
            } catch (Exception e) {
                Msgbox.alert(null, "RAM phải là số!");
                txtRAM.setText("");
                txtRAM.requestFocus();
                lblRAM.setForeground(Color.red);
                return;
            }
            lblRAM.setForeground(Color.white);
            if (Integer.parseInt(txtRAM.getText()) <= 0) {
                Msgbox.alert(null, "RAM phải lơn hơn 0!");
                txtRAM.setText("");
                txtRAM.requestFocus();
                lblRAM.setForeground(Color.red);
                return;
            } else {
                lblRAM.setForeground(Color.white);
            }
            //validate dung luong
            if (txtDungLuong.getText().trim().equals("")) {
                Msgbox.alert(null, "Không được để trống dung lượng!");
                txtDungLuong.setText("");
                txtDungLuong.requestFocus();
                lblDL.setForeground(Color.red);
                return;
            } else {
                lblDL.setForeground(Color.white);
            }
            try {
                Integer.parseInt(txtDungLuong.getText());
            } catch (Exception e) {
                Msgbox.alert(null, "Dunglượng phải là số!");
                txtDungLuong.setText("");
                txtDungLuong.requestFocus();
                lblDL.setForeground(Color.red);
                return;
            }
            lblDL.setForeground(Color.white);
            if (Integer.parseInt(txtDungLuong.getText()) <= 0) {
                Msgbox.alert(null, "Dung lượng phải lơn hơn 0!");
                txtDungLuong.setText("");
                txtDungLuong.requestFocus();
                lblDL.setForeground(Color.red);
                return;
            } else {
                lblDL.setForeground(Color.white);
            }
            //validate mau sac
            if (txtMauSac.getText().trim().equals("")) {
                Msgbox.alert(null, "Không được để trống màu sắc!");
                txtMauSac.setText("");
                txtMauSac.requestFocus();
                lblMS.setForeground(Color.red);
                return;
            } else {
                lblMS.setForeground(Color.white);
            }

            //validate so luong
//            if (txtSoLuong.getText().trim().equals("")) {
//                Msgbox.alert(null, "Không được để trống số lượng!");
//                txtSoLuong.setText("");
//                txtSoLuong.requestFocus();
//                lblSL.setForeground(Color.red);
//                return;
//            } else {
//                lblSL.setForeground(Color.white);
//            }
//            try {
//                Integer.parseInt(txtSoLuong.getText());
//            } catch (Exception e) {
//                Msgbox.alert(null, "Số lượng phải là số!");
//                txtSoLuong.setText("");
//                txtSoLuong.requestFocus();
//                lblSL.setForeground(Color.red);
//                return;
//            }
//            lblSL.setForeground(Color.white);
//            if (Integer.parseInt(txtSoLuong.getText()) <= 0) {
//                Msgbox.alert(null, "Số lượng phải lơn hơn 0!");
//                txtSoLuong.setText("");
//                txtSoLuong.requestFocus();
//                lblSL.setForeground(Color.red);
//                return;
//            } else {
//                lblSL.setForeground(Color.white);
//            }
            //validate gia mua
            if (txtGiaMua.getText().trim().equals("")) {
                Msgbox.alert(null, "Không được để trống giá mua!");
                txtGiaMua.setText("");
                txtGiaMua.requestFocus();
                lblGM.setForeground(Color.red);
                return;
            } else {
                lblGM.setForeground(Color.white);
            }
            try {
                Integer.parseInt(txtGiaMua.getText());
            } catch (Exception e) {
                Msgbox.alert(null, "Giá mua phải là số!");
                txtGiaMua.setText("");
                txtGiaMua.requestFocus();
                lblGM.setForeground(Color.red);
                return;
            }
            if (Integer.parseInt(txtGiaMua.getText()) <= 0) {
                Msgbox.alert(null, "Giá mua phải lơn hơn 0!");
                txtGiaMua.setText("");
                txtGiaMua.requestFocus();
                lblGM.setForeground(Color.red);
                return;
            } else {
                lblGM.setForeground(Color.white);
            }
            lblGM.setForeground(Color.white);
            //validate gia si
//            if (txtGiaBanSi.getText().trim().equals("")) {
//                Msgbox.alert(null, "Không được để trống giá bán sỉ!");
//                txtGiaBanSi.setText("");
//                txtGiaBanSi.requestFocus();
//                lblGBS.setForeground(Color.red);
//                return;
//            } else {
//                lblGBS.setForeground(Color.white);
//            }
//            try {
//                Integer.parseInt(txtGiaBanSi.getText());
//            } catch (Exception e) {
//                Msgbox.alert(null, "Giá bán sỉ phải là số!");
//                txtGiaBanSi.setText("");
//                txtGiaBanSi.requestFocus();
//                lblGBS.setForeground(Color.red);
//                return;
//            }
//            lblGBS.setForeground(Color.white);
//            if (Integer.parseInt(txtGiaBanSi.getText()) <= 0) {
//                Msgbox.alert(null, "Giá bán sỉ phải lơn hơn 0!");
//                txtGiaBanSi.setText("");
//                txtGiaBanSi.requestFocus();
//                lblGBS.setForeground(Color.red);
//                return;
//            } else {
//                lblGBS.setForeground(Color.white);
//            }
            //validate gia ban le
            if (txtGiaBanLe.getText().trim().equals("")) {
                Msgbox.alert(null, "Không được để trống giá bán lẻ!");
                txtGiaBanLe.setText("");
                txtGiaBanLe.requestFocus();
                lblGBL.setForeground(Color.red);
                return;
            } else {
                lblGBL.setForeground(Color.white);
            }
            try {
                Integer.parseInt(txtGiaBanLe.getText());
            } catch (Exception e) {
                Msgbox.alert(null, "Giá bán lẻ phải là số!");
                txtGiaBanLe.setText("");
                txtGiaBanLe.requestFocus();
                lblGBL.setForeground(Color.red);
                return;
            }
            lblGBL.setForeground(Color.white);
            if (Integer.parseInt(txtGiaBanLe.getText()) <= 0) {
                Msgbox.alert(null, "Giá bán lẻ phải lơn hơn 0!");
                txtGiaBanLe.setText("");
                txtGiaBanLe.requestFocus();
                lblGBL.setForeground(Color.red);
                return;
            } else {
                lblGBL.setForeground(Color.white);
            }
            //validate bao hanh
            if (txtTGBH.getText().trim().equals("")) {
                Msgbox.alert(null, "Không được để trống thời gian bảo hành!");
                txtTGBH.setText("");
                txtTGBH.requestFocus();
                lblBH.setForeground(Color.red);
                return;
            } else {
                lblBH.setForeground(Color.white);
            }
            try {
                Integer.parseInt(txtTGBH.getText());
            } catch (Exception e) {
                Msgbox.alert(null, "Thời gian bảo hành phải là số!");
                txtTGBH.setText("");
                txtTGBH.requestFocus();
                lblBH.setForeground(Color.red);
                return;
            }
            lblBH.setForeground(Color.white);
            if (Integer.parseInt(txtTGBH.getText()) <= 0) {
                Msgbox.alert(null, "Thời gian bảo hành phải lơn hơn 0!");
                txtTGBH.setText("");
                txtTGBH.requestFocus();
                lblBH.setForeground(Color.red);
                return;
            } else {
                lblBH.setForeground(Color.white);
            }
            //validate hinh mh
            if (lblHinhMH.getToolTipText() == null) {
                Msgbox.alert(null, "Bạn chưa chọn ảnh!");
                lblHMH.setForeground(Color.red);
                return;
            } else {
                lblHMH.setForeground(Color.white);
            }
            MatHangDAO dao = new MatHangDAO();
            dao.insert(this.getMatHang());
            java.util.Date now = new java.util.Date();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String nowString = sdf.format(now);
            now = sdf.parse(nowString);
            fillTable();
            for (int i = 0; i < tblList.getRowCount(); i++) {
                if (tblList.getValueAt(i, 0).toString().trim().equalsIgnoreCase(txtMaMH.getText())) {
                    index = i;
                    break;
                }
            }
            showDetail();
            Msgbox.alert(null, "Thêm thành công!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private double getGiaBan() {
        try {
            String sql = "select top 1 GIABAN from CAPNHATGIA where MAMH like ?\n"
                    + "order by NGAYCAPNHAT desc";
            ResultSet rs = JdbcHelper.query(sql, txtMaMH.getText());
            double giaBan = 0;
            while (rs.next()) {
                giaBan = rs.getDouble(1);
            }
            return giaBan;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    private void sua() {
        try {
//            this.chkSua();
            //validate txtMaMH

            //validate txtTenMH
            if (txtTenMH.getText().trim().equals("")) {
                Msgbox.alert(null, "Không được để trống tên mặt hàng!");
                txtTenMH.setText("");
                txtTenMH.requestFocus();
                lblTenMH.setForeground(Color.red);
                return;
            } else {
                lblTenMH.setForeground(Color.white);
            }
            //validate RAm
            if (txtRAM.getText().trim().equals("")) {
                Msgbox.alert(null, "Không được để trống RAM!");
                txtRAM.setText("");
                txtRAM.requestFocus();
                lblRAM.setForeground(Color.red);
                return;
            } else {
                lblRAM.setForeground(Color.white);
            }
            try {
                Integer.parseInt(txtRAM.getText());
            } catch (Exception e) {
                Msgbox.alert(null, "RAM phải là số!");
                txtRAM.setText("");
                txtRAM.requestFocus();
                lblRAM.setForeground(Color.red);
                return;
            }
            lblRAM.setForeground(Color.white);
            if (Integer.parseInt(txtRAM.getText()) <= 0) {
                Msgbox.alert(null, "RAM phải lơn hơn 0!");
                txtRAM.setText("");
                txtRAM.requestFocus();
                lblRAM.setForeground(Color.red);
                return;
            } else {
                lblRAM.setForeground(Color.white);
            }
            //validate dung luong
            if (txtDungLuong.getText().trim().equals("")) {
                Msgbox.alert(null, "Không được để trống dung lượng!");
                txtDungLuong.setText("");
                txtDungLuong.requestFocus();
                lblDL.setForeground(Color.red);
                return;
            } else {
                lblDL.setForeground(Color.white);
            }
            try {
                Integer.parseInt(txtDungLuong.getText());
            } catch (Exception e) {
                Msgbox.alert(null, "Dung lượng phải là số!");
                txtDungLuong.setText("");
                txtDungLuong.requestFocus();
                lblDL.setForeground(Color.red);
                return;
            }
            lblDL.setForeground(Color.white);
            if (Integer.parseInt(txtDungLuong.getText()) <= 0) {
                Msgbox.alert(null, "Dung lượng phải lơn hơn 0!");
                txtDungLuong.setText("");
                txtDungLuong.requestFocus();
                lblDL.setForeground(Color.red);
                return;
            } else {
                lblDL.setForeground(Color.white);
            }
            //validate mau sac
            if (txtMauSac.getText().trim().equals("")) {
                Msgbox.alert(null, "Không được để trống màu sắc!");
                txtMauSac.setText("");
                txtMauSac.requestFocus();
                lblMS.setForeground(Color.red);
                return;
            } else {
                lblMS.setForeground(Color.white);
            }

            //validate bao hanh
            if (txtTGBH.getText().trim().equals("")) {
                Msgbox.alert(null, "Không được để trống thời gian bảo hành!");
                txtTGBH.setText("");
                txtTGBH.requestFocus();
                lblBH.setForeground(Color.red);
                return;
            } else {
                lblBH.setForeground(Color.white);
            }
            try {
                Integer.parseInt(txtTGBH.getText());
            } catch (Exception e) {
                Msgbox.alert(null, "Thời gian bảo hành phải là số!");
                txtTGBH.setText("");
                txtTGBH.requestFocus();
                lblBH.setForeground(Color.red);
                return;
            }
            lblBH.setForeground(Color.white);
            if (Integer.parseInt(txtTGBH.getText()) <= 0) {
                Msgbox.alert(null, "Thời gian bảo hành phải lơn hơn 0!");
                txtTGBH.setText("");
                txtTGBH.requestFocus();
                lblBH.setForeground(Color.red);
                return;
            } else {
                lblBH.setForeground(Color.white);
            }
            //validate hinh mh
            if (lblHinhMH.getToolTipText() == null) {
                Msgbox.alert(null, "Bạn chưa chọn ảnh!");
                lblHMH.setForeground(Color.red);
                return;
            } else {
                lblHMH.setForeground(Color.white);
            }
            MatHangDAO dao = new MatHangDAO();
            dao.update(this.getMatHang());
            this.fillTable();
            this.showDetail();
            Msgbox.alert(null, "Sửa thành công!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private MatHang getMatHang() {
        HangSanXuat hsx = (HangSanXuat) cboHSX.getSelectedItem();
        QuocGia qg = (QuocGia) cboQuocGia.getSelectedItem();
        
        String giamuaString = txtGiaMua.getText();
        giamuaString = giamuaString.replace("VND", "");
        giamuaString = giamuaString.replaceAll(" ", "");
        giamuaString = giamuaString.replace(".", "");
        
        String giaBanLeString = txtGiaBanLe.getText();
        giaBanLeString = giaBanLeString.replace("VND", "");
        giaBanLeString = giaBanLeString.replace(" ", "");
        giaBanLeString = giaBanLeString.replace(".", "");
        
        MatHang mh = new MatHang(txtMaMH.getText(), hsx.getMaHSX(), txtTenMH.getText(),
                Integer.parseInt(txtRAM.getText()), Integer.parseInt(txtDungLuong.getText()),
                txtMauSac.getText(), qg.getMaQG(),
                lblHinhMH.getToolTipText(), 0,
                Integer.parseInt(txtTGBH.getText()), Double.parseDouble(giamuaString),
                Double.parseDouble(giaBanLeString),
                rdoTrangThai1.isSelected());
        return mh;
    }

    private void chonAnh() {
        try {
            JFileChooser fileChooser = new JFileChooser();

            fileChooser.setAcceptAllFileFilterUsed(false);
            FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images", "jpg", "gif", "png", "jpeg");
            fileChooser.addChoosableFileFilter(filter);

            if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();

                XImage.save(file);

                ImageIcon icon = XImage.read(file.getName());
                Image image = icon.getImage(); // transform it 
                Image newimg = image.getScaledInstance(lblHinhMH.getWidth(), lblHinhMH.getHeight(), java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
                icon = new ImageIcon(newimg);  // transform it back
                lblHinhMH.setIcon(icon);
                lblHinhMH.setToolTipText(file.getName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void clear() {
        try {
            if (Msgbox.confirm(null, "Bạn có muốn tạo mới không?")) {
                txtMaMH.setText("");
                txtTenMH.setText("");
                txtRAM.setText("");
                txtDungLuong.setText("");
                txtMauSac.setText("");
                txtGiaMua.setText("");
//                txtGiaBanSi.setText("");
                txtGiaBanLe.setText("");
                txtTGBH.setText("");
                lblHinhMH.setToolTipText(null);
                lblHinhMH.setIcon(null);
                cboHSX.setSelectedIndex(0);
                cboQuocGia.setSelectedIndex(0);
                index = -1;
                rdoTrangThai1.setSelected(true);
                tblList.setRowSelectionAllowed(false);
                showBtnThem();
                lblBH.setForeground(Color.white);
                lblDL.setForeground(Color.white);
                lblGM.setForeground(Color.white);
                lblGBL.setForeground(Color.white);
//                lblGBS.setForeground(Color.white);
                lblHMH.setForeground(Color.white);
                lblHSX.setForeground(Color.white);
                lblMS.setForeground(Color.white);
                lblMaMH.setForeground(Color.white);
                lblQG.setForeground(Color.white);
                lblRAM.setForeground(Color.white);
                lblTenMH.setForeground(Color.white);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showDetail() {
        try {
            txtMaMH.setText(tblList.getValueAt(index, 0).toString());
            txtTenMH.setText(tblList.getValueAt(index, 2).toString());
            
            String ram, dl;
            ram = tblList.getValueAt(index, 3).toString();
            dl = tblList.getValueAt(index, 4).toString();
            ram = ram.substring(0, ram.length() - 2);
            dl = dl.substring(0, dl.length() - 2);
            
            txtRAM.setText(ram);
            txtDungLuong.setText(dl);
            txtMauSac.setText(tblList.getValueAt(index, 5).toString());
            txtGiaMua.setText(tblList.getValueAt(index, 10).toString());
            txtTGBH.setText(tblList.getValueAt(index, 9).toString());
            lblHinhMH.setToolTipText(tblList.getValueAt(index, 7).toString());
//            txtGiaBanSi.setText(tblList.getValueAt(index, 11).toString());
            txtGiaBanLe.setText(tblList.getValueAt(index, 11).toString());
//            Icon ic = XImage.read(lblHinhMH.getToolTipText());
            ImageIcon icon = XImage.read(lblHinhMH.getToolTipText());
            Image image = icon.getImage(); // transform it 
            Image newimg = image.getScaledInstance(lblHinhMH.getWidth(), lblHinhMH.getHeight(), java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
            icon = new ImageIcon(newimg);  // transform it back
            lblHinhMH.setIcon(icon);
            this.setSelectedComboboxHSX(tblList.getValueAt(index, 1).toString(), cboHSX);
            this.setSelectedComboboxQG(tblList.getValueAt(index, 6).toString(), cboQuocGia);
            hideBtnThem();

            if (this.getTrangThaiMH(tblList.getValueAt(index, 12).toString())) {
                rdoTrangThai1.setSelected(true);
            } else {
                rdoTrangThai0.setSelected(true);
            }
            tblList.setRowSelectionAllowed(true);
            tblList.setRowSelectionInterval(index, index);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void setSelectedComboboxQG(String cbbselected, JComboBox cbb) {
        for (int i = 0; i < cbb.getItemCount(); i++) {
            QuocGia m = (QuocGia) cbb.getItemAt(i);
            if (m != null) {
                if (cbbselected.trim().equals(m.getTenQG())) {
                    cbb.setSelectedItem(m);
                }
            }
        }
    }

    public void setSelectedComboboxHSX(String cbbselected, JComboBox cbb) {
        for (int i = 0; i < cbb.getItemCount(); i++) {
            HangSanXuat m = (HangSanXuat) cbb.getItemAt(i);
            if (m != null) {
                if (cbbselected.trim().equals(m.getTenHSX())) {
                    cbb.setSelectedItem(m);
                }
            }
        }
    }

    private String getDlRAM(int DLorRAM) {
        return DLorRAM + "GB";
    }

    private boolean getTrangThaiMH(String trangThaiString) {
        if (trangThaiString.trim().equalsIgnoreCase("Đang kinh doanh")) {
            return true;
        } else {
            return false;
        }
    }

    private String getTrangThaiMH(boolean trangThai) {
        if (trangThai) {
            return "Đang kinh doanh";
        } else {
            return "Ngừng kinh doanh";
        }
    }

    private void searchMH() {
        try {
            String sql = "select MAMH,TENHSX,TENMH,RAM,DUNGLUONG,MAUSAC,TENQG,HINHMH,\n"
                    + "SOLUONG,TGBH, GIAMUA,GIABAN,TRANGTHAI\n"
                    + "from MATHANG join HANGSANXUAT on MATHANG.MAHSX=HANGSANXUAT.MAHSX\n"
                    + "join QUOCGIA on MATHANG.MAQG= QUOCGIA.MAQG\n"
                    + "where MAMH like ? or TENMH like ? or TENHSX like ?";
            String search = "%" + txtSearch.getText() + "%";
            ResultSet rs = JdbcHelper.query(sql, search, search, search);
            String giaMua,  giaBanLe;
            modelTbl.setRowCount(0);
            while (rs.next()) {
                giaMua = rs.getString(11);
//                giaBanSi = rs.getString(12);
                giaBanLe = rs.getString(12);
                int gm = giaMua.indexOf(".");
//                int gbs = giaBanSi.indexOf(".");
                int gbl = giaBanLe.indexOf(".");
                giaMua = giaMua.substring(0, gm);
//                giaBanSi = giaBanSi.substring(0, gbs);
                giaBanLe = giaBanLe.substring(0, gbl);
                modelTbl.addRow(new Object[]{
                    rs.getString(1), rs.getString(2), rs.getString(3),
                    this.getDlRAM(rs.getInt(4)), this.getDlRAM(rs.getInt(5)), rs.getString(6),
                    rs.getString(7), rs.getString(8), rs.getInt(9),
                    rs.getInt(10), giaMua, 
                    giaBanLe, this.getTrangThaiMH(rs.getBoolean(13))});
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void fillTable() {
        try {
            String sql = "select MAMH,TENHSX,TENMH,RAM,DUNGLUONG,MAUSAC,TENQG,HINHMH,SOLUONG,TGBH,\n"
                    + "                    GIAMUA,GIABAN,TRANGTHAI\n"
                    + "                    from MATHANG join HANGSANXUAT on MATHANG.MAHSX=HANGSANXUAT.MAHSX\n"
                    + "                    join QUOCGIA on MATHANG.MAQG= QUOCGIA.MAQG";
            ResultSet rs = JdbcHelper.query(sql);
            String giaMua,  giaBanLe;
            modelTbl.setRowCount(0);
            while (rs.next()) {
                giaMua = rs.getString(11);
                int gm = giaMua.indexOf(".");
                giaMua = giaMua.substring(0, gm);
                giaMua = StringToPrice.getPrice(giaMua);
                
                giaBanLe = rs.getString(12);
                int gb = giaBanLe.indexOf(".");
                giaBanLe = giaBanLe.substring(0, gb);
                giaBanLe = StringToPrice.getPrice(giaBanLe);
                
                modelTbl.addRow(new Object[]{
                    rs.getString(1), rs.getString(2), rs.getString(3),
                    this.getDlRAM(rs.getInt(4)), this.getDlRAM(rs.getInt(5)), rs.getString(6),
                    rs.getString(7), rs.getString(8), rs.getInt(9),
                    rs.getInt(10), giaMua, 
                    giaBanLe, this.getTrangThaiMH(rs.getBoolean(13))});
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void fillCboHSX() {
        try {
            HangSanXuatDao daohsx = new HangSanXuatDao();
            List<HangSanXuat> lstHSX = daohsx.selectAll();
            modelCboHSX.removeAllElements();
            for (HangSanXuat s : lstHSX) {
                modelCboHSX.addElement(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void fillCboQG() {
        try {
            List<QuocGia> lstQG = new ArrayList<>();
            String sql = "select * from QUOCGIA";
            ResultSet rs = JdbcHelper.query(sql);
            modelCboQG.removeAllElements();
            while (rs.next()) {
                QuocGia qg = new QuocGia(rs.getString(1), rs.getString(2));
                lstQG.add(qg);
            }

            for (QuocGia s : lstQG) {
                modelCboQG.addElement(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grpStatus = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblList = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        txtMaMH = new javax.swing.JTextField();
        lblMaMH = new javax.swing.JLabel();
        lblHSX = new javax.swing.JLabel();
        cboHSX = new javax.swing.JComboBox<>();
        btnThemHSX = new javax.swing.JButton();
        lblTenMH = new javax.swing.JLabel();
        txtTenMH = new javax.swing.JTextField();
        txtRAM = new javax.swing.JTextField();
        txtDungLuong = new javax.swing.JTextField();
        txtMauSac = new javax.swing.JTextField();
        cboQuocGia = new javax.swing.JComboBox<>();
        btnThemQG = new javax.swing.JButton();
        txtGiaMua = new javax.swing.JTextField();
        txtTGBH = new javax.swing.JTextField();
        lblRAM = new javax.swing.JLabel();
        lblDL = new javax.swing.JLabel();
        lblMS = new javax.swing.JLabel();
        lblQG = new javax.swing.JLabel();
        lblGM = new javax.swing.JLabel();
        lblBH = new javax.swing.JLabel();
        txtGiaBanLe = new javax.swing.JTextField();
        lblGBL = new javax.swing.JLabel();
        lblHinhMH = new javax.swing.JLabel();
        lblHMH = new javax.swing.JLabel();
        btnClear = new javax.swing.JButton();
        btnThemMH = new javax.swing.JButton();
        btnSuaMH = new javax.swing.JButton();
        lblThang = new javax.swing.JLabel();
        lblTrangThai = new javax.swing.JLabel();
        rdoTrangThai1 = new javax.swing.JRadioButton();
        rdoTrangThai0 = new javax.swing.JRadioButton();

        setBackground(new java.awt.Color(34, 116, 173));

        jLabel1.setFont(new java.awt.Font("Baloo 2 ExtraBold", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("DANH SÁCH MẶT HÀNG");

        tblList.setBackground(new java.awt.Color(34, 116, 173));
        tblList.setFont(new java.awt.Font("Baloo 2", 0, 13)); // NOI18N
        tblList.setForeground(new java.awt.Color(255, 255, 255));
        tblList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã MH", "HSX", "Tên MH", "RAM", "Dung lượng", "Màu", "Quốc gia", "Hình", "Số Lượng", "TGBH", "Giá Mua", "Giá bán lẻ", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblList.setToolTipText("");
        tblList.setGridColor(new java.awt.Color(255, 255, 255));
        tblList.setRowHeight(25);
        tblList.setSelectionBackground(new java.awt.Color(51, 51, 51));
        tblList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblList.setShowGrid(true);
        tblList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblListMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblList);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mobilez/icon/search_32px.png"))); // NOI18N

        txtSearch.setBackground(new java.awt.Color(34, 116, 173));
        txtSearch.setForeground(new java.awt.Color(255, 255, 255));
        txtSearch.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)));
        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Baloo 2 ExtraBold", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("CẬP NHẬT");

        txtMaMH.setBackground(new java.awt.Color(34, 116, 173));
        txtMaMH.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtMaMH.setForeground(new java.awt.Color(255, 255, 255));
        txtMaMH.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)));
        txtMaMH.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDungLuongKeyPressed(evt);
            }
        });

        lblMaMH.setFont(new java.awt.Font("Baloo Chettan 2", 1, 14)); // NOI18N
        lblMaMH.setForeground(new java.awt.Color(255, 255, 255));
        lblMaMH.setText("Mã mặt hàng");

        lblHSX.setFont(new java.awt.Font("Baloo Chettan 2", 1, 14)); // NOI18N
        lblHSX.setForeground(new java.awt.Color(255, 255, 255));
        lblHSX.setText("Hãng sản xuất");

        cboHSX.setBackground(new java.awt.Color(34, 116, 173));
        cboHSX.setForeground(new java.awt.Color(255, 255, 255));

        btnThemHSX.setBackground(new java.awt.Color(34, 116, 173));
        btnThemHSX.setFont(new java.awt.Font("Baloo 2", 1, 12)); // NOI18N
        btnThemHSX.setForeground(new java.awt.Color(255, 255, 255));
        btnThemHSX.setText("Thêm HSX");
        btnThemHSX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemHSXActionPerformed(evt);
            }
        });

        lblTenMH.setFont(new java.awt.Font("Baloo Chettan 2", 1, 14)); // NOI18N
        lblTenMH.setForeground(new java.awt.Color(255, 255, 255));
        lblTenMH.setText("Tên mặt hàng");

        txtTenMH.setBackground(new java.awt.Color(34, 116, 173));
        txtTenMH.setForeground(new java.awt.Color(255, 255, 255));
        txtTenMH.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)));
        txtTenMH.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDungLuongKeyPressed(evt);
            }
        });

        txtRAM.setBackground(new java.awt.Color(34, 116, 173));
        txtRAM.setForeground(new java.awt.Color(255, 255, 255));
        txtRAM.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)));
        txtRAM.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDungLuongKeyPressed(evt);
            }
        });

        txtDungLuong.setBackground(new java.awt.Color(34, 116, 173));
        txtDungLuong.setForeground(new java.awt.Color(255, 255, 255));
        txtDungLuong.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)));
        txtDungLuong.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDungLuongKeyPressed(evt);
            }
        });

        txtMauSac.setBackground(new java.awt.Color(34, 116, 173));
        txtMauSac.setForeground(new java.awt.Color(255, 255, 255));
        txtMauSac.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)));
        txtMauSac.setCaretColor(new java.awt.Color(255, 255, 255));
        txtMauSac.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDungLuongKeyPressed(evt);
            }
        });

        cboQuocGia.setBackground(new java.awt.Color(34, 116, 173));
        cboQuocGia.setForeground(new java.awt.Color(255, 255, 255));

        btnThemQG.setBackground(new java.awt.Color(34, 116, 173));
        btnThemQG.setFont(new java.awt.Font("Baloo 2", 1, 12)); // NOI18N
        btnThemQG.setForeground(new java.awt.Color(255, 255, 255));
        btnThemQG.setText("Thêm QG");
        btnThemQG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemQGActionPerformed(evt);
            }
        });

        txtGiaMua.setBackground(new java.awt.Color(34, 116, 173));
        txtGiaMua.setForeground(new java.awt.Color(255, 255, 255));
        txtGiaMua.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)));
        txtGiaMua.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDungLuongKeyPressed(evt);
            }
        });

        txtTGBH.setBackground(new java.awt.Color(34, 116, 173));
        txtTGBH.setForeground(new java.awt.Color(255, 255, 255));
        txtTGBH.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)));
        txtTGBH.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTGBHKeyPressed(evt);
            }
        });

        lblRAM.setFont(new java.awt.Font("Baloo Chettan 2", 1, 14)); // NOI18N
        lblRAM.setForeground(new java.awt.Color(255, 255, 255));
        lblRAM.setText("RAM (GB)");

        lblDL.setFont(new java.awt.Font("Baloo Chettan 2", 1, 14)); // NOI18N
        lblDL.setForeground(new java.awt.Color(255, 255, 255));
        lblDL.setText("ROM (GB)");

        lblMS.setFont(new java.awt.Font("Baloo Chettan 2", 1, 14)); // NOI18N
        lblMS.setForeground(new java.awt.Color(255, 255, 255));
        lblMS.setText("Màu sắc");

        lblQG.setFont(new java.awt.Font("Baloo Chettan 2", 1, 14)); // NOI18N
        lblQG.setForeground(new java.awt.Color(255, 255, 255));
        lblQG.setText("Quốc gia");

        lblGM.setFont(new java.awt.Font("Baloo Chettan 2", 1, 14)); // NOI18N
        lblGM.setForeground(new java.awt.Color(255, 255, 255));
        lblGM.setText("Giá mua");

        lblBH.setFont(new java.awt.Font("Baloo Chettan 2", 1, 14)); // NOI18N
        lblBH.setForeground(new java.awt.Color(255, 255, 255));
        lblBH.setText("Bảo hành");

        txtGiaBanLe.setBackground(new java.awt.Color(34, 116, 173));
        txtGiaBanLe.setForeground(new java.awt.Color(255, 255, 255));
        txtGiaBanLe.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)));
        txtGiaBanLe.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDungLuongKeyPressed(evt);
            }
        });

        lblGBL.setFont(new java.awt.Font("Baloo Chettan 2", 1, 14)); // NOI18N
        lblGBL.setForeground(new java.awt.Color(255, 255, 255));
        lblGBL.setText("Giá bán");

        lblHinhMH.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHinhMH.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)));
        lblHinhMH.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblHinhMHMouseClicked(evt);
            }
        });

        lblHMH.setFont(new java.awt.Font("Baloo Chettan 2", 1, 14)); // NOI18N
        lblHMH.setForeground(new java.awt.Color(255, 255, 255));
        lblHMH.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHMH.setText("Hình mặt hàng");

        btnClear.setBackground(new java.awt.Color(34, 116, 173));
        btnClear.setFont(new java.awt.Font("Baloo 2", 1, 12)); // NOI18N
        btnClear.setForeground(new java.awt.Color(255, 255, 255));
        btnClear.setText("Tạo mới");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        btnThemMH.setBackground(new java.awt.Color(34, 116, 173));
        btnThemMH.setFont(new java.awt.Font("Baloo 2", 1, 12)); // NOI18N
        btnThemMH.setForeground(new java.awt.Color(255, 255, 255));
        btnThemMH.setText("Thêm");
        btnThemMH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemMHActionPerformed(evt);
            }
        });

        btnSuaMH.setBackground(new java.awt.Color(34, 116, 173));
        btnSuaMH.setFont(new java.awt.Font("Baloo 2", 1, 12)); // NOI18N
        btnSuaMH.setForeground(new java.awt.Color(255, 255, 255));
        btnSuaMH.setText("Sửa");
        btnSuaMH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaMHActionPerformed(evt);
            }
        });

        lblThang.setFont(new java.awt.Font("Baloo Chettan 2", 1, 14)); // NOI18N
        lblThang.setForeground(new java.awt.Color(255, 255, 255));
        lblThang.setText("(Tháng)");

        lblTrangThai.setFont(new java.awt.Font("Baloo Chettan 2", 1, 14)); // NOI18N
        lblTrangThai.setForeground(new java.awt.Color(255, 255, 255));
        lblTrangThai.setText("Trạng thái");

        grpStatus.add(rdoTrangThai1);
        rdoTrangThai1.setFont(new java.awt.Font("Baloo 2 ExtraBold", 1, 12)); // NOI18N
        rdoTrangThai1.setForeground(new java.awt.Color(255, 255, 255));
        rdoTrangThai1.setSelected(true);
        rdoTrangThai1.setText("Đang kinh doanh");

        grpStatus.add(rdoTrangThai0);
        rdoTrangThai0.setFont(new java.awt.Font("Baloo 2 ExtraBold", 1, 12)); // NOI18N
        rdoTrangThai0.setForeground(new java.awt.Color(255, 255, 255));
        rdoTrangThai0.setText("Ngừng kinh doanh");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 646, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                        .addComponent(lblTrangThai, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(lblBH, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addComponent(lblGBL))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addComponent(txtTGBH, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addComponent(lblThang))
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addComponent(rdoTrangThai1)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(rdoTrangThai0))))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(104, 104, 104)
                                                .addComponent(btnThemMH)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(btnSuaMH)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(btnClear)))
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(lblHSX, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(lblMaMH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(cboHSX, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(btnThemHSX))
                                            .addComponent(txtMaMH)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblTenMH, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lblRAM))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(txtTenMH))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(12, 12, 12)
                                                .addComponent(txtRAM, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                                                .addComponent(lblDL)
                                                .addGap(18, 18, 18)
                                                .addComponent(txtDungLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(1, 1, 1))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(lblMS, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(lblQG, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtMauSac)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(cboQuocGia, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(btnThemQG))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblGM)
                                        .addGap(51, 51, 51)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtGiaBanLe)
                                            .addComponent(txtGiaMua))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblHinhMH, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblHMH, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnClear, btnSuaMH, btnThemMH});

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnThemHSX, btnThemQG});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMaMH, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblMaMH))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblHSX)
                                .addComponent(cboHSX, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnThemHSX, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblTenMH)
                            .addComponent(txtTenMH, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtRAM, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblRAM)
                            .addComponent(lblDL)
                            .addComponent(txtDungLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblMS))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cboQuocGia, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblQG)
                            .addComponent(btnThemQG))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblGM)
                            .addComponent(txtGiaMua, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtGiaBanLe, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblGBL))
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTGBH, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblBH)
                            .addComponent(lblThang))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblTrangThai)
                            .addComponent(rdoTrangThai1)
                            .addComponent(rdoTrangThai0))
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnThemMH)
                            .addComponent(btnSuaMH)
                            .addComponent(btnClear)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(134, 134, 134)
                        .addComponent(lblHinhMH, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(lblHMH))))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnThemHSX, btnThemQG});

    }// </editor-fold>//GEN-END:initComponents

    private void btnThemHSXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemHSXActionPerformed
        // TODO add your handling code here:
        new JDialogHSX(null, true).setVisible(true);
        this.fillCboHSX();

    }//GEN-LAST:event_btnThemHSXActionPerformed

    private void btnThemQGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemQGActionPerformed
        // TODO add your handling code here:
        new JDialogQuocGia(null, true).setVisible(true);
        this.fillCboQG();
    }//GEN-LAST:event_btnThemQGActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        // TODO add your handling code here:
        this.clear();
    }//GEN-LAST:event_btnClearActionPerformed

    private void tblListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblListMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 2) {
            index = tblList.getSelectedRow();
            this.showDetail();
        }


    }//GEN-LAST:event_tblListMouseClicked

    private void lblHinhMHMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHinhMHMouseClicked
        // TODO add your handling code here:
        this.chonAnh();
    }//GEN-LAST:event_lblHinhMHMouseClicked

    private void btnThemMHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemMHActionPerformed
        // TODO add your handling code here:
        this.them();
    }//GEN-LAST:event_btnThemMHActionPerformed

    private void btnSuaMHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaMHActionPerformed
        // TODO add your handling code here:
        this.sua();
    }//GEN-LAST:event_btnSuaMHActionPerformed

    private void txtTGBHKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTGBHKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            this.them();
        }
    }//GEN-LAST:event_txtTGBHKeyPressed

    private void txtDungLuongKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDungLuongKeyPressed
        // TODO add your handling code here:
        searchMH();
    }//GEN-LAST:event_txtDungLuongKeyPressed

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        // TODO add your handling code here:
        searchMH();
    }//GEN-LAST:event_txtSearchKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnSuaMH;
    private javax.swing.JButton btnThemHSX;
    private javax.swing.JButton btnThemMH;
    private javax.swing.JButton btnThemQG;
    private javax.swing.JComboBox<String> cboHSX;
    private javax.swing.JComboBox<String> cboQuocGia;
    private javax.swing.ButtonGroup grpStatus;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblBH;
    private javax.swing.JLabel lblDL;
    private javax.swing.JLabel lblGBL;
    private javax.swing.JLabel lblGM;
    private javax.swing.JLabel lblHMH;
    private javax.swing.JLabel lblHSX;
    private javax.swing.JLabel lblHinhMH;
    private javax.swing.JLabel lblMS;
    private javax.swing.JLabel lblMaMH;
    private javax.swing.JLabel lblQG;
    private javax.swing.JLabel lblRAM;
    private javax.swing.JLabel lblTenMH;
    private javax.swing.JLabel lblThang;
    private javax.swing.JLabel lblTrangThai;
    private javax.swing.JRadioButton rdoTrangThai0;
    private javax.swing.JRadioButton rdoTrangThai1;
    private javax.swing.JTable tblList;
    private javax.swing.JTextField txtDungLuong;
    private javax.swing.JTextField txtGiaBanLe;
    private javax.swing.JTextField txtGiaMua;
    private javax.swing.JTextField txtMaMH;
    private javax.swing.JTextField txtMauSac;
    private javax.swing.JTextField txtRAM;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtTGBH;
    private javax.swing.JTextField txtTenMH;
    // End of variables declaration//GEN-END:variables
}
