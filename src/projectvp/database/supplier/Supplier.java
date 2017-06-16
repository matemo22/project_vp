/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectvp.database.supplier;

import projectvp.database.Brand.Brand;

/**
 *
 * @author Matemo
 */
public class Supplier {
    private int id;
    private Brand merek;
    private String location;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Brand getMerek() {
        return merek;
    }

    public void setMerek(Brand merek) {
        this.merek = merek;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
