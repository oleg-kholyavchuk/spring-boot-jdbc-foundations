package ru.itsjava.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.itsjava.domain.Animal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@JdbcTest
@Import(AnimalDaoImpl.class)
public class AnimalJdbcDaoImplTest {
    private static final String DEFAULT_VIEW = "Human";
    private static final int DEFAULT_WEIGHT = 100;
    private static final long CURRENT_ID = 3L;
    private static final long NEW_ID = 5L;

    @Autowired
    private AnimalDao animalDao;

    @Test
    public void shouldHaveCorrectCount() {
        int actualAnimalsCount = animalDao.count();

        assertEquals(2, actualAnimalsCount);
    }

    @Test
    public void shouldHaveCorrectInsert() {
        Animal expectedAnimal = new Animal(NEW_ID, DEFAULT_VIEW, DEFAULT_WEIGHT);
        animalDao.insert(expectedAnimal);

        Animal actualAnimal = animalDao.findById(NEW_ID);

        assertEquals(actualAnimal, expectedAnimal);
    }

    @Test
    public void shouldHaveCorrectUpdate() {
        Animal expectedAnimal = new Animal(CURRENT_ID, DEFAULT_VIEW, DEFAULT_WEIGHT);
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
