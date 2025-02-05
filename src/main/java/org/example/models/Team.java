package org.example.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Class to save all the team information like the id, name, city and stadium
 * @author Joaquin Trujillo
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Team {
    private Integer id;
    private String name;
    private String city;
    private String stadium;

    @Override
    public String toString() {
        return name;
    }
}
