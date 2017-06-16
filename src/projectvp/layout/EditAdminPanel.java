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
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author user
 */
public class EditAdminPanel extends JPanel 
{
    private JLabel titleLabel;
    private JTextField adminNameField;
    private JPasswordField adminPasswordField;
    private JButton saveButton, cancelButton;
    
    public EditAdminPanel()
    {
        initComponent();
        buildGui();
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
        this.add(new JLabel("New Admin Name"), c.xyw(2, 4,3, CellConstraints.CENTER, CellConstraints.CENTER));
        this.add(adminNameField, c.xyw(2, 6,3, CellConstraints.CENTER, CellConstraints.CENTER));
        this.add(new JLabel("New Password"), c.xyw(2, 8,3, CellConstraints.CENTER, CellConstraints.CENTER));
        this.add(adminPasswordField, c.xyw(2, 10,3, CellConstraints.CENTER, CellConstraints.CENTER));
        
        
        this.add(saveButton, c.xy(2, 14));
        this.add(cancelButton, c.xy(4, 14));
    }
    
    private void initComponent()
    {
        titleLabel = new JLabel("Edit Admin");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        adminNameField = new JTextField(20);
        adminPasswordField = new JPasswordField(20);
        saveButton = new JButton("Save");
        cancelButton = new JButton("Cancel");
    }
    

  
}