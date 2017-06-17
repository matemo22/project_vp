/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectvp.database.supplier;

import java.util.Vector;
import projectvp.database.order.Order;

/**
 *
 * @author Matemo
 */
public class SupplierService {
    private SupplierJdbcDao dao = new SupplierJdbcDao();
    
    public Vector<Supplier> getSupplier()
    {
        return dao.readAllSuppliers();
    }
      public boolean addNewSupplier(Order newOrder)
    {
        return dao.insertSupplier(newOrder);
    }
}
