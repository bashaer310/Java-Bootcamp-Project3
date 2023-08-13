package com.example.project3.Service;

import com.example.project3.Model.MerchantStockModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MerchantStockService {

    ArrayList<MerchantStockModel> merchantStocks = new ArrayList<MerchantStockModel>();

    public ArrayList<MerchantStockModel> getMerchantStocks(){
        return merchantStocks;
    }

    public void addMerchantStock(MerchantStockModel merchantStock){
        merchantStocks.add(merchantStock);
    }

    public boolean updateMerchantStock(MerchantStockModel merchantStock, String id){
        for (int i = 0; i <merchantStocks.size() ; i++) {
            if(merchantStocks.get(i).getId().equals(id))
            {
                merchantStocks.set(i,merchantStock);
                return true;
            }
        }
        return false;
    }

    public boolean deleteMerchantStock(String id){
        for (int i = 0; i <merchantStocks.size() ; i++) {
            if(merchantStocks.get(i).getId().equals(id))
            {
                merchantStocks.remove(i);
                return true;
            }
        }
        return false;
    }


}
