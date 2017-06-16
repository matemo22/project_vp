/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectvp.model;

import projectvp.database.barang.Barang;
import projectvp.database.barang.BarangService;

/**
 *
 * @author Matemo
 */
public class BarangModel {
    BarangService bs = new BarangService();
    
    public boolean addNewBarang(Barang newBarang)
    {
        return bs.addNewBarang(newBarang);
    }
    
    public boolean editBarang(Barang newBarang, Barang prev)
    {
        return bs.updateBarang(newBarang, prev);
    }
}
