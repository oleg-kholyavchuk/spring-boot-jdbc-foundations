package ru.itsjava.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Component;
import ru.itsjava.domain.Animal;

@Component
@RequiredArgsConstructor
public class AnimalDaoImpl implements AnimalDao {
    private final JdbcOperations jdbc;

    @SuppressWarnings("ConstantConditions")
    @Override
    public int count() {
        return jdbc.queryForObject("select count(*) from animals", Integer.class);
    }

    @Override
    public void insert(Animal animal) {
        jdbc.update("insert into animals(vie, wei) values( ?, ?)", animal.getVie(), animal.getWei());
    }

    @Override
    public void updateAnimal(Animal animal) {
        jdbc.update("update animals set vie = ? where id = ?", animal.getVie(), animal.getId());
        jdbc.update("update animals set wei = ? where id = ?", animal.getWei(), animal.getId());
    }

    @Override
    public void delete(long id) {
        jdbc.update("delete from animals where id = ?", id);
    }
}
