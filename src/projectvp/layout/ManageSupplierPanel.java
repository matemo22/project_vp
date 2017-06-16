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
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import projectvp.database.barang.Barang;
import projectvp.model.ItemTableModel;

/**
 *
 * @author user
 */
public class ManageSupplierPanel extends JPanel {
    private JLabel titleLabel;
    private JTextField searchField;
    private JButton goButton, addItemButton, detailItemButton;
    private JComboBox filter;
    private JTable itemTable;
    private JScrollPane tablePane;
    private Barang[] allBarang;

    public ManageSupplierPanel() {
        initComponent();
        buildGui();
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
        this.add(addItemButton, c.xy(6, 4));
        this.add(detailItemButton, c.xy(8, 4));
    }

    private void initComponent() {
        titleLabel = new JLabel("Manage Supplier");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        searchField= new JTextField();
        goButton = new JButton("Go!");
        addItemButton = new JButton("Add Supplier");
        detailItemButton = new JButton("Edit Supplier");
        
        ItemTableModel tableModel = new ItemTableModel(); 
        itemTable = new JTable();
        itemTable.setModel(tableModel);
        itemTable.setAutoCreateRowSorter(true);
        itemTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        itemTable.setRowSelectionAllowed(true);
        itemTable.setColumnSelectionAllowed(false);
        itemTable.setRowHeight(30);
        
        
        tablePane = new JScrollPane(itemTable);
        tablePane.setPreferredSize(new Dimension(400, 100));
    }
}
