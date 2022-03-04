package ru.itsjava.services;

import ru.itsjava.domain.Animal;

import java.util.List;

public interface AnimalService {
    void insert(Animal animal);
    List<Animal> findAll();
}
