package gui;

import domain.Message;
import domain.SelectedMenu;
import domain.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import observer.Observer;
import observer.Subject;
import repository.messageRepository;
import repository.userRepository;
import service.userService;

import java.io.IOException;
import java.util.ArrayList;

public class MainWindow implements Observer {

    Enum<SelectedMenu> selected = SelectedMenu.NONE;

    @FXML
    ImageView logOutButton;

    @FXML
    ListView listView;

    @FXML
    TextArea messageArea;

    @FXML
    ImageView imagePlus;

    @FXML
    ImageView imageFriends;

    @FXML
    ImageView imageBubble;

    @FXML
    ImageView imageSend;

    @FXML
    Label labelUser;

    @FXML
    TextField textField;

    @FXML
    TextField searchBar;

    private User user;

    Image hoverExit_plus = new Image("photos/plus.png");
    Image hoverEnter_plus = new Image("photos/plus_orange.png");
    Image hoverExit_friends = new Image("photos/friends.png");
    Image hoverEnter_friends = new Image("photos/friends_orange.png");
    Image hoverExit_bubble = new Image("photos/speech-bubbles-with-ellipsis.png");
    Image hoverEnter_bubble = new Image("photos/speech-bubbles-with-ellipsis_orange.png");
    Image hoverExit_logout = new Image("photos/logout.png");
    Image hoverEnter_logout = new Image("photos/logout_orange.png");
    Image hoverExit_send = new Image("photos/send.png");
    Image hoverEnter_send = new Image("photos/send_orange.png");


    public void initialize(){
        Subject subject = Subject.getInstance();
        subject.addObserver(this);
        listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

    }

    public void addUsers(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("addUsers.fxml"));
            Parent root = fxmlLoader.load();
            imagePlus.setImage(hoverExit_plus);
            AddUsers addUsers = fxmlLoader.getController();
            addUsers.populateList(user);

            Stage stage = new Stage();
            stage.setScene(new Scene(root,400,400));
            stage.setTitle("Choose an user!");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void logOutPressed()
    {
        Stage stage = (Stage) logOutButton.getScene().getWindow();
        stage.close();
    }

    public void setUser(String username, String password) {
        userRepository userRepository = new userRepository();
        user = userRepository.getUser(username,password);
    }

    public void setTitle(Stage stage){
        stage.setTitle("Welcome " + user.getFirstName() + " " + user.getLastName() + "!");
    }

    public void displayMessages(){
        User selectedUser = (User) listView.getSelectionModel().getSelectedItem();
        //System.out.println(selectedUser);
        messageRepository messageRepository = new messageRepository();
        ArrayList<Message> messages = messageRepository.getMessages(user,selectedUser);
        labelUser.setText("@" + selectedUser.getUsername());
        String text = "";

        for(Message m : messages){
            if(m.getUser1() == user.getId()){
                text += user.getFirstName() + " " + user.getLastName() + ": " + m.getMessage() + '\n';
            }else{
                text += selectedUser.getFirstName() + " " + selectedUser.getLastName() + ": " + m.getMessage() + '\n';
            }
        }
        messageArea.setText(text);
        messageArea.setScrollTop(Double.MAX_VALUE);
    }

    public void setFriendList(){
        System.out.println(user);
        listView.getItems().clear();
        selected = SelectedMenu.FRIEND_LIST;
        ObservableList<User> friendList = FXCollections.observableArrayList();
        userRepository userRepository = new userRepository();
        ArrayList<Integer> friendListId = userRepository.getUserFriends(user);
        friendListId.forEach(System.out::println);
        for(Integer f : friendListId){
            friendList.add(userRepository.getUser(f));
        }
        listView.setItems(friendList);
    }

    public void showListMessages()
    {
        listView.getItems().clear();
        selected = SelectedMenu.MESSAGE_LIST;
        ObservableList<User> userList = FXCollections.observableArrayList();
        messageRepository messageRepository = new messageRepository();
        userRepository userRepository = new userRepository();
        ArrayList<Integer> users = messageRepository.getUserMessage(user);
        for(int u : users)
        {
            userList.add(userRepository.getUser(u));
        }
        listView.setItems(userList);
    }

    public void sendMessage()
    {
        Subject subject = Subject.getInstance();
        messageRepository messageRepository = new messageRepository();
        User selectedUser = (User) listView.getSelectionModel().getSelectedItem();
        String message = textField.getText();
        messageRepository.addMessage(user.getId(),selectedUser.getId(),message);
        displayMessages();
        textField.clear();
        subject.notifyObs();
    }

    public void filterList(){
        String filterText = searchBar.getText();
        userService userService = new userService();
        ArrayList<User> users = new ArrayList<>();
        if(selected == SelectedMenu.FRIEND_LIST) {
            ArrayList<Integer> friendListId = userRepository.getUserFriends(user);
            for(Integer f : friendListId){
                users.add(userRepository.getUser(f));
            }
        }
        else {
            ArrayList<Integer> usersList = messageRepository.getUserMessage(user);
            for(int u : usersList)
            {
                users.add(userRepository.getUser(u));
            }
        }

        ArrayList<User> filteredUsers = userService.filterUsers(users,filterText);
        ObservableList<User> filteredUsersObs = FXCollections.observableArrayList();
        filteredUsersObs.addAll(filteredUsers);
        listView.setItems(filteredUsersObs);

    }


    public void whenHoverEnterPlus()
    {
        imagePlus.setImage(hoverEnter_plus);
    }
    public void whenHoverExitPlus()
    {
        imagePlus.setImage(hoverExit_plus);
    }
    public void whenHoverEnterFriends()
    {
        imageFriends.setImage(hoverEnter_friends);
    }
    public void whenHoverExitFriends()
    {
        imageFriends.setImage(hoverExit_friends);
    }
    public void whenHoverEnterBubble()
    {
        imageBubble.setImage(hoverEnter_bubble);
    }
    public void whenHoverExitBubble()
    {
        imageBubble.setImage(hoverExit_bubble);
    }
    public void whenHoverEnterLogOut()
    {
        logOutButton.setImage(hoverEnter_logout);
    }
    public void whenHoverExitLogOut()
    {
        logOutButton.setImage(hoverExit_logout);
    }
    public void whenHoverEnterSend()
    {
        imageSend.setImage(hoverEnter_send);
    }
    public void whenHoverExitSend()
    {
        imageSend.setImage(hoverExit_send);
    }

    @Override
    public void update() {
        displayMessages();
    }
}
