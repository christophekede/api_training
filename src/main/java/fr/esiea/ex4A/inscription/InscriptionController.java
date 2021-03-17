package fr.esiea.ex4A.inscription;


import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
class InscriptionController {   


    InscriptionController() {
      
    }
    
    
    @PostMapping(path = "/api/inscription", produces = MediaType.APPLICATION_JSON_VALUE,  consumes = MediaType.APPLICATION_JSON_VALUE)
    UserInfo inscription( @RequestBody UserInfo user) {
        System.out.println("==================  "+user.userEmail);
        return user;
        
    }
}
