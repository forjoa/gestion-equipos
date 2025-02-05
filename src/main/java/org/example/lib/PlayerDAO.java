package org.example.lib;

import org.example.models.Database;
import org.example.models.Player;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class to access database data
 * @author Joaquin Trujillo
 */
public class PlayerDAO {
    private final Connection database;

    public PlayerDAO() throws SQLException, IOException {
        database = Database.getConnection();
    }

    /**
     * Function to insert new player
     * @param player all the player information
     * @return boolean to show the correct insertion in the database
     */
    public boolean addPlayer(Player player) {
        try (PreparedStatement stmt = database.prepareStatement(Constants.INSERT_PLAYER)) {
            stmt.setString(1, player.getName());
            stmt.setString(2, player.getPosition());
            stmt.setInt(3, player.getTeam_id());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Function to get all players list
     * @return list of Player
     */
    public List<Player> getAllPlayers() {
        List<Player> players = new ArrayList<>();
        try (Statement stmt = database.createStatement();
             ResultSet rs = stmt.executeQuery(Constants.GET_PLAYERS)
        ) {
            while (rs.next()) {
                Player player = new Player(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("posicion"),
                        rs.getInt("equipo_id")
                );
                players.add(player);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return players;
    }

    /**
     * Function to get a single player based on the ID
     * @param id player identification number
     * @return Player found, null in case of not found
     */
    public Player getPlayerById(Integer id) {
        try (PreparedStatement stmt = database.prepareStatement(Constants.PLAYER_BY_ID)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Player(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("posicion"),
                        rs.getInt("equipo_id")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    /**
     * Function to update a player information
     * @param player Player object to edit, including the ID
     * @return boolean to know the correct update in the database
     */
    public boolean updatePlayer(Player player) {
        try (PreparedStatement stmt = database.prepareStatement(Constants.UPDATE_PLAYER)) {
            stmt.setString(1, player.getName());
            stmt.setString(2, player.getPosition());
            stmt.setInt(3, player.getTeam_id());
            stmt.setInt(4, player.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Function to delete a player based on the ID
     * @param id player identification number
     * @return boolean to know the correct deletion in the database
     */
    public boolean deletePlayer(Integer id) {
        try (PreparedStatement stmt = database.prepareStatement(Constants.DELETE_PLAYER)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
