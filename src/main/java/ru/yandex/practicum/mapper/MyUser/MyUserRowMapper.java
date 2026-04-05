package ru.yandex.practicum.mapper.MyUser;

import org.springframework.jdbc.core.RowMapper;
import ru.yandex.practicum.entity.MyUser;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MyUserRowMapper implements RowMapper<MyUser> {
    @Override
    public MyUser mapRow(ResultSet rs, int rowNum) throws SQLException {
        MyUser myUser = new MyUser();
        myUser.setId(rs.getInt("id"));
        myUser.setUsername(rs.getString("username"));
        myUser.setPassword(rs.getString("password"));
        myUser.setRole(rs.getString("role"));
        return myUser;
    }
}
