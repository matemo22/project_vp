/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectvp.listener;

/**
 *
 * @author user
 */
public interface EditOrderItemListener {
    public void saveOrderEdit(Object[] newOrder,int selectedRow);
    public void cancelToOrderEdit();
}
