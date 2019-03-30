package ru.kpfu.itis.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.model.User;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class UsersDaoImpl implements UsersDao {
    private JdbcTemplate jdbcTemplate;

    private final String SQL_FIND_BY_ID = "SELECT * FROM desvelado.user WHERE id = ?";

    private final String SQL_UPDATE_BY_ID = "UPDATE desvelado.user SET email = ?, username = ?, " +
            "password = ?, gender = ?, birthday = ? WHERE id = ?";

    private final String SQL_FIND_ALL = "SELECT * FROM desvelado.user";

    private final String SQL_INSERT = "INSERT INTO desvelado.user(email, username, password, gender, birthday) VALUES (?,?,?,?,?)";

    private final String SQL_DELETE_BY_ID = "DELETE FROM desvelado.user WHERE id = :id";

    @Autowired
    public UsersDaoImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private RowMapper<User> userRowMapper = (resultSet, i) ->
            new User(resultSet.getInt("id"),
                    resultSet.getString("email"),
                    resultSet.getString("username"),
                    resultSet.getString("password"),
                    resultSet.getBoolean("gender"),
                    resultSet.getDate("birthday"));

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public User find(Integer id) {
        return jdbcTemplate.queryForObject(SQL_FIND_BY_ID, userRowMapper, id);
    }

    @Override
    public void save(User model) {
        jdbcTemplate.update(SQL_INSERT, model.getEmail(), model.getUsername(), model.getPassword(),
                model.isGender(), model.getBirthday());
    }

    @Override
    public void update(User model) {
        jdbcTemplate.update(SQL_UPDATE_BY_ID, model.getEmail(), model.getUsername(), model.getPassword(),
                model.isGender(), model.getBirthday(), model.getId());
    }

    @Override
    public void delete(User model) {
        jdbcTemplate.update(SQL_DELETE_BY_ID, model.getId());
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query(SQL_FIND_ALL, userRowMapper);
    }
}
