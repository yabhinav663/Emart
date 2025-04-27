/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package emart.dao;

import emart.dbutil.DBConnection;
import emart.pojo.ProductsPojo;
import emart.pojo.UserProfile;
import java.sql.Connection;
import java.sql.*;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.sql.PreparedStatement;

/**
 *
 * @author Abhinav Yadav
 */
public class OrderDAO {
    public static String getNextOrderId() throws SQLException
    {
        Connection conn=DBConnection.getConnection();
        Statement st = conn.createStatement();
        ResultSet rs= st.executeQuery("Select max(order_id) from orders");
        rs.next();
        String ordid=rs.getString(1);
        if(ordid==null)
        {
            return "O-P101";
        }
        int ordno = Integer.parseInt(ordid.substring(2));
        ordno++;
        return "P"+ordno;
    }
    public static boolean addOrder(ArrayList<ProductsPojo> al,String ordId)throws SQLException
    {
        Connection conn=DBConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("Insert into orders values(?,?,?,?)");
        int count=0;
        for(ProductsPojo p:al)
        {
            ps.setString(1, ordId);
            ps.setString(2,p.getProductId());
            ps.setInt(3, p.getQuantity());
            ps.setString(4,UserProfile.getUserid());
            count=count+ps.executeUpdate();
        }
        return count==al.size();
    }
}
