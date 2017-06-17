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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import projectvp.database.Brand.Brand;
import projectvp.database.Brand.BrandService;
import projectvp.database.barang.Barang;
import projectvp.database.supplier.Supplier;
import projectvp.database.supplier.SupplierService;
import projectvp.listener.EditSupplierListener;
import projectvp.listener.ItemsListener;

/**
 *
 * @author user
 */
public class EditSupplierPanel extends JPanel implements ActionListener, ItemListener, KeyListener {

    private JLabel titleLabel;
    private JTextField newLocationField, newManufactureField;
    private JComboBox locationListComboBox, manfactureListComboBox;
    private JCheckBox locationNotListedCheckBox, manufactureNotListedCheckBox;
    private JButton saveButton, cancelButton;
    private EditSupplierListener listener;
     private int selectedIndex;
     private Supplier prevSupplier;
      private DefaultComboBoxModel manufatureModel = new DefaultComboBoxModel();
    Vector<Brand> brands = new BrandService().getBrands();
    Vector<Supplier> suppliers = new SupplierService().getSupplier();
    private DefaultComboBoxModel locationModel = new DefaultComboBoxModel();
    private String[] location={"--Choose--", "Surabaya", "Jakarta", "Malang", "Jember", "Sidoharjo", 
        "Banyuwangi","Balik Papan","Semarang","Yogyakarta","Bali","Aceh","Batu","Tanggerang","Madura"};

    public EditSupplierPanel(int selectedIndex, Supplier supplier) {
        this.selectedIndex = selectedIndex;
        this.prevSupplier = supplier;
        initComponent();
        buildGui();
        registerListener();
    }
     public void addListener(EditSupplierListener listener)
    {
        this.listener = listener;
    }
    private void buildGui() {
        this.setPreferredSize(new Dimension(500, 450));

        String column = "100px, 125px,50px,125px, 100px";
        String row = "50px, pref, 50px, pref, 10px, pref, 10px, pref, 10px, pref, 20px, pref, 20px, pref, 5px";

        FormLayout layout = new FormLayout(column, row);
        this.setLayout(layout);
        CellConstraints c = new CellConstraints();

        this.add(titleLabel, c.xyw(2, 2, 3, CellConstraints.CENTER, CellConstraints.CENTER));
        
        this.add(new JLabel("New Manufacture"), c.xy(2, 4));
        this.add(manfactureListComboBox, c.xy(4, 4));
        this.add(manufactureNotListedCheckBox, c.xy(2, 6));
        this.add(newManufactureField, c.xy(4, 6));
        newManufactureField.disable();
        
        this.add(new JLabel("New Location"), c.xy(2, 8));
        this.add(locationListComboBox, c.xy(4, 8));
        this.add(locationNotListedCheckBox, c.xy(2, 10));
        this.add(newLocationField, c.xy(4, 10));
        newLocationField.disable();

        this.add(saveButton, c.xy(2, 14));
        this.add(cancelButton, c.xy(4, 14));
    }

    private void initComponent() {
        titleLabel = new JLabel("Edit Supplier");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        saveButton = new JButton("Save");
        cancelButton = new JButton("Cancel");
        
        
        locationListComboBox = new JComboBox(locationModel);
        manfactureListComboBox = new JComboBox(manufatureModel);
        manufatureModel.addElement("--Choose--");
        int i=1, j=1;
        for(Brand a : brands)
        {
            manufatureModel.addElement(a.getName());
            if(prevSupplier.getMerek().getName().equals(a.getName()))
            {
                manfactureListComboBox.setSelectedIndex(i);
                manfactureListComboBox.setEnabled(true);
                locationModel.removeAllElements();
                locationModel.addElement("--Choose--");
                for (Supplier b : suppliers)
                {
                        locationModel.addElement(b.getLocation());
                        if(prevSupplier.getLocation().equals(b.getLocation()))
                            locationListComboBox.setSelectedIndex(j);
                        j++;
                    
                }
            }
            i++;
        }
        
        locationNotListedCheckBox = new JCheckBox("Not listed");
        manufactureNotListedCheckBox = new JCheckBox("Not Listed");
        newLocationField = new JTextField();
        newManufactureField = new JTextField();

    }
    
    public void registerListener()
    {
        saveButton.addActionListener(this);
        cancelButton.addActionListener(this);
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
    if(e.getSource().equals(cancelButton))
          {
              listener.cancelFromEditSuppiler();
          }
          if(e.getSource().equals(saveButton))
          {
             
          }
      }    

    @Override
    public void itemStateChanged(ItemEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}
