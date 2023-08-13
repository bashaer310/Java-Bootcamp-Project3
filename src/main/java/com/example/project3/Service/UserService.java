package com.example.project3.Service;

import com.example.project3.ApiResponse.ApiResponse;
import com.example.project3.Model.MerchantModel;
import com.example.project3.Model.MerchantStockModel;
import com.example.project3.Model.ProductModel;
import com.example.project3.Model.UserModel;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class UserService {

    final private MerchantService merchantService;
    final private ProductService productService;
    final private MerchantStockService merchantStockService;

    ArrayList<UserModel> users=new ArrayList<UserModel>();
    public ArrayList<UserModel> getUsers(){
        return users;
    }

    public void addUser(UserModel user){
        users.add(user);
    }

    public boolean updateUser(UserModel user, String id){
        for (int i = 0; i <users.size() ; i++) {
            if(users.get(i).getId().equals(id))
            {
                users.set(i,user);
                return true;
            }
        }
        return false;
    }

    public boolean deleteUser(String id){
        for (int i = 0; i <users.size() ; i++) {
            if(users.get(i).getId().equals(id))
            {
                users.remove(i);
                return true;
            }
        }
        return false;
    }
    public int findUser(String id){
        for (int i = 0; i <users.size() ; i++) {
            if(users.get(i).getId().equals(id))
            {
                return i;
            }
        }
        return -1;
    }

    public boolean buyProduct(String userId,String productId,String merchantId){

        ArrayList<MerchantStockModel> merchantStocks = merchantStockService.getMerchantStocks();
        ArrayList<ProductModel> products = productService.getProducts();

        int uIndex=findUser(userId);
        int pIndex=productService.findProduct(productId);
        int mIndex=merchantService.findMerchant(merchantId);

        if((uIndex & pIndex & mIndex)!=-1)
        {
            for (int i = 0; i < merchantStocks.size(); i++) {
                if (merchantStocks.get(i).getMerchantId().equals(merchantId) & merchantStocks.get(i).getProductId().equals(productId)) {
                    if(users.get(uIndex).getBalance()>products.get(pIndex).getPrice()&&merchantStocks.get(i).getStock()>0)
                    {
                        merchantStocks.get(i).setStock(merchantStocks.get(i).getStock()-1);
                        users.get(uIndex).setBalance(users.get(uIndex).getBalance()-products.get(pIndex).getPrice());
                        return true;
                    }
                }
            }

        }
        return false;
    }

}


