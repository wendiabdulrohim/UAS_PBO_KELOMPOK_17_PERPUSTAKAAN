/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package perpustakaanku;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author Administrator
 */
public class databasekoneksi
{
 Connection databasekoneksi=null;
public static Connection koneksiDb()
{
    try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection databasekoneksi = DriverManager.getConnection("jdbc:mysql://localhost/perpustakaanku","root","");
            return databasekoneksi;
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
}