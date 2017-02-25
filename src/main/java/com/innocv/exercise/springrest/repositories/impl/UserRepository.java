package com.innocv.exercise.springrest.repositories.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.innocv.exercise.springrest.repositories.IUserRepository;
import com.innocv.exercise.springrest.repositories.entities.UserDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Repository
public class UserRepository implements IUserRepository {

	@Autowired
    private JdbcTemplate jdbcTemplate;

   

    @Override
    @Transactional(readOnly=true)
    public UserDao getUser(Long id) {
    	 return jdbcTemplate.queryForObject(
    	            "select * from test.users where id=?",
    	            new Object[]{id}, new UserRowMapper());
    }

    @Override
    @Transactional(readOnly=true)
    public List<UserDao> listUsers() {
    	
    	return jdbcTemplate.query("select * from test.users", 
                new UserRowMapper());
    }

    @Override
    public UserDao createUser(final UserDao userDao) {
    	 final String sql = "insert into test.users(name,birthdate) values(?,?)";
    	 
         KeyHolder holder = new GeneratedKeyHolder();
         jdbcTemplate.update(new PreparedStatementCreator() {
             @Override
             public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                 PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                 ps.setString(1, userDao.getName());
                 ps.setString(2, userDao.getBirthDate());
                 return ps;
             }
         }, holder);
  
         int newUserId = holder.getKey().intValue();
         userDao.setId(Long.valueOf(newUserId));
         return userDao;
    }

    @Override
    public UserDao updateUser(final UserDao userDao) {
    	 final String updateSql = "UPDATE test.users SET name= ? ,birthdate= ? WHERE id=?";
         jdbcTemplate.update(new PreparedStatementCreator() {
             @Override
             public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                 PreparedStatement ps = connection.prepareStatement(updateSql, Statement.NO_GENERATED_KEYS);
                 ps.setString(1, userDao.getName());
                 ps.setString(2, userDao.getBirthDate());
                 ps.setInt(3, userDao.getId().intValue());
                 return ps;
             }
         });
         return userDao;
    }

    @Override
    public void removeUser(final Long id) {
    	final String deleteSql = "DELETE FROM test.users WHERE id = ?";
    	jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(deleteSql, Statement.NO_GENERATED_KEYS);
                ps.setInt(1, id.intValue());
                return ps;
            }
        });
    }
    
    class UserRowMapper implements RowMapper<UserDao>
    {
        @Override
        public UserDao mapRow(ResultSet rs, int rowNum) throws SQLException {
            UserDao user = new UserDao();
            user.setId(rs.getLong("id"));
            user.setName(rs.getString("name"));
            user.setBirthDate(rs.getString("birthdate"));
            return user;
        }
    }
}
