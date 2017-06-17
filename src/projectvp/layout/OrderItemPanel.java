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
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Date;
import java.util.Calendar;
import java.util.Vector;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import projectvp.database.Brand.Brand;
import projectvp.database.Brand.BrandService;
import projectvp.database.barang.Barang;
import projectvp.database.order.Order;
import projectvp.database.supplier.Supplier;
import projectvp.database.supplier.SupplierService;
import projectvp.database.user.User;
import projectvp.listener.OrderItemListener;
import projectvp.model.ItemTableModel;
import projectvp.model.OrderItemModel;

/**
 *
 * @author user
 */
public class OrderItemPanel extends JPanel implements ActionListener, TableModelListener, ListSelectionListener,
        ItemListener {

    private JLabel titleLabel;
    private JComboBox pickSupplier, pickSuppLocation;
    private JButton addOrder, finishOrder, deleteOrder, clearAll, editOrder;
    private JTable orderTable;
    private JScrollPane tablePane;
    private OrderItemListener orderItemListener;
    private DefaultComboBoxModel suppplierModel = new DefaultComboBoxModel();
    Vector<Brand> brands = new BrandService().getBrands();
    private DefaultComboBoxModel locationSuppModel = new DefaultComboBoxModel();
    Vector<Supplier> suppliers = new SupplierService().getSupplier();
    private OrderItemModel oim;
    int selectedRow;
    private User currentUser;
             
    public OrderItemPanel(User user) {
        initComponent();
        buildGui();
        registerListener();
        this.currentUser=user;
    }

    public void addListener(OrderItemListener a) {
        this.orderItemListener = a;
    }

    private void registerListener() {
        orderTable.getSelectionModel().addListSelectionListener(this);
        orderTable.getModel().addTableModelListener(this);
        addOrder.addActionListener(this);
        editOrder.addActionListener(this);
        pickSupplier.addItemListener(this);
        deleteOrder.addActionListener(this);
        finishOrder.addActionListener(this);
        clearAll.addActionListener(this);
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
        addOrder.setEnabled(false);
        deleteOrder = new JButton("Delete");
        deleteOrder.setEnabled(false);
        editOrder = new JButton("Edit");
        editOrder.setEnabled(false);
        clearAll = new JButton("Clear All");
        finishOrder = new JButton("Order!");
        finishOrder.setEnabled(false);

        pickSupplier = new JComboBox(suppplierModel);
        suppplierModel.addElement("--Choose--");
        for (Brand a : brands) {
            suppplierModel.addElement(a.getName());
        }

        pickSuppLocation = new JComboBox(locationSuppModel);
        pickSuppLocation.setEnabled(false);

        OrderItemModel tableModel = new OrderItemModel();
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

    public OrderItemModel getOrderTable() {
        return (OrderItemModel) orderTable.getModel();
    }

    public JComboBox getPickSupplier() {
        return pickSupplier;
    }

    public JComboBox getPickSuppLocation() {
        return pickSuppLocation;
    }

    public DefaultComboBoxModel getLocationSuppModel() {
        return locationSuppModel;
    }

    public DefaultComboBoxModel getSuppplierModel() {
        return suppplierModel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(addOrder)) {
            
            orderItemListener.moveToAddItemOrder(pickSupplier.getSelectedItem(), pickSuppLocation.getSelectedItem());

        }
        
        if (e.getSource().equals(deleteOrder)) {
           OrderItemModel oim= (OrderItemModel) orderTable.getModel();
           oim.hapus(orderTable.getSelectedRow());
           if(oim.getRowCount()==0)
           {
               editOrder.setEnabled(false);
               deleteOrder.setEnabled(false);
           }
        }
        if(e.getSource().equals(editOrder)){
           orderItemListener.moveToEditItemOrder(selectedRow, (OrderItemModel)orderTable.getModel());
        }
        
        if(e.getSource().equals(clearAll))
        {
            OrderItemModel oim= (OrderItemModel) orderTable.getModel();
            oim.hapusAll();
            editOrder.setEnabled(false);
            deleteOrder.setEnabled(false);
        }
         if(e.getSource().equals(finishOrder)){
            Vector<Order> orders= new Vector<Order>();
            for (int i = 0; i < orderTable.getRowCount(); i++) 
            {
                Order newOrder = new Order();
                newOrder.setNamaBarang(orderTable.getValueAt(i, 0).toString());
                newOrder.setProdukName(orderTable.getValueAt(i, 1).toString());
                newOrder.setProdukType(orderTable.getValueAt(i, 2).toString());
                String[] split;

                String a= orderTable.getValueAt(i, 3).toString();
                split= a.split(" ");
                String suppName=split[0];
                String suppLoc=split[1];
                 for (Supplier s : suppliers)
                {
                    if(s.getMerek().getName().equals(suppName))
                    {
                        if(s.getLocation().equals(suppLoc))
                        {
                            newOrder.setSupplier(s);
                            break;
                        }
                    }
                }
                newOrder.setQty(Integer.parseInt(orderTable.getValueAt(i, 4).toString()));
                java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
                newOrder.setDate(date);
              
                newOrder.setUser(this.currentUser);
                orders.add(newOrder);
            }
            orderItemListener.finishOrder(orders);     
                 
            orderTable.removeAll();;
         }

    }

    @Override
    public void tableChanged(TableModelEvent tme) {
        if (!orderTable.equals(null)) {
            finishOrder.setEnabled(true);

        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getSource().equals(orderTable.getSelectionModel())) {
            deleteOrder.setEnabled(true);
            editOrder.setEnabled(true);
            selectedRow=orderTable.getSelectedRow();
        }
      
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource().equals(pickSupplier)) {
            if (pickSupplier.getSelectedIndex() != 0) {
                //Isi SupplierLocationBox
                pickSuppLocation.setEnabled(true);
                locationSuppModel.removeAllElements();
                locationSuppModel.addElement("--Choose--");
                for (Supplier a : suppliers) {
                    if (pickSupplier.getSelectedItem().equals(a.getMerek().getName())) {
                        locationSuppModel.addElement(a.getLocation());
                    }
                }
                addOrder.setEnabled(true);
            } else {
                locationSuppModel.removeAllElements();
                pickSuppLocation.setEnabled(false);
            }
        }
    }

    public OrderItemModel getOim() {
        return oim;
    }

    public void setOim(OrderItemModel oim) {
        this.oim = oim;
    }
    
    public Supplier getSupplier()
    {
        for (Supplier s : suppliers) {
            if(s.getMerek().getName().equals(pickSupplier.getSelectedItem()))
            {
                if(s.getLocation().equals(pickSuppLocation.getSelectedItem()))
                {
                    return s;
                }
            }
        }
        return null;
    }
}
