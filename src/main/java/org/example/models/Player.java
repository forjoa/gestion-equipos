package org.example.models;

public class Player {
    private Integer id;
    private String name;
    private String position;
    private Integer team_id;

    public Player(Integer id, String name, String position, Integer team_id) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.team_id = team_id;
    }

    public Player() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Integer getTeam_id() {
        return team_id;
    }

    public void setTeam_id(Integer team_id) {
        this.team_id = team_id;
    }
}
