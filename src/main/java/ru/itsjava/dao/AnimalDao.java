package ru.itsjava.dao;

import ru.itsjava.domain.Animal;

public interface AnimalDao {
    int count();

    long insert(Animal animal);
    void update(Animal animal);
    void delete(long id);

    Animal findById(long id);
}
