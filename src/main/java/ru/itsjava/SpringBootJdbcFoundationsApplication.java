package ru.itsjava;

import org.h2.tools.Console;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.itsjava.dao.AnimalDao;
import ru.itsjava.dao.StudentDao;
import ru.itsjava.domain.Animal;
import ru.itsjava.domain.Student;

import java.sql.SQLException;

@SpringBootApplication
public class SpringBootJdbcFoundationsApplication {

    public static void main(String[] args) throws SQLException {
        ApplicationContext context = SpringApplication.run(SpringBootJdbcFoundationsApplication.class, args);

        StudentDao studentDao = context.getBean(StudentDao.class);
        System.out.println("studentDao.count() = " + studentDao.count());

        Student student = new Student("San", 33);
        studentDao.insert(student);
        System.out.println("studentDao.count() = " + studentDao.count());

        Student updateStudent = new Student("Ivanov 2", 100);
        updateStudent.setId(1L);
        studentDao.updateStudent(updateStudent);

        System.out.println("studentDao.findById(1L) = " + studentDao.findById(1L));

        studentDao.delete(updateStudent);
        System.out.println("studentDao.count() = " + studentDao.count());

        AnimalDao animalDao = context.getBean(AnimalDao.class);
        System.out.println("animalDao.count() = " + animalDao.count());

        Animal animal = new Animal("Parrot", 12);
        animalDao.insert(animal);
        System.out.println("animalDao.count() = " + animalDao.count());

        Animal updateAnimal = new Animal("The Blue Whale", 150_000);
        updateAnimal.setId(2L);
        animalDao.updateAnimal(updateAnimal);

        System.out.println("animalDao.findById(1L) = " + animalDao.findById(1L));

        animalDao.delete(3);
        System.out.println("animalDao.count() = " + animalDao.count());


        Console.main(args);
    }

}
