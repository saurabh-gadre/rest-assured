package org.example;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

public class User2 {

    private String login;
    private int id;

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }
}
