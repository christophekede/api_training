  package fr.esiea.ex4A.API;


import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ch.qos.logback.core.status.Status;

@RestController
class InscriptionController {   


    InscriptionController() {
      
    }
    
    
    @PostMapping(path = "/api/inscription", produces = MediaType.APPLICATION_JSON_VALUE,  consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<UserInfo> inscription(@Valid @RequestBody UserInfo user) {
       
        return new ResponseEntity<UserInfo>(user, HttpStatus.CREATED);
        
        
    }
    @GetMapping(path = "/api/matches", produces = MediaType.APPLICATION_JSON_VALUE,  consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<Match>> matches(@RequestParam(name = "name", required = false) String name, @RequestParam(name = "twitter", required = false) String twiter) {
       
        List.of(new Match("kede", "twi_kede"));
        return new ResponseEntity<List<Match>>(List.of(new Match("kede", "chris"))   , HttpStatus.OK);
        
        
    }
}
