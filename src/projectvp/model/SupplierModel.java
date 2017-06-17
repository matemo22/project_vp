/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectvp.model;

import projectvp.database.Brand.Brand;
import projectvp.database.Brand.BrandService;
import projectvp.database.supplier.Supplier;
import projectvp.database.supplier.SupplierService;

/**
 *
 * @author user
 */
public class SupplierModel {
      SupplierService ss = new SupplierService();
      BrandService bs= new BrandService();
    
    public boolean addNewSupplier(Supplier newSuplier,Brand brand)
    {
        return ss.addNewSupplier(newSuplier,brand);
    }
   
    public boolean addNewBrand(Brand newBrand)
    {
        return bs.addNewBrand(newBrand);
    }
    
    public boolean editSupplier(Supplier editSupplier, Supplier prev)
    {
        return ss.updateSupplier(editSupplier, prev);
    }
}
