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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JLabel;
import javax.swing.JPanel;
import projectvp.database.user.User;
import projectvp.listener.MasterListener;

/**
 *
 * @author Matemo
 */
public class AdminPanel extends JPanel implements ActionListener, MouseListener{
private JLabel titleLabel, manageItem, manageSupplier, orderItem;
//    private JButton manageItem, manageAdmin, manageSupplier;
    private User currentUser;
    private MasterListener listener;

    public AdminPanel(User currentUser) {
        initComponent();
        buildGui();
        registerListener();
        this.currentUser = currentUser;
    }

    public void addListener(MasterListener listener) {
        this.listener = listener;
    }

    private void buildGui() {
        this.setPreferredSize(new Dimension(500, 450));

        String column = "85px, pref, 100px";
        String row = "50px, pref, 50px, pref, 20px, pref, 20px, pref, 20px,pref,20px";

        FormLayout layout = new FormLayout(column, row);
        this.setLayout(layout);
        CellConstraints c = new CellConstraints();

        this.add(titleLabel, c.xy(2, 2, CellConstraints.CENTER, CellConstraints.CENTER));
        this.add(manageItem, c.xy(2, 4, CellConstraints.CENTER, CellConstraints.CENTER));
        this.add(manageSupplier, c.xy(2, 6, CellConstraints.CENTER, CellConstraints.CENTER));
        this.add(orderItem, c.xy(2, 8, CellConstraints.CENTER, CellConstraints.CENTER));

    }

    private void initComponent() {
        titleLabel = new JLabel("Admin Menu");
        titleLabel.setFont(new Font("Arial", Font.PLAIN, 55));
        manageItem = new JLabel("Manage Item");
        manageItem.setFont(new Font("Arial", Font.PLAIN, 30));
        manageSupplier = new JLabel("Manage Supplier");
        manageSupplier.setFont(new Font("Arial", Font.PLAIN, 30));
        orderItem= new JLabel("Order Item");
        orderItem.setFont(new Font("Arial", Font.PLAIN, 30));
//        manageItem = new JButton("Manage Item");
//        manageAdmin = new JButton("Manage Admin");
//        manageSupplier = new JButton("Manage Supplier");
    }

    private void registerListener() {
        manageItem.addMouseListener(this);
        manageSupplier.addMouseListener(this);
        orderItem.addMouseListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource().equals(manageItem)) {
            listener.moveToManageItem();
        }
        if (e.getSource().equals(manageSupplier)) {
            listener.moveToManageSupplier();
        }
         if (e.getSource().equals(orderItem)) {
            listener.moveToOrderItem(currentUser);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent me) {

    }
}
