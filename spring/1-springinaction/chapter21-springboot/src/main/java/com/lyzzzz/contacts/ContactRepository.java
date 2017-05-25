package com.lyzzzz.contacts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by 37 on 2017/3/10.
 */
@Repository
public class ContactRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Contact> findAll() {
        return jdbcTemplate
        .query("select id, firstName, lastName, phoneNumber, " +
                        "emailAddress from contacts order by lastName",
                new RowMapper<Contact>() {
                    @Override
                    public Contact mapRow(ResultSet resultSet, int i) throws SQLException {
                        Contact contact = new Contact();
                        contact.setId(resultSet.getLong(1));
                        contact.setFirstName(resultSet.getString(2));
                        contact.setLastName(resultSet.getString(3));
                        contact.setPhoneNumber(resultSet.getString(4));
                        contact.setEmailAddress(resultSet.getString(5));

                        return contact;
                    }
                });
    }
}
