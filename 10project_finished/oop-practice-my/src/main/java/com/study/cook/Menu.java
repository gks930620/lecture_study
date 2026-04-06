package com.study.cook;

import java.util.List;

public class Menu {
    private final List<MenuItem> menuItems;

    public  Menu(List<MenuItem> menuItems) {
        this.menuItems=menuItems;

    }

    public MenuItem choose(String name) {
        return this.menuItems.stream().filter(menuItem -> menuItem.matches(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("일치하는 메뉴가 없습니다."));
    }
}
