/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectvp.model;

import java.util.Vector;
import javax.swing.table.AbstractTableModel;
import projectvp.database.barang.*;

/**
 *
 * @author Matemo
 */
public class ItemTableModel extends AbstractTableModel{
    private Vector<Barang> data;
    private String[] columnNames = {"Nama", "Jenis", "Produk","Brand", "Harga", "Quantity", "Gudang"};
    private Class<?>[] columnClasses = {String.class, String.class, String.class, String.class, Integer.class, Integer.class, Integer.class};
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
    
    public void editRow(Object[] editRow, int row)
    {
        editRow[0]=editRow[0].toString();
        editRow[1]=editRow[1].toString();
        editRow[2]=editRow[2].toString();
        editRow[3]=editRow[3].toString();
        editRow[4]=Integer.valueOf(editRow[4].toString());
        editRow[5]=Integer.valueOf(editRow[5].toString());
        editRow[6]=Integer.valueOf(editRow[6].toString());
        for (int i = 0; i < 7; i++)
        {
            setValueAt(editRow[i], row, i);
        }
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
    public Class<?> getColumnClass(int col)
    {
        return columnClasses[col];
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
}
