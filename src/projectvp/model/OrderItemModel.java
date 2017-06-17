/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectvp.model;

import java.util.Vector;
import javax.swing.table.AbstractTableModel;
import projectvp.database.barang.Barang;
import projectvp.database.barang.BarangService;
import projectvp.database.order.Order;
import projectvp.database.order.OrderService;
import projectvp.layout.AddItemOrderPanel;

/**
 *
 * @author user
 */
public class OrderItemModel extends AbstractTableModel {
    private Vector<AddItemOrderPanel> data;
    private Vector<Order> dataOrder;
    private String[] columnNames = {"Nama", "Produk", "Jenis","Supplier", "Quantity"};
    private Class<?>[] columnClasses = {String.class, String.class, String.class, String.class, Integer.class};
    private Vector<Object[]> rows;
    private OrderService os;
    
    public OrderItemModel()
    {
        data= new Vector<AddItemOrderPanel>();
        rows =  new Vector<Object[]>();
        for (AddItemOrderPanel a : data)
        {
            //Masukkan isian ke table
            Object[] arow = new Object[5];
            arow[0]=a.getDetailNamePanel().getText();
            arow[1]=a.getProductBox().getSelectedItem();
            arow[2]=a.getProductModel().getSelectedItem();
            arow[3]=a.getOip().getSuppplierModel().getSelectedItem()+ " "+ a.getOip().getLocationSuppModel().getSelectedItem();
            arow[4]=a.getDetailQuantityField().getText();
            rows.add(arow);
        }
    }
    public void hapusAll()
    {
        for (int i = getRowCount(); i > 0; i--) {
            hapus(i-1);
        }
    }
  
    public void hapus(int row)
    {
        rows.remove(row);
        fireTableDataChanged();
    }
    
    public void editRow(Object[] editRow, int row)
    {
        editRow[0]=editRow[0].toString();
        editRow[1]=editRow[1].toString();
        editRow[2]=editRow[2].toString();
        editRow[3]=editRow[3].toString();
        editRow[4]=editRow[4].toString();
        for (int i = 0; i < 5; i++)
        {
            setValueAt(editRow[i], row, i);
        }
    }
    
    public void addRow(Object[] newRow)
    {
        Object[] aRow = new Object[5];
        aRow[0]=newRow[0];
        aRow[1]=newRow[1];
        aRow[2]=newRow[2];
        aRow[3]=newRow[3];
        aRow[4]=newRow[4];
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
    public Order getorder(String name)
    {
        for (Order a : dataOrder)
        {
            if(a.getNamaBarang().equals(name))
                return a;
        }
        return null;
    }
}
