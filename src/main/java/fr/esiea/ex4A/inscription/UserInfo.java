package fr.esiea.ex4A.inscription;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;


public class UserInfo {
    @NotBlank(message = "Name is mandatory")
    public final String userEmail;
    public final String userName;
    public final String userTweeter;
    @Pattern(regexp="^[A-Z]{2}$",message="length must be 2")
    public final String userCountry;
    @Pattern(regexp="^[MFO]$",message="length must be 1, M, F, or O")  
    public final String userSex;
    @Pattern(regexp="^[MFO]$",message="length must be 1, M, F, or O")  
    public final String userSexPref;

    @JsonCreator
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