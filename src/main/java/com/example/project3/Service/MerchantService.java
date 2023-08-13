package com.example.project3.Service;

import com.example.project3.Model.MerchantModel;
import com.example.project3.Model.MerchantStockModel;
import com.example.project3.Model.UserModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class MerchantService {

    final private MerchantStockService merchantStockService;
    ArrayList<MerchantModel> merchants = new ArrayList<MerchantModel>();

    public ArrayList<MerchantModel> getMerchants(){
        return merchants;
    }

    public void addMerchant(MerchantModel merchant){
        merchants.add(merchant);
    }

    public boolean updateMerchant(MerchantModel merchant, String id){
        for (int i = 0; i <merchants.size() ; i++) {
            if(merchants.get(i).getId().equals(id))
            {
                merchants.set(i,merchant);
                return true;
            }
        }
        return false;
    }

    public boolean deleteMerchant(String id){
        for (int i = 0; i <merchants.size() ; i++) {
            if(merchants.get(i).getId().equals(id))
            {
                merchants.remove(i);
                return true;
            }
        }
        return false;
    }


    public boolean updateStock(String productid, String merchantid, Integer amount){

        ArrayList<MerchantStockModel> merchantStocks=merchantStockService.getMerchantStocks();

        for (int i = 0; i <merchantStocks.size() ; i++) {
            if(merchantStocks.get(i).getProductId().equals(productid)&&merchantStocks.get(i).getMerchantId().equals(merchantid))
            {
                merchantStocks.get(i).setStock(merchantStocks.get(i).getStock()+amount);
                return true;
            }
        }
        return false;
    }

    public int findMerchant(ArrayList<MerchantModel> merchants, String id){
        for (int i = 0; i <merchants.size() ; i++) {
            if(merchants.get(i).getId().equals(id))
            {
                return i;
            }
        }
        return -1;
    }

}
