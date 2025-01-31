package org.example.lib;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.models.Database;
import org.example.models.Player;
import org.example.models.Team;
import org.example.models.TeamPlayers;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TeamDAO {
    private final Connection database;

    public TeamDAO() throws SQLException, IOException {
        database = Database.getConnection();
    }

    // insert new team
    public boolean addTeam(Team team) {
        try (PreparedStatement stmt = database.prepareStatement(Constants.INSERT_TEAM)) {
            stmt.setString(1, team.getName());
            stmt.setString(2, team.getCity());
            stmt.setString(3, team.getStadium());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // get all teams
    public List<Team> getAllTeams() {
        List<Team> teams = new ArrayList<>();
        try (Statement stmt = database.createStatement();
             ResultSet rs = stmt.executeQuery(Constants.GET_TEAMS)
        ) {
            while (rs.next()) {
                Team team = new Team(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("ciudad"),
                        rs.getString("estadio")
                );
                teams.add(team);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return teams;
    }

    // get a team by id
    public Team getTeamById(Integer id) {
        try (PreparedStatement stmt = database.prepareStatement(Constants.TEAM_BY_ID)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Team(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("ciudad"),
                        rs.getString("estadio")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    // update team information
    public boolean updateTeam(Team team) {
        try (PreparedStatement stmt = database.prepareStatement(Constants.UPDATE_TEAM)) {
            stmt.setString(1, team.getName());
            stmt.setString(2, team.getCity());
            stmt.setString(3, team.getStadium());
            stmt.setInt(4, team.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // delete by id
    public boolean deleteTeam(Integer id) {
        try (PreparedStatement stmt = database.prepareStatement(Constants.DELETE_TEAM)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // get team info and players
    public TeamPlayers getAllTeamPlayersById(Integer id) {
        try (PreparedStatement stmt = database.prepareStatement(Constants.TEAM_PLAYERS_BY_ID)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            Team team = null;
            List<Player> playersList = new ArrayList<>();
            ObjectMapper objectMapper = new ObjectMapper();

            while (rs.next()) {
                if (team == null) {
                    team = new Team(
                            rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getString("ciudad"),
                            rs.getString("estadio")
                    );
                }

                // get JSON column added like string
                String jsonPlayers = rs.getString("json_agg");

                // get JSON in a Player list
                playersList = objectMapper.readValue(jsonPlayers, new TypeReference<>() {
                });
            }

            if (team != null) {
                return new TeamPlayers(team, playersList);
            } else {
                return null;
            }

        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<TeamPlayers> getAllTeamPlayers() {
        List<TeamPlayers> teamsList = new ArrayList<>();

        try (Statement stmt = database.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT e.*, json_agg(j.*) FROM equipos e JOIN jugadores j ON j.equipo_id = e.id GROUP BY e.id");

            ObjectMapper objectMapper = new ObjectMapper();

            while (rs.next()) {
                // create new team on each iteration
                Team team = new Team(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("ciudad"),
                        rs.getString("estadio")
                );

                // get JSON column added like string
                String jsonPlayers = rs.getString("json_agg");

                // get JSON as a Player list
                List<Player> playersList = objectMapper.readValue(jsonPlayers, new TypeReference<List<Player>>() {});

                // add team w/ players
                teamsList.add(new TeamPlayers(team, playersList));
            }

        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }

        return teamsList;
    }
}
