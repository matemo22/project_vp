package projectvp.database.order;

import java.util.Vector;
import projectvp.database.Brand.Brand;
import projectvp.database.Brand.BrandJdbcDao;
import projectvp.database.barang.Barang;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author user
 */
public class OrderService {
     private OrderJdbcDao dao = new OrderJdbcDao();
    
    public Vector<Order> getOrder()
    {
        return dao.readAllOrder();
    }
      public boolean addNewOrder(Order newOrder)
    {
        return dao.insertOrder(newOrder);
    }
}
