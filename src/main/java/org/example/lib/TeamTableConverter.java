package org.example.lib;

import org.example.models.Team;

import java.util.List;

/**
 * Class that work like a helper to convert the objects in a multidimensional array to set in a table
 * @author Joaquin Trujillo
 */
public class TeamTableConverter {
    /**
     * Function to convert the list to array
     * @param teams the list of the object to convert
     * @return and multidimensional object array
     */
    public static Object[][] convertListToArray(List<Team> teams) {
        Object[][] data = new Object[teams.size()][4];

        for (int i = 0; i < teams.size(); i++) {
            Team team = teams.get(i);
            data[i][0] = team.getId();
            data[i][1] = team.getName();
            data[i][2] = team.getCity();
            data[i][3] = team.getStadium();
        }

        return data;
    }
}
