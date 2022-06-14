package ru.netology.repository;

import ru.netology.domain.Product;
import ru.netology.repository.NotFoundException;

public class Repository {
    private Product[] items = new Product[0];

    public Product findById(int id) {
        for (Product item : items) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }

    public void save(Product item) {
        int id = item.getId();
        if (findById(id) != null) {
            throw new AlreadyExistsException("Element with id: " + id + " already exists");
        }
        Product[] tmp = new Product[items.length + 1];
        System.arraycopy(items, 0, tmp, 0, items.length);
        tmp[tmp.length - 1] = item;
        items = tmp;
    }

    public Product[] findAll() {
        return items;
    }


    public void removeById(int id) {
        if (findById(id) == null) {
            throw new NotFoundException("Element with id: " + id + " not found");
        }
        Product[] tmp = new Product[items.length - 1];
        int index = 0;
        for (Product item : items) {
            if (item.getId() != id) {
                tmp[index] = item;
                index++;
            }
        }
        items = tmp;
    }
}