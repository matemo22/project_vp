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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import projectvp.database.barang.Barang;
import projectvp.database.supplier.Supplier;
import projectvp.listener.ManageItemListener;
import projectvp.listener.ManageSupplierListener;
import projectvp.model.ItemTableModel;
import projectvp.model.SupplierTableModel;

/**
 *
 * @author user
 */
public class ManageSupplierPanel extends JPanel implements TableModelListener, ListSelectionListener, ActionListener, KeyListener{
    private JLabel titleLabel;
    private JTextField searchField;
    private JButton goButton, addSuppButton, editSupButton;
    private JComboBox filter;
    private JTable supplierTable;
    private JScrollPane tablePane;
    private ManageSupplierListener supplierListener;
    private int selectedIndex;

    public ManageSupplierPanel() {
        initComponent();
        buildGui();
        registerListener();
    }
     public void addListener(ManageSupplierListener listener)
    {
        this.supplierListener=listener;
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
        this.add(addSuppButton, c.xy(6, 4));
        this.add(editSupButton, c.xy(8, 4));
    }

    private void initComponent() {
        titleLabel = new JLabel("Manage Supplier");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        searchField= new JTextField();
        goButton = new JButton("Go!");
        addSuppButton = new JButton("Add Supplier");
        editSupButton = new JButton("Edit Supplier");
        editSupButton.setEnabled(false);
        
        SupplierTableModel tableModel = new SupplierTableModel(); 
        supplierTable = new JTable();
        supplierTable.setModel(tableModel);
        supplierTable.setAutoCreateRowSorter(true);
        supplierTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        supplierTable.setRowSelectionAllowed(true);
        supplierTable.setColumnSelectionAllowed(false);
        supplierTable.setRowHeight(30);
        
        
        tablePane = new JScrollPane(supplierTable);
        tablePane.setPreferredSize(new Dimension(400, 100));
    }
    
    public void registerListener()
    {
        supplierTable.getSelectionModel().addListSelectionListener(this);
        supplierTable.getModel().addTableModelListener(this);
        addSuppButton.addActionListener(this);
        editSupButton.addActionListener(this);
        goButton.addActionListener(this);
        searchField.addKeyListener(this);
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if(e.getSource().equals(supplierTable.getSelectionModel()))
        {
            editSupButton.setEnabled(true);
            selectedIndex=supplierTable.getSelectedRow();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(addSuppButton))
        {
            supplierListener.moveToAddSupplier();
        }
        if(e.getSource().equals(editSupButton))
        {
            Supplier prevSupplier = getTableModel().getSupplier((String) getTableModel().getValueAt(selectedIndex, 0), (String) getTableModel().getValueAt(selectedIndex, 1));
            supplierListener.moveToEditSupplier(selectedIndex, prevSupplier);
        }
        if(e.getSource().equals(goButton))
        {
            SupplierTableModel temp = supplierListener.searchSupplier(getTableModel(), searchField.getText());
            supplierTable.setModel(temp);
        }
    }
      @Override
    public void tableChanged(TableModelEvent e) {
        
    }
    
    public SupplierTableModel getTableModel()
    {
        return (SupplierTableModel) supplierTable.getModel();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if(e.getSource().equals(searchField))
        {
            SupplierTableModel temp = supplierListener.searchSupplier(getTableModel(), searchField.getText());
            supplierTable.setModel(temp);
            if(e.getKeyChar()==('\n'))
            {
                goButton.doClick();
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
    
  
}
