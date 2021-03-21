package fr.esiea.ex4A.myData;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AgifyData {
    public final String name;
    public final int age;
    public final int count;
    public final String country_id;

    @JsonCreator
    public AgifyData(
            @JsonProperty("name") String name,
            @JsonProperty("age") int age,
            @JsonProperty("count") int count,
            @JsonProperty("country_id") String country_id) {

        this.name = name;
        this.age = age;
        this.count = count;
        this.country_id = country_id;
    }
}
