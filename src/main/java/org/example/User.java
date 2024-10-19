package org.example;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

    private String login;
    private int id;
    @JsonProperty("user_view_type")
    private String userViewType;
    @JsonProperty("public_repos")
    private int publicRepos;

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getUserViewType() {
        return userViewType;
    }

    public int getPublicRepos() {
        return publicRepos;
    }
}
