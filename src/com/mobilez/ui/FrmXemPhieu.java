/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mobilez.ui;

import com.mobilez.utils.JdbcHelper;
import java.awt.Color;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

/**
 *
 * @author uhtku
 */
public class FrmXemPhieu extends javax.swing.JPanel {

    /**
     * Creates new form FrmXemPhieu
     */
    DefaultTableModel modelPNK;
    DefaultTableModel modelPXK;
    DefaultTableModel modelPBH;
    DefaultTableModel modelPGC;
    DefaultTableModel modelHDBS;
    DefaultTableModel modelHDBL;

    public FrmXemPhieu() {
        initComponents();
        this.setColorTextMenu();
        this.getAllModel();
        this.viewPNK();

    }

    private void viewPNK() {
        try {
            lblPNK.setForeground(Color.orange);
            lblPXK.setForeground(Color.white);
            lblPBH.setForeground(Color.white);
            lblPGC.setForeground(Color.white);
            lblPBHang.setForeground(Color.white);
            pnlParentCard.removeAll();
            pnlParentCard.add(pnlPNK);
            pnlParentCard.validate();
            pnlParentCard.repaint();
            this.fillTablePNK();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setColorTextMenu() {
        try {
            lblPNK.setForeground(Color.white);
            lblPXK.setForeground(Color.white);
            lblPBH.setForeground(Color.white);
            lblPGC.setForeground(Color.white);
            lblPBHang.setForeground(Color.white);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getAllModel() {
        try {
            modelPNK = (DefaultTableModel) tblPNK.getModel();
            modelPXK = (DefaultTableModel) tblPXK.getModel();
            modelPBH = (DefaultTableModel) tblPBH.getModel();
            modelPGC = (DefaultTableModel) tblPGC.getModel();
            modelHDBS = (DefaultTableModel) tblHDBS.getModel();
            modelHDBL = (DefaultTableModel) tblHDBL.getModel();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void fillTablePNK() {
        try {
            String sql = "select MAPNK,TENNCC,TENK,NHANVIEN.HOTEN, convert(varchar,NGAYNHAP,103) as NGAYNHAP,TONGGIA \n"
                    + "from PHIEUNHAPKHO join NHACUNGCAP on PHIEUNHAPKHO.MANCC=NHACUNGCAP.MANCC\n"
                    + "				  join KHO on PHIEUNHAPKHO.MAK=KHO.MAK\n"
                    + "				  join NHANVIEN on PHIEUNHAPKHO.MANV=NHANVIEN.MANV";
            ResultSet rs = JdbcHelper.query(sql);
            modelPNK.setRowCount(0);
            String tongGia;
            while (rs.next()) {
                tongGia = rs.getString("TONGGIA");
                int vt = tongGia.indexOf(".");
                tongGia = tongGia.substring(0, vt);
                modelPNK.addRow(new Object[]{
                    rs.getString("MAPNK"),
                    rs.getString("TENNCC"),
                    rs.getString("TENK"),
                    rs.getString("HOTEN"),
                    rs.getString("NGAYNHAP"),
                    tongGia
                });
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void fillTablePXK() {
        try {
            String sql = "select MAPXK,TENK,HOTEN,TENQH,convert(varchar,NGAYXK,103) as NGAYXK, TONGGIA \n"
                    + "from PHIEUXUATKHO join KHO on PHIEUXUATKHO.MAK=KHO.MAK\n"
                    + "				  join NHANVIEN on PHIEUXUATKHO.MANV=NHANVIEN.MANV\n"
                    + "				  join QUAYHANG on PHIEUXUATKHO.MAQH=QUAYHANG.MAQH";
            ResultSet rs = JdbcHelper.query(sql);
            modelPXK.setRowCount(0);
            String tongGia;
            while (rs.next()) {
                tongGia = rs.getString("TONGGIA");
                int vt = tongGia.indexOf(".");
                tongGia = tongGia.substring(0, vt);
                modelPXK.addRow(new Object[]{
                    rs.getString("MAPXK"),
                    rs.getString("TENK"),
                    rs.getString("HOTEN"),
                    rs.getString("TENQH"),
                    rs.getString("NGAYXK"),
                    tongGia
                });
            }
            rs.close();
        } catch (Exception e) {
        }
    }

    private void fillTablePBH() {
        try {
            String sql = "select SOIMEI,MAMH,convert(varchar,NGAYHETHAN,103) as NGAYHETHAN,TRANGTHAI from PHIEUBAOHANH";
            ResultSet rs = JdbcHelper.query(sql);
            modelPBH.setRowCount(0);
            while (rs.next()) {
                modelPBH.addRow(new Object[]{
                    rs.getString("SOIMEI"),
                    rs.getString("MAMH"),
                    rs.getString("NGAYHETHAN"),
                    rs.getString("TRANGTHAI")
                });
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void fillTablePGC() {
        try {
            String sql = "select MAPGC,convert(varchar,NGAYGC,103) as NGAYGC,MANV,TENQH,CA  from \n"
                    + "PHIEUGIAOCA join QUAYHANG on PHIEUGIAOCA.MAQH=QUAYHANG.MAQH";
            ResultSet rs = JdbcHelper.query(sql);
            modelPGC.setRowCount(0);
            while (rs.next()) {
                modelPGC.addRow(new Object[]{
                    rs.getString("MAPGC"),
                    rs.getString("NGAYGC"),
                    rs.getString("MANV"),
                    rs.getString("TENQH"),
                    rs.getString("CA")
                });
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void fillTableHDBS() {
        try {
            String sql = "select MAHDBS,TENKH,TENK,HOTEN,convert(varchar,NGAYLAP,103) as NGAYLAP,TONGGIA from \n"
                    + "HOADONBANSI join KHACHHANG on HOADONBANSI.MAKH=KHACHHANG.MAKH\n"
                    + "			join KHO on HOADONBANSI.MAK=KHO.MAK\n"
                    + "			join NHANVIEN on HOADONBANSI.MANV=NHANVIEN.MANV";
            ResultSet rs = JdbcHelper.query(sql);
            modelHDBS.setRowCount(0);
            String tongGia;
            while (rs.next()) {
                tongGia = rs.getString("TONGGIA");
                int vt = tongGia.indexOf(".");
                tongGia = tongGia.substring(0, vt);
                modelHDBS.addRow(new Object[]{
                    rs.getString("MAHDBS"),
                    rs.getString("TENKH"),
                    rs.getString("TENK"),
                    rs.getString("HOTEN"),
                    rs.getString("NGAYLAP"),
                    tongGia
                });
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void fillTableHDBL() {
        try {
            String sql = "select MAHDBL,HOTEN, convert(varchar,NGAYLAPHD,103) as NGAYLAPHD,TONGGIA\n"
                    + "from HOADONBANLE join NHANVIEN on HOADONBANLE.MANV=NHANVIEN.MANV";
            ResultSet rs = JdbcHelper.query(sql);
            modelHDBL.setRowCount(0);
            String tongGia;
            while (rs.next()) {
                tongGia = rs.getString("TONGGIA");
                int vt = tongGia.indexOf(".");
                tongGia = tongGia.substring(0, vt);
                modelHDBL.addRow(new Object[]{
                    rs.getString("MAHDBL"),
                    rs.getString("HOTEN"),
                    rs.getString("NGAYLAPHD"),
                    tongGia
                });
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

        lblXemPhieu = new javax.swing.JLabel();
        pnlMenuXP = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        lblPNK = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lblPXK = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        lblPBH = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        lblPGC = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        lblPBHang = new javax.swing.JLabel();
        pnlParentCard = new javax.swing.JPanel();
        pnlPNK = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPNK = new javax.swing.JTable();
        pnlPXK = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblPXK = new javax.swing.JTable();
        pnlPBH = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblPBH = new javax.swing.JTable();
        pnlPGC = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblPGC = new javax.swing.JTable();
        pnlPBHang = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblHDBS = new javax.swing.JTable();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblHDBL = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(34, 116, 173));

        lblXemPhieu.setFont(new java.awt.Font("Baloo 2 ExtraBold", 0, 24)); // NOI18N
        lblXemPhieu.setForeground(new java.awt.Color(255, 255, 255));
        lblXemPhieu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblXemPhieu.setText("XEM PHIẾU");

        pnlMenuXP.setBackground(new java.awt.Color(34, 116, 173));
        pnlMenuXP.setLayout(new java.awt.GridLayout(5, 1, 10, 10));

        jPanel1.setBackground(new java.awt.Color(34, 116, 173));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.setLayout(new java.awt.GridLayout(1, 1));

        lblPNK.setFont(new java.awt.Font("Baloo Chettan 2 ExtraBold", 0, 16)); // NOI18N
        lblPNK.setForeground(new java.awt.Color(255, 255, 255));
        lblPNK.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPNK.setText("Phiếu nhập kho");
        lblPNK.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblPNKMouseClicked(evt);
            }
        });
        jPanel1.add(lblPNK);

        pnlMenuXP.add(jPanel1);

        jPanel2.setBackground(new java.awt.Color(34, 116, 173));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel2.setLayout(new java.awt.GridLayout(1, 1));

        lblPXK.setFont(new java.awt.Font("Baloo Chettan 2 ExtraBold", 0, 16)); // NOI18N
        lblPXK.setForeground(new java.awt.Color(255, 255, 255));
        lblPXK.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPXK.setText("Phiếu xuất kho");
        lblPXK.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblPXKMouseClicked(evt);
            }
        });
        jPanel2.add(lblPXK);

        pnlMenuXP.add(jPanel2);

        jPanel3.setBackground(new java.awt.Color(34, 116, 173));
        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel3.setLayout(new java.awt.GridLayout(1, 1));

        lblPBH.setFont(new java.awt.Font("Baloo Chettan 2 ExtraBold", 0, 16)); // NOI18N
        lblPBH.setForeground(new java.awt.Color(255, 255, 255));
        lblPBH.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPBH.setText("Phiếu bảo hành");
        lblPBH.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblPBHMouseClicked(evt);
            }
        });
        jPanel3.add(lblPBH);

        pnlMenuXP.add(jPanel3);

        jPanel4.setBackground(new java.awt.Color(34, 116, 173));
        jPanel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel4.setLayout(new java.awt.GridLayout(1, 1));

        lblPGC.setFont(new java.awt.Font("Baloo Chettan 2 ExtraBold", 0, 16)); // NOI18N
        lblPGC.setForeground(new java.awt.Color(255, 255, 255));
        lblPGC.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPGC.setText("Phiếu giao ca");
        lblPGC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblPGCMouseClicked(evt);
            }
        });
        jPanel4.add(lblPGC);

        pnlMenuXP.add(jPanel4);

        jPanel5.setBackground(new java.awt.Color(34, 116, 173));
        jPanel5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel5.setLayout(new java.awt.GridLayout(1, 1));

        lblPBHang.setFont(new java.awt.Font("Baloo Chettan 2 ExtraBold", 0, 16)); // NOI18N
        lblPBHang.setForeground(new java.awt.Color(255, 255, 255));
        lblPBHang.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPBHang.setText("Phiếu bán hàng");
        lblPBHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblPBHangMouseClicked(evt);
            }
        });
        jPanel5.add(lblPBHang);

        pnlMenuXP.add(jPanel5);

        pnlParentCard.setBackground(new java.awt.Color(34, 116, 173));
        pnlParentCard.setLayout(new java.awt.CardLayout());

        pnlPNK.setBackground(new java.awt.Color(34, 116, 173));

        tblPNK.setBackground(new java.awt.Color(34, 116, 173));
        tblPNK.setForeground(new java.awt.Color(255, 255, 255));
        tblPNK.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã phiếu nhập kho", "Mã nhà cung cấp", "Mã kho", "Mã nhân viên", "Ngày nhập", "Tổng giá"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblPNK.setGridColor(new java.awt.Color(255, 255, 255));
        tblPNK.setRowHeight(25);
        tblPNK.setSelectionBackground(new java.awt.Color(51, 51, 51));
        tblPNK.setSelectionForeground(new java.awt.Color(255, 255, 255));
        tblPNK.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblPNK.setShowGrid(true);
        jScrollPane1.setViewportView(tblPNK);

        javax.swing.GroupLayout pnlPNKLayout = new javax.swing.GroupLayout(pnlPNK);
        pnlPNK.setLayout(pnlPNKLayout);
        pnlPNKLayout.setHorizontalGroup(
            pnlPNKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPNKLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1027, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlPNKLayout.setVerticalGroup(
            pnlPNKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPNKLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 562, Short.MAX_VALUE))
        );

        pnlParentCard.add(pnlPNK, "card6");

        pnlPXK.setBackground(new java.awt.Color(34, 116, 173));

        tblPXK.setBackground(new java.awt.Color(34, 116, 173));
        tblPXK.setForeground(new java.awt.Color(255, 255, 255));
        tblPXK.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã phiếu xuất kho", "Mã kho", "Mã nhân viên", "Mã quầy hàng", "Ngày xuất kho", "Tổng giá"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblPXK.setGridColor(new java.awt.Color(255, 255, 255));
        tblPXK.setRowHeight(25);
        tblPXK.setSelectionBackground(new java.awt.Color(51, 51, 51));
        tblPXK.setSelectionForeground(new java.awt.Color(255, 255, 255));
        tblPXK.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblPXK.setShowGrid(true);
        jScrollPane2.setViewportView(tblPXK);

        javax.swing.GroupLayout pnlPXKLayout = new javax.swing.GroupLayout(pnlPXK);
        pnlPXK.setLayout(pnlPXKLayout);
        pnlPXKLayout.setHorizontalGroup(
            pnlPXKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPXKLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1027, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlPXKLayout.setVerticalGroup(
            pnlPXKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPXKLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 562, Short.MAX_VALUE))
        );

        pnlParentCard.add(pnlPXK, "card5");

        pnlPBH.setBackground(new java.awt.Color(34, 116, 173));

        tblPBH.setBackground(new java.awt.Color(34, 116, 173));
        tblPBH.setForeground(new java.awt.Color(255, 255, 255));
        tblPBH.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Số IMEI", "Mã mặt hàng", "Ngày hết hạn", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblPBH.setGridColor(new java.awt.Color(255, 255, 255));
        tblPBH.setRowHeight(25);
        tblPBH.setSelectionBackground(new java.awt.Color(51, 51, 51));
        tblPBH.setSelectionForeground(new java.awt.Color(255, 255, 255));
        tblPBH.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblPBH.setShowGrid(true);
        jScrollPane3.setViewportView(tblPBH);

        javax.swing.GroupLayout pnlPBHLayout = new javax.swing.GroupLayout(pnlPBH);
        pnlPBH.setLayout(pnlPBHLayout);
        pnlPBHLayout.setHorizontalGroup(
            pnlPBHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPBHLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 1027, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlPBHLayout.setVerticalGroup(
            pnlPBHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPBHLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 562, Short.MAX_VALUE))
        );

        pnlParentCard.add(pnlPBH, "card4");

        pnlPGC.setBackground(new java.awt.Color(34, 116, 173));

        tblPGC.setBackground(new java.awt.Color(34, 116, 173));
        tblPGC.setForeground(new java.awt.Color(255, 255, 255));
        tblPGC.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã phiếu giao ca", "Ngày giao ca", "Mã nhân viên", "Mã quầy hàng", "Ca"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblPGC.setGridColor(new java.awt.Color(255, 255, 255));
        tblPGC.setRowHeight(25);
        tblPGC.setSelectionBackground(new java.awt.Color(51, 51, 51));
        tblPGC.setSelectionForeground(new java.awt.Color(255, 255, 255));
        tblPGC.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblPGC.setShowGrid(true);
        jScrollPane4.setViewportView(tblPGC);

        javax.swing.GroupLayout pnlPGCLayout = new javax.swing.GroupLayout(pnlPGC);
        pnlPGC.setLayout(pnlPGCLayout);
        pnlPGCLayout.setHorizontalGroup(
            pnlPGCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPGCLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 1027, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlPGCLayout.setVerticalGroup(
            pnlPGCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPGCLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 562, Short.MAX_VALUE))
        );

        pnlParentCard.add(pnlPGC, "card3");

        pnlPBHang.setBackground(new java.awt.Color(34, 116, 173));

        tblHDBS.setBackground(new java.awt.Color(34, 116, 173));
        tblHDBS.setForeground(new java.awt.Color(255, 255, 255));
        tblHDBS.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã HĐ bán sỉ", "Mã khách hàng", "Mã kho", "Mã nhân viên", "Ngày lập", "Tổng giá"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblHDBS.setGridColor(new java.awt.Color(255, 255, 255));
        tblHDBS.setRowHeight(25);
        tblHDBS.setSelectionBackground(new java.awt.Color(51, 51, 51));
        tblHDBS.setSelectionForeground(new java.awt.Color(255, 255, 255));
        tblHDBS.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblHDBS.setShowGrid(true);
        jScrollPane5.setViewportView(tblHDBS);

        tblHDBL.setBackground(new java.awt.Color(34, 116, 173));
        tblHDBL.setForeground(new java.awt.Color(255, 255, 255));
        tblHDBL.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã HĐ bán lẻ", "Mã nhân viên", "Ngày lập", "Tổng giá"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblHDBL.setGridColor(new java.awt.Color(255, 255, 255));
        tblHDBL.setRowHeight(25);
        tblHDBL.setSelectionBackground(new java.awt.Color(51, 51, 51));
        tblHDBL.setSelectionForeground(new java.awt.Color(255, 255, 255));
        tblHDBL.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblHDBL.setShowGrid(true);
        jScrollPane6.setViewportView(tblHDBL);

        jLabel1.setFont(new java.awt.Font("Baloo 2 ExtraBold", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Hóa đơn bán sỉ");

        jLabel2.setFont(new java.awt.Font("Baloo 2 ExtraBold", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Hóa đơn bán lẻ");

        javax.swing.GroupLayout pnlPBHangLayout = new javax.swing.GroupLayout(pnlPBHang);
        pnlPBHang.setLayout(pnlPBHangLayout);
        pnlPBHangLayout.setHorizontalGroup(
            pnlPBHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPBHangLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlPBHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 605, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlPBHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 422, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        pnlPBHangLayout.setVerticalGroup(
            pnlPBHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlPBHangLayout.createSequentialGroup()
                .addGroup(pnlPBHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlPBHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 523, Short.MAX_VALUE)
                    .addComponent(jScrollPane6)))
        );

        pnlParentCard.add(pnlPBHang, "card2");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlMenuXP, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lblXemPhieu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addComponent(pnlParentCard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(lblXemPhieu)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlMenuXP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlParentCard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void lblPNKMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblPNKMouseClicked
        // TODO add your handling code here:
        try {
            lblPNK.setForeground(Color.orange);
            lblPXK.setForeground(Color.white);
            lblPBH.setForeground(Color.white);
            lblPGC.setForeground(Color.white);
            lblPBHang.setForeground(Color.white);
            pnlParentCard.removeAll();
            pnlParentCard.add(pnlPNK);
            pnlParentCard.validate();
            pnlParentCard.repaint();
            //FILL TABLE
            this.fillTablePNK();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_lblPNKMouseClicked

    private void lblPXKMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblPXKMouseClicked
        // TODO add your handling code here:
        try {
            lblPXK.setForeground(Color.orange);
            lblPNK.setForeground(Color.white);
            lblPBH.setForeground(Color.white);
            lblPGC.setForeground(Color.white);
            lblPBHang.setForeground(Color.white);
            pnlParentCard.removeAll();
            pnlParentCard.add(pnlPXK);
            pnlParentCard.validate();
            pnlParentCard.repaint();
            //fill table
            this.fillTablePXK();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_lblPXKMouseClicked

    private void lblPBHMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblPBHMouseClicked
        // TODO add your handling code here:
        try {
            lblPBH.setForeground(Color.orange);
            lblPXK.setForeground(Color.white);
            lblPNK.setForeground(Color.white);
            lblPGC.setForeground(Color.white);
            lblPBHang.setForeground(Color.white);
            pnlParentCard.removeAll();
            pnlParentCard.add(pnlPBH);
            pnlParentCard.validate();
            pnlParentCard.repaint();
            //fill
            this.fillTablePBH();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_lblPBHMouseClicked

    private void lblPGCMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblPGCMouseClicked
        // TODO add your handling code here:
        try {
            lblPGC.setForeground(Color.orange);
            lblPXK.setForeground(Color.white);
            lblPBH.setForeground(Color.white);
            lblPNK.setForeground(Color.white);
            lblPBHang.setForeground(Color.white);
            pnlParentCard.removeAll();
            pnlParentCard.add(pnlPGC);
            pnlParentCard.validate();
            pnlParentCard.repaint();
            //filllll
            this.fillTablePGC();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_lblPGCMouseClicked

    private void lblPBHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblPBHangMouseClicked
        // TODO add your handling code here:
        try {
            lblPBHang.setForeground(Color.orange);
            lblPXK.setForeground(Color.white);
            lblPBH.setForeground(Color.white);
            lblPGC.setForeground(Color.white);
            lblPNK.setForeground(Color.white);
            pnlParentCard.removeAll();
            pnlParentCard.add(pnlPBHang);
            pnlParentCard.validate();
            pnlParentCard.repaint();
            //fill
            this.fillTableHDBS();
            this.fillTableHDBL();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_lblPBHangMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JLabel lblPBH;
    private javax.swing.JLabel lblPBHang;
    private javax.swing.JLabel lblPGC;
    private javax.swing.JLabel lblPNK;
    private javax.swing.JLabel lblPXK;
    private javax.swing.JLabel lblXemPhieu;
    private javax.swing.JPanel pnlMenuXP;
    private javax.swing.JPanel pnlPBH;
    private javax.swing.JPanel pnlPBHang;
    private javax.swing.JPanel pnlPGC;
    private javax.swing.JPanel pnlPNK;
    private javax.swing.JPanel pnlPXK;
    private javax.swing.JPanel pnlParentCard;
    private javax.swing.JTable tblHDBL;
    private javax.swing.JTable tblHDBS;
    private javax.swing.JTable tblPBH;
    private javax.swing.JTable tblPGC;
    private javax.swing.JTable tblPNK;
    private javax.swing.JTable tblPXK;
    // End of variables declaration//GEN-END:variables
}
