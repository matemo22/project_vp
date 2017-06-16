package projectvp.database.barang;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import projectvp.database.Brand.Brand;
import projectvp.database.Brand.BrandService;
import projectvp.database.ConnectionManager;
import projectvp.database.supplier.Supplier;
import projectvp.database.supplier.SupplierService;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Matemo
 */
public class BarangJdbcDao {
    private final Connection conn;
    
    public BarangJdbcDao()
    {
        conn=ConnectionManager.getInstance().getConnection();
    }
    
    public Vector<Barang> readAllBarangs()
    {
        Vector<Barang> result = null;
        String query = "SELECT * FROM barang";
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            rs=stmt.executeQuery(query);
            result = new Vector<Barang>();
            while(rs.next())
            {
                Barang barang = new Barang();
                barang.setName(rs.getString("name"));
                barang.setProduct(rs.getString("product"));
                barang.setJenis(rs.getString("jenis"));
                Supplier temp = getSupplier(rs);
                barang.setSupplier(temp);
                barang.setHarga(rs.getInt("harga"));
                barang.setQty(rs.getInt("quantity"));
                barang.setGudang(rs.getInt("gudang"));
                result.add(barang);
            }
        }
        catch (SQLException ex) {
            System.out.println("BarangJdbcDao");
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
//                    Logger.getLogger(UserJdbcDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        return result;
    }
    
    public boolean insertBarang(Barang newBarang)
    {
        int berhasil=0;
        String query = "INSERT INTO barang (name, product, jenis, supplier, harga, quantity, gudang) values (?,?,?,?,?,?,?)"; 
        PreparedStatement pstmt = null;
        
        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, newBarang.getName());
            pstmt.setString(2, newBarang.getProduct());
            pstmt.setString(3, newBarang.getJenis());
            pstmt.setInt(4, newBarang.getSupplier().getId());
            pstmt.setInt(5, newBarang.getHarga());
            pstmt.setInt(6, newBarang.getQty());
            pstmt.setInt(7, newBarang.getGudang());
            berhasil = pstmt.executeUpdate();
            conn.commit();
        } catch (SQLException ex) {
//            Logger.getLogger(UserJdbcDao.class.getName()).log(Level.SEVERE, null, ex);
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
    
    public boolean updateBarang(Barang newBarang, Barang prevBarang)
    {
        int berhasil = 0;
        String query = "UPDATE barang SET name = ?, product = ?, jenis = ?, supplier = ?, harga = ?, quantity = ?, gudang = ? WHERE name = ?";
//        String query = "UPDATE barang SET name = ? WHERE name = ?";
        System.out.println(query);
        PreparedStatement pstmt = null;
        try {
            System.out.println("pstmt1");
            pstmt = conn.prepareStatement(query);
//            pstmt.setString(1, newBarang.getName());
//            pstmt.setString(2, prevBarang.getName());
            pstmt.setString(1, newBarang.getName());
            pstmt.setString(2, newBarang.getProduct());
            pstmt.setString(3, newBarang.getJenis());
            pstmt.setInt(4, newBarang.getSupplier().getId());
            pstmt.setInt(5, newBarang.getHarga());
            pstmt.setInt(6, newBarang.getQty());
            pstmt.setInt(7, newBarang.getGudang());
            pstmt.setString(8, prevBarang.getName());
            berhasil = pstmt.executeUpdate();
            conn.commit();
        } catch (SQLException ex) {
//            Logger.getLogger(UserJdbcDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(berhasil > 0) return true;
        else return false;
    }
}
