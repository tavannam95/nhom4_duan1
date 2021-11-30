/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mobilez.ui;

import com.mobilez.dao.KhachHangDAO;
import com.mobilez.models.KhachHang;
import com.mobilez.utils.Msgbox;
import java.awt.Color;

/**
 *
 * @author uhtku
 */
public class FrmNewKH extends javax.swing.JDialog {

    /**
     * Creates new form FrmNewKH
     */
    public FrmNewKH(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
    }

    private void insertKH(){
        try {
            KhachHang kh = new KhachHang(txtMaKH.getText(), txtTenKH.getText(), txtDiaChi.getText(), txtSoDT.getText());
            KhachHangDAO dao = new KhachHangDAO();
            dao.insert(kh);
            Msgbox.alert(null, "Thêm thành công!");
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
        lblMaKH = new javax.swing.JLabel();
        txtMaKH = new javax.swing.JTextField();
        txtTenKH = new javax.swing.JTextField();
        lblTenKH = new javax.swing.JLabel();
        txtDiaChi = new javax.swing.JTextField();
        lblDiaChi = new javax.swing.JLabel();
        txtSoDT = new javax.swing.JTextField();
        lblSoDT = new javax.swing.JLabel();
        btnDongY = new javax.swing.JButton();
        btnHuy = new javax.swing.JButton();
        lblNewKH = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(34, 116, 173));

        lblMaKH.setFont(new java.awt.Font("Baloo Chettan 2", 1, 14)); // NOI18N
        lblMaKH.setForeground(new java.awt.Color(255, 255, 255));
        lblMaKH.setText("Mã khách hàng");

        txtMaKH.setBackground(new java.awt.Color(34, 116, 173));
        txtMaKH.setForeground(new java.awt.Color(255, 255, 255));
        txtMaKH.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)));
        txtMaKH.setCaretColor(new java.awt.Color(255, 255, 255));
        txtMaKH.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtMaKHtxtDungLuongKeyPressed(evt);
            }
        });

        txtTenKH.setBackground(new java.awt.Color(34, 116, 173));
        txtTenKH.setForeground(new java.awt.Color(255, 255, 255));
        txtTenKH.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)));
        txtTenKH.setCaretColor(new java.awt.Color(255, 255, 255));
        txtTenKH.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTenKHtxtDungLuongKeyPressed(evt);
            }
        });

        lblTenKH.setFont(new java.awt.Font("Baloo Chettan 2", 1, 14)); // NOI18N
        lblTenKH.setForeground(new java.awt.Color(255, 255, 255));
        lblTenKH.setText("Tên khách hàng");

        txtDiaChi.setBackground(new java.awt.Color(34, 116, 173));
        txtDiaChi.setForeground(new java.awt.Color(255, 255, 255));
        txtDiaChi.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)));
        txtDiaChi.setCaretColor(new java.awt.Color(255, 255, 255));
        txtDiaChi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDiaChitxtDungLuongKeyPressed(evt);
            }
        });

        lblDiaChi.setFont(new java.awt.Font("Baloo Chettan 2", 1, 14)); // NOI18N
        lblDiaChi.setForeground(new java.awt.Color(255, 255, 255));
        lblDiaChi.setText("Địa chỉ");

        txtSoDT.setBackground(new java.awt.Color(34, 116, 173));
        txtSoDT.setForeground(new java.awt.Color(255, 255, 255));
        txtSoDT.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)));
        txtSoDT.setCaretColor(new java.awt.Color(255, 255, 255));
        txtSoDT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSoDTtxtDungLuongKeyPressed(evt);
            }
        });

        lblSoDT.setFont(new java.awt.Font("Baloo Chettan 2", 1, 14)); // NOI18N
        lblSoDT.setForeground(new java.awt.Color(255, 255, 255));
        lblSoDT.setText("Số điện thoại");

        btnDongY.setBackground(new java.awt.Color(34, 116, 173));
        btnDongY.setFont(new java.awt.Font("Baloo 2", 1, 12)); // NOI18N
        btnDongY.setForeground(new java.awt.Color(255, 255, 255));
        btnDongY.setText("Đồng ý");
        btnDongY.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDongYActionPerformed(evt);
            }
        });

        btnHuy.setBackground(new java.awt.Color(34, 116, 173));
        btnHuy.setFont(new java.awt.Font("Baloo 2", 1, 12)); // NOI18N
        btnHuy.setForeground(new java.awt.Color(255, 255, 255));
        btnHuy.setText("Hủy");
        btnHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyActionPerformed(evt);
            }
        });

        lblNewKH.setFont(new java.awt.Font("Baloo 2 ExtraBold", 0, 20)); // NOI18N
        lblNewKH.setForeground(new java.awt.Color(255, 255, 255));
        lblNewKH.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNewKH.setText("Thêm mới khách hàng");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblNewKH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblSoDT)
                        .addGap(35, 35, 35)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnDongY)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnHuy))
                            .addComponent(txtSoDT, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblMaKH)
                            .addComponent(lblTenKH)
                            .addComponent(lblDiaChi))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtTenKH, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE)
                            .addComponent(txtDiaChi, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMaKH))))
                .addContainerGap(62, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(lblNewKH)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMaKH))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTenKH))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDiaChi))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSoDT, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSoDT))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDongY)
                    .addComponent(btnHuy))
                .addGap(16, 16, 16))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtMaKHtxtDungLuongKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMaKHtxtDungLuongKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaKHtxtDungLuongKeyPressed

    private void txtTenKHtxtDungLuongKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTenKHtxtDungLuongKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenKHtxtDungLuongKeyPressed

    private void txtDiaChitxtDungLuongKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDiaChitxtDungLuongKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDiaChitxtDungLuongKeyPressed

    private void txtSoDTtxtDungLuongKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSoDTtxtDungLuongKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSoDTtxtDungLuongKeyPressed

    private void btnDongYActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDongYActionPerformed
        //Validate ma kh
        if (txtMaKH.getText().trim().equals("")) {
            Msgbox.alert(null, "Vui lòng nhập mã khách hàng!");
            txtMaKH.setText("");
            txtMaKH.requestFocus();
            lblMaKH.setForeground(Color.red);
            return;
        }else{
            lblMaKH.setForeground(Color.white);
        }
        //Validate ten KH
        if (txtTenKH.getText().trim().equals("")) {
            Msgbox.alert(null, "Vui lòng nhập mã khách hàng!");
            txtTenKH.setText("");
            txtTenKH.requestFocus();
            lblTenKH.setForeground(Color.red);
            return;
        }else{
            lblTenKH.setForeground(Color.white);
        }
        //Validate dia chi
        if (txtDiaChi.getText().trim().equals("")) {
            Msgbox.alert(null, "Vui lòng nhập mã khách hàng!");
            txtDiaChi.setText("");
            txtDiaChi.requestFocus();
            lblDiaChi.setForeground(Color.red);
            return;
        }else{
            lblDiaChi.setForeground(Color.white);
        }
        //Validate so dt
        if (txtSoDT.getText().trim().equals("")) {
            Msgbox.alert(null, "Vui lòng nhập mã khách hàng!");
            txtSoDT.setText("");
            txtSoDT.requestFocus();
            lblSoDT.setForeground(Color.red);
            return;
        }else{
            lblSoDT.setForeground(Color.white);
        }
        String chkSoDT = "0[3,7,8,9]\\d{7}";
        if (!txtSoDT.getText().matches(chkSoDT)) {
            Msgbox.alert(null, "Số điện thoại sai định dạng!");
            txtSoDT.requestFocus();
            lblSoDT.setForeground(Color.red);
            return;
        }else{
            lblSoDT.setForeground(Color.white);
        }
        this.insertKH();
    }//GEN-LAST:event_btnDongYActionPerformed

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
    }//GEN-LAST:event_btnHuyActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmNewKH.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmNewKH.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmNewKH.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmNewKH.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FrmNewKH dialog = new FrmNewKH(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDongY;
    private javax.swing.JButton btnHuy;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblDiaChi;
    private javax.swing.JLabel lblMaKH;
    private javax.swing.JLabel lblNewKH;
    private javax.swing.JLabel lblSoDT;
    private javax.swing.JLabel lblTenKH;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtMaKH;
    private javax.swing.JTextField txtSoDT;
    private javax.swing.JTextField txtTenKH;
    // End of variables declaration//GEN-END:variables
}