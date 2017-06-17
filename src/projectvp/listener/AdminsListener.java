/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectvp.listener;

import javax.swing.table.TableModel;
import projectvp.database.user.User;
import projectvp.model.AdminTableModel;

/**
 *
 * @author Matemo
 */
public interface AdminsListener {
    public void moveToAddUser();
    public void moveToEditUser(int selectedRow, TableModel table, User prevUser);
    public AdminTableModel searchUser(AdminTableModel atm, String keyword);
    public void saveUser(User newUser);
    public void cancelAdmin();
    public void editUser(User newUser, User prevUser, int selectedIndex, AdminTableModel atm);
}
