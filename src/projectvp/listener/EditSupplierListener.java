/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectvp.listener;

import projectvp.database.Brand.Brand;
import projectvp.database.supplier.Supplier;

/**
 *
 * @author user
 */
public interface EditSupplierListener {
    public void cancelFromEditSuppiler();
    public void saveEdit(Supplier newSupplier, Brand newBrand, boolean isNew, int selectedIndex, Supplier prevSupplier);
}
