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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import projectvp.database.barang.Barang;
import projectvp.listener.OrderItemListener;
import projectvp.model.ItemTableModel;

/**
 *
 * @author user
 */
public class OrderItemPanel extends JPanel implements ActionListener, TableModelListener, ListSelectionListener{

    private JLabel titleLabel;
    private JComboBox pickSupplier, pickSuppLocation;
    private JButton addOrder, finishOrder, deleteOrder, clearAll, editOrder;
    private JTable itemTable;
    private JScrollPane tablePane;
    private Barang[] allBarang;
    private OrderItemListener orderItemListener;

    public OrderItemPanel() {
        initComponent();
        buildGui();
        registerListener();
    }
    public void addListener(OrderItemListener a){
        this.orderItemListener= a;
    }
    
     private void registerListener() {
         addOrder.addActionListener(this);
    }

    private void buildGui() {
        this.setPreferredSize(new Dimension(500, 450));

        String column = "20px, 100px, 25px, 100px, 25px, 100px, 25px, 95px";
        String row = "50px, pref, 25px, pref, 20px, pref, 20px, pref, 200px,20px,pref, 25px";

        FormLayout layout = new FormLayout(column, row);
        this.setLayout(layout);
        CellConstraints c = new CellConstraints();

        this.add(titleLabel, c.xyw(4, 2, 3, CellConstraints.CENTER, CellConstraints.CENTER));
        this.add(pickSupplier, c.xy(2, 4));
        this.add(pickSuppLocation, c.xy(4, 4));
        this.add(addOrder, c.xy(8, 4));
        this.add(tablePane, c.xywh(2, 6, 7, 4));

        this.add(editOrder, c.xy(2, 11));
        this.add(deleteOrder, c.xy(4, 11));
        this.add(clearAll, c.xy(6, 11));

        this.add(finishOrder, c.xy(8, 11));
    }

    private void initComponent() {
        titleLabel = new JLabel("Order Item");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        addOrder = new JButton("Add Item");
        deleteOrder = new JButton("Delete");
        deleteOrder.setEnabled(false);
        editOrder = new JButton("Edit");
        editOrder.setEnabled(false);
        clearAll = new JButton("Clear All");
        clearAll.setEnabled(false);
        finishOrder = new JButton("Order!");
        finishOrder.setEnabled(false);
        pickSupplier = new JComboBox();
        pickSupplier.addItem("Supplier");
        pickSuppLocation = new JComboBox();
        pickSuppLocation.addItem("Location");

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

     
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(addOrder)) {
            orderItemListener.moveToAddItemOrder();
            
        }
        if (e.getSource().equals(itemTable.isRowSelected(0))) {
            deleteOrder.setEnabled(true);
            editOrder.setEnabled(true);
            
        }
        
    }

    @Override
    public void tableChanged(TableModelEvent tme) {
    }
    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (itemTable.getSelectedRow() > -1) {
            deleteOrder.setEnabled(true);
            editOrder.setEnabled(true);
        }
    }

}
