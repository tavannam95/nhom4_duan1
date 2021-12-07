/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mobilez.ui;

import com.mobilez.dao.NhanVienDAO;
import com.mobilez.models.NhanVien;
import com.mobilez.utils.Auth;
import com.mobilez.utils.JdbcHelper;
import com.mobilez.utils.Msgbox;
import com.mobilez.utils.TextAffect;
import com.mobilez.utils.XDate;
import com.mobilez.utils.XImage;
import java.awt.Color;
import static java.awt.Color.blue;
import static java.awt.Color.pink;
import static java.awt.Color.white;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author uhtku
 */
public class FrmNhanVien extends javax.swing.JPanel {

    DefaultTableModel modelTbl = new DefaultTableModel();
    int index = 0;
    NhanVienDAO dao = new NhanVienDAO();
    JFileChooser fileChooser = new JFileChooser();
    ArrayList<NhanVien> lstNhanVien = new ArrayList<>();

    /**
     * Creates new form FrmNhanVien
     */
    public FrmNhanVien() {
        initComponents();
        modelTbl = (DefaultTableModel) tblList.getModel();
        fillToTable();
        this.clear();
    }

    public boolean check() {
        //manv
        if (txtMaNV.getText().trim().equals("")) {
            Msgbox.alert(null, "Mã nhân viên không được để trống");
            txtMaNV.setText("");
            txtMaNV.requestFocus();
            txtMaNV.setBackground(pink);
            return false;
        } else {
            txtMaNV.setBackground(cl);
        }
        
        //check độ dài chữ
        if (txtMaNV.getText().matches("[a-zA-Z0-9]{4,25}")) {
            txtMaNV.setBackground(cl);
        } else {
            Msgbox.alert(null, "Mã nhân viên phải có từ 4 - 25 ký tự \n Không được có ký tự đặc biệt");
            txtMaNV.requestFocus();
            txtMaNV.setBackground(pink);
            return false;

        }

        //check trùng
        for (int i = 0; i < tblList.getRowCount(); i++) {
            if (tblList.getValueAt(i, 0).toString().equalsIgnoreCase(txtMaNV.getText())) {
                Msgbox.alert(null, "Mã nhân viên đã tồn tại!");
                txtMaNV.setText("");
                txtMaNV.requestFocus();
                txtMaNV.setBackground(pink);
                return false;
            } else {
                txtMaNV.setBackground(cl);
            }
        }
        //ngày sinh
        if (txtNgaySinh.getText().trim().equals("")) {
            Msgbox.alert(null, "Ngày sinh không được để trống");
            txtNgaySinh.setText("");
            txtNgaySinh.requestFocus();
            txtNgaySinh.setBackground(pink);
            return false;

        } else {
            txtNgaySinh.setBackground(cl);
        }
        //check ngày
        try {
            String ns = txtNgaySinh.getText();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY");
            Date d = sdf.parse(ns);
        } catch (Exception e) {
            Msgbox.alert(this, "Vui lòng nhập đúng định dạng dd/MM/YYYY");
            txtNgaySinh.requestFocus();
            txtNgaySinh.setBackground(pink);
            return false;
        }
        //sdt
        if (txtSDT.getText().trim().equals("")) {
            Msgbox.alert(null, "Số điện thoại không được để trống");
            txtSDT.setText("");
            txtSDT.requestFocus();
            txtSDT.setBackground(pink);
            return false;
        } else {
            txtSDT.setBackground(cl);
        }
        //check sdt
        String sdt = txtSDT.getText().replace(".", "");
        if (sdt.matches("(09|08|07|05|03)[0-9]{8}")) {
            txtSDT.setBackground(cl);
        } else {
            Msgbox.alert(null, "SDT phải có 10 số bắt đầu từ 09,08,07,05,03");
            txtSDT.requestFocus();
            txtSDT.setBackground(pink);
            return false;

        }

        //mat khau
        if (txtMatKhau.getText().trim().equals("")) {
            Msgbox.alert(null, "Mật khẩu không được để trống");
            txtMatKhau.setText("");
            txtMatKhau.requestFocus();
            txtMatKhau.setBackground(pink);
            return false;
        } else {
            txtMatKhau.setBackground(cl);
        }
        if (txtMatKhau.getText().matches("^[A-Za-zÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚÝàáâãèéêìíòóôõùúýĂăĐđĨĩŨũƠơƯưẠ-ỹ!@#$%^&*,-_./0-9]{6,25}$")) {
            txtMatKhau.setBackground(cl);
        } else {
            Msgbox.alert(null, "Mật khẩu phải có ít nhất 6 ký tự \n Không được để khoảng trống");
            txtMatKhau.setText("");
            txtMatKhau.requestFocus();
            txtMatKhau.setBackground(pink);
            return false;

        }

        //cmnd
        if (txtCMND.getText().trim().equals("")) {
            Msgbox.alert(null, "Số CMND không được để trống");
            txtCMND.setText("");
            txtCMND.requestFocus();
            txtCMND.setBackground(pink);
            return false;
        } else {
            txtCMND.setBackground(cl);
        }
        // check số   
        if (txtCMND.getText().matches("(\\d+){9,12}")) {
            txtMatKhau.setBackground(cl);
        } else {
            Msgbox.alert(null, "Độ dài số CCCD từ 9 - 12 số, không được để khoảng trống");
            txtCMND.setText("");
            txtCMND.requestFocus();
            txtCMND.setBackground(pink);
            return false;

        }

        //tên
        if (txtTen.getText().trim().equals("")) {
            Msgbox.alert(null, "Tên nhân viên không được để trống");
            txtTen.setText("");
            txtTen.requestFocus();
            txtTen.setBackground(pink);
            return false;

        } else {
            txtTen.setBackground(cl);
        }
        //check tên
        if (txtTen.getText().matches("^[A-Za-zÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚÝàáâãèéêìíòóôõùúýĂăĐđĨĩŨũƠơƯưẠ-ỹ ]{1,25}$")) {
            txtTen.setBackground(cl);
        } else {
            Msgbox.alert(null, "Tên nhân viên không được chứa số, ký tự đặc biệt");
            txtTen.setText("");
            txtTen.requestFocus();
            txtTen.setBackground(pink);
            return false;

        }

        //địa chỉ
        if (txtDiaChi.getText().trim().equals("")) {
            Msgbox.alert(null, "Địa chỉ không được để trống");
            txtDiaChi.setText("");
            txtDiaChi.requestFocus();
            txtDiaChi.setBackground(pink);
            return false;

        } else {
            txtDiaChi.setBackground(cl);
        }

//         if (txtDiaChi.getText().matches("^[A-Za-zÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚÝàáâãèéêìíòóôõùúýĂăĐđĨĩŨũƠơƯưẠ-ỹ!@#$%^&*,-_./0-9 ]{1,25}$")) {
//            txtDiaChi.setBackground(cl);
//        }
//        else{
//            Msgbox.alert(null, "Địa chỉ không được để khoảng trống");
//            txtDiaChi.setText("");
//            txtDiaChi.requestFocus();
//            txtDiaChi.setBackground(pink);
//            return false;
//        }
        //hinh
        if (lblHinhNV.getToolTipText() == null) {
            Msgbox.alert(null, "Bạn chưa chọn ảnh");
            lblHinhNV.setForeground(pink);
            return false;
        } else {
            lblHinhNV.setForeground(cl);
        }
        return true;
    }

