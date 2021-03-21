package fr.esiea.ex4A.repo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import fr.esiea.ex4A.myData.UserData;

@Service
public class UserRepository {
    private final List<UserData> users = new ArrayList<UserData>();

    public List<UserData> getUserRepo(){
        return this.users;
    }

    public boolean addUser(UserData user){
        return this.users.add(user);
    }

    public UserData getUserByName(String name){
        for(UserData user : this.users){
            if(user.userName.equals(name)){
                return user;
            }
        }
        return null;
    }
}
