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
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author uhtku
 */
public class FrmHoaDonBanSi extends javax.swing.JPanel {

    /**
     * Creates new form FrmHoaDonBanSi
     */
    DefaultTableModel modelList;
    DefaultTableModel modelKH;
    DefaultTableModel modelHDCT;
    DefaultComboBoxModel<Kho> modelCboKho;
    int indexList = -1, indexKH = -1, indexHDCT = -1;

    public FrmHoaDonBanSi() {
        initComponents();
        modelKH = (DefaultTableModel) tblKH.getModel();
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

    private void addToHDCT() {
        try {
            modelHDCT.addRow(new Object[]{
                tblList.getValueAt(indexList, 0).toString(),
                tblList.getValueAt(indexList, 7).toString(),
                ""
            });
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

    private void fillTablePr() {
        try {
            String sql = "select MATHANG.MAMH,TENHSX,TENMH,RAM,DUNGLUONG,MAUSAC,TENQG,GIABANSI, KHOHANG.SOLUONG\n"
                    + "from MATHANG join KHOHANG ON MATHANG.MAMH=KHOHANG.MAMH\n"
                    + "join HANGSANXUAT on MATHANG.MAHSX=HANGSANXUAT.MAHSX\n"
                    + "join QUOCGIA on MATHANG.MAQG=QUOCGIA.MAQG\n"
                    + "where MAK like ? AND TRANGTHAI = 1";
            Kho k = (Kho) cboKho.getSelectedItem();
            ResultSet rs = JdbcHelper.query(sql, k.getMaK());
            String giaBanSi;
            modelList.setRowCount(0);
            while (rs.next()) {
                giaBanSi = rs.getString(8);
                int gbs = giaBanSi.indexOf(".");
                giaBanSi = giaBanSi.substring(0, gbs);
                modelList.addRow(new Object[]{
                    rs.getString(1), rs.getString(2), rs.getString(3),
                    this.getDlRAM(rs.getInt(4)), this.getDlRAM(rs.getInt(5)), rs.getString(6),
                    rs.getString(7), giaBanSi, rs.getString(9)});
            }
            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void searchMH() {
        try {
            String sql = "select MATHANG.MAMH,TENHSX,TENMH,RAM,DUNGLUONG,MAUSAC,TENQG,GIABANSI,\n" +
"                    KHOHANG.SOLUONG\n" +
"                    from MATHANG \n" +
"					join KHOHANG ON MATHANG.MAMH=KHOHANG.MAMH\n" +
"                    join HANGSANXUAT on MATHANG.MAHSX=HANGSANXUAT.MAHSX\n" +
"                    join QUOCGIA on MATHANG.MAQG=QUOCGIA.MAQG\n" +
"                    where MAK like ? and (MATHANG.MAMH like ? or TENMH like ? or TENHSX like ?) and TRANGTHAI = 1";
            Kho k = (Kho) cboKho.getSelectedItem();
            String search = "%" + txtSearch.getText() + "%";
            ResultSet rs = JdbcHelper.query(sql, k.getMaK(), search, search, search);
            String giaMua, giaBanSi, giaBanLe;
            modelList.setRowCount(0);
            while (rs.next()) {
                giaBanSi = rs.getString(8);
                int gbs = giaBanSi.indexOf(".");
                giaBanSi = giaBanSi.substring(0, gbs);
                modelList.addRow(new Object[]{
                    rs.getString(1), rs.getString(2), rs.getString(3),
                    this.getDlRAM(rs.getInt(4)), this.getDlRAM(rs.getInt(5)), rs.getString(6),
                    rs.getString(7), giaBanSi, rs.getString(9)});
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getDlRAM(int DLorRAM) {
        return DLorRAM + "GB";
    }

    private int getMaHDBS() {
        try {
            String sql = "select count(*) from HOADONBANSI";
            ResultSet rs = JdbcHelper.query(sql);
            int maHDBS = -1;
            while (rs.next()) {
                maHDBS = rs.getInt(1);
            }
            rs.close();
            return maHDBS;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    private void searchKH() {
        try {
            String sql = "select top 3 * from KHACHHANG where MAKH like ? or TENKH like ? or SODT like ?";
            String searchKHString = "%" + txtSearchKH.getText() + "%";
            ResultSet rs = JdbcHelper.query(sql, searchKHString, searchKHString, searchKHString);
            modelKH.setRowCount(0);
            while (rs.next()) {
                modelKH.addRow(new Object[]{
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4)
                });
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
    
    private void addToTblList(){
        try {
            String sql = "select MATHANG.MAMH,TENHSX,TENMH,RAM,DUNGLUONG,MAUSAC,TENQG,GIABANSI, KHOHANG.SOLUONG\n"
                    + "from MATHANG join KHOHANG ON MATHANG.MAMH=KHOHANG.MAMH\n"
                    + "join HANGSANXUAT on MATHANG.MAHSX=HANGSANXUAT.MAHSX\n"
                    + "join QUOCGIA on MATHANG.MAQG=QUOCGIA.MAQG\n"
                    + "where MATHANG.MAMH like ?";
            String maMH = tblHoaDonChiTiet.getValueAt(indexHDCT, 0).toString();
            ResultSet rs = JdbcHelper.query(sql, maMH);
            String giaBanSi;
            modelList.setRowCount(0);
            while (rs.next()) {
                giaBanSi = rs.getString(8);
                int gbs = giaBanSi.indexOf(".");
                giaBanSi = giaBanSi.substring(0, gbs);
                modelList.addRow(new Object[]{
                    rs.getString(1), rs.getString(2), rs.getString(3),
                    this.getDlRAM(rs.getInt(4)), this.getDlRAM(rs.getInt(5)), rs.getString(6),
                    rs.getString(7), giaBanSi, rs.getString(9)});
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private String getTongGia(){
        int giaBanSi,tongGia=0;
        int soLuong;
        for (int i = 0; i < tblHoaDonChiTiet.getRowCount(); i++) {
            giaBanSi = Integer.parseInt(tblHoaDonChiTiet.getValueAt(i, 1).toString());
            soLuong = Integer.parseInt(tblHoaDonChiTiet.getValueAt(i, 2).toString());
            tongGia+=(soLuong*giaBanSi);
        }
        return tongGia+"VNĐ";
    }
    
    private boolean chkSLNotNull(){
        boolean chk = false;
        for (int i = 0; i < tblHoaDonChiTiet.getRowCount(); i++) {
            if (tblHoaDonChiTiet.getValueAt(i, 2).toString().equals("")) {
                chk = true;
                break;
            }
        }
        return chk;
    }
    
    private boolean chkSLIsNum(){
        boolean chk = false;
        for (int i = 0; i < tblHoaDonChiTiet.getRowCount(); i++) {
            try {
                Integer.parseInt(tblHoaDonChiTiet.getValueAt(i, 2).toString());
            } catch (Exception e) {
                chk = true;
            }
        }
        return chk;
    }
    
    private boolean chkSLLonHon0(){
        boolean chk = false;
        for (int i = 0; i < tblHoaDonChiTiet.getRowCount(); i++) {
            try {
                if (Integer.parseInt(tblHoaDonChiTiet.getValueAt(i, 2).toString())<=0) {
                    chk= true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return chk;
    }
    private void insertCTHDBS(){
        try {
            String sql = "insert into CHITIETHOADONBANSI values (?,?,?,?)";
            int maHDBS = getMaHDBS();
            String maMH;
            int soLuong;
            double giaBanSi;
            for (int i = 0; i < tblHoaDonChiTiet.getRowCount(); i++) {
                maMH = tblHoaDonChiTiet.getValueAt(i, 0).toString();
                soLuong = Integer.parseInt(tblHoaDonChiTiet.getValueAt(i, 2).toString());
                giaBanSi = Double.parseDouble(tblHoaDonChiTiet.getValueAt(i, 1).toString());
                JdbcHelper.update(sql, maHDBS,maMH,soLuong,giaBanSi);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void insertHDBS(){
        try {
            String sql ="insert into HOADONBANSI values (?,?,?,?,?)";
            String maKH = tblKH.getValueAt(indexKH, 0).toString();
            Kho k = (Kho) cboKho.getSelectedItem();
            String maK = k.getMaK();
            String maNV = Auth.user.getMaNV();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date now = new Date();
            now = sdf.parse(sdf.format(now));
            double tongGia = Double.parseDouble(lblRsTongGia.getText().substring(0,lblRsTongGia.getText().length()-3));
            int s = JdbcHelper.update(sql, maKH,maK,maNV,now,tongGia);
            if (s>0) {
                Msgbox.alert(null, "Thêm hóa đơn bán sỉ thành công!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void resetAll(){
        lblSearch.setText("");
        cboKho.setSelectedIndex(0);
        modelHDCT.setRowCount(0);
        fillTablePr();
        lblSearchKH.setText("");
        lblRsTongGia.setText("");
        indexList = -1;
        indexKH = -1;
        indexHDCT = -1;
        btnInHoaDon.setEnabled(false);
    }
    //-------------------------------------------------------------------------------------------------//
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
        btnInHoaDon = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        btnThemVaoHoaDon = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblHoaDonChiTiet = new javax.swing.JTable();
        lblKho = new javax.swing.JLabel();
        lblTongGia = new javax.swing.JLabel();
        btnXoaTatCa = new javax.swing.JButton();
        lblSearchKH = new javax.swing.JLabel();
        txtSearchKH = new javax.swing.JTextField();
        lblKhachHang = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblKH = new javax.swing.JTable();
        btnTaoMoiKH = new javax.swing.JButton();
        cboKho = new javax.swing.JComboBox<>();
        lblRsTongGia = new javax.swing.JLabel();
        lblKhachHang1 = new javax.swing.JLabel();
        btnOK = new javax.swing.JButton();
        btnOKKH = new javax.swing.JButton();

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
                "Mã MH", "HSX", "Tên MH", "RAM", "Dung lượng", "Màu", "Quốc gia", "Giá bán sỉ", "Số lượng"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
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
        lblHoaDonCT.setText("HÓA ĐƠN CHI TIẾT");

        btnInHoaDon.setBackground(new java.awt.Color(34, 116, 173));
        btnInHoaDon.setFont(new java.awt.Font("Baloo 2", 1, 18)); // NOI18N
        btnInHoaDon.setForeground(new java.awt.Color(255, 255, 255));
        btnInHoaDon.setText("In hóa đơn");
        btnInHoaDon.setEnabled(false);
        btnInHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInHoaDonActionPerformed(evt);
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
        btnThemVaoHoaDon.setText("Thêm vào hóa đơn");
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
                "Mã mặt hàng", "Giá bán sỉ", "Số lượng"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true
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

        lblSearchKH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mobilez/icon/search_32px.png"))); // NOI18N

        txtSearchKH.setBackground(new java.awt.Color(34, 116, 173));
        txtSearchKH.setForeground(new java.awt.Color(255, 255, 255));
        txtSearchKH.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)));
        txtSearchKH.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKHKeyReleased(evt);
            }
        });

        lblKhachHang.setFont(new java.awt.Font("Baloo Chettan 2", 1, 14)); // NOI18N
        lblKhachHang.setForeground(new java.awt.Color(255, 255, 255));
        lblKhachHang.setText("Khách hàng");

        tblKH.setBackground(new java.awt.Color(34, 116, 173));
        tblKH.setFont(new java.awt.Font("Baloo 2", 0, 13)); // NOI18N
        tblKH.setForeground(new java.awt.Color(255, 255, 255));
        tblKH.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                " Mã khách hàng", "Tên khách hàng", "Địa chỉ", "Số điện thoại"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblKH.setGridColor(new java.awt.Color(255, 255, 255));
        tblKH.setRowHeight(25);
        tblKH.setSelectionBackground(new java.awt.Color(51, 51, 51));
        tblKH.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblKH.setShowGrid(true);
        tblKH.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKHMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblKH);

        btnTaoMoiKH.setBackground(new java.awt.Color(34, 116, 173));
        btnTaoMoiKH.setFont(new java.awt.Font("Baloo 2", 1, 12)); // NOI18N
        btnTaoMoiKH.setForeground(new java.awt.Color(255, 255, 255));
        btnTaoMoiKH.setText("Tạo mới khách hàng");
        btnTaoMoiKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoMoiKHActionPerformed(evt);
            }
        });

        cboKho.setBackground(new java.awt.Color(34, 116, 173));
        cboKho.setForeground(new java.awt.Color(255, 255, 255));
        cboKho.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboKhoItemStateChanged(evt);
            }
        });

        lblRsTongGia.setFont(new java.awt.Font("Baloo Chettan 2", 1, 14)); // NOI18N
        lblRsTongGia.setForeground(new java.awt.Color(255, 255, 255));

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

        btnOKKH.setBackground(new java.awt.Color(34, 116, 173));
        btnOKKH.setFont(new java.awt.Font("Baloo 2", 1, 12)); // NOI18N
        btnOKKH.setForeground(new java.awt.Color(255, 255, 255));
        btnOKKH.setText("Đồng ý");
        btnOKKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOKKHActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 666, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnOKKH)
                                .addGap(8, 8, 8)
                                .addComponent(btnTaoMoiKH))
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
                            .addComponent(btnThemVaoHoaDon)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblSearchKH)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtSearchKH, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblKhachHang)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblHoaDonCT, javax.swing.GroupLayout.PREFERRED_SIZE, 618, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btnXoaTatCa)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnXoa)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnOK)))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 618, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblTongGia, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(lblRsTongGia, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jSeparator2, javax.swing.GroupLayout.DEFAULT_SIZE, 1, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnInHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 376, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(105, 105, 105))))
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnThemVaoHoaDon)
                        .addComponent(btnXoaTatCa)
                        .addComponent(btnXoa)
                        .addComponent(lblTongGia)
                        .addComponent(btnOK))
                    .addComponent(lblRsTongGia, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtSearchKH, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblKhachHang))
                    .addComponent(lblSearchKH, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnInHoaDon))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTaoMoiKH)
                    .addComponent(btnOKKH))
                .addContainerGap(32, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnOK, btnXoa, btnXoaTatCa});

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

    private void btnInHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInHoaDonActionPerformed
        // TODO add your handling code here:
        if (indexKH==-1) {
            Msgbox.alert(null, "Bạn chưa chọn khách hàng!");
            return;
        }
        this.insertHDBS();
        this.insertCTHDBS();
        Msgbox.alert(null, "In hóa đơn thành công!");
        this.resetAll();
    }//GEN-LAST:event_btnInHoaDonActionPerformed

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
            lblRsTongGia.setText("");
            btnInHoaDon.setEnabled(false);
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
            lblRsTongGia.setText("");
            btnInHoaDon.setEnabled(false);
        }
    }//GEN-LAST:event_btnXoaTatCaActionPerformed

    private void cboKhoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboKhoItemStateChanged
        // TODO add your handling code here:
        this.fillTablePr();
    }//GEN-LAST:event_cboKhoItemStateChanged

    private void btnOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOKActionPerformed
        // TODO add your handling code here:
        if (tblHoaDonChiTiet.getRowCount()<=0) {
            Msgbox.alert(null, "Danh sách trống!");
            return;
        }
        if (chkSLNotNull()) {
            Msgbox.alert(null, "Bạn chưa nhập số lượng!");
            return;
        }
        if (chkSLIsNum()) {
            Msgbox.alert(null, "Số lượng phải là số!");
            return;
        }
        if (chkSLLonHon0()) {
            Msgbox.alert(null, "Số lượng phải lớn hơn 0!");
            return;
        }
        lblRsTongGia.setText(this.getTongGia());
        btnInHoaDon.setEnabled(true);
        
    }//GEN-LAST:event_btnOKActionPerformed

    private void btnTaoMoiKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoMoiKHActionPerformed
        // TODO add your handling code here:
        new FrmNewKH(null, true).setVisible(true);
    }//GEN-LAST:event_btnTaoMoiKHActionPerformed

    private void btnOKKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOKKHActionPerformed
        // TODO add your handling code here:
        indexKH = tblKH.getSelectedRow();
    }//GEN-LAST:event_btnOKKHActionPerformed

    private void tblKHMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKHMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblKHMouseClicked

    private void txtSearchKHKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKHKeyReleased
        // TODO add your handling code here:
        this.searchKH();
    }//GEN-LAST:event_txtSearchKHKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnInHoaDon;
    private javax.swing.JButton btnOK;
    private javax.swing.JButton btnOKKH;
    private javax.swing.JButton btnTaoMoiKH;
    private javax.swing.JButton btnThemVaoHoaDon;
    private javax.swing.JButton btnXoa;
    private javax.swing.JButton btnXoaTatCa;
    private javax.swing.JComboBox<String> cboKho;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lblDSMH;
    private javax.swing.JLabel lblHoaDonCT;
    private javax.swing.JLabel lblKhachHang;
    private javax.swing.JLabel lblKhachHang1;
    private javax.swing.JLabel lblKho;
    private javax.swing.JLabel lblRsTongGia;
    private javax.swing.JLabel lblSearch;
    private javax.swing.JLabel lblSearchKH;
    private javax.swing.JLabel lblTongGia;
    private javax.swing.JTable tblHoaDonChiTiet;
    private javax.swing.JTable tblKH;
    private javax.swing.JTable tblList;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtSearchKH;
    // End of variables declaration//GEN-END:variables
}
