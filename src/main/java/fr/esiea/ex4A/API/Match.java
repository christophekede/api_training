package fr.esiea.ex4A.API;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Match {
    public final String name;
    public final String twitter;

    @JsonCreator
    public Match(@JsonProperty(value="name", required = true) String name, @JsonProperty(value="twitter", required = true) String twitter){
        this.name = name;
        this.twitter = twitter;
    }
}
