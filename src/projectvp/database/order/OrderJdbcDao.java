package projectvp.database.order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import projectvp.database.order.Order;
import projectvp.database.ConnectionManager;
import projectvp.database.barang.Barang;
import projectvp.database.supplier.Supplier;
import projectvp.database.supplier.SupplierService;
import projectvp.database.user.User;
import projectvp.database.user.UserService;

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
    
    public Vector<Order> readAllOrder()
    {
        Vector<Order> result = null;
        String query = "SELECT * FROM `order`";
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            rs=stmt.executeQuery(query);
            result = new Vector<Order>();
            
            while(rs.next())
            {
                Order order = new Order();
                order.setId(rs.getInt("id"));
                Supplier temp = getSupplier(rs);
                order.setSupplier(temp);
                order.setProdukName(rs.getString("produkName"));
                order.setProdukType(rs.getString("produkType"));
                order.setNamaBarang(rs.getString("namaBarang"));
                order.setQty(rs.getInt("quantity"));
                order.setDate(rs.getDate("tglPesan"));
                User tempUser = getUser(rs);
                order.setUser(tempUser);
                result.add(order);
            }
        }
        catch (SQLException ex) {
//            Logger.getLogger(OrderJdbcDao.class.getName()).log(Level.SEVERE, null, ex);
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
    
     public boolean insertOrder(Order newOrder)
    {
        int berhasil=0;
        String query = "INSERT INTO `order`(`supplier`, `produkName`, `produkType`, `namaBarang`, `quantity`, `tglPesan`,`user`) values (?,?,?,?,?,?,?)"; 
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, newOrder.getSupplier().getId());
            pstmt.setString(2, newOrder.getProdukName());
            pstmt.setString(3, newOrder.getProdukType());
            pstmt.setString(4, newOrder.getNamaBarang());
            pstmt.setInt(5, newOrder.getQty());
            pstmt.setDate(6, newOrder.getDate());
            pstmt.setInt(7, newOrder.getUser().getId());
            berhasil = pstmt.executeUpdate();
            conn.commit();
        } catch (SQLException ex) {
//            Logger.getLogger(OrderJdbcDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(berhasil > 0) return true;
        else return false;
    }
     
    private Supplier getSupplier(ResultSet rs) throws SQLException
    {
        SupplierService ss = new SupplierService();
        Vector<Supplier> suppliers = ss.getSupplier();
        for (Supplier s : suppliers)
        {
            if(rs.getInt("supplier")==s.getId())
            {
                return s;
            }
        }
        return null;
    }
     private User getUser(ResultSet rs) throws SQLException
    {
        UserService Us = new UserService();
        Vector<User> users = Us.getUsers();
        for (User u : users)
        {
            if(rs.getInt("user")==u.getId())
            {
                return u;
            }
        }
        return null;
    }
     
   
}
