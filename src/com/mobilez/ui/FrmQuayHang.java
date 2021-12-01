/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mobilez.ui;

import com.mobilez.utils.JdbcHelper;
import com.mobilez.utils.Msgbox;
import java.awt.Color;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author uhtku
 */
public class FrmQuayHang extends javax.swing.JPanel {

    /**
     * Creates new form FrmHangHoa
     */
    DefaultTableModel modelTbl;
    int index = -1;
    
    public FrmQuayHang() {
        initComponents();
        modelTbl = (DefaultTableModel) tblList.getModel();
        this.fillTable();
        this.setButtonAdd();
    }

    //My method--------------------------------------------------------------------------------------------------
    //Sreach
    private void searchQH(){
        try {
            String sql = "SELECT * FROM QUAYHANG where MAQH like ? or TENQH like ?";
            String search = "%"+txtSearch.getText()+"%";
            ResultSet rs = JdbcHelper.query(sql,search,search);
            modelTbl.setRowCount(0);
            while (rs.next()) {
                String trangThai = rs.getBoolean("TRANGTHAI") ? "Đang hoạt động" : "Ngừng hoạt động";
                modelTbl.addRow(new Object[]{
                    rs.getString(1),
                    rs.getString(2),
                    trangThai
                });
            }
        } catch (Exception e) {
           e.printStackTrace();
        }
    }

    //check
    private boolean chkSua() {
        if (txtTenQH.getText().trim().equals("")) {
            txtTenQH.setText("");
            txtTenQH.requestFocus();
            Msgbox.alert(null, "Tên quầy hàng không được để trống!");
            lblTenQH.setForeground(Color.red);
            return true;
        } else {
            lblMaQH.setForeground(Color.white);
        }
        return false;
    }
    
