package com.example.service;

import com.example.dao.Itemdao;
import com.example.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class Itemservice {


    private final Itemdao itemdao;

    @Autowired
    public Itemservice(Itemdao itemdao) {
        this.itemdao = itemdao;
    }


//       --------------------ADD BOOK---------------------

    @Transactional
    public Item createItem(Item item){
        itemdao.createItemdao(item);
        return item;
    }

    @Transactional
    public void postItems(List<Item>items){
        itemdao.postItems(items);
    }



//       --------------------------GET BOOK---------------------



    @Transactional
    public Item getItemById(Long id,Item item){
        return itemdao.getItemById(id,item);
    }
    @Transactional
    public Item getName(String name){
        return itemdao.getItemByName(name);
    }

    @Transactional
    public List<Item> getAllItems(){
        return itemdao.getAllItems();
    }



    //       --------------------------UPDATE BOOK---------------------



    @Transactional
    public void updateItem(Long id, Item item){
        itemdao.updateItem(id,item);
    }


    @Transactional
    public void updatingInPostman(Long id, Item item){
        itemdao.updateItemInPostman(id,item);
    }




    //       --------------------------PICK A BOOK---------------------


    @Transactional
    public void drawingABook(Long id){
        itemdao.drawABook(id);
    }

    @Transactional
    public boolean isIdPresentOrNotService(Item item) {
        return itemdao.isIdPresentOrNot(item);
    }


    @Transactional
    public boolean isEmailPresentOrNotService(String email) {
        return itemdao.isEmailPresentOrNotDao(email);
    }



    //          ------------------------------------DELETE BOOK---------------------


    @Transactional
    public void deleteItem(Long id){
        itemdao.deleteItem(id);
    }


    //------------------------ADD STOCK--------------------


    @Transactional
    public void addStock(Long id,Integer i){
        itemdao.addBookStock(id,i);
    }





}
