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
import projectvp.database.Brand.Brand;
import projectvp.database.Brand.BrandService;
import projectvp.database.barang.Barang;
import projectvp.database.supplier.Supplier;
import projectvp.database.supplier.SupplierService;
import projectvp.listener.ItemsListener;
import projectvp.model.ItemTableModel;

/**
 *
 * @author user
 */
public class EditItemPanel extends JPanel implements ActionListener, ItemListener{
    private JLabel titleLabel;
    private JTextField editNameField, editPriceField, editQuantityField;
    private JButton editButton, cancelButton;
    private JComboBox productBox, supplierNameBox, supplierLocationBox, itemTypeBox;
    private TitledBorder titleBorder;
    private ItemsListener listener;
    private ItemTableModel itm;
    private int selectedIndex;
    private String[] product={"--Choose--", "TV", "Kulkas", "Mesin Cuci", "Dispenser", "AC"};
    private String[][] type={{"LED", "LCD", "CRT"}, {"Satu Pintu", "Dua Pintu"}, {"Top load", "Front load"}, {"Upside", "Downside"}, {"Inventer", "Low watt", "Standart", "Hybrid"}};
    private DefaultComboBoxModel productModel = new DefaultComboBoxModel();
    private DefaultComboBoxModel brandModel = new DefaultComboBoxModel();
    private DefaultComboBoxModel locationModel = new DefaultComboBoxModel();
    private DefaultComboBoxModel typeModel = new DefaultComboBoxModel();
    Vector<Brand> brands = new BrandService().getBrands();
    Vector<Supplier> suppliers = new SupplierService().getSupplier();
    private Barang prevBarang;
    
    public EditItemPanel(ItemTableModel itm, int selectedIndex) {
        this.itm = itm;
        this.selectedIndex = selectedIndex;
        initComponent();
        buildGui();
        registerListener();
    }
    
    public void addListener(ItemsListener listener)
    {
        this.listener = listener;
    }
    
    private void buildGui() {
        this.setPreferredSize(new Dimension(500, 450));

        String column = "50px, 100px, 10px, 100px, 25px, 50px, 25px,30px,70px,10px";
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
        this.add(editNameField, c.xyw(8, 4,2));
        this.add(new JLabel("Price: "), c.xy(6, 6));
        this.add(editPriceField, c.xyw(8, 6,2));
        this.add(new JLabel("Qty: "), c.xy(6, 8));
        this.add(editQuantityField, c.xyw(8, 8,2));
        this.add(new JLabel("Type: "), c.xy(6, 10));
        this.add(itemTypeBox, c.xyw(8, 10,2));
        
        this.add(editButton, c.xy(4, 12));
        this.add(cancelButton, c.xyw(6, 12,3));
    }

    private void initComponent() {
        titleLabel = new JLabel("Edit Item");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        
        productBox = new JComboBox();
        productBox.setModel(productModel);
        for (int i = 0; i < product.length; i++) 
        {
            productModel.addElement(product[i]);
        }
        
        supplierLocationBox = new JComboBox(locationModel);
        locationModel.addElement("--Choose--");
        
        editButton = new JButton("Edit");
        cancelButton = new JButton("Cancel");
        
        prevBarang = new Barang();
        Object[] temp = new Object[7];
        for (int i = 0; i < temp.length; i++)
        {
            temp[i]=itm.getValueAt(selectedIndex, i);
        }
        prevBarang.setName(temp[0].toString());
        
        itemTypeBox = new JComboBox(typeModel);
        typeModel.addElement("--Choose--");
        for (int i = 0; i < product.length; i++)
        {
            if(temp[1].toString().equals(product[i]))
            {
                productBox.setSelectedIndex(i);
                typeModel.removeAllElements();
                typeModel.addElement("--Choose--");
                itemTypeBox.setEnabled(true);
                for (int j = 0; j < type.length; j++)
                {
                    if(productBox.getSelectedIndex()==(j+1))
                    {
                        for (int k = 0; k < type[j].length; k++)
                        {
                            typeModel.addElement(type[j][k]);
                            if(type[j][k].equals(temp[2]))
                                itemTypeBox.setSelectedIndex(k+1);
                        }
                    }
                }
                break;
            }
        }
        
        String[] supp = temp[3].toString().split(" ");
        
        supplierNameBox = new JComboBox(brandModel);
        brandModel.addElement("--Choose--");
        int i=1, j=1;
        for(Brand a : brands)
        {
            brandModel.addElement(a.getName());
            if(supp[0].equals(a.getName()))
            {
                supplierNameBox.setSelectedIndex(i);
                supplierLocationBox.setEnabled(true);
                locationModel.removeAllElements();
                locationModel.addElement("--Choose--");
                for (Supplier b : suppliers)
                {
                    if(supplierNameBox.getSelectedItem().equals(b.getMerek().getName()))
                    {
                        locationModel.addElement(b.getLocation());
                        if(supp[1].equals(b.getLocation()))
                            supplierLocationBox.setSelectedIndex(j);
                        j++;
                    }
                }
            }
            i++;
        }
        editNameField = new JTextField();
        editNameField.setText(temp[0].toString());
        editPriceField = new JTextField();
        editPriceField.setText(temp[4].toString());
        editQuantityField = new JTextField();
        editQuantityField.setText(temp[5].toString());
        
    }
    
    public void registerListener()
    {
        editButton.addActionListener(this);
        cancelButton.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(cancelButton))
        {
            listener.cancel();
        }
        if(e.getSource().equals(editButton))
        {
            Barang a = new Barang();
            a.setName(editNameField.getText());
            a.setHarga(Integer.parseInt(editPriceField.getText()));
            a.setQty(Integer.parseInt(editQuantityField.getText()));
            a.setProduct(itemTypeBox.getSelectedItem().toString());
            a.setGudang(1);
            for (Supplier s : suppliers)
            {
                if(s.getMerek().getName().equals(supplierNameBox.getSelectedItem().toString()))
                {
                    if(s.getLocation().equals(supplierLocationBox.getSelectedItem().toString()))
                    {
                        a.setSupplier(s);
                        break;
                    }
                }
            }
            a.setJenis(productBox.getSelectedItem().toString());
            listener.editItem(a, null);
        }
    }
    
    @Override
    public void itemStateChanged(ItemEvent e) 
    {
        if(e.getSource().equals(productBox))
        {
            if(productBox.getSelectedIndex()!=0)
            {
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
                itemTypeBox.setEnabled(false);
            }
        }
        if(e.getSource().equals(supplierNameBox))
        {
            editButton.setEnabled(true);
            if(supplierNameBox.getSelectedIndex()!=0)
            {
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
                editButton.setEnabled(false);
            }
        }
    }
}
