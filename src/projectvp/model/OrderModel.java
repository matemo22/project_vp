/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectvp.model;

import projectvp.database.order.Order;
import projectvp.database.order.OrderService;

/**
 *
 * @author user
 */
public class OrderModel {
     OrderService os = new OrderService();
    
    public boolean addNewOrder(Order newOrder)
    {
        return os.addNewOrder(newOrder);
    }
    
}
