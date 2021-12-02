/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mobilez.ui;

import com.mobilez.utils.JdbcHelper;
import com.mobilez.utils.Msgbox;
import com.mobilez.utils.StringToPrice;
import java.awt.Color;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author uhtku
 */
public class FrmKhuyenMai extends javax.swing.JPanel {

    /**
     * Creates new form FrmHangHoa
     */
    DefaultTableModel modelTbl;
    int index = -1;

    public FrmKhuyenMai() {
        initComponents();
        modelTbl = (DefaultTableModel) tblList.getModel();
        this.fillTable();
        this.setButtonAdd();
    }

    //My method--------------------------------------------------------------------------------------------------
    //Sreach
    private void searchQH() {
        try {

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //check
    private boolean chkSua() {
        return false;
    }

    private boolean chkThem() {
        try {
            String sql = "SELECT * FROM QUAYHANG where MAQH like ?";
            ResultSet rs = JdbcHelper.query(sql, txtMaKM.getText());
            while (rs.next()) {
                Msgbox.alert(null, "Mã quầy hàng đã tồn tại!");
                lblMaKM.setForeground(Color.red);
                txtMaKM.requestFocus();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            lblMaKM.setForeground(Color.red);
        }
        if (txtMaKM.getText().trim().equals("")) {
            txtMaKM.setText("");
            txtMaKM.requestFocus();
            Msgbox.alert(null, "Mã quầy hàng không được để trống!");
            lblMaKM.setForeground(Color.red);
            return true;
        } else {
            lblMaKM.setForeground(Color.white);
        }
        if (txtMaKM.getText().trim().length() < 4 || txtMaKM.getText().trim().length() > 10) {
            Msgbox.alert(this, "Mã khách hàng từ 4 đến 10 ký tự!");
            this.txtMaKM.requestFocus();
            txtMaKM.setForeground(Color.red);
            return true;
        } else {
            txtMaKM.setForeground(Color.white);
        }
        if (txtTenKM.getText().trim().equals("")) {
            txtTenKM.setText("");
            txtTenKM.requestFocus();
            Msgbox.alert(null, "Tên quầy hàng không được để trống!");
            lblTenKM.setForeground(Color.red);
            return true;
        } else {
            lblMaKM.setForeground(Color.white);
        }
        return false;
    }

    private void sua() {
        try {
            String sql = "insert into KHUYENMAI values (?,?,?,?,?,?,?,?)";
            
            String maKM = txtMaKM.getText();
            String tenKM = txtTenKM.getText();
            boolean kieuKM = rdoPT1.isSelected();
            int mucKM = Integer.parseInt(txtMucKM.getText()), dieuKien = Integer.parseInt(txtDieuKien.getText()), giamToiDa = Integer.parseInt(txtGiamToiDa.getText());
            String ngayBD = txtNgayBD.getText(), ngayKT = txtNgayKT.getText();
            
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            int s = JdbcHelper.update(sql, maKM, tenKM, kieuKM, mucKM, dieuKien, giamToiDa, sdf.parse(ngayBD), sdf.parse(ngayKT));
            
            if (s > 0) {
                Msgbox.alert(null, "Thêm thành công!");
                clearForm();
            } else {
                Msgbox.alert(null, "Thêm thất bại!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void them() {
        try {
            String sql = "insert into KHUYENMAI values (?,?,?,?,?,?,?,?)";
            String maKM = txtMaKM.getText();
            String tenKM = txtTenKM.getText();
            boolean kieuKM = rdoPT1.isSelected();
            int mucKM = Integer.parseInt(txtMucKM.getText()), dieuKien = Integer.parseInt(txtDieuKien.getText()), giamToiDa = Integer.parseInt(txtGiamToiDa.getText());
            String ngayBD = txtNgayBD.getText(), ngayKT = txtNgayKT.getText();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            int s = JdbcHelper.update(sql, maKM, tenKM, kieuKM, mucKM, dieuKien, giamToiDa, sdf.parse(ngayBD), sdf.parse(ngayKT));
            if (s > 0) {
                Msgbox.alert(null, "Thêm thành công!");
                clearForm();
            } else {
                Msgbox.alert(null, "Thêm thất bại!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showDetail() {
        txtMaKM.setText(tblList.getValueAt(index, 0).toString());
        txtTenKM.setText(tblList.getValueAt(index, 1).toString());
        if (tblList.getValueAt(index, 2).toString().equalsIgnoreCase("Phần trăm")) {
            rdoPT1.setSelected(true);
        }else{
            rdoTien0.setSelected(true);
        }
        txtMucKM.setText(tblList.getValueAt(index, 3).toString());
        txtDieuKien.setText(tblList.getValueAt(index, 4).toString());
        txtGiamToiDa.setText(tblList.getValueAt(index, 5).toString());
        txtNgayBD.setText(tblList.getValueAt(index, 6).toString());
        txtNgayKT.setText(tblList.getValueAt(index, 7).toString());
        this.setButtonUpdate();
        tblList.setRowSelectionAllowed(true);
        tblList.setRowSelectionInterval(index, index);

    }

    private void fillTable() {
        try {
            String sql = "SELECT MAKM,TENKM,KIEUKM,MUCKM,DIEUKIEN,GIAMTOIDA,\n"
                    + "convert(varchar,NGAYBD,103) as NGAYBD,\n"
                    + "convert(varchar,NGAYKT,103) as NGAYKT\n"
                    + "FROM KHUYENMAI";
            ResultSet rs = JdbcHelper.query(sql);
            modelTbl.setRowCount(0);
            while (rs.next()) {
                String dieuKien = rs.getInt("DIEUKIEN") + "";
                String giamToiDa = rs.getInt("GIAMTOIDA") + "";
                dieuKien = StringToPrice.getPrice(dieuKien);
                giamToiDa = StringToPrice.getPrice(giamToiDa);
                modelTbl.addRow(new Object[]{
                    rs.getString("MAKM"),
                    rs.getString("TENKM"),
                    rs.getBoolean("KIEUKM") ? "Phần trăm" : "Tiền",
                    rs.getInt("MUCKM"),
                    dieuKien,
                    giamToiDa,
                    rs.getString("NGAYBD"),
                    rs.getString("NGAYKT")
                });
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void clearForm() {
        txtMaKM.setText("");
        txtTenKM.setText("");
        rdoPT1.setSelected(true);
        txtMucKM.setText("");
        txtDieuKien.setText("");
        txtGiamToiDa.setText("");
        txtNgayBD.setText("");
        txtNgayKT.setText("");
        this.setButtonAdd();
        index = -1;
        tblList.setRowSelectionAllowed(false);
    }

    private void setButtonAdd() {
        txtMaKM.setEditable(true);
        btnThem.setEnabled(true);
        btnSua.setEnabled(false);
    }

    private void setButtonUpdate() {
        btnThem.setEnabled(false);
        btnSua.setEnabled(true);
        txtMaKM.setEditable(false);
    }
//------------------------------------------------------------------------------------------------------------//

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
        lblMaKM = new javax.swing.JLabel();
        txtMaKM = new javax.swing.JTextField();
        lblTenKM = new javax.swing.JLabel();
        txtTenKM = new javax.swing.JTextField();
        lblKieuKM = new javax.swing.JLabel();
        rdoPT1 = new javax.swing.JRadioButton();
        rdoTien0 = new javax.swing.JRadioButton();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnTaoMoi = new javax.swing.JButton();
        lblNgayBD = new javax.swing.JLabel();
        txtNgayBD = new javax.swing.JTextField();
        lblNgayKT = new javax.swing.JLabel();
        txtNgayKT = new javax.swing.JTextField();
        txtMucKM = new javax.swing.JTextField();
        lblMucKM = new javax.swing.JLabel();
        lblDieuKien = new javax.swing.JLabel();
        txtDieuKien = new javax.swing.JTextField();
        lblGiamToiDa = new javax.swing.JLabel();
        txtGiamToiDa = new javax.swing.JTextField();

        setBackground(new java.awt.Color(34, 116, 173));

        jLabel1.setFont(new java.awt.Font("Baloo 2 ExtraBold", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("DANH SÁCH KHUYẾN MÃI");

        tblList.setBackground(new java.awt.Color(34, 116, 173));
        tblList.setFont(new java.awt.Font("Baloo 2", 0, 13)); // NOI18N
        tblList.setForeground(new java.awt.Color(255, 255, 255));
        tblList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã KM", "Tên KM", "Kiểu KM", "Mức KM", "Điều kiện", "Giảm tối đa", "Ngày bắt đầu", "Ngày kết thúc"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
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

        lblMaKM.setFont(new java.awt.Font("Baloo Chettan 2", 1, 14)); // NOI18N
        lblMaKM.setForeground(new java.awt.Color(255, 255, 255));
        lblMaKM.setText("Mã khuyến mãi");

        txtMaKM.setBackground(new java.awt.Color(34, 116, 173));
        txtMaKM.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtMaKM.setForeground(new java.awt.Color(255, 255, 255));
        txtMaKM.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)));
        txtMaKM.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtMaKMtxtDungLuongKeyPressed(evt);
            }
        });

        lblTenKM.setFont(new java.awt.Font("Baloo Chettan 2", 1, 14)); // NOI18N
        lblTenKM.setForeground(new java.awt.Color(255, 255, 255));
        lblTenKM.setText("Tên khuyến mãi");

        txtTenKM.setBackground(new java.awt.Color(34, 116, 173));
        txtTenKM.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtTenKM.setForeground(new java.awt.Color(255, 255, 255));
        txtTenKM.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)));
        txtTenKM.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTenKMtxtDungLuongKeyPressed(evt);
            }
        });

        lblKieuKM.setFont(new java.awt.Font("Baloo Chettan 2", 1, 14)); // NOI18N
        lblKieuKM.setForeground(new java.awt.Color(255, 255, 255));
        lblKieuKM.setText("Kiểu khuyến mãi");

        grpStatus.add(rdoPT1);
        rdoPT1.setSelected(true);
        rdoPT1.setText("Phần trăm");

        grpStatus.add(rdoTien0);
        rdoTien0.setText("Tiền");

        btnThem.setBackground(new java.awt.Color(34, 116, 173));
        btnThem.setFont(new java.awt.Font("Baloo 2", 1, 12)); // NOI18N
        btnThem.setForeground(new java.awt.Color(255, 255, 255));
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setBackground(new java.awt.Color(34, 116, 173));
        btnSua.setFont(new java.awt.Font("Baloo 2", 1, 12)); // NOI18N
        btnSua.setForeground(new java.awt.Color(255, 255, 255));
        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnTaoMoi.setBackground(new java.awt.Color(34, 116, 173));
        btnTaoMoi.setFont(new java.awt.Font("Baloo 2", 1, 12)); // NOI18N
        btnTaoMoi.setForeground(new java.awt.Color(255, 255, 255));
        btnTaoMoi.setText("Tạo mới");
        btnTaoMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoMoiActionPerformed(evt);
            }
        });

        lblNgayBD.setFont(new java.awt.Font("Baloo Chettan 2", 1, 14)); // NOI18N
        lblNgayBD.setForeground(new java.awt.Color(255, 255, 255));
        lblNgayBD.setText("Ngày bắt đầu");

        txtNgayBD.setBackground(new java.awt.Color(34, 116, 173));
        txtNgayBD.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtNgayBD.setForeground(new java.awt.Color(255, 255, 255));
        txtNgayBD.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)));
        txtNgayBD.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNgayBDtxtDungLuongKeyPressed(evt);
            }
        });

        lblNgayKT.setFont(new java.awt.Font("Baloo Chettan 2", 1, 14)); // NOI18N
        lblNgayKT.setForeground(new java.awt.Color(255, 255, 255));
        lblNgayKT.setText("Ngày kết thúc");

        txtNgayKT.setBackground(new java.awt.Color(34, 116, 173));
        txtNgayKT.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtNgayKT.setForeground(new java.awt.Color(255, 255, 255));
        txtNgayKT.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)));
        txtNgayKT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNgayKTtxtDungLuongKeyPressed(evt);
            }
        });

        txtMucKM.setBackground(new java.awt.Color(34, 116, 173));
        txtMucKM.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtMucKM.setForeground(new java.awt.Color(255, 255, 255));
        txtMucKM.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)));
        txtMucKM.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtMucKMtxtDungLuongKeyPressed(evt);
            }
        });

        lblMucKM.setFont(new java.awt.Font("Baloo Chettan 2", 1, 14)); // NOI18N
        lblMucKM.setForeground(new java.awt.Color(255, 255, 255));
        lblMucKM.setText("Mức khuyến mãi");

        lblDieuKien.setFont(new java.awt.Font("Baloo Chettan 2", 1, 14)); // NOI18N
        lblDieuKien.setForeground(new java.awt.Color(255, 255, 255));
        lblDieuKien.setText("Điều kiện KM");

        txtDieuKien.setBackground(new java.awt.Color(34, 116, 173));
        txtDieuKien.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtDieuKien.setForeground(new java.awt.Color(255, 255, 255));
        txtDieuKien.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)));
        txtDieuKien.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDieuKientxtDungLuongKeyPressed(evt);
            }
        });

        lblGiamToiDa.setFont(new java.awt.Font("Baloo Chettan 2", 1, 14)); // NOI18N
        lblGiamToiDa.setForeground(new java.awt.Color(255, 255, 255));
        lblGiamToiDa.setText("Giảm tối đa");

        txtGiamToiDa.setBackground(new java.awt.Color(34, 116, 173));
        txtGiamToiDa.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtGiamToiDa.setForeground(new java.awt.Color(255, 255, 255));
        txtGiamToiDa.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)));
        txtGiamToiDa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtGiamToiDatxtDungLuongKeyPressed(evt);
            }
        });

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
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 655, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 515, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblMaKM, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtMaKM))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblTenKM, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtTenKM))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblMucKM, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtMucKM))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblDieuKien, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtDieuKien))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblGiamToiDa, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtGiamToiDa))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblNgayBD, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtNgayBD))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblNgayKT, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtNgayKT))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblKieuKM, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(rdoPT1)
                                        .addGap(18, 18, 18)
                                        .addComponent(rdoTien0))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(124, 124, 124)
                                        .addComponent(btnThem)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnSua)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnTaoMoi)))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnSua, btnTaoMoi, btnThem});

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
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 479, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMaKM, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblMaKM))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTenKM, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTenKM))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblKieuKM)
                            .addComponent(rdoPT1)
                            .addComponent(rdoTien0))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMucKM, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblMucKM))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtDieuKien, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblDieuKien))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtGiamToiDa, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblGiamToiDa))
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNgayBD, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblNgayBD))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNgayKT, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblNgayKT))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnThem)
                            .addComponent(btnSua)
                            .addComponent(btnTaoMoi))
                        .addContainerGap())))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblListMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 2) {
            index = tblList.getSelectedRow();
            this.showDetail();
        }


    }//GEN-LAST:event_tblListMouseClicked

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        // TODO add your handling code here:
        this.searchQH();
    }//GEN-LAST:event_txtSearchKeyReleased

    private void txtMaKMtxtDungLuongKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMaKMtxtDungLuongKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaKMtxtDungLuongKeyPressed

    private void txtTenKMtxtDungLuongKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTenKMtxtDungLuongKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenKMtxtDungLuongKeyPressed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        if (this.chkThem()) {
            return;
        }
        this.them();
        this.clearForm();
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        if (chkSua()) {
            return;
        }

    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnTaoMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoMoiActionPerformed
        // TODO add your handling code here:
        if (Msgbox.confirm(null, "Bạn có muốn tạo mới không?")) {
            clearForm();
        }

    }//GEN-LAST:event_btnTaoMoiActionPerformed

    private void txtNgayBDtxtDungLuongKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNgayBDtxtDungLuongKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNgayBDtxtDungLuongKeyPressed

    private void txtNgayKTtxtDungLuongKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNgayKTtxtDungLuongKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNgayKTtxtDungLuongKeyPressed

    private void txtMucKMtxtDungLuongKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMucKMtxtDungLuongKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMucKMtxtDungLuongKeyPressed

    private void txtDieuKientxtDungLuongKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDieuKientxtDungLuongKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDieuKientxtDungLuongKeyPressed

    private void txtGiamToiDatxtDungLuongKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGiamToiDatxtDungLuongKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGiamToiDatxtDungLuongKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnTaoMoi;
    private javax.swing.JButton btnThem;
    private javax.swing.ButtonGroup grpStatus;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblDieuKien;
    private javax.swing.JLabel lblGiamToiDa;
    private javax.swing.JLabel lblKieuKM;
    private javax.swing.JLabel lblMaKM;
    private javax.swing.JLabel lblMucKM;
    private javax.swing.JLabel lblNgayBD;
    private javax.swing.JLabel lblNgayKT;
    private javax.swing.JLabel lblTenKM;
    private javax.swing.JRadioButton rdoPT1;
    private javax.swing.JRadioButton rdoTien0;
    private javax.swing.JTable tblList;
    private javax.swing.JTextField txtDieuKien;
    private javax.swing.JTextField txtGiamToiDa;
    private javax.swing.JTextField txtMaKM;
    private javax.swing.JTextField txtMucKM;
    private javax.swing.JTextField txtNgayBD;
    private javax.swing.JTextField txtNgayKT;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtTenKM;
    // End of variables declaration//GEN-END:variables
}
