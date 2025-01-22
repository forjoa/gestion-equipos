package org.example.lib;

import org.example.models.Team;

import java.util.List;

public class TeamTableConverter {
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
