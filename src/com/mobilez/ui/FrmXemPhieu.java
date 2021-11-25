/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mobilez.ui;
import com.mobilez.utils.JdbcHelper;
import com.mobilez.utils.XDate;
import java.sql.*;
import java.util.Vector;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author uhtku
 */
public class FrmXemPhieu extends javax.swing.JPanel {
        DefaultTableModel model  = new DefaultTableModel();
        int index;
    /**
     * Creates new form FrmXemPhieu
     */
    public FrmXemPhieu() {
        initComponents();
        fillTable();
        
    }
    public void ShowDetail(){
        index = tblList.getSelectedRow();
    }
    
    private void fillToTablePX(){
        model = (DefaultTableModel) tblList.getModel();
        model.setRowCount(0);
        try {
            model.setRowCount(0);
            String sql = "select MAPXK,nhanvien.HOTEN,NGAYXK,TONGGIA from PHIEUXUATKHO\n" +
                            "join NHANVIEN on NHANVIEN.MANV = PHIEUXUATKHO.MANV";
            ResultSet rs = JdbcHelper.query(sql);
            while (rs.next()) {                
                Object[] row = {
                    rs.getString("MAPXK"), rs.getString("HoTEN"),
                    rs.getDate("NGAYXK"), rs.getInt("TONGGIA")
                };
                model.addRow(row);
            }
            tblList.setModel(model);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void fillToTableNX(){
        model = (DefaultTableModel) tblList.getModel();
        model.setRowCount(0);
        try {
            model.setRowCount(0);
            String sql = "select MAPNK,nhanvien.HOTEN,NGAYNHAP,TONGGIA from PHIEUNHAPKHO\n" +
                            "join NHANVIEN on NHANVIEN.MANV = PHIEUNHAPKHO.MANV";
            ResultSet rs = JdbcHelper.query(sql);
            while (rs.next()) {                
                Object[] row = {
                    rs.getString("MAPNK"), rs.getString("Hoten"),
                    rs.getDate("NGAYNHAP"), rs.getInt("TONGGIA")
                };
                model.addRow(row);
            }
            tblList.setModel(model);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void fillToTablePGC(){
        model = (DefaultTableModel) tblList.getModel();
        model.setRowCount(0);
        try {
            model.setRowCount(0);
            String sql = "select MAPGC,NHANVIEN.HOTEN,NGAYGC,Ca from PHIEUGIAOCA\n" +
                            "join NHANVIEN on NHANVIEN.MANV = PHIEUGIAOCA.MANV";
            ResultSet rs = JdbcHelper.query(sql);
            while (rs.next()) {                
                Object[] row = {
                    rs.getString("MAPGC"), rs.getString("HOTEN"),
                    rs.getDate("NGAYGC"),rs.getInt("CA")
                };
                model.addRow(row);
            }
            tblList.setModel(model);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
     private void fillToTablePBH(){
        model = (DefaultTableModel) tblList.getModel();
        model.setRowCount(0);
        try {
            model.setRowCount(0);
            String sql = "select SOIMEI,MATHANG.TENMH,NGAYHETHAN,TRANGTHAI from PHIEUBAOHANH\n" +
                            "join MATHANG on MATHANg.MAMH = PHIEUBAOHANH.MAMH";
            ResultSet rs = JdbcHelper.query(sql);
            while (rs.next()) {                
                Object[] row = {
                    rs.getString("SOIMEI"), rs.getString("TENMH"),
                    rs.getDate("NGAYHETHAN"),rs.getString("TRANGTHAI")
                };
                model.addRow(row);
            }
            tblList.setModel(model);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
 
     
     void fillTable(){
         if (rdoPNK.isSelected()) {
             fillToTableNX();
         } else if (rdoPXK.isSelected()) {
             fillToTablePX();
         }else if(rdoPBH.isSelected())
{      fillToTablePBH();
         }else{
             fillToTablePGC();
         }
     }
     
     public void OpenPXK(){
         FrmPhieuXuat pxk = new FrmPhieuXuat();
         pxk.setVisible(true);
         
     }
    public void OpenPNK(){
         FrmPhieuNhap pnk = new FrmPhieuNhap();
         pnk.setVisible(true);
         
     }    
    public void OpenPGC(){
         FrmPhieuGiaoCa pgc = new FrmPhieuGiaoCa();
         pgc.setVisible(true);
         
     }     
    public void OpenPBH(){
         FrmPhieuBaoHanh pbh = new FrmPhieuBaoHanh();
         pbh.setVisible(true);
         
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
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblList = new javax.swing.JTable();
        jSeparator2 = new javax.swing.JSeparator();
        btnXem = new javax.swing.JButton();
        rdoPXK = new javax.swing.JRadioButton();
        rdoPNK = new javax.swing.JRadioButton();
        rdoPGC = new javax.swing.JRadioButton();
        rdoPBH = new javax.swing.JRadioButton();

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
                "Mã  Phiếu", "Nhân Viên/Mặt Hàng(Phiếu Bảo Hành)", "Thời Gian", "Tổng Tiền/Ca(PGC)/Trạng Thái(PBH)"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
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

        btnXem.setText("Xem Chi Tiết");
        btnXem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXemActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdoPXK);
        rdoPXK.setText("Phiếu Xuất Kho");
        rdoPXK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoPXKActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdoPNK);
        rdoPNK.setText("Phiếu Nhập Kho");
        rdoPNK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoPNKActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdoPGC);
        rdoPGC.setText("Phiếu Giao Ca");
        rdoPGC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoPGCActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdoPBH);
        rdoPBH.setText("Phiếu Bảo Hành");
        rdoPBH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoPBHActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator2)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 807, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 12, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(95, 95, 95)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rdoPXK)
                            .addComponent(rdoPGC))
                        .addGap(130, 130, 130)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rdoPBH)
                            .addComponent(rdoPNK))
                        .addGap(100, 100, 100)
                        .addComponent(btnXem)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rdoPXK)
                            .addComponent(rdoPNK))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rdoPGC)
                            .addComponent(rdoPBH)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(btnXem)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 492, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 611, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblListMouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_tblListMouseClicked

    private void rdoPXKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoPXKActionPerformed
        // TODO add your handling code here:
        this.fillTable();
    }//GEN-LAST:event_rdoPXKActionPerformed

    private void rdoPNKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoPNKActionPerformed
        // TODO add your handling code here:
        this.fillTable();
    }//GEN-LAST:event_rdoPNKActionPerformed

    private void rdoPGCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoPGCActionPerformed
        // TODO add your handling code here:
        this.fillTable();
    }//GEN-LAST:event_rdoPGCActionPerformed

    private void rdoPBHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoPBHActionPerformed
        // TODO add your handling code here:
        this.fillTable();
    }//GEN-LAST:event_rdoPBHActionPerformed

    private void btnXemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXemActionPerformed
        // TODO add your handling code here:
        if (rdoPNK.isSelected()) {
            FrmPhieuNhap pnk = new FrmPhieuNhap();
            pnk.setVisible(true);
            OpenPNK();
        } else if (rdoPXK.isSelected()) {
            OpenPXK();
        
        }else if(rdoPBH.isSelected())
{           OpenPBH();
        }else{
            OpenPGC();
        }

    }//GEN-LAST:event_btnXemActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnXem;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JRadioButton rdoPBH;
    private javax.swing.JRadioButton rdoPGC;
    private javax.swing.JRadioButton rdoPNK;
    private javax.swing.JRadioButton rdoPXK;
    private javax.swing.JTable tblList;
    // End of variables declaration//GEN-END:variables
}
