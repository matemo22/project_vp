/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectvp.database.barang;

import java.util.Vector;
import projectvp.database.Brand.Brand;
import projectvp.database.supplier.Supplier;

/**
 *
 * @author Matemo
 */
public class BarangService {
    private BarangJdbcDao dao = new BarangJdbcDao();
    
    public Vector<Barang> getBarang()
    {
        return dao.readAllBarangs();
    }
    
    public boolean addNewBarang(Barang newBarang)
    {
        return dao.insertBarang(newBarang);
    }
    
    public boolean updateBarang(Barang newBarang, Barang prevBarang)
    {
        return dao.updateBarang(newBarang, prevBarang);
    }
}
