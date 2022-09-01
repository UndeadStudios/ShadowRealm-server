package io.exilius.sql;

import io.exilius.Configuration;

public class EmbeddedDatabase extends DatabaseCredentials {

    public EmbeddedDatabase(String name, String username, String password) {
        super("jdbc:derby:" + Configuration.USER_FOLDER + "/" + name + ";create=true", username, password);
    }

    public EmbeddedDatabase(String name) {
        this(name, "", "");
    }

}
