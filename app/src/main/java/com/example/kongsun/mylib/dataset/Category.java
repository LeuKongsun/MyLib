package com.example.kongsun.mylib.dataset;

/**
 * Created by kongsun on 5/11/17.
 */

public class Category {
    private  int id;
    private String category_list;
    public Category(int id,String category_list){
        this.id = id;
        this.category_list = category_list;
    }


    public String getCategory_list() {
        return category_list;
    }

    public void setCategory_list(String category_list) {
        this.category_list = category_list;
    }
}
