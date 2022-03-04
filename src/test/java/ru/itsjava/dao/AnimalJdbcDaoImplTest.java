package ru.itsjava.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.itsjava.domain.Animal;
import ru.itsjava.domain.Pet;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@JdbcTest
@Import(AnimalDaoImpl.class)
public class AnimalJdbcDaoImplTest {
    private static final String DEFAULT_VIEW = "Sibo-uno";
    private static final int DEFAULT_WEIGHT = 100;
    private static final long CURRENT_ID = 3L;
    public static final Pet DEFAULT_PET_ID = new Pet(1L, "Dog");

    @Autowired
    private AnimalDao animalDao;

    @Test
    public void shouldHaveCorrectCount() {
        int actualAnimalsCount = animalDao.count();

        assertEquals(2, actualAnimalsCount);
    }

    @Test
    public void shouldHaveCorrectInsert() {
        Animal expectedAnimal = new Animal(DEFAULT_VIEW, DEFAULT_WEIGHT, DEFAULT_PET_ID);
        long idFromDB = animalDao.insert(expectedAnimal);
        Animal actualAnimal = animalDao.findById(idFromDB);

        assertAll(() -> assertEquals(actualAnimal.getView(), expectedAnimal.getView()),
                () -> assertEquals(actualAnimal.getWeight(), expectedAnimal.getWeight()));
    }

    @Test
    public void shouldHaveCorrectUpdate() {
        Animal expectedAnimal = new Animal(CURRENT_ID, DEFAULT_VIEW, DEFAULT_WEIGHT, DEFAULT_PET_ID);
        animalDao.update(expectedAnimal);

        Animal actualAnimal = animalDao.findById(CURRENT_ID);

        assertEquals(actualAnimal, expectedAnimal);
    }

    @Test
    public void shouldHaveCorrectDelete() {
        animalDao.delete(CURRENT_ID);

        assertEquals(animalDao.count(), 1);
    }
}
