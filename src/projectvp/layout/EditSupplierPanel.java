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
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author user
 */
public class EditSupplierPanel extends JPanel {

    private JLabel titleLabel;
    private JTextField newLocationField, newManufactureField;
    private JComboBox locationListComboBox, manfactureListComboBox;
    private JCheckBox locationNotListedCheckBox, manufactureNotListedCheckBox;
    private JButton saveButton, cancelButton;

    public EditSupplierPanel() {
        initComponent();
        buildGui();
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
        locationListComboBox = new JComboBox();
        manfactureListComboBox = new JComboBox();
        locationNotListedCheckBox = new JCheckBox("Not listed");
        manufactureNotListedCheckBox = new JCheckBox("Not Listed");
        newLocationField = new JTextField();
        newManufactureField = new JTextField();

    }

}
