package org.example.lib;

import org.example.models.Database;
import org.example.models.Player;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlayerDAO {
    private final Connection database;

    public PlayerDAO() throws SQLException, IOException {
        database = Database.getConnection();
    }

    // insert new player
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

    // get all players
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

    // get a player by id
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

    // update player information
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

    // delete by id
    public boolean deletePlayer(Integer id) {
        try (PreparedStatement stmt = database.prepareStatement(Constants.DELETE_PLAYER)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
