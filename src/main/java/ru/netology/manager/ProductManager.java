package ru.netology.manager;

import ru.netology.domain.Product;
import ru.netology.repository.Repository;

public class ProductManager {
    private Repository repo = new Repository();

    public ProductManager() {
    }

    public ProductManager(Repository repo) {
        this.repo = repo;
    }

    public void addItem(Product item) {
        repo.save(item);
    }

    public Product[] searchBy(String text) {
        Product[] result = new Product[0];
        int index = 0;
        for (Product item : repo.findAll()) {
            if (matches(item, text)) {
                Product[] tmp = new Product[result.length + 1];
                System.arraycopy(result, 0, tmp, 0, result.length);
                tmp[index] = item;
                index++;
                result = tmp;
            }
        }
        return result;
    }

    private boolean matches(Product item, String search) {
        return item.getName().contains(search);
    }
}
