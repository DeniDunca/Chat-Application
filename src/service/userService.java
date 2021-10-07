package service;

import domain.User;
import repository.userRepository;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class userService {
    private repository.userRepository userRepository = new userRepository();

    public boolean addUser(String username, String password, String firstName, String lastName){
        userRepository.addUser(username,password,firstName,lastName);
        return true;
    }

    public boolean checkUser(String username,String password){
        return userRepository.checkUser(username,password);
    }

    public ArrayList<User> filterUsers(ArrayList<User> users,String string){
        //ArrayList<User> filteredUsers = new ArrayList<>();
        users = (ArrayList<User>) users.stream()
                .filter(u -> (u.getFirstName() + " " + u.getLastName()).contains(string))
                .collect(Collectors.toList());

        return users;
    }
}
