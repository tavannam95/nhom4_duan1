/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mobilez.ui;

import com.mobilez.models.Kho;
import com.mobilez.utils.JdbcHelper;
import com.mobilez.utils.Msgbox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
/**
 *
 * @author uhtku
 */
public class FrmPhieuXuat extends javax.swing.JPanel {

    /**
     * Creates new form FrmPhieuXuat
     */
    DefaultTableModel modelList;
    DefaultTableModel modelKH;
    DefaultTableModel modelHDCT;
    DefaultComboBoxModel<Kho> modelCboKho;
    int indexList = -1, indexHDCT = -1;
    public FrmPhieuXuat() {
        initComponents();
        modelList = (DefaultTableModel) tblList.getModel();
        modelHDCT = (DefaultTableModel) tblHoaDonChiTiet.getModel();
        modelCboKho = (DefaultComboBoxModel) cboKho.getModel();
        this.fillCboKho();
        this.fillTablePr();
    }

    private void fillCboKho() {
        try {
            String sql = "select * from KHO";
            ResultSet rs = JdbcHelper.query(sql);
            while (rs.next()) {
                Kho k = new Kho(rs.getString(1), rs.getString(2), rs.getString(3));
                modelCboKho.addElement(k);
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void fillTablePr() {
        try {
            String sql = "select MATHANG.MAMH,TENHSX,TENMH,RAM,DUNGLUONG,MAUSAC,TENQG, KHOHANG.SOLUONG\n"
                    + "from MATHANG join KHOHANG ON MATHANG.MAMH=KHOHANG.MAMH\n"
                    + "join HANGSANXUAT on MATHANG.MAHSX=HANGSANXUAT.MAHSX\n"
                    + "join QUOCGIA on MATHANG.MAQG=QUOCGIA.MAQG\n"
                    + "where MAK like ? AND TRANGTHAI = 1";
            Kho k = (Kho) cboKho.getSelectedItem();
            ResultSet rs = JdbcHelper.query(sql, k.getMaK());
            String giaBanSi;
            modelList.setRowCount(0);
            while (rs.next()) {
                modelList.addRow(new Object[]{
                    rs.getString(1), rs.getString(2), rs.getString(3),
                    this.getDlRAM(rs.getInt(4)), this.getDlRAM(rs.getInt(5)), rs.getString(6),
                    rs.getString(7), rs.getString(8)});
            }
            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private String getDlRAM(int DLorRAM) {
        return DLorRAM + "GB";
    }
    
    private void searchMH() {
        try {
            String sql = "select MATHANG.MAMH,TENHSX,TENMH,RAM,DUNGLUONG,MAUSAC,TENQG,\n"
                    + " KHOHANG.SOLUONG "
                    + "from MATHANG join KHOHANG ON MATHANG.MAMH=KHOHANG.MAMH\n"
                    + "join HANGSANXUAT on MATHANG.MAHSX=HANGSANXUAT.MAHSX\n"
                    + "join QUOCGIA on MATHANG.MAQG=QUOCGIA.MAQG\n"
                    + "where MAK like ? and (MATHANG.MAMH like ? or TENMH like ? or TENHSX like ?) and TRANGTHAI = 1";
            Kho k = (Kho) cboKho.getSelectedItem();
            String search = "%" + txtSearch.getText() + "%";
            ResultSet rs = JdbcHelper.query(sql, k.getMaK(), search, search, search);
            
            modelList.setRowCount(0);
            while (rs.next()) {
                modelList.addRow(new Object[]{
                    rs.getString(1), rs.getString(2), rs.getString(3),
                    this.getDlRAM(rs.getInt(4)), this.getDlRAM(rs.getInt(5)), rs.getString(6),
                    rs.getString(7), rs.getString(8)});
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void addToTblList(){
        try {
            String sql = "select MATHANG.MAMH,TENHSX,TENMH,RAM,DUNGLUONG,MAUSAC,TENQG, KHOHANG.SOLUONG\n"
                    + "from MATHANG join KHOHANG ON MATHANG.MAMH=KHOHANG.MAMH\n"
                    + "join HANGSANXUAT on MATHANG.MAHSX=HANGSANXUAT.MAHSX\n"
                    + "join QUOCGIA on MATHANG.MAQG=QUOCGIA.MAQG\n"
                    + "where MATHANG.MAMH like ?";
            String maMH = tblHoaDonChiTiet.getValueAt(indexHDCT, 0).toString();
            ResultSet rs = JdbcHelper.query(sql, maMH);
            modelList.setRowCount(0);
            while (rs.next()) {
                modelList.addRow(new Object[]{
                    rs.getString(1), rs.getString(2), rs.getString(3),
                    this.getDlRAM(rs.getInt(4)), this.getDlRAM(rs.getInt(5)), rs.getString(6),
                    rs.getString(7),  rs.getString(8)});
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void removeHDCT(){
        try {
            modelHDCT.removeRow(indexHDCT);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void removeTblList() {
        try {
            modelList.removeRow(indexList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void addToHDCT() {
        try {
            modelHDCT.addRow(new Object[]{
                tblList.getValueAt(indexList, 0).toString(),
                tblList.getValueAt(indexList, 1).toString(),
                tblList.getValueAt(indexList, 2).toString(),
                tblList.getValueAt(indexList, 3).toString(),
                tblList.getValueAt(indexList, 4).toString(),
                tblList.getValueAt(indexList, 5).toString(),
                
                ""
            });
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
        lblHoaDonCT = new javax.swing.JLabel();
        btnXuatKho = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        btnThemVaoHoaDon = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblHoaDonChiTiet = new javax.swing.JTable();
        lblKho = new javax.swing.JLabel();
        btnXoaTatCa = new javax.swing.JButton();
        cboKho = new javax.swing.JComboBox<>();
        lblKhachHang1 = new javax.swing.JLabel();

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
                "Mã MH", "HSX", "Tên MH", "RAM", "Dung lượng", "Màu", "Quốc gia", "Số lượng"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
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

        lblHoaDonCT.setFont(new java.awt.Font("Baloo 2 ExtraBold", 0, 24)); // NOI18N
        lblHoaDonCT.setForeground(new java.awt.Color(255, 255, 255));
        lblHoaDonCT.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHoaDonCT.setText("PHIẾU XUẤT KHO");

        btnXuatKho.setBackground(new java.awt.Color(34, 116, 173));
        btnXuatKho.setFont(new java.awt.Font("Baloo 2", 1, 18)); // NOI18N
        btnXuatKho.setForeground(new java.awt.Color(255, 255, 255));
        btnXuatKho.setText("Xuất kho");
        btnXuatKho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatKhoActionPerformed(evt);
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

        btnThemVaoHoaDon.setBackground(new java.awt.Color(34, 116, 173));
        btnThemVaoHoaDon.setFont(new java.awt.Font("Baloo 2", 1, 12)); // NOI18N
        btnThemVaoHoaDon.setForeground(new java.awt.Color(255, 255, 255));
        btnThemVaoHoaDon.setText("Thêm vào phiếu xuất kho");
        btnThemVaoHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemVaoHoaDonActionPerformed(evt);
            }
        });

        tblHoaDonChiTiet.setBackground(new java.awt.Color(34, 116, 173));
        tblHoaDonChiTiet.setFont(new java.awt.Font("Baloo 2", 0, 13)); // NOI18N
        tblHoaDonChiTiet.setForeground(new java.awt.Color(255, 255, 255));
        tblHoaDonChiTiet.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã mặt hàng", "Hãng sản xuất", "Tên mặt hàng", "RAM", "Dung lượng", "Màu", "Số lượng"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblHoaDonChiTiet.setGridColor(new java.awt.Color(255, 255, 255));
        tblHoaDonChiTiet.setRowHeight(25);
        tblHoaDonChiTiet.setSelectionBackground(new java.awt.Color(51, 51, 51));
        tblHoaDonChiTiet.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblHoaDonChiTiet.setShowGrid(true);
        tblHoaDonChiTiet.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDonChiTietMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblHoaDonChiTiet);

        lblKho.setFont(new java.awt.Font("Baloo Chettan 2", 1, 14)); // NOI18N
        lblKho.setForeground(new java.awt.Color(255, 255, 255));
        lblKho.setText("Kho");

        btnXoaTatCa.setBackground(new java.awt.Color(34, 116, 173));
        btnXoaTatCa.setFont(new java.awt.Font("Baloo 2", 1, 12)); // NOI18N
        btnXoaTatCa.setForeground(new java.awt.Color(255, 255, 255));
        btnXoaTatCa.setText("Xóa tất cả");
        btnXoaTatCa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaTatCaActionPerformed(evt);
            }
        });

        cboKho.setBackground(new java.awt.Color(34, 116, 173));
        cboKho.setForeground(new java.awt.Color(255, 255, 255));
        cboKho.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboKhoItemStateChanged(evt);
            }
        });

        lblKhachHang1.setFont(new java.awt.Font("Baloo Chettan 2", 1, 14)); // NOI18N
        lblKhachHang1.setForeground(new java.awt.Color(255, 255, 255));
        lblKhachHang1.setText("Mặt hàng");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnThemVaoHoaDon)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblSearch)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblKhachHang1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblKho)
                                .addGap(18, 18, 18)
                                .addComponent(cboKho, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 666, Short.MAX_VALUE)
                            .addComponent(lblDSMH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblHoaDonCT, javax.swing.GroupLayout.PREFERRED_SIZE, 618, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 618, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jSeparator2, javax.swing.GroupLayout.DEFAULT_SIZE, 1, Short.MAX_VALUE)))
                                .addContainerGap())
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnXuatKho, javax.swing.GroupLayout.PREFERRED_SIZE, 376, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(105, 105, 105))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnXoaTatCa)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnXoa)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnXoa, btnXoaTatCa});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDSMH)
                    .addComponent(lblHoaDonCT))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblKhachHang1)
                        .addComponent(lblKho)
                        .addComponent(cboKho, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnXoaTatCa)
                            .addComponent(btnXoa))
                        .addGap(18, 18, 18)
                        .addComponent(btnXuatKho))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 485, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnThemVaoHoaDon)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblListMouseClicked
        // TODO add your handling code here:
        indexList = tblList.getSelectedRow();
        tblList.setRowSelectionInterval(indexList, indexList);
    }//GEN-LAST:event_tblListMouseClicked

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        // TODO add your handling code here:
        indexList = -1;
        searchMH();
    }//GEN-LAST:event_txtSearchKeyReleased

    private void btnXuatKhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatKhoActionPerformed
        // TODO add your handling code here:
        
        Msgbox.alert(null, "In hóa đơn thành công!");
    }//GEN-LAST:event_btnXuatKhoActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        if (tblHoaDonChiTiet.getRowCount() <= 0) {
            Msgbox.alert(null, "Danh sách trống!");
            return;
        }
        if (indexHDCT == -1) {
            Msgbox.alert(null, "Bạn chưa chọn mặt hàng cần xóa!");
            return;
        }
        this.addToTblList();
        this.removeHDCT();
        if (tblHoaDonChiTiet.getRowCount() <= 0) {
            indexHDCT = -1;
            tblHoaDonChiTiet.setRowSelectionAllowed(false);
            btnXuatKho.setEnabled(false);
        }
        if (tblHoaDonChiTiet.getRowCount() == indexList) {
            indexHDCT--;
            this.tblHoaDonChiTiet.setRowSelectionInterval(indexList, indexList);
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnThemVaoHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemVaoHoaDonActionPerformed
        // TODO add your handling code here:
        if (tblList.getRowCount() <= 0) {
            Msgbox.alert(null, "Danh sách trống!");
            return;
        }
        if (indexList == -1) {
            Msgbox.alert(null, "Bạn chưa chọn mặt hàng cần thêm!");
            return;
        }
        this.addToHDCT();
        this.removeTblList();
        if (tblList.getRowCount() <= 0) {
            indexList = -1;
            tblList.setRowSelectionAllowed(false);
        }
        if (tblList.getRowCount() == indexList) {
            indexList--;
            this.tblList.setRowSelectionInterval(indexList, indexList);
        }
    }//GEN-LAST:event_btnThemVaoHoaDonActionPerformed

    private void tblHoaDonChiTietMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonChiTietMouseClicked
        // TODO add your handling code here:
        indexHDCT = tblHoaDonChiTiet.getSelectedRow();
    }//GEN-LAST:event_tblHoaDonChiTietMouseClicked

    private void btnXoaTatCaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaTatCaActionPerformed
        // TODO add your handling code here:
        if (Msgbox.confirm(null, "Bạn có muốn làm mới danh sách hóa đơn chi tiết không?")) {
            modelHDCT.setRowCount(0);
            this.fillTablePr();
        }
    }//GEN-LAST:event_btnXoaTatCaActionPerformed

    private void cboKhoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboKhoItemStateChanged
        // TODO add your handling code here:
        this.fillTablePr();
    }//GEN-LAST:event_cboKhoItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnThemVaoHoaDon;
    private javax.swing.JButton btnXoa;
    private javax.swing.JButton btnXoaTatCa;
    private javax.swing.JButton btnXuatKho;
    private javax.swing.JComboBox<String> cboKho;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lblDSMH;
    private javax.swing.JLabel lblHoaDonCT;
    private javax.swing.JLabel lblKhachHang1;
    private javax.swing.JLabel lblKho;
    private javax.swing.JLabel lblSearch;
    private javax.swing.JTable tblHoaDonChiTiet;
    private javax.swing.JTable tblList;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
