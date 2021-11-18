/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mobilez.ui;

/**
 *
 * @author Admin
 */
public class ManHinhChao extends java.awt.Dialog {

    /**
     * Creates new form ManHinhChao
     */
    public ManHinhChao(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        init();
    }
    private void init(){
        setLocationRelativeTo(null);
        Thread t=new Thread(){
                int i=-1;
          @Override
          public void run(){
              while(true){
                  try {
                      i++;
                      prg.setValue(i);
                      if(i==20)lblStatus.setText("   Đang khởi tạo cái modun...");
                      if(i==50)lblStatus.setText("   Đang kết nối CSDL...");
                      if(i==90)lblStatus.setText("   Chuẩn bị vào chương trình...");
                      if(i==100){
                         ManHinhChao.this.dispose();   //đóng ChaoJDialog
                         break; 
                      }
                      Thread.sleep(20);   //thread tạm dừng hoạt động trong 20 ms
                  } catch (InterruptedException ex) {
                      break;
                  }
              }
          }  
        };
        t.start();       //thread bắt đầu hoạt động
    
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        prg = new javax.swing.JProgressBar();
        lblStatus = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(60, 141, 19));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel2.setBackground(new java.awt.Color(60, 141, 19));
        jPanel2.setLayout(new java.awt.BorderLayout());

        prg.setBackground(new java.awt.Color(0, 102, 204));
        prg.setMaximumSize(new java.awt.Dimension(32767, 17));
        prg.setMinimumSize(new java.awt.Dimension(10, 17));
        prg.setPreferredSize(new java.awt.Dimension(146, 17));
        prg.setRequestFocusEnabled(false);
        prg.setStringPainted(true);
        jPanel2.add(prg, java.awt.BorderLayout.CENTER);

        lblStatus.setText("Khởi động ứng dụng...");
        jPanel2.add(lblStatus, java.awt.BorderLayout.PAGE_START);

        jPanel1.add(jPanel2, java.awt.BorderLayout.PAGE_END);

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mobilez/icon/logoM_128px.png"))); // NOI18N
        jPanel1.add(jLabel3, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 307, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Closes the dialog
     */
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        setVisible(false);
        dispose();
    }//GEN-LAST:event_closeDialog

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ManHinhChao dialog = new ManHinhChao(new java.awt.Frame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JProgressBar prg;
    // End of variables declaration//GEN-END:variables
}
