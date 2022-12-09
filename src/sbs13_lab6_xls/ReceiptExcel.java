package sbs13_lab6_xls;

import java.awt.Cursor;
import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

public class ReceiptExcel extends javax.swing.JFrame {
    private static final long serialVersionUID = 1L;

    class TThread1 extends Thread { // Поток запуска MS Excel

        public void run() {
            
            String dir = new File(".").getAbsoluteFile().getParentFile().getAbsolutePath()
                    + System.getProperty("file.separator"); // Текущий катаолог
            try {
                modifData(dir + "receipt_template.xls", dir + "receipt.xls", jTextField_FIO.getText(),
                        jTextField_FIO2.getText(), jTextField_Date.getText()); // Вызов метода создания отчета
                if (System.getProperty("os.name").equals("Linux")
                        && System.getProperty("java.vendor").startsWith("Red Hat")) {
                    new ProcessBuilder("xdg-open", dir + "receipt.xls").start();
                } else {
                    Desktop.getDesktop().open(new File(dir + "receipt.xls")); // Запуск отчета в MS Excel
                }
            } catch (Exception ex) {
                System.err.println("Error modifData!");
                ex.printStackTrace();
            }
            setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }
    }

    // Метод создания отчета
    private void modifData(String inputFileName, String outputFileName, String FIO, String FIO2,
            String Date) throws IOException {
        HSSFWorkbook wb = new HSSFWorkbook(new POIFSFileSystem(new FileInputStream(inputFileName))); // Документ MS Excel
        HSSFSheet sheet = wb.getSheetAt(0); // Первый лист в документе MS Excel
        sheet.getRow(15).getCell(3).setCellValue(FIO);
        sheet.getRow(23).getCell(2).setCellValue(FIO2);
        sheet.getRow(23).getCell(7).setCellValue(Date);
        
        try (FileOutputStream fileOut = new FileOutputStream(outputFileName)) {
            wb.write(fileOut);
        }
    }

    public ReceiptExcel() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jTextField_FIO = new javax.swing.JTextField();
        jTextField_FIO2 = new javax.swing.JTextField();
        jTextField_Date = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Чек в Excel");
        setResizable(false);
        getContentPane().setLayout(null);

        jButton1.setText("в Excel");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(560, 330, 66, 22);

        jTextField_FIO.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        getContentPane().add(jTextField_FIO);
        jTextField_FIO.setBounds(160, 210, 380, 30);

        jTextField_FIO2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTextField_FIO2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        getContentPane().add(jTextField_FIO2);
        jTextField_FIO2.setBounds(90, 320, 240, 20);

        jTextField_Date.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTextField_Date.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        getContentPane().add(jTextField_Date);
        jTextField_Date.setBounds(380, 320, 160, 20);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sbs13_lab6_xls/scr.png"))); // NOI18N
        getContentPane().add(jLabel2);
        jLabel2.setBounds(0, 0, 710, 420);

        setSize(new java.awt.Dimension(721, 439));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        new TThread1().start();
    }//GEN-LAST:event_jButton1ActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ReceiptExcel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ReceiptExcel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ReceiptExcel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ReceiptExcel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        
        //</editor-fold>
        

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ReceiptExcel().setVisible(true);

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField jTextField_Date;
    private javax.swing.JTextField jTextField_FIO;
    private javax.swing.JTextField jTextField_FIO2;
    // End of variables declaration//GEN-END:variables
}
