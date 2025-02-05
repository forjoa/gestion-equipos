package org.example.lib;

/**
 * Queries storage, here we have all the teams and players queries to get information, insert new data,
 * update data or delete it
 * @author Joaquin Trujillo
 */
public class Constants {
    // teams queries
    public static final String INSERT_TEAM = "INSERT INTO equipos (nombre, ciudad, estadio) VALUES (?, ?, ?)";
    public static final String GET_TEAMS = "SELECT * FROM equipos";
    public static final String TEAM_BY_ID = "SELECT * FROM equipos WHERE id = ?";
    public static final String UPDATE_TEAM = "UPDATE equipos SET nombre = ?, ciudad = ?, estadio = ? WHERE id = ?";
    public static final String DELETE_TEAM = "DELETE FROM equipos WHERE id = ?";
    public static final String TEAM_PLAYERS_BY_ID = "SELECT e.*, json_agg(j.*) FROM equipos e JOIN jugadores j ON j.equipo_id = e.id WHERE e.id = ? GROUP BY e.id";
    public static final String TEAM_PLAYERS = "SELECT e.*, json_agg(j.*) FROM equipos e JOIN jugadores j ON j.equipo_id = e.id GROUP BY e.id";

    // players queries
    public static final String INSERT_PLAYER = "INSERT INTO jugadores (nombre, posicion, equipo_id) VALUES (?, ?, ?)";
    public static final String GET_PLAYERS = "SELECT * FROM jugadores";
    public static final String PLAYER_BY_ID = "SELECT * FROM jugadores WHERE id = ?";
    public static final String UPDATE_PLAYER = "UPDATE jugadores SET nombre = ?, posicion = ?, equipo_id = ? WHERE id = ?";
    public static final String DELETE_PLAYER = "DELETE FROM jugadores WHERE id = ?";
}
