package fr.esiea.ex4A.myService;

import fr.esiea.ex4A.repo.UserRepository;
import fr.esiea.ex4A.myData.UserData;
import java.util.List;

public class UserService {
    private final UserRepository userRepo;

    public UserService(UserRepository userRepo){
        this.userRepo = userRepo;
    }

    public void addUser(UserData user){
        this.userRepo.addUser(user);
    }

    public List <UserData> getUsersList (){
        return this.userRepo.getUserRepo();
    }



}
