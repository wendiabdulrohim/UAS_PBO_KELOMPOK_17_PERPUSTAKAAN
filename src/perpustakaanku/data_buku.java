/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package perpustakaanku;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import static jdk.nashorn.internal.runtime.Debug.id;

/**
 *
 * @author Administrator
 */
public class data_buku extends javax.swing.JFrame {

    /**
     * Creates new form data_anggota
     */
    public data_buku() {
        initComponents();
        load_data();
        KODEOtomatis();
       
        
        BEdit.setEnabled(false);
        BDelete.setEnabled(false);
        
    }
    
    private void load_data()
    {
        Connection dbkon=databasekoneksi.koneksiDb();
        Object header[]={"KODE","JUDUL","NAMA","TAHUN","PENERBIT", "STATUS"};
        DefaultTableModel data=new DefaultTableModel(null,header);
        TableBuku.setModel(data);
        String sql_data="SELECT * FROM buku";
        try 
        {
            Statement st=dbkon.createStatement();
            ResultSet rs=st.executeQuery(sql_data);
            while(rs.next())
            {
                String d1=rs.getString(1);
                String d2=rs.getString(2);
                String d3=rs.getString(3);
                String d4=rs.getString(4);
                String d5=rs.getString(5);
                String d6=rs.getString(6);
                
                String d[]={d1,d2,d3,d4,d5,d6};
                data.addRow(d);
            }
        }
        catch (Exception e)
                {
                    JOptionPane.showMessageDialog(null, e);
                }
    }
    
    //ID OTOMATIS
    private void KODEOtomatis()
    {
        try 
        {
             Connection dbkon=databasekoneksi.koneksiDb();
             Statement st=dbkon.createStatement();
             String sql_kode="SELECT * FROM buku order by kode desc";
             ResultSet rs=st.executeQuery(sql_kode);
             if(rs.next())
             {
                 String kode=rs.getString("kode").substring(1);
                 String AN=""+(Integer.parseInt(kode)+1);
                 String Nol="";
                 if(AN.length()==1) //jika buku kode B0001
                 {
                     Nol="000";
                 }
                 else if(AN.length()==2) //jika buku kode B0010
                 {
                     Nol="00";
                 }
                 else if(AN.length()==3) //jika buku kodeB0100
                 {
                     Nol="0";
                 }
                 KODE.setText("B"+Nol+AN);
             }
             else
             {
                 KODE.setText("B0001");
             }
        }
        catch (Exception e)
                {
                    JOptionPane.showMessageDialog(null, e);
                }
    }
    
