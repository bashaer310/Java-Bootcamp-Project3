package com.example.project3.Service;

import com.example.project3.Model.CategoryModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CategoryService {

    ArrayList<CategoryModel> categories = new ArrayList<CategoryModel>();

    public ArrayList<CategoryModel> getCategories(){
        return categories;
    }

    public void addCategory(CategoryModel category){
        categories.add(category);
    }

    public boolean updateCategory(CategoryModel category, String id){
        for (int i = 0; i <categories.size() ; i++) {
            if(categories.get(i).getId().equals(id))
            {
                categories.set(i,category);
                return true;
            }
        }
        return false;
    }

    public boolean deleteCategory(String id){
        for (int i = 0; i <categories.size() ; i++) {
            if(categories.get(i).getId().equals(id))
            {
                categories.remove(i);
                return true;
            }
        }
        return false;
    }

}
