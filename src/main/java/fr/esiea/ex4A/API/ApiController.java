  package fr.esiea.ex4A.API;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.esiea.ex4A.myData.MatchData;
import fr.esiea.ex4A.myData.UserData;
import fr.esiea.ex4A.myInterface.AgifyClient;
import fr.esiea.ex4A.myService.UserService;
import fr.esiea.ex4A.repo.UserRepository;

@RestController
class ApiController {  

    // @Autowired
     public final UserService userService;

    // public ApiController (UserService userService) {
    //     this.userService = userService;
      
    
     ApiController() {
         UserRepository userRepo = new UserRepository();
         this.userService = new UserService(userRepo);
         

        
    }
    
    
    @PostMapping(path = "/api/inscription", produces = MediaType.APPLICATION_JSON_VALUE,  consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<UserData> inscription(@Valid @RequestBody UserData user) {
       
        userService.addUser(user);
        // AgifyClient.getCallerResponse("name", "FR");
        return new ResponseEntity<UserData>(this.userService.getUsersList().get(0), HttpStatus.CREATED);
        
        
    }
    @GetMapping(path = "/api/matches", produces = MediaType.APPLICATION_JSON_VALUE,  consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<MatchData>> matches(@RequestParam(name = "userName", required = false) String name, @RequestParam(name = "userCountry", required = false) String country) {
       System.out.println(name);
       System.out.println(userService.getUsersList().get(0));
        return new ResponseEntity<List<MatchData>>(List.of(new MatchData("kede", "chris"))   , HttpStatus.OK);
        
        
    }
}
