/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectvp.listener;

import javax.swing.table.TableModel;

/**
 *
 * @author user
 */
public interface OrderItemListener {
    public void moveToAddItemOrder(Object a, Object b);
    public void finishOrder();
    public void deleteOrder();
    public void clearAll();
    public void moveToEditItemOrder(int row,TableModel a);
}
