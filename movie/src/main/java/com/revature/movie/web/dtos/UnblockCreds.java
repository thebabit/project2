package com.revature.movie.web.dtos;

import java.util.Objects;

public class UnblockCreds {
    private String username;

    public UnblockCreds() {super();
    }

    public UnblockCreds(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UnblockCreds that = (UnblockCreds) o;
        return Objects.equals(username, that.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }

    @Override
    public String toString() {
        return "UnblockCreds{" +
                "username='" + username + '\'' +
                '}';
    }
}
