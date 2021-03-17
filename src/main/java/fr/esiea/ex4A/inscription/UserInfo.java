package fr.esiea.ex4A.inscription;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserInfo {
   
    public final String userEmail;
    public final String userName;
    public final String userTweeter;
    public final String userCountry;
    public final String userSex;
    public final String userSexPref;

    
    public UserInfo(  @JsonProperty(value = "userEmail", required = true) String userEmail, @JsonProperty(value = "userName", required = true) String userName,
    @JsonProperty(value = "userTweeter", required = true) String userTweeter,
    @JsonProperty(value = "userCountry", required = true) String userCountry,
    @JsonProperty(value = "userSex", required = true) String userSex,
    @JsonProperty(value = "userSexPref", required = true) String userSexPref) {
        this.userEmail = userEmail;
        this.userName = userName;
        this.userTweeter = userTweeter;
        this.userCountry = userCountry;
        this.userSex = userSex;
        this.userSexPref = userSexPref;
        

    }

  
}