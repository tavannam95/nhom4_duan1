/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mobilez.ui;

import com.mobilez.dao.MatHangDAO;
import com.mobilez.dao.PhieuBaoHanhDao;
import com.mobilez.models.MatHang;
import com.mobilez.models.PhieuBaoHanh;
import com.mobilez.utils.JdbcHelper;
import com.mobilez.utils.Msgbox;
import com.mobilez.utils.XDate;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author uhtku
 */
public class FrmPhieuBaoHanh extends javax.swing.JPanel {

    DefaultTableModel modelBaoHanh = new DefaultTableModel();
    DefaultTableModel modelmatHang = new DefaultTableModel();
    PhieuBaoHanhDao dao = new PhieuBaoHanhDao();
    int indexMatHang = -1;
    int indexBaoHanh = -1;
    MatHangDAO mhDAO = new MatHangDAO();
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    /**
     * Creates new form FrmPhieuBaoHanh
     */
    public FrmPhieuBaoHanh() {
        initComponents();
        modelBaoHanh = (DefaultTableModel) tblBaoHanh.getModel();
        modelmatHang = (DefaultTableModel) tblMatHang.getModel();
        this.filltableMatHang();
        this.filltable();
        clearform();
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
        lblDSMH = new javax.swing.JLabel();
        txtNgayHetHan = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblMatHang = new javax.swing.JTable();
        txtSearchMH = new javax.swing.JTextField();
        txtsoIMEI = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnMoi = new javax.swing.JButton();
        rdoDangBaoHanh = new javax.swing.JRadioButton();
        rdoTrong = new javax.swing.JRadioButton();
        txtMaMH = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblBaoHanh = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        txtSearchPBH = new javax.swing.JTextField();
        lblDSMH1 = new javax.swing.JLabel();
        lblDSMH2 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(34, 116, 173));

        lblDSMH.setFont(new java.awt.Font("Baloo 2 ExtraBold", 0, 24)); // NOI18N
        lblDSMH.setForeground(new java.awt.Color(255, 255, 255));
        lblDSMH.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDSMH.setText("CẬP NHẬT");

        txtNgayHetHan.setEditable(false);
        txtNgayHetHan.setFont(new java.awt.Font("Baloo 2", 0, 13)); // NOI18N

        tblMatHang.setBackground(new java.awt.Color(34, 116, 173));
        tblMatHang.setFont(new java.awt.Font("Baloo 2", 0, 13)); // NOI18N
        tblMatHang.setForeground(new java.awt.Color(255, 255, 255));
        tblMatHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã mặt hàng", "Hãng sản xuất", "Tên mặt hàng", "RAM", "Dung lượng", "Màu sắc", "Thời gian BH"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
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

