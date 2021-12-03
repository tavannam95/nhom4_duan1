/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mobilez.ui;

import com.mobilez.models.NhanVien;
import com.mobilez.utils.JdbcHelper;
import java.awt.Color;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

/**
 *
 * @author uhtku
 */
public class FrmThongKe extends javax.swing.JPanel {

    /**
     * Creates new form FrmThongKe
     */
    DefaultTableModel modelDS;
    DefaultTableModel modelSPBC;
    DefaultTableModel modelSPTK;
    DefaultTableModel modelSPHH;
    DefaultTableModel modelDTBL;
    DefaultTableModel modelDTBS;

    public FrmThongKe() {
        initComponents();
        this.getAllModel();
        this.fillCboNam(cboNamDS);
        this.fillCboThang(cboThangDS, cboNamDS);
        lblDoanhSo.setForeground(Color.orange);

    }

    ///////CODE DOANH THU BAN SI
    private int getTongSLBanSi() {
        try {
            int sl = 0;
            String sql = "select sum(SOLUONG) from CHITIETHOADONBANSI \n"
                    + "join HOADONBANSI on HOADONBANSI.MAHDBS=CHITIETHOADONBANSI.MAHDBS \n"
                    + "where year(NGAYLAP) = ? and month(NGAYLAP) = ?";
            ResultSet rs = JdbcHelper.query(sql, cboNamDTBS.getSelectedItem() + "", cboThangDTBS.getSelectedItem() + "");
            while (rs.next()) {
                sl = rs.getInt(1);
            }
            rs.close();
            return sl;
        } catch (Exception e) {
            return 0;
        }
    }

    private int getTongGiaBanSi() {
        try {
            int sl = 0;
            String sql = "select sum(TONGGIA) from HOADONBANSI where year(NGAYLAP) = ? and month(NGAYLAP) = ?";
            ResultSet rs = JdbcHelper.query(sql, cboNamDTBS.getSelectedItem() + "", cboThangDTBS.getSelectedItem() + "");
            while (rs.next()) {
                sl = rs.getInt(1);
            }
            rs.close();
            return sl;
        } catch (Exception e) {
            return 0;
        }
    }

    /////CODE DOANH THU BÁN LẺ
    private int getTongSLBanLe() {
        try {
            int sl = 0;
            String sql = "select sum(SOLUONG) from CHITIETHOADONBANLE \n"
                    + "join HOADONBANLE on HOADONBANLE.MAHDBL=CHITIETHOADONBANLE.MAHDBL \n"
                    + "where year(NGAYLAPHD) = ? and month(NGAYLAPHD) = ?";
            ResultSet rs = JdbcHelper.query(sql, cboNamDTBL.getSelectedItem() + "", cboThangDTBL.getSelectedItem() + "");
            while (rs.next()) {
                sl = rs.getInt(1);
            }
            rs.close();
            return sl;
        } catch (Exception e) {
            return 0;
        }
    }

    private int getTongGiaBanLe() {
        try {
            int sl = 0;
            String sql = "select sum(TONGGIA) from HOADONBANLE where year(NGAYLAPHD) = ? and month(NGAYLAPHD) = ?";
            ResultSet rs = JdbcHelper.query(sql, cboNamDTBL.getSelectedItem() + "", cboThangDTBL.getSelectedItem() + "");
            while (rs.next()) {
                sl = rs.getInt(1);
            }
            rs.close();
            return sl;
        } catch (Exception e) {
            return 0;
        }
    }

    ////CODE SAN PHAM HET HANG
    private void fillTableSPHH() {
        try {
            String sql = "select MAMH,TENMH,RAM,DUNGLUONG,MAUSAC,SOLUONG\n"
                    + "from MATHANG where SOLUONG = 0 and TRANGTHAI = 1";
            modelSPHH.setRowCount(0);
            ResultSet rs = JdbcHelper.query(sql);
            while (rs.next()) {
                modelSPHH.addRow(new Object[]{
                    rs.getString("MAMH"),
                    rs.getString("TENMH"),
                    rs.getString("RAM") + "GB",
                    rs.getString("DUNGLUONG"),
                    rs.getString("MAUSAC"),
                    rs.getString("SOLUONG")
                });
            }
            rs.close();
        } catch (Exception e) {
        }
    }
//////CODE SAN PHAM TON KHO

