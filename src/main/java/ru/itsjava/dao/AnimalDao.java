package ru.itsjava.dao;

import ru.itsjava.domain.Animal;

public interface AnimalDao {
    int count();

    void insert(Animal animal);
    void updateAnimal(Animal animal);
    void delete(long id);

    Animal findById(long id);
}
