/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectvp.model;

import java.util.Vector;
import javax.swing.table.AbstractTableModel;
import projectvp.database.Brand.BrandService;
import projectvp.database.barang.*;

/**
 *
 * @author Matemo
 */
public class ItemTableModel extends AbstractTableModel{
    private Vector<Barang> data;
    private String[] columnNames = {"Nama", "Produk", "Jenis","Brand", "Harga", "Quantity", "Gudang"};
    private BarangService bs;
    private Vector<Object[]> rows;
    
    public ItemTableModel()
    {
        bs = new BarangService();
        data = bs.getBarang();
        rows =  new Vector<Object[]>();
        for (Barang a : data)
        {
            //Masukkan isian ke table
            Object[] arow = new Object[7];
            arow[0]=a.getName();
            arow[1]=a.getProduct();
            arow[2]=a.getJenis();
            arow[3]=a.getSupplier().getMerek().getName()+" "+a.getSupplier().getLocation();
            arow[4]=a.getHarga();
            arow[5]=a.getQty();
            arow[6]=a.getGudang();
            rows.add(arow);
        }
    }
    
    public void hapus(int index)
    {
        rows.remove(index);
        fireTableDataChanged();
    }
    
    public void editRow(Barang newBarang, int row)
    {
        Object[] editRow = new Object[7];
        editRow[0]=newBarang.getName();
        editRow[1]=newBarang.getProduct();
        editRow[2]=newBarang.getJenis();
        editRow[3]=newBarang.getSupplier().getMerek().getName()+" "+newBarang.getSupplier().getLocation();
        editRow[4]=newBarang.getHarga();
        editRow[5]=newBarang.getQty();
        editRow[6]=newBarang.getGudang();
        for (int i = 0; i < 7; i++)
        {
            setValueAt(editRow[i], row, i);
        }
    }
    
    public void filterTable(String keyword)
    {
        rows.removeAllElements();
        for (Barang a : data)
        {
            if(a.getName().toLowerCase().contains(keyword.toLowerCase())
                || a.getProduct().toLowerCase().contains(keyword.toLowerCase())
                || a.getJenis().toLowerCase().contains(keyword.toLowerCase())
                || a.getSupplier().getMerek().getName().toLowerCase().contains(keyword.toLowerCase())
                || a.getSupplier().getLocation().toLowerCase().contains(keyword.toLowerCase())
                || (a.getSupplier().getMerek().getName()+" "+a.getSupplier().getLocation()).toLowerCase().contains(keyword.toLowerCase()))
            {
                //Masukkan isian ke table
                Object[] arow = new Object[7];
                arow[0]=a.getName();
                arow[1]=a.getProduct();
                arow[2]=a.getJenis();
                arow[3]=a.getSupplier().getMerek().getName()+" "+a.getSupplier().getLocation();
                arow[4]=a.getHarga();
                arow[5]=a.getQty();
                arow[6]=a.getGudang();
                rows.add(arow);
            }
        }
        fireTableDataChanged();
    }
    
    public void addRow(Object[] newRow)
    {
        Object[] aRow = new Object[7];
        aRow[0]=newRow[0];
        aRow[1]=newRow[1];
        aRow[2]=newRow[2];
        aRow[3]=newRow[3];
        aRow[4]=newRow[4];
        aRow[5]=newRow[5];
        aRow[6]=newRow[6];
        rows.add(aRow);
        fireTableDataChanged();
    }
    
    @Override
    public int getRowCount() {
        return rows.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object[] arow = rows.get(rowIndex);
        return arow[columnIndex];
    }
    
    @Override
    public void setValueAt(Object value, int row, int col)
    {
        Object[] arow = rows.get(row);
        arow[col]= value;
    }
    
    @Override
    public String getColumnName(int col)
    {
        return columnNames[col];
    }
    
    @Override
    public boolean isCellEditable(int row, int col)
    {
        return false;
    }
    
    public Barang getBarang(String name)
    {
        data = bs.getBarang();
        for (Barang a : data)
        {
            if(a.getName().equals(name))
                return a;
        }
        return null;
    }
}
