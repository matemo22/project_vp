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
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import projectvp.database.user.User;
import projectvp.listener.AdminsListener;

/**
 *
 * @author user
 */
public class EditAdminPanel extends JPanel implements ActionListener
{
    private JLabel titleLabel;
    private JTextField adminNameField, adminPasswordField;
    private JButton saveButton, cancelButton;
    private User prevUser;
    private int selectedIndex;
    private AdminsListener listener;
    private JComboBox statusBox;
    
    public EditAdminPanel(int selectedIndex, User prevUser)
    {
        this.selectedIndex=selectedIndex;
        this.prevUser=prevUser;
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
        String row = "50px, pref, 50px, pref, 10px, pref, 10px, pref, 10px, pref, 10, pref, 10px, pref, 20px, pref, 5px";
        
        FormLayout layout = new FormLayout(column, row);
        this.setLayout(layout);
        CellConstraints c = new CellConstraints();
        
        this.add(titleLabel, c.xyw(2, 2,3, CellConstraints.CENTER, CellConstraints.CENTER));
        this.add(new JLabel("Admin Name"), c.xyw(2, 4,3, CellConstraints.CENTER, CellConstraints.CENTER));
        this.add(adminNameField, c.xyw(2, 6,3, CellConstraints.CENTER, CellConstraints.CENTER));
        this.add(new JLabel("Password"), c.xyw(2, 8,3, CellConstraints.CENTER, CellConstraints.CENTER));
        this.add(adminPasswordField, c.xyw(2, 10,3, CellConstraints.CENTER, CellConstraints.CENTER));
        this.add(new JLabel("Status"), c.xyw(2, 12, 3, CellConstraints.CENTER, CellConstraints.CENTER));
        this.add(statusBox, c.xyw(2, 14, 3, CellConstraints.CENTER, CellConstraints.CENTER));
        this.add(saveButton, c.xy(2, 16));
        this.add(cancelButton, c.xy(4, 16));
    }
    
    private void initComponent()
    {
        titleLabel = new JLabel("Edit Admin");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        adminNameField = new JTextField(20);
        adminNameField.setText(prevUser.getUsername());
        adminNameField.setEnabled(false);
        adminPasswordField = new JTextField(20);
        adminPasswordField.setText(prevUser.getPassword());
        saveButton = new JButton("Save");
        cancelButton = new JButton("Cancel");
        String[] status = {"Active", "Deactive"};
        statusBox = new JComboBox(status);
        if(prevUser.getStatus()==1)
            statusBox.setSelectedIndex(0);
        else
            statusBox.setSelectedIndex(1);
    }
    
    private void registerListener()
    {
        saveButton.addActionListener(this);
        cancelButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(saveButton))
        {
            User newUser = new User();
            newUser.setUsername(adminNameField.getText());
            newUser.setPassword(adminPasswordField.getText());
            newUser.setAuthority(prevUser.getAuthority());
            if(statusBox.getSelectedIndex()==0)
                newUser.setStatus(1);
            else
                newUser.setStatus(2);
            listener.editUser(newUser, prevUser, selectedIndex);
        }
        if(e.getSource().equals(cancelButton))
        {
            listener.cancelAdmin();
        }
    }
  
}