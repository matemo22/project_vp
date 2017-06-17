/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectvp.model;

import projectvp.database.user.User;
import projectvp.database.user.UserService;

/**
 *
 * @author Matemo
 */
public class UserModel {
    UserService us = new UserService();
    
    public boolean addNewBarang(User newUser)
    {
        return us.addNewUser(newUser);
    }
    
    public boolean editBarang(User newUser, User prevUser)
    {
        return us.updateUser(newUser, prevUser);
    }
}
