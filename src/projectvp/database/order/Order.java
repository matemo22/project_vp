package projectvp.database.order;

import java.sql.Date;
import projectvp.database.supplier.Supplier;
import projectvp.database.user.User;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author user
 */
public class Order {
    private String produkName,produkType, namaBarang;
    private int id,qty;
    private Date date;
    private Supplier supplier;
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public String getProdukName() {
        return produkName;
    }

    public void setProdukName(String produkName) {
        this.produkName = produkName;
    }

    public String getProdukType() {
        return produkType;
    }

    public void setProdukType(String produkType) {
        this.produkType = produkType;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
    
}
