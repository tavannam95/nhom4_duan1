/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mobilez.ui;

import com.mobilez.utils.Auth;
import com.mobilez.utils.JdbcHelper;
import com.mobilez.utils.Msgbox;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author uhtku
 */
public class FrmPhieuGiaoCa extends javax.swing.JPanel {

    /**
     * Creates new form FrmPhieuGiaoCa
     */
    DefaultTableModel model = new DefaultTableModel();
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public FrmPhieuGiaoCa() {
        initComponents();
        model = (DefaultTableModel) tblMatHang.getModel();
        init();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        tblMatHang = new javax.swing.JTable();
        lblKho = new javax.swing.JLabel();
        lblDSMH = new javax.swing.JLabel();
        btnGiaoCa = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtNgayGiaoCa = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtNhanVien = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtCa = new javax.swing.JTextField();
        txtQuayHang = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();

        setBackground(new java.awt.Color(34, 116, 173));
        setPreferredSize(new java.awt.Dimension(1300, 581));

        tblMatHang.setBackground(new java.awt.Color(34, 116, 173));
        tblMatHang.setFont(new java.awt.Font("Baloo 2", 0, 13)); // NOI18N
        tblMatHang.setForeground(new java.awt.Color(255, 255, 255));
        tblMatHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã mặt hàng", "Tên mặt hàng", "Số lượng "
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblMatHang.setGridColor(new java.awt.Color(255, 255, 255));
        tblMatHang.setRowHeight(25);
        tblMatHang.setSelectionBackground(new java.awt.Color(51, 51, 51));
        tblMatHang.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblMatHang.setShowGrid(true);
        tblMatHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblMatHangMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblMatHang);

        lblKho.setFont(new java.awt.Font("Baloo 2 ExtraBold", 1, 18)); // NOI18N
        lblKho.setForeground(new java.awt.Color(255, 255, 255));
        lblKho.setText("Ngày giao ca:");

        lblDSMH.setFont(new java.awt.Font("Baloo 2 ExtraBold", 0, 36)); // NOI18N
        lblDSMH.setForeground(new java.awt.Color(255, 255, 255));
        lblDSMH.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDSMH.setText("PHIẾU GIAO CA");

        btnGiaoCa.setBackground(new java.awt.Color(34, 116, 173));
        btnGiaoCa.setFont(new java.awt.Font("Baloo 2", 1, 24)); // NOI18N
        btnGiaoCa.setForeground(new java.awt.Color(255, 255, 255));
        btnGiaoCa.setText("GIAO CA");
        btnGiaoCa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGiaoCaActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Baloo 2 ExtraBold", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Ca:");

        txtNgayGiaoCa.setEditable(false);
        txtNgayGiaoCa.setBackground(new java.awt.Color(34, 116, 173));
        txtNgayGiaoCa.setFont(new java.awt.Font("Baloo 2 ExtraBold", 1, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Baloo 2 ExtraBold", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Nhân viên:");

        txtNhanVien.setEditable(false);
        txtNhanVien.setBackground(new java.awt.Color(34, 116, 173));
        txtNhanVien.setFont(new java.awt.Font("Baloo 2 ExtraBold", 1, 14)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Baloo 2 ExtraBold", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Quầy hàng:");

        txtCa.setEditable(false);
        txtCa.setBackground(new java.awt.Color(34, 116, 173));
        txtCa.setFont(new java.awt.Font("Baloo 2 ExtraBold", 1, 14)); // NOI18N
        txtCa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCaActionPerformed(evt);
            }
        });

        txtQuayHang.setEditable(false);
        txtQuayHang.setBackground(new java.awt.Color(34, 116, 173));
        txtQuayHang.setFont(new java.awt.Font("Baloo 2 ExtraBold", 1, 14)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(17, 17, 17))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(11, 11, 11))
                                    .addComponent(lblKho, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(10, 10, 10)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtNhanVien, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtQuayHang, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtNgayGiaoCa, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtCa, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(btnGiaoCa, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(276, 276, 276)
                .addComponent(lblDSMH, javax.swing.GroupLayout.PREFERRED_SIZE, 666, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(371, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(lblDSMH, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtQuayHang, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblKho, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNgayGiaoCa, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCa, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnGiaoCa, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 509, Short.MAX_VALUE))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel2, jLabel3, jLabel4, lblKho});

    }// </editor-fold>//GEN-END:initComponents

    private void tblMatHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMatHangMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_tblMatHangMouseClicked

    private void btnGiaoCaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGiaoCaActionPerformed
        if (Msgbox.confirm(this, "Bạn xác nhận giao ca?")) {
            giaoCa();
            Msgbox.alert(this, "Giao ca thành công!!");
            
        }

    }//GEN-LAST:event_btnGiaoCaActionPerformed

    private void txtCaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGiaoCa;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblDSMH;
    private javax.swing.JLabel lblKho;
    private javax.swing.JTable tblMatHang;
    private javax.swing.JTextField txtCa;
    private javax.swing.JTextField txtNgayGiaoCa;
    private javax.swing.JTextField txtNhanVien;
    private javax.swing.JTextField txtQuayHang;
    // End of variables declaration//GEN-END:variables

    private void init() {
        txtNhanVien.setText(Auth.user.getHoTen());
        txtCa.setText(Auth.caLam + "");
        txtNgayGiaoCa.setText(sdf.format(new Date()));
        selectQuayHang();
        filltoTable();
    }

    private void selectQuayHang() {
        try {
            String query = "Select tenQH from QUAYHANG where MaQH = ?";
            ResultSet rs = JdbcHelper.query(query, Auth.maQuay);
            if (rs.next()) {
                txtQuayHang.setText(rs.getString("TenQH"));
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void filltoTable() {
        try {
            int stt = 1;
            String query = "select CHITIETPHIEUGIAOCA.MAMH, TENMH, CHITIETPHIEUGIAOCA.SOLUONG\n"
                    + "from MATHANG join CHITIETPHIEUGIAOCA on MATHANG.MAMH = CHITIETPHIEUGIAOCA.MAMH\n"
                    + "where MAPGC = ?";
            ResultSet rs = JdbcHelper.query(query, Auth.maPGC);
            while (rs.next()) {                
                model.addRow(new Object[]{stt,rs.getString("MaMH"),rs.getString("TenMH"),rs.getString("SoLuong")});
                stt++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void giaoCa(){
        try {
            String query = "update PhieuGiaoCa set NgayGC = ? where MaPGC = ?";
            JdbcHelper.update(query, new Date(),Auth.maPGC);
            Auth.giaoCa();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
