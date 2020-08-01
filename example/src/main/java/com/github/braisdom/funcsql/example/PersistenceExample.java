package com.github.braisdom.funcsql.example;

import com.github.braisdom.funcsql.Database;
import com.github.braisdom.funcsql.PersistenceException;

import java.io.File;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class PersistenceExample {

    private static void createMember() throws SQLException, PersistenceException {
        Map<String, Object> extendedAttributes = new HashMap<>();
        extendedAttributes.put("hobbies", new String[]{"Play football"});
        extendedAttributes.put("age", 28);

        Domains.Member newMember = new Domains.Member()
                .setId(12)
                .setNo("200000")
                .setName("Smith")
                .setGender(1)
                .setExtendedAttributes(extendedAttributes)
                .setMobile("15011112222");

        Domains.Member.create(newMember);
    }

    private static void createMemberArray() throws SQLException, PersistenceException {
        Domains.Member newMember1 = new Domains.Member()
                .setNo("200001")
                .setName("Alice")
                .setGender(0)
                .setMobile("15011112222");

        Domains.Member newMember2 = new Domains.Member()
                .setNo("200003")
                .setName("Mary")
                .setGender(0)
                .setMobile("15011112222");

        Domains.Member.create(new Domains.Member[]{newMember1, newMember2});
    }

    private static void updateSmithMember() throws SQLException, PersistenceException {
        Domains.Member newMember = new Domains.Member()
                .setName("Smith => Jackson");

        Domains.Member.update(12, newMember);
    }

    private static void updateJacksonMember() throws SQLException, PersistenceException {
        Domains.Member.update("name = 'Smith => Jackson => Davies'", "name = 'Smith => Jackson'");
    }

    public static void main(String args[]) throws SQLException, PersistenceException {
        File file = new File("persistence_example.db");

        if (file.exists())
            file.delete();

        Database.installConnectionFactory(new SqliteConnectionFactory(file.getPath()));
        Domains.createTables(Database.getConnectionFactory().getConnection());

        createMember();
        createMemberArray();
        updateSmithMember();
        updateJacksonMember();
    }
}