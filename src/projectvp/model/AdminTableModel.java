/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectvp.model;

import java.util.Vector;
import javax.swing.table.AbstractTableModel;
import projectvp.database.user.User;
import projectvp.database.user.UserService;

/**
 *
 * @author Matemo
 */
public class AdminTableModel extends AbstractTableModel{
    private Vector<User> data;
    private String[] columnNames = {"Username", "Password", "Status"};
    private UserService us;
    private Vector<Object[]> rows;
    
    public AdminTableModel()
    {
        us = new UserService();
        data = us.getUsers();
        rows =  new Vector<Object[]>();
        for (User a : data)
        {
            //Masukkan isian ke table
            if(a.getAuthority()!=1)
            {
                Object[] arow = new Object[3];
                arow[0]=a.getUsername();
                arow[1]=a.getPassword();
                if(a.getStatus()==1)
                    arow[2]="Active";
                else
                    arow[2]="Deactive";
                rows.add(arow);
            }
        }
    }
    
    public void hapus(int index)
    {
        rows.remove(index);
        fireTableDataChanged();
    }
    
    public void editRow(User newUser, int row)
    {
        Object[] editRow = new Object[3];
        editRow[0]=newUser.getUsername();
        editRow[1]=newUser.getPassword();
        if(newUser.getStatus()==1)
            editRow[2]="Active";
        else
            editRow[2]="Deactive";
        for (int i = 0; i < 3; i++)
        {
            setValueAt(editRow[i], row, i);
        }
    }
    
    public void filterTable(String keyword)
    {
        rows.removeAllElements();
        for (User a : data)
        {
            if(a.getUsername().toLowerCase().contains(keyword.toLowerCase()) && a.getAuthority()==2)
            {
                //Masukkan isian ke table
                Object[] arow = new Object[3];
                arow[0]=a.getUsername();
                arow[1]=a.getPassword();
                if(a.getStatus()==1)
                    arow[2]="Active";
                else
                    arow[2]="Deactive";
                rows.add(arow);
            }
        }
        fireTableDataChanged();
    }
    
    public void addRow(Object[] newRow)
    {
        Object[] aRow = new Object[3];
        aRow[0]=newRow[0];
        aRow[1]=newRow[1];
        aRow[2]=newRow[2];
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
    
    public User getUser(String name)
    {
        for (User a : data)
        {
            if(a.getUsername().equals(name))
                return a;
        }
        return null;
    }
}
