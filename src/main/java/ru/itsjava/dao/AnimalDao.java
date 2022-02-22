package ru.itsjava.dao;

import ru.itsjava.domain.Animal;
import ru.itsjava.domain.Student;

public interface AnimalDao {
    public int count();

    void insert(Animal animal);
    void updateAnimal(Animal animal);
    void delete(long id);

    Animal findById(long id);
}
