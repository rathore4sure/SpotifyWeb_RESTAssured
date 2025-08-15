package com.spotify.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Album implements Serializable {

    private String id;
    private String name;
    private String release_date;
    private List<String> genres;

    public Album() {
    }

    public Album(String id, String name, String release_date, List<String> genres) {
        this.id = id;
        this.name = name;
        this.release_date = release_date;
        this.genres = genres;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    @Override
    public String toString() {
        return "Album{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", release_date='" + release_date + '\'' +
                ", genres=" + genres +
                '}';
    }
}
