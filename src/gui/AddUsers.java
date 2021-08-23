package gui;

import domain.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import repository.userRepository;
import service.userService;

import java.util.ArrayList;

public class AddUsers {
    @FXML
    ListView listView;

    public User user;

    @FXML
    TextField searchBar;

    public void initialize(){

    }

    public void setUser(User user){
        this.user = user;
    }


    public void populateList(User user){
        userRepository userRepo = new userRepository();
        setUser(user);
        //ObservableList<String> items = userRepo.getUsersName(user.getId());
        ArrayList<User> items = userRepo.getUsersName(user.getId());
        ObservableList<User> users = FXCollections.observableArrayList();
        ArrayList<Integer> fren = userRepo.getUserFriends(user);
        for(User i : items)
        {
           if(!fren.contains(i.getId()))
           {
               users.add(i);
           }
        }
        listView.setItems(users);
    }

    public void addFriend()
    {
        userRepository userRepo = new userRepository();
        User userToAdd = (User) listView.getSelectionModel().getSelectedItem();
        userRepo.addInFriendList(user.getId(),userToAdd.getId());
        populateList(user);
    }

    public void filterUsers(){
        String searchString = searchBar.getText();
        userRepository userRepo = new userRepository();
        userService userService = new userService();

        ArrayList<User> items = userRepo.getUsersName(user.getId());
        ArrayList<Integer> fren = userRepo.getUserFriends(user);
        ArrayList<User> friendList = new ArrayList<>();
        for(User i : items)
        {
            if(!fren.contains(i.getId()))
            {
                friendList.add(i);
            }
        }

        ArrayList<User> filteredUsers = userService.filterUsers(friendList,searchString);

        ObservableList<User> users = FXCollections.observableArrayList();
        users.addAll(filteredUsers);

        listView.setItems(users);
    }

}
