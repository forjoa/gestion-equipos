package org.example.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Player {
    private Integer id;

    @JsonProperty("nombre")
    private String name;

    @JsonProperty("posicion")
    private String position;

    @JsonProperty("equipo_id")
    private Integer team_id;

    @Override
    public String toString() {
        return name;
    }
}
