/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mobilez.ui;

import com.mobilez.utils.JdbcHelper;
import com.mobilez.utils.Msgbox;
import java.awt.Color;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author uhtku
 */
public class FrmCapNhatGiaHang extends javax.swing.JPanel {

    /**
     * Creates new form FrmCapNhatGiaHang
     */
    DefaultTableModel modelList;
    DefaultTableModel modelCN;
    int index = -1, indexUP = -1;

    public FrmCapNhatGiaHang() {
        initComponents();
        modelCN = (DefaultTableModel) tblCapNhat.getModel();
        modelList = (DefaultTableModel) tblList.getModel();
        this.fillTablePr();
    }

    private void searchMH() {
        try {
            String sql = "select MAMH,TENHSX,TENMH,RAM,DUNGLUONG,MAUSAC,TENQG,\n"
                    + "GIAMUA,GIABANSI,GIABANLE,TRANGTHAI\n"
                    + "from MATHANG join HANGSANXUAT on MATHANG.MAHSX=HANGSANXUAT.MAHSX\n"
                    + "join QUOCGIA on MATHANG.MAQG= QUOCGIA.MAQG \n"
                    + "where MAMH like ? or TENMH like ? or TENHSX like ?";
            String search = "%" + txtSearch.getText() + "%";
            ResultSet rs = JdbcHelper.query(sql, search, search, search);
            String giaMua, giaBanSi, giaBanLe;
            modelList.setRowCount(0);
            while (rs.next()) {
                giaMua = rs.getString(8);
                giaBanSi = rs.getString(9);
                giaBanLe = rs.getString(10);
                int gm = giaMua.indexOf(".");
                int gbs = giaBanSi.indexOf(".");
                int gbl = giaBanLe.indexOf(".");
                giaMua = giaMua.substring(0, gm);
                giaBanSi = giaBanSi.substring(0, gbs);
                giaBanLe = giaBanLe.substring(0, gbl);
                modelList.addRow(new Object[]{
                    rs.getString(1), rs.getString(2), rs.getString(3),
                    this.getDlRAM(rs.getInt(4)), this.getDlRAM(rs.getInt(5)), rs.getString(6),
                    rs.getString(7), giaMua, giaBanSi,
                    giaBanLe, this.getTrangThaiMH(rs.getBoolean(11))});
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void fillTableUP() {
        try {
//            modelCN.setRowCount(0);
            modelCN.addRow(new Object[]{
                tblList.getValueAt(index, 0).toString(),
                tblList.getValueAt(index, 1).toString(),
                tblList.getValueAt(index, 2).toString(),
                tblList.getValueAt(index, 3).toString(),
                tblList.getValueAt(index, 4).toString(),
                tblList.getValueAt(index, 5).toString(),
                tblList.getValueAt(index, 6).toString(),
                tblList.getValueAt(index, 7).toString(),
                tblList.getValueAt(index, 8).toString(),
                tblList.getValueAt(index, 9).toString(),
                tblList.getValueAt(index, 10).toString(),});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addToList() {
        try {
//            modelCN.setRowCount(0);
            modelList.addRow(new Object[]{
                tblCapNhat.getValueAt(indexUP, 0).toString(),
                tblCapNhat.getValueAt(indexUP, 1).toString(),
                tblCapNhat.getValueAt(indexUP, 2).toString(),
                tblCapNhat.getValueAt(indexUP, 3).toString(),
                tblCapNhat.getValueAt(indexUP, 4).toString(),
                tblCapNhat.getValueAt(indexUP, 5).toString(),
                tblCapNhat.getValueAt(indexUP, 6).toString(),
                tblCapNhat.getValueAt(indexUP, 7).toString(),
                tblCapNhat.getValueAt(indexUP, 8).toString(),
                tblCapNhat.getValueAt(indexUP, 9).toString(),
                tblCapNhat.getValueAt(indexUP, 10).toString(),});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void fillTablePr() {
        try {
            String sql = "select MAMH,TENHSX,TENMH,RAM,DUNGLUONG,MAUSAC,TENQG,\n"
                    + "                    GIAMUA,GIABANSI,GIABANLE,TRANGTHAI\n"
                    + "                    from MATHANG join HANGSANXUAT on MATHANG.MAHSX=HANGSANXUAT.MAHSX\n"
                    + "                    join QUOCGIA on MATHANG.MAQG= QUOCGIA.MAQG";
            ResultSet rs = JdbcHelper.query(sql);
            String giaMua, giaBanSi, giaBanLe;
            modelList.setRowCount(0);
            while (rs.next()) {
                giaMua = rs.getString(8);
                giaBanSi = rs.getString(9);
                giaBanLe = rs.getString(10);
                int gm = giaMua.indexOf(".");
                int gbs = giaBanSi.indexOf(".");
                int gbl = giaBanLe.indexOf(".");
                giaMua = giaMua.substring(0, gm);
                giaBanSi = giaBanSi.substring(0, gbs);
                giaBanLe = giaBanLe.substring(0, gbl);
                modelList.addRow(new Object[]{
                    rs.getString(1), rs.getString(2), rs.getString(3),
                    this.getDlRAM(rs.getInt(4)), this.getDlRAM(rs.getInt(5)), rs.getString(6),
                    rs.getString(7), giaMua, giaBanSi,
                    giaBanLe, this.getTrangThaiMH(rs.getBoolean(11))});
            }
            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void removeTableUP() {
        try {
            modelCN.removeRow(indexUP);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void removeList() {
        for (int i = 0; i < tblCapNhat.getRowCount(); i++) {
            for (int j = 0; j < tblList.getRowCount(); j++) {
                if (tblCapNhat.getValueAt(i, 0).toString().equalsIgnoreCase(tblList.getValueAt(j, 0).toString())) {
                    modelList.removeRow(j);
                }
            }
        }
    }

    private String getDlRAM(int DLorRAM) {
        return DLorRAM + "GB";
    }

    private String getTrangThaiMH(boolean trangThai) {
        if (trangThai) {
            return "Đang kinh doanh";
        } else {
            return "Ngừng kinh doanh";
        }
    }

    private void updatePrice() {
        try {
            String sql = "update MATHANG set \n"
                    + "GIABANLE = ? where MAMH like ?";
            double giaBanLe = Double.parseDouble(txtGiaBanLe.getText());
            String maMH = "";
            for (int i = 0; i < tblCapNhat.getRowCount(); i++) {
                maMH = tblCapNhat.getValueAt(i, 0).toString().trim();
                int s = JdbcHelper.update(sql,  giaBanLe, maMH);
            }
            Msgbox.alert(null, "Cập nhật giá thành công!");
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

        lblDSMH = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblList = new javax.swing.JTable();
        lblSearch = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        lblCapNhat = new javax.swing.JLabel();
        btnCapNhat = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        btnThemVaoDS = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblCapNhat = new javax.swing.JTable();
        lblDSCN = new javax.swing.JLabel();
        txtGiaBanLe = new javax.swing.JTextField();
        lblGiaBanLe = new javax.swing.JLabel();
        btnXoaTatCa = new javax.swing.JButton();

        setBackground(new java.awt.Color(34, 116, 173));

        lblDSMH.setFont(new java.awt.Font("Baloo 2 ExtraBold", 0, 24)); // NOI18N
        lblDSMH.setForeground(new java.awt.Color(255, 255, 255));
        lblDSMH.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDSMH.setText("DANH SÁCH MẶT HÀNG");

        tblList.setBackground(new java.awt.Color(34, 116, 173));
        tblList.setFont(new java.awt.Font("Baloo 2", 0, 13)); // NOI18N
        tblList.setForeground(new java.awt.Color(255, 255, 255));
        tblList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã MH", "HSX", "Tên MH", "RAM", "Dung lượng", "Màu", "Quốc gia", "Giá Mua", "Giá bán sỉ", "Giá bán lẻ", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
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

        lblSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mobilez/icon/search_32px.png"))); // NOI18N

        txtSearch.setBackground(new java.awt.Color(34, 116, 173));
        txtSearch.setForeground(new java.awt.Color(255, 255, 255));
        txtSearch.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)));
        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
        });

        lblCapNhat.setFont(new java.awt.Font("Baloo 2 ExtraBold", 0, 24)); // NOI18N
        lblCapNhat.setForeground(new java.awt.Color(255, 255, 255));
        lblCapNhat.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCapNhat.setText("CẬP NHẬT");

        btnCapNhat.setBackground(new java.awt.Color(34, 116, 173));
        btnCapNhat.setFont(new java.awt.Font("Baloo 2", 1, 12)); // NOI18N
        btnCapNhat.setForeground(new java.awt.Color(255, 255, 255));
        btnCapNhat.setText("Cập nhật");
        btnCapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatActionPerformed(evt);
            }
        });

        btnXoa.setBackground(new java.awt.Color(34, 116, 173));
        btnXoa.setFont(new java.awt.Font("Baloo 2", 1, 12)); // NOI18N
        btnXoa.setForeground(new java.awt.Color(255, 255, 255));
        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        jSeparator2.setBackground(new java.awt.Color(255, 255, 255));

        btnThemVaoDS.setBackground(new java.awt.Color(34, 116, 173));
        btnThemVaoDS.setFont(new java.awt.Font("Baloo 2", 1, 12)); // NOI18N
        btnThemVaoDS.setForeground(new java.awt.Color(255, 255, 255));
        btnThemVaoDS.setText("Thêm vào danh sách");
        btnThemVaoDS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemVaoDSActionPerformed(evt);
            }
        });

        tblCapNhat.setBackground(new java.awt.Color(34, 116, 173));
        tblCapNhat.setFont(new java.awt.Font("Baloo 2", 0, 13)); // NOI18N
        tblCapNhat.setForeground(new java.awt.Color(255, 255, 255));
        tblCapNhat.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã MH", "HSX", "Tên MH", "RAM", "Dung lượng", "Màu", "Quốc gia", "Giá Mua", "Giá bán sỉ", "Giá bán lẻ", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblCapNhat.setGridColor(new java.awt.Color(255, 255, 255));
        tblCapNhat.setRowHeight(25);
        tblCapNhat.setSelectionBackground(new java.awt.Color(51, 51, 51));
        tblCapNhat.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblCapNhat.setShowGrid(true);
        tblCapNhat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCapNhatMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblCapNhat);

        lblDSCN.setFont(new java.awt.Font("Baloo 2 ExtraBold", 0, 20)); // NOI18N
        lblDSCN.setForeground(new java.awt.Color(255, 255, 255));
        lblDSCN.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDSCN.setText("Danh sách mặt hàng cập nhật giá");

        txtGiaBanLe.setBackground(new java.awt.Color(34, 116, 173));
        txtGiaBanLe.setForeground(new java.awt.Color(255, 255, 255));
        txtGiaBanLe.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)));
        txtGiaBanLe.setCaretColor(new java.awt.Color(255, 255, 255));
        txtGiaBanLe.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtGiaBanLetxtDungLuongKeyPressed(evt);
            }
        });

        lblGiaBanLe.setFont(new java.awt.Font("Baloo Chettan 2", 1, 14)); // NOI18N
        lblGiaBanLe.setForeground(new java.awt.Color(255, 255, 255));
        lblGiaBanLe.setText("Giá bán");

        btnXoaTatCa.setBackground(new java.awt.Color(34, 116, 173));
        btnXoaTatCa.setFont(new java.awt.Font("Baloo 2", 1, 12)); // NOI18N
        btnXoaTatCa.setForeground(new java.awt.Color(255, 255, 255));
        btnXoaTatCa.setText("Xóa tất cả");
        btnXoaTatCa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaTatCaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(lblSearch)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 666, Short.MAX_VALUE)
                        .addComponent(lblDSMH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(btnThemVaoDS))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(lblGiaBanLe, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtGiaBanLe, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 618, Short.MAX_VALUE)
                            .addComponent(lblDSCN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jSeparator2))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnXoaTatCa)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnXoa))
                            .addComponent(btnCapNhat)
                            .addComponent(lblCapNhat, javax.swing.GroupLayout.PREFERRED_SIZE, 618, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 28, Short.MAX_VALUE)))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnXoa, btnXoaTatCa});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDSMH)
                    .addComponent(lblCapNhat))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblDSCN))
                    .addComponent(lblSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 417, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnThemVaoDS)
                        .addContainerGap(20, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnXoa)
                            .addComponent(btnXoaTatCa))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtGiaBanLe, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblGiaBanLe))
                        .addGap(18, 18, 18)
                        .addComponent(btnCapNhat)
                        .addGap(29, 29, 29))))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblListMouseClicked
        // TODO add your handling code here:
        index = tblList.getSelectedRow();

    }//GEN-LAST:event_tblListMouseClicked

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        // TODO add your handling code here:
        index=-1;
        searchMH();
        this.removeList();
    }//GEN-LAST:event_txtSearchKeyReleased

    private void btnCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatActionPerformed
        // TODO add your handling code here:
        //validate table
        if (tblCapNhat.getRowCount() == 0) {
            Msgbox.alert(null, "Bạn chưa thêm mặt hàng cần giảm giá!");
            return;
        }
        
        //validate gia ban le
        if (txtGiaBanLe.getText().trim().equals("")) {
            Msgbox.alert(null, "Không được để trống giá bán lẻ!");
            txtGiaBanLe.setText("");
            txtGiaBanLe.requestFocus();
            lblGiaBanLe.setForeground(Color.red);
            return;
        } else {
            lblGiaBanLe.setForeground(Color.white);
        }
        try {
            Integer.parseInt(txtGiaBanLe.getText());
        } catch (Exception e) {
            Msgbox.alert(null, "Giá bán lẻ phải là số!");
            txtGiaBanLe.setText("");
            txtGiaBanLe.requestFocus();
            lblGiaBanLe.setForeground(Color.red);
            return;
        }
        lblGiaBanLe.setForeground(Color.white);
        if (Integer.parseInt(txtGiaBanLe.getText()) <= 0) {
            Msgbox.alert(null, "Giá bán lẻ phải lơn hơn 0!");
            txtGiaBanLe.setText("");
            txtGiaBanLe.requestFocus();
            lblGiaBanLe.setForeground(Color.red);
            return;
        } else {
            lblGiaBanLe.setForeground(Color.white);
        }
        this.updatePrice();
        modelCN.setRowCount(0);
        this.fillTablePr();
        txtGiaBanLe.setText("");
    }//GEN-LAST:event_btnCapNhatActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        if (indexUP == -1) {
            Msgbox.alert(null, "Bạn chưa chọn mặt hàng cần xóa!");
            return;
        }
        if (tblCapNhat.getRowCount() == 0) {
            return;
        }
        this.addToList();
        this.removeTableUP();
        if (tblCapNhat.getRowCount() > 0) {
            if (indexUP == tblCapNhat.getRowCount()) {
                indexUP--;
            }
            tblCapNhat.setRowSelectionInterval(indexUP, indexUP);
        } else {
            indexUP = -1;
        }

    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnThemVaoDSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemVaoDSActionPerformed
        // TODO add your handling code here:
        if (index == -1) {
            Msgbox.alert(null, "Bạn chưa chọn mặt hàng cần thêm!");
            return;
        }

        this.fillTableUP();
        this.removeList();

        if (tblList.getRowCount() > 0) {
            if (index == tblList.getRowCount()) {
                index--;
            }
            tblList.setRowSelectionInterval(index, index);
        } else {
            index = -1;
        }

    }//GEN-LAST:event_btnThemVaoDSActionPerformed

    private void tblCapNhatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCapNhatMouseClicked
        // TODO add your handling code here:
        indexUP = tblCapNhat.getSelectedRow();
    }//GEN-LAST:event_tblCapNhatMouseClicked

    private void txtGiaBanLetxtDungLuongKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGiaBanLetxtDungLuongKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGiaBanLetxtDungLuongKeyPressed

    private void btnXoaTatCaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaTatCaActionPerformed
        // TODO add your handling code here:
        if (tblCapNhat.getRowCount() == 0) {
            Msgbox.alert(null, "Danh sách trống!");
            return;
        }
        if (Msgbox.confirm(null, "Bạn có muốn xóa hết danh sách cập nhật giá không?")) {
            modelCN.setRowCount(0);
            this.fillTablePr();
        }

    }//GEN-LAST:event_btnXoaTatCaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCapNhat;
    private javax.swing.JButton btnThemVaoDS;
    private javax.swing.JButton btnXoa;
    private javax.swing.JButton btnXoaTatCa;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lblCapNhat;
    private javax.swing.JLabel lblDSCN;
    private javax.swing.JLabel lblDSMH;
    private javax.swing.JLabel lblGiaBanLe;
    private javax.swing.JLabel lblSearch;
    private javax.swing.JTable tblCapNhat;
    private javax.swing.JTable tblList;
    private javax.swing.JTextField txtGiaBanLe;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
