package fr.eni.basket.dal;


import fr.eni.basket.bo.Equipe;
import fr.eni.basket.bo.Joueur;
import fr.eni.basket.dto.JoueurDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class JoueurDAOImpl implements JoueurDAO {

    private NamedParameterJdbcTemplate namedJdbcTemplate;
    private JdbcTemplate jdbcTemplate;

    public JoueurDAOImpl(NamedParameterJdbcTemplate namedJdbcTemplate) {
        this.namedJdbcTemplate = namedJdbcTemplate;
        this.jdbcTemplate = namedJdbcTemplate.getJdbcTemplate();

    }

    @Override
    public List<Joueur> findAllJoueurs() {

        String sql = """            
            select j.noJoueur, j.nom, prenom, email, noEquipe, e.nom as nom_equipe 
            from joueurs j inner join equipes e on j.noEquipe=e.noEquipe
             """;
        return jdbcTemplate.query(sql, new JoueurRowMapper());
        }

    @Override
    public List<Joueur> findJoueursByNomEquipe(String nomEquipe) {
            String sql = """
            select j.noJoueur, j.nom, prenom, email, noEquipe, e.nom as nom_equipe 
            from joueurs j inner join equipes e on j.noEquipe=e.noEquipe
            where e.nom = ? 
             """;
            return jdbcTemplate.query(sql, new JoueurRowMapper(), nomEquipe);
    }

    @Override
    public Optional<Joueur> findJoueurByNoJoueur(int noJoueur) {
        String sql = "select j.noJoueur, j.nom, j.prenom, j.email, j.noEquipe, e.nom as nom_equipe from joueurs j inner join equipes e " +
                "on j.noEquipe=e.noEquipe where j.noJoueur = ?";
        Joueur joueur = jdbcTemplate.queryForObject(sql, new JoueurRowMapper(), noJoueur);
        return Optional.ofNullable(joueur);
    }

    class JoueurRowMapper implements RowMapper<Joueur> {

        @Override
        public Joueur mapRow(ResultSet rs, int rowNum) throws SQLException {
            Joueur  joueur = new Joueur();
            joueur.setNoJoueur(rs.getInt("noJoueur"));
            joueur.setNom(rs.getString("nom"));
            joueur.setPrenom(rs.getString("prenom"));
            joueur.setEmail(rs.getString("email"));
            Equipe equipe = new Equipe();
            equipe.setNoEquipe(rs.getInt("noEquipe"));
            equipe.setNom(rs.getString("nom_equipe"));
            joueur.setEquipe(equipe);
            return joueur;
        }
    }

    @Override
    public Joueur saveJoueur(JoueurDTO joueur) {
        String sql = "insert into joueurs ( nom, prenom, email, id_equipe) values ( :nom, :prenom, :email, :id_equipe)";

        MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();
        sqlParameterSource.addValue("nom", joueur.nom());
        sqlParameterSource.addValue("prenom", joueur.prenom());
        sqlParameterSource.addValue("email", joueur.email());
        sqlParameterSource.addValue("id_equipe", joueur.noEquipe());

        //KeyHolder permet de récupérer la clé primaire générée par la base
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int nbRows = namedJdbcTemplate.update(sql, sqlParameterSource, keyHolder );
        Joueur newJoueur = new Joueur();
        BeanUtils.copyProperties(joueur, newJoueur);
        newJoueur.setEquipe(new Equipe());
        newJoueur.getEquipe().setNoEquipe(keyHolder.getKey().intValue());
        newJoueur.setNoJoueur(keyHolder.getKey().intValue());
        return newJoueur;
    }
}
