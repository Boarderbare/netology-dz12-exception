package ru.netology.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.repository.AlreadyExistsException;
import ru.netology.repository.NotFoundException;
import ru.netology.repository.Repository;

import static org.junit.jupiter.api.Assertions.*;

public class ProductRepositoryTest {
    private Repository repository = new Repository();
    private Product first = new Product(1, "Motocycle", 9000);
    private Product second = new Product(2, "Photocamera", 1200);
    private Product third = new Book(3, "War and Peace", 34, "Leo Tolstoy");
    private Product fourth = new Smartphone(4, "iPhone", 1500, "Apple");

    @BeforeEach
    public void setUp() {
        repository.save(first);
        repository.save(second);
        repository.save(third);
        repository.save(fourth);

    }

    @Test
    public void shouldRemoveById() {
        repository.removeById(2);
        Product[] actual = repository.findAll();
        Product[] expected = new Product[]{first, third, fourth};
        assertArrayEquals(actual, expected);
    }

    @Test
    public void shouldRemoveByIdNotFound() {
        assertThrows(NotFoundException.class, () -> {
            repository.removeById(6);
        });
    }

    @Test
    public void shouldAddById() {
        Product five = new Product(5, "TV", 1400);
        repository.save(five);
        Product[] actual = repository.findAll();
        Product[] expected = new Product[]{first, second, third, fourth,five};
        assertArrayEquals(actual, expected);
    }

    @Test
    public void shouldAddByIdAlreadyExists() {
        Product five = new Product(3, "TV", 1400);
        assertThrows(AlreadyExistsException.class, () -> {
            repository.save(five);
        });
    }
}
