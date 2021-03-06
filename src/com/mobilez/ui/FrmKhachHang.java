/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mobilez.ui;

import com.mobilez.dao.KhachHangDAO;
import com.mobilez.models.KhachHang;
import com.mobilez.utils.Msgbox;
import com.mobilez.utils.TextAffect;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author uhtku
 */
public class FrmKhachHang extends javax.swing.JPanel {

    /**
     * Creates new form FrmKhachHang
     */
    DefaultTableModel model = new DefaultTableModel();
    KhachHangDAO dao = new KhachHangDAO();
    int index = -1;

    public FrmKhachHang() {
        initComponents();
        model = (DefaultTableModel) tblKhachHang.getModel();
        filltoTable();
        updateStatus();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblKhachHang = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        lblDSKH1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtTimKiem = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        lblMaKH4 = new javax.swing.JLabel();
        lblMaKH1 = new javax.swing.JLabel();
        lblMaKH2 = new javax.swing.JLabel();
        lblMaKH3 = new javax.swing.JLabel();
        txtMaKH = new javax.swing.JTextField();
        txtSDT = new javax.swing.JTextField();
        txtTenKH = new javax.swing.JTextField();
        txtDiaChi = new javax.swing.JTextField();
        btnFirst = new javax.swing.JButton();
        btnPrew = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        btnLast = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        lblDSKH = new javax.swing.JLabel();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnMoi = new javax.swing.JButton();

        setBackground(new java.awt.Color(34, 116, 173));

        tblKhachHang.setBackground(new java.awt.Color(34, 116, 173));
        tblKhachHang.setFont(new java.awt.Font("Baloo 2", 0, 13)); // NOI18N
        tblKhachHang.setForeground(new java.awt.Color(255, 255, 255));
        tblKhachHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "M?? kh??ch h??ng", "T??n kh??ch h??ng", "S??? ??i???n tho???i", "?????a ch???"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblKhachHang.setGridColor(new java.awt.Color(255, 255, 255));
        tblKhachHang.setRowHeight(25);
        tblKhachHang.setSelectionBackground(new java.awt.Color(51, 51, 51));
        tblKhachHang.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblKhachHang.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblKhachHang.setShowGrid(true);
        tblKhachHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKhachHangMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblKhachHang);
        if (tblKhachHang.getColumnModel().getColumnCount() > 0) {
            tblKhachHang.getColumnModel().getColumn(0).setPreferredWidth(50);
            tblKhachHang.getColumnModel().getColumn(1).setPreferredWidth(100);
            tblKhachHang.getColumnModel().getColumn(2).setResizable(false);
            tblKhachHang.getColumnModel().getColumn(2).setPreferredWidth(75);
            tblKhachHang.getColumnModel().getColumn(3).setResizable(false);
            tblKhachHang.getColumnModel().getColumn(3).setPreferredWidth(200);
        }

        jPanel1.setBackground(new java.awt.Color(34, 116, 173));

