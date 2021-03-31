package fr.esiea.ex4A.API;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.esiea.ex4A.myData.AgifyData;
import fr.esiea.ex4A.myData.MatchData;
import fr.esiea.ex4A.myData.UserData;
import fr.esiea.ex4A.myInterface.AgifyClient;
import fr.esiea.ex4A.myService.AgifyService;
import fr.esiea.ex4A.myService.UserService;
import fr.esiea.ex4A.repo.UserRepository;
import retrofit2.Call;
import retrofit2.Response;


@RestController
public class ApiController {

    public final UserService userService;
    public final AgifyService agService;
    @Autowired
    public final AgifyClient agClient;

    public ApiController(AgifyClient agClient) {
        UserRepository userRepo = new UserRepository();
        this.userService = new UserService(userRepo);
        this.agClient = agClient;
        this.agService = new AgifyService(agClient);
    }

    @PostMapping(path = "/api/inscription", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserData> inscription(@Valid @RequestBody UserData user) {
        
        Call<AgifyData> req = agClient.getCallerResponse(user.userName, user.userCountry);
        try {
            Response<AgifyData> res = req.execute();
            UserData userWithAge = new UserData(user, res.body().age);
            userService.addUser(userWithAge);
           
            return new ResponseEntity<UserData>(this.userService.getUsersList().get(0), HttpStatus.CREATED);
        } catch (IOException e) {
            return new ResponseEntity<UserData>(this.userService.getUsersList().get(0), HttpStatus.CREATED);
        }

    }

    @GetMapping(path = "/api/matches", produces ="application/json")
    ResponseEntity<List<MatchData>> matches(@RequestParam(name = "userName", required = false) String name,
            @RequestParam(name = "userCountry", required = false) String country) {
                UserData user = userService.getUserByName(name);
                if(user != null){
                  List<MatchData> matches = new ArrayList<>();
                  List<UserData> users =   userService.getMatchUsers(name, user.getAge().get(), user.userSex);
                  users.forEach(usr -> matches.add(new MatchData(usr.userName, usr.userTweeter)));
                  return new ResponseEntity<List<MatchData>>(List.of(new MatchData("kede", "chris"), new MatchData("toto", "tata"), new MatchData("tu", "ta")), HttpStatus.OK);
                }

                return new ResponseEntity<List<MatchData>>(List.of(), HttpStatus.OK);

    }
}
