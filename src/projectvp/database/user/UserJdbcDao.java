package projectvp.database.user;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import projectvp.database.ConnectionManager;
import projectvp.database.user.User;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Matemo
 */
public class UserJdbcDao {
    private final Connection conn;
    
    public UserJdbcDao()
    {
        conn=ConnectionManager.getInstance().getConnection();
    }
    
    public Vector<User> readAllUsers()
    {
        Vector<User> result = null;
        String query = "SELECT * FROM user";
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            rs=stmt.executeQuery(query);
            result = new Vector<User>();
            while(rs.next())
            {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setAuthority(rs.getInt("authority"));
                user.setStatus(rs.getInt("status"));
                result.add(user);
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
    
    public boolean insertBarang(User newUser)
    {
        int berhasil=0;
        String query = "INSERT INTO user (username, password, authority, status) values (?,?,?,?)"; 
        PreparedStatement pstmt = null;
        
        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, newUser.getUsername());
            pstmt.setString(2, newUser.getPassword());
            pstmt.setInt(3, newUser.getAuthority());
            pstmt.setInt(4, newUser.getStatus());
            berhasil = pstmt.executeUpdate();
            conn.commit();
        } catch (SQLException ex) {
//            Logger.getLogger(UserJdbcDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(berhasil > 0) return true;
        else return false;
    }
}
