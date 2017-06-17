/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectvp.layout;

import projectvp.model.ItemTableModel;
import com.jgoodies.forms.layout.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import projectvp.database.barang.Barang;
import projectvp.database.barang.BarangService;
import projectvp.listener.ManageItemListener;

/**
 *
 * @author Matemo
 */
public class ManageItemPanel extends JPanel implements TableModelListener, ListSelectionListener, ActionListener{

    private JLabel titleLabel;
    private JTextField searchField;
    private JButton goButton, addButton, editButton;
    private JTable itemTable;
    private JScrollPane tablePane;
    private Barang[] allBarang;
    private ManageItemListener listener;
    private int selectedIndex;
    
    public ManageItemPanel() {
        initComponent();
        buildGui();
        registerListener();
    }

    public void addListener(ManageItemListener listener)
    {
        this.listener=listener;
    }
    
    private void buildGui() {
        this.setPreferredSize(new Dimension(500,450));
        
        String column = "20px, 175px, 10px, pref, 15px, 95px, 15px, 95px, 20px";
        String row = "50px, pref, 25px, pref, 20px, pref, 20px, pref, 250px";
        
        FormLayout layout = new FormLayout(column, row);
        this.setLayout(layout);
        CellConstraints c = new CellConstraints();
        
        this.add(titleLabel, c.xyw(4,2,2, CellConstraints.CENTER, CellConstraints.CENTER));
        this.add(searchField,c.xy(2, 4));
        this.add(tablePane, c.xywh(2, 6,7,4));
        this.add(goButton, c.xy(4, 4,CellConstraints.CENTER, CellConstraints.CENTER));
        this.add(addButton, c.xy(6, 4));
        this.add(editButton, c.xy(8, 4));
    }

    private void initComponent() {
        titleLabel = new JLabel("Manage Item");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        searchField= new JTextField();
        goButton = new JButton("Go!");
        addButton = new JButton("Add Item");
        editButton = new JButton("Edit Item");
        editButton.setEnabled(false);
        
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
    
    public void registerListener()
    {
        itemTable.getSelectionModel().addListSelectionListener(this);
        itemTable.getModel().addTableModelListener(this);
        addButton.addActionListener(this);
        editButton.addActionListener(this);
        goButton.addActionListener(this);
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
            listener.moveToAddItem();
        }
        if(e.getSource().equals(editButton))
        {
            Barang prevBarang = getTableModel().getBarang((String) getTableModel().getValueAt(selectedIndex, 0));
            listener.moveToEditItem(selectedIndex, prevBarang);
        }
        if(e.getSource().equals(goButton))
        {
            
        }
    }

    @Override
    public void tableChanged(TableModelEvent e) {
        
    }
    
    public ItemTableModel getTableModel()
    {
        return (ItemTableModel) itemTable.getModel();
    }
}