    //=================INPUT========================//
    private void input_data()
    {   
        try
        {
            Connection dbkon=databasekoneksi.koneksiDb();
            Statement st=dbkon.createStatement();
            
            String sql="INSERT INTO buku values('"+KODE.getText()
                    +"','"+JUDUL.getText()
                    +"','"+NAMA.getText()
                    +"','"+TAHUN.getText()
                    +"','"+PENERBIT.getText()
                    +"','"+STATUS.getSelectedItem()
                    +"')";
            st.execute(sql);
            JOptionPane.showMessageDialog(null, "Data Buku Berhasil Dimasukan");
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    //RESET DATA
    public void clear()
    {
        JUDUL.setText("");
        NAMA.setText("");
        TAHUN.setText("");
        PENERBIT.setText("");
        STATUS.setSelectedItem("ADA");
    }
    
    //edit data
    public void update()
    {
        try 
        {
            Connection dbkon=databasekoneksi.koneksiDb();
            Statement st=dbkon.createStatement();
    
            String sql_update="UPDATE buku set judul='"+JUDUL.getText()
                    +"',nama='"+NAMA.getText()
                    +"',tahun='"+TAHUN.getText()
                    +"',penerbit='"+PENERBIT.getText()
                    +"',status='"+STATUS.getSelectedItem()
                    +"'WHERE kode='"+KODE.getText()+"'";
            st.execute(sql_update);
            JOptionPane.showMessageDialog(null, "Data Berhasil Di Update");
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
     //delete data
    private void delete()
    {
        try
        {
            Connection dbkon=databasekoneksi.koneksiDb();
            Statement st=dbkon.createStatement();
            String sql_delete="DELETE from buku WHERE "
                    +"kode='"+KODE.getText()+"'";
            st.executeUpdate(sql_delete);
            JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus");
            
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
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

        JK = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        BKeluar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        KODE = new javax.swing.JTextField();
        JUDUL = new javax.swing.JTextField();
        NAMA = new javax.swing.JTextField();
        TAHUN = new javax.swing.JTextField();
        BInput = new javax.swing.JButton();
        BEdit = new javax.swing.JButton();
        BDelete = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        TableBuku = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        PENERBIT = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        STATUS = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Clarendon Blk BT", 1, 24)); // NOI18N
        jLabel1.setText("KELOLA DATA BUKU PERPUSTAKAAN");

        BKeluar.setFont(new java.awt.Font("Clarendon Blk BT", 0, 18)); // NOI18N
        BKeluar.setText("Keluar");
        BKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BKeluarActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Clarendon Blk BT", 0, 18)); // NOI18N
        jLabel2.setText("Kode Buku");

        jLabel3.setFont(new java.awt.Font("Clarendon Blk BT", 0, 18)); // NOI18N
        jLabel3.setText("Judul");

        jLabel4.setFont(new java.awt.Font("Clarendon Blk BT", 0, 18)); // NOI18N
        jLabel4.setText("Nama Pengarang");

        jLabel9.setFont(new java.awt.Font("Clarendon Blk BT", 0, 18)); // NOI18N
        jLabel9.setText("Tahun");

        KODE.setFont(new java.awt.Font("Clarendon Blk BT", 0, 18)); // NOI18N
        KODE.setEnabled(false);
        KODE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                KODEActionPerformed(evt);
            }
        });

        JUDUL.setFont(new java.awt.Font("Clarendon Blk BT", 0, 18)); // NOI18N

        NAMA.setFont(new java.awt.Font("Clarendon Blk BT", 0, 18)); // NOI18N

        TAHUN.setFont(new java.awt.Font("Clarendon Blk BT", 0, 18)); // NOI18N

        BInput.setFont(new java.awt.Font("Clarendon Blk BT", 0, 18)); // NOI18N
        BInput.setText("INPUT");
        BInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BInputActionPerformed(evt);
            }
        });

