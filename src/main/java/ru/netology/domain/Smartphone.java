package ru.netology.domain;

public class Smartphone extends Product {
    private String manufaсture;

    public Smartphone(int id, String name, int price, String manufaсture) {
        super(id, name, price);
        this.manufaсture = manufaсture;
    }
}
