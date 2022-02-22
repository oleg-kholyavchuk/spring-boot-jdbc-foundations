package ru.itsjava.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Component;
import ru.itsjava.domain.Animal;
import ru.itsjava.domain.Student;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class AnimalDaoImpl implements AnimalDao {
    private final NamedParameterJdbcOperations jdbc;

    @SuppressWarnings("ConstantConditions")
    @Override
    public int count() {
        return jdbc.getJdbcOperations().queryForObject("select count(*) from animals", Integer.class);
    }

    @Override
    public void insert(Animal animal) {
        Map<String, Object> params = Map.of("vie", animal.getVie(), "wei", animal.getWei());
        jdbc.update("insert into animals(vie, wei) values( :vie, :wei)", params);
    }

    @Override
    public void updateAnimal(Animal animal) {
        Map<String, Object> params = Map.of("id", animal.getId(), "vie", animal.getVie(), "wei", animal.getWei());
        jdbc.update("update animals set vie = :vie where id = :id", params);
        jdbc.update("update animals set wei = :wei where id = :id", params);
    }

    @Override
    public void delete(long id) {
        Map<String, Object> params = Map.of("id", id);
        jdbc.update("delete from animals where id = :id", params);
    }

    @Override
    public Animal findById(long id) {
        Map<String, Object> params = Map.of("id", id);
        return jdbc.queryForObject("select id, vie, wei from animals where id = :id", params,
                new AnimalsMapper());
    }

    private static class AnimalsMapper implements RowMapper<Animal> {

        @Override
        public Animal mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Animal(rs.getLong("id"), rs.getString("vie"), rs.getInt("wei"));
        }
    }
}