        txtSearchMH.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchMHKeyReleased(evt);
            }
        });

        txtsoIMEI.setFont(new java.awt.Font("Baloo 2", 0, 13)); // NOI18N

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mobilez/icon/search_32px.png"))); // NOI18N

        jLabel4.setFont(new java.awt.Font("Baloo 2 ExtraBold", 1, 14)); // NOI18N
        jLabel4.setText("Mã Mặt Hàng: ");

        jLabel5.setFont(new java.awt.Font("Baloo 2 ExtraBold", 1, 14)); // NOI18N
        jLabel5.setText("Ngày Hết Hạn: ");

        jLabel6.setFont(new java.awt.Font("Baloo 2 ExtraBold", 1, 14)); // NOI18N
        jLabel6.setText("Trạng Thái:");

        jLabel3.setFont(new java.awt.Font("Baloo 2 ExtraBold", 1, 14)); // NOI18N
        jLabel3.setText("Số IMEI: ");

        btnThem.setFont(new java.awt.Font("Baloo 2 ExtraBold", 1, 14)); // NOI18N
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setFont(new java.awt.Font("Baloo 2 ExtraBold", 1, 14)); // NOI18N
        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnMoi.setFont(new java.awt.Font("Baloo 2 ExtraBold", 1, 14)); // NOI18N
        btnMoi.setText("Mới");
        btnMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoiActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdoDangBaoHanh);
        rdoDangBaoHanh.setFont(new java.awt.Font("Baloo 2 ExtraBold", 1, 14)); // NOI18N
        rdoDangBaoHanh.setText("Đang bảo hành");

        buttonGroup1.add(rdoTrong);
        rdoTrong.setFont(new java.awt.Font("Baloo 2 ExtraBold", 1, 14)); // NOI18N
        rdoTrong.setSelected(true);
        rdoTrong.setText("Trống");

        txtMaMH.setEditable(false);
        txtMaMH.setFont(new java.awt.Font("Baloo 2", 0, 13)); // NOI18N

        tblBaoHanh.setBackground(new java.awt.Color(34, 116, 173));
        tblBaoHanh.setFont(new java.awt.Font("Baloo 2", 0, 13)); // NOI18N
        tblBaoHanh.setForeground(new java.awt.Color(255, 255, 255));
        tblBaoHanh.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Số IMEI", "Mã mặt hàng", "Tên mặt hàng", "Ngày hết hạn", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblBaoHanh.setGridColor(new java.awt.Color(255, 255, 255));
        tblBaoHanh.setRowHeight(25);
        tblBaoHanh.setSelectionBackground(new java.awt.Color(51, 51, 51));
        tblBaoHanh.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblBaoHanh.setShowGrid(true);
        tblBaoHanh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblBaoHanhMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblBaoHanh);

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mobilez/icon/search_32px.png"))); // NOI18N

        txtSearchPBH.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchPBHKeyReleased(evt);
            }
        });

        lblDSMH1.setFont(new java.awt.Font("Baloo 2 ExtraBold", 0, 24)); // NOI18N
        lblDSMH1.setForeground(new java.awt.Color(255, 255, 255));
        lblDSMH1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDSMH1.setText("PHIẾU BẢO HÀNH");

        lblDSMH2.setFont(new java.awt.Font("Baloo 2 ExtraBold", 0, 24)); // NOI18N
        lblDSMH2.setForeground(new java.awt.Color(255, 255, 255));
        lblDSMH2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDSMH2.setText("DANH SÁCH MẶT HÀNG");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSearchMH, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSearchPBH, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnThem)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSua)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnMoi))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 761, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(55, 55, 55)
                                .addComponent(txtsoIMEI))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                                .addComponent(rdoTrong)
                                .addGap(18, 18, 18)
                                .addComponent(rdoDangBaoHanh)
                                .addGap(185, 185, 185))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtNgayHetHan)
                                    .addComponent(txtMaMH))))))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblDSMH2, javax.swing.GroupLayout.PREFERRED_SIZE, 404, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(270, 270, 270)
                .addComponent(lblDSMH, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(102, 102, 102))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(536, Short.MAX_VALUE)
                    .addComponent(lblDSMH1, javax.swing.GroupLayout.PREFERRED_SIZE, 404, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(372, 372, 372)))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnMoi, btnSua, btnThem});

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {txtSearchMH, txtSearchPBH});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDSMH)
                    .addComponent(lblDSMH2))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtSearchMH, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtsoIMEI, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtMaMH, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtNgayHetHan, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(rdoTrong)
                            .addComponent(rdoDangBaoHanh)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(69, 69, 69)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel8)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtSearchPBH, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnThem)
                        .addComponent(btnSua)
                        .addComponent(btnMoi)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE)
                .addGap(34, 34, 34))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(328, Short.MAX_VALUE)
                    .addComponent(lblDSMH1)
                    .addGap(299, 299, 299)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoiActionPerformed
        // TODO add your handling code here:
        if (Msgbox.confirm(this, "Bạn muốn clear form?")) {
            this.clearform();
        }

    }//GEN-LAST:event_btnMoiActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        if (this.checkform()) {
            this.insert();
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        if (indexBaoHanh == -1) {
            Msgbox.alert(this, "Bạn chưa chọn phiếu bảo hành cần sửa!!");
            return;
        }
        this.update();
    }//GEN-LAST:event_btnSuaActionPerformed

    private void tblMatHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMatHangMouseClicked
        if (evt.getClickCount() == 2) {
            this.showDetaiMatHang();
        }
    }//GEN-LAST:event_tblMatHangMouseClicked

    private void tblBaoHanhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBaoHanhMouseClicked
        if (evt.getClickCount() == 2) {
            this.showDetaiBaoHanh();
        }
    }//GEN-LAST:event_tblBaoHanhMouseClicked

    private void txtSearchMHKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchMHKeyReleased
        this.selectMatHang(txtSearchMH.getText());
    }//GEN-LAST:event_txtSearchMHKeyReleased

    private void txtSearchPBHKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchPBHKeyReleased
        this.selectPBH(txtSearchPBH.getText());
    }//GEN-LAST:event_txtSearchPBHKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnMoi;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblDSMH;
    private javax.swing.JLabel lblDSMH1;
    private javax.swing.JLabel lblDSMH2;
    private javax.swing.JRadioButton rdoDangBaoHanh;
    private javax.swing.JRadioButton rdoTrong;
    private javax.swing.JTable tblBaoHanh;
    private javax.swing.JTable tblMatHang;
    private javax.swing.JTextField txtMaMH;
    private javax.swing.JTextField txtNgayHetHan;
    private javax.swing.JTextField txtSearchMH;
    private javax.swing.JTextField txtSearchPBH;
    private javax.swing.JTextField txtsoIMEI;
    // End of variables declaration//GEN-END:variables

    private void filltable() {
        try {
            List<PhieuBaoHanh> list = dao.selectALL();
            modelBaoHanh.setRowCount(0);
            for (PhieuBaoHanh ph : list) {
                MatHang mh = mhDAO.selectById(ph.getMaMH());
                Object[] row = {
                    ph.getSoIMEI(),
                    ph.getMaMH(),
                    mh.getTenMH(),
                    XDate.toString(ph.getNgayHetHan(), "dd/MM/yyyy"),
                    ph.isTrangThai() ? "Đang bảo hành" : "Trống"
                };
                modelBaoHanh.addRow(row);
            }
            updateStaTus();
        } catch (Exception e) {
            e.printStackTrace();
            Msgbox.alert(this, "Lỗi truy vẫn dữ liệu");
        }

    }
    
    private void selectPBH(String keywork){
        try {
            String query = "select SOIMEI, PHIEUBAOHANH.MAMH,TENMH,NGAYHETHAN,PHIEUBAOHANH.TRANGTHAI from PHIEUBAOHANH join MATHANG on PHIEUBAOHANH.MAMH = MATHANG.MAMH"
                    + " where SOIMEI like '%"+keywork+"%' or PHIEUBAOHANH.MAMH like '%"+keywork+"%' or PHIEUBAOHANH.trangthai like '%"+keywork+"%'"
                    + "or TENMH like N'%"+keywork+"%'";
            ResultSet rs = JdbcHelper.query(query);
            modelBaoHanh.setRowCount(0);
            while (rs.next()) {                
                modelBaoHanh.addRow(new Object[]{rs.getString(1),rs.getString(2),
                                                 rs.getString(3),XDate.toString(rs.getDate(4))
                                                 ,rs.getBoolean(5)?"Đang bảo hành":"Trống"});
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void filltableMatHang() {
        try {
            String sql = "select MAMH,TENHSX,TENMH,RAM,DUNGLUONG,MAUSAC,TGBH\n"
                    + "                    from MATHANG join HANGSANXUAT on MATHANG.MAHSX=HANGSANXUAT.MAHSX\n"
                    + "                    join QUOCGIA on MATHANG.MAQG= QUOCGIA.MAQG where TRANGTHAI = 1";
            ResultSet rs = JdbcHelper.query(sql);
            modelmatHang.setRowCount(0);
            while (rs.next()) {
                modelmatHang.addRow(new Object[]{
                    rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4) + "GB",
                    rs.getString(5) + "GB", rs.getString(6),
                    rs.getString(7) + " tháng"});
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void selectMatHang(String keywork){
        try {
            String sql = "select MAMH,TENHSX,TENMH,RAM,DUNGLUONG,MAUSAC,TGBH\n"
                    + "                    from MATHANG join HANGSANXUAT on MATHANG.MAHSX=HANGSANXUAT.MAHSX\n"
                    + "                    join QUOCGIA on MATHANG.MAQG= QUOCGIA.MAQG where TRANGTHAI = 1 and"
                    + "                     (mamh like '%"+keywork+"%' or tenMH like N'%"+keywork+"%' or TENHSX like N'%"+keywork+"%')";
            ResultSet rs = JdbcHelper.query(sql);
            modelmatHang.setRowCount(0);
            while (rs.next()) {
                modelmatHang.addRow(new Object[]{
                    rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4) + "GB",
                    rs.getString(5) + "GB", rs.getString(6),
                    rs.getString(7) + " tháng"});
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void clearform() {
        this.txtNgayHetHan.setText("");
        this.txtsoIMEI.setText("");
        this.txtMaMH.setText("");
        indexBaoHanh = -1;
        indexMatHang = -1;
        btnThem.setEnabled(false);
        btnSua.setEnabled(false);
    }

    private void insert() {
        PhieuBaoHanh ph = setform();
        try {
            dao.insert(ph);
            this.filltable();
            Msgbox.alert(this, "Thêm mới thành công");
        } catch (Exception e) {
            e.printStackTrace();
            Msgbox.alert(this, "Thêm mới thất  bại");
        }

    }

    private void update() {
        try {
            String update = "update PHIEUBAOHANH set TRANGTHAI = ? where SOIMEI = ?";
            JdbcHelper.update(update, rdoDangBaoHanh.isSelected(), txtsoIMEI.getText());
            this.filltable();
            Msgbox.alert(this, "Cập nhật thành công");
        } catch (Exception e) {
            e.printStackTrace();
            Msgbox.alert(this, "Cập nhật thất bại");
        }
    }

    private PhieuBaoHanh setform() {
        PhieuBaoHanh ph = new PhieuBaoHanh();
        try {
            ph.setSoIMEI(txtsoIMEI.getText());
            ph.setMaMH(txtMaMH.getText());
            ph.setNgayHetHan(XDate.toDate(this.txtNgayHetHan.getText(), "dd/MM/yyyy"));
            ph.setTrangThai(rdoDangBaoHanh.isSelected());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ph;

    }

    private boolean checkform() {
        String reSoIMEI = "\\d{15}";
        String soimei = this.txtsoIMEI.getText();
        if (this.txtsoIMEI.getText().trim().length() == 0) {
            Msgbox.alert(this, "Số IMEI không được dể trống");
            return false;
        }
        if (!txtsoIMEI.getText().matches(reSoIMEI)) {
            Msgbox.alert(this, "Số IMEI phải có 15 số");
            return false;
        }
        return true;
    }

    private void showDetaiMatHang() {
        indexBaoHanh = -1;
        indexMatHang = tblMatHang.getSelectedRow();
        txtsoIMEI.setText("");
        rdoDangBaoHanh.setEnabled(false);
        txtMaMH.setText(modelmatHang.getValueAt(indexMatHang, 0).toString());
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        String tgbh = modelmatHang.getValueAt(indexMatHang, 6).toString().substring(0, 2);
        int tgbhint = Integer.parseInt(tgbh);
        c.add(Calendar.MONTH, tgbhint);
        tgbh = sdf.format(c.getTime());
        txtNgayHetHan.setText(tgbh);
        tblMatHang.setRowSelectionInterval(indexMatHang, indexMatHang);
        updateStaTus();
    }

    private void updateStaTus() {
        boolean edit = indexBaoHanh >= 0;
        txtsoIMEI.setEditable(!edit);
        btnThem.setEnabled(!edit);
        btnSua.setEnabled(edit);
        rdoDangBaoHanh.setEnabled(edit);
    }

    private void showDetaiBaoHanh() {
        indexMatHang = -1;
        indexBaoHanh = tblBaoHanh.getSelectedRow();
        txtsoIMEI.setText(tblBaoHanh.getValueAt(indexBaoHanh, 0).toString());
        txtMaMH.setText(tblBaoHanh.getValueAt(indexBaoHanh, 1).toString());
        txtNgayHetHan.setText(tblBaoHanh.getValueAt(indexBaoHanh, 3).toString());
        rdoDangBaoHanh.setSelected(tblBaoHanh.getValueAt(indexBaoHanh, 4).toString().equalsIgnoreCase("Đang bảo hành"));
        rdoTrong.setSelected(!tblBaoHanh.getValueAt(indexBaoHanh, 4).toString().equalsIgnoreCase("Đang bảo hành"));
        updateStaTus();
    }
}
