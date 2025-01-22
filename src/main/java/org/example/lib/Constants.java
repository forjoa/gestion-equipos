package org.example.lib;

public class Constants {
    // teams queries
    public static final String INSERT_TEAM = "INSERT INTO equipos (nombre, ciudad, estadio) VALUES (?, ?, ?)";
    public static final String GET_TEAMS = "SELECT * FROM equipos";
    public static final String TEAM_BY_ID = "SELECT * FROM equipos WHERE id = ?";
    public static final String UPDATE_TEAM = "UPDATE equipos SET nombre = ?, ciudad = ?, estadio = ? WHERE id = ?";
    public static final String DELETE_TEAM = "DELETE FROM equipos WHERE id = ?";
}
