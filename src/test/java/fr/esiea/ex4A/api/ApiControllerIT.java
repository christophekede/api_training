package fr.esiea.ex4A.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static org.hamcrest.CoreMatchers.endsWith;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import fr.esiea.ex4A.API.*;
import fr.esiea.ex4A.myData.UserData;



@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
class ApiControllerIT {

    private final MockMvc mockMvc;

    @MockBean
    private ApiController ApiControlerMock;

    ApiControllerIT(@Autowired MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Test
    void hello_user_sign_up() throws Exception {
        String jsonData = "{\"userEmail\":\"chris@test.fr\",\"userName\":\"toto\",\"userTweeter\":\"tata\",\"userCountry\":\"US\",\"userSex\":\"M\",\"userSexPref\":\"F\"}";
        UserData user = new UserData("chris@test.fr", "toto", "tata", "US", "M", "F");
       
        when(ApiControlerMock.inscription(any(UserData.class))).thenReturn(ResponseEntity.status(HttpStatus.CREATED).body(user));

        mockMvc
        .perform(MockMvcRequestBuilders.post("/api/inscription")
        .contentType(MediaType.APPLICATION_JSON)
        .content(jsonData)
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated())
        .andExpect(content().json("""
                {
                   "userEmail":"chris@test.fr",
                    "userName":"toto",
                    "userTweeter":"tata",
                    "userCountry":"US",
                    "userSex":"M",
                    "userSexPref":"F"
                }
                """));
}

   
}
