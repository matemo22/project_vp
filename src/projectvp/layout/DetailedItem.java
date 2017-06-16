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
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

/**
 *
 * @author user
 */
public class DetailedItem extends JPanel{
    private JLabel titleLabel;
    private JTextField detailNamePanel, detailPriceField, detailQuantityField,gudangField;
    private JButton saveButton, cancelButton;
    private JComboBox productBox, supplierNameBox, supplierLocationBox, itemTypeBox;
    private TitledBorder titleBorder;

    public DetailedItem() {
        initComponent();
        buildGui();
    }

    private void buildGui() {
        this.setPreferredSize(new Dimension(500, 450));

        String column = "50px, 100px, 10px, 100px, 25px, 40px,25px,30px,70px,10px";
        String row = "100px, pref, 10px, pref, 10px, pref, 10px, pref, 10px,pref,10px,pref, 50px,pref,50px";

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
        this.add(detailNamePanel, c.xyw(8, 4,2));
         this.add(new JLabel("Price:"), c.xy(6, 6));
        this.add(detailPriceField, c.xyw(8, 6,2));
         this.add(new JLabel("Qty:"), c.xy(6, 8));
        this.add(detailQuantityField, c.xyw(8, 8,2));
         this.add(new JLabel("Type:"), c.xy(6, 10));
        this.add(itemTypeBox, c.xyw(8, 10,2));
        this.add(new JLabel("Gudang:"), c.xy(6, 12));
        this.add(gudangField, c.xyw(8,12,2));
        
        this.add(saveButton, c.xy(4, 14));
        this.add(cancelButton, c.xyw(6, 14,3));
    }

    private void initComponent() {
        titleLabel = new JLabel("Detail Item");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        productBox = new JComboBox();
        supplierNameBox = new JComboBox();
        supplierLocationBox = new JComboBox();
        saveButton = new JButton("Save");
        cancelButton = new JButton("Cancel");

        detailNamePanel = new JTextField();
        detailPriceField = new JTextField();
        detailQuantityField = new JTextField();
        itemTypeBox = new JComboBox();
        gudangField= new JTextField();

    }
}
