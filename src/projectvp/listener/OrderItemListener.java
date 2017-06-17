/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectvp.listener;

import java.util.Vector;
import javax.swing.table.TableModel;
import projectvp.database.order.Order;

/**
 *
 * @author user
 */
public interface OrderItemListener {
    public void moveToAddItemOrder(Object a, Object b);
    public void finishOrder(Vector<Order> newOrder);
    public void moveToEditItemOrder(int row,TableModel a);
}
