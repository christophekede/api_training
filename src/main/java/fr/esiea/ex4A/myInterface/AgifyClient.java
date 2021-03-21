package fr.esiea.ex4A.myInterface;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import fr.esiea.ex4A.myData.AgifyData;


public interface AgifyClient {
    @GET("/")
    Call<AgifyData> getCallerResponse(@Query("name") String name, @Query("country_id") String country_id);
        
    

}