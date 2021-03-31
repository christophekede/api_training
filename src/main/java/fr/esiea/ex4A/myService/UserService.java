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

    public List<UserData> getMatchUsers(String userName, int age,String userSex,  String sexPref) {
        return this.getUsersList().stream()
                .filter(user -> !user.userName.toLowerCase().equals(userName.toLowerCase()) && (user.getAge().get() <= age + 4 && user.getAge().get() >= age - 4) && user.userSexPref.equals(userSex) && sexPref.equals(user.userSex)).collect(Collectors.toList());
    }

    public UserData getUserByName(String username) {
        for (UserData user : this.getUsersList()){
           //System.out.println(user.userName+" "+user.userSexPref+" "+user.getAge().get());
            if(user.userName.toLowerCase().equals(username.toLowerCase())){
                System.out.println(user.userName+" "+user.userSexPref+" "+user.getAge().get());
                return user;
            }
           
        }
        return null;
    }


}
