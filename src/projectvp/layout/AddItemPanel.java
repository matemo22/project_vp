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
import java.util.Vector;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import projectvp.database.Brand.Brand;
import projectvp.database.Brand.BrandService;
import projectvp.database.barang.Barang;
import projectvp.database.supplier.Supplier;
import projectvp.database.supplier.SupplierService;
import projectvp.listener.ItemsListener;

/**
 *
 * @author user
 */
public class AddItemPanel extends JPanel implements ActionListener, ItemListener{

    private JLabel titleLabel;
    private JTextField itemNameField, itemPriceField, itemQuantityField;
    private JButton saveButton, cancelButton;
    private JComboBox productBox, supplierNameBox, supplierLocationBox, itemTypeBox;
    private TitledBorder titleBorder;
    private String[] product={"--Choose--", "TV", "Kulkas", "Mesin Cuci", "Dispenser", "AC"};
    private String[][] type={{"LED", "LCD", "CRT"}, {"Satu Pintu", "Dua Pintu"}, {"Top load", "Front load"}, {"Upside", "Downside"}, {"Inventer", "Low watt", "Standart", "Hybrid"}};
    private DefaultComboBoxModel productModel = new DefaultComboBoxModel();
    private DefaultComboBoxModel brandModel = new DefaultComboBoxModel();
    private DefaultComboBoxModel locationModel = new DefaultComboBoxModel();
    private DefaultComboBoxModel typeModel = new DefaultComboBoxModel();
    Vector<Brand> brands = new BrandService().getBrands();
    Vector<Supplier> suppliers = new SupplierService().getSupplier();
    ItemsListener listener;
    
    public void addListener(ItemsListener listener)
    {
        this.listener=listener;
    }
    
    public AddItemPanel() {
        initComponent();
        buildGui();
        registerListener();
    }

    private void buildGui() {
        this.setPreferredSize(new Dimension(500, 450));

        String column = "50px, 100px, 10px, 100px, 25px, pref, 25px,30px,70px,50px";
        
        String row = "100px, pref, 10px, pref, 10px, pref, 10px, pref, 10px,pref,50px,pref, 50px";

        FormLayout layout = new FormLayout(column, row);
        this.setLayout(layout);
        CellConstraints c = new CellConstraints();

        this.add(titleLabel, c.xyw(3, 1, 4, CellConstraints.CENTER, CellConstraints.CENTER));
        this.add(new JLabel("Pick Product"), c.xy(2, 4));
        this.add(productBox, c.xyw(2, 6, 3));
        this.add(new JLabel("Pick Supplier"), c.xy(2, 8));
        this.add(supplierNameBox, c.xy(2, 10));
        this.add(supplierLocationBox, c.xy(4, 10));

        this.add(new JLabel("Name:"), c.xy(6, 4));
        this.add(itemNameField, c.xyw(8, 4,2));
        this.add(new JLabel("Price: "), c.xy(6, 6));
        this.add(itemPriceField, c.xyw(8, 6,2));
        this.add(new JLabel("Qty: "), c.xy(6, 8));
        this.add(itemQuantityField, c.xyw(8, 8,2));
        this.add(new JLabel("Type: "), c.xy(6, 10));
        this.add(itemTypeBox, c.xyw(8, 10,2));
        
        this.add(saveButton, c.xy(4, 12));
        this.add(cancelButton, c.xyw(6, 12,3));
    }

    private void initComponent() {
        titleLabel = new JLabel("Add Item");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        
        productBox = new JComboBox();
        productBox.setModel(productModel);
        for (int i = 0; i < product.length; i++)
        {
            productModel.addElement(product[i]);
        }
        productBox.setSelectedIndex(0);
        
        supplierNameBox = new JComboBox(brandModel);
        brandModel.addElement("--Choose--");
        for (Brand a : brands)
        {
           brandModel.addElement(a.getName());
        }
        
        supplierLocationBox = new JComboBox(locationModel);
        
        supplierLocationBox.setEnabled(false);
        
        saveButton = new JButton("Save");
        cancelButton = new JButton("Cancel");
        
        itemNameField = new JTextField();
        itemPriceField = new JTextField();
        itemQuantityField = new JTextField();
        itemTypeBox = new JComboBox(typeModel);
        saveButton.setEnabled(false);
        itemNameField.setEnabled(false);
        itemPriceField.setEnabled(false);
        itemQuantityField.setEnabled(false);
        itemTypeBox.setEnabled(false);
    }

    public void registerListener()
    {
        productBox.addItemListener(this);
        supplierNameBox.addItemListener(this);
        saveButton.addActionListener(this);
        cancelButton.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(saveButton))
        {
            Barang newBarang = new Barang();
            newBarang.setName(itemNameField.getText());
            newBarang.setProduct(productBox.getSelectedItem().toString());
            newBarang.setJenis(itemTypeBox.getSelectedItem().toString());
            for (Supplier s : suppliers)
            {
                if(s.getMerek().getName().equals(supplierNameBox.getSelectedItem().toString()))
                {
                    if(s.getLocation().equals(supplierLocationBox.getSelectedItem().toString()))
                    {
                        newBarang.setSupplier(s);
                        break;
                    }
                }
            }
            newBarang.setHarga(Integer.parseInt(itemPriceField.getText()));
            newBarang.setQty(Integer.parseInt(itemQuantityField.getText()));
            if(productBox.getSelectedItem().toString().equals("TV") || productBox.getSelectedItem().toString().equals("Kulkas"))
                newBarang.setGudang(1);
            else if(productBox.getSelectedItem().toString().equals("Mesin Cuci"))
                newBarang.setGudang(2);
            else if(productBox.getSelectedItem().toString().equals("Dispenser") || productBox.getSelectedItem().toString().equals("AC"))
                newBarang.setGudang(3);
            listener.saveItem(newBarang);
        }
        if(e.getSource().equals(cancelButton))
        {
            listener.cancel();
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if(e.getSource().equals(productBox))
        {
            if(productBox.getSelectedIndex()!=0)
            {
                saveButton.setEnabled(true);
                itemNameField.setEnabled(true);
                itemPriceField.setEnabled(true);
                itemQuantityField.setEnabled(true);
                itemTypeBox.setEnabled(true);
                typeModel.removeAllElements();
                typeModel.addElement("--Choose--");
                for (int i = 0; i < type.length; i++)
                {
                    if(productBox.getSelectedIndex()==(i+1))
                    {
                        for (int j = 0; j < type[i].length; j++)
                        {
                            typeModel.addElement(type[i][j]);
                        }
                    }
                }
            }
            else
            {
                saveButton.setEnabled(false);
                itemNameField.setEnabled(false);
                itemPriceField.setEnabled(false);
                itemQuantityField.setEnabled(false);
                itemTypeBox.setEnabled(false);
            }
        }
        if(e.getSource().equals(supplierNameBox))
        {
            if(supplierNameBox.getSelectedIndex()!=0)
            {
                //Isi SupplierLocationBox
                supplierLocationBox.setEnabled(true);
                locationModel.removeAllElements();
                locationModel.addElement("--Choose--");
                for (Supplier a : suppliers)
                {
                    if(supplierNameBox.getSelectedItem().equals(a.getMerek().getName()))
                    {
                        locationModel.addElement(a.getLocation());
                    }
                }
            }
            else
            {
                locationModel.removeAllElements();
                supplierLocationBox.setEnabled(false);
            }
        }
    }
}
