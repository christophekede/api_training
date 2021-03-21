package fr.esiea.ex4A.myService;

import org.springframework.stereotype.Service;

import fr.esiea.ex4A.myData.AgifyData;
import fr.esiea.ex4A.myInterface.AgifyClient;
import retrofit2.Call;
import retrofit2.Response;

@Service
public class AgifyService {
    public final AgifyClient agClient;

    public AgifyService(AgifyClient agClient){
        this.agClient = agClient;
    }

    public AgifyData fetchUserAge(String name, String country_id){
        try {
            Call<AgifyData> fecthAge = agClient.getCallerResponse(name, country_id);
            Response<AgifyData> res = fecthAge.execute();
    
            if(res.isSuccessful()){
                return res.body();
            }
     
        } catch (Exception e) {
            System.out.println(e);

    }
    return null;
}
    
}
