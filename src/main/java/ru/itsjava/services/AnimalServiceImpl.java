package ru.itsjava.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itsjava.dao.AnimalDao;
import ru.itsjava.domain.Animal;

@Service
@RequiredArgsConstructor
public class AnimalServiceImpl implements AnimalService {
    private final AnimalDao animalDao;

    @Override
    public void insert(Animal animal) {
        long id = animalDao.insert(animal);
        System.out.println("ID new animal = " + id);
    }
}
