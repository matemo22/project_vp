/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectvp.database.user;

import java.util.Vector;

/**
 *
 * @author Matemo
 */
public class UserService {
    private UserJdbcDao dao = new UserJdbcDao();
    
    public Vector<User> getUsers()
    {
        return dao.readAllUsers();
    }
    
    public boolean addNewUser(User newBarang)
    {
        return false;
    }
    
    public boolean updateUser(User newBarang, User prevBarang)
    {
        return false;
    }
}
