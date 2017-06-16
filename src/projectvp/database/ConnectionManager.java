/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectvp.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Matemo
 */
public class ConnectionManager {
    private static ConnectionManager myInstance = new ConnectionManager();
    private Connection conn;
    
    public static ConnectionManager getInstance()
    {
        return myInstance;
    }
    
    private ConnectionManager()
    {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/projectvp", "root", "");
        } catch (ClassNotFoundException ex) {
            System.out.println("1");
            System.out.println("A");
        } catch (InstantiationException ex) {
            System.out.println("2");
            System.out.println("B");
        } catch (IllegalAccessException ex) {
            System.out.println("3");
            System.out.println("C");
        } catch (SQLException ex) {
            System.out.println("4");
            System.out.println("D");
        }
    }
    
    public Connection getConnection()
    {
        return conn;
    }
    
    public static void main(String[] args) {
        ConnectionManager c = new ConnectionManager();
        if(c.getConnection()!=null)
            System.out.println("YEEEYY");
        else
            System.out.println("NOOOOO");
    }
}
