/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectvp.listener;

import javax.swing.table.TableModel;
import projectvp.database.barang.Barang;
import projectvp.model.ItemTableModel;

/**
 *
 * @author user
 */
public interface ManageItemListener {
    public void moveToAddItem();
    public void moveToEditItem(int selectedRow, TableModel table, Barang prevBarang);
    public ItemTableModel searchItem(ItemTableModel itm, String keyword);
}
