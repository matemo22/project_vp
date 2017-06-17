/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectvp.listener;

import projectvp.database.supplier.Supplier;
import projectvp.model.SupplierTableModel;

/**
 *
 * @author user
 */
public interface ManageSupplierListener {
    public void moveToAddSupplier();
    public void moveToEditSupplier(int selectedIndex, Supplier prevSupplier);
    public SupplierTableModel searchSupplier(SupplierTableModel stm, String keyword);
}
