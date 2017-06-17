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
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.TableModel;
import projectvp.database.supplier.Supplier;
import projectvp.listener.AddItemOrderListener;
import projectvp.listener.EditOrderItemListener;
import projectvp.model.OrderItemModel;

/**
 *
 * @author user
 */
public class EditOrderItemPanel extends JPanel implements ActionListener, ItemListener, KeyListener {

    private JLabel titleLabel;
    private JTextField detailNamePanel, detailQuantityField;
    private JButton saveButton, cancelButton;
    private JComboBox productBox, itemTypeBox;
    private String[] product = {"--Choose--", "TV", "Kulkas", "Mesin Cuci", "Dispenser", "AC"};
    private String[][] type = {{"LED", "LCD", "CRT"}, {"Satu Pintu", "Dua Pintu"}, {"Top Load", "Front Load"}, {"Upside", "Downside"}, {"AC Standart", "AC Low Watt", "AC Hybrid", "AC Inverter"}};
    private DefaultComboBoxModel productModel = new DefaultComboBoxModel();
    private DefaultComboBoxModel itemTypeModel = new DefaultComboBoxModel();
    private EditOrderItemListener editItemOrderListener;
    private OrderItemPanel oip;
    private OrderItemModel oim;
    private int selectedRow;
    private String location;

    public OrderItemPanel getOip() {
        return oip;
    }

    public EditOrderItemPanel() {
        initComponent();
        buildGui();
        registerListener();
    }

    public EditOrderItemPanel(int row, OrderItemModel table) {
        
        oim = table;
        selectedRow = row;
        initComponent();
        buildGui();
        registerListener();
        
      
    }

    public void addListener(EditOrderItemListener a) {
        this.editItemOrderListener = a;

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

        this.add(saveButton, c.xy(2, 14));
        this.add(cancelButton, c.xy(4, 14));
    }

    private void initComponent() {
        titleLabel = new JLabel("Edit Item");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        productBox = new JComboBox();
        productBox.setModel(productModel);
        for (int i = 0; i < product.length; i++) {
            productModel.addElement(product[i]);
        }
        
        itemTypeBox = new JComboBox(itemTypeModel);
         itemTypeModel.addElement("--Choose--");
         
        Object[] temp= new Object[5];
        for (int i = 0; i < temp.length; i++) {
            temp[i]= oim.getValueAt(selectedRow, i);
        }
        location = temp[3].toString();
        
        for (int i = 0; i < product.length; i++) 
        {

            if (temp[1].toString().equals(product[i])) 
            {
                productBox.setSelectedIndex(i);
                itemTypeModel.removeAllElements();
                itemTypeModel.addElement("--Choose--");
                itemTypeBox.setEnabled(true);
                for (int j = 0; j < type.length; j++) 
                { 
                    if (productBox.getSelectedIndex() == (j + 1)) 
                    {
                        for (int k = 0; k < type[j].length; k++) 
                        {
                            itemTypeModel.addElement(type[j][k]);
                            if (type[j][k].equals(temp[2]))
                            {
                                itemTypeBox.setSelectedIndex(k + 1);
                            }
                        }
                    }
                }
                   break;
            }
         
        }

        saveButton = new JButton("Save");
        cancelButton = new JButton("Cancel");

        detailNamePanel = new JTextField();
        detailNamePanel.setText(temp[0].toString());
        detailQuantityField = new JTextField();
        detailQuantityField.setText(temp[4].toString());

    }

    public void registerListener() {
        productBox.addItemListener(this);
        saveButton.addActionListener(this);
        cancelButton.addActionListener(this);
        detailQuantityField.addKeyListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource().equals(saveButton)) {
        Object[] newOrder = new Object[5];
            newOrder[0] = this.detailNamePanel.getText();
            newOrder[1] = this.productBox.getSelectedItem();
            newOrder[2] = this.itemTypeBox.getSelectedItem();
            newOrder[3] = location;
            newOrder[4] = this.detailQuantityField.getText();
            editItemOrderListener.saveOrderEdit(newOrder,selectedRow);

        }
        if (e.getSource().equals(cancelButton)) {
            editItemOrderListener.cancelToOrderEdit();
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource().equals(productBox)) {
            if (productBox.getSelectedIndex() != 0) {
                saveButton.setEnabled(true);
                detailNamePanel.setEnabled(true);
                detailQuantityField.setEnabled(true);
                itemTypeBox.setEnabled(true);
                itemTypeModel.removeAllElements();
                itemTypeModel.addElement("--Choose--");
                for (int i = 0; i < type.length; i++) {
                    if (productBox.getSelectedIndex() == (i + 1)) {
                        for (int j = 0; j < type[i].length; j++) {
                            itemTypeModel.addElement(type[i][j]);
                        }
                    }
                }
            } else {
                saveButton.setEnabled(false);
                detailNamePanel.setEnabled(false);
                detailQuantityField.setEnabled(false);
                itemTypeBox.setEnabled(false);
            }
        }
    }

    public JTextField getDetailNamePanel() {
        return detailNamePanel;
    }

    public JTextField getDetailQuantityField() {
        return detailQuantityField;
    }

    public JComboBox getProductBox() {
        return productBox;
    }

    public JComboBox getItemTypeBox() {
        return itemTypeBox;
    }

    public DefaultComboBoxModel getProductModel() {
        return productModel;
    }

    public DefaultComboBoxModel getItemTypeModel() {
        return itemTypeModel;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if(e.getSource().equals(detailQuantityField))
        {
            if(e.getKeyChar() < '0' || e.getKeyChar() > '9'){
                e.consume();
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
