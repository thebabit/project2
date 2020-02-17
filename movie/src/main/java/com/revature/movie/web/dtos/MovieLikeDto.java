package com.revature.movie.web.dtos;

import java.util.Objects;

public class MovieLikeDto {

    private int id;
    private String title;
    private int userId;

    public MovieLikeDto() {
        super();
    }

    public MovieLikeDto(int id, String title, int userId) {
        this.id = id;
        this.title = title;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovieLikeDto that = (MovieLikeDto) o;
        return id == that.id &&
                userId == that.userId &&
                Objects.equals(title, that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, userId);
    }

    @Override
    public String toString() {
        return "MovieLikeDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", userId=" + userId +
                '}';
    }
}
