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
import com.mobilez.utils.XDate;
import com.mobilez.utils.XImage;
import java.awt.Color;
import static java.awt.Color.pink;
import static java.awt.Color.white;
import java.io.File;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.Icon;

/**
 *
 * @author uhtku
 */
public class FrmNhanVien extends javax.swing.JPanel {

    DefaultTableModel modelTbl = new DefaultTableModel();
    int index;
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
        setStatus(true);
    }
    
    
    public void check(){
        //manv
        if (txtMaNV.getText().trim().equals("")) {
            Msgbox.alert(null, "Mã nhân viên không được để trống");
            txtMaNV.setText("");
            txtMaNV.requestFocus();
            txtMaNV.setBackground(pink);
            return;
       
        }
        //ngày sinh
         if (txtNgaySinh.getText().trim().equals("")) {
            Msgbox.alert(null, "Tên nhân viên không được để trống");
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
       

        }
          //mat khau
           if (txtMatKhau.getText().trim().equals("")) {
            Msgbox.alert(null, "Mật khẩu không được để trống");
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
        
        }
         // chech số   
        try {
            Integer.parseInt(txtCMND.getText());
        } catch (Exception e) {
            Msgbox.alert(null, "CMND phải là số");
            txtCMND.setText("");
            txtCMND.requestFocus();
            txtCMND.setBackground(pink);
            return;
        }
         try {
            Integer.parseInt(txtSDT.getText());
        } catch (Exception e) {
            Msgbox.alert(null, "SĐT phải là số");
            txtSDT.setText("");
            txtSDT.requestFocus();
            txtSDT.setBackground(pink);
            return;
        }

            //tên
             if (txtTen.getText().trim().equals("")) {
            Msgbox.alert(null, "Tên nhân viên không được để trống");
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
       
        }
    }
    
    void fillToTable(){
        modelTbl.setRowCount(0);
        try {
            List<NhanVien>  list = dao.selectAll();
            for (NhanVien nhanVien : list) {
                Object[] row = {
                    nhanVien.getMaNV(),
                    nhanVien.getHoTen(),
                    nhanVien.isGioiTinh() ? "Nam" : "Nữ",
//                    XDate.toString(nhanVien.getNgaysinh()),
                    nhanVien.getNgaysinh(),
                    nhanVien.getSoCCCD(),
                    nhanVien.getDiaChi(),
                    nhanVien.getSoDienThoai(),
                    nhanVien.isVaiTro() ? "Quản lý" : "Nhân Viên",
                    nhanVien.getHinhNV(),
                    nhanVien.getMatKhau()
//                    Auth.user.isVaiTro()?nhanVien.getMatKhau():matKhauToSao(nhanVien.getMatKhau())
                };
                modelTbl.addRow(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Msgbox.alert(this, "Lỗi truy vấn dữ liệu");
        }
    }
    void them(){
        NhanVien nv = new NhanVien();
        try {
            dao.insert(nv);
            this.fillToTable();
            this.clear();
            Msgbox.alert(this, "Thêm mới thành công");
        } catch (Exception e) {
            e.printStackTrace();
            Msgbox.alert(this, "Lỗi truy vấn dữ liệu");
        }
    }
    
    void CapNhat(){
        NhanVien nv = new NhanVien();
        try {
            dao.update(nv);
            this.fillToTable();
            this.clear();
            Msgbox.alert(this, "Cập nhật thành công");
        } catch (Exception e) {
            e.printStackTrace();
            Msgbox.alert(this, "Lỗi truy vấn dữ liệu");
        }
    }
    
    void xoa(){
        if (Msgbox.confirm(this, "Bạn có muốn xóa hay không?")) {
            String msnv = txtMaNV.getText();
            try {
                dao.delete(msnv);
                this.fillToTable();
                this.clear();
                Msgbox.alert(this, "Xóa thành công");
            } catch (Exception e) {
                Msgbox.alert(this, "Lỗi truy vấn dữ liệu");
            }
        }
    }
    void edit(){
        try {
            String manv = (String) tblList.getValueAt(this.index, 0);
            NhanVien model = dao.selectById(manv);
            if (model != null) {
                this.setModel(model);
                this.setStatus(false);
            }
        } catch (Exception e) {
            Msgbox.alert(this, "Lỗi truy vấn dữ liệu");
        }
    }
     public String matKhauToSao(String pass){
        String sao="";
        for(int i=0;i<pass.length();i++){
            sao+="*";
        }
        return sao;
    }
    
    void clear(){
//        txtTen.setText("");
//        txtCMND.setText("");
//        txtDiaChi.setText("");
//        txtMaNV.setText("");
//        txtMatKhau.setText("");
//        txtSDT.setText("");
//        txtNgaySinh.setText("");
           this.setModel(new NhanVien());
          this.setStatus(true);
        
    }



    void setModel(NhanVien model){
        txtMaNV.setText(model.getMaNV());
        txtTen.setText(model.getHoTen());
        boolean tt = model.isGioiTinh();
        rdoNam.setSelected(model.isGioiTinh());
        rdoNu.setSelected(!model.isGioiTinh());
//        txtNgaySinh.setText(XDate.toString(model.getNgaysinh()));
        txtCMND.setText(model.getSoCCCD());
        txtDiaChi.setText(model.getDiaChi());
        txtSDT.setText(model.getSoDienThoai());
        rdoNhanVien.setSelected(!model.isVaiTro());
        rdoQuanLy.setSelected(model.isVaiTro());
        if (model.getHinhNV() != null ) {
            lblHinhNV.setIcon(XImage.read(model.getHinhNV()));
        } else {
            lblHinhNV.setIcon(XImage.read("logoM_128px.png"));
        }
        txtMatKhau.setText(model.getMatKhau());
        
    }
//    
    NhanVien getModel(){
        NhanVien model = new NhanVien();
        model.setMaNV(txtMaNV.getText());
        model.setHoTen(txtTen.getText());
        model.setGioiTinh(rdoNam.isSelected());
//        model.setNgaysinh(XDate.toDate(txtNgaySinh.getText()));
        model.setSoCCCD(txtCMND.getText());
        model.setDiaChi(txtDiaChi.getText());
        model.setSoDienThoai(txtSDT.getText());
        model.setVaiTro(rdoQuanLy.isSelected());
        model.setHinhNV(lblHinhNV.getToolTipText());
        model.setMatKhau(txtMatKhau.getText());
        return model;
    }
    
    void selectImage(){
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            XImage.save(file); 
                lblHinhNV.setIcon(XImage.read(file.getName()));
                lblHinhNV.setToolTipText(file.getName());
            
        } 
    }

    void setStatus(boolean inserttable){
        btnSuaNV.setEnabled(!inserttable);
        btnThemNV.setEnabled(inserttable);
        btnXoa.setEnabled(!inserttable);
    }
    
//    public boolean checkNullHinh(){
//        if (lblHinhNV.getToolTipText() != null) {
//            return true;
//        } else {
//            Msgbox.alert(this, "Không được để trống hình");
//            return false;
//        }
//    }
//    
//    public void chẹckTrungMa(){
//        for (int i = 0; i < tblList.getRowCount(); i++) {
//            if (tblList.getValueAt(i, 0).toString().equalsIgnoreCase(txtMaNV.getText())) {
//                 Msgbox.alert(this, "Mã đã tồn tại");
//                txtMaNV.setBackground(Color.red);
//            }
//            else{
//                txtMaNV.setBackground(Color.white);
//            }
//        }
//    }
    
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
        btnXoa = new javax.swing.JButton();
        txtMatKhau = new javax.swing.JPasswordField();

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
                "Mã NV", "Họ Tên", "Giới Tính", "Ngày Sinh", "CMND", "Địa Chỉ", "SĐT", "Vai Trò", "Hình", "Mật Khẩu"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
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

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 774, Short.MAX_VALUE)
            .addComponent(jSeparator2)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 547, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(34, 116, 173));

        jLabel12.setFont(new java.awt.Font("Baloo 2 ExtraBold", 0, 24)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("CẬP NHẬT");

        txtMaNV.setBackground(new java.awt.Color(34, 116, 173));
        txtMaNV.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtMaNV.setForeground(new java.awt.Color(255, 255, 255));
        txtMaNV.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)));

        lblMaMH.setFont(new java.awt.Font("Baloo Chettan 2", 1, 14)); // NOI18N
        lblMaMH.setForeground(new java.awt.Color(255, 255, 255));
        lblMaMH.setText("Mã nhân viên");

        lblHSX.setFont(new java.awt.Font("Baloo Chettan 2", 1, 14)); // NOI18N
        lblHSX.setForeground(new java.awt.Color(255, 255, 255));
        lblHSX.setText("Tên nhân viên");

        lblTenMH.setFont(new java.awt.Font("Baloo Chettan 2", 1, 14)); // NOI18N
        lblTenMH.setForeground(new java.awt.Color(255, 255, 255));
        lblTenMH.setText("Giới tính");

        txtNgaySinh.setBackground(new java.awt.Color(34, 116, 173));
        txtNgaySinh.setForeground(new java.awt.Color(255, 255, 255));
        txtNgaySinh.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)));

        txtCMND.setBackground(new java.awt.Color(34, 116, 173));
        txtCMND.setForeground(new java.awt.Color(255, 255, 255));
        txtCMND.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)));

        txtDiaChi.setBackground(new java.awt.Color(34, 116, 173));
        txtDiaChi.setForeground(new java.awt.Color(255, 255, 255));
        txtDiaChi.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)));

        lblRAM.setFont(new java.awt.Font("Baloo Chettan 2", 1, 14)); // NOI18N
        lblRAM.setForeground(new java.awt.Color(255, 255, 255));
        lblRAM.setText("Ngày sinh");

        lblDL.setFont(new java.awt.Font("Baloo Chettan 2", 1, 14)); // NOI18N
        lblDL.setForeground(new java.awt.Color(255, 255, 255));
        lblDL.setText("CMND");

        lblMS.setFont(new java.awt.Font("Baloo Chettan 2", 1, 14)); // NOI18N
        lblMS.setForeground(new java.awt.Color(255, 255, 255));
        lblMS.setText("Địa chỉ");

        lblQG.setFont(new java.awt.Font("Baloo Chettan 2", 1, 14)); // NOI18N
        lblQG.setForeground(new java.awt.Color(255, 255, 255));
        lblQG.setText("SĐT");

        lblDVT.setFont(new java.awt.Font("Baloo Chettan 2", 1, 14)); // NOI18N
        lblDVT.setForeground(new java.awt.Color(255, 255, 255));
        lblDVT.setText("Vai trò");

        lblGM.setFont(new java.awt.Font("Baloo Chettan 2", 1, 14)); // NOI18N
        lblGM.setForeground(new java.awt.Color(255, 255, 255));
        lblGM.setText("Mật khẩu");

        lblHinhNV.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHinhNV.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)));
        lblHinhNV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblHinhNVMouseClicked(evt);
            }
        });

        lblHMH.setFont(new java.awt.Font("Baloo Chettan 2", 1, 14)); // NOI18N
        lblHMH.setForeground(new java.awt.Color(255, 255, 255));
        lblHMH.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHMH.setText("Hình nhân viên");

        btnClear.setBackground(new java.awt.Color(34, 116, 173));
        btnClear.setFont(new java.awt.Font("Baloo 2", 1, 12)); // NOI18N
        btnClear.setForeground(new java.awt.Color(255, 255, 255));
        btnClear.setText("Clear");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        btnThemNV.setBackground(new java.awt.Color(34, 116, 173));
        btnThemNV.setFont(new java.awt.Font("Baloo 2", 1, 12)); // NOI18N
        btnThemNV.setForeground(new java.awt.Color(255, 255, 255));
        btnThemNV.setText("Thêm");
        btnThemNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemNVActionPerformed(evt);
            }
        });

        btnSuaNV.setBackground(new java.awt.Color(34, 116, 173));
        btnSuaNV.setFont(new java.awt.Font("Baloo 2", 1, 12)); // NOI18N
        btnSuaNV.setForeground(new java.awt.Color(255, 255, 255));
        btnSuaNV.setText("Sửa");
        btnSuaNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaNVActionPerformed(evt);
            }
        });

        txtTen.setBackground(new java.awt.Color(34, 116, 173));
        txtTen.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtTen.setForeground(new java.awt.Color(255, 255, 255));
        txtTen.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)));

        buttonGroup1.add(rdoNam);
        rdoNam.setSelected(true);
        rdoNam.setText("Nam");

        buttonGroup1.add(rdoNu);
        rdoNu.setText("Nữ");

        txtSDT.setBackground(new java.awt.Color(34, 116, 173));
        txtSDT.setForeground(new java.awt.Color(255, 255, 255));
        txtSDT.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)));

        buttonGroup2.add(rdoQuanLy);
        rdoQuanLy.setSelected(true);
        rdoQuanLy.setText("Quản lý");

        buttonGroup2.add(rdoNhanVien);
        rdoNhanVien.setText("Nhân viên");

        btnXoa.setBackground(new java.awt.Color(34, 116, 173));
        btnXoa.setFont(new java.awt.Font("Baloo 2", 1, 12)); // NOI18N
        btnXoa.setForeground(new java.awt.Color(255, 255, 255));
        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        txtMatKhau.setBackground(new java.awt.Color(34, 116, 173));
        txtMatKhau.setForeground(new java.awt.Color(255, 255, 255));
        txtMatKhau.setText("jPasswordField1");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblHSX, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblMaMH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblTenMH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblRAM, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblDL, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblMS, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblQG, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblDVT, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblGM, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtMaNV, javax.swing.GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE)
                                .addComponent(txtNgaySinh)
                                .addComponent(txtCMND)
                                .addComponent(txtDiaChi, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(txtTen)
                                .addComponent(txtSDT))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(rdoNam)
                                .addGap(10, 10, 10)
                                .addComponent(rdoNu)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblHinhNV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblHMH, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(rdoQuanLy)
                                .addGap(18, 18, 18)
                                .addComponent(rdoNhanVien))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(btnThemNV)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnSuaNV)
                                .addGap(12, 12, 12)
                                .addComponent(btnXoa)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnClear))
                            .addComponent(txtMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 180, Short.MAX_VALUE)))
                .addContainerGap())
        );
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
                            .addComponent(lblRAM)))
                    .addComponent(lblHinhNV, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCMND, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDL)
                    .addComponent(lblHMH))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMS))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblQG)
                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDVT)
                    .addComponent(rdoQuanLy)
                    .addComponent(rdoNhanVien))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblGM, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(txtMatKhau))
                .addGap(30, 30, 30)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThemNV)
                    .addComponent(btnSuaNV)
                    .addComponent(btnClear)
                    .addComponent(btnXoa))
                .addContainerGap(77, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblListMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() ==2) {
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
        this.check();
        this.them();
    }//GEN-LAST:event_btnThemNVActionPerformed

    private void btnSuaNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaNVActionPerformed
        // TODO add your handling code here:
        this.check();
        this.CapNhat();
    }//GEN-LAST:event_btnSuaNVActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        if (Auth.user.isVaiTro()) {
            xoa();
        } else {
            Msgbox.alert(this, "Chỉ trưởng phòng mới có thể xóa");
        }
    }//GEN-LAST:event_btnXoaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnSuaNV;
    private javax.swing.JButton btnThemNV;
    private javax.swing.JButton btnXoa;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lblDL;
    private javax.swing.JLabel lblDVT;
    private javax.swing.JLabel lblGM;
    private javax.swing.JLabel lblHMH;
    private javax.swing.JLabel lblHSX;
    private javax.swing.JLabel lblHinhNV;
    private javax.swing.JLabel lblMS;
    private javax.swing.JLabel lblMaMH;
    private javax.swing.JLabel lblQG;
    private javax.swing.JLabel lblRAM;
    private javax.swing.JLabel lblTenMH;
    private javax.swing.JRadioButton rdoNam;
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
