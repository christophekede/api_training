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

import fr.esiea.ex4A.cache.Cache;
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
    public final Cache cache;
    public final AgifyService agService;
    @Autowired
    public final AgifyClient agClient;

    public ApiController(AgifyClient agClient) {
        UserRepository userRepo = new UserRepository();
        this.userService = new UserService(userRepo);
        this.cache = new Cache();
        this.agClient = agClient;
        this.agService = new AgifyService(agClient);
        //this.userService.addUser(new UserData(new UserData("ff", "kam", "userTweeter", "FR", "F", "M"), 22));
        this.userService.addUser(new UserData(new UserData("ff", "eng", "userTweeter", "FR", "M", "F"), 24));
        this.userService.addUser(new UserData(new UserData("ff", "ang", "userTweeter", "US", "M", "F"), 58));
        this.userService.addUser(new UserData(new UserData("ff", "chan", "userTweeter", "FR", "F", "M"), 2));
        this.cache.addUser("kam", "FR", 22);
    }

    @PostMapping(path = "/api/inscription", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserData> inscription(@Valid @RequestBody UserData user) {
            if(this.cache.getUserCachedAge(user.userName, user.userCountry) == -1){
               saveUser(user);
               
            }else{
                System.out.println("cahc");
                userService.addUser(new UserData(user, this.cache.getUserCachedAge(user.userName, user.userCountry)));
            }         
            return new ResponseEntity<UserData>(this.userService.getUsersList().get(0), HttpStatus.CREATED);
       
        }
    @GetMapping(path = "/api/matches", produces ="application/json")
    ResponseEntity<List<MatchData>> matches(@RequestParam(name = "userName", required = false) String name,
            @RequestParam(name = "userCountry", required = false) String country) {
                UserData user = userService.getUserByName(name);
                if(user != null){
                  List<MatchData> matches = new ArrayList<>();
                  List<UserData> users =   userService.getMatchUsers(name, user.getAge().get(), user.userSexPref);
                  users.forEach(usr -> matches.add(new MatchData(usr.userName, usr.userTweeter)));
                  return new ResponseEntity<List<MatchData>>(matches, HttpStatus.OK);
                }
                return new ResponseEntity<List<MatchData>>(List.of(), HttpStatus.OK);
    }
    void saveUser (UserData user){
        Call<AgifyData> req = agClient.getCallerResponse(user.userName, user.userCountry);
        Response<AgifyData> res;
        try {
            res = req.execute();
            UserData userWithAge = new UserData(user, res.body().age);
            this.cache.addUser(user.userName, user.userCountry, res.body().age);
            userService.addUser(userWithAge);
        } catch (IOException e) {
        }
    }
}
