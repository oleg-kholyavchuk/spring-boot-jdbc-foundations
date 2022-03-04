package ru.itsjava.dao;

import ru.itsjava.domain.Animal;

import java.util.List;

public interface AnimalDao {
    int count();

    long insert(Animal animal);
    void update(Animal animal);
    void delete(long id);

    Animal findById(long id);
    List<Animal> findAll();
}
