package org.example.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Useful class to get the team information with the Team class, and including the player list
 * @author Joaquin Trujillo
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TeamPlayers {
    private Team team;
    private List<Player> playersList;
}
