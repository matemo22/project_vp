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
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import projectvp.listener.ManageAdminListener;
import projectvp.model.AdminTableModel;
import projectvp.model.ItemTableModel;

/**
 *
 * @author user
 */
public class ManageAdminPanel extends JPanel implements TableModelListener, ListSelectionListener, ActionListener{

  private JLabel titleLabel;
    private JTextField searchField;
    private JButton goButton, addButton, editButton;
    private JComboBox filter;
    private JTable itemTable;
    private JScrollPane tablePane;
    private ManageAdminListener listener;
    private int selectedIndex;

    public ManageAdminPanel() {
        initComponent();
        buildGui();
        registerListener();
    }
    
    public void addListener(ManageAdminListener listener)
    {
        this.listener=listener;
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
        this.add(tablePane, c.xywh(2, 6,7,4));
        this.add(goButton, c.xy(4, 4,CellConstraints.CENTER, CellConstraints.CENTER));
//        this.add(filter, c.xy(6, 4));
        this.add(addButton, c.xy(6, 4));
        this.add(editButton, c.xy(8, 4));
    }

    private void initComponent() {
        titleLabel = new JLabel("Manage Admin");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        searchField= new JTextField();
        goButton = new JButton("Go!");
        addButton = new JButton("Add Admin");
        editButton = new JButton("Edit Admin");
        editButton.setEnabled(false);
        
        AdminTableModel tableModel = new AdminTableModel();
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
    
    private void registerListener()
    {
        itemTable.getSelectionModel().addListSelectionListener(this);
        itemTable.getModel().addTableModelListener(this);
        addButton.addActionListener(this);
        editButton.addActionListener(this);
        goButton.addActionListener(this);
    }

    @Override
    public void tableChanged(TableModelEvent e) {
        
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if(e.getSource().equals(itemTable.getSelectionModel()))
        {
            editButton.setEnabled(true);
            selectedIndex=itemTable.getSelectedRow();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(addButton))
        {
            listener.moveToAddUser();
        }
        if(e.getSource().equals(editButton))
        {
//            Barang prevBarang = getTableModel().getBarang((String) getTableModel().getValueAt(selectedIndex, 0));
//            listener.moveToEditItem(selectedIndex, getTableModel(), prevBarang);
        }
        if(e.getSource().equals(goButton))
        {
            
        }
    }
}
