package fr.esiea.ex4A;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fr.esiea.ex4A.myInterface.AgifyCaller;
import okhttp3.OkHttpClient;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;

@SpringBootApplication
public class Launcher {
    @Bean
    public AgifyCaller agifyClient() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        Retrofit retrofit =
                new Retrofit.Builder()
                        .baseUrl("https://api.agify.io")
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(httpClient.build())
                        .build();

        return retrofit.create(AgifyCaller.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(Launcher.class, args);
    }
}
