/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectvp.listener;

import projectvp.database.barang.Barang;
import projectvp.model.ItemTableModel;

/**
 *
 * @author user
 */
public interface ItemsListener {
    public void saveItem(Barang newBarang);
    public void cancel();
    public void editItem(Barang newBarang, Barang prevBarang, int selectedIndex, ItemTableModel itm);
}
