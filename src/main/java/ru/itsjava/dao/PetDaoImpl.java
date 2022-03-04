package ru.itsjava.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.itsjava.domain.Pet;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@SuppressWarnings("ALL")
@Repository
@RequiredArgsConstructor
public class PetDaoImpl implements PetDao {
    private final NamedParameterJdbcOperations jdbc;

    @Override
    public List<Pet> findAll() {
        return jdbc.query("select id, homePet from pets ", new PetMapper());
    }

    @Override
    public Pet findByHomePet(String homePet) {
        return (Pet) jdbc.queryForObject("select id, homePet from pets where homePet = :homePet ",
                new MapSqlParameterSource(Map.of("homePet", homePet)), new PetMapper());
    }

    private static class PetMapper implements RowMapper {

        @Override
        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Pet(rs.getLong("id"), rs.getString("homePet"));
        }
    }
}
