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
import javax.swing.border.TitledBorder;
import projectvp.listener.AddItemOrderListener;

/**
 *
 * @author user
 */
public class AddItemOrderPanel extends JPanel implements ActionListener {

    private JLabel titleLabel;
    private JTextField detailNamePanel, detailQuantityField;
    private JButton saveButton, cancelButton;
    private JComboBox productBox, itemTypeBox;
    private AddItemOrderListener addItemOrderListener;

    public AddItemOrderPanel() {
        initComponent();
        buildGui();
        registerListener();
    }

    public void addListener(AddItemOrderListener a) {
        this.addItemOrderListener = a;

    }

    public void registerListener() {
        productBox.addActionListener(this);
        itemTypeBox.addActionListener(this);

    }

    private void buildGui() {
        this.setPreferredSize(new Dimension(500, 450));

        String column = "110px, 130px, 25px, 130px, 100px";
        String row = "75px, pref, 50px, pref, 10px, pref, 10px, pref, 10px,pref,10px,pref, 50px,pref,50px";

        FormLayout layout = new FormLayout(column, row);
        this.setLayout(layout);
        CellConstraints c = new CellConstraints();

        this.add(titleLabel, c.xyw(2, 2, 3, CellConstraints.CENTER, CellConstraints.CENTER));
        this.add(new JLabel("Pick Product:"), c.xy(2, 4));
        this.add(productBox, c.xy(4, 4));
        this.add(new JLabel("Type:"), c.xy(2, 6));
        this.add(itemTypeBox, c.xy(4, 6));

        this.add(new JLabel("Name:"), c.xy(2, 8));
        this.add(detailNamePanel, c.xy(4, 8));
        this.add(new JLabel("Qty:"), c.xy(2, 10));
        this.add(detailQuantityField, c.xy(4, 10));

        this.add(saveButton, c.xy(4, 14));
        this.add(cancelButton, c.xy(2, 14));
    }

    private void initComponent() {
        titleLabel = new JLabel("Order Item");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        productBox = new JComboBox();
        productBox.addItem("------");
        productBox.addItem("TV");
        productBox.addItem("Refrigerator");
        productBox.addItem("Washing Machine");
        productBox.addItem("Dispenser");
        productBox.addItem("AC");

        itemTypeBox = new JComboBox();
        itemTypeBox.setEnabled(false);

        saveButton = new JButton("Save");
        cancelButton = new JButton("Cancel");

        detailNamePanel = new JTextField();
        detailNamePanel.setEnabled(false);
        detailQuantityField = new JTextField();
        detailQuantityField.setEnabled(false);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource().equals(productBox)) {
            if (productBox.getSelectedItem().equals("------")) {
                itemTypeBox.setEnabled(false);
                detailNamePanel.setEnabled(false);
                detailQuantityField.setEnabled(false);

            } else if (productBox.getSelectedItem().equals("TV")) {

                itemTypeBox.setEnabled(true);
                itemTypeBox.addItem("------");
                itemTypeBox.addItem("CRT");
                itemTypeBox.addItem("LCD");
                itemTypeBox.addItem("LED");

            }

        }
        if (e.getSource().equals(itemTypeBox)) {
            detailNamePanel.setEnabled(true);
            detailQuantityField.setEnabled(true);

        }

    }

}