    private boolean chkThem() {
        try {
            String sql = "SELECT * FROM QUAYHANG where MAQH like ?";
            ResultSet rs = JdbcHelper.query(sql, txtMaQH.getText());
            while (rs.next()) {  
                Msgbox.alert(null, "Mã quầy hàng đã tồn tại!");
                lblMaQH.setForeground(Color.red);
                txtMaQH.requestFocus();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            lblMaQH.setForeground(Color.red);
        }
        if (txtMaQH.getText().trim().equals("")) {
            txtMaQH.setText("");
            txtMaQH.requestFocus();
            Msgbox.alert(null, "Mã quầy hàng không được để trống!");
            lblMaQH.setForeground(Color.red);
            return true;
        } else {
            lblMaQH.setForeground(Color.white);
        }
        if (txtMaQH.getText().trim().length()<4||txtMaQH.getText().trim().length()>10) {
            Msgbox.alert(this, "Mã khách hàng từ 4 đến 10 ký tự!");
            this.txtMaQH.requestFocus();
            txtMaQH.setForeground(Color.red);
            return true;
        }else{
            txtMaQH.setForeground(Color.white);
        }
        if (txtTenQH.getText().trim().equals("")) {
            txtTenQH.setText("");
            txtTenQH.requestFocus();
            Msgbox.alert(null, "Tên quầy hàng không được để trống!");
            lblTenQH.setForeground(Color.red);
            return true;
        } else {
            lblMaQH.setForeground(Color.white);
        }
        return false;
    }

    private void sua() {
        try {
            String sql = "update QUAYHANG set TENQH = ?, TRANGTHAI = ? where MAQH like ?";
            String maQH = txtMaQH.getText();
            String tenQH = txtTenQH.getText();
            boolean trangThai = rdo1.isSelected();
            int s = JdbcHelper.update(sql, tenQH, trangThai, maQH);
            if (s > 0) {
                Msgbox.alert(null, "Sửa thành công!");
                this.clearForm();
            } else {
                Msgbox.alert(null, "Sửa thất bại!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private List<String> getListMaMH(){
        try {
            String sql = "select MAMH from MATHANG";
            ResultSet rs = JdbcHelper.query(sql);
            List<String> lst = new ArrayList<>();
            while (rs.next()) {                
                lst.add(rs.getString(1));
            }
            return lst;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    private void themMHVaoQuay(){
        try {
            String sql = "insert into CHITIETQUAYHANG values (?,?,0)";
            List<String> lstMaMH = this.getListMaMH();
            for (int i = 0; i < lstMaMH.size(); i++) {
                int s = JdbcHelper.update(sql, txtMaQH.getText().trim(),lstMaMH.get(i));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void them() {
        try {
            String sql = "insert into QUAYHANG values (?,?,?)";
            String maQH = txtMaQH.getText();
            String tenQH = txtTenQH.getText();
            boolean trangThai = rdo1.isSelected();
            int s = JdbcHelper.update(sql, maQH, tenQH, trangThai);
            if (s > 0) {
                Msgbox.alert(null, "Thêm thành công!");
                for (int i = 0; i < tblList.getRowCount(); i++) {
                    if (tblList.getValueAt(i, 0).toString().equalsIgnoreCase(txtMaQH.getText())) {
                        index=i;
                        this.showDetail();
                    }
                }
            } else {
                Msgbox.alert(null, "Thêm thất bại!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void showDetail() {
        txtMaQH.setEditable(false);
        txtMaQH.setText(tblList.getValueAt(index, 0).toString());
        txtTenQH.setText(tblList.getValueAt(index, 1).toString());
        if (tblList.getValueAt(index, 2).toString().equalsIgnoreCase("Đang hoạt động")) {
            rdo1.setSelected(true);
        } else {
            rdo0.setSelected(true);
        }
        tblList.setRowSelectionAllowed(true);
        tblList.setRowSelectionInterval(index, index);
        this.setButtonUpdate();
    }
    
    private void fillTable() {
        try {
            String sql = "SELECT * FROM QUAYHANG";
            ResultSet rs = JdbcHelper.query(sql);
            modelTbl.setRowCount(0);
            while (rs.next()) {
                String trangThai = rs.getBoolean("TRANGTHAI") ? "Đang hoạt động" : "Ngừng hoạt động";
                modelTbl.addRow(new Object[]{
                    rs.getString(1),
                    rs.getString(2),
                    trangThai
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void clearForm() {
        lblMaQH.setForeground(Color.white);
        lblTenQH.setForeground(Color.white);
        txtMaQH.setEditable(true);
        txtMaQH.setText("");
        txtTenQH.setText("");
        rdo1.setSelected(true);
        this.setButtonAdd();
        index = -1;
        tblList.setRowSelectionAllowed(false);
    }
    
    private void setButtonAdd() {
        btnThem.setEnabled(true);
        btnSua.setEnabled(false);
    }

    private void setButtonUpdate() {
        btnThem.setEnabled(false);
        btnSua.setEnabled(true);
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
        lblMaQH = new javax.swing.JLabel();
        txtMaQH = new javax.swing.JTextField();
        lblTenQH = new javax.swing.JLabel();
        txtTenQH = new javax.swing.JTextField();
        lblTrangThai = new javax.swing.JLabel();
        rdo1 = new javax.swing.JRadioButton();
        rdo0 = new javax.swing.JRadioButton();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnTaoMoi = new javax.swing.JButton();

        setBackground(new java.awt.Color(34, 116, 173));

        jLabel1.setFont(new java.awt.Font("Baloo 2 ExtraBold", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("DANH SÁCH QUẦY HÀNG");

        tblList.setBackground(new java.awt.Color(34, 116, 173));
        tblList.setFont(new java.awt.Font("Baloo 2", 0, 13)); // NOI18N
        tblList.setForeground(new java.awt.Color(255, 255, 255));
        tblList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã quầy hàng", "Tên quầy hàng", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
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

        lblMaQH.setFont(new java.awt.Font("Baloo Chettan 2", 1, 14)); // NOI18N
        lblMaQH.setForeground(new java.awt.Color(255, 255, 255));
        lblMaQH.setText("Mã quầy hàng");

        txtMaQH.setBackground(new java.awt.Color(34, 116, 173));
        txtMaQH.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtMaQH.setForeground(new java.awt.Color(255, 255, 255));
        txtMaQH.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)));
        txtMaQH.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtMaQHtxtDungLuongKeyPressed(evt);
            }
        });

        lblTenQH.setFont(new java.awt.Font("Baloo Chettan 2", 1, 14)); // NOI18N
        lblTenQH.setForeground(new java.awt.Color(255, 255, 255));
        lblTenQH.setText("Tên quầy hàng");

        txtTenQH.setBackground(new java.awt.Color(34, 116, 173));
        txtTenQH.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtTenQH.setForeground(new java.awt.Color(255, 255, 255));
        txtTenQH.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)));
        txtTenQH.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTenQHtxtDungLuongKeyPressed(evt);
            }
        });

        lblTrangThai.setFont(new java.awt.Font("Baloo Chettan 2", 1, 14)); // NOI18N
        lblTrangThai.setForeground(new java.awt.Color(255, 255, 255));
        lblTrangThai.setText("Trạng thái");

        grpStatus.add(rdo1);
        rdo1.setSelected(true);
        rdo1.setText("Đang hoạt động");

        grpStatus.add(rdo0);
        rdo0.setText("Ngừng hoạt động");

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
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 649, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 509, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblMaQH, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtMaQH))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblTenQH, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtTenQH))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(rdo1)
                                        .addGap(18, 18, 18)
                                        .addComponent(rdo0))
                                    .addGroup(layout.createSequentialGroup()
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
                            .addComponent(txtMaQH, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblMaQH))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTenQH, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTenQH))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblTrangThai)
                            .addComponent(rdo1)
                            .addComponent(rdo0))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnThem)
                            .addComponent(btnSua)
                            .addComponent(btnTaoMoi))
                        .addGap(0, 0, Short.MAX_VALUE))))
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

    private void txtMaQHtxtDungLuongKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMaQHtxtDungLuongKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaQHtxtDungLuongKeyPressed

    private void txtTenQHtxtDungLuongKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTenQHtxtDungLuongKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenQHtxtDungLuongKeyPressed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        if (this.chkThem()) {
            return;
        }
        this.them();
        this.themMHVaoQuay();
        this.fillTable();
        this.clearForm();
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        if (chkSua()) {
            return;
        }
        this.sua();
        this.fillTable();
        this.clearForm();
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnTaoMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoMoiActionPerformed
        // TODO add your handling code here:
        if (Msgbox.confirm(null, "Bạn có muốn tạo mới không?")) {
            clearForm();            
        }
        
    }//GEN-LAST:event_btnTaoMoiActionPerformed


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
    private javax.swing.JLabel lblMaQH;
    private javax.swing.JLabel lblTenQH;
    private javax.swing.JLabel lblTrangThai;
    private javax.swing.JRadioButton rdo0;
    private javax.swing.JRadioButton rdo1;
    private javax.swing.JTable tblList;
    private javax.swing.JTextField txtMaQH;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtTenQH;
    // End of variables declaration//GEN-END:variables
}
