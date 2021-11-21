/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mobilez.ui;

import com.mobilez.dao.CapNhatGiaDAO;
import com.mobilez.dao.HangSanXuatDao;
import com.mobilez.dao.MatHangDAO;
import com.mobilez.models.CapNhatGia;
import com.mobilez.models.HangSanXuat;
import com.mobilez.models.MatHang;
import com.mobilez.models.NhanVien;
import com.mobilez.models.QuocGia;
import com.mobilez.utils.JdbcHelper;
import com.mobilez.utils.Msgbox;
import com.mobilez.utils.XImage;
import java.awt.Color;
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
        fillCboHSX();
        fillCboQG();
        fillTable();
        showBtnThem();
    }
    //My method
    
    private void chkThem(){
        //validate txtMaMH
        if (txtMaMH.getText().trim().equals("")) {
            Msgbox.alert(null, "Không được để trống mã mặt hàng!");
            txtMaMH.setText("");
            txtMaMH.requestFocus();
            lblMaMH.setForeground(Color.red);
            return;
        }else{
            lblMaMH.setForeground(Color.white);
        }
        //validate txtTenMH
        if (txtTenMH.getText().trim().equals("")) {
            Msgbox.alert(null, "Không được để trống tên mặt hàng!");
            txtTenMH.setText("");
            txtTenMH.requestFocus();
            lblTenMH.setForeground(Color.red);
            return;
        }else{
            lblTenMH.setForeground(Color.white);
        }
        //validate RAm
        if (txtRAM.getText().trim().equals("")) {
            Msgbox.alert(null, "Không được để trống RAM!");
            txtRAM.setText("");
            txtRAM.requestFocus();
            lblRAM.setForeground(Color.red);
            return;
        }else{
            lblRAM.setForeground(Color.white);
        }
        //validate dung luong
        if (txtDungLuong.getText().trim().equals("")) {
            Msgbox.alert(null, "Không được để trống dung lượng!");
            txtDungLuong.setText("");
            txtDungLuong.requestFocus();
            lblDL.setForeground(Color.red);
            return;
        }else{
            lblDL.setForeground(Color.white);
        }
        //validate mau sac
        if (txtDungLuong.getText().trim().equals("")) {
            Msgbox.alert(null, "Không được để trống màu sắc!");
            txtDungLuong.setText("");
            txtDungLuong.requestFocus();
            lblDL.setForeground(Color.red);
            return;
        }else{
            lblDL.setForeground(Color.white);
        }
        //validate don vi
        if (txtDVT.getText().trim().equals("")) {
            Msgbox.alert(null, "Không được để trống màu sắc!");
            txtDVT.setText("");
            txtDVT.requestFocus();
            lblDVT.setForeground(Color.red);
            return;
        }else{
            lblDVT.setForeground(Color.white);
        }
        //validate so luong
        if (txtSoLuong.getText().trim().equals("")) {
            Msgbox.alert(null, "Không được để trống số lượng!");
            txtSoLuong.setText("");
            txtSoLuong.requestFocus();
            lblSL.setForeground(Color.red);
            return;
        }else{
            lblSL.setForeground(Color.white);
        }
        try {
            Integer.parseInt(txtSoLuong.getText());
        } catch (Exception e) {
            Msgbox.alert(null, "Số lượng phải là số!");
            txtSoLuong.setText("");
            txtSoLuong.requestFocus();
            lblSL.setForeground(Color.red);
            return;
        }
        lblSL.setForeground(Color.white);
        if (Integer.parseInt(txtSoLuong.getText())<=0) {
            Msgbox.alert(null, "Số lượng phải lơn hơn 0!");
            txtSoLuong.setText("");
            txtSoLuong.requestFocus();
            lblSL.setForeground(Color.red);
            return;
        }else{
            lblSL.setForeground(Color.white);
        }
        //validate bao hanh
        if (txtBaoHanh.getText().trim().equals("")) {
            Msgbox.alert(null, "Không được để trống thời gian bảo hành!");
            txtBaoHanh.setText("");
            txtBaoHanh.requestFocus();
            lblBH.setForeground(Color.red);
            return;
        }else{
            lblBH.setForeground(Color.white);
        }
        try {
            Integer.parseInt(txtBaoHanh.getText());
        } catch (Exception e) {
            Msgbox.alert(null, "Thời gian bảo hành phải là số!");
            txtBaoHanh.setText("");
            txtBaoHanh.requestFocus();
            lblBH.setForeground(Color.red);
            return;
        }
        lblBH.setForeground(Color.white);
        if (Integer.parseInt(txtBaoHanh.getText())<=0) {
            Msgbox.alert(null, "Thời gian bảo hành phải lơn hơn 0!");
            txtBaoHanh.setText("");
            txtBaoHanh.requestFocus();
            lblBH.setForeground(Color.red);
            return;
        }else{
            lblBH.setForeground(Color.white);
        }
        //validate gia mua
        if (txtGiaMua.getText().trim().equals("")) {
            Msgbox.alert(null, "Không được để trống giá mua!");
            txtGiaMua.setText("");
            txtGiaMua.requestFocus();
            lblGM.setForeground(Color.red);
            return;
        }else{
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
        lblGM.setForeground(Color.white);
        if (Integer.parseInt(txtGiaMua.getText())<=0) {
            Msgbox.alert(null, "Giá mua phải lơn hơn 0!");
            txtGiaMua.setText("");
            txtGiaMua.requestFocus();
            lblGM.setForeground(Color.red);
            return;
        }else{
            lblGM.setForeground(Color.white);
        }
        //validate gia ban
        if (txtGiaBan.getText().trim().equals("")) {
            Msgbox.alert(null, "Không được để trống giá mua!");
            txtGiaBan.setText("");
            txtGiaBan.requestFocus();
            lblGB.setForeground(Color.red);
            return;
        }else{
            lblGB.setForeground(Color.white);
        }
        try {
            Integer.parseInt(txtGiaBan.getText());
        } catch (Exception e) {
            Msgbox.alert(null, "Giá mua phải là số!");
            txtGiaBan.setText("");
            txtGiaBan.requestFocus();
            lblGB.setForeground(Color.red);
            return;
        }
        lblGB.setForeground(Color.white);
        if (Integer.parseInt(txtGiaBan.getText())<=0) {
            Msgbox.alert(null, "Giá mua phải lơn hơn 0!");
            txtGiaBan.setText("");
            txtGiaBan.requestFocus();
            lblGB.setForeground(Color.red);
            return;
        }else{
            lblGB.setForeground(Color.white);
        }
        //validate hinh mh
        if (lblHinhMH.getToolTipText().trim().equals("")) {
            Msgbox.alert(null, "Bạn chưa chọn ảnh!");
            lblHMH.setForeground(Color.red);
            return;
        }else{
            lblHMH.setForeground(Color.white);
        }
    }
    
    private void chkSua(){
        
        //validate txtTenMH
        if (txtTenMH.getText().trim().equals("")) {
            Msgbox.alert(null, "Không được để trống tên mặt hàng!");
            txtTenMH.setText("");
            txtTenMH.requestFocus();
            lblTenMH.setForeground(Color.red);
            return;
        }else{
            lblTenMH.setForeground(Color.white);
        }
        //validate RAm
        if (txtRAM.getText().trim().equals("")) {
            Msgbox.alert(null, "Không được để trống RAM!");
            txtRAM.setText("");
            txtRAM.requestFocus();
            lblRAM.setForeground(Color.red);
            return;
        }else{
            lblRAM.setForeground(Color.white);
        }
        //validate dung luong
        if (txtDungLuong.getText().trim().equals("")) {
            Msgbox.alert(null, "Không được để trống dung lượng!");
            txtDungLuong.setText("");
            txtDungLuong.requestFocus();
            lblDL.setForeground(Color.red);
            return;
        }else{
            lblDL.setForeground(Color.white);
        }
        //validate mau sac
        if (txtDungLuong.getText().trim().equals("")) {
            Msgbox.alert(null, "Không được để trống màu sắc!");
            txtDungLuong.setText("");
            txtDungLuong.requestFocus();
            lblDL.setForeground(Color.red);
            return;
        }else{
            lblDL.setForeground(Color.white);
        }
        //validate don vi
        if (txtDVT.getText().trim().equals("")) {
            Msgbox.alert(null, "Không được để trống màu sắc!");
            txtDVT.setText("");
            txtDVT.requestFocus();
            lblDVT.setForeground(Color.red);
            return;
        }else{
            lblDVT.setForeground(Color.white);
        }
        
        //validate bao hanh
        if (txtBaoHanh.getText().trim().equals("")) {
            Msgbox.alert(null, "Không được để trống thời gian bảo hành!");
            txtBaoHanh.setText("");
            txtBaoHanh.requestFocus();
            lblBH.setForeground(Color.red);
            return;
        }else{
            lblBH.setForeground(Color.white);
        }
        try {
            Integer.parseInt(txtBaoHanh.getText());
        } catch (Exception e) {
            Msgbox.alert(null, "Thời gian bảo hành phải là số!");
            txtBaoHanh.setText("");
            txtBaoHanh.requestFocus();
            lblBH.setForeground(Color.red);
            return;
        }
        lblBH.setForeground(Color.white);
        if (Integer.parseInt(txtBaoHanh.getText())<=0) {
            Msgbox.alert(null, "Thời gian bảo hành phải lơn hơn 0!");
            txtBaoHanh.setText("");
            txtBaoHanh.requestFocus();
            lblBH.setForeground(Color.red);
            return;
        }else{
            lblBH.setForeground(Color.white);
        }
        
        //validate hinh mh
        if (lblHinhMH.getToolTipText().trim().equals("")) {
            Msgbox.alert(null, "Bạn chưa chọn ảnh!");
            lblHMH.setForeground(Color.red);
            return;
        }else{
            lblHMH.setForeground(Color.white);
        }
    }
    
    private void showBtnThem(){
        btnThemMH.setEnabled(true);
        btnSuaMH.setEnabled(false);
//        btnXoaMH.setEnabled(false);
        txtMaMH.setEditable(true);
        txtGiaMua.setEditable(true);
        txtGiaBan.setEditable(true);
        txtSoLuong.setEditable(true);
        txtMaMH.setForeground(Color.WHITE);
        txtGiaMua.setForeground(Color.WHITE);
        txtGiaBan.setForeground(Color.WHITE);
        txtSoLuong.setForeground(Color.WHITE);
    }
    
    private void hideBtnThem(){
        btnThemMH.setEnabled(false);
        btnSuaMH.setEnabled(true);
//        btnXoaMH.setEnabled(true);
        txtMaMH.setEditable(false);
        txtGiaMua.setEditable(false);
        txtGiaBan.setEditable(false);
        txtSoLuong.setEditable(false);
        txtMaMH.setForeground(Color.ORANGE);
        txtGiaMua.setForeground(Color.ORANGE);
        txtGiaBan.setForeground(Color.ORANGE);
        txtSoLuong.setForeground(Color.ORANGE);
    }
    
    private void them() {
        try {
            chkThem();
            MatHangDAO dao = new MatHangDAO();
            dao.insert(this.getMatHang());
            java.util.Date now = new java.util.Date();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String nowString = sdf.format(now);
            now = sdf.parse(nowString);
            CapNhatGia cng = new CapNhatGia(now, txtMaMH.getText(), Double.parseDouble(txtGiaMua.getText()), Double.parseDouble(txtGiaBan.getText()));
            CapNhatGiaDAO cngDAO = new CapNhatGiaDAO();
            cngDAO.insert(cng);
            fillTable();
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
            MatHangDAO dao = new MatHangDAO();
            dao.update(this.getMatHang());
            fillTable();
            Msgbox.alert(null, "Sửa thành công!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    
    private MatHang getMatHang() {
        HangSanXuat hsx = (HangSanXuat) cboHSX.getSelectedItem();
        QuocGia qg = (QuocGia) cboQuocGia.getSelectedItem();
        MatHang mh = new MatHang(txtMaMH.getText(), hsx.getMaHSX(), txtTenMH.getText(),
                txtRAM.getText(), txtDungLuong.getText(), txtMauSac.getText(), qg.getMaQG(),
                lblHinhMH.getToolTipText(), txtDVT.getText(), Integer.parseInt(txtSoLuong.getText()),
                txtBaoHanh.getText());
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
                txtDVT.setText("");
                txtSoLuong.setText("");
                txtGiaMua.setText("");
                txtGiaBan.setText("");
                txtBaoHanh.setText("");
                lblHinhMH.setToolTipText("");
                cboHSX.setSelectedIndex(0);
                cboQuocGia.setSelectedIndex(0);
                index = -1;
                tblList.setRowSelectionAllowed(false);
                showBtnThem();
                lblBH.setForeground(Color.white);
                lblDL.setForeground(Color.white);
                lblDVT.setForeground(Color.white);
                lblGB.setForeground(Color.white);
                lblGM.setForeground(Color.white);
                lblHMH.setForeground(Color.white);
                lblHSX.setForeground(Color.white);
                lblMS.setForeground(Color.white);
                lblMaMH.setForeground(Color.white);
                lblQG.setForeground(Color.white);
                lblRAM.setForeground(Color.white);
                lblSL.setForeground(Color.white);
                lblTenMH.setForeground(Color.white);
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showDetail() {
        try {
            String sql = "SELECT *  FROM CAPNHATGIA where MAMH like ?";
            ResultSet rs = JdbcHelper.query(sql, tblList.getValueAt(index, 0).toString());
            String giaMua = "";
            String giaBan = "";
            while (rs.next()) {
                giaMua = rs.getString("GIAMUA");
                giaBan = rs.getString("GIABAN");
            }
            int vtm = giaMua.indexOf(".");
            int vtb = giaBan.indexOf(".");
            giaMua = giaMua.substring(0, vtm);
            giaBan = giaBan.substring(0, vtb);
            txtGiaMua.setText(giaMua);
            txtGiaBan.setText(giaBan);
            txtMaMH.setText(tblList.getValueAt(index, 0).toString());
            txtTenMH.setText(tblList.getValueAt(index, 2).toString());
            txtRAM.setText(tblList.getValueAt(index, 3).toString());
            txtDungLuong.setText(tblList.getValueAt(index, 4).toString());
            txtMauSac.setText(tblList.getValueAt(index, 5).toString());
            txtDVT.setText(tblList.getValueAt(index, 8).toString());
            txtSoLuong.setText(tblList.getValueAt(index, 9).toString());
            txtBaoHanh.setText(tblList.getValueAt(index, 10).toString());
            lblHinhMH.setToolTipText(tblList.getValueAt(index, 7).toString());
            Icon ic = XImage.read(lblHinhMH.getToolTipText());
            lblHinhMH.setIcon(ic);
            this.setSelectedComboboxHSX(tblList.getValueAt(index, 1).toString(), cboHSX);
            this.setSelectedComboboxQG(tblList.getValueAt(index, 6).toString(), cboQuocGia);
            hideBtnThem();
            tblList.setRowSelectionInterval(index, index);
            rs.close();
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

    private void fillTable() {
        try {
            String sql = "select MAMH,TENHSX,TENMH,RAM,DUNGLUONG,MAUSAC,TENQG,HINHMH,DONVITINH,SOLUONG,TGBH\n"
                    + "from MATHANG join HANGSANXUAT on MATHANG.MAHSX=HANGSANXUAT.MAHSX\n"
                    + "			 join QUOCGIA on MATHANG.MAQG= QUOCGIA.MAQG";
            ResultSet rs = JdbcHelper.query(sql);
            modelTbl.setRowCount(0);
            while (rs.next()) {
                modelTbl.addRow(new Object[]{
                    rs.getString(1), rs.getString(2), rs.getString(3),
                    rs.getString(4), rs.getString(5), rs.getString(6),
                    rs.getString(7), rs.getString(8), rs.getString(9),
                    rs.getInt(10), rs.getString(11),});
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblList = new javax.swing.JTable();
        jSeparator2 = new javax.swing.JSeparator();
        jPanel2 = new javax.swing.JPanel();
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
        txtDVT = new javax.swing.JTextField();
        txtBaoHanh = new javax.swing.JTextField();
        lblRAM = new javax.swing.JLabel();
        lblDL = new javax.swing.JLabel();
        lblMS = new javax.swing.JLabel();
        lblQG = new javax.swing.JLabel();
        lblDVT = new javax.swing.JLabel();
        lblSL = new javax.swing.JLabel();
        lblBH = new javax.swing.JLabel();
        txtSoLuong = new javax.swing.JTextField();
        txtGiaMua = new javax.swing.JTextField();
        txtGiaBan = new javax.swing.JTextField();
        lblGB = new javax.swing.JLabel();
        lblGM = new javax.swing.JLabel();
        lblHinhMH = new javax.swing.JLabel();
        lblHMH = new javax.swing.JLabel();
        btnClear = new javax.swing.JButton();
        btnThemMH = new javax.swing.JButton();
        btnSuaMH = new javax.swing.JButton();
        lblGB1 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(34, 116, 173));

        jLabel1.setFont(new java.awt.Font("Baloo 2 ExtraBold", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("DANH SÁCH MẶT HÀNG");

        tblList.setBackground(new java.awt.Color(34, 116, 173));
        tblList.setFont(new java.awt.Font("Baloo 2", 0, 12)); // NOI18N
        tblList.setForeground(new java.awt.Color(255, 255, 255));
        tblList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã MH", "HSX", "Tên MH", "RAM", "Dung lượng", "Màu", "Quốc gia", "Hình", "Đơn vị tính", "Số lượng", "TGBH"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblList.setRowHeight(25);
        tblList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblList.setShowGrid(true);
        tblList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblListMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblList);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 774, Short.MAX_VALUE)
            .addComponent(jSeparator2)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 557, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(34, 116, 173));

        jLabel2.setFont(new java.awt.Font("Baloo 2 ExtraBold", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("CẬP NHẬT");

        txtMaMH.setBackground(new java.awt.Color(34, 116, 173));
        txtMaMH.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtMaMH.setForeground(new java.awt.Color(255, 255, 255));
        txtMaMH.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)));

        lblMaMH.setFont(new java.awt.Font("Baloo Chettan 2", 0, 12)); // NOI18N
        lblMaMH.setForeground(new java.awt.Color(255, 255, 255));
        lblMaMH.setText("Mã mặt hàng");

        lblHSX.setFont(new java.awt.Font("Baloo Chettan 2", 0, 12)); // NOI18N
        lblHSX.setForeground(new java.awt.Color(255, 255, 255));
        lblHSX.setText("Hãng sản xuất");

        cboHSX.setBackground(new java.awt.Color(34, 116, 173));
        cboHSX.setForeground(new java.awt.Color(255, 255, 255));

        btnThemHSX.setBackground(new java.awt.Color(34, 116, 173));
        btnThemHSX.setFont(new java.awt.Font("Baloo 2", 0, 12)); // NOI18N
        btnThemHSX.setForeground(new java.awt.Color(255, 255, 255));
        btnThemHSX.setText("Thêm");
        btnThemHSX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemHSXActionPerformed(evt);
            }
        });

        lblTenMH.setFont(new java.awt.Font("Baloo Chettan 2", 0, 12)); // NOI18N
        lblTenMH.setForeground(new java.awt.Color(255, 255, 255));
        lblTenMH.setText("Tên mặt hàng");

        txtTenMH.setBackground(new java.awt.Color(34, 116, 173));
        txtTenMH.setForeground(new java.awt.Color(255, 255, 255));
        txtTenMH.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)));

        txtRAM.setBackground(new java.awt.Color(34, 116, 173));
        txtRAM.setForeground(new java.awt.Color(255, 255, 255));
        txtRAM.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)));

        txtDungLuong.setBackground(new java.awt.Color(34, 116, 173));
        txtDungLuong.setForeground(new java.awt.Color(255, 255, 255));
        txtDungLuong.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)));

        txtMauSac.setBackground(new java.awt.Color(34, 116, 173));
        txtMauSac.setForeground(new java.awt.Color(255, 255, 255));
        txtMauSac.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)));

        cboQuocGia.setBackground(new java.awt.Color(34, 116, 173));
        cboQuocGia.setForeground(new java.awt.Color(255, 255, 255));

        btnThemQG.setBackground(new java.awt.Color(34, 116, 173));
        btnThemQG.setFont(new java.awt.Font("Baloo 2", 0, 12)); // NOI18N
        btnThemQG.setForeground(new java.awt.Color(255, 255, 255));
        btnThemQG.setText("Thêm");
        btnThemQG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemQGActionPerformed(evt);
            }
        });

        txtDVT.setBackground(new java.awt.Color(34, 116, 173));
        txtDVT.setForeground(new java.awt.Color(255, 255, 255));
        txtDVT.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)));

        txtBaoHanh.setBackground(new java.awt.Color(34, 116, 173));
        txtBaoHanh.setForeground(new java.awt.Color(255, 255, 255));
        txtBaoHanh.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)));

        lblRAM.setFont(new java.awt.Font("Baloo Chettan 2", 0, 12)); // NOI18N
        lblRAM.setForeground(new java.awt.Color(255, 255, 255));
        lblRAM.setText("RAM");

        lblDL.setFont(new java.awt.Font("Baloo Chettan 2", 0, 12)); // NOI18N
        lblDL.setForeground(new java.awt.Color(255, 255, 255));
        lblDL.setText("Dung lượng");

        lblMS.setFont(new java.awt.Font("Baloo Chettan 2", 0, 12)); // NOI18N
        lblMS.setForeground(new java.awt.Color(255, 255, 255));
        lblMS.setText("Màu sắc");

        lblQG.setFont(new java.awt.Font("Baloo Chettan 2", 0, 12)); // NOI18N
        lblQG.setForeground(new java.awt.Color(255, 255, 255));
        lblQG.setText("Quốc gia");

        lblDVT.setFont(new java.awt.Font("Baloo Chettan 2", 0, 12)); // NOI18N
        lblDVT.setForeground(new java.awt.Color(255, 255, 255));
        lblDVT.setText("Đơn vị tính");

        lblSL.setFont(new java.awt.Font("Baloo Chettan 2", 0, 12)); // NOI18N
        lblSL.setForeground(new java.awt.Color(255, 255, 255));
        lblSL.setText("Số lượng");

        lblBH.setFont(new java.awt.Font("Baloo Chettan 2", 0, 12)); // NOI18N
        lblBH.setForeground(new java.awt.Color(255, 255, 255));
        lblBH.setText("Bảo hành");

        txtSoLuong.setBackground(new java.awt.Color(34, 116, 173));
        txtSoLuong.setForeground(new java.awt.Color(255, 255, 255));
        txtSoLuong.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)));

        txtGiaMua.setBackground(new java.awt.Color(34, 116, 173));
        txtGiaMua.setForeground(new java.awt.Color(255, 255, 255));
        txtGiaMua.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)));

        txtGiaBan.setBackground(new java.awt.Color(34, 116, 173));
        txtGiaBan.setForeground(new java.awt.Color(255, 255, 255));
        txtGiaBan.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)));

        lblGB.setFont(new java.awt.Font("Baloo Chettan 2", 0, 12)); // NOI18N
        lblGB.setForeground(new java.awt.Color(255, 255, 255));
        lblGB.setText("Giá bán");

        lblGM.setFont(new java.awt.Font("Baloo Chettan 2", 0, 12)); // NOI18N
        lblGM.setForeground(new java.awt.Color(255, 255, 255));
        lblGM.setText("Giá mua");

        lblHinhMH.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHinhMH.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)));
        lblHinhMH.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblHinhMHMouseClicked(evt);
            }
        });

        lblHMH.setFont(new java.awt.Font("Baloo Chettan 2", 0, 12)); // NOI18N
        lblHMH.setForeground(new java.awt.Color(255, 255, 255));
        lblHMH.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHMH.setText("Hình mặt hàng");

        btnClear.setBackground(new java.awt.Color(34, 116, 173));
        btnClear.setFont(new java.awt.Font("Baloo 2", 1, 12)); // NOI18N
        btnClear.setForeground(new java.awt.Color(255, 255, 255));
        btnClear.setText("Clear");
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

        lblGB1.setFont(new java.awt.Font("Baloo Chettan 2", 0, 12)); // NOI18N
        lblGB1.setForeground(new java.awt.Color(255, 255, 255));
        lblGB1.setText("(Tháng)");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblHSX, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblMaMH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblTenMH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblRAM, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblDL, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblMS, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblQG, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblDVT, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblBH, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblGM, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnThemMH)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSuaMH)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnClear)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(cboHSX, 0, 175, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnThemHSX))
                            .addComponent(txtMaMH)
                            .addComponent(txtTenMH)
                            .addComponent(txtRAM)
                            .addComponent(txtDungLuong)
                            .addComponent(txtMauSac, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txtDVT, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblSL)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(cboQuocGia, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnThemQG))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtBaoHanh, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtGiaMua, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(lblGB)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(lblGB1)
                                        .addGap(0, 0, Short.MAX_VALUE)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblHinhMH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblHMH, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE))))
                .addContainerGap())
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnClear, btnSuaMH, btnThemMH});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMaMH, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblMaMH))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblHSX)
                            .addComponent(cboHSX, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnThemHSX))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblTenMH)
                            .addComponent(txtTenMH, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtRAM, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblRAM))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtDungLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblDL))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblMS))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cboQuocGia, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnThemQG)
                            .addComponent(lblQG))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtDVT, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblDVT)
                            .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblSL))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtGiaMua, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblGB)
                            .addComponent(lblGM))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtBaoHanh, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblBH)
                            .addComponent(lblGB1)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(87, 87, 87)
                        .addComponent(lblHinhMH, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(lblHMH)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThemMH)
                    .addComponent(btnSuaMH)
                    .addComponent(btnClear))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
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
        if (evt.getClickCount()==2) {
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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnSuaMH;
    private javax.swing.JButton btnThemHSX;
    private javax.swing.JButton btnThemMH;
    private javax.swing.JButton btnThemQG;
    private javax.swing.JComboBox<String> cboHSX;
    private javax.swing.JComboBox<String> cboQuocGia;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lblBH;
    private javax.swing.JLabel lblDL;
    private javax.swing.JLabel lblDVT;
    private javax.swing.JLabel lblGB;
    private javax.swing.JLabel lblGB1;
    private javax.swing.JLabel lblGM;
    private javax.swing.JLabel lblHMH;
    private javax.swing.JLabel lblHSX;
    private javax.swing.JLabel lblHinhMH;
    private javax.swing.JLabel lblMS;
    private javax.swing.JLabel lblMaMH;
    private javax.swing.JLabel lblQG;
    private javax.swing.JLabel lblRAM;
    private javax.swing.JLabel lblSL;
    private javax.swing.JLabel lblTenMH;
    private javax.swing.JTable tblList;
    private javax.swing.JTextField txtBaoHanh;
    private javax.swing.JTextField txtDVT;
    private javax.swing.JTextField txtDungLuong;
    private javax.swing.JTextField txtGiaBan;
    private javax.swing.JTextField txtGiaMua;
    private javax.swing.JTextField txtMaMH;
    private javax.swing.JTextField txtMauSac;
    private javax.swing.JTextField txtRAM;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtTenMH;
    // End of variables declaration//GEN-END:variables
}
