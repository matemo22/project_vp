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
import java.awt.event.KeyEvent;
import javax.swing.*;
import projectvp.database.user.User;
import projectvp.listener.AdminsListener;
import projectvp.model.OrderHistoryTableModel;
import projectvp.model.OrderItemModel;

/**
 *
 * @author user
 */
public class HistoryOrderPanel extends JPanel{
    private JLabel titleLabel;
     private JTable orderTable;
    private JScrollPane tablePane;
     private User currentUser;
     
    public HistoryOrderPanel()
    {
        initComponent();
        buildGui();
        registerListener();
    }
    
   
    private void buildGui()
    {
       this.setPreferredSize(new Dimension(500, 450));

        String column = "20px, 100px, 25px, 100px, 25px, 100px, 25px, 95px";
        String row = "50px, pref, 25px, pref, 20px, pref, 20px, pref, 200px,20px,pref, 25px";

        FormLayout layout = new FormLayout(column, row);
        this.setLayout(layout);
        CellConstraints c = new CellConstraints();

        this.add(titleLabel, c.xyw(4, 2, 3, CellConstraints.CENTER, CellConstraints.CENTER));
      
        this.add(tablePane, c.xywh(2, 6, 7, 4));

    }
    
    
    private void initComponent()
    {
        titleLabel = new JLabel("History Order");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        
        OrderHistoryTableModel tableModel = new OrderHistoryTableModel();
        orderTable = new JTable();
        orderTable.setModel(tableModel);
        orderTable.setAutoCreateRowSorter(true);
        orderTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        orderTable.setRowSelectionAllowed(true);
        orderTable.setColumnSelectionAllowed(false);
        orderTable.setRowHeight(30);

        tablePane = new JScrollPane(orderTable);
        tablePane.setPreferredSize(new Dimension(400, 100));
    }
    
    public void registerListener()
    {
    }

}
