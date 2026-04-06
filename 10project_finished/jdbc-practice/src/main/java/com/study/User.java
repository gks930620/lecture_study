package com.study;

import java.util.Objects;

public class User {


    private final String userId;
    private final String email;
    private final String password;
    private final String name;

    public User(String userId, String password, String name, String email) {
        this.userId=userId;
        this.password=password;
        this.name=name;
        this.email=email;
    }



    public String getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userId, user.userId) && Objects.equals(email, user.email) && Objects.equals(password, user.password) && Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, email, password, name);
    }
}
