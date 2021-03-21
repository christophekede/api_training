package fr.esiea.ex4A.myData;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MatchData {
    public final String name;
    public final String twitter;

    @JsonCreator
    public MatchData(@JsonProperty(value="userName", required = true) String name, @JsonProperty(value="userTweeter", required = true) String twitter){
        
        this.name = name;
        this.twitter = twitter;
    }
}
