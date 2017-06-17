/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectvp.listener;

import projectvp.database.user.User;
import projectvp.layout.MasterPanel;

/**
 *
 * @author user
 */
public interface MasterListener {
    public void moveToManageItem();
    public void moveToManageAdmin();
    public void moveToManageSupplier();
    public void moveToOrderItem(User user);
}
