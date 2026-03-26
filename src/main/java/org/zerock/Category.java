package org.zerock;

import java.util.ArrayList;
import java.util.List;

public class Category {
    private List<Product> Electronics;
    private List<Product> Clothes;
    private List<Product> Groceries;

    Category(){
        Electronics = new ArrayList<>();
        Clothes = new ArrayList<>();
        Groceries = new ArrayList<>();
    }

    public void addElectronics(Product product) {
        Electronics.add(product);
    }
    public void addClothes(Product product) {
        Clothes.add(product);
    }
    public void addGroceries(Product product) {
        Groceries.add(product);
    }

    public  List<Product> getElectronics() {
        return Electronics;
    }
    public List<Product> getClothes() {
        return Clothes;
    }
    public List<Product> getGroceries() {
        return Groceries;
    }
}
