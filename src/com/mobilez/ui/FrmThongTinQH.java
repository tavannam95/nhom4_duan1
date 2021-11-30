/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mobilez.ui;

import com.mobilez.models.QuayHang;
import com.mobilez.utils.Auth;
import com.mobilez.utils.JdbcHelper;
import com.mobilez.utils.Msgbox;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author uhtku
 */
public class FrmThongTinQH extends javax.swing.JPanel {

    /**
     * Creates new form FrmPhieuGiaoCa
     */
    DefaultTableModel model = new DefaultTableModel();
    DefaultComboBoxModel<QuayHang> modelCbo;
    
    
    public FrmThongTinQH() {
        initComponents();
        model = (DefaultTableModel) tblMatHang.getModel();
        modelCbo = (DefaultComboBoxModel) cboQH.getModel();
        this.fillCboQH();
        for (int i = 0; i < modelCbo.getSize(); i++) {
            cboQH.setSelectedIndex(i);
            QuayHang qh = (QuayHang) cboQH.getSelectedItem();
            if (Auth.maQuay.equalsIgnoreCase(qh.getMaQH())) {
                break;
            }
        }
        if (!Auth.isManager()) {
            cboQH.setEditable(false);
        }
    }
    
    private void fillCboQH(){
        try {
            String sql = "select * from QUAYHANG";
            ResultSet rs = JdbcHelper.query(sql);
            while (rs.next()) {                
                QuayHang qh = new QuayHang(rs.getString(1), rs.getString(2), rs.getBoolean(3));
                modelCbo.addElement(qh);
            }
            rs.close();
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

        jScrollPane2 = new javax.swing.JScrollPane();
        tblMatHang = new javax.swing.JTable();
        lblDSMH = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        cboQH = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

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

        lblDSMH.setFont(new java.awt.Font("Baloo 2 ExtraBold", 0, 36)); // NOI18N
        lblDSMH.setForeground(new java.awt.Color(255, 255, 255));
        lblDSMH.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDSMH.setText("QUẦY HÀNG");

        jLabel1.setFont(new java.awt.Font("Baloo 2 ExtraBold", 0, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Quầy:");

        jButton1.setBackground(new java.awt.Color(34, 116, 173));
        jButton1.setFont(new java.awt.Font("Baloo 2 ExtraBold", 0, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Thêm quầy");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1288, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(cboQH, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(lblDSMH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(lblDSMH, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboQH, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 427, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {cboQH, jButton1});

    }// </editor-fold>//GEN-END:initComponents

    private void tblMatHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMatHangMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_tblMatHangMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cboQH;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblDSMH;
    private javax.swing.JTable tblMatHang;
    // End of variables declaration//GEN-END:variables

    
}