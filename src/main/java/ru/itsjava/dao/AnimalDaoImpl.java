package ru.itsjava.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import ru.itsjava.domain.Animal;
import ru.itsjava.domain.Pet;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Component
@SuppressWarnings("ALL")
@RequiredArgsConstructor
public class AnimalDaoImpl implements AnimalDao {
    private final NamedParameterJdbcOperations jdbc;

    @SuppressWarnings("ConstantConditions")
    @Override
    public int count() {
        return jdbc.getJdbcOperations().queryForObject("select count(*) from animals", Integer.class);
    }

    @Override
    public long insert(Animal animal) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        Map<String, Object> params = Map.of("view", animal.getView(), "weight", animal.getWeight(),
                "homePets_id", animal.getPet().getId());
        jdbc.update("insert into animals(view, weight, homePets_id) values(:view, :weight, :homePets_id)",
                new MapSqlParameterSource(params), keyHolder);

        return keyHolder.getKey().longValue();
    }

    @Override
    public void update(Animal animal) {
        Map<String, Object> params = Map.of("id", animal.getId(), "view", animal.getView(), "weight", animal.getWeight());
        jdbc.update("update animals set view = :view where id = :id", params);
        jdbc.update("update animals set weight = :weight where id = :id", params);
    }

    @Override
    public void delete(long id) {
        Map<String, Object> params = Map.of("id", id);
        jdbc.update("delete from animals where id = :id", params);
    }

    @Override
    public Animal findById(long id) {
        Map<String, Object> params = Map.of("id", id);
        return jdbc.queryForObject("select a.id as AID, view, weight, " +
                "p.id as PID, homePet from animals a, pets p  where a.id = :id " +
                " and a.homePets_id = p.id", params, new AnimalsMapper());
    }

    @Override
    public List<Animal> findAll() {
        return jdbc.query("select a.id as AID, view, weight, p.id as PID, homePet from animals a, pets p  where a.homePets_id = p.id ", new AnimalsMapper());
    }

    private static class AnimalsMapper implements RowMapper<Animal> {

        @Override
        public Animal mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Animal(rs.getLong("AID"), rs.getString("view"), rs.getInt("weight"),
                    new Pet(rs.getLong("PID"), rs.getString("homePet")));
        }
    }
}
