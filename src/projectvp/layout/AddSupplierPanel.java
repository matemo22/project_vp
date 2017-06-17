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
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import projectvp.database.Brand.Brand;
import projectvp.database.Brand.BrandService;
import projectvp.database.barang.Barang;
import projectvp.database.supplier.Supplier;
import projectvp.listener.AddSupplierListener;
import projectvp.listener.ManageSupplierListener;

/**
 *
 * @author user
 */
public class AddSupplierPanel extends JPanel implements ActionListener, ItemListener{

    private JLabel titleLabel;
    private JTextField newLocationField, newManufactureField;
    private JComboBox locationListComboBox, manfactureListComboBox;
    private JCheckBox locationNotListedCheckBox, manufactureNotListedCheckBox;
    private JButton saveButton, cancelButton;
    private AddSupplierListener listener;
    private int selectedIndex;
    private DefaultComboBoxModel manufatureModel = new DefaultComboBoxModel();
    Vector<Brand> brands = new BrandService().getBrands();
    private DefaultComboBoxModel locationModel = new DefaultComboBoxModel();
    private String[] location={"--Choose--", "Surabaya", "Jakarta", "Malang", "Jember", "Sidoharjo", 
        "Banyuwangi","Balik Papan","Semarang","Yogyakarta","Bali","Aceh","Batu","Tanggerang","Madura"};
    
    public AddSupplierPanel() {
        initComponent();
        buildGui();
        registerListener();
    }
    public void addListener(AddSupplierListener listener)
    {
        this.listener=listener;
    }

    private void buildGui() {
        this.setPreferredSize(new Dimension(500, 450));

        String column = "100px, 125px,50px,125px, 100px";
        String row = "50px, pref, 50px, pref, 10px, pref, 10px, pref, 10px, pref, 20px, pref, 20px, pref, 5px";

        FormLayout layout = new FormLayout(column, row);
        this.setLayout(layout);
        CellConstraints c = new CellConstraints();

        this.add(titleLabel, c.xyw(2, 2, 3, CellConstraints.CENTER, CellConstraints.CENTER));
        
        this.add(new JLabel("Manufacture Name"), c.xy(2, 4));
        this.add(manfactureListComboBox, c.xy(4, 4));
        this.add(manufactureNotListedCheckBox, c.xy(2, 6));
        this.add(newManufactureField, c.xy(4, 6));
        newManufactureField.disable();
        
        this.add(new JLabel("Location"), c.xy(2, 8));
        this.add(locationListComboBox, c.xy(4, 8));
        this.add(locationNotListedCheckBox, c.xy(2, 10));
        this.add(newLocationField, c.xy(4, 10));
        newLocationField.disable();

        this.add(saveButton, c.xy(2, 14));
        this.add(cancelButton, c.xy(4, 14));
    }
 public void registerListener()
    {
        locationListComboBox.addActionListener(this);
        locationNotListedCheckBox.addActionListener(this);
        manfactureListComboBox.addActionListener(this);
        manufactureNotListedCheckBox.addActionListener(this);
        saveButton.addActionListener(this);
        cancelButton.addActionListener(this);
    }
    private void initComponent() {
        titleLabel = new JLabel("Add Supplier");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        saveButton = new JButton("Save");
        cancelButton = new JButton("Cancel");
        manfactureListComboBox = new JComboBox(manufatureModel);
        manufatureModel.addElement("--Choose--");
        for (Brand a : brands) {
            manufatureModel.addElement(a.getName());
        }
        
        locationListComboBox = new JComboBox();
         locationListComboBox.setModel(locationModel);
        for (int i = 0; i < location.length; i++)
        {
            locationModel.addElement(location[i]);
        }
        locationListComboBox.setSelectedIndex(0);
        
        locationNotListedCheckBox = new JCheckBox("Not listed");
        manufactureNotListedCheckBox = new JCheckBox("Not Listed");
        newLocationField = new JTextField();
        newManufactureField = new JTextField();

    }
@Override
    public void actionPerformed(ActionEvent e) 
    {
          if(e.getSource().equals(saveButton))
        {
            Supplier newSupplier = new Supplier();
            Brand newBrand= new Brand();
            
            if (manufactureNotListedCheckBox.isSelected()) {
                newBrand.setName(newManufactureField.getText());
                
            }
            else{
                newBrand.setName(manfactureListComboBox.getSelectedItem().toString());
            }
            
            if (locationNotListedCheckBox.isSelected()) {
                newSupplier.setLocation(newLocationField.getText());
                
            }
            else{
                newSupplier.setLocation(locationListComboBox.getSelectedItem().toString());
            }
            
            listener.saveSupplier(newSupplier, newBrand);
        }
        if(e.getSource().equals(cancelButton))
        {
            listener.cancelToManageSupplier();
        } 
        if(manufactureNotListedCheckBox.isSelected())
        {
            manfactureListComboBox.setEnabled(false);
            newManufactureField.setEnabled(true);
        }
        else{
            manfactureListComboBox.setEnabled(true);
            newManufactureField.setEnabled(false);
        }
        if(locationNotListedCheckBox.isSelected())
        {
            locationListComboBox.setEnabled(false);
            newLocationField.setEnabled(true);
        }
        else{
            locationListComboBox.setEnabled(true);
            newLocationField.setEnabled(false);
        }
      
        
    }
    
    @Override
    public void itemStateChanged(ItemEvent e) {
     
    }
}
