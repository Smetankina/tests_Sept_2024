package org.helpusdefend.model;

public class User {

    private String id;
    private String token;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User(String id, String token) {
        this.id = id;
        this.token = token;
    }

    public User(String id){
        this(id, null);
    }

    public User() {
        this(null, null);  // Default constructor with null values
    }
}
