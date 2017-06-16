package projectvp.database.order;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import projectvp.database.order.Order;
import projectvp.database.ConnectionManager;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author user
 */
public class OrderJdbcDao {
     private final Connection conn;
    
    public OrderJdbcDao()
    {
        conn=ConnectionManager.getInstance().getConnection();
    }
    
    public Vector<Order> readAllBrands()
    {
        Vector<Order> result = null;
        String query = "SELECT * FROM merek";
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            rs=stmt.executeQuery(query);
            result = new Vector<Order>();
            while(rs.next())
            {
                Order brand = new Order();
                brand.setId(rs.getInt("id"));
                brand.setName(rs.getString("name"));
                result.add(brand);
            }
        }
        catch (SQLException ex) {
            
        }
        finally
        {
            if(rs!=null)
            {
                try {
                    rs.close();
                    stmt.close();
                }
                catch (SQLException ex)
                {
                    
                }
            }
        }
        
        return result;
    }
}
