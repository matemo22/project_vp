/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectvp.database.supplier;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import projectvp.database.Brand.Brand;
import projectvp.database.Brand.BrandService;
import projectvp.database.ConnectionManager;
import projectvp.database.user.User;
import projectvp.database.user.UserJdbcDao;

/**
 *
 * @author Matemo
 */
public class SupplierJdbcDao {
    private final Connection conn;
    
    public SupplierJdbcDao()
    {
        conn=ConnectionManager.getInstance().getConnection();
    }
    
    public Vector<Supplier> readAllSuppliers()
    {
        Vector<Supplier> result = null;
        String query = "SELECT * FROM supplier";
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            rs=stmt.executeQuery(query);
            result = new Vector<Supplier>();
            while(rs.next())
            {
                Supplier supplier = new Supplier();
                supplier.setId(rs.getInt("id"));
                supplier.setMerek(getNameBrand(rs));
                supplier.setLocation(rs.getString("location"));
                result.add(supplier);
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
                    Logger.getLogger(UserJdbcDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        return result;
    }
    
    private Brand getNameBrand(ResultSet rs) throws SQLException
    {
        BrandService bs = new BrandService();
        Vector<Brand> brands = bs.getBrands();
        for (Brand b : brands)
        {
            if(rs.getInt("id_merek")==b.getId())
            {
                return b;
            }
        }
        return null;
    }
}
