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

/**
 *
 * @author Administrator
 */
public class data_peminjaman extends javax.swing.JFrame {

    /**
     * Creates new form data_peminjaman
     */
    public data_peminjaman() {
        initComponents();
        load_data();
        IDOtomatis();
        LoadNim();
        LoadNama();
        LoadBuku();
        
        BEdit.setEnabled(false);
        BDelete.setEnabled(false);
        
    }
    
    private void load_data()
    {
        Connection dbkon=databasekoneksi.koneksiDb();
        Object header[]={"ID","NIM","NAMA","JUDUL","TANGGALPINJAM"};
        DefaultTableModel data=new DefaultTableModel(null,header);
        TablePeminjaman.setModel(data);
        String sql_data="SELECT * FROM peminjaman";
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
                
                String d[]={d1,d2,d3,d4,d5};
                data.addRow(d);
            }
        }
        catch (Exception e)
                {
                    JOptionPane.showMessageDialog(null, e);
                }
    }
    
    //ID OTOMATIS
    private void IDOtomatis()
    {
        try 
        {
             Connection dbkon=databasekoneksi.koneksiDb();
             Statement st=dbkon.createStatement();
             String sql_id="SELECT * FROM peminjaman order by id desc";
             ResultSet rs=st.executeQuery(sql_id);
             if(rs.next())
             {
                 String id=rs.getString("id").substring(1);
                 String AN=""+(Integer.parseInt(id)+1);
                 String Nol="";
                 if(AN.length()==1) //jika peminjaman id C0001
                 {
                     Nol="000";
                 }
                 else if(AN.length()==2) //jika id peminjaman C0010
                 {
                     Nol="00";
                 }
                 else if(AN.length()==3) //jika id peminjaman C0100
                 {
                     Nol="0";
                 }
                 ID.setText("C"+Nol+AN);
             }
             else
             {
                 ID.setText("C0001");
             }
        }
        catch (Exception e)
                {
                    JOptionPane.showMessageDialog(null, e);
                }
    }
    
    //=================LOAD NIM COMBO ANGGOTA==========//
    public void LoadNim()
    {
        try
        {
            Connection dbkon=databasekoneksi.koneksiDb();
            Statement st=dbkon.createStatement();
            String sql_nim="SELECT * FROM anggota";
            ResultSet rs=st.executeQuery(sql_nim);
            while(rs.next())
            {
                NIM.addItem(rs.getString("id"));
            }
            rs.close();
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    //=================NAMA NIM ANGGOTA================/
    public void NomorNim()
    {
        try 
        {
            Connection dbkon=databasekoneksi.koneksiDb();
            Statement st=dbkon.createStatement();
            String sql_nim="SELECT nim FROM anggota WHERE id='"+NIM.getSelectedItem()+"'";
            ResultSet rs=st.executeQuery(sql_nim);
            while(rs.next())
            {
                NNIM.setText(rs.getString("nim"));
            }
        }
        
        catch(Exception e)
        {
            
        }
    }
    
    //=================LOAD NAMA COMBO ANGGOTA ==========//
    public void LoadNama()
    {
        try
        {
            Connection dbkon=databasekoneksi.koneksiDb();
            Statement st=dbkon.createStatement();
            String sql_nama="SELECT id FROM anggota";
            ResultSet rs=st.executeQuery(sql_nama);
            while(rs.next())
            {
                NAMA.addItem(rs.getString("id"));
            }
            rs.close();
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    //=================NAMA Peminjam ANGGOTA================/
    public void NamaPeminjam()
    {
        try 
        {
            Connection dbkon=databasekoneksi.koneksiDb();
            Statement st=dbkon.createStatement();
            String sql_peminjam="SELECT nama FROM anggota WHERE id ='"+NAMA.getSelectedItem()+"'";
            ResultSet rs=st.executeQuery(sql_peminjam);
            while(rs.next())
            {
                NNAMA.setText(rs.getString("nama"));
            }
        }
        
        catch(Exception e)
        {
            
        }
    }
    
    //=================LOAD BUKU==========//
    public void LoadBuku()
    {
        try
        {
            Connection dbkon=databasekoneksi.koneksiDb();
            Statement st=dbkon.createStatement();
            String sql_buku="SELECT kode FROM buku";
            ResultSet rs=st.executeQuery(sql_buku);
            while(rs.next())
            {
                JUDUL.addItem(rs.getString("kode"));
            }
            rs.close();
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    //=================NAMA Buku================/
    public void NamaBuku()
    {
        try 
        {
            Connection dbkon=databasekoneksi.koneksiDb();
            Statement st=dbkon.createStatement();
            String sql_buku="SELECT judul FROM buku WHERE kode ='"+JUDUL.getSelectedItem()+"'";
            ResultSet rs=st.executeQuery(sql_buku);
            while(rs.next())
            {
                NJUDUL.setText(rs.getString("judul"));
            }
        }
        
        catch(Exception e)
        {
            
        }
    } 
    
    //=================INPUT========================//
    private void input_data()
    {   
        try
        {
            Connection dbkon=databasekoneksi.koneksiDb();
            Statement st=dbkon.createStatement();
            
            String sql="INSERT INTO peminjaman values('"+ID.getText()
                    +"','"+NIM.getSelectedItem()
                    +"','"+NAMA.getSelectedItem()
                    +"','"+JUDUL.getSelectedItem()
                    +"','"+TANGGALPINJAM.getText()
                    +"')";
            st.execute(sql);
            JOptionPane.showMessageDialog(null, "Data Pinjam Berhasil Dimasukan");
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    //RESET DATA
    public void clear()
    {
        NIM.setSelectedItem("");
        NAMA.setSelectedItem("");
        JUDUL.setSelectedItem("");
        TANGGALPINJAM.setText("");
    }
    
    //edit data
    public void update()
    {
        try 
        {
            Connection dbkon=databasekoneksi.koneksiDb();
            Statement st=dbkon.createStatement();
            
            String sql_update="UPDATE peminjaman set nim_anggota='"
                    +"',nim_anggota='"+NIM.getSelectedItem()
                    +"',nama_anggota='"+NAMA.getSelectedItem()
                    +"',judul_buku='"+JUDUL.getSelectedItem()
                    +"',tanggal_pinjam='"+TANGGALPINJAM.getText()
                    +"'WHERE id='"+ID.getText()+"'";
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
            String sql_delete="DELETE from peminjaman WHERE "
                    +"id='"+ID.getText()+"'";
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
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        ID = new javax.swing.JTextField();
        NIM = new javax.swing.JComboBox<>();
        NAMA = new javax.swing.JComboBox<>();
        JUDUL = new javax.swing.JComboBox<>();
        TANGGALPINJAM = new javax.swing.JTextField();
        NNIM = new javax.swing.JTextField();
        NNAMA = new javax.swing.JTextField();
        BInput = new javax.swing.JButton();
        BEdit = new javax.swing.JButton();
        BDelete = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablePeminjaman = new javax.swing.JTable();
        NJUDUL = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Clarendon Blk BT", 1, 24)); // NOI18N
        jLabel1.setText("KELOLA DATA PEMINJAMAN PERPUSTAKAAN");

        BKeluar.setFont(new java.awt.Font("Clarendon Blk BT", 0, 18)); // NOI18N
        BKeluar.setText("Keluar");
        BKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BKeluarActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Clarendon Blk BT", 0, 18)); // NOI18N
        jLabel2.setText("ID PEMINJAMAN");

        jLabel6.setFont(new java.awt.Font("Clarendon Blk BT", 0, 18)); // NOI18N
        jLabel6.setText("Nim");

        jLabel7.setFont(new java.awt.Font("Clarendon Blk BT", 0, 18)); // NOI18N
        jLabel7.setText("Nama Anggota");

        jLabel8.setFont(new java.awt.Font("Clarendon Blk BT", 0, 18)); // NOI18N
        jLabel8.setText("Judul Buku");

        jLabel9.setFont(new java.awt.Font("Clarendon Blk BT", 0, 18)); // NOI18N
        jLabel9.setText("Tanggal Pinjam");

        ID.setFont(new java.awt.Font("Clarendon Blk BT", 0, 18)); // NOI18N
        ID.setEnabled(false);

        NIM.setFont(new java.awt.Font("Clarendon Blk BT", 0, 18)); // NOI18N
        NIM.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                NIMMouseClicked(evt);
            }
        });
        NIM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NIMActionPerformed(evt);
            }
        });

        NAMA.setFont(new java.awt.Font("Clarendon Blk BT", 0, 18)); // NOI18N
        NAMA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NAMAActionPerformed(evt);
            }
        });

        JUDUL.setFont(new java.awt.Font("Clarendon Blk BT", 0, 18)); // NOI18N
        JUDUL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JUDULActionPerformed(evt);
            }
        });

        TANGGALPINJAM.setFont(new java.awt.Font("Clarendon Blk BT", 0, 18)); // NOI18N

        NNIM.setEditable(false);
        NNIM.setFont(new java.awt.Font("Clarendon Blk BT", 0, 18)); // NOI18N
        NNIM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NNIMActionPerformed(evt);
            }
        });

        NNAMA.setEditable(false);
        NNAMA.setFont(new java.awt.Font("Clarendon Blk BT", 0, 18)); // NOI18N
        NNAMA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NNAMAActionPerformed(evt);
            }
        });

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

        TablePeminjaman.setModel(new javax.swing.table.DefaultTableModel(
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
        TablePeminjaman.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TablePeminjamanMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TablePeminjaman);

        NJUDUL.setEditable(false);
        NJUDUL.setFont(new java.awt.Font("Clarendon Blk BT", 0, 18)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(152, 152, 152)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(BKeluar)
                        .addGap(90, 90, 90)
                        .addComponent(BInput)
                        .addGap(18, 18, 18)
                        .addComponent(BEdit)
                        .addGap(23, 23, 23)
                        .addComponent(BDelete))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ID)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(NIM, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(NAMA, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(NNIM, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(NNAMA, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel7))
                                .addGap(51, 51, 51)
                                .addComponent(JUDUL, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(NJUDUL, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(40, 40, 40)
                        .addComponent(TANGGALPINJAM, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(30, 30, 30)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 705, Short.MAX_VALUE))
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
                            .addComponent(ID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(NIM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(NNIM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(NAMA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(NNAMA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(JUDUL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(NJUDUL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TANGGALPINJAM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(BKeluar)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(BInput)
                                .addComponent(BEdit))
                            .addComponent(BDelete))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 527, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE))
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
                "Keluar dari Kelola Data Peminjaman ?", 
                "Keluar",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE, null,null,null);
        if(keluar==JOptionPane.YES_NO_OPTION)
        {
            new pustakawan().show();
            this.dispose();
        }
    }//GEN-LAST:event_BKeluarActionPerformed

    private void NIMMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NIMMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_NIMMouseClicked

    private void NIMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NIMActionPerformed
        // TODO add your handling code here:
        NomorNim();
    }//GEN-LAST:event_NIMActionPerformed

    private void NAMAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NAMAActionPerformed
        // TODO add your handling code here:
         NamaPeminjam();
    }//GEN-LAST:event_NAMAActionPerformed

    private void JUDULActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JUDULActionPerformed
        // TODO add your handling code here:
        NamaBuku();
    }//GEN-LAST:event_JUDULActionPerformed

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
        IDOtomatis();
        }
    }//GEN-LAST:event_BInputActionPerformed

    private void TablePeminjamanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablePeminjamanMouseClicked
        // TODO add your handling code here:
        int bar=TablePeminjaman.getSelectedRow();
        String a=TablePeminjaman.getValueAt(bar, 0).toString();
        String b=TablePeminjaman.getValueAt(bar, 1).toString();
        String c=TablePeminjaman.getValueAt(bar, 2).toString();
        String d=TablePeminjaman.getValueAt(bar, 3).toString();
        String e=TablePeminjaman.getValueAt(bar, 4).toString();
        
        
        ID.setText(a);
        NIM.setSelectedItem(b);
        NAMA.setSelectedItem(c);
        JUDUL.setSelectedItem(d);
        TANGGALPINJAM.setText(e);
        
        //input disable
        BInput.setEnabled(false);
        BEdit.setEnabled(true);
        BDelete.setEnabled(true);
        
    }//GEN-LAST:event_TablePeminjamanMouseClicked

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
        IDOtomatis();
         //input
        BInput.setEnabled(true);
        BEdit.setEnabled(false);
        BDelete.setEnabled(false);
        }
    }//GEN-LAST:event_BEditActionPerformed

    private void NNIMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NNIMActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NNIMActionPerformed

    private void NNAMAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NNAMAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NNAMAActionPerformed

    private void BDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BDeleteActionPerformed
        // TODO add your handling code here:
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
        IDOtomatis();
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
            java.util.logging.Logger.getLogger(kepalapustakawan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(kepalapustakawan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(kepalapustakawan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(kepalapustakawan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new data_peminjaman().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BDelete;
    private javax.swing.JButton BEdit;
    private javax.swing.JButton BInput;
    private javax.swing.JButton BKeluar;
    private javax.swing.JTextField ID;
    private javax.swing.ButtonGroup JK;
    private javax.swing.JComboBox<String> JUDUL;
    private javax.swing.JComboBox<String> NAMA;
    private javax.swing.JComboBox<String> NIM;
    private javax.swing.JTextField NJUDUL;
    private javax.swing.JTextField NNAMA;
    private javax.swing.JTextField NNIM;
    private javax.swing.JTextField TANGGALPINJAM;
    private javax.swing.JTable TablePeminjaman;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
