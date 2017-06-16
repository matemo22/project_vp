/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectvp.layout;

import com.jgoodies.forms.layout.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;
import projectvp.MainFrame;
import projectvp.database.user.User;
import projectvp.listener.LoginListener;

/**
 *
 * @author Matemo
 */
public class LoginPanel extends JPanel implements ActionListener, KeyListener
{
    private JLabel titleLabel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton, registerButton;
    LoginListener listener;
    
    public LoginPanel()
    {
        initComponent();
        buildGui();
        registerListener();
    }
    
    public void addLoginListener(LoginListener listener) {
        this.listener= listener;
    }
    
    private void buildGui()
    {
        this.setPreferredSize(new Dimension(500,450));
        
        String column = "100px, 300px, 100px";
        String row = "50px, pref, 50px, pref, 10px, pref, 10px, pref, 10px, pref, 20px, pref, 20px, pref, 5px";
        
        FormLayout layout = new FormLayout(column, row);
        this.setLayout(layout);
        CellConstraints c = new CellConstraints();
        
        this.add(titleLabel, c.xy(2, 2, CellConstraints.CENTER, CellConstraints.CENTER));
        this.add(new JLabel("Username"), c.xy(2, 4, CellConstraints.CENTER, CellConstraints.CENTER));
        this.add(usernameField, c.xy(2, 6, CellConstraints.CENTER, CellConstraints.CENTER));
        this.add(new JLabel("Password"), c.xy(2, 8, CellConstraints.CENTER, CellConstraints.CENTER));
        this.add(passwordField, c.xy(2, 10, CellConstraints.CENTER, CellConstraints.CENTER));
        this.add(loginButton, c.xy(2, 12, CellConstraints.CENTER, CellConstraints.CENTER));
        this.add(registerButton, c.xy(2, 14, CellConstraints.CENTER, CellConstraints.CENTER));
    }
    
    private void initComponent()
    {
        titleLabel = new JLabel("Login");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 55));
        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);
        loginButton = new JButton("Login");
        registerButton = new JButton("Register");
        usernameField.setText("matemo22");
        passwordField.setText("asdf");
    }
    
    private void registerListener()
    {
        loginButton.addActionListener(this);
        passwordField.addKeyListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(loginButton))
        {
            User u = listener.attemptLogin(usernameField.getText(), String.valueOf(passwordField.getPassword()));
            if(u!=null)
            {
                listener.loginSucceed(u);
            }
            else
            {
                //Alert Login Failed
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if(e.getSource().equals(passwordField))
        {
            if(e.getKeyChar()==('\n'))
                loginButton.doClick();
        }
//        if(e.getKeyCode()==KeyEvent.VK_ENTER) loginButton.doClick();
    }

    @Override
    public void keyPressed(KeyEvent e) {
       
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
}
