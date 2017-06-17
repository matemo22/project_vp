/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectvp.model;

import java.util.Vector;
import javax.swing.table.AbstractTableModel;
import projectvp.database.supplier.Supplier;
import projectvp.database.supplier.SupplierService;

/**
 *
 * @author user
 */
public class SupplierTableModel extends AbstractTableModel{

     private Vector<Supplier> data;
    private String[] columnNames = {"Nama", "Lokasi"};
    private Class<?>[] columnClasses = {String.class, String.class};
    private SupplierService ss;
    private Vector<Object[]> rows;
    
    public SupplierTableModel()
    {
        ss = new SupplierService();
        data = ss.getSupplier();
        rows =  new Vector<Object[]>();
        for (Supplier a : data)
        {
            //Masukkan isian ke table
            Object[] arow = new Object[2];
            arow[0]=a.getMerek().getName();
            arow[1]=a.getLocation();
           
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
        for (int i = 0; i < 2; i++)
        {
            setValueAt(editRow[i], row, i);
        }
    }
    
    public void addRow(Object[] newRow)
    {
        Object[] aRow = new Object[3];
        aRow[0]=newRow[0];
        aRow[1]=newRow[1];
    
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
    
     public Supplier getSupplier(String supplier)
    {
        for (Supplier a : data)
        {
            if(a.getMerek().getName().equals(supplier))
                return a;
        }
        return null;
    }
    
}