    void fillToTable() {
        modelTbl.setRowCount(0);
        try {
            List<NhanVien> list = dao.selectAll();
            for (NhanVien nhanVien : list) {
                Object[] row = {
                    nhanVien.getMaNV(),
                    nhanVien.getHoTen(),
                    nhanVien.isGioiTinh() ? "Nam" : "Nữ",
                    XDate.toString(nhanVien.getNgaysinh()),
                    nhanVien.getSoCCCD(),
                    nhanVien.getDiaChi(),
                    nhanVien.getSoDienThoai(),
                    nhanVien.isVaiTro() ? "Quản lý" : "Nhân Viên",
                    nhanVien.getHinhNV(),
                    nhanVien.isTrangThai() ? "Đang làm" : "Đã nghỉ việc",
                    nhanVien.getMatKhau()
                };
                modelTbl.addRow(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Msgbox.alert(this, "Lỗi truy vấn dữ liệu");
        }
        updateStatus();
    }

    void them() {
        String mk =  new String(txtMatKhau.getPassword());
        NhanVien nv = new NhanVien(txtMaNV.getText(), txtTen.getText(), rdoNam.isSelected(), 
                XDate.toDate(txtNgaySinh.getText(), "dd/MM/yyyy") , txtCMND.getText(), 
                txtDiaChi.getText(), txtSDT.getText(), rdoQuanLy.isSelected(), lblHinhNV.getToolTipText(), 
                rdoDangLam.isSelected(), mk);
        try {
            dao.insert(this.getNhanVien());
            this.fillToTable();
            this.clear();
            Msgbox.alert(this, "Thêm mới thành công");
        } catch (Exception e) {
            e.printStackTrace();
            Msgbox.alert(this, "Lỗi truy vấn dữ liệu");
        }
    }

    void CapNhat() {
        String mk =  new String(txtMatKhau.getPassword());
        NhanVien nv = new NhanVien(txtMaNV.getText(), txtTen.getText(), rdoNam.isSelected(), 
                XDate.toDate(txtNgaySinh.getText(), "dd/MM/yyyy") , txtCMND.getText(), 
                txtDiaChi.getText(), txtSDT.getText(), rdoQuanLy.isSelected(), lblHinhNV.getToolTipText(), 
                rdoDangLam.isSelected(), mk);
        try {
            dao.update(this.getNhanVien());
            this.fillToTable();
            this.clear();
            Msgbox.alert(this, "Cập nhật thành công");
        } catch (Exception e) {
            e.printStackTrace();
            Msgbox.alert(this, "Lỗi truy vấn dữ liệu");
        }
    }

    void edit() {
        try {
            String manv = (String) tblList.getValueAt(this.index, 0);
            NhanVien model = dao.selectById(manv);
            if (model != null) {
                this.setModel(model);
                updateStatus();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Msgbox.alert(this, "Lỗi truy vấn dữ liệu");
        }
    }

    private static final Color cl = new Color(34, 116, 173);

    void clear() {
        txtTen.setText("");
        txtCMND.setText("");
        txtDiaChi.setText("");
        txtMaNV.setText("");
        txtMatKhau.setText("");
        txtSDT.setText("");
        txtNgaySinh.setText("");
        rdoNam.setSelected(true);
        rdoQuanLy.setSelected(true);
        rdoDangLam.setSelected(true);
        lblHinhNV.setToolTipText(null);
        lblHinhNV.setIcon(null);
        txtMaNV.setBackground(cl);
        txtTen.setBackground(cl);
        txtCMND.setBackground(cl);
        txtDiaChi.setBackground(cl);
        txtMatKhau.setBackground(cl);
        txtSDT.setBackground(cl);
        txtNgaySinh.setBackground(cl);
        index = -1;
        updateStatus();

    }

    private NhanVien getNhanVien() {
        NhanVien nv = new NhanVien(txtMaNV.getText(), txtTen.getText(), rdoNam.isSelected(), XDate.toDate(txtNgaySinh.getText(), "dd/MM/yyyy"),
                txtCMND.getText(), txtDiaChi.getText(), txtSDT.getText(), rdoQuanLy.isSelected(), lblHinhNV.getToolTipText(), rdoDangLam.isSelected(), txtMatKhau.getText());
        return nv;
    }

    void setModel(NhanVien model) {
        txtMaNV.setText(model.getMaNV());
        txtTen.setText(model.getHoTen());
        rdoNam.setSelected(model.isGioiTinh());
        rdoNu.setSelected(!model.isGioiTinh());
        txtNgaySinh.setText(XDate.toString(model.getNgaysinh()));
        txtCMND.setText(model.getSoCCCD());
        txtDiaChi.setText(model.getDiaChi());
        txtSDT.setText(model.getSoDienThoai());
        rdoNhanVien.setSelected(!model.isVaiTro());
        rdoQuanLy.setSelected(model.isVaiTro());
        rdoDangLam.setSelected(model.isTrangThai());
        rdoNghiViec.setSelected(!model.isTrangThai());
//        if (model.getHinhNV() != null ) {
//            lblHinhNV.setIcon(XImage.read(model.getHinhNV()));
//        } else {
//            lblHinhNV.setIcon(XImage.read("logoM_128px.png"));
//        }
        lblHinhNV.setToolTipText(model.getHinhNV());
        ImageIcon icon = XImage.read(lblHinhNV.getToolTipText());
        Image image = icon.getImage(); // transform it 
        Image newimg = image.getScaledInstance(lblHinhNV.getWidth(), lblHinhNV.getHeight(), java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
        icon = new ImageIcon(newimg);  // transform it back
        lblHinhNV.setIcon(icon);
        rdoDangLam.setSelected(model.isTrangThai());
        txtMatKhau.setText(model.getMatKhau());
    }

    NhanVien getModel() {
        NhanVien model = new NhanVien();
        model.setMaNV(txtMaNV.getText());
        model.setHoTen(txtTen.getText());
        model.setGioiTinh(rdoNam.isSelected());
        model.setNgaysinh(XDate.toDate(txtNgaySinh.getText()));
        model.setSoCCCD(txtCMND.getText());
        model.setDiaChi(txtDiaChi.getText());
        model.setSoDienThoai(txtSDT.getText());
        model.setVaiTro(rdoQuanLy.isSelected());
        model.setHinhNV(lblHinhNV.getToolTipText());
        model.setVaiTro(rdoDangLam.isSelected());
        model.setMatKhau(txtMatKhau.getText());
        return model;
    }

    void selectImage() {
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
                Image newimg = image.getScaledInstance(lblHinhNV.getWidth(), lblHinhNV.getHeight(), java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
                icon = new ImageIcon(newimg);
                lblHinhNV.setIcon(icon);
                lblHinhNV.setToolTipText(file.getName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateStatus() {
        boolean edit = (this.index >= 0);
        txtMaNV.setEditable(!edit);
        btnSuaNV.setEnabled(edit);
        btnThemNV.setEnabled(!edit);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblList = new javax.swing.JTable();
        jSeparator2 = new javax.swing.JSeparator();
        jPanel5 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        txtMaNV = new javax.swing.JTextField();
        lblMaMH = new javax.swing.JLabel();
        lblHSX = new javax.swing.JLabel();
        lblTenMH = new javax.swing.JLabel();
        txtNgaySinh = new javax.swing.JTextField();
        txtCMND = new javax.swing.JTextField();
        txtDiaChi = new javax.swing.JTextField();
        lblRAM = new javax.swing.JLabel();
        lblDL = new javax.swing.JLabel();
        lblMS = new javax.swing.JLabel();
        lblQG = new javax.swing.JLabel();
        lblDVT = new javax.swing.JLabel();
        lblGM = new javax.swing.JLabel();
        lblHinhNV = new javax.swing.JLabel();
        lblHMH = new javax.swing.JLabel();
        btnClear = new javax.swing.JButton();
        btnThemNV = new javax.swing.JButton();
        btnSuaNV = new javax.swing.JButton();
        txtTen = new javax.swing.JTextField();
        rdoNam = new javax.swing.JRadioButton();
        rdoNu = new javax.swing.JRadioButton();
        txtSDT = new javax.swing.JTextField();
        rdoQuanLy = new javax.swing.JRadioButton();
        rdoNhanVien = new javax.swing.JRadioButton();
        txtMatKhau = new javax.swing.JPasswordField();
        lblDVT1 = new javax.swing.JLabel();
        rdoDangLam = new javax.swing.JRadioButton();
        rdoNghiViec = new javax.swing.JRadioButton();

        setBackground(new java.awt.Color(34, 116, 173));

        jPanel3.setBackground(new java.awt.Color(34, 116, 173));

        jLabel1.setFont(new java.awt.Font("Baloo 2 ExtraBold", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("DANH SÁCH NHÂN VIÊN");

        tblList.setBackground(new java.awt.Color(34, 116, 173));
        tblList.setFont(new java.awt.Font("Baloo 2", 0, 13)); // NOI18N
        tblList.setForeground(new java.awt.Color(255, 255, 255));
        tblList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã NV", "Họ Tên", "Giới Tính", "Ngày Sinh", "CMND", "Địa Chỉ", "SĐT", "Vai Trò", "Hình", "Trạng Thái", "Mật Khẩu"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
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

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator2)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 653, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jScrollPane2)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 565, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(34, 116, 173));

        jLabel12.setFont(new java.awt.Font("Baloo 2 ExtraBold", 0, 24)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("CẬP NHẬT");

        txtMaNV.setBackground(new java.awt.Color(34, 116, 173));
        txtMaNV.setFont(new java.awt.Font("Baloo 2", 1, 12)); // NOI18N
        txtMaNV.setForeground(new java.awt.Color(255, 255, 255));
        txtMaNV.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)));

        lblMaMH.setFont(new java.awt.Font("Baloo 2 ExtraBold", 1, 14)); // NOI18N
        lblMaMH.setForeground(new java.awt.Color(255, 255, 255));
        lblMaMH.setText("Mã nhân viên");

        lblHSX.setFont(new java.awt.Font("Baloo 2 ExtraBold", 1, 14)); // NOI18N
        lblHSX.setForeground(new java.awt.Color(255, 255, 255));
        lblHSX.setText("Tên nhân viên");

        lblTenMH.setFont(new java.awt.Font("Baloo 2 ExtraBold", 1, 14)); // NOI18N
        lblTenMH.setForeground(new java.awt.Color(255, 255, 255));
        lblTenMH.setText("Giới tính");

        txtNgaySinh.setBackground(new java.awt.Color(34, 116, 173));
        txtNgaySinh.setFont(new java.awt.Font("Baloo 2", 1, 12)); // NOI18N
        txtNgaySinh.setForeground(new java.awt.Color(255, 255, 255));
        txtNgaySinh.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)));

        txtCMND.setBackground(new java.awt.Color(34, 116, 173));
        txtCMND.setFont(new java.awt.Font("Baloo 2", 1, 12)); // NOI18N
        txtCMND.setForeground(new java.awt.Color(255, 255, 255));
        txtCMND.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)));

        txtDiaChi.setBackground(new java.awt.Color(34, 116, 173));
        txtDiaChi.setFont(new java.awt.Font("Baloo 2", 1, 12)); // NOI18N
        txtDiaChi.setForeground(new java.awt.Color(255, 255, 255));
        txtDiaChi.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)));
        txtDiaChi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDiaChiActionPerformed(evt);
            }
        });

        lblRAM.setFont(new java.awt.Font("Baloo 2 ExtraBold", 1, 14)); // NOI18N
        lblRAM.setForeground(new java.awt.Color(255, 255, 255));
        lblRAM.setText("Ngày sinh");

        lblDL.setFont(new java.awt.Font("Baloo 2 ExtraBold", 1, 14)); // NOI18N
        lblDL.setForeground(new java.awt.Color(255, 255, 255));
        lblDL.setText("Số CCCD");

        lblMS.setFont(new java.awt.Font("Baloo 2 ExtraBold", 1, 14)); // NOI18N
        lblMS.setForeground(new java.awt.Color(255, 255, 255));
        lblMS.setText("Địa chỉ");

        lblQG.setFont(new java.awt.Font("Baloo 2 ExtraBold", 1, 14)); // NOI18N
        lblQG.setForeground(new java.awt.Color(255, 255, 255));
        lblQG.setText("SĐT");

        lblDVT.setFont(new java.awt.Font("Baloo 2 ExtraBold", 1, 14)); // NOI18N
        lblDVT.setForeground(new java.awt.Color(255, 255, 255));
        lblDVT.setText("Vai trò");

        lblGM.setFont(new java.awt.Font("Baloo 2 ExtraBold", 1, 14)); // NOI18N
        lblGM.setForeground(new java.awt.Color(255, 255, 255));
        lblGM.setText("Mật khẩu");

        lblHinhNV.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHinhNV.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)));
        lblHinhNV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblHinhNVMouseClicked(evt);
            }
        });

        lblHMH.setFont(new java.awt.Font("Baloo 2 ExtraBold", 1, 14)); // NOI18N
        lblHMH.setForeground(new java.awt.Color(255, 255, 255));
        lblHMH.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHMH.setText("Hình nhân viên");

        btnClear.setBackground(new java.awt.Color(34, 116, 173));
        btnClear.setFont(new java.awt.Font("Baloo 2 ExtraBold", 1, 14)); // NOI18N
        btnClear.setForeground(new java.awt.Color(255, 255, 255));
        btnClear.setText("Clear");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        btnThemNV.setBackground(new java.awt.Color(34, 116, 173));
        btnThemNV.setFont(new java.awt.Font("Baloo 2 ExtraBold", 1, 14)); // NOI18N
        btnThemNV.setForeground(new java.awt.Color(255, 255, 255));
        btnThemNV.setText("Thêm");
        btnThemNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemNVActionPerformed(evt);
            }
        });

        btnSuaNV.setBackground(new java.awt.Color(34, 116, 173));
        btnSuaNV.setFont(new java.awt.Font("Baloo 2 ExtraBold", 1, 14)); // NOI18N
        btnSuaNV.setForeground(new java.awt.Color(255, 255, 255));
        btnSuaNV.setText("Sửa");
        btnSuaNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaNVActionPerformed(evt);
            }
        });

        txtTen.setBackground(new java.awt.Color(34, 116, 173));
        txtTen.setFont(new java.awt.Font("Baloo 2", 1, 12)); // NOI18N
        txtTen.setForeground(new java.awt.Color(255, 255, 255));
        txtTen.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)));

        buttonGroup1.add(rdoNam);
        rdoNam.setFont(new java.awt.Font("Baloo 2", 1, 14)); // NOI18N
        rdoNam.setForeground(new java.awt.Color(255, 255, 255));
        rdoNam.setSelected(true);
        rdoNam.setText("Nam");

        buttonGroup1.add(rdoNu);
        rdoNu.setFont(new java.awt.Font("Baloo 2", 1, 14)); // NOI18N
        rdoNu.setForeground(new java.awt.Color(255, 255, 255));
        rdoNu.setText("Nữ");

        txtSDT.setBackground(new java.awt.Color(34, 116, 173));
        txtSDT.setFont(new java.awt.Font("Baloo 2", 1, 12)); // NOI18N
        txtSDT.setForeground(new java.awt.Color(255, 255, 255));
        txtSDT.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)));
        txtSDT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSDTActionPerformed(evt);
            }
        });
        txtSDT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSDTKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSDTKeyReleased(evt);
            }
        });

        buttonGroup2.add(rdoQuanLy);
        rdoQuanLy.setFont(new java.awt.Font("Baloo 2", 1, 14)); // NOI18N
        rdoQuanLy.setForeground(new java.awt.Color(255, 255, 255));
        rdoQuanLy.setSelected(true);
        rdoQuanLy.setText("Quản lý");

        buttonGroup2.add(rdoNhanVien);
        rdoNhanVien.setFont(new java.awt.Font("Baloo 2", 1, 14)); // NOI18N
        rdoNhanVien.setForeground(new java.awt.Color(255, 255, 255));
        rdoNhanVien.setText("Nhân viên");

        txtMatKhau.setBackground(new java.awt.Color(34, 116, 173));
        txtMatKhau.setFont(new java.awt.Font("Baloo 2", 1, 12)); // NOI18N
        txtMatKhau.setForeground(new java.awt.Color(255, 255, 255));

        lblDVT1.setFont(new java.awt.Font("Baloo 2 ExtraBold", 1, 14)); // NOI18N
        lblDVT1.setForeground(new java.awt.Color(255, 255, 255));
        lblDVT1.setText("Trạng Thái");

        buttonGroup3.add(rdoDangLam);
        rdoDangLam.setFont(new java.awt.Font("Baloo 2", 1, 14)); // NOI18N
        rdoDangLam.setForeground(new java.awt.Color(255, 255, 255));
        rdoDangLam.setSelected(true);
        rdoDangLam.setText("Đang làm");

        buttonGroup3.add(rdoNghiViec);
        rdoNghiViec.setFont(new java.awt.Font("Baloo 2", 1, 14)); // NOI18N
        rdoNghiViec.setForeground(new java.awt.Color(255, 255, 255));
        rdoNghiViec.setText("Đã nghỉ việc");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(lblHSX, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblMaMH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblTenMH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblRAM, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblDL, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblMS, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblQG, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblDVT, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblGM, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblDVT1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(btnThemNV, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel5Layout.createSequentialGroup()
                                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel5Layout.createSequentialGroup()
                                            .addComponent(rdoQuanLy)
                                            .addGap(18, 18, 18)
                                            .addComponent(rdoNhanVien))
                                        .addGroup(jPanel5Layout.createSequentialGroup()
                                            .addComponent(rdoDangLam)
                                            .addGap(18, 18, 18)
                                            .addComponent(rdoNghiViec)))
                                    .addGap(82, 82, 82))
                                .addComponent(txtMatKhau, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                                .addGap(38, 38, 38)
                                .addComponent(btnSuaNV)
                                .addGap(53, 53, 53)
                                .addComponent(btnClear)))
                        .addContainerGap(149, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(rdoNam)
                                .addGap(10, 10, 10)
                                .addComponent(rdoNu))
                            .addComponent(txtTen, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
                            .addComponent(txtNgaySinh)
                            .addComponent(txtCMND)
                            .addComponent(txtDiaChi)
                            .addComponent(txtSDT)
                            .addComponent(txtMaNV))
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addComponent(lblHMH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(lblHinhNV, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))))
        );

        jPanel5Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnClear, btnSuaNV, btnThemNV});

        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(lblHinhNV, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(45, 45, 45)
                                .addComponent(lblMS))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblHMH))))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblMaMH))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblHSX)
                            .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblTenMH)
                            .addComponent(rdoNam)
                            .addComponent(rdoNu))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblRAM))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCMND, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblDL))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblQG))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdoQuanLy)
                    .addComponent(rdoNhanVien)
                    .addComponent(lblDVT))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdoDangLam)
                    .addComponent(rdoNghiViec)
                    .addComponent(lblDVT1))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblGM, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThemNV, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSuaNV)
                    .addComponent(btnClear))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jPanel5Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnClear, btnSuaNV, btnThemNV});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblListMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 2) {
            this.index = tblList.rowAtPoint(evt.getPoint());
            if (this.index >= 0) {
                this.edit();

            }
        }

    }//GEN-LAST:event_tblListMouseClicked

    private void lblHinhNVMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHinhNVMouseClicked
        // TODO add your handling code here:
        this.selectImage();
    }//GEN-LAST:event_lblHinhNVMouseClicked

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        // TODO add your handling code here:
        this.clear();
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnThemNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemNVActionPerformed
        // TODO add your handling code here:
        if (this.check()) {
            them();
        }
    }//GEN-LAST:event_btnThemNVActionPerformed

    private void btnSuaNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaNVActionPerformed
        // TODO add your handling code here:
        if (txtMaNV.getText().trim().equals("")) {
            Msgbox.alert(null, "Mã nhân viên không được để trống");
            txtMaNV.setText("");
            txtMaNV.requestFocus();
            txtMaNV.setBackground(pink);
            return;
        } else {
            txtMaNV.setBackground(cl);
        }

        //check độ dài chữ
        if (txtMaNV.getText().matches("[a-zA-Z0-9]{4,25}")) {
            txtMaNV.setBackground(cl);
        } else {
            Msgbox.alert(null, "Mã nhân viên phải có từ 5 - 10 ký tự \n Không được có ký tự đặc biệt");
            txtMaNV.setText("");
            txtMaNV.requestFocus();
            txtMaNV.setBackground(pink);
            return;

        }

        //ngày sinh
        if (txtNgaySinh.getText().trim().equals("")) {
            Msgbox.alert(null, "Ngày sinh không được để trống");
            txtNgaySinh.setText("");
            txtNgaySinh.requestFocus();
            txtNgaySinh.setBackground(pink);
            return;

        } else {
            txtNgaySinh.setBackground(cl);
        }
        //check ngày
        try {
            String ns = txtNgaySinh.getText();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY");
            Date d = sdf.parse(ns);
        } catch (Exception e) {
            Msgbox.alert(this, "Vui lòng nhập đúng định dạng dd/MM/YYYY");
            txtNgaySinh.setText("");
            txtNgaySinh.requestFocus();
            txtNgaySinh.setBackground(pink);
            return;
        }
        //sdt
        if (txtSDT.getText().trim().equals("")) {
            Msgbox.alert(null, "Số điện thoại không được để trống");
            txtSDT.setText("");
            txtSDT.requestFocus();
            txtSDT.setBackground(pink);
            return;
        } else {
            txtSDT.setBackground(cl);
        }
        //check sdt
        String sdt = txtSDT.getText().replace(".", "");
        if (sdt.matches("(09|08|07|05|03)[0-9]{8}")) {
            txtSDT.setBackground(cl);
        } else {
            Msgbox.alert(null, "SDT phải có 10 số bắt đầu từ 09,08,07,05,03");
            txtSDT.setText("");
            txtSDT.requestFocus();
            txtSDT.setBackground(pink);
            return;

        }

        //mat khau
        if (txtMatKhau.getText().trim().equals("")) {
            Msgbox.alert(null, "Mật khẩu không được để trống");
            txtMatKhau.setText("");
            txtMatKhau.requestFocus();
            txtMatKhau.setBackground(pink);
            return;
        } else {
            txtMatKhau.setBackground(cl);
        }
        if (txtMatKhau.getText().matches("^[A-Za-zÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚÝàáâãèéêìíòóôõùúýĂăĐđĨĩŨũƠơƯưẠ-ỹ!@#$%^&*,-_./0-9]{6,25}$")) {
            txtMatKhau.setBackground(cl);
        } else {
            Msgbox.alert(null, "Mật khẩu phải có ít nhất 6 ký tự \n Không được để khoảng trống");
            txtMatKhau.setText("");
            txtMatKhau.requestFocus();
            txtMatKhau.setBackground(pink);
            return;

        }

        //cmnd
        if (txtCMND.getText().trim().equals("")) {
            Msgbox.alert(null, "Số CMND không được để trống");
            txtCMND.setText("");
            txtCMND.requestFocus();
            txtCMND.setBackground(pink);
            return;
        } else {
            txtCMND.setBackground(cl);
        }
        // check số   
        if (txtCMND.getText().matches("(\\d+){9,12}")) {
            txtMatKhau.setBackground(cl);
        } else {
            Msgbox.alert(null, "Độ dài số CCCD từ 9 - 12 số, không được để khoảng trống");
            txtCMND.setText("");
            txtCMND.requestFocus();
            txtCMND.setBackground(pink);
            return;

        }

        //tên
        if (txtTen.getText().trim().equals("")) {
            Msgbox.alert(null, "Tên nhân viên không được để trống");
            txtTen.setText("");
            txtTen.requestFocus();
            txtTen.setBackground(pink);
            return;

        } else {
            txtTen.setBackground(cl);
        }
        //check tên
        if (txtTen.getText().matches("^[A-Za-zÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚÝàáâãèéêìíòóôõùúýĂăĐđĨĩŨũƠơƯưẠ-ỹ ]{1,25}$")) {
            txtTen.setBackground(cl);
        } else {
            Msgbox.alert(null, "Tên nhân viên không được chứa số, để khoảng trống, ký tự đặc biệt");
            txtTen.setText("");
            txtTen.requestFocus();
            txtTen.setBackground(pink);
            return;

        }

        //địa chỉ
        if (txtDiaChi.getText().trim().equals("")) {
            Msgbox.alert(null, "Địa chỉ không được để trống");
            txtDiaChi.setText("");
            txtDiaChi.requestFocus();
            txtDiaChi.setBackground(pink);
            return;

        } else {
            txtDiaChi.setBackground(cl);
        }

