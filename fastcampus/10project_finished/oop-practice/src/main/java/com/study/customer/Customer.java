package com.study.customer;

public class Customer {

    public void order(String menuName,Menu menu, Cooking cooking){
        MenuItem menuItem = menu.choose(menuName);
        Cook cook = cooking.makeCook(menuItem);
        //cook요리를 먹습니다.
    }
}
