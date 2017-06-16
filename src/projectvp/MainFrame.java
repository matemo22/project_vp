/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectvp;

import projectvp.layout.ManageItemPanel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;
import javax.swing.*;
import javax.swing.table.TableModel;
import projectvp.database.barang.Barang;
import projectvp.database.user.User;
import projectvp.layout.AddItemOrderPanel;
import projectvp.layout.AddItemPanel;
import projectvp.layout.EditItemPanel;
import projectvp.layout.EditOrderItemPanel;
import projectvp.model.ItemTableModel;
import projectvp.layout.LoginPanel;
import projectvp.layout.ManageAdminPanel;
import projectvp.layout.ManageSupplierPanel;
import projectvp.layout.MasterPanel;
import projectvp.layout.OrderItemPanel;
import projectvp.listener.AddItemOrderListener;
import projectvp.listener.EditOrderItemListener;
import projectvp.listener.ItemsListener;
import projectvp.listener.LoginListener;
import projectvp.listener.ManageItemListener;
import projectvp.listener.ManageSupplierListener;
import projectvp.listener.MasterListener;
import projectvp.listener.OrderItemListener;
import projectvp.model.BarangModel;
import projectvp.model.LoginModel;
import projectvp.model.OrderItemModel;

/**
 *
 * @author Matemo
 */
public class MainFrame extends JFrame
implements LoginListener, ActionListener, KeyListener, MasterListener, ManageItemListener,
        ItemsListener, OrderItemListener, EditOrderItemListener,ManageSupplierListener, AddItemOrderListener
    {
    private Vector<JPanel> historyPanel=new Vector<JPanel>();
    private JMenuBar menuBar, loginMenuBar;
    private JMenu option, menu, currentUserMenu;
    private JMenuItem home, editProfile, language, manageItem, manageAdmin, manageSupplier, logout;
    private User currentUser;
    
    public MainFrame()
    {
        initContainer();
    }
    
    private void initContainer()
    {
        buildMenu();
        this.setSize(new Dimension(500, 500));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        LoginPanel panel = new LoginPanel();
        this.setContentPane(panel);
        panel.addLoginListener(this);
        this.setVisible(true);
        this.pack();
    }
    
    public void buildMenu()
    {
        menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);
        option = new JMenu("Option");
        option.setMnemonic(KeyEvent.VK_O);
        language = new JMenuItem("Language");
        language.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.CTRL_MASK));
        language.setMnemonic(KeyEvent.VK_L);
        language.addActionListener(this);
        option.add(language);
        logout = new JMenuItem("Logout");
        logout.addActionListener(this);
//        menuBar.add(Box.createHorizontalGlue());
//        menuBar.add(option);
        editProfile = new JMenuItem("Edit Profile");
        editProfile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
        editProfile.setMnemonic(KeyEvent.VK_E);
        editProfile.addActionListener(this);
        menu = new JMenu("Menu");
        menu.setMnemonic(KeyEvent.VK_M);
        home = new JMenuItem("Home");
        home.setMnemonic(KeyEvent.VK_H);
        home.addActionListener(this);
        manageItem = new JMenuItem("Manage Item");
        manageItem.addActionListener(this);
        manageItem.setMnemonic(KeyEvent.VK_I);
        manageAdmin = new JMenuItem("Manage Admin");
        manageAdmin.addActionListener(this);
        manageAdmin.setMnemonic(KeyEvent.VK_A);
        manageSupplier = new JMenuItem("Manage Supplier");
        manageSupplier.addActionListener(this);
        manageSupplier.setMnemonic(KeyEvent.VK_S);
        menu.add(option);
        menuBar.add(menu);
