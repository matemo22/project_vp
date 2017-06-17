/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectvp.model;

import projectvp.database.Brand.Brand;
import projectvp.database.supplier.Supplier;
import projectvp.database.supplier.SupplierService;

/**
 *
 * @author user
 */
public class SupplierModel {
      SupplierService ss = new SupplierService();
    
    public boolean addNewSupplier(Supplier newSuplier)
    {
        return ss.addNewSupplier(newSuplier);
    }
   
    public boolean addNewBrand(Brand newBrand)
    {
        return ss.addNewBrand(newBrand);
    }
    
    public boolean editSupplier(Supplier newBarang, Barang prev)
    {
        return ss.updateBarang(newBarang, prev);
    }
}
