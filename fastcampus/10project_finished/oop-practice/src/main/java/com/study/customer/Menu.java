package com.study.customer;

import java.util.List;

public class Menu {
    private final List<MenuItem> menuItems;

    public Menu(List<MenuItem> menuItems){
        this.menuItems=menuItems;
    }


    public MenuItem choose(String name) {
        return this.menuItems.stream()
                .filter(menuItem -> menuItem.matches(name))
                .findFirst()
                .orElseThrow(()-> new IllegalArgumentException("잘몬된 메뉴"));
    }
}
