/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mobilez.ui;

import com.mobilez.models.Kho;
import com.mobilez.utils.Auth;
import com.mobilez.utils.JdbcHelper;
import com.mobilez.utils.Msgbox;
import com.mobilez.utils.StringToPrice;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.text.SimpleDateFormat;

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
        modelHDCT = (DefaultTableModel) tblCTPX.getModel();
        modelCboKho = (DefaultComboBoxModel) cboKho.getModel();
        this.fillCboKho();
        this.fillTablePr();

    }

    private void fillCboKho() {
        try {
            String sql = "select * from KHO";
            ResultSet rs = JdbcHelper.query(sql);
            while (rs.next()) {
                Kho k = new Kho(rs.getString(1), rs.getString(2), rs.getString(3),rs.getBoolean(4));
                modelCboKho.addElement(k);
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void fillTablePr() {
        try {
            String sql = "select MATHANG.MAMH,TENHSX,TENMH,RAM,DUNGLUONG,MAUSAC,TENQG,GIAMUA, KHOHANG.SOLUONG\n"
                    + "from MATHANG join KHOHANG ON MATHANG.MAMH=KHOHANG.MAMH\n"
                    + "join HANGSANXUAT on MATHANG.MAHSX=HANGSANXUAT.MAHSX\n"
                    + "join QUOCGIA on MATHANG.MAQG=QUOCGIA.MAQG\n"
                    + "where MAK like ? AND TRANGTHAI = 1";
            Kho k = (Kho) cboKho.getSelectedItem();
            ResultSet rs = JdbcHelper.query(sql, k.getMaK());
            String giaMua;
            modelList.setRowCount(0);
            while (rs.next()) {
                giaMua = rs.getString(8);
                int gbs = giaMua.indexOf(".");
                giaMua = giaMua.substring(0, gbs);
                modelList.addRow(new Object[]{
                    rs.getString(1), rs.getString(2), rs.getString(3),
                    this.getDlRAM(rs.getInt(4)), this.getDlRAM(rs.getInt(5)), rs.getString(6),
                    rs.getString(7), giaMua, rs.getString(9)});
            }
            rs.close();

        } catch (Exception e) {
//            e.printStackTrace();
        }
    }

    private String getDlRAM(int DLorRAM) {
        return DLorRAM + "GB";
    }

    private void searchMH() {
        try {
            String sql = "select MATHANG.MAMH,TENHSX,TENMH,RAM,DUNGLUONG,MAUSAC,TENQG,GIAMUA,\n"
                    + " KHOHANG.SOLUONG "
                    + "from MATHANG join KHOHANG ON MATHANG.MAMH=KHOHANG.MAMH\n"
                    + "join HANGSANXUAT on MATHANG.MAHSX=HANGSANXUAT.MAHSX\n"
                    + "join QUOCGIA on MATHANG.MAQG=QUOCGIA.MAQG\n"
                    + "where MAK like ? and (MATHANG.MAMH like ? or TENMH like ? or TENHSX like ?) and TRANGTHAI = 1";
            Kho k = (Kho) cboKho.getSelectedItem();
            String search = "%" + txtSearch.getText() + "%";
            ResultSet rs = JdbcHelper.query(sql, k.getMaK(), search, search, search);
            String giaMua;
            modelList.setRowCount(0);
            while (rs.next()) {
                giaMua = rs.getString(8);
                int gbs = giaMua.indexOf(".");
                giaMua = giaMua.substring(0, gbs);
                modelList.addRow(new Object[]{
                    rs.getString(1), rs.getString(2), rs.getString(3),
                    this.getDlRAM(rs.getInt(4)), this.getDlRAM(rs.getInt(5)), rs.getString(6),
                    rs.getString(7), giaMua, rs.getString(9)});
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addToTblList() {
        try {
            String sql = "select MATHANG.MAMH,TENHSX,TENMH,RAM,DUNGLUONG,MAUSAC,TENQG,GIAMUA, KHOHANG.SOLUONG\n"
                    + "from MATHANG join KHOHANG ON MATHANG.MAMH=KHOHANG.MAMH\n"
                    + "join HANGSANXUAT on MATHANG.MAHSX=HANGSANXUAT.MAHSX\n"
                    + "join QUOCGIA on MATHANG.MAQG=QUOCGIA.MAQG\n"
                    + "where MATHANG.MAMH like ?";
            String maMH = tblCTPX.getValueAt(indexHDCT, 0).toString();
            ResultSet rs = JdbcHelper.query(sql, maMH);
            String giaMua;
            while (rs.next()) {
                giaMua = rs.getString(8);
                int gbs = giaMua.indexOf(".");
                giaMua = giaMua.substring(0, gbs);
                modelList.addRow(new Object[]{
                    rs.getString(1), rs.getString(2), rs.getString(3),
                    this.getDlRAM(rs.getInt(4)), this.getDlRAM(rs.getInt(5)), rs.getString(6),
                    rs.getString(7), giaMua, rs.getString(9)});
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void removeHDCT() {
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
                tblList.getValueAt(indexList, 7).toString(),
                tblList.getValueAt(indexList, 8).toString(),
                ""
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void insertIntoPXK() {
        try {
            String sql = "insert into PHIEUXUATKHO\n"
                    + "values (?,?,?,?,?)";
            Kho k = (Kho) cboKho.getSelectedItem();
            String maNV = Auth.user.getMaNV();
            String maQH = Auth.maQuay;
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            java.util.Date ngayXK = new java.util.Date();
            ngayXK = sdf.parse(sdf.format(ngayXK));
            int s = JdbcHelper.update(sql, k.getMaK(), maNV, maQH, ngayXK, this.getTongGia());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int getSLTrongKhoHang(String maMH, String maK) {
        try {
            String sql = "select SOLUONG\n"
                    + " from KHOHANG where MAMH like ? and MAK like ?";
            ResultSet rs = JdbcHelper.query(sql, maMH, maK);
            int sl = 0;
            while (rs.next()) {
                sl = rs.getInt(1);
            }
            rs.close();
            return sl;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    private int getSLTrongQH(String maMH, String maQH) {
        try {
            String sql = "select SOLUONG from CHITIETQUAYHANG\n"
                    + "where MAMH like ? and MAQH like ?";
            ResultSet rs = JdbcHelper.query(sql, maMH, maQH);
            int sl = 0;
            while (rs.next()) {
                sl = rs.getInt(1);
            }
            rs.close();
            return sl;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    private void updateKhoHang() {
        try {
            String sql = " update KHOHANG set SOLUONG = SOLUONG - ?\n"
                    + " where MAMH like ? and MAK like ?";
            Kho k = (Kho) cboKho.getSelectedItem();
            for (int i = 0; i < tblCTPX.getRowCount(); i++) {
                String maMH = tblCTPX.getValueAt(i, 0).toString();
                int sl = Integer.parseInt(tblCTPX.getValueAt(i, 8).toString());
                int s = JdbcHelper.update(sql, sl, maMH, k.getMaK());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateQH() {
        try {
            String sql = "update CHITIETQUAYHANG set SOLUONG = SOLUONG + ?\n"
                    + "                     where MAQH like ? and MAMH like ?";
            String maQH = Auth.maQuay;
            for (int i = 0; i < tblCTPX.getRowCount(); i++) {
                String maMH = tblCTPX.getValueAt(i, 0).toString();
                int sl = Integer.parseInt(tblCTPX.getValueAt(i, 8).toString());
                int s = JdbcHelper.update(sql, sl, maQH, maMH);
            }
        } catch (Exception e) {
//            e.printStackTrace();
        }
    }

    private String getTongGia() {
        long tongGia = 0;
        for (int i = 0; i < tblCTPX.getRowCount(); i++) {
            long gia = Long.parseLong(tblCTPX.getValueAt(i, 6).toString());
            int sl = Integer.parseInt(tblCTPX.getValueAt(i, 8).toString());
            tongGia += (sl * gia);
        }
        String tongGiaString = tongGia + "";
//        int vt = tongGiaString.indexOf(".");
//        tongGiaString = tongGiaString.substring(0, vt);
        return tongGiaString;
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
        tblCTPX = new javax.swing.JTable();
        lblKho = new javax.swing.JLabel();
        lblTongGia = new javax.swing.JLabel();
        btnXoaTatCa = new javax.swing.JButton();
        cboKho = new javax.swing.JComboBox<>();
        lblRsTongGia = new javax.swing.JLabel();
        lblKhachHang1 = new javax.swing.JLabel();
        btnOK = new javax.swing.JButton();

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
                "Mã MH", "HSX", "Tên MH", "RAM", "Dung lượng", "Màu", "Quốc gia", "Giá mua", "Số lượng"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, true, false, false
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
        lblHoaDonCT.setText("CHI TIẾT PHIẾU XUẤT");

        btnXuatKho.setBackground(new java.awt.Color(34, 116, 173));
        btnXuatKho.setFont(new java.awt.Font("Baloo 2", 1, 18)); // NOI18N
        btnXuatKho.setForeground(new java.awt.Color(255, 255, 255));
        btnXuatKho.setText("Xuất kho");
        btnXuatKho.setEnabled(false);
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

        tblCTPX.setBackground(new java.awt.Color(34, 116, 173));
        tblCTPX.setFont(new java.awt.Font("Baloo 2", 0, 13)); // NOI18N
        tblCTPX.setForeground(new java.awt.Color(255, 255, 255));
        tblCTPX.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã MH", "HSX", "Tên MH", "RAM", "Dung lượng", "Màu", "Giá mua", "SL còn", "SL xuất"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblCTPX.setGridColor(new java.awt.Color(255, 255, 255));
        tblCTPX.setRowHeight(25);
        tblCTPX.setSelectionBackground(new java.awt.Color(51, 51, 51));
        tblCTPX.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblCTPX.setShowGrid(true);
        tblCTPX.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCTPXMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblCTPX);

        lblKho.setFont(new java.awt.Font("Baloo Chettan 2", 1, 14)); // NOI18N
        lblKho.setForeground(new java.awt.Color(255, 255, 255));
        lblKho.setText("Kho");

        lblTongGia.setFont(new java.awt.Font("Baloo Chettan 2", 1, 14)); // NOI18N
        lblTongGia.setForeground(new java.awt.Color(255, 255, 255));
        lblTongGia.setText("Tổng giá:");

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

        lblRsTongGia.setFont(new java.awt.Font("Baloo Chettan 2", 1, 24)); // NOI18N
        lblRsTongGia.setForeground(new java.awt.Color(0, 0, 0));
        lblRsTongGia.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        lblKhachHang1.setFont(new java.awt.Font("Baloo Chettan 2", 1, 14)); // NOI18N
        lblKhachHang1.setForeground(new java.awt.Color(255, 255, 255));
        lblKhachHang1.setText("Mặt hàng");

        btnOK.setBackground(new java.awt.Color(34, 116, 173));
        btnOK.setFont(new java.awt.Font("Baloo 2", 1, 12)); // NOI18N
        btnOK.setForeground(new java.awt.Color(255, 255, 255));
        btnOK.setText("Đồng ý");
        btnOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOKActionPerformed(evt);
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
                                .addGap(0, 6, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblHoaDonCT, javax.swing.GroupLayout.PREFERRED_SIZE, 618, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 618, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(btnXoaTatCa)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(btnXoa)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(btnOK)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(lblTongGia, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(lblRsTongGia, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jSeparator2, javax.swing.GroupLayout.DEFAULT_SIZE, 1, Short.MAX_VALUE)))
                                .addContainerGap())
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnXuatKho, javax.swing.GroupLayout.PREFERRED_SIZE, 376, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(108, 108, 108))))))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnOK, btnXoa, btnXoaTatCa});

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
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnXoaTatCa)
                                .addComponent(btnXoa)
                                .addComponent(lblTongGia)
                                .addComponent(btnOK))
                            .addComponent(lblRsTongGia, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
        tblList.setRowSelectionAllowed(true);
        tblList.setRowSelectionInterval(indexList, indexList);
    }//GEN-LAST:event_tblListMouseClicked

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        // TODO add your handling code here:
        indexList = -1;
        searchMH();
    }//GEN-LAST:event_txtSearchKeyReleased

    private void btnXuatKhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatKhoActionPerformed
        // TODO add your handling code here:
        if (Msgbox.confirm(null, "Bạn có muốn xuất kho không?")) {
            this.updateKhoHang();
            this.updateQH();
            this.insertIntoPXK();
            Msgbox.alert(null, "Xuất kho thành công!");
            modelHDCT.setRowCount(0);
            this.fillTablePr();
            this.btnXuatKho.setEnabled(false);
            lblRsTongGia.setText("");
        }
        
    }//GEN-LAST:event_btnXuatKhoActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        btnXuatKho.setEnabled(false);
        if (tblCTPX.getRowCount() <= 0) {
            Msgbox.alert(null, "Danh sách trống!");
            return;
        }
        if (indexHDCT == -1) {
            Msgbox.alert(null, "Bạn chưa chọn mặt hàng cần xóa!");
            return;
        }
        this.addToTblList();
        this.removeHDCT();
        if (tblCTPX.getRowCount() <= 0) {
            indexHDCT = -1;
            tblCTPX.setRowSelectionAllowed(false);
            btnXuatKho.setEnabled(false);
        }
        if (tblCTPX.getRowCount() == indexHDCT) {
            indexHDCT--;
            this.tblCTPX.setRowSelectionInterval(indexHDCT, indexHDCT);
        }
        if (indexHDCT >= 0) {
            this.tblList.setRowSelectionInterval(indexHDCT, indexHDCT);
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
        if (tblList.getRowCount() == 0) {
            indexList = -1;
            tblList.setRowSelectionAllowed(false);
        }
        if (tblList.getRowCount() == indexList) {
            indexList--;
            tblList.setRowSelectionAllowed(true);
            this.tblList.setRowSelectionInterval(indexList, indexList);
        }
        if (indexHDCT >= 0) {
            tblList.setRowSelectionAllowed(true);
            this.tblList.setRowSelectionInterval(indexList, indexList);
        }

    }//GEN-LAST:event_btnThemVaoHoaDonActionPerformed

    private void tblCTPXMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCTPXMouseClicked
        // TODO add your handling code here:
        indexHDCT = tblCTPX.getSelectedRow();
        tblCTPX.setRowSelectionAllowed(true);
    }//GEN-LAST:event_tblCTPXMouseClicked

    private void btnXoaTatCaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaTatCaActionPerformed
        // TODO add your handling code here:
        if (Msgbox.confirm(null, "Bạn có muốn làm mới danh sách hóa đơn chi tiết không?")) {
            modelHDCT.setRowCount(0);
            this.fillTablePr();
            this.btnXuatKho.setEnabled(false);
        }
    }//GEN-LAST:event_btnXoaTatCaActionPerformed

    private void cboKhoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboKhoItemStateChanged
        // TODO add your handling code here:
        this.fillTablePr();
    }//GEN-LAST:event_cboKhoItemStateChanged

    private void btnOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOKActionPerformed
        // TODO add your handling code here:
        if (tblCTPX.getRowCount() <= 0) {
            Msgbox.alert(null, "Danh sách trống!");
            return;
        }

        //validate sl
        for (int i = 0; i < tblCTPX.getRowCount(); i++) {
            if (tblCTPX.getValueAt(i, 8).toString().trim().equals("")) {
                Msgbox.alert(null, "Số lượng không được để trống!");
                return;
            }
        }
        for (int i = 0; i < tblCTPX.getRowCount(); i++) {
            try {
                Integer.parseInt(tblCTPX.getValueAt(i, 8).toString());
            } catch (Exception e) {
                Msgbox.alert(null, "Vui lòng nhập số lượng là số!");
                return;
            }
        }
        for (int i = 0; i < tblCTPX.getRowCount(); i++) {
            if (Integer.parseInt(tblCTPX.getValueAt(i, 8).toString()) <= 0) {
                Msgbox.alert(null, "Số lượng phải lớn hơn 0!");
                return;
            }
        }
        for (int i = 0; i < tblCTPX.getRowCount(); i++) {
            int slc = 0, slx = 0;
            try {
                slc = Integer.parseInt(tblCTPX.getValueAt(i, 7).toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                slx = Integer.parseInt(tblCTPX.getValueAt(i, 8).toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (slc < slx) {
                Msgbox.alert(null, "Số lượng còn trong kho không đủ!");
                return;
            }

        }
        lblRsTongGia.setText(StringToPrice.getPrice(this.getTongGia()));
        btnXuatKho.setEnabled(true);
    }//GEN-LAST:event_btnOKActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnOK;
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
    private javax.swing.JLabel lblRsTongGia;
    private javax.swing.JLabel lblSearch;
    private javax.swing.JLabel lblTongGia;
    private javax.swing.JTable tblCTPX;
    private javax.swing.JTable tblList;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