//        menuBar.add(option);
    }
    
    public static void main(String[] args) {
        MainFrame m = new MainFrame();
    }

    @Override
    public User attemptLogin(String username, String password) {
        LoginModel lm = new LoginModel();
        User u = lm.checkLogin(username, password);
        return u;
    }

    @Override
    public void loginSucceed(User user) {
        currentUser = user;
        if(currentUser!=null)
        {
            menuBar.add(Box.createHorizontalGlue());
            currentUserMenu = new JMenu(currentUser.getUsername());
            currentUserMenu.add(editProfile);
            currentUserMenu.addSeparator();
            currentUserMenu.add(logout);
            menuBar.add(currentUserMenu);
            if(currentUser.getAuthority()==1)
            {
                MasterPanel masterPanel = new MasterPanel(currentUser);
                masterPanel.addListener(this);
                this.setVisible(false);
                this.setContentPane(masterPanel);
                menu.remove(option);
                menu.add(home);
                menu.add(manageItem);
                menu.add(manageAdmin);
                menu.add(manageSupplier);
                menu.addSeparator();
    //            option.add(profile);
                menu.add(option);
                this.pack();
                this.setVisible(true);
                historyPanel.add(masterPanel);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(language))
        {
            //Tombol Language di tekan
        }
        if(e.getSource().equals(editProfile))
        {
            //Tombol Language di tekan
        }
        if(e.getSource().equals(logout))
        {
            currentUser=null;
            historyPanel.removeAllElements();
            this.setVisible(false);
            menuBar.removeAll();
            menu.removeAll();
            menu.add(option);
            menuBar.add(menu);
            LoginPanel panel = new LoginPanel();
            this.setContentPane(panel);
            panel.addLoginListener(this);
            this.setVisible(true);
            this.pack();
        }
        if(e.getSource().equals(home))
        {
            JPanel temp = historyPanel.get(0);
            historyPanel.removeAllElements();
            historyPanel.add(temp);
            this.setVisible(false);
            this.setContentPane(temp);
            this.setVisible(true);
            this.pack();
        }
        if(e.getSource().equals(manageItem))
        {
            for (int i = 1; i < historyPanel.size(); i++)
            {
                historyPanel.removeElementAt(1);
            }
            ManageItemPanel manageItemPanel = new ManageItemPanel();
            manageItemPanel.addListener(this);
            this.setVisible(false);
            this.setContentPane(manageItemPanel);
            this.pack();
            this.setVisible(true);
            historyPanel.add(manageItemPanel);
        }
        if(e.getSource().equals(manageAdmin))
        {
            for (int i = 1; i < historyPanel.size(); i++)
            {
                historyPanel.removeElementAt(1);
            }
            ManageAdminPanel manageAdminPanel = new ManageAdminPanel();
            this.setVisible(false);
            this.setContentPane(manageAdminPanel);
            this.pack();
            this.setVisible(true);
            historyPanel.add(manageAdminPanel);
        }
        if(e.getSource().equals(manageSupplier))
        {
            for (int i = 1; i < historyPanel.size(); i++)
            {
                historyPanel.removeElementAt(1);
            }
            ManageSupplierPanel manageSupplierPanel = new ManageSupplierPanel();
            this.setVisible(false);
            this.setContentPane(manageSupplierPanel);
            this.pack();
            this.setVisible(true);
            historyPanel.add(manageSupplierPanel);
        }
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

    @Override
    public void moveToManageItem()
    {
        ManageItemPanel manageItemPanel = new ManageItemPanel();
        manageItemPanel.addListener(this);
        this.setVisible(false);
        this.setContentPane(manageItemPanel);
        this.pack();
        this.setVisible(true);
        historyPanel.add(manageItemPanel);
    }

    @Override
    public void moveToManageAdmin()
    {
        ManageAdminPanel manageAdminPanel = new ManageAdminPanel();
        this.setVisible(false);
        this.setContentPane(manageAdminPanel);
        this.pack();
        this.setVisible(true);
        historyPanel.add(manageAdminPanel);
    }

    @Override
    public void moveToManageSupplier()
    {
        ManageSupplierPanel manageSupplierPanel = new ManageSupplierPanel();
        this.setVisible(false);
        this.setContentPane(manageSupplierPanel);
        this.pack();
        this.setVisible(true);
        historyPanel.add(manageSupplierPanel);
    }
    
    @Override
    public void moveToOrderItem() {
        OrderItemPanel orderItemPanel = new OrderItemPanel();
        orderItemPanel.addListener(this);
        this.setVisible(false);
        this.setContentPane(orderItemPanel);
        this.pack();
        this.setVisible(true);
        historyPanel.add(orderItemPanel);

    }
    
    @Override
    public void moveToAddItem()
    {
        AddItemPanel addItemPanel = new AddItemPanel();
        addItemPanel.addListener(this);
        this.setVisible(false);
        this.setContentPane(addItemPanel);
        this.pack();
        this.setVisible(true);
        historyPanel.add(addItemPanel);
    }

    @Override
    public void moveToEditItem(int selectedRow, TableModel table, Barang prevBarang) {
        EditItemPanel editItemPanel = new EditItemPanel((ItemTableModel)table, selectedRow, prevBarang);
        editItemPanel.addListener(this);
        this.setVisible(false);
        this.setContentPane(editItemPanel);
        this.pack();
        this.setVisible(true);
        historyPanel.add(editItemPanel);
    }

    @Override
    public ItemTableModel searchItem(ItemTableModel itm, String keyword) {
        return null;
    }

    @Override
    public void saveItem(Barang newBarang) {
        historyPanel.removeElement(historyPanel.lastElement());
        ManageItemPanel panel = (ManageItemPanel) historyPanel.lastElement();
        ItemTableModel itm = panel.getTableModel();
        Object[] temp = {newBarang.getName(), newBarang.getJenis(), newBarang.getProduct(), newBarang.getSupplier().getMerek().getName()+" "+newBarang.getSupplier().getLocation(), newBarang.getHarga(), newBarang.getQty(), newBarang.getGudang()};
        itm.addRow(temp);
        this.setVisible(false);
        this.setContentPane(panel);
        this.pack();
        this.repaint();
        this.setVisible(true);
        BarangModel aim = new BarangModel();
        boolean hasil = aim.addNewBarang(newBarang);
    }

    @Override
    public void cancel() {
        historyPanel.removeElement(historyPanel.lastElement());
        ManageItemPanel panel = (ManageItemPanel) historyPanel.lastElement();
        this.setVisible(false);
        this.setContentPane(panel);
        this.pack();
        this.repaint();
        this.setVisible(true);
    }
    
    @Override
    public void editItem(Barang newBarang, Barang prevBarang, int selectedIndex, ItemTableModel itm) {
        itm.editRow(newBarang, selectedIndex);
        BarangModel aim = new BarangModel();
        boolean hasil = aim.editBarang(newBarang, prevBarang);
        historyPanel.removeElement(historyPanel.lastElement());
        ManageItemPanel panel = (ManageItemPanel) historyPanel.lastElement();
        this.setVisible(false);
        this.setContentPane(panel);
        this.pack();
        this.repaint();
        this.setVisible(true);
    }
    
  //order item

    @Override
    public void moveToAddItemOrder(Object a, Object b) {
        AddItemOrderPanel addOrderItemPanel = new AddItemOrderPanel(a.toString(), b.toString());
        addOrderItemPanel.addListener(this);
        this.setVisible(false);
        this.setContentPane(addOrderItemPanel);
        this.pack();
        this.setVisible(true);
        historyPanel.add(addOrderItemPanel);
    }

    @Override
    public void moveToEditItemOrder(int selectedRow, TableModel table) {
        EditOrderItemPanel editItemOrderPanel = new EditOrderItemPanel(selectedRow, (OrderItemModel) table);
        editItemOrderPanel.addListener(this);
        this.setVisible(false);
        this.setContentPane(editItemOrderPanel);
        this.pack();
        this.setVisible(true);
        historyPanel.add(editItemOrderPanel);

    }

    @Override
    public void finishOrder() {

    }

    @Override
    public void deleteOrder() {
        OrderItemPanel orderItemPanel = new OrderItemPanel();
        orderItemPanel.addListener(this);
        this.setVisible(false);
        this.setContentPane(orderItemPanel);
        this.pack();
        this.setVisible(true);
        historyPanel.add(orderItemPanel);
    }

    @Override
    public void clearAll() {

    }
    
     //addorderitem
    @Override
    public void cancelToOrder() {
        historyPanel.removeElement(historyPanel.lastElement());
        OrderItemPanel panel = (OrderItemPanel) historyPanel.lastElement();
        this.setVisible(false);
        this.setContentPane(panel);
        this.pack();
        this.repaint();
        this.setVisible(true);
    }

    @Override
    public void saveOrder(Object[] newOrder) {
        historyPanel.removeElement(historyPanel.lastElement());
        OrderItemPanel panel = (OrderItemPanel) historyPanel.lastElement();
        OrderItemModel itm = panel.getOrderTable();

//        newOrder[0] = a.getDetailNamePanel().getText();
//        newOrder[1] = a.getProductBox().getSelectedItem();
//        newOrder[2] = a.getProductModel().getSelectedItem();
//        newOrder[3] = "aaa";
//        newOrder[4] = a.getDetailQuantityField().getText();
        itm.addRow(newOrder);
        this.setVisible(false);
        this.setContentPane(panel);
        this.pack();
        this.repaint();
        this.setVisible(true);
    }

    //editOrder
    @Override
    public void cancelToOrderEdit()
    {
        historyPanel.removeElement(historyPanel.lastElement());
        OrderItemPanel panel = (OrderItemPanel) historyPanel.lastElement();
        this.setVisible(false);
        this.setContentPane(panel);
        this.pack();
        this.repaint();
        this.setVisible(true);
    }
    
    @Override
    public void saveOrderEdit(Object[] newOrder, int selectedRow) {
        historyPanel.removeElement(historyPanel.lastElement());
        OrderItemPanel panel = (OrderItemPanel) historyPanel.lastElement();
        OrderItemModel itm = panel.getOrderTable();

//        newOrder[0] = a.getDetailNamePanel().getText();
//        newOrder[1] = a.getProductBox().getSelectedItem();
//        newOrder[2] = a.getProductModel().getSelectedItem();
//        newOrder[3] = "aaa";
//        newOrder[4] = a.getDetailQuantityField().getText();
        itm.editRow(newOrder,selectedRow);
        this.setVisible(false);
        this.setContentPane(panel);
        this.pack();
        this.repaint();
        this.setVisible(true);
    }
    
    //managesupplier
    @Override
    public void moveToAddSupplier() {
    }

    @Override
    public void moveToEditSupplier() {
    }
}