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

/**
 * Class to access all the database information
 * @author Joaquin Trujillo
 */
public class TeamDAO {
    private final Connection database;

    public TeamDAO() throws SQLException, IOException {
        database = Database.getConnection();
    }

    /**
     * Function to create a new team
     * @param team all the team information recollected in form
     * @return boolean to know if the insertion went well
     */
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

    /**
     * Function to get all the teams
     * @return a list of Team
     */
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

    /**
     * Function to get a single team found by ID
     * @param id identification number from the team
     * @return the Team information or null in case not found
     */
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

    /**
     * Function to update the team information
     * @param team all the team information updated
     * @return boolean to know if the update went well
     */
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

    /**
     * Delete a team found by ID
     * @param id team identification number
     * @return boolean to know if the deletion went well
     */
    public boolean deleteTeam(Integer id) {
        try (PreparedStatement stmt = database.prepareStatement(Constants.DELETE_TEAM)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Function to get not just the team information but the players list
     * @param id team identification number
     * @return new Team Players object with team information and players list
     */
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

    /**
     * Function to get not just the team information but the players list from ALL the teams
     * @return a list from the Team Players object
     */
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
