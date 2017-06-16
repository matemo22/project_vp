/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectvp.listener;

import projectvp.database.user.User;
import projectvp.layout.LoginPanel;

/**
 *
 * @author Matemo
 */
public interface LoginListener {
    public User attemptLogin(String email, String password);
    public void loginSucceed(User user);
}
