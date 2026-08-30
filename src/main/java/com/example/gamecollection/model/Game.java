package com.example.gamecollection.model;

public class Game {

    private Long id;
    private String title;
    private String platform;
    private String genre;
    private boolean physical;

    public Game() {
    }

    public Game(Long id, String title, String platform, String genre, boolean physical) {
        this.id = id;
        this.title = title;
        this.platform = platform;
        this.genre = genre;
        this.physical = physical;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public boolean isPhysical() {
        return physical;
    }

    public void setPhysical(boolean physical) {
        this.physical = physical;
    }
}