        BEdit.setFont(new java.awt.Font("Clarendon Blk BT", 0, 18)); // NOI18N
        BEdit.setText("EDIT");
        BEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BEditActionPerformed(evt);
            }
        });

        BDelete.setFont(new java.awt.Font("Clarendon Blk BT", 0, 18)); // NOI18N
        BDelete.setText("DELETE");
        BDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BDeleteActionPerformed(evt);
            }
        });

        TableBuku.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        TableBuku.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableBukuMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TableBuku);

        jLabel11.setFont(new java.awt.Font("Clarendon Blk BT", 0, 18)); // NOI18N
        jLabel11.setText("Penerbit");

        PENERBIT.setFont(new java.awt.Font("Clarendon Blk BT", 0, 18)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Clarendon Blk BT", 0, 18)); // NOI18N
        jLabel5.setText("Status");

        STATUS.setFont(new java.awt.Font("Clarendon Blk BT", 0, 18)); // NOI18N
        STATUS.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ADA", "TIDAK ADA" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(jLabel9)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(BKeluar)
                            .addComponent(jLabel11))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(47, 47, 47)
                                .addComponent(BInput)
                                .addGap(18, 18, 18)
                                .addComponent(BEdit)
                                .addGap(23, 23, 23)
                                .addComponent(BDelete))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(KODE)
                                    .addComponent(PENERBIT)
                                    .addComponent(TAHUN)
                                    .addComponent(NAMA)
                                    .addComponent(JUDUL, javax.swing.GroupLayout.DEFAULT_SIZE, 331, Short.MAX_VALUE)
                                    .addComponent(STATUS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addComponent(jLabel5))
                .addGap(28, 28, 28)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 705, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(249, 249, 249)
                .addComponent(jLabel1)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(KODE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(JUDUL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(NAMA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(TAHUN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(PENERBIT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(STATUS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(BKeluar)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(BInput)
                                .addComponent(BEdit))
                            .addComponent(BDelete)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 527, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BKeluarActionPerformed
        // TODO add your handling code here:
        int keluar;
                keluar = JOptionPane.showOptionDialog(this,
                "Keluar dari Kelola Data Buku ?", 
                "Keluar",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE, null,null,null);
        if(keluar==JOptionPane.YES_NO_OPTION)
        {
            new pustakawan().show();
            this.dispose();
        }
    }//GEN-LAST:event_BKeluarActionPerformed

    private void BInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BInputActionPerformed
        // TODO add your handling code here:
        int simpan=JOptionPane.showOptionDialog(this,
                "Apakah Data Sudah Benar ? Simpan",
                "Simpan Data",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,null,null,null);
        if(simpan==JOptionPane.YES_OPTION)
        {
        input_data();
        load_data();
        clear();
        KODEOtomatis();
        }
    }//GEN-LAST:event_BInputActionPerformed

    private void TableBukuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableBukuMouseClicked
        // TODO add your handling code here:
        int bar=TableBuku.getSelectedRow();
        String a=TableBuku.getValueAt(bar, 0).toString();
        String b=TableBuku.getValueAt(bar, 1).toString();
        String c=TableBuku.getValueAt(bar, 2).toString();
        String d=TableBuku.getValueAt(bar, 3).toString();
        String e=TableBuku.getValueAt(bar, 4).toString();
        String f=TableBuku.getValueAt(bar, 5).toString();
        
        KODE.setText(a);
        JUDUL.setText(b);
        NAMA.setText(c);
        TAHUN.setText(d);
        PENERBIT.setText(e);
         //Status
        if("ADA".equals(f))
        {
            STATUS.setSelectedItem(f);
        }
        else
        {
            STATUS.setSelectedItem(f);
        }
        
        //input disable
        BInput.setEnabled(false);
        BEdit.setEnabled(true);
        BDelete.setEnabled(true);
        
    }//GEN-LAST:event_TableBukuMouseClicked

    private void BEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BEditActionPerformed
        // TODO add your handling code here:
        int update=JOptionPane.showOptionDialog(this,
                "Apakah Data Akan Di Ubah ?",
                "Ubah Data",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,null,null,null);
        if(update==JOptionPane.YES_OPTION)
        {
        update();
        clear();
        load_data();
        KODEOtomatis();
         //input
        BInput.setEnabled(true);
        BEdit.setEnabled(false);
        BDelete.setEnabled(false);
        }
    }//GEN-LAST:event_BEditActionPerformed

    private void KODEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_KODEActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_KODEActionPerformed

    private void BDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BDeleteActionPerformed
        // TODO add your handling code here:
         int delete=JOptionPane.showOptionDialog(this,
                "Apakah Data Akan Di Hapus ?",
                "Hapus Data",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,null,null,null);
        if(delete==JOptionPane.YES_OPTION)
        {
        delete();
        clear();
        load_data();
        KODEOtomatis();
         //input
        BInput.setEnabled(true);
        BEdit.setEnabled(false);
        BDelete.setEnabled(false);
    }//GEN-LAST:event_BDeleteActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(data_buku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(data_buku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(data_buku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(data_buku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new data_buku().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BDelete;
    private javax.swing.JButton BEdit;
    private javax.swing.JButton BInput;
    private javax.swing.JButton BKeluar;
    private javax.swing.ButtonGroup JK;
    private javax.swing.JTextField JUDUL;
    private javax.swing.JTextField KODE;
    private javax.swing.JTextField NAMA;
    private javax.swing.JTextField PENERBIT;
    private javax.swing.JComboBox<String> STATUS;
    private javax.swing.JTextField TAHUN;
    private javax.swing.JTable TableBuku;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
