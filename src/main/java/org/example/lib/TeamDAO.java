package org.example.lib;

import org.example.models.Database;
import org.example.models.Team;

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
}
