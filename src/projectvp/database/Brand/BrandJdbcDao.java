package projectvp.database.Brand;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import projectvp.database.ConnectionManager;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Matemo
 */
public class BrandJdbcDao {
    private final Connection conn;
    
    public BrandJdbcDao()
    {
        conn=ConnectionManager.getInstance().getConnection();
    }
    
    public Vector<Brand> readAllBrands()
    {
        Vector<Brand> result = null;
        String query = "SELECT * FROM merek";
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            rs=stmt.executeQuery(query);
            result = new Vector<Brand>();
            while(rs.next())
            {
                Brand brand = new Brand();
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
    
    public boolean insertSupplier(Brand newBrand)
    {
        int berhasil=0;
        String query = "INSERT INTO `merek`(`name`) values (?)"; 
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setString(2, newBrand.getName());
            berhasil = pstmt.executeUpdate();
            conn.commit();
        } catch (SQLException ex) {
//            Logger.getLogger(OrderJdbcDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(berhasil > 0) return true;
        else return false;
    }
}
