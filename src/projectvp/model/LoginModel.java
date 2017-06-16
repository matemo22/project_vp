/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectvp.model;

import java.util.Vector;
import projectvp.database.user.User;
import projectvp.database.user.UserService;

/**
 *
 * @author Matemo
 */
public class LoginModel {
    public User checkLogin(String username, String password)
    {
        UserService us = new UserService();
        Vector<User> users = us.getUsers();
        for(User u : users)
        {
            if(u.getUsername().equals(username))
            {
                if(u.getPassword().equals(password))
                {
                    return u;
                }
            }
        }
        return null;
    }
}