//        if (txtDiaChi.getText().matches("^[A-Za-zÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚÝàáâãèéêìíòóôõùúýĂăĐđĨĩŨũƠơƯưẠ-ỹ!@#$%^&*,-_./0-9]{1,25}$")) {
//            txtDiaChi.setBackground(cl);
//        }
//        else{
//            Msgbox.alert(null, "Địa chỉ không được để khoảng trống");
//            txtDiaChi.setText("");
//            txtDiaChi.requestFocus();
//            txtDiaChi.setBackground(pink);
//            return;
//            
//        }
        //hinh
        if (lblHinhNV.getToolTipText() == null) {
            Msgbox.alert(null, "Bạn chưa chọn ảnh");
            lblHinhNV.setForeground(pink);
            return;
        } else {
            lblHinhNV.setForeground(cl);
        }
        CapNhat();
    }//GEN-LAST:event_btnSuaNVActionPerformed

    private void txtDiaChiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDiaChiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDiaChiActionPerformed

    private void txtSDTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSDTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSDTActionPerformed

    private void txtSDTKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSDTKeyReleased
        // TODO add your handling code here:
        TextAffect.convertToPhoneDot(txtSDT);
        
    }//GEN-LAST:event_txtSDTKeyReleased

    private void txtSDTKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSDTKeyPressed
        // TODO add your handling code here:
        if (txtSDT.getText().length()==5) {
            if (evt.getKeyCode()==KeyEvent.VK_BACK_SPACE) {
                txtSDT.setText(txtSDT.getText().substring(0,4));
            }
        }
        if (txtSDT.getText().length()==9) {
            if (evt.getKeyCode()==KeyEvent.VK_BACK_SPACE) {
                txtSDT.setText(txtSDT.getText().substring(0,8));
            }
        }
    }//GEN-LAST:event_txtSDTKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnSuaNV;
    private javax.swing.JButton btnThemNV;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lblDL;
    private javax.swing.JLabel lblDVT;
    private javax.swing.JLabel lblDVT1;
    private javax.swing.JLabel lblGM;
    private javax.swing.JLabel lblHMH;
    private javax.swing.JLabel lblHSX;
    private javax.swing.JLabel lblHinhNV;
    private javax.swing.JLabel lblMS;
    private javax.swing.JLabel lblMaMH;
    private javax.swing.JLabel lblQG;
    private javax.swing.JLabel lblRAM;
    private javax.swing.JLabel lblTenMH;
    private javax.swing.JRadioButton rdoDangLam;
    private javax.swing.JRadioButton rdoNam;
    private javax.swing.JRadioButton rdoNghiViec;
    private javax.swing.JRadioButton rdoNhanVien;
    private javax.swing.JRadioButton rdoNu;
    private javax.swing.JRadioButton rdoQuanLy;
    private javax.swing.JTable tblList;
    private javax.swing.JTextField txtCMND;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtMaNV;
    private javax.swing.JPasswordField txtMatKhau;
    private javax.swing.JTextField txtNgaySinh;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTen;
    // End of variables declaration//GEN-END:variables
}
