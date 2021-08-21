package model;

public class AppData {
    private static AppData appData_instance;
    private Integer loggedInUserId = null;
    private String chatUser;

    AppData() {
    }

    public static AppData getInstance() {
        if (appData_instance == null) appData_instance = new AppData();
        return appData_instance;
    }

    public int getLoggedInUserId() {
        return this.loggedInUserId;
    }

    public void setLoggedInUserId(Integer loggedInUserId) {
        this.loggedInUserId = loggedInUserId;
    }

    public String getChatUser() {
        return this.chatUser;
    }

    public void setChatUser(String user) {
        this.chatUser = user;
    }
}