    private void fillTableSPTK() {
        try {
            String sql = "select MAMH,TENMH,RAM,DUNGLUONG,MAUSAC,SOLUONG\n"
                    + "from MATHANG where MAMH not in \n"
                    + "(select distinct MAMH from CHITIETHOADONBANLE \n"
                    + "join HOADONBANLE on CHITIETHOADONBANLE.MAHDBL = HOADONBANLE.MAHDBL\n"
                    + "where year(NGAYLAPHD)=? and month(NGAYLAPHD) = ?)\n"
                    + "and TRANGTHAI = 1 and SOLUONG > 0";
            modelSPTK.setRowCount(0);
            ResultSet rs = JdbcHelper.query(sql, cboNamSPTK.getSelectedItem() + "", cboThangSPTK.getSelectedItem() + "");
            while (rs.next()) {
                modelSPTK.addRow(new Object[]{
                    rs.getString("MAMH"),
                    rs.getString("TENMH"),
                    rs.getString("RAM") + "GB",
                    rs.getString("DUNGLUONG"),
                    rs.getString("MAUSAC"),
                    rs.getString("SOLUONG")
                });
            }
            rs.close();
        } catch (Exception e) {
        }
    }

    ///////CODE SAN PHAM BAN CHAY
    private void fillTableSPBC() {
        try {
            String sql = "select top 20 MATHANG.MAMH,TENMH,sum(CHITIETHOADONBANLE.SOLUONG) as TONGSOLUONG, sum(TONGGIA) as TONGTONGGIA\n"
                    + "from MATHANG join CHITIETHOADONBANLE on MATHANG.MAMH=CHITIETHOADONBANLE.MAMH\n"
                    + "			 join HOADONBANLE on CHITIETHOADONBANLE.MAHDBL=HOADONBANLE.MAHDBL\n"
                    + "where year(NGAYLAPHD) = ? and month(NGAYLAPHD) = ?\n"
                    + "group by MATHANG.MAMH,TENMH"
                    + " order by TONGSOLUONG desc";
            modelSPBC.setRowCount(0);
            ResultSet rs = JdbcHelper.query(sql, cboNamSPBC.getSelectedItem() + "", cboThangSPBC.getSelectedItem() + "");
            while (rs.next()) {
                double tongGia = rs.getDouble("TONGTONGGIA");
                int soLuong = rs.getInt("TONGSOLUONG");
                modelSPBC.addRow(new Object[]{
                    rs.getString("MAMH"),
                    rs.getString("TENMH"),
                    soLuong,
                    tongGia});
            }
            rs.close();

        } catch (Exception e) {
//            e.printStackTrace();
        }
    }

    ////// CODE OF THONG KE DOANH SO
    private void fillTableSortByQuantity() {
        try {
            String sql = "select NHANVIEN.MANV,HOTEN, sum(TONGGIA) as TONGTONGGIA, sum(SOLUONG) as TONGSOLUONG\n"
                    + "from NHANVIEN join HOADONBANLE on NHANVIEN.MANV=HOADONBANLE.MANV\n"
                    + "			  join CHITIETHOADONBANLE on HOADONBANLE.MAHDBL=CHITIETHOADONBANLE.MAHDBL\n"
                    + "WHERE YEAR(NGAYLAPHD) = ? and MONTH(NGAYLAPHD) = ?\n"
                    + "group by NHANVIEN.MANV, HOTEN\n"
                    + "ORDER BY TONGSOLUONG DESC";
            modelDS.setRowCount(0);
            ResultSet rs = JdbcHelper.query(sql, cboNamDS.getSelectedItem() + "", cboThangDS.getSelectedItem() + "");
            while (rs.next()) {
                double tongGia = rs.getDouble("TONGTONGGIA");
                int soLuong = rs.getInt("TONGSOLUONG");
                modelDS.addRow(new Object[]{
                    rs.getString("MANV"),
                    rs.getString("HOTEN"),
                    tongGia,
                    soLuong});
            }
            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void fillTableSortByPrice() {
        try {
            String sql = "select NHANVIEN.MANV,HOTEN, sum(TONGGIA) as TONGTONGGIA, sum(SOLUONG) as TONGSOLUONG\n"
                    + "from NHANVIEN join HOADONBANLE on NHANVIEN.MANV=HOADONBANLE.MANV\n"
                    + "			  join CHITIETHOADONBANLE on HOADONBANLE.MAHDBL=CHITIETHOADONBANLE.MAHDBL\n"
                    + "WHERE YEAR(NGAYLAPHD) = ? and MONTH(NGAYLAPHD) = ?\n"
                    + "group by NHANVIEN.MANV, HOTEN\n"
                    + "ORDER BY TONGTONGGIA DESC";
            modelDS.setRowCount(0);
            ResultSet rs = JdbcHelper.query(sql, cboNamDS.getSelectedItem() + "", cboThangDS.getSelectedItem() + "");
            while (rs.next()) {
                double tongGia = rs.getDouble("TONGTONGGIA");
                int soLuong = rs.getInt("TONGSOLUONG");
                modelDS.addRow(new Object[]{
                    rs.getString("MANV"),
                    rs.getString("HOTEN"),
                    tongGia,
                    soLuong});
            }
            rs.close();

        } catch (Exception e) {
//            e.printStackTrace();
        }
    }

    private void fillCboNam(JComboBox cboYear) {
        try {
            String sql = "select distinct year(NGAYLAPHD) as YEAR from HOADONBANLE";
            ResultSet rs = JdbcHelper.query(sql);
            cboYear.removeAllItems();
            while (rs.next()) {
                cboYear.addItem(rs.getString(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void fillCboThang(JComboBox cboMonth, JComboBox cboYear) {
        try {
            String sql = "select distinct month(NGAYLAPHD) as MONTH from HOADONBANLE"
                    + " where YEAR(NGAYLAPHD)= ?";
            cboMonth.removeAllItems();
            ResultSet rs = JdbcHelper.query(sql, cboYear.getSelectedItem() + "");
            while (rs.next()) {
                cboMonth.addItem(rs.getString(1));
            }
        } catch (Exception e) {
//            e.printStackTrace();
        }
    }

    private void fillCboNamBS(JComboBox cboYear) {
        try {
            String sql = "select distinct year(NGAYLAP) as YEAR from HOADONBANSI";
            ResultSet rs = JdbcHelper.query(sql);
            cboYear.removeAllItems();
            while (rs.next()) {
                cboYear.addItem(rs.getString(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void fillCboThangBS(JComboBox cboMonth, JComboBox cboYear) {
        try {
            String sql = "select distinct MONTH(NGAYLAP) as YEAR from HOADONBANSI"
                    + " where YEAR(NGAYLAP)= ?";
            cboMonth.removeAllItems();
            ResultSet rs = JdbcHelper.query(sql, cboYear.getSelectedItem() + "");
            while (rs.next()) {
                cboMonth.addItem(rs.getString(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getAllModel() {
        try {
            modelDS = (DefaultTableModel) tblDS.getModel();
            modelDTBS = (DefaultTableModel) tblDTBS.getModel();
            modelDTBL = (DefaultTableModel) tblDTBL.getModel();
            modelSPBC = (DefaultTableModel) tblSPBC.getModel();
            modelSPHH = (DefaultTableModel) tblSPHH.getModel();
            modelSPTK = (DefaultTableModel) tblSPTK.getModel();

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

        pnlMenuXP = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        lblDoanhSo = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lblSPBanChay = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        lblSPTonKho = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        lblHetHang = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        lblDoanhThu = new javax.swing.JLabel();
        pnlParentCard = new javax.swing.JPanel();
        pnlDoanhSo = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDS = new javax.swing.JTable();
        lblTitle = new javax.swing.JLabel();
        cboThangDS = new javax.swing.JComboBox<>();
        cboNamDS = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cboSort = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        pnlSPBanChay = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblSPBC = new javax.swing.JTable();
        lblTitle1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cboNamSPBC = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        cboThangSPBC = new javax.swing.JComboBox<>();
        pnlSPTonKho = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblSPTK = new javax.swing.JTable();
        lblTitle2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cboNamSPTK = new javax.swing.JComboBox<>();
        cboThangSPTK = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        pnlSPHetHang = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblSPHH = new javax.swing.JTable();
        lblTitle4 = new javax.swing.JLabel();
        pnlDoanhThu = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblDTBL = new javax.swing.JTable();
        lblTitle6 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tblDTBS = new javax.swing.JTable();
        lblTitle7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        cboNamDTBL = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        cboThangDTBL = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        cboNamDTBS = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        cboThangDTBS = new javax.swing.JComboBox<>();

        setBackground(new java.awt.Color(85, 159, 198));

        pnlMenuXP.setBackground(new java.awt.Color(34, 116, 173));
        pnlMenuXP.setLayout(new java.awt.GridLayout(5, 1, 10, 10));

        jPanel1.setBackground(new java.awt.Color(34, 116, 173));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.setLayout(new java.awt.GridLayout(1, 1));

        lblDoanhSo.setFont(new java.awt.Font("Baloo Chettan 2 ExtraBold", 0, 16)); // NOI18N
        lblDoanhSo.setForeground(new java.awt.Color(255, 255, 255));
        lblDoanhSo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDoanhSo.setText("Doanh số");
        lblDoanhSo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblDoanhSoMouseClicked(evt);
            }
        });
        jPanel1.add(lblDoanhSo);

        pnlMenuXP.add(jPanel1);

        jPanel2.setBackground(new java.awt.Color(34, 116, 173));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel2.setLayout(new java.awt.GridLayout(1, 1));

        lblSPBanChay.setFont(new java.awt.Font("Baloo Chettan 2 ExtraBold", 0, 16)); // NOI18N
        lblSPBanChay.setForeground(new java.awt.Color(255, 255, 255));
        lblSPBanChay.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSPBanChay.setText("SP bán chạy");
        lblSPBanChay.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblSPBanChayMouseClicked(evt);
            }
        });
        jPanel2.add(lblSPBanChay);

        pnlMenuXP.add(jPanel2);

        jPanel3.setBackground(new java.awt.Color(34, 116, 173));
        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel3.setLayout(new java.awt.GridLayout(1, 1));

        lblSPTonKho.setFont(new java.awt.Font("Baloo Chettan 2 ExtraBold", 0, 16)); // NOI18N
        lblSPTonKho.setForeground(new java.awt.Color(255, 255, 255));
        lblSPTonKho.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSPTonKho.setText("SP tồn kho");
        lblSPTonKho.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblSPTonKhoMouseClicked(evt);
            }
        });
        jPanel3.add(lblSPTonKho);

        pnlMenuXP.add(jPanel3);

        jPanel4.setBackground(new java.awt.Color(34, 116, 173));
        jPanel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel4.setLayout(new java.awt.GridLayout(1, 1));

        lblHetHang.setFont(new java.awt.Font("Baloo Chettan 2 ExtraBold", 0, 16)); // NOI18N
        lblHetHang.setForeground(new java.awt.Color(255, 255, 255));
        lblHetHang.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHetHang.setText("SP hết hàng");
        lblHetHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblHetHangMouseClicked(evt);
            }
        });
        jPanel4.add(lblHetHang);

        pnlMenuXP.add(jPanel4);

        jPanel6.setBackground(new java.awt.Color(34, 116, 173));
        jPanel6.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel6.setLayout(new java.awt.GridLayout(1, 1));

        lblDoanhThu.setFont(new java.awt.Font("Baloo Chettan 2 ExtraBold", 0, 16)); // NOI18N
        lblDoanhThu.setForeground(new java.awt.Color(255, 255, 255));
        lblDoanhThu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDoanhThu.setText("Doanh thu");
        lblDoanhThu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblDoanhThuMouseClicked(evt);
            }
        });
        jPanel6.add(lblDoanhThu);

        pnlMenuXP.add(jPanel6);

        pnlParentCard.setBackground(new java.awt.Color(34, 116, 173));
        pnlParentCard.setLayout(new java.awt.CardLayout());

        pnlDoanhSo.setBackground(new java.awt.Color(34, 116, 173));

        tblDS.setBackground(new java.awt.Color(34, 116, 173));
        tblDS.setForeground(new java.awt.Color(255, 255, 255));
        tblDS.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã nhân viên", "Tên nhân viên", "Tổng giá", "Tổng số lượng"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblDS.setGridColor(new java.awt.Color(255, 255, 255));
        tblDS.setRowHeight(25);
        tblDS.setSelectionBackground(new java.awt.Color(51, 51, 51));
        tblDS.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblDS.setShowGrid(true);
        jScrollPane1.setViewportView(tblDS);

        lblTitle.setFont(new java.awt.Font("Baloo 2 ExtraBold", 0, 24)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(255, 255, 255));
        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitle.setText("DOANH SỐ");

        cboThangDS.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboThangDSItemStateChanged(evt);
            }
        });

        cboNamDS.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboNamDSItemStateChanged(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Baloo Chettan 2", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Tháng");

        jLabel3.setFont(new java.awt.Font("Baloo Chettan 2", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Năm");

        cboSort.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tổng giá", "Tổng số lượng" }));
        cboSort.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboSortItemStateChanged(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Baloo Chettan 2", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Sắp xếp theo");

        javax.swing.GroupLayout pnlDoanhSoLayout = new javax.swing.GroupLayout(pnlDoanhSo);
        pnlDoanhSo.setLayout(pnlDoanhSoLayout);
        pnlDoanhSoLayout.setHorizontalGroup(
            pnlDoanhSoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDoanhSoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDoanhSoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1125, Short.MAX_VALUE)
                    .addGroup(pnlDoanhSoLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(cboSort, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboNamDS, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboThangDS, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(pnlDoanhSoLayout.createSequentialGroup()
                .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 986, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pnlDoanhSoLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {cboNamDS, cboSort, cboThangDS});

        pnlDoanhSoLayout.setVerticalGroup(
            pnlDoanhSoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDoanhSoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlDoanhSoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlDoanhSoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cboThangDS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2))
                    .addGroup(pnlDoanhSoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cboNamDS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3))
                    .addGroup(pnlDoanhSoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cboSort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 584, Short.MAX_VALUE))
        );

        pnlParentCard.add(pnlDoanhSo, "card6");

        pnlSPBanChay.setBackground(new java.awt.Color(34, 116, 173));

        tblSPBC.setBackground(new java.awt.Color(34, 116, 173));
        tblSPBC.setForeground(new java.awt.Color(255, 255, 255));
        tblSPBC.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã mặt hàng", "Tên mặt hàng", "Tổng số lượng", "Tổng giá"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSPBC.setGridColor(new java.awt.Color(255, 255, 255));
        tblSPBC.setRowHeight(25);
        tblSPBC.setSelectionBackground(new java.awt.Color(51, 51, 51));
        tblSPBC.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblSPBC.setShowGrid(true);
        jScrollPane2.setViewportView(tblSPBC);

        lblTitle1.setFont(new java.awt.Font("Baloo 2 ExtraBold", 0, 24)); // NOI18N
        lblTitle1.setForeground(new java.awt.Color(255, 255, 255));
        lblTitle1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitle1.setText("SẢN PHẨM BÁN CHẠY");

        jLabel5.setFont(new java.awt.Font("Baloo Chettan 2", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Năm");

        cboNamSPBC.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboNamSPBCItemStateChanged(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Baloo Chettan 2", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Tháng");

        cboThangSPBC.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboThangSPBCItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout pnlSPBanChayLayout = new javax.swing.GroupLayout(pnlSPBanChay);
        pnlSPBanChay.setLayout(pnlSPBanChayLayout);
        pnlSPBanChayLayout.setHorizontalGroup(
            pnlSPBanChayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSPBanChayLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlSPBanChayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1125, Short.MAX_VALUE)
                    .addComponent(lblTitle1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlSPBanChayLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboNamSPBC, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboThangSPBC, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlSPBanChayLayout.setVerticalGroup(
            pnlSPBanChayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlSPBanChayLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitle1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlSPBanChayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboNamSPBC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(cboThangSPBC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 584, Short.MAX_VALUE))
        );

        pnlParentCard.add(pnlSPBanChay, "card5");

        pnlSPTonKho.setBackground(new java.awt.Color(34, 116, 173));

        tblSPTK.setBackground(new java.awt.Color(34, 116, 173));
        tblSPTK.setForeground(new java.awt.Color(255, 255, 255));
        tblSPTK.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã mặt hàng", "Tên mặt hàng", "RAM", "Dung lượng", "Màu sắc", "Số lượng"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSPTK.setGridColor(new java.awt.Color(255, 255, 255));
        tblSPTK.setRowHeight(25);
        tblSPTK.setSelectionBackground(new java.awt.Color(51, 51, 51));
        tblSPTK.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblSPTK.setShowGrid(true);
        jScrollPane3.setViewportView(tblSPTK);

        lblTitle2.setFont(new java.awt.Font("Baloo 2 ExtraBold", 0, 24)); // NOI18N
        lblTitle2.setForeground(new java.awt.Color(255, 255, 255));
        lblTitle2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitle2.setText("SẢN PHẨM TỒN KHO");

        jLabel7.setFont(new java.awt.Font("Baloo Chettan 2", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Năm");

        cboNamSPTK.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboNamSPTKItemStateChanged(evt);
            }
        });

        cboThangSPTK.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboThangSPTKItemStateChanged(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Baloo Chettan 2", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Tháng");

        javax.swing.GroupLayout pnlSPTonKhoLayout = new javax.swing.GroupLayout(pnlSPTonKho);
        pnlSPTonKho.setLayout(pnlSPTonKhoLayout);
        pnlSPTonKhoLayout.setHorizontalGroup(
            pnlSPTonKhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSPTonKhoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlSPTonKhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 1125, Short.MAX_VALUE)
                    .addComponent(lblTitle2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlSPTonKhoLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboNamSPTK, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboThangSPTK, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlSPTonKhoLayout.setVerticalGroup(
            pnlSPTonKhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlSPTonKhoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitle2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlSPTonKhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboNamSPTK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(cboThangSPTK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 584, Short.MAX_VALUE))
        );

        pnlParentCard.add(pnlSPTonKho, "card4");

        pnlSPHetHang.setBackground(new java.awt.Color(34, 116, 173));

        tblSPHH.setBackground(new java.awt.Color(34, 116, 173));
        tblSPHH.setForeground(new java.awt.Color(255, 255, 255));
        tblSPHH.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã mặt hàng", "Tên mặt hàng", "RAM", "Dung lượng", "Màu sắc", "Số lượng"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSPHH.setGridColor(new java.awt.Color(255, 255, 255));
        tblSPHH.setRowHeight(25);
        tblSPHH.setSelectionBackground(new java.awt.Color(51, 51, 51));
        tblSPHH.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblSPHH.setShowGrid(true);
        jScrollPane4.setViewportView(tblSPHH);

        lblTitle4.setFont(new java.awt.Font("Baloo 2 ExtraBold", 0, 24)); // NOI18N
        lblTitle4.setForeground(new java.awt.Color(255, 255, 255));
        lblTitle4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitle4.setText("SẢN PHẨM HẾT HÀNG");

        javax.swing.GroupLayout pnlSPHetHangLayout = new javax.swing.GroupLayout(pnlSPHetHang);
        pnlSPHetHang.setLayout(pnlSPHetHangLayout);
        pnlSPHetHangLayout.setHorizontalGroup(
            pnlSPHetHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSPHetHangLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlSPHetHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 1125, Short.MAX_VALUE)
                    .addComponent(lblTitle4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlSPHetHangLayout.setVerticalGroup(
            pnlSPHetHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlSPHetHangLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitle4, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 606, Short.MAX_VALUE))
        );

        pnlParentCard.add(pnlSPHetHang, "card3");

        pnlDoanhThu.setBackground(new java.awt.Color(34, 116, 173));

        tblDTBL.setBackground(new java.awt.Color(34, 116, 173));
        tblDTBL.setForeground(new java.awt.Color(255, 255, 255));
        tblDTBL.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Số lượng bán được", "Tổng tiền bán được"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblDTBL.setGridColor(new java.awt.Color(255, 255, 255));
        tblDTBL.setRowHeight(25);
        tblDTBL.setSelectionBackground(new java.awt.Color(51, 51, 51));
        tblDTBL.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblDTBL.setShowGrid(true);
        jScrollPane6.setViewportView(tblDTBL);

        lblTitle6.setFont(new java.awt.Font("Baloo 2 ExtraBold", 0, 24)); // NOI18N
        lblTitle6.setForeground(new java.awt.Color(255, 255, 255));
        lblTitle6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitle6.setText("DOANH THU BÁN LẺ");

        tblDTBS.setBackground(new java.awt.Color(34, 116, 173));
        tblDTBS.setForeground(new java.awt.Color(255, 255, 255));
        tblDTBS.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Số lượng bán được", "Tổng tiền bán đc"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblDTBS.setGridColor(new java.awt.Color(255, 255, 255));
        tblDTBS.setRowHeight(25);
        tblDTBS.setSelectionBackground(new java.awt.Color(51, 51, 51));
        tblDTBS.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblDTBS.setShowGrid(true);
        jScrollPane7.setViewportView(tblDTBS);

        lblTitle7.setFont(new java.awt.Font("Baloo 2 ExtraBold", 0, 24)); // NOI18N
        lblTitle7.setForeground(new java.awt.Color(255, 255, 255));
        lblTitle7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitle7.setText("DOANH THU BÁN SỈ");

        jLabel9.setFont(new java.awt.Font("Baloo Chettan 2", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Năm");

        cboNamDTBL.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboNamDTBLItemStateChanged(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Baloo Chettan 2", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Tháng");

        cboThangDTBL.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboThangDTBLItemStateChanged(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Baloo Chettan 2", 0, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Năm");

        cboNamDTBS.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboNamDTBSItemStateChanged(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Baloo Chettan 2", 0, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Tháng");

        cboThangDTBS.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboThangDTBSItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout pnlDoanhThuLayout = new javax.swing.GroupLayout(pnlDoanhThu);
        pnlDoanhThu.setLayout(pnlDoanhThuLayout);
        pnlDoanhThuLayout.setHorizontalGroup(
            pnlDoanhThuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDoanhThuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDoanhThuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlDoanhThuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(lblTitle6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 558, Short.MAX_VALUE))
                    .addGroup(pnlDoanhThuLayout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboNamDTBL, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboThangDTBL, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlDoanhThuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTitle7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlDoanhThuLayout.createSequentialGroup()
                        .addGroup(pnlDoanhThuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlDoanhThuLayout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cboNamDTBS, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cboThangDTBS, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 558, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 3, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlDoanhThuLayout.setVerticalGroup(
            pnlDoanhThuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDoanhThuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDoanhThuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTitle6, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTitle7, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlDoanhThuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboNamDTBL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(cboThangDTBL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(cboNamDTBS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(cboThangDTBS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlDoanhThuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 578, Short.MAX_VALUE)
                    .addComponent(jScrollPane7)))
        );

        pnlParentCard.add(pnlDoanhThu, "card2");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlMenuXP, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlParentCard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlParentCard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlMenuXP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void lblDoanhSoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDoanhSoMouseClicked
        // TODO add your handling code here:
        try {
            lblDoanhSo.setForeground(Color.orange);
            lblSPBanChay.setForeground(Color.white);
            lblSPTonKho.setForeground(Color.white);
            lblHetHang.setForeground(Color.white);
            lblDoanhThu.setForeground(Color.white);
            pnlParentCard.removeAll();
            pnlParentCard.add(pnlDoanhSo);
            pnlParentCard.validate();
            pnlParentCard.repaint();
            //FILL TABLE
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_lblDoanhSoMouseClicked

    private void lblSPBanChayMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSPBanChayMouseClicked
        // TODO add your handling code here:
        this.fillCboNam(cboNamSPBC);
        this.fillCboThang(cboThangSPBC, cboNamSPBC);
        fillTableSPBC();
        try {
            lblSPBanChay.setForeground(Color.orange);
            lblDoanhSo.setForeground(Color.white);
            lblSPTonKho.setForeground(Color.white);
            lblHetHang.setForeground(Color.white);
            lblDoanhThu.setForeground(Color.white);
            pnlParentCard.removeAll();
            pnlParentCard.add(pnlSPBanChay);
            pnlParentCard.validate();
            pnlParentCard.repaint();
            //fill table
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_lblSPBanChayMouseClicked

    private void lblSPTonKhoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSPTonKhoMouseClicked
        // TODO add your handling code here:
        this.fillCboNam(cboNamSPTK);
        this.fillCboThang(cboThangSPTK, cboNamSPTK);
        try {
            lblSPTonKho.setForeground(Color.orange);
            lblSPBanChay.setForeground(Color.white);
            lblDoanhSo.setForeground(Color.white);
            lblHetHang.setForeground(Color.white);
            lblDoanhThu.setForeground(Color.white);
            pnlParentCard.removeAll();
            pnlParentCard.add(pnlSPTonKho);
            pnlParentCard.validate();
            pnlParentCard.repaint();
            //fill
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_lblSPTonKhoMouseClicked

    private void lblHetHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHetHangMouseClicked
        // TODO add your handling code here:
        try {
            lblHetHang.setForeground(Color.orange);
            lblSPBanChay.setForeground(Color.white);
            lblSPTonKho.setForeground(Color.white);
            lblDoanhSo.setForeground(Color.white);
            lblDoanhThu.setForeground(Color.white);
            pnlParentCard.removeAll();
            pnlParentCard.add(pnlSPHetHang);
            pnlParentCard.validate();
            pnlParentCard.repaint();
            //filllll
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_lblHetHangMouseClicked

    private void lblDoanhThuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDoanhThuMouseClicked
        // TODO add your handling code here:
        this.fillCboNam(cboNamDTBL);
        this.fillCboThang(cboThangDTBL, cboNamDTBL);
        this.fillCboNamBS(cboNamDTBS);
        this.fillCboThangBS(cboThangDTBS, cboNamDTBS);
        modelDTBL.setRowCount(0);
        modelDTBL.addRow(new Object[]{
            this.getTongSLBanLe(),
            this.getTongGiaBanLe()
        });
        modelDTBS.setRowCount(0);
        modelDTBS.addRow(new Object[]{
            this.getTongSLBanSi(),
            this.getTongGiaBanSi()
        });
        try {
            lblDoanhThu.setForeground(Color.orange);
            lblDoanhSo.setForeground(Color.white);
            lblSPTonKho.setForeground(Color.white);
            lblHetHang.setForeground(Color.white);
            lblSPBanChay.setForeground(Color.white);
            pnlParentCard.removeAll();
            pnlParentCard.add(pnlDoanhThu);
            pnlParentCard.validate();
            pnlParentCard.repaint();
            //fill table
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_lblDoanhThuMouseClicked

    private void cboThangDSItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboThangDSItemStateChanged
        // TODO add your handling code here:
        try {
            if (cboSort.getSelectedIndex() == 0) {
                this.fillTableSortByPrice();
            } else {
                this.fillTableSortByQuantity();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_cboThangDSItemStateChanged

    private void cboNamDSItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboNamDSItemStateChanged
        // TODO add your handling code here:
        this.fillCboThang(cboThangDS, cboNamDS);
        try {
            if (cboSort.getSelectedIndex() == 0) {
                this.fillTableSortByPrice();
            } else {
                this.fillTableSortByQuantity();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_cboNamDSItemStateChanged

    private void cboSortItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboSortItemStateChanged
        // TODO add your handling code here:
        try {
            if (cboSort.getSelectedIndex() == 0) {
                this.fillTableSortByPrice();
            } else {
                this.fillTableSortByQuantity();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_cboSortItemStateChanged

    private void cboNamSPBCItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboNamSPBCItemStateChanged
        // TODO add your handling code here:
        this.fillCboThang(cboThangSPBC, cboNamSPBC);
    }//GEN-LAST:event_cboNamSPBCItemStateChanged

    private void cboThangSPBCItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboThangSPBCItemStateChanged
        // TODO add your handling code here:
        this.fillTableSPBC();
    }//GEN-LAST:event_cboThangSPBCItemStateChanged

    private void cboNamSPTKItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboNamSPTKItemStateChanged
        // TODO add your handling code here:
        this.fillCboThang(cboThangSPTK, cboNamSPTK);
    }//GEN-LAST:event_cboNamSPTKItemStateChanged

    private void cboThangSPTKItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboThangSPTKItemStateChanged
        // TODO add your handling code here:
        this.fillTableSPTK();
    }//GEN-LAST:event_cboThangSPTKItemStateChanged

    private void cboNamDTBLItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboNamDTBLItemStateChanged
        // TODO add your handling code here:
        this.fillCboThang(cboThangDTBL, cboNamDTBL);
    }//GEN-LAST:event_cboNamDTBLItemStateChanged

    private void cboThangDTBLItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboThangDTBLItemStateChanged
        // TODO add your handling code here:
        modelDTBL.setRowCount(0);
        modelDTBL.addRow(new Object[]{
            this.getTongSLBanLe(),
            this.getTongGiaBanLe()
        });
    }//GEN-LAST:event_cboThangDTBLItemStateChanged

    private void cboNamDTBSItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboNamDTBSItemStateChanged
        // TODO add your handling code here:
        this.fillCboThangBS(cboThangDTBS, cboNamDTBS);
    }//GEN-LAST:event_cboNamDTBSItemStateChanged

    private void cboThangDTBSItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboThangDTBSItemStateChanged
        // TODO add your handling code here:
        modelDTBS.setRowCount(0);
        modelDTBS.addRow(new Object[]{
            this.getTongSLBanSi(),
            this.getTongGiaBanSi()
        });
    }//GEN-LAST:event_cboThangDTBSItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cboNamDS;
    private javax.swing.JComboBox<String> cboNamDTBL;
    private javax.swing.JComboBox<String> cboNamDTBS;
    private javax.swing.JComboBox<String> cboNamSPBC;
    private javax.swing.JComboBox<String> cboNamSPTK;
    private javax.swing.JComboBox<String> cboSort;
    private javax.swing.JComboBox<String> cboThangDS;
    private javax.swing.JComboBox<String> cboThangDTBL;
    private javax.swing.JComboBox<String> cboThangDTBS;
    private javax.swing.JComboBox<String> cboThangSPBC;
    private javax.swing.JComboBox<String> cboThangSPTK;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JLabel lblDoanhSo;
    private javax.swing.JLabel lblDoanhThu;
    private javax.swing.JLabel lblHetHang;
    private javax.swing.JLabel lblSPBanChay;
    private javax.swing.JLabel lblSPTonKho;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblTitle1;
    private javax.swing.JLabel lblTitle2;
    private javax.swing.JLabel lblTitle4;
    private javax.swing.JLabel lblTitle6;
    private javax.swing.JLabel lblTitle7;
    private javax.swing.JPanel pnlDoanhSo;
    private javax.swing.JPanel pnlDoanhThu;
    private javax.swing.JPanel pnlMenuXP;
    private javax.swing.JPanel pnlParentCard;
    private javax.swing.JPanel pnlSPBanChay;
    private javax.swing.JPanel pnlSPHetHang;
    private javax.swing.JPanel pnlSPTonKho;
    private javax.swing.JTable tblDS;
    private javax.swing.JTable tblDTBL;
    private javax.swing.JTable tblDTBS;
    private javax.swing.JTable tblSPBC;
    private javax.swing.JTable tblSPHH;
    private javax.swing.JTable tblSPTK;
    // End of variables declaration//GEN-END:variables
}
