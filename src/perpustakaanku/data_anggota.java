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
public class data_anggota extends javax.swing.JFrame {

    /**
     * Creates new form data_anggota
     */
    public data_anggota() {
        initComponents();
        load_data();
        IDOtomatis();
        LoadSemester();
        LoadJurusan();
        LoadKelas();
        
        BEdit.setEnabled(false);
        BDelete.setEnabled(false);
        
    }
    
    private void load_data()
    {
        Connection dbkon=databasekoneksi.koneksiDb();
        Object header[]={"ID","NIM","NAMA","JK","SEMESTER","JURUSAN","KELAS","NOHP","STATUS"};
        DefaultTableModel data=new DefaultTableModel(null,header);
        TableAnggota.setModel(data);
        String sql_data="SELECT * FROM anggota";
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
                String d7=rs.getString(7);
                String d8=rs.getString(8);
                String d9=rs.getString(9);
                
                String d[]={d1,d2,d3,d4,d5,d6,d7,d8,d9};
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
             String sql_id="SELECT * FROM anggota order by id desc";
             ResultSet rs=st.executeQuery(sql_id);
             if(rs.next())
             {
                 String id=rs.getString("id").substring(1);
                 String AN=""+(Integer.parseInt(id)+1);
                 String Nol="";
                 if(AN.length()==1) //jika anggota id A0001
                 {
                     Nol="000";
                 }
                 else if(AN.length()==2) //jika id anggota A0010
                 {
                     Nol="00";
                 }
                 else if(AN.length()==3) //jika id anggota A0100
                 {
                     Nol="0";
                 }
                 ID.setText("A"+Nol+AN);
             }
             else
             {
                 ID.setText("A0001");
             }
        }
        catch (Exception e)
                {
                    JOptionPane.showMessageDialog(null, e);
                }
    }
    
    //=================LOAD NAMA COMBO SEMESTER==========//
    public void LoadSemester()
    {
        try
        {
            Connection dbkon=databasekoneksi.koneksiDb();
            Statement st=dbkon.createStatement();
            String sql_semester="SELECT * FROM semester";
            ResultSet rs=st.executeQuery(sql_semester);
            while(rs.next())
            {
                SEMESTER.addItem(rs.getString("id"));
            }
            rs.close();
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    //=================NAMA SEMESTER================/
    public void NamaSemester()
    {
        try 
        {
            Connection dbkon=databasekoneksi.koneksiDb();
            Statement st=dbkon.createStatement();
            String sql_semester="SELECT nama_semester FROM semester WHERE id='"+SEMESTER.getSelectedItem()+"'";
            ResultSet rs=st.executeQuery(sql_semester);
            while(rs.next())
            {
                NSEMESTER.setText(rs.getString("nama_semester"));
            }
        }
        
        catch(Exception e)
        {
            
        }
    }
    
    //=================LOAD NAMA COMBO JURUSAN==========//
    public void LoadJurusan()
    {
        try
        {
            Connection dbkon=databasekoneksi.koneksiDb();
            Statement st=dbkon.createStatement();
            String sql_jurusan="SELECT kode_jurusan FROM jurusan";
            ResultSet rs=st.executeQuery(sql_jurusan);
            while(rs.next())
            {
                JURUSAN.addItem(rs.getString("kode_jurusan"));
            }
            rs.close();
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    //=================NAMA JURUSAN================/
    public void NamaJurusan()
    {
        try 
        {
            Connection dbkon=databasekoneksi.koneksiDb();
            Statement st=dbkon.createStatement();
            String sql_jurusan="SELECT nama_jurusan FROM jurusan WHERE kode_jurusan ='"+JURUSAN.getSelectedItem()+"'";
            ResultSet rs=st.executeQuery(sql_jurusan);
            while(rs.next())
            {
                NJURUSAN.setText(rs.getString("nama_jurusan"));
            }
        }
        
        catch(Exception e)
        {
            
        }
    }
    
    //=================LOAD NAMA COMBO KELAS==========//
    public void LoadKelas()
    {
        try
        {
            Connection dbkon=databasekoneksi.koneksiDb();
            Statement st=dbkon.createStatement();
            String sql_kelas="SELECT kode_kelas FROM kelas";
            ResultSet rs=st.executeQuery(sql_kelas);
            while(rs.next())
            {
                KELAS.addItem(rs.getString("kode_kelas"));
            }
            rs.close();
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    //=================NAMA JURUSAN================/
    public void NamaKelas()
    {
        try 
        {
            Connection dbkon=databasekoneksi.koneksiDb();
            Statement st=dbkon.createStatement();
            String sql_kelas="SELECT nama_kelas FROM kelas WHERE kode_kelas ='"+KELAS.getSelectedItem()+"'";
            ResultSet rs=st.executeQuery(sql_kelas);
            while(rs.next())
            {
                NKELAS.setText(rs.getString("nama_kelas"));
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
            
            //untuk jenis kelamin
            String jk="";
            if(JKP.isSelected())
            {
                jk=JKP.getText();
            }
            else
            {
                jk=JKW.getText();
            }
            
            String sql="INSERT INTO anggota values('"+ID.getText()
                    +"','"+NIM.getText()
                    +"','"+NAMA.getText()
                    +"','"+jk
                    +"','"+SEMESTER.getSelectedItem()
                    +"','"+JURUSAN.getSelectedItem()
                    +"','"+KELAS.getSelectedItem()
                    +"','"+NOHP.getText()
                    +"','"+STATUS.getSelectedItem()
                    +"')";
            st.execute(sql);
            JOptionPane.showMessageDialog(null, "Data Anggota Berhasil Dimasukan");
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    //RESET DATA
    public void clear()
    {
        NIM.setText("");
        NAMA.setText("");
        NOHP.setText("");
        JKP.setSelected(rootPaneCheckingEnabled);
        SEMESTER.setSelectedItem("1");
        JURUSAN.setSelectedItem("IF");
        KELAS.setSelectedItem(1);
        STATUS.setSelectedItem("AKTIF");
    }
    
    //edit data
    public void update()
    {
        try 
        {
            Connection dbkon=databasekoneksi.koneksiDb();
            Statement st=dbkon.createStatement();
            String jk="";
            
            if(JKP.isSelected())
            {
                jk=JKP.getText();
            }
            else
            {
                jk=JKW.getText();
            }
            
            String sql_update="UPDATE anggota set nim='"+NIM.getText()
                    +"',nama='"+NAMA.getText()
                    +"',jk='"+jk
                    +"',id_semester='"+SEMESTER.getSelectedItem()
                    +"',kd_jurusan='"+JURUSAN.getSelectedItem()
                    +"',kd_kelas='"+KELAS.getSelectedItem()
                    +"',no_telephone='"+NOHP.getText()
                    +"',status='"+STATUS.getSelectedItem()
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
            String sql_delete="DELETE from anggota WHERE "
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
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        ID = new javax.swing.JTextField();
        NIM = new javax.swing.JTextField();
        NAMA = new javax.swing.JTextField();
        SEMESTER = new javax.swing.JComboBox<>();
        JURUSAN = new javax.swing.JComboBox<>();
        KELAS = new javax.swing.JComboBox<>();
        NOHP = new javax.swing.JTextField();
        STATUS = new javax.swing.JComboBox<>();
        NSEMESTER = new javax.swing.JTextField();
        NJURUSAN = new javax.swing.JTextField();
        BInput = new javax.swing.JButton();
        BEdit = new javax.swing.JButton();
        BDelete = new javax.swing.JButton();
        JKP = new javax.swing.JRadioButton();
        JKW = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        TableAnggota = new javax.swing.JTable();
        NKELAS = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Clarendon Blk BT", 1, 24)); // NOI18N
        jLabel1.setText("KELOLA DATA ANGGOTA PERPUSTAKAAN");

        BKeluar.setFont(new java.awt.Font("Clarendon Blk BT", 0, 18)); // NOI18N
        BKeluar.setText("Keluar");
        BKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BKeluarActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Clarendon Blk BT", 0, 18)); // NOI18N
        jLabel2.setText("ID Anggota");

        jLabel3.setFont(new java.awt.Font("Clarendon Blk BT", 0, 18)); // NOI18N
        jLabel3.setText("NIM");

        jLabel4.setFont(new java.awt.Font("Clarendon Blk BT", 0, 18)); // NOI18N
        jLabel4.setText("Nama Anggota");

        jLabel5.setFont(new java.awt.Font("Clarendon Blk BT", 0, 18)); // NOI18N
        jLabel5.setText("Jenis Kelamin");

        jLabel6.setFont(new java.awt.Font("Clarendon Blk BT", 0, 18)); // NOI18N
        jLabel6.setText("Semester");

        jLabel7.setFont(new java.awt.Font("Clarendon Blk BT", 0, 18)); // NOI18N
        jLabel7.setText("Jurusan");

        jLabel8.setFont(new java.awt.Font("Clarendon Blk BT", 0, 18)); // NOI18N
        jLabel8.setText("Kelas");

        jLabel9.setFont(new java.awt.Font("Clarendon Blk BT", 0, 18)); // NOI18N
        jLabel9.setText("No HP");

        jLabel10.setFont(new java.awt.Font("Clarendon Blk BT", 0, 18)); // NOI18N
        jLabel10.setText("Status");

        ID.setFont(new java.awt.Font("Clarendon Blk BT", 0, 18)); // NOI18N
        ID.setEnabled(false);

        NIM.setFont(new java.awt.Font("Clarendon Blk BT", 0, 18)); // NOI18N

        NAMA.setFont(new java.awt.Font("Clarendon Blk BT", 0, 18)); // NOI18N

        SEMESTER.setFont(new java.awt.Font("Clarendon Blk BT", 0, 18)); // NOI18N
        SEMESTER.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SEMESTERMouseClicked(evt);
            }
        });
        SEMESTER.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SEMESTERActionPerformed(evt);
            }
        });

        JURUSAN.setFont(new java.awt.Font("Clarendon Blk BT", 0, 18)); // NOI18N
        JURUSAN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JURUSANActionPerformed(evt);
            }
        });

        KELAS.setFont(new java.awt.Font("Clarendon Blk BT", 0, 18)); // NOI18N
        KELAS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                KELASActionPerformed(evt);
            }
        });

        NOHP.setFont(new java.awt.Font("Clarendon Blk BT", 0, 18)); // NOI18N

        STATUS.setFont(new java.awt.Font("Clarendon Blk BT", 0, 18)); // NOI18N
        STATUS.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "AKTIF", "TIDAK AKTIF" }));

        NSEMESTER.setEditable(false);
        NSEMESTER.setFont(new java.awt.Font("Clarendon Blk BT", 0, 18)); // NOI18N

        NJURUSAN.setEditable(false);
        NJURUSAN.setFont(new java.awt.Font("Clarendon Blk BT", 0, 18)); // NOI18N

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

        JK.add(JKP);
        JKP.setFont(new java.awt.Font("Clarendon Blk BT", 0, 18)); // NOI18N
        JKP.setSelected(true);
        JKP.setText("PRIA");

        JK.add(JKW);
        JKW.setFont(new java.awt.Font("Clarendon Blk BT", 0, 18)); // NOI18N
        JKW.setText("WANITA");

        TableAnggota.setModel(new javax.swing.table.DefaultTableModel(
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
        TableAnggota.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableAnggotaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TableAnggota);

        NKELAS.setEditable(false);
        NKELAS.setFont(new java.awt.Font("Clarendon Blk BT", 0, 18)); // NOI18N

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
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel10)
                            .addComponent(BKeluar))
                        .addGap(47, 47, 47)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(BInput)
                                        .addGap(18, 18, 18)
                                        .addComponent(BEdit))
                                    .addComponent(STATUS, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(23, 23, 23)
                                .addComponent(BDelete))
                            .addComponent(NOHP)
                            .addComponent(NAMA, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(JKP)
                                .addGap(18, 18, 18)
                                .addComponent(JKW))
                            .addComponent(NJURUSAN, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(SEMESTER, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(NSEMESTER))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(KELAS, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(JURUSAN, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addComponent(NKELAS, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2))
                        .addGap(79, 79, 79)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ID)
                            .addComponent(NIM, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
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
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(NIM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(NAMA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(JKP)
                            .addComponent(JKW))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(SEMESTER, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(NSEMESTER, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(JURUSAN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(NJURUSAN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(KELAS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(NKELAS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(NOHP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(STATUS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(BKeluar)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(BInput)
                                .addComponent(BEdit))
                            .addComponent(BDelete)))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 15, Short.MAX_VALUE))
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
                "Keluar dari Kelola Data Anggota ?", 
                "Keluar",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE, null,null,null);
        if(keluar==JOptionPane.YES_NO_OPTION)
        {
            new pustakawan().show();
            this.dispose();
        }
    }//GEN-LAST:event_BKeluarActionPerformed

    private void SEMESTERMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SEMESTERMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_SEMESTERMouseClicked

    private void SEMESTERActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SEMESTERActionPerformed
        // TODO add your handling code here:
        NamaSemester();
    }//GEN-LAST:event_SEMESTERActionPerformed

    private void JURUSANActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JURUSANActionPerformed
        // TODO add your handling code here:
         NamaJurusan();
    }//GEN-LAST:event_JURUSANActionPerformed

    private void KELASActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_KELASActionPerformed
        // TODO add your handling code here:
        NamaKelas();
    }//GEN-LAST:event_KELASActionPerformed

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

    private void TableAnggotaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableAnggotaMouseClicked
        // TODO add your handling code here:
        int bar=TableAnggota.getSelectedRow();
        String a=TableAnggota.getValueAt(bar, 0).toString();
        String b=TableAnggota.getValueAt(bar, 1).toString();
        String c=TableAnggota.getValueAt(bar, 2).toString();
        String d=TableAnggota.getValueAt(bar, 3).toString();
        String e=TableAnggota.getValueAt(bar, 4).toString();
        String f=TableAnggota.getValueAt(bar, 5).toString();
        String g=TableAnggota.getValueAt(bar, 6).toString();
        String h=TableAnggota.getValueAt(bar, 7).toString();
        String i=TableAnggota.getValueAt(bar, 8).toString();
        
        
        ID.setText(a);
        NIM.setText(b);
        NAMA.setText(c);
        //Jenis Kelamin
        if("PRIA".equals(d))
        {
            JKP.setSelected(true);
        }
        else
        {
            JKW.setSelected(true);
        }
        //Semester
        SEMESTER.setSelectedItem(e);
        JURUSAN.setSelectedItem(f);
        KELAS.setSelectedItem(g);
        NOHP.setText(h);
        //Status
        if("AKTIF".equals(i))
        {
            STATUS.setSelectedItem(i);
        }
        else
        {
            STATUS.setSelectedItem(i);
        }
        
        //input disable
        BInput.setEnabled(false);
        BEdit.setEnabled(true);
        BDelete.setEnabled(true);
        
    }//GEN-LAST:event_TableAnggotaMouseClicked

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
            java.util.logging.Logger.getLogger(data_anggota.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(data_anggota.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(data_anggota.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(data_anggota.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new data_anggota().setVisible(true);
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
    private javax.swing.JRadioButton JKP;
    private javax.swing.JRadioButton JKW;
    private javax.swing.JComboBox<String> JURUSAN;
    private javax.swing.JComboBox<String> KELAS;
    private javax.swing.JTextField NAMA;
    private javax.swing.JTextField NIM;
    private javax.swing.JTextField NJURUSAN;
    private javax.swing.JTextField NKELAS;
    private javax.swing.JTextField NOHP;
    private javax.swing.JTextField NSEMESTER;
    private javax.swing.JComboBox<String> SEMESTER;
    private javax.swing.JComboBox<String> STATUS;
    private javax.swing.JTable TableAnggota;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
