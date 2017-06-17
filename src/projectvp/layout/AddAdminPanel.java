/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectvp.layout;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import projectvp.database.user.User;
import projectvp.listener.AdminsListener;
import projectvp.listener.LoginListener;

/**
 *
 * @author user
 */
public class AddAdminPanel extends JPanel implements ActionListener, KeyListener
{
    private JLabel titleLabel;
    private JTextField adminNameField;
    private JPasswordField adminPasswordField;
    private JButton saveButton, cancelButton;
    private AdminsListener listener;
    
    public AddAdminPanel()
    {
        initComponent();
        buildGui();
        registerListener();
    }
    
    public void addListener(AdminsListener listener)
    {
        this.listener=listener;
    }
    
    private void buildGui()
    {
        this.setPreferredSize(new Dimension(500,450));
        
        String column = "100px, 125px,50px,125px, 100px";
        String row = "50px, pref, 50px, pref, 10px, pref, 10px, pref, 10px, pref, 20px, pref, 20px, pref, 5px";
        
        FormLayout layout = new FormLayout(column, row);
        this.setLayout(layout);
        CellConstraints c = new CellConstraints();
        
        this.add(titleLabel, c.xyw(2, 2,3, CellConstraints.CENTER, CellConstraints.CENTER));
        this.add(new JLabel("Admin Name"), c.xyw(2, 4,3, CellConstraints.CENTER, CellConstraints.CENTER));
        this.add(adminNameField, c.xyw(2, 6,3, CellConstraints.CENTER, CellConstraints.CENTER));
        this.add(new JLabel("Password"), c.xyw(2, 8,3, CellConstraints.CENTER, CellConstraints.CENTER));
        this.add(adminPasswordField, c.xyw(2, 10,3, CellConstraints.CENTER, CellConstraints.CENTER));
        
        this.add(saveButton, c.xy(2, 14));
        saveButton.setEnabled(false);
        this.add(cancelButton, c.xy(4, 14));
    }
    
    private void initComponent()
    {
        titleLabel = new JLabel("Add Admin");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        adminNameField = new JTextField(20);
        adminPasswordField = new JPasswordField(20);
        saveButton = new JButton("Save");
        cancelButton = new JButton("Cancel");
    }
    
    public void registerListener()
    {
        adminNameField.addKeyListener(this);
        adminPasswordField.addKeyListener(this);
        saveButton.addActionListener(this);
        cancelButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(saveButton))
        {
            char[] a = adminPasswordField.getPassword();
            String b ="";
            for (int i = 0; i < a.length; i++) {
                b=b+a[i];
            }
            User newUser = new User();
            newUser.setUsername(adminNameField.getText());
            newUser.setPassword(b);
            newUser.setAuthority(2);
            newUser.setStatus(1);
            listener.saveUser(newUser);
            
        }
        if(e.getSource().equals(cancelButton))
            listener.cancelAdmin();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if(adminNameField.getText().length()!=0 && adminPasswordField.getPassword().length!=0)
        {
            saveButton.setEnabled(true);
        }
        else saveButton.setEnabled(false);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
  
}