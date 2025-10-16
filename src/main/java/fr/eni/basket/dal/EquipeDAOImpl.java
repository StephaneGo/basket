package fr.eni.basket.dal;

import fr.eni.basket.bo.Equipe;
import fr.eni.basket.dto.EquipeDTO;
import fr.eni.basket.exceptions.EquipeDejaExistante;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

@Repository
public class EquipeDAOImpl implements EquipeDAO {
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public EquipeDAOImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.jdbcTemplate = namedParameterJdbcTemplate.getJdbcTemplate();
    }

    @Override
    public List<Equipe> findAllEquipes() {
        String sql = "SELECT noEquipe, nom  FROM equipes";
        List<Equipe> equipes = jdbcTemplate.query(sql, new EquipeRowMapper());
        return equipes;
    }


    @Override
    public Optional<Equipe> findEquipeByNom(String nom) {
        String sql =  "SELECT noEquipe, nom FROM equipes WHERE nom =:nom";

        Equipe equipe = null;
        try{
            MapSqlParameterSource source = new MapSqlParameterSource();
            source.addValue("nom", nom);

            equipe = namedParameterJdbcTemplate.queryForObject(sql, source, new EquipeRowMapper());
        }
        catch (EmptyResultDataAccessException e){
            //rien: equipe = null
        }

        return Optional.ofNullable(equipe);
    }

    @Override
    public void deleteEquipe(int noEquipe) {
        String sql = "DELETE FROM equipes WHERE noEquipe =:noEquipe";
        MapSqlParameterSource source = new MapSqlParameterSource();
        source.addValue("noEquipe", noEquipe);
        namedParameterJdbcTemplate.update(sql, source);
    }

    @Override
    public void deleteEquipeByNom(String nomEquipe) {
        String sql = "DELETE FROM equipes WHERE nom =:nom";
        MapSqlParameterSource source = new MapSqlParameterSource();
        source.addValue("nom", nomEquipe);
        namedParameterJdbcTemplate.update(sql, source);
    }

    /* V1 avec JdbcTemplate

    @Override
    public Equipe addEquipe(EquipeDTO equipeDto) {
        //ajout
        String sql = "insert into equipes ( nom) values ( ?)";

        Equipe newEquipe;
        //KeyHolder permet de récupérer la clé primaire générée par la base
        KeyHolder keyHolder = new GeneratedKeyHolder();
        try {
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, equipeDto.nom());
                return ps;
            }, keyHolder);
            newEquipe = new Equipe();
            BeanUtils.copyProperties(equipeDto, newEquipe);
            newEquipe.setNoEquipe(keyHolder.getKey().intValue());
        }catch(DuplicateKeyException exc){
            throw new EquipeDejaExistante(equipe.nom());
        }
        return newEquipe;
    }
    */
    @Override
    public Equipe insertEquipe(EquipeDTO equipeDto) {
        String sql = "insert into equipes (nom) values (:nom)";

        Equipe newEquipe;
        KeyHolder keyHolder = new GeneratedKeyHolder();
        Map<String, Object> params = new HashMap<>();
        params.put("nom", equipeDto.nom());

        try {
            namedParameterJdbcTemplate.update(
                    sql,
                    new MapSqlParameterSource(params),
                    keyHolder,
                    new String[]{"id"} // le nom de la clé primaire générée
            );
            newEquipe = new Equipe();
            BeanUtils.copyProperties(equipeDto, newEquipe);
            newEquipe.setNoEquipe(keyHolder.getKey().intValue());
        } catch (DuplicateKeyException exc) {
            throw new EquipeDejaExistante(equipeDto.nom());
        }
        return newEquipe;
    }

   /*
    @Override
    public Optional<Equipe> findEquipeByNom(String nom) {
        String sql =  "SELECT noEquipe, nom FROM equipes WHERE nom =?";

        Equipe equipe = null;
        try{
            equipe = jdbcTemplate.queryForObject(sql, new EquipeRowMapper(), nom);}
        catch (EmptyResultDataAccessException e){
            //rien: equipe = null
        }

        return Optional.ofNullable(equipe);
    }*/


    class EquipeRowMapper implements RowMapper<Equipe> {

        @Override
        public Equipe mapRow(ResultSet rs, int rowNum) throws SQLException {
            Equipe equipe = new Equipe();
            equipe.setNoEquipe(rs.getInt("noEquipe"));
            equipe.setNom(rs.getString("nom"));

            return equipe;
        }
    }

}
