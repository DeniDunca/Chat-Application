package domain;

public class Message {

    private int user1;
    private int user2;
    private String message;

    public Message(int user1, int user2, String message) {
        this.user1 = user1;
        this.user2 = user2;
        this.message = message;
    }

    public int getUser1() {
        return user1;
    }

    public void setUser1(int user1) {
        this.user1 = user1;
    }

    public int getUser2() {
        return user2;
    }

    public void setUser2(int user2) {
        this.user2 = user2;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
