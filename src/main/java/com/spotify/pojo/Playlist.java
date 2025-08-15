package com.spotify.pojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a Spotify Playlist request/response object.
 * This class is used for both serialization (request) and
 * deserialization (response).
 */
@JsonInclude(JsonInclude.Include.NON_NULL) // Ignore null fields during serialization
public class Playlist {

    private String name;
    private String description;

    @JsonProperty("public")
    private Boolean _public; // 'public' is a reserved word in Java

    private Boolean collaborative;
    private List<Track> tracks; // Optional, for response mapping

    // ==== Constructors ====
    public Playlist() {}

    public Playlist(String name, String description, Boolean _public) {
        this.name = name;
        this.description = description;
        this._public = _public;
    }

    public Playlist(String name, String description, Boolean _public, Boolean collaborative) {
        this.name = name;
        this.description = description;
        this._public = _public;
        this.collaborative = collaborative;
    }

    // ==== Getters & Setters ====
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getPublic() {
        return _public;
    }
    public void setPublic(Boolean _public) {
        this._public = _public;
    }

    public Boolean getCollaborative() {
        return collaborative;
    }
    public void setCollaborative(Boolean collaborative) {
        this.collaborative = collaborative;
    }

    public List<Track> getTracks() {
        return tracks;
    }
    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }

    @Override
    public String toString() {
        return "Playlist{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", public=" + _public +
                ", collaborative=" + collaborative +
                ", tracks=" + tracks +
                '}';
    }
}
