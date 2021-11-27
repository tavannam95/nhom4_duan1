/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mobilez.ui;

import com.mobilez.dao.NhaCungCapDao;
import com.mobilez.models.NhaCungCap;
import com.mobilez.utils.Msgbox;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author uhtku
 */
public class FrmNhaCungCap extends javax.swing.JPanel {

    DefaultTableModel model = new DefaultTableModel();
    NhaCungCapDao ncdao = new NhaCungCapDao();
    int index = -1;

    /**
     * Creates new form FrmNhaCungCap
     */
    public FrmNhaCungCap() {
        initComponents();
        model = (DefaultTableModel) this.tblncc.getModel();
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
        tblncc = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        txtmancc = new javax.swing.JTextField();
        lblMaMH = new javax.swing.JLabel();
        lblHSX = new javax.swing.JLabel();
        lblTenMH = new javax.swing.JLabel();
        lblDL = new javax.swing.JLabel();
        lblMS = new javax.swing.JLabel();
        lblQG = new javax.swing.JLabel();
        lblSL = new javax.swing.JLabel();
        btnclear = new javax.swing.JButton();
        btnadd = new javax.swing.JButton();
        btnupdate = new javax.swing.JButton();
        txttenncc = new javax.swing.JTextField();
        txtdiachi = new javax.swing.JTextField();
        txtsodt = new javax.swing.JTextField();
        txtsofax = new javax.swing.JTextField();
        txtwebsite = new javax.swing.JTextField();
        txtemail = new javax.swing.JTextField();

        setBackground(new java.awt.Color(34, 116, 173));

        jPanel1.setBackground(new java.awt.Color(34, 116, 173));

        jLabel1.setFont(new java.awt.Font("Baloo 2 ExtraBold", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("DANH SÁCH MẶT HÀNG");

        tblncc.setBackground(new java.awt.Color(34, 116, 173));
        tblncc.setFont(new java.awt.Font("Baloo 2", 0, 13)); // NOI18N
        tblncc.setForeground(new java.awt.Color(255, 255, 255));
        tblncc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã nhà cung cấp", "Tên nhà cung cấp", "Địa Chỉ", "Số Điện Thoại", "Sô Fax", "website", "Email"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblncc.setToolTipText("");
        tblncc.setGridColor(new java.awt.Color(255, 255, 255));
        tblncc.setRowHeight(25);
        tblncc.setSelectionBackground(new java.awt.Color(51, 51, 51));
        tblncc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblnccMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblncc);

        jLabel2.setFont(new java.awt.Font("Baloo 2 ExtraBold", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("CẬP NHẬT");

        txtmancc.setBackground(new java.awt.Color(34, 116, 173));
        txtmancc.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtmancc.setForeground(new java.awt.Color(255, 255, 255));
        txtmancc.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)));
        txtmancc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtmancctxtDungLuongKeyPressed(evt);
            }
        });

        lblMaMH.setFont(new java.awt.Font("Baloo Chettan 2", 1, 14)); // NOI18N
        lblMaMH.setForeground(new java.awt.Color(255, 255, 255));
        lblMaMH.setText("Mã NCC");

        lblHSX.setFont(new java.awt.Font("Baloo Chettan 2", 1, 14)); // NOI18N
        lblHSX.setForeground(new java.awt.Color(255, 255, 255));
        lblHSX.setText("Tên NCC");

        lblTenMH.setFont(new java.awt.Font("Baloo Chettan 2", 1, 14)); // NOI18N
        lblTenMH.setForeground(new java.awt.Color(255, 255, 255));
        lblTenMH.setText("Địa Chỉ");

        lblDL.setFont(new java.awt.Font("Baloo Chettan 2", 1, 14)); // NOI18N
        lblDL.setForeground(new java.awt.Color(255, 255, 255));
        lblDL.setText("Số Điện Thoại");

        lblMS.setFont(new java.awt.Font("Baloo Chettan 2", 1, 14)); // NOI18N
        lblMS.setForeground(new java.awt.Color(255, 255, 255));
        lblMS.setText("Số FAX");

        lblQG.setFont(new java.awt.Font("Baloo Chettan 2", 1, 14)); // NOI18N
        lblQG.setForeground(new java.awt.Color(255, 255, 255));
        lblQG.setText("Website");

        lblSL.setFont(new java.awt.Font("Baloo Chettan 2", 1, 14)); // NOI18N
        lblSL.setForeground(new java.awt.Color(255, 255, 255));
        lblSL.setText("Email");

        btnclear.setBackground(new java.awt.Color(34, 116, 173));
        btnclear.setFont(new java.awt.Font("Baloo 2", 1, 12)); // NOI18N
        btnclear.setForeground(new java.awt.Color(255, 255, 255));
        btnclear.setText("Tạo mới");
        btnclear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnclearActionPerformed(evt);
            }
        });

        btnadd.setBackground(new java.awt.Color(34, 116, 173));
        btnadd.setFont(new java.awt.Font("Baloo 2", 1, 12)); // NOI18N
        btnadd.setForeground(new java.awt.Color(255, 255, 255));
        btnadd.setText("Thêm");
        btnadd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnaddActionPerformed(evt);
            }
        });

        btnupdate.setBackground(new java.awt.Color(34, 116, 173));
        btnupdate.setFont(new java.awt.Font("Baloo 2", 1, 12)); // NOI18N
        btnupdate.setForeground(new java.awt.Color(255, 255, 255));
        btnupdate.setText("Sửa");
        btnupdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnupdateActionPerformed(evt);
            }
        });

        txttenncc.setBackground(new java.awt.Color(34, 116, 173));
        txttenncc.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txttenncc.setForeground(new java.awt.Color(255, 255, 255));
        txttenncc.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)));
        txttenncc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txttenncctxtDungLuongKeyPressed(evt);
            }
        });

        txtdiachi.setBackground(new java.awt.Color(34, 116, 173));
        txtdiachi.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtdiachi.setForeground(new java.awt.Color(255, 255, 255));
        txtdiachi.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)));
        txtdiachi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtdiachitxtDungLuongKeyPressed(evt);
            }
        });

        txtsodt.setBackground(new java.awt.Color(34, 116, 173));
        txtsodt.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtsodt.setForeground(new java.awt.Color(255, 255, 255));
        txtsodt.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)));
        txtsodt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtsodttxtDungLuongKeyPressed(evt);
            }
        });

        txtsofax.setBackground(new java.awt.Color(34, 116, 173));
        txtsofax.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtsofax.setForeground(new java.awt.Color(255, 255, 255));
        txtsofax.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)));
        txtsofax.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtsofaxActionPerformed(evt);
            }
        });
        txtsofax.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtsofaxtxtDungLuongKeyPressed(evt);
            }
        });

        txtwebsite.setBackground(new java.awt.Color(34, 116, 173));
        txtwebsite.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtwebsite.setForeground(new java.awt.Color(255, 255, 255));
        txtwebsite.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)));
        txtwebsite.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtwebsiteActionPerformed(evt);
            }
        });
        txtwebsite.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtwebsitetxtDungLuongKeyPressed(evt);
            }
        });

        txtemail.setBackground(new java.awt.Color(34, 116, 173));
        txtemail.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtemail.setForeground(new java.awt.Color(255, 255, 255));
        txtemail.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)));
        txtemail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtemailActionPerformed(evt);
            }
        });
        txtemail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtemailtxtDungLuongKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 594, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(62, 62, 62)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblMaMH, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblHSX)
                                    .addComponent(lblTenMH, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblDL, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblMS, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblQG, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblSL))))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(33, 33, 33)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtemail, javax.swing.GroupLayout.DEFAULT_SIZE, 467, Short.MAX_VALUE)
                                            .addComponent(txtwebsite)
                                            .addComponent(txtsofax)
                                            .addComponent(txtsodt)
                                            .addComponent(txtmancc)
                                            .addComponent(txttenncc)
                                            .addComponent(txtdiachi))
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(162, 162, 162)
                                .addComponent(btnadd)
                                .addGap(36, 36, 36)
                                .addComponent(btnupdate)
                                .addGap(52, 52, 52)
                                .addComponent(btnclear)
                                .addGap(0, 100, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtmancc, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblMaMH))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblHSX)
                            .addComponent(txttenncc, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblTenMH)
                            .addComponent(txtdiachi, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblDL)
                            .addComponent(txtsodt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblMS)
                            .addComponent(txtsofax, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblQG)
                            .addComponent(txtwebsite, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblSL)
                            .addComponent(txtemail, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(62, 62, 62)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnadd)
                            .addComponent(btnupdate)
                            .addComponent(btnclear))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 531, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(46, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtemailtxtDungLuongKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtemailtxtDungLuongKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtemailtxtDungLuongKeyPressed

    private void txtemailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtemailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtemailActionPerformed

    private void txtwebsitetxtDungLuongKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtwebsitetxtDungLuongKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtwebsitetxtDungLuongKeyPressed

    private void txtwebsiteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtwebsiteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtwebsiteActionPerformed

    private void txtsofaxtxtDungLuongKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtsofaxtxtDungLuongKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtsofaxtxtDungLuongKeyPressed

    private void txtsofaxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtsofaxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtsofaxActionPerformed

    private void txtsodttxtDungLuongKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtsodttxtDungLuongKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtsodttxtDungLuongKeyPressed

    private void txtdiachitxtDungLuongKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtdiachitxtDungLuongKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtdiachitxtDungLuongKeyPressed

    private void txttenncctxtDungLuongKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttenncctxtDungLuongKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txttenncctxtDungLuongKeyPressed

    private void btnupdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnupdateActionPerformed
        // TODO add your handling code here:
        if (this.checkform()) {
            this.update();
            this.filltable();
            this.clear();
        }

    }//GEN-LAST:event_btnupdateActionPerformed

    private void btnaddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnaddActionPerformed
        // TODO add your handling code here:
        if (this.checkform()) {
            this.insert();
            this.filltable();
            this.clear();
        }

    }//GEN-LAST:event_btnaddActionPerformed

    private void btnclearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnclearActionPerformed
        // TODO add your handling code here:
        this.clear();

    }//GEN-LAST:event_btnclearActionPerformed

    private void txtmancctxtDungLuongKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtmancctxtDungLuongKeyPressed
        // TODO add your handling code here:

    }//GEN-LAST:event_txtmancctxtDungLuongKeyPressed

    private void tblnccMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblnccMouseClicked
        // TODO add your handling code here:
        int viTri = this.tblncc.getSelectedRow();
        if (viTri == -1) {
            return;
        }
        this.txtmancc.setText(model.getValueAt(viTri, 0).toString());
        this.txttenncc.setText(model.getValueAt(viTri, 1).toString());
        this.txtdiachi.setText(model.getValueAt(viTri, 2).toString());
        this.txtsodt.setText(model.getValueAt(viTri, 3).toString());
        this.txtsofax.setText(model.getValueAt(viTri, 4).toString());
        this.txtwebsite.setText(model.getValueAt(viTri, 5).toString());
        this.txtemail.setText(model.getValueAt(viTri, 6).toString());

    }//GEN-LAST:event_tblnccMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnadd;
    private javax.swing.JButton btnclear;
    private javax.swing.JButton btnupdate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblDL;
    private javax.swing.JLabel lblHSX;
    private javax.swing.JLabel lblMS;
    private javax.swing.JLabel lblMaMH;
    private javax.swing.JLabel lblQG;
    private javax.swing.JLabel lblSL;
    private javax.swing.JLabel lblTenMH;
    private javax.swing.JTable tblncc;
    private javax.swing.JTextField txtdiachi;
    private javax.swing.JTextField txtemail;
    private javax.swing.JTextField txtmancc;
    private javax.swing.JTextField txtsodt;
    private javax.swing.JTextField txtsofax;
    private javax.swing.JTextField txttenncc;
    private javax.swing.JTextField txtwebsite;
    // End of variables declaration//GEN-END:variables

    public void filltable() {
        model.setRowCount(0);
        try {
            List<NhaCungCap> list = ncdao.selectAll();
            for (NhaCungCap nc : list) {
                Object[] row = {
                    nc.getMaNcc(),
                    nc.getTenNcc(),
                    nc.getDiaChi(),
                    nc.getSodt(),
                    nc.getFax(),
                    nc.getWebsite(),
                    nc.getEmail()
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Msgbox.alert(this, "Lỗi truy vấn dữ liệu");
        }
    }

    private void clear() {
        this.txtmancc.setText("");
        this.txttenncc.setText("");
        this.txtdiachi.setText("");
        this.txtsodt.setText("");
        this.txtsofax.setText("");
        this.txtwebsite.setText("");
        this.txtemail.setText("");
        this.index = -1;
    }

    private NhaCungCap getform() {
        NhaCungCap nc = new NhaCungCap();
        nc.setMaNcc(this.txtmancc.getText());
        nc.setTenNcc(this.txttenncc.getText());
        nc.setDiaChi(this.txtdiachi.getText());
        nc.setSodt(this.txtsodt.getText());
        nc.setFax(this.txtsofax.getText());
        nc.setWebsite(this.txtwebsite.getText());
        nc.setEmail(this.txtemail.getText());
        return nc;
    }

    private void insert() {
        NhaCungCap nc = getform();
        try {
            if (!ncdao.checkma(this.txtmancc.getText().trim())) {
                Msgbox.alert(this, "Mã đã tồn tại");
                return;
            }
            ncdao.insert(nc);
            this.filltable();
            Msgbox.alert(this, "Thêm thành công");
        } catch (Exception e) {
            e.printStackTrace();
            Msgbox.alert(this, "Thêm thất bại");
        }
    }

    private void update() {
        NhaCungCap nc = getform();
        try {
            ncdao.update(nc);
            this.filltable();
            Msgbox.alert(this, "Cập nhật thành công");
        } catch (Exception e) {
            e.printStackTrace();
            Msgbox.alert(this, "Cập nhật thất bại");
        }
    }

    private boolean checkform() {
        String reSDT = "(03|05|07|08|09)\\d{8,9}";
        String reEmail = "\\w+@\\w+(\\.\\w+){1,2}";
        if (this.txtmancc.getText().trim().length() == 0) {
            Msgbox.alert(this, "Vui lòng không bỏ trống mã nhà cung cấp");
            this.txtmancc.requestFocus();
            return false;
        }
        if (this.txttenncc.getText().trim().length() == 0) {
            Msgbox.alert(this, "Vui lòng không bỏ trống tên nhà cung cấp");
            this.txttenncc.requestFocus();
            return false;
        }
        if (this.txtdiachi.getText().trim().length() == 0) {
            Msgbox.alert(this, "Vui lòng không bỏ trống Địa chỉ");
            this.txtdiachi.requestFocus();
            return false;
        }
        if (this.txtsodt.getText().trim().length() == 0) {
            Msgbox.alert(this, "Vui lòng không bỏ trống số điện thoại");
            this.txtsodt.requestFocus();
            return false;
        }
        if (!this.txtsodt.getText().matches(reSDT)) {
            Msgbox.alert(this, "Vui lòng nhập đúng định dạng SDT");
            this.txtsodt.requestFocus();
            return false;
        }
        if (this.txtsofax.getText().trim().length() == 0) {
            Msgbox.alert(this, "Vui lòng không bỏ trống số fax");
            this.txtsofax.requestFocus();
            return false;
        }
        if (this.txtwebsite.getText().trim().length() == 0) {
            Msgbox.alert(this, "Vui lòng không bỏ trống website ");
            this.txtwebsite.requestFocus();
            return false;
        }
        if (this.txtemail.getText().trim().length() == 0) {
            Msgbox.alert(this, "Vui lòng không bỏ trống Email");
            this.txtemail.requestFocus();
            return false;
        }

        if (!this.txtemail.getText().matches(reEmail)) {
            Msgbox.alert(this, "Vui lòng nhập đúng định dạng email");
            this.txtemail.requestFocus();
            return false;
        }

        return true;
    }

}
