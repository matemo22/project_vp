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
public interface AddSupplierListener {
    public void saveSupplier(Supplier supplier,Brand brand, boolean newBrand);
    public void cancelToManageSupplier();
}
