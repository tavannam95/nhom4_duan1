/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mobilez.ui;

import com.mobilez.dao.NhaCungCapDao;
import com.mobilez.models.NhaCungCap;
import com.mobilez.utils.Msgbox;
import java.awt.event.KeyEvent;

/**
 *
 * @author LAPTOP
 */
public class JDialogNhaCC extends javax.swing.JDialog {

    /**
     * Creates new form JDialogNhaCC
     */
    NhaCungCapDao dao = new NhaCungCapDao();

    public JDialogNhaCC(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
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
        txtMaNCC = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtTenNCC = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        btnThem = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        txtDiaChi = new javax.swing.JTextField();
        txtSoDT = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtFax = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtWebsite = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(34, 116, 173));

        txtMaNCC.setBackground(new java.awt.Color(34, 116, 173));
        txtMaNCC.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)));

        jLabel7.setFont(new java.awt.Font("Baloo 2 ExtraBold", 0, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("M?? nh?? cung c???p");

        txtTenNCC.setBackground(new java.awt.Color(34, 116, 173));
        txtTenNCC.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)));
        txtTenNCC.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTenNCCKeyPressed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Baloo 2 ExtraBold", 0, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("T??n nh?? cung c???p");

        btnThem.setBackground(new java.awt.Color(34, 116, 173));
        btnThem.setFont(new java.awt.Font("Baloo 2", 0, 12)); // NOI18N
        btnThem.setForeground(new java.awt.Color(255, 255, 255));
        btnThem.setText("Th??m");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Baloo 2 ExtraBold", 0, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("?????a ch???");

        txtDiaChi.setBackground(new java.awt.Color(34, 116, 173));
        txtDiaChi.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)));
        txtDiaChi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDiaChiKeyPressed(evt);
            }
        });

        txtSoDT.setBackground(new java.awt.Color(34, 116, 173));
        txtSoDT.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)));
        txtSoDT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSoDTKeyPressed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Baloo 2 ExtraBold", 0, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("S??? ??i???n tho???i");

        txtFax.setBackground(new java.awt.Color(34, 116, 173));
        txtFax.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)));
        txtFax.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtFaxKeyPressed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Baloo 2 ExtraBold", 0, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Fax");

        txtEmail.setBackground(new java.awt.Color(34, 116, 173));
        txtEmail.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)));
        txtEmail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtEmailKeyPressed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Baloo 2 ExtraBold", 0, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Email");

        txtWebsite.setBackground(new java.awt.Color(34, 116, 173));
        txtWebsite.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)));
        txtWebsite.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtWebsiteKeyPressed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Baloo 2 ExtraBold", 0, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Website");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(txtTenNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(txtMaNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnThem))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(txtSoDT, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(txtFax, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(txtWebsite, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMaNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTenNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSoDT, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFax, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtWebsite, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addGap(18, 18, 18)
                .addComponent(btnThem)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtTenNCCKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTenNCCKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenNCCKeyPressed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed

        if (checkForm()) {
            insert();
        }

    }//GEN-LAST:event_btnThemActionPerformed

    private void txtDiaChiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDiaChiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (checkForm()) {
                insert();
            }
        }
    }//GEN-LAST:event_txtDiaChiKeyPressed

    private void txtSoDTKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSoDTKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSoDTKeyPressed

    private void txtFaxKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFaxKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFaxKeyPressed

    private void txtEmailKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEmailKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmailKeyPressed

    private void txtWebsiteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtWebsiteKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtWebsiteKeyPressed

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
            java.util.logging.Logger.getLogger(JDialogNhaCC.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JDialogNhaCC.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JDialogNhaCC.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JDialogNhaCC.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JDialogNhaCC dialog = new JDialogNhaCC(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnThem;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtFax;
    private javax.swing.JTextField txtMaNCC;
    private javax.swing.JTextField txtSoDT;
    private javax.swing.JTextField txtTenNCC;
    private javax.swing.JTextField txtWebsite;
    // End of variables declaration//GEN-END:variables

    private boolean checkForm() {
        String reSDT = "(03|05|07|08|09)\\d{8,9}";
        if (txtMaNCC.getText().trim().equals("")) {
            Msgbox.alert(this, "Kh??ng ???????c ????? tr???ng m??!!");
            txtMaNCC.requestFocus();
            return false;
        }
        if (txtTenNCC.getText().trim().equals("")) {
            Msgbox.alert(this, "Kh??ng ???????c ????? tr???ng t??n!!");
            txtTenNCC.requestFocus();
            return false;
        }
        if (txtDiaChi.getText().trim().equals("")) {
            Msgbox.alert(this, "Kh??ng ???????c ????? tr???ng ?????a ch???!!");
            txtDiaChi.requestFocus();
            return false;
        }
        if (txtSoDT.getText().trim().equals("")) {
            Msgbox.alert(this, "Kh??ng ???????c ????? tr???ng s??? ??i???n tho???i!!");
            txtSoDT.requestFocus();
            return false;
        }
        if (txtFax.getText().trim().equals("")) {
            Msgbox.alert(this, "Kh??ng ???????c ????? tr???ng Fax!!");
            txtFax.requestFocus();
            return false;
        }
        if (txtEmail.getText().trim().equals("")) {
            Msgbox.alert(this, "Kh??ng ???????c ????? tr???ng email!!");
            txtEmail.requestFocus();
            return false;
        }
        if (txtWebsite.getText().trim().equals("")) {
            Msgbox.alert(this, "Kh??ng ???????c ????? tr???ng website!!");
            txtWebsite.requestFocus();
            return false;
        }
        if (!dao.checkDuplicate(txtMaNCC.getText().trim())) {
            Msgbox.alert(this, "M?? nh?? cung c???p ???? t???n t???i!!");
            txtMaNCC.requestFocus();
            return false;
        }
        if (!txtSoDT.getText().matches(reSDT)) {
            Msgbox.alert(this, "S??? ??i???n tho???i kh??ng ????ng ?????nh d???ng!!");
            txtSoDT.requestFocus();
            return false;
        }
        return true;
    }

    private void insert() {
        try {
            NhaCungCap ncc = new NhaCungCap();
            ncc.setMaNcc(txtMaNCC.getText().trim());
            ncc.setTenNcc(txtTenNCC.getText().trim());
            ncc.setDiaChi(txtDiaChi.getText().trim());
            ncc.setSodt(txtSoDT.getText().trim());
            ncc.setFax(txtFax.getText().trim());
            ncc.setEmail(txtEmail.getText().trim());
            ncc.setWebsite(txtWebsite.getText().trim());
            dao.insert(ncc);
            Msgbox.alert(this, "Th??m th??nh c??ng!!");
        } catch (Exception e) {
            Msgbox.alert(this, "Th??m th???t b???i!!");
        }
    }
}
