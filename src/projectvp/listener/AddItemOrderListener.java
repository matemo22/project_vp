/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectvp.listener;

import projectvp.layout.AddItemOrderPanel;
import projectvp.layout.OrderItemPanel;

/**
 *
 * @author user
 */
public interface AddItemOrderListener {
    
    public void cancelToOrder();
    public void saveOrder(Object[] newOrder);
}
