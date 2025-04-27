/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package emart.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Abhinav Yadav
 */
public class DBConnection {
    private static Connection conn;
    static
    {
        try{
            Class.forName("oracle.jdbc.OracleDriver");
            conn=DriverManager.getConnection("jdbc:oracle:thin:@//Abhinavyadav:1521/XEPDB1","grocery","grocery");
            JOptionPane.showMessageDialog(null,"Connection opened successfully!","Success",JOptionPane.INFORMATION_MESSAGE);
        }
        catch(ClassNotFoundException ex)
        {
            JOptionPane.showMessageDialog(null, "Error in loading the driver","Driver Error!",JOptionPane.INFORMATION_MESSAGE);
            ex.printStackTrace();
            System.exit(1);
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error in opening Connection","DB Error!",JOptionPane.INFORMATION_MESSAGE);
            ex.printStackTrace();
            System.exit(1);
        }
    }
    
    public static Connection getConnection()
    {
        return conn;
    }
    public static void closeConnection()
    {
        try
        {
            conn.close();
            JOptionPane.showMessageDialog(null,"Connection closed successfully!","Success",JOptionPane.INFORMATION_MESSAGE);
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error in closing Connection","DB Error!",JOptionPane.INFORMATION_MESSAGE);
            ex.printStackTrace();
        }
    }
}
