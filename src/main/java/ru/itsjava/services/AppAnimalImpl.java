package ru.itsjava.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itsjava.dao.PetDao;
import ru.itsjava.domain.Animal;
import ru.itsjava.domain.Pet;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AppAnimalImpl implements AppAnimal {
    private final AnimalService animalService;
    private final IOAnimalService ioAnimalService;
    private final PetDao petDao;

    @Override
    public void start() {
        System.out.println("Welcome to Zoo");
        while (true) {
            System.out.println();
            System.out.println("Input number menu");
            System.out.println("1 -- print all animal");
            System.out.println("2 -- add a animal");
            System.out.println("the rest is output");
            int menuNum = ioAnimalService.inputInt();

            if (menuNum == 1) {
                printAllStudent();
            } else if (menuNum == 2) {
                insertAnimal();
            } else {
                System.exit(0);
            }

        }
    }

    private void insertAnimal() {
        System.out.println("Input animals");
        System.out.println("Input view");
        String view = ioAnimalService.input();
        System.out.println("Input weight");
        int weight = ioAnimalService.inputInt();
        System.out.println("Input pet");
        String pet = ioAnimalService.input();
        Pet animalPet = (Pet) petDao.findByHomePet(pet);

        Animal animal = new Animal(view, weight, animalPet);

        animalService.insert(animal);
    }

    private void printAllStudent() {
        List<Animal> animalList = animalService.findAll();

        System.out.print("List animals " + animalList);
    }
}

