package com.example.project3.Service;

import com.example.project3.Model.ProductModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ProductService {

    ArrayList<ProductModel> products = new ArrayList<ProductModel>();

    public ArrayList<ProductModel> getProducts(){
        return products;
    }

    public void addProduct(ProductModel product){
        products.add(product);
    }

    public boolean updateProduct(ProductModel product, String id){
        for (int i = 0; i <products.size() ; i++) {
            if(products.get(i).getId().equals(id))
            {
                products.set(i,product);
                return true;
            }
        }
        return false;
    }

    public boolean deleteProduct(String id){
        for (int i = 0; i <products.size() ; i++) {
            if(products.get(i).getId().equals(id))
            {
                products.remove(i);
                return true;
            }
        }
        return false;
    }

    public int findProduct(ArrayList<ProductModel> products,String id){
        for (int i = 0; i <products.size() ; i++) {
            if(products.get(i).getId().equals(id))
            {
                return i;
            }
        }
        return -1;
    }
}
