package fr.esiea.ex4A.myService;

import fr.esiea.ex4A.repo.UserRepository;
import fr.esiea.ex4A.myData.UserData;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<UserData> getMatchUsers(String userName, int age, String sexPref) {
        return this.getUsersList().stream()
                .filter(user -> user.getAge().get() <= age + 4 && user.getAge().get() >= age - 4 && !user.userName.toLowerCase().equals(userName.toLowerCase()) && user.userSex.equals(sexPref)).collect(Collectors.toList());
    }

    public UserData getUserByName(String username) {
        for (UserData user : this.getUsersList()){
            if(user.userName.toLowerCase().equals(username.toLowerCase()))
                return user;
        }
        return null;
    }


}