        lblDSKH1.setFont(new java.awt.Font("Baloo 2 ExtraBold", 1, 36)); // NOI18N
        lblDSKH1.setForeground(new java.awt.Color(255, 255, 255));
        lblDSKH1.setText("Danh s??ch kh??ch h??ng");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(178, 178, 178)
                .addComponent(lblDSKH1, javax.swing.GroupLayout.PREFERRED_SIZE, 472, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(115, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblDSKH1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("Baloo 2 ExtraBold", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("T??m ki???m:");

        txtTimKiem.setFont(new java.awt.Font("Baloo 2", 1, 12)); // NOI18N
        txtTimKiem.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTimKiemCaretUpdate(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(34, 116, 173));

        lblMaKH4.setFont(new java.awt.Font("Baloo 2 ExtraBold", 1, 18)); // NOI18N
        lblMaKH4.setForeground(new java.awt.Color(255, 255, 255));
        lblMaKH4.setText("M?? kh??ch h??ng:");

        lblMaKH1.setFont(new java.awt.Font("Baloo 2 ExtraBold", 1, 18)); // NOI18N
        lblMaKH1.setForeground(new java.awt.Color(255, 255, 255));
        lblMaKH1.setText("T??n kh??ch h??ng:");

        lblMaKH2.setFont(new java.awt.Font("Baloo 2 ExtraBold", 1, 18)); // NOI18N
        lblMaKH2.setForeground(new java.awt.Color(255, 255, 255));
        lblMaKH2.setText("S??? ??i???n tho???i:");

        lblMaKH3.setFont(new java.awt.Font("Baloo 2 ExtraBold", 1, 18)); // NOI18N
        lblMaKH3.setForeground(new java.awt.Color(255, 255, 255));
        lblMaKH3.setText("?????a ch???:");

        txtMaKH.setBackground(new java.awt.Color(34, 116, 173));
        txtMaKH.setFont(new java.awt.Font("Baloo 2", 1, 14)); // NOI18N
        txtMaKH.setForeground(new java.awt.Color(255, 255, 255));

        txtSDT.setBackground(new java.awt.Color(34, 116, 173));
        txtSDT.setFont(new java.awt.Font("Baloo 2", 1, 12)); // NOI18N
        txtSDT.setForeground(new java.awt.Color(255, 255, 255));
        txtSDT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSDTKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSDTKeyReleased(evt);
            }
        });

        txtTenKH.setBackground(new java.awt.Color(34, 116, 173));
        txtTenKH.setFont(new java.awt.Font("Baloo 2", 1, 12)); // NOI18N
        txtTenKH.setForeground(new java.awt.Color(255, 255, 255));

