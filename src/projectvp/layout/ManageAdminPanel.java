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
import javax.swing.*;
import javax.swing.border.TitledBorder;
import projectvp.model.ItemTableModel;

/**
 *
 * @author user
 */
public class ManageAdminPanel extends JPanel {

  private JLabel titleLabel;
    private JTextField searchField;
    private JButton goButton, addItemButton, detailItemButton;
    private JComboBox filter;
    private JTable itemTable;
    private JScrollPane tablePane;

    public ManageAdminPanel() {
        initComponent();
        buildGui();
        System.out.println("aksjfkljsadklf");
    }

    private void buildGui() {
       this.setPreferredSize(new Dimension(500,450));
        
        String column = "20px, 175px, 10px, pref, 15px, 95px, 15px, 95px";
        String row = "50px, pref, 25px, pref, 20px, pref, 20px, pref, 250px";
        
        FormLayout layout = new FormLayout(column, row);
        this.setLayout(layout);
        CellConstraints c = new CellConstraints();
        
        this.add(titleLabel, c.xyw(4,2,2, CellConstraints.CENTER, CellConstraints.CENTER));
        this.add(searchField,c.xy(2, 4));
//        this.add(tablePane, c.xywh(2, 6,3,4));
        this.add(goButton, c.xy(4, 4,CellConstraints.CENTER, CellConstraints.CENTER));
//        this.add(filter, c.xy(6, 4));
        this.add(addItemButton, c.xy(6, 4));
        this.add(detailItemButton, c.xy(8, 4));
    }

    private void initComponent() {
        titleLabel = new JLabel("Manage Admin");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        searchField= new JTextField();
        goButton = new JButton("Go!");
        addItemButton = new JButton("Add Admin");
        detailItemButton = new JButton("Edit Admin");
        
      
        
        ItemTableModel tableModel = new ItemTableModel(); 
        itemTable = new JTable();
        itemTable.setModel(tableModel);
        itemTable.setAutoCreateRowSorter(true);
        itemTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        itemTable.setRowSelectionAllowed(true);
        itemTable.setColumnSelectionAllowed(false);
        itemTable.setRowHeight(50);
        
        
        tablePane = new JScrollPane(itemTable);
        tablePane.setPreferredSize(new Dimension(400, 100));
    }
}
