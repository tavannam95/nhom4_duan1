/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mobilez.ui;

import com.mobilez.dao.HangSanXuatDao;
import com.mobilez.dao.MatHangDAO;
import com.mobilez.dao.NhaCungCapDao;
import com.mobilez.models.HangSanXuat;
import com.mobilez.models.Kho;
import com.mobilez.models.MatHang;
import com.mobilez.models.NhaCungCap;
import com.mobilez.models.PhieuNhap;
import com.mobilez.utils.Auth;
import com.mobilez.utils.JdbcHelper;
import com.mobilez.utils.Msgbox;
import com.mobilez.utils.StringToPrice;
import com.mobilez.utils.XDate;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import java.util.Date;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 *
 * @author uhtku
 */
public class FrmPhieuNhap extends javax.swing.JPanel {

    /**
     * Creates new form FrmPhieuNhap
     */
    int indexMH = -1; //Index bảng mặt hàng
    int indexPN = -1; // Index bảng phiếu nhập
    int indexExcel = 0;
    DefaultTableModel modeltblMatHang = new DefaultTableModel();
    DefaultTableModel modeltblPhieuNhap = new DefaultTableModel();
    DefaultComboBoxModel modelCboNCC = new DefaultComboBoxModel();
    DefaultComboBoxModel modelCboKho = new DefaultComboBoxModel();
    MatHangDAO mhDAO = new MatHangDAO();
    HangSanXuatDao hsxDAO = new HangSanXuatDao();
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    long tongTien = 0;
    String exString = "";

    public FrmPhieuNhap() {
        initComponents();
        init();
    }

    private void chosseExcel() throws IOException {
        File exFile;
        FileInputStream exFIS = null;

        JFileChooser exFileChooser = new JFileChooser();
        int rsChosse = exFileChooser.showOpenDialog(null);
        if (rsChosse == JFileChooser.APPROVE_OPTION) {
            exFile = exFileChooser.getSelectedFile();
            // Đọc một file XSL.
            // Đọc một file XSL.
            FileInputStream inputStream = new FileInputStream(exFile);

            Workbook workbook = WorkbookFactory.create(inputStream);
            Iterator<Sheet> sheetIterator = workbook.sheetIterator();

            Sheet sheet = workbook.getSheetAt(0);

            // Create a DataFormatter to format and get each cell's value as String
            DataFormatter dataFormatter = new DataFormatter();

            Iterator<Row> rowIterator = sheet.rowIterator();
            while (rowIterator.hasNext()) {
                
                Row row = rowIterator.next();
//                System.out.println(row);
                // Now let's iterate over the columns of the current row
                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()) {

                    Cell cell = cellIterator.next();
//                    System.out.println(cell);
                    String cellValue = dataFormatter.formatCellValue(cell);
                    int columnIndex = cell.getColumnIndex();
                    switch (columnIndex) {
                        case 0:
//                            System.out.print(cellValue + ",");
                            exString += cellValue + ",";

                            break;
                        case 1:
//                            System.out.print(cellValue + ",");
                            exString += cellValue + ",";
//                            tblNhapHang.setValueAt(cellValue, indexExcel, 1);
                            break;
                        case 2:
//                            System.out.print(cellValue + ",");
                            exString += cellValue + ",";
//                            tblNhapHang.setValueAt(cellValue, indexExcel, 2);
                            break;
                        case 3:
//                            System.out.print(cellValue + ",");
                            exString += cellValue + ",";
//                            tblNhapHang.setValueAt(cellValue, indexExcel, 3);
                            break;
                        case 4:
//                            System.out.print(cellValue + ";");
                            exString += cellValue + ";";
//                            tblNhapHang.setValueAt(cellValue, indexExcel, 4);
                            break;
                        default:
                            break;
                    }

                }
//                System.out.println(exString);
            }

//            System.out.println(exString);
            //count PN
            int countPN = 0;
            for (int i = 0; i < exString.length(); i++) {
                if (exString.charAt(i) == ';') {
                    countPN++;
                }
            }
            //List PN
            List<PhieuNhap> lstPN = new ArrayList<>();
            
            
            // List String
            List<String> testList = new ArrayList<>();
            for (int i = 0; i < countPN; i++) {
                int vt = exString.indexOf(";");
                String pn = exString.substring(0,vt);
                testList.add(pn);
                exString = exString.substring(vt+1);
            }
            String [] lst1Row = new String[testList.size()];
            testList.toArray(lst1Row);
            for (String string : lst1Row) {
                String [] data = string.split(",");
                PhieuNhap pn = new PhieuNhap();
                pn.setMaMH(data[0]);
                pn.setTenMh(data[1]);
                pn.setSoLuong(data[2]);
                pn.setDonGia(data[3]);
                pn.setThanhTien(data[4]);
                lstPN.add(pn);
            }
            for (int i = 0; i < lstPN.size(); i++) {
                modeltblPhieuNhap.addRow(new Object[]{
                    lstPN.get(i).getMaMH().trim(),
                    lstPN.get(i).getTenMh().trim(),
                    lstPN.get(i).getSoLuong().trim(),
                    lstPN.get(i).getDonGia().trim(),
                    lstPN.get(i).getThanhTien().trim()
                });
            }
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

        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblMatHang = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        txtMaMH = new javax.swing.JTextField();
        lblMaMH = new javax.swing.JLabel();
        lblHSX = new javax.swing.JLabel();
        cboNCC = new javax.swing.JComboBox<>();
        btnThemNCC = new javax.swing.JButton();
        lblTenMH = new javax.swing.JLabel();
        txtTenMH = new javax.swing.JTextField();
        txtMaNV = new javax.swing.JTextField();
        txtDongia = new javax.swing.JTextField();
        lblRAM = new javax.swing.JLabel();
        lblDL = new javax.swing.JLabel();
        lblGM = new javax.swing.JLabel();
        lblSL = new javax.swing.JLabel();
        txtSoLuong = new javax.swing.JTextField();
        btnThemMatHang = new javax.swing.JButton();
        btnThemPN = new javax.swing.JButton();
        btnXoaPN = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblNhapHang = new javax.swing.JTable();
        btnClear1 = new javax.swing.JButton();
        lblGM1 = new javax.swing.JLabel();
        txtNgayNhap = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        lblTongTien = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cboMaKho = new javax.swing.JComboBox<>();
        btnThemKho = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        btnExcel = new javax.swing.JButton();

        setBackground(new java.awt.Color(34, 116, 173));

        jLabel1.setFont(new java.awt.Font("Baloo 2 ExtraBold", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("DANH SÁCH MẶT HÀNG");

        tblMatHang.setBackground(new java.awt.Color(34, 116, 173));
        tblMatHang.setFont(new java.awt.Font("Baloo 2", 0, 13)); // NOI18N
        tblMatHang.setForeground(new java.awt.Color(255, 255, 255));
        tblMatHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã MH", "Tên MH", "HSX", "RAM", "Dung lượng", "Màu", "Quốc gia", "Số lượng trong kho"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblMatHang.setToolTipText("");
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
        if (tblMatHang.getColumnModel().getColumnCount() > 0) {
            tblMatHang.getColumnModel().getColumn(0).setResizable(false);
            tblMatHang.getColumnModel().getColumn(0).setPreferredWidth(20);
            tblMatHang.getColumnModel().getColumn(1).setResizable(false);
            tblMatHang.getColumnModel().getColumn(1).setPreferredWidth(100);
            tblMatHang.getColumnModel().getColumn(2).setResizable(false);
            tblMatHang.getColumnModel().getColumn(3).setResizable(false);
            tblMatHang.getColumnModel().getColumn(3).setPreferredWidth(15);
            tblMatHang.getColumnModel().getColumn(4).setResizable(false);
            tblMatHang.getColumnModel().getColumn(4).setPreferredWidth(20);
            tblMatHang.getColumnModel().getColumn(5).setResizable(false);
            tblMatHang.getColumnModel().getColumn(6).setResizable(false);
            tblMatHang.getColumnModel().getColumn(7).setResizable(false);
        }

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mobilez/icon/search_32px.png"))); // NOI18N

        txtSearch.setBackground(new java.awt.Color(34, 116, 173));
        txtSearch.setFont(new java.awt.Font("Baloo 2 ExtraBold", 1, 14)); // NOI18N
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

        txtMaMH.setEditable(false);
        txtMaMH.setBackground(new java.awt.Color(34, 116, 173));
        txtMaMH.setFont(new java.awt.Font("Baloo 2 ExtraBold", 1, 14)); // NOI18N
        txtMaMH.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)));
        txtMaMH.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtMaMHtxtDungLuongKeyPressed(evt);
            }
        });

        lblMaMH.setFont(new java.awt.Font("Baloo Chettan 2", 1, 14)); // NOI18N
        lblMaMH.setForeground(new java.awt.Color(255, 255, 255));
        lblMaMH.setText("Mã mặt hàng");

        lblHSX.setFont(new java.awt.Font("Baloo Chettan 2", 1, 14)); // NOI18N
        lblHSX.setForeground(new java.awt.Color(255, 255, 255));
        lblHSX.setText("Nhà cung cấp");

        cboNCC.setBackground(new java.awt.Color(34, 116, 173));
        cboNCC.setFont(new java.awt.Font("Baloo 2 ExtraBold", 1, 14)); // NOI18N
        cboNCC.setForeground(new java.awt.Color(255, 255, 255));

        btnThemNCC.setBackground(new java.awt.Color(34, 116, 173));
        btnThemNCC.setFont(new java.awt.Font("Baloo 2", 1, 12)); // NOI18N
        btnThemNCC.setForeground(new java.awt.Color(255, 255, 255));
        btnThemNCC.setText("Thêm NCC");
        btnThemNCC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemNCCActionPerformed(evt);
            }
        });

        lblTenMH.setFont(new java.awt.Font("Baloo Chettan 2", 1, 14)); // NOI18N
        lblTenMH.setForeground(new java.awt.Color(255, 255, 255));
        lblTenMH.setText("Tên mặt hàng");

        txtTenMH.setEditable(false);
        txtTenMH.setBackground(new java.awt.Color(34, 116, 173));
        txtTenMH.setFont(new java.awt.Font("Baloo 2 ExtraBold", 1, 14)); // NOI18N
        txtTenMH.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)));
        txtTenMH.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTenMHtxtDungLuongKeyPressed(evt);
            }
        });

        txtMaNV.setEditable(false);
        txtMaNV.setBackground(new java.awt.Color(34, 116, 173));
        txtMaNV.setFont(new java.awt.Font("Baloo 2 ExtraBold", 1, 14)); // NOI18N
        txtMaNV.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)));
        txtMaNV.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtMaNVtxtDungLuongKeyPressed(evt);
            }
        });

        txtDongia.setBackground(new java.awt.Color(34, 116, 173));
        txtDongia.setFont(new java.awt.Font("Baloo 2 ExtraBold", 1, 14)); // NOI18N
        txtDongia.setForeground(new java.awt.Color(255, 255, 255));
        txtDongia.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)));
        txtDongia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDongiatxtDungLuongKeyPressed(evt);
            }
        });

        lblRAM.setFont(new java.awt.Font("Baloo Chettan 2", 1, 14)); // NOI18N
        lblRAM.setForeground(new java.awt.Color(255, 255, 255));
        lblRAM.setText("Mã nhân viên");

        lblDL.setFont(new java.awt.Font("Baloo Chettan 2", 1, 14)); // NOI18N
        lblDL.setForeground(new java.awt.Color(255, 255, 255));
        lblDL.setText("Kho");

        lblGM.setFont(new java.awt.Font("Baloo Chettan 2", 1, 14)); // NOI18N
        lblGM.setForeground(new java.awt.Color(255, 255, 255));
        lblGM.setText("Đơn giá");

        lblSL.setFont(new java.awt.Font("Baloo Chettan 2", 1, 14)); // NOI18N
        lblSL.setForeground(new java.awt.Color(255, 255, 255));
        lblSL.setText("Số lượng");

        txtSoLuong.setBackground(new java.awt.Color(34, 116, 173));
        txtSoLuong.setFont(new java.awt.Font("Baloo 2 ExtraBold", 1, 14)); // NOI18N
        txtSoLuong.setForeground(new java.awt.Color(255, 255, 255));
        txtSoLuong.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)));
        txtSoLuong.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSoLuongtxtDungLuongKeyPressed(evt);
            }
        });

        btnThemMatHang.setBackground(new java.awt.Color(34, 116, 173));
        btnThemMatHang.setFont(new java.awt.Font("Baloo 2", 1, 12)); // NOI18N
        btnThemMatHang.setForeground(new java.awt.Color(255, 255, 255));
        btnThemMatHang.setText("Thêm MH");
        btnThemMatHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemMatHangActionPerformed(evt);
            }
        });

        btnThemPN.setBackground(new java.awt.Color(34, 116, 173));
        btnThemPN.setFont(new java.awt.Font("Baloo 2", 1, 12)); // NOI18N
        btnThemPN.setForeground(new java.awt.Color(255, 255, 255));
        btnThemPN.setText("Thêm vào phiếu nhập");
        btnThemPN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemPNActionPerformed(evt);
            }
        });

        btnXoaPN.setBackground(new java.awt.Color(34, 116, 173));
        btnXoaPN.setFont(new java.awt.Font("Baloo 2", 1, 12)); // NOI18N
        btnXoaPN.setForeground(new java.awt.Color(255, 255, 255));
        btnXoaPN.setText("Xóa");
        btnXoaPN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaPNActionPerformed(evt);
            }
        });

        tblNhapHang.setBackground(new java.awt.Color(34, 116, 173));
        tblNhapHang.setForeground(new java.awt.Color(255, 255, 255));
        tblNhapHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã mặt hàng", "Tên mặt hàng", "Số lượng", "Đơn giá", "Thành tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblNhapHang.setGridColor(new java.awt.Color(255, 255, 255));
        tblNhapHang.setRowHeight(25);
        tblNhapHang.setSelectionBackground(new java.awt.Color(51, 51, 51));
        tblNhapHang.setShowGrid(true);
        jScrollPane1.setViewportView(tblNhapHang);

        btnClear1.setBackground(new java.awt.Color(34, 116, 173));
        btnClear1.setFont(new java.awt.Font("Baloo 2", 1, 18)); // NOI18N
        btnClear1.setForeground(new java.awt.Color(255, 255, 255));
        btnClear1.setText("Nhập kho");
        btnClear1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClear1ActionPerformed(evt);
            }
        });

        lblGM1.setFont(new java.awt.Font("Baloo Chettan 2", 1, 14)); // NOI18N
        lblGM1.setForeground(new java.awt.Color(255, 255, 255));
        lblGM1.setText("Ngày nhập");

        txtNgayNhap.setEditable(false);
        txtNgayNhap.setBackground(new java.awt.Color(34, 116, 173));
        txtNgayNhap.setFont(new java.awt.Font("Baloo 2 ExtraBold", 1, 14)); // NOI18N
        txtNgayNhap.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)));
        txtNgayNhap.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNgayNhaptxtDungLuongKeyPressed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Baloo 2 ExtraBold", 1, 24)); // NOI18N
        jLabel4.setText("Tổng tiền:");

        lblTongTien.setFont(new java.awt.Font("Baloo 2 ExtraBold", 1, 36)); // NOI18N
        lblTongTien.setForeground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 10, Short.MAX_VALUE))
        );

        jLabel5.setFont(new java.awt.Font("Baloo 2 ExtraBold", 0, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("PHIẾU NHẬP");

        cboMaKho.setBackground(new java.awt.Color(34, 116, 173));
        cboMaKho.setFont(new java.awt.Font("Baloo 2 ExtraBold", 1, 14)); // NOI18N
        cboMaKho.setForeground(new java.awt.Color(255, 255, 255));
        cboMaKho.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboMaKhoItemStateChanged(evt);
            }
        });

        btnThemKho.setBackground(new java.awt.Color(34, 116, 173));
        btnThemKho.setFont(new java.awt.Font("Baloo 2", 1, 12)); // NOI18N
        btnThemKho.setForeground(new java.awt.Color(255, 255, 255));
        btnThemKho.setText("Thêm Kho");
        btnThemKho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemKhoActionPerformed(evt);
            }
        });

        btnClear.setBackground(new java.awt.Color(34, 116, 173));
        btnClear.setFont(new java.awt.Font("Baloo 2", 1, 12)); // NOI18N
        btnClear.setForeground(new java.awt.Color(255, 255, 255));
        btnClear.setText("Clear phiếu nhập");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        btnExcel.setBackground(new java.awt.Color(34, 116, 173));
        btnExcel.setFont(new java.awt.Font("Baloo 2", 1, 12)); // NOI18N
        btnExcel.setForeground(new java.awt.Color(255, 255, 255));
        btnExcel.setText("Nhập bằng excel");
        btnExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 832, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(184, 184, 184)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
                        .addGap(118, 118, 118))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lblMaMH, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(txtMaMH, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnThemMatHang, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblDL, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cboMaKho, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnThemKho))
                            .addComponent(jScrollPane2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblTenMH)
                                    .addComponent(lblSL)
                                    .addComponent(lblGM))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtTenMH, javax.swing.GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE)
                                    .addComponent(txtSoLuong)
                                    .addComponent(txtDongia)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(btnThemPN)
                                .addGap(18, 18, 18)
                                .addComponent(btnXoaPN)
                                .addGap(18, 18, 18)
                                .addComponent(btnClear)))))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 621, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnExcel))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(lblHSX, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(lblRAM, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(8, 8, 8)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(lblGM1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(22, 22, 22)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtMaNV)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(cboNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(12, 12, 12)
                                    .addComponent(btnThemNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(txtNgayNhap, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnClear1)
                        .addGap(97, 97, 97))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cboMaKho, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnThemKho, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(lblDL, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMaMH, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnThemMatHang)
                            .addComponent(lblMaMH, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblTenMH, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTenMH, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblSL, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblGM, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDongia, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnThemPN)
                            .addComponent(btnXoaPN)
                            .addComponent(btnClear)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(btnExcel))
                .addGap(2, 2, 2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblHSX, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnThemNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblRAM, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblGM1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNgayNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnClear1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {cboNCC, lblDL, lblGM, lblHSX, lblMaMH, lblRAM, lblSL, lblTenMH, txtDongia, txtMaMH, txtMaNV, txtNgayNhap, txtSoLuong, txtTenMH});

    }// </editor-fold>//GEN-END:initComponents

    private void tblMatHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMatHangMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 2) {
            indexMH = tblMatHang.getSelectedRow();
            this.setform();
        }

    }//GEN-LAST:event_tblMatHangMouseClicked

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        this.selectByKeyWork(txtSearch.getText());
    }//GEN-LAST:event_txtSearchKeyReleased

    private void txtMaMHtxtDungLuongKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMaMHtxtDungLuongKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaMHtxtDungLuongKeyPressed

    private void btnThemNCCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemNCCActionPerformed
        // TODO add your handling code here:
        new JDialogNhaCC(null, true).setVisible(true);
        this.fillcboNCC();
    }//GEN-LAST:event_btnThemNCCActionPerformed

    private void txtTenMHtxtDungLuongKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTenMHtxtDungLuongKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenMHtxtDungLuongKeyPressed

    private void txtMaNVtxtDungLuongKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMaNVtxtDungLuongKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaNVtxtDungLuongKeyPressed

    private void txtDongiatxtDungLuongKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDongiatxtDungLuongKeyPressed
        // TODO add your handling code here:

    }//GEN-LAST:event_txtDongiatxtDungLuongKeyPressed

    private void txtSoLuongtxtDungLuongKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSoLuongtxtDungLuongKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSoLuongtxtDungLuongKeyPressed

    private void btnThemMatHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemMatHangActionPerformed
        new JDialogHangHoa(null, true).setVisible(true);
        this.filltoTblMatHang();
    }//GEN-LAST:event_btnThemMatHangActionPerformed

    private void btnThemPNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemPNActionPerformed
        if (checkForm()) {
            this.themTblPhieuNhap();
            this.clear();
        }

    }//GEN-LAST:event_btnThemPNActionPerformed

    private void btnXoaPNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaPNActionPerformed
        indexPN = tblNhapHang.getSelectedRow();

        if (indexPN == -1) {
            Msgbox.alert(this, "Bạn chưa chọn mặt hàng cần xóa trong phiếu nhập");
            return;
        }
        deletePN();
    }//GEN-LAST:event_btnXoaPNActionPerformed

    private void btnClear1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClear1ActionPerformed
        if (modeltblPhieuNhap.getRowCount() == 0) {
            Msgbox.alert(this, "Không có dữ liệu trong phiếu nhập!!");
            return;
        }
        if (Msgbox.confirm(this, "Bạn có muốn nhập kho?")) {
            nhapKho();
            
        }

    }//GEN-LAST:event_btnClear1ActionPerformed

    private void txtNgayNhaptxtDungLuongKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNgayNhaptxtDungLuongKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNgayNhaptxtDungLuongKeyPressed

    private void btnThemKhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemKhoActionPerformed
        new JDialogKho(null, true).setVisible(true);
        this.fillcboKho();
    }//GEN-LAST:event_btnThemKhoActionPerformed

    private void cboMaKhoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboMaKhoItemStateChanged

        this.filltoTblMatHang();
    }//GEN-LAST:event_cboMaKhoItemStateChanged

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        if (modeltblPhieuNhap.getRowCount() == 0) {
            Msgbox.alert(this, "Không còn dữ liệu");
            return;
        }
        if (Msgbox.confirm(this, "Bạn có muốn xóa hết mặt hàng trong phiếu nhập?")) {
            modeltblPhieuNhap.setRowCount(0);
            indexPN = -1;
            tongTien = 0;
            lblTongTien.setText(tongTien + "");
            txtMaMH.setText("");
            txtTenMH.setText("");
            txtSoLuong.setText("");
            txtDongia.setText("");
        }

    }//GEN-LAST:event_btnClearActionPerformed

    private void btnExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcelActionPerformed
        try {
            // TODO add your handling code here:
//        File exFile;
//        FileInputStream exFIS = null;
//        
//        JFileChooser exFileChooser = new JFileChooser();
//        int rsChosse = exFileChooser.showOpenDialog(null);
//        if (rsChosse == JFileChooser.APPROVE_OPTION) {
//            exFile = exFileChooser.getSelectedFile();
//            
//        }
            modeltblPhieuNhap.setRowCount(0);
            this.chosseExcel();
            long tongGia = 0; 
            for (int i = 0; i < tblNhapHang.getRowCount(); i++) {
                tongGia += Integer.parseInt(tblNhapHang.getValueAt(i, 4).toString());
            }
            String tonGiaString = tongGia+"";
            lblTongTien.setText(StringToPrice.getPrice(tonGiaString));
            btnThemPN.setEnabled(false);
        } catch (IOException ex) {
            Logger.getLogger(FrmPhieuNhap.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnExcelActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnClear1;
    private javax.swing.JButton btnExcel;
    private javax.swing.JButton btnThemKho;
    private javax.swing.JButton btnThemMatHang;
    private javax.swing.JButton btnThemNCC;
    private javax.swing.JButton btnThemPN;
    private javax.swing.JButton btnXoaPN;
    private javax.swing.JComboBox<String> cboMaKho;
    private javax.swing.JComboBox<String> cboNCC;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblDL;
    private javax.swing.JLabel lblGM;
    private javax.swing.JLabel lblGM1;
    private javax.swing.JLabel lblHSX;
    private javax.swing.JLabel lblMaMH;
    private javax.swing.JLabel lblRAM;
    private javax.swing.JLabel lblSL;
    private javax.swing.JLabel lblTenMH;
    private javax.swing.JLabel lblTongTien;
    private javax.swing.JTable tblMatHang;
    private javax.swing.JTable tblNhapHang;
    private javax.swing.JTextField txtDongia;
    private javax.swing.JTextField txtMaMH;
    private javax.swing.JTextField txtMaNV;
    private javax.swing.JTextField txtNgayNhap;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtTenMH;
    // End of variables declaration//GEN-END:variables

    private void init() {
        modeltblMatHang = (DefaultTableModel) tblMatHang.getModel();
        modeltblPhieuNhap = (DefaultTableModel) tblNhapHang.getModel();
        modelCboNCC = (DefaultComboBoxModel) cboNCC.getModel();
        modelCboKho = (DefaultComboBoxModel) cboMaKho.getModel();
        fillcboKho();
        fillcboNCC();
        //        txtMaNV.setText(Auth.user.getMaNV());
        txtNgayNhap.setText(sdf.format(new Date()));
        txtMaNV.setText(Auth.user.getMaNV());
    }

    private void filltoTblMatHang() {
        Kho kho = (Kho) cboMaKho.getSelectedItem();
        if (kho != null) {
            modeltblMatHang.setRowCount(0);
            try {
                String sql = "select KHOHANG.MAMH,TENMH,TENHSX,RAM,DUNGLUONG,MAUSAC,TENQG,KHOHANG.SOLUONG\n"
                        + "from MATHANG join HANGSANXUAT on MATHANG.MAHSX=HANGSANXUAT.MAHSX\n"
                        + "join QUOCGIA on MATHANG.MAQG= QUOCGIA.MAQG\n"
                        + "join KHOHANG on MATHANG.MAMH = KHOHANG.MAMH\n"
                        + "join KHO on KHOHANG.MAK = KHO.MAK\n"
                        + "where KHOHANG.MAK = ? and MATHANG.TRANGTHAI = 1";

                ResultSet rs = JdbcHelper.query(sql, kho.getMaK());
                while (rs.next()) {
                    modeltblMatHang.addRow(new Object[]{rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4) + "GB", rs.getString(5) + "GB", rs.getString(6), rs.getString(7), rs.getInt(8)});
                }
                rs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void setform() {
        txtMaMH.setText((tblMatHang.getValueAt(indexMH, 0)).toString());
        txtTenMH.setText((tblMatHang.getValueAt(indexMH, 1)).toString());

    }

    private void deletePN() {
        tongTien -= Integer.parseInt(modeltblPhieuNhap.getValueAt(indexPN, 4).toString());
        modeltblPhieuNhap.removeRow(indexPN);
        lblTongTien.setText(tongTien + "");
        indexPN = -1;
    }

    private void themTblPhieuNhap() {
        NhaCungCap ncc = (NhaCungCap) cboNCC.getSelectedItem();
        Kho kho = (Kho) cboMaKho.getSelectedItem();
        String maMH = txtMaMH.getText();
        String tenMH = txtTenMH.getText();
        int soluong = Integer.parseInt(txtSoLuong.getText());
        int dongia = Integer.parseInt(txtDongia.getText());
        int thanhTien = soluong * dongia;
        modeltblPhieuNhap.addRow(new Object[]{maMH, tenMH, soluong, dongia, thanhTien});
        tongTien += thanhTien;
        String tongtien = String.valueOf(tongTien);
        String tongtien2 = "";
        if (String.valueOf(tongTien).length() > 3) {
            int tongDauCham = (int) tongtien.length() / 3; // Tổng số dấu chấm trong thành tiền
            if (tongtien.length() % 3 == 1) {
                tongtien2 = tongtien.substring(0, 1) + "." + tongtien.substring(1, 4);
                int indexDauCham = 4;
                while (tongDauCham > 1) {
                    tongtien2 = tongtien2 + "." + tongtien.substring(indexDauCham, indexDauCham + 3);
                    indexDauCham += 3;
                    tongDauCham--;
                }
            } else if (tongtien.length() % 3 == 2) {
                tongtien2 = tongtien.substring(0, 2) + "." + tongtien.substring(2, 5);
                int indexDauCham = 5;
                while (tongDauCham > 1) {
                    tongtien2 = tongtien2 + "." + tongtien.substring(indexDauCham, indexDauCham + 3);
                    indexDauCham += 3;
                    tongDauCham--;
                }
            } else if (tongtien.length() % 3 == 0) {
                if (tongDauCham > 1) {
                    tongtien2 = tongtien.substring(0, 3) + "." + tongtien.substring(3, 6);
                }
                int indexDauCham = 6;
                while (tongDauCham > 2) {
                    tongtien2 = tongtien2 + "." + tongtien.substring(indexDauCham, indexDauCham + 3);
                    indexDauCham += 3;
                    tongDauCham--;
                }
            }
            lblTongTien.setText(tongtien2 + " VND");
        } else {
            lblTongTien.setText(tongtien + " VND");
        }

    }

    private void fillcboNCC() {
        try {
            NhaCungCapDao nccDAO = new NhaCungCapDao();
            List<NhaCungCap> lstNCC = nccDAO.selectAll();
            modelCboNCC.removeAllElements();
            for (NhaCungCap ncc : lstNCC) {
                modelCboNCC.addElement(ncc);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void fillcboKho() {
        try {
            String sql = "Select * from Kho";
            ResultSet rs = JdbcHelper.query(sql);
            modelCboKho.removeAllElements();
            while (rs.next()) {
                modelCboKho.addElement(new Kho(rs.getString("MaK"), rs.getString("TenK"), rs.getString("DiaChi"),rs.getBoolean(4)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void selectByKeyWork(String keywork) {
        modeltblMatHang.setRowCount(0);
        Kho kho = (Kho) cboMaKho.getSelectedItem();
        try {
            String sql = "select KHOHANG.MAMH,TENMH,TENHSX,RAM,DUNGLUONG,MAUSAC,TENQG,KHOHANG.SOLUONG\n"
                    + "from MATHANG join HANGSANXUAT on MATHANG.MAHSX=HANGSANXUAT.MAHSX\n"
                    + "join QUOCGIA on MATHANG.MAQG= QUOCGIA.MAQG\n"
                    + "join KHOHANG on MATHANG.MAMH = KHOHANG.MAMH\n"
                    + "join KHO on KHOHANG.MAK = KHO.MAK\n"
                    + "where KHOHANG.MAK = ? and TRANGTHAI = 1 and (KHOHANG.MAMH like N'%" + keywork + "%'"
                    + " or TENMH like N'%" + keywork + "%' or TENHSX like N'%" + keywork + "%'" + " or TENQG like N'%" + keywork + "%')";
            ResultSet rs = JdbcHelper.query(sql, kho.getMaK());
            while (rs.next()) {
                modeltblMatHang.addRow(new Object[]{rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4) + "GB", rs.getString(5) + "GB", rs.getString(6), rs.getString(7), rs.getInt(8)});
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean checkForm() {
        int dongia;
        int soluong;
        if (indexMH == -1) {
            Msgbox.alert(this, "Bạn chưa chọn mặt hàng");
            return false;
        }
        if (txtSoLuong.getText().trim().equals("")) {
            Msgbox.alert(this, "Không được để trống số lượng!!");
            txtSoLuong.requestFocus();
            return false;
        }
        if (txtDongia.getText().trim().equals("")) {
            Msgbox.alert(this, "Không được để trống đơn giá!!");
            txtDongia.requestFocus();
            return false;
        }
        try {
            soluong = Integer.parseInt(txtSoLuong.getText().trim());
            if (soluong < 1) {
                Msgbox.alert(this, "số lượng phải lớn hơn 0!!");
                txtSoLuong.requestFocus();
                return false;
            }
        } catch (Exception e) {
            Msgbox.alert(this, "số lượng phải là số!!");
            txtSoLuong.requestFocus();
            return false;
        }
        try {
            dongia = Integer.parseInt(txtDongia.getText().trim());
            if (dongia < 1) {
                Msgbox.alert(this, "Đơn giá phải lớn hơn 0!!");
                txtDongia.requestFocus();
                return false;
            }
        } catch (Exception e) {
            Msgbox.alert(this, "Đơn giá phải là số!!");
            txtDongia.requestFocus();
            return false;
        }
        return true;
    }

    private void nhapKho() {
        try {
            String insertPNK = "insert into PHIEUNHAPKHO values (?,?,?,?,?)";
            String queryIDPNH = "select COUNT(*) IDPNK from PHIEUNHAPKHO";
            String insertCTPNK = "insert into CHITIETPHIEUNHAPKHO values (?,?,?)";
            String updateSoLuongFromKhoHang = "update khohang set soluong = soluong + ? where mamh = ? and mak = ?";
            String updateSoluongFromMatHang = "update MatHang set soluong = soluong + ? where mamh = ?";
            NhaCungCap ncc = (NhaCungCap) cboNCC.getSelectedItem();
            Kho kho = (Kho) cboMaKho.getSelectedItem();
            JdbcHelper.update(insertPNK, ncc.getMaNcc(), kho.getMaK(), Auth.user.getMaNV(), sdf.parse(txtNgayNhap.getText()), tongTien);
            for (int i = 0; i < modeltblPhieuNhap.getRowCount(); i++) {
                String mamh = (String) tblNhapHang.getValueAt(i, 0).toString();
                int soluong = Integer.parseInt(tblNhapHang.getValueAt(i, 2).toString()) ;
                ResultSet rs = JdbcHelper.query(queryIDPNH);
                if (rs.next()) {
                    int maPNK = rs.getInt("IDPNK");
                    JdbcHelper.update(insertCTPNK, maPNK, mamh, soluong);
                    JdbcHelper.update(updateSoLuongFromKhoHang, soluong, mamh, kho.getMaK());
                    JdbcHelper.update(updateSoluongFromMatHang, soluong, mamh);
                }
            }
            Msgbox.alert(this, "Nhập kho thành công");
            filltoTblMatHang();
            modeltblPhieuNhap.setRowCount(0);
            indexPN = -1;
            tongTien = 0;
            lblTongTien.setText(tongTien + "");
            txtMaMH.setText("");
            txtTenMH.setText("");
            txtSoLuong.setText("");
            txtDongia.setText("");
            btnThemPN.setEnabled(true);
        } catch (Exception e) {
            Msgbox.alert(this, "Nhập kho thất bại");
            e.printStackTrace();

        }
    }

    private void clear() {
        txtMaMH.setText("");
        txtTenMH.setText("");
        txtSoLuong.setText("");
        txtDongia.setText("");
        indexMH = -1;
    }
}