        txtDiaChi.setBackground(new java.awt.Color(34, 116, 173));
        txtDiaChi.setFont(new java.awt.Font("Baloo 2", 1, 12)); // NOI18N
        txtDiaChi.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lblMaKH4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtMaKH))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblMaKH1)
                            .addComponent(lblMaKH2)
                            .addComponent(lblMaKH3, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTenKH)
                            .addComponent(txtSDT)
                            .addComponent(txtDiaChi))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMaKH4, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMaKH1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMaKH2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMaKH3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(261, Short.MAX_VALUE))
        );

        btnFirst.setBackground(new java.awt.Color(34, 116, 173));
        btnFirst.setFont(new java.awt.Font("Baloo 2 ExtraBold", 1, 18)); // NOI18N
        btnFirst.setForeground(new java.awt.Color(255, 255, 255));
        btnFirst.setText("|<");
        btnFirst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirstActionPerformed(evt);
            }
        });

        btnPrew.setBackground(new java.awt.Color(34, 116, 173));
        btnPrew.setFont(new java.awt.Font("Baloo 2 ExtraBold", 1, 18)); // NOI18N
        btnPrew.setForeground(new java.awt.Color(255, 255, 255));
        btnPrew.setText("<<");
        btnPrew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrewActionPerformed(evt);
            }
        });

        btnNext.setBackground(new java.awt.Color(34, 116, 173));
        btnNext.setFont(new java.awt.Font("Baloo 2 ExtraBold", 1, 18)); // NOI18N
        btnNext.setForeground(new java.awt.Color(255, 255, 255));
        btnNext.setText(">>");
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        btnLast.setBackground(new java.awt.Color(34, 116, 173));
        btnLast.setFont(new java.awt.Font("Baloo 2 ExtraBold", 1, 18)); // NOI18N
        btnLast.setForeground(new java.awt.Color(255, 255, 255));
        btnLast.setText(">|");
        btnLast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastActionPerformed(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(34, 116, 173));

        lblDSKH.setFont(new java.awt.Font("Baloo 2 ExtraBold", 1, 36)); // NOI18N
        lblDSKH.setForeground(new java.awt.Color(255, 255, 255));
        lblDSKH.setText("C???p nh???t");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(140, Short.MAX_VALUE)
                .addComponent(lblDSKH, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(128, 128, 128))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblDSKH, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnThem.setBackground(new java.awt.Color(34, 116, 173));
        btnThem.setFont(new java.awt.Font("Baloo 2 ExtraBold", 1, 18)); // NOI18N
        btnThem.setForeground(new java.awt.Color(255, 255, 255));
        btnThem.setText("Th??m");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setBackground(new java.awt.Color(34, 116, 173));
        btnSua.setFont(new java.awt.Font("Baloo 2 ExtraBold", 1, 18)); // NOI18N
        btnSua.setForeground(new java.awt.Color(255, 255, 255));
        btnSua.setText("S???a");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnMoi.setBackground(new java.awt.Color(34, 116, 173));
        btnMoi.setFont(new java.awt.Font("Baloo 2 ExtraBold", 1, 18)); // NOI18N
        btnMoi.setForeground(new java.awt.Color(255, 255, 255));
        btnMoi.setText("M???i");
        btnMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(btnFirst)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(btnPrew)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(btnNext)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(btnLast))
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 770, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnMoi, btnSua, btnThem});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPrew, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFirst, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLast, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 465, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnMoi, btnSua, btnThem});

        getAccessibleContext().setAccessibleParent(this);
    }// </editor-fold>//GEN-END:initComponents

    private void txtTimKiemCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTimKiemCaretUpdate
        select();
    }//GEN-LAST:event_txtTimKiemCaretUpdate

    private void tblKhachHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKhachHangMouseClicked
        this.index = tblKhachHang.getSelectedRow();
        this.edit();
    }//GEN-LAST:event_tblKhachHangMouseClicked

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        if (checkform()) {
            this.insert();
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        if (checkform()) {
            this.update();
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoiActionPerformed
        this.clear();
    }//GEN-LAST:event_btnMoiActionPerformed

    private void btnFirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstActionPerformed
        this.first();
    }//GEN-LAST:event_btnFirstActionPerformed

    private void btnPrewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrewActionPerformed
        this.prev();
    }//GEN-LAST:event_btnPrewActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        this.next();
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastActionPerformed
        this.last();
    }//GEN-LAST:event_btnLastActionPerformed

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
    private javax.swing.JButton btnFirst;
    private javax.swing.JButton btnLast;
    private javax.swing.JButton btnMoi;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrew;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblDSKH;
    private javax.swing.JLabel lblDSKH1;
    private javax.swing.JLabel lblMaKH1;
    private javax.swing.JLabel lblMaKH2;
    private javax.swing.JLabel lblMaKH3;
    private javax.swing.JLabel lblMaKH4;
    private javax.swing.JTable tblKhachHang;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtMaKH;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTenKH;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables

    public void filltoTable() {
        model.setRowCount(0);
        try {
            List<KhachHang> lstKH = dao.selectAll();
            for (KhachHang kh : lstKH) {
                Object[] row = {kh.getMaKH(), kh.getTenKH(), kh.getSoDienThoai(), kh.getDiaChi()};
                model.addRow(row);
            }

        } catch (Exception e) {
            Msgbox.alert(this, "L???i truy v???n d??? li???u!");
        }
        updateStatus();
    }

    private void select() {
        model.setRowCount(0);
        try {
            List<KhachHang> lstKH = dao.selectByKeyWork(txtTimKiem.getText());
            for (KhachHang nv : lstKH) {
                Object[] row = {nv.getMaKH(), nv.getTenKH(), nv.getSoDienThoai(), nv.getDiaChi()};
                model.addRow(row);
            }
        } catch (Exception e) {
            Msgbox.alert(this, "L???i truy v???n d??? li???u!");
            e.printStackTrace();
        }
        clear();

    }

    private void updateStatus() {
        boolean edit = (this.index >= 0);
        txtMaKH.setEditable(!edit);
        btnSua.setEnabled(edit);
        btnThem.setEnabled(!edit);
    }

    private void delete() {
        String makh = (String) tblKhachHang.getValueAt(this.index, 0);
        if (Msgbox.confirm(this, "B???n th???c s??? mu???n x??a kh??ch h??ng n??y?")) {
            try {
                dao.delete(makh);
                this.filltoTable();
                this.clear();
                Msgbox.alert(this, "X??a th??nh c??ng!");
            } catch (Exception e) {
                Msgbox.alert(this, "X??a  th???t b???i!");
                e.printStackTrace();
            }
        }
    }

    private void clear() {
        txtMaKH.setText("");
        txtTenKH.setText("");
        txtDiaChi.setText("");
        txtSDT.setText("");
        this.index = -1;
        this.updateStatus();
    }

    private void insert() {
        KhachHang kh = getForm();
        try {
            if (!dao.checkDuplicate(txtMaKH.getText().trim())) {
                Msgbox.alert(this, "M?? ???? t???n t???i!!");
                return;
            }
            dao.insert(kh);
            this.filltoTable();
            Msgbox.alert(this, "Th??m m???i th??nh c??ng!");
        } catch (Exception e) {
            Msgbox.alert(this, "Th??m m???i th???t b???i!");
            e.printStackTrace();
        }
    }

    private void update() {
        KhachHang kh = getForm();
        try {
            dao.update(kh);
            this.filltoTable();
            Msgbox.alert(this, "C???p nh???t th??nh c??ng!");
        } catch (Exception e) {
            Msgbox.alert(this, "C???p nh???t th???t b???i!");
        }
    }

    private KhachHang getForm() {
        KhachHang kh = new KhachHang();
        kh.setMaKH(txtMaKH.getText().trim());
        kh.setTenKH(txtTenKH.getText().trim());
        kh.setSoDienThoai(txtSDT.getText().trim());
        kh.setDiaChi(txtDiaChi.getText().trim());
        return kh;
    }

    private void edit() {
        String makh = (String) tblKhachHang.getValueAt(this.index, 0);
        KhachHang kh = dao.selectById(makh);
        this.setForm(kh);
        this.updateStatus();
    }

    private void setForm(KhachHang kh) {
        txtMaKH.setText(kh.getMaKH());
        txtTenKH.setText(kh.getTenKH());
        txtDiaChi.setText(kh.getDiaChi());
        txtSDT.setText(kh.getSoDienThoai());
        tblKhachHang.setRowSelectionInterval(index, index);
    }

    private void first() {
        this.index = 0;
        this.edit();
    }

    private void next() {
        if (this.index < tblKhachHang.getRowCount() - 1) {
            this.index++;
            this.edit();
        }
    }

    private void prev() {
        if (this.index > 0) {
            this.index--;
            this.edit();
        }
    }

    private void last() {
        this.index = tblKhachHang.getRowCount() - 1;
        this.edit();
    }

    private boolean checkform() {
        String reSDT = "(03|05|07|08|09)\\d{8,9}";
        if (txtMaKH.getText().trim().equals("")) {
            Msgbox.alert(this, "M?? kh??ch h??ng kh??ng ???????c ????? tr???ng");
            txtMaKH.requestFocus();
            return false;
        }
        if (txtMaKH.getText().trim().length()<4||txtMaKH.getText().trim().length()>10) {
            Msgbox.alert(this, "M?? kh??ch h??ng t??? 4 ?????n 10 k?? t???!");
            this.txtMaKH.requestFocus();
            return false;
        }
        if (txtTenKH.getText().trim().equals("")) {
            Msgbox.alert(this, "T??n kh??ch h??ng kh??ng ???????c ????? tr???ng");
            txtTenKH.requestFocus();
            return false;
        }
        if (txtSDT.getText().trim().equals("")) {
            Msgbox.alert(this, "S??? ??i???n tho???i kh??ng ???????c ????? tr???ng");
            txtSDT.requestFocus();
            return false;
        }
        String sdt = txtSDT.getText().replace(".", "");
        if (!sdt.matches(reSDT)) {
            Msgbox.alert(this, "S??? ??i???n tho???i kh??ng ????ng ?????nh d???ng");
            txtSDT.requestFocus();
            return false;
        }

        if (txtDiaChi.getText().trim().equals("")) {
            Msgbox.alert(this, "?????a ch??? kh??ng ???????c ????? tr???ng");
            txtDiaChi.requestFocus();
            return false;
        }
        return true;
    }
}
