/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectvp.database.Brand;

import java.util.Vector;

/**
 *
 * @author Matemo
 */
public class BrandService {
    private BrandJdbcDao dao = new BrandJdbcDao();
    
    public Vector<Brand> getBrands()
    {
        return dao.readAllBrands();
    }
}
