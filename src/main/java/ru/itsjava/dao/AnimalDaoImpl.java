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
        Map<String, Object> params = Map.of("vie", animal.getVie(), "wei", animal.getWei(),
                "homePets_id", animal.getPet().getId());
        jdbc.update("insert into animals(vie, wei, homePets_id) values(:vie, :wei, :homePets_id)",
                new MapSqlParameterSource(params), keyHolder);

        return keyHolder.getKey().longValue();
    }

    @Override
    public void update(Animal animal) {
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
        return jdbc.queryForObject("select a.id as SID, vie, wei, p.id as FID, homePet from animals a, pets p  where a.id = :id " +
                " and a.homePets_id = p.id", params, new AnimalsMapper());
    }

    private static class AnimalsMapper implements RowMapper<Animal> {

        @Override
        public Animal mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Animal(rs.getLong("SID"), rs.getString("vie"), rs.getInt("wei"),
                    new Pet(rs.getLong("FID"), rs.getString("homePet")));
        }
    }
}
