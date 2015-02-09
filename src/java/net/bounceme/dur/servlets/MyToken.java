package net.bounceme.dur.servlets;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class MyToken {

    private static Logger log = Logger.getLogger(MyToken.class.getName());

    private String myName = "someone";
    private String myId = "5232435";
    private String name = "nemo";
    private MyRoles role = MyRoles.USER;  //only users for now
    private String password = "abc";
    private boolean authenticated = false;
    private boolean attemptedLogin = false;
    private String greeting = "";
    private Map<String, MyRoles> mapOfUsers;  //needs its own wrapper, really...or something
    private String duke;

    public MyToken() {
        log.severe("new token, default");
    }

    public MyToken(Enumeration<String> users) {
        log.severe("new token with users");
        populateUsers(users);
    }

    @Override
    public String toString() {
        return getName() + isAuthenticated() + isAttemptedLogin();
    }

    public String getMyName() {
        return myName;
    }

    public void setMyName(String myName) {
        this.myName = myName;
    }

    public String getMyId() {
        return myId;
    }

    public void setMyId(String myId) {
        this.myId = myId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        setGreeting();
    }

    public MyRoles getRole() {
        return role;
    }

    public void setRole(MyRoles role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }

    public boolean isAttemptedLogin() {
        return attemptedLogin;
    }

    public void setAttemptedLogin(boolean attemptedLogin) {
        this.attemptedLogin = attemptedLogin;
    }

    private void populateUsers(Enumeration<String> users) {
        mapOfUsers = new HashMap<>();
        while (users.hasMoreElements()) {
            mapOfUsers.put(users.nextElement().toLowerCase(), MyRoles.USER);  //everyone is a user..?
        }

    }

    public String getGreeting() {
        return greeting;
    }

    private void setGreeting() {
        greeting = "";
        if (mapOfUsers.containsValue(name)) {
            authenticated = true;
            greeting = "welcome " + name + "you're authenticated";
        } else {
            greeting = "welcome " + name;
        }
        greeting = (name == null) ? "" : greeting;
    }

    public String getDuke() {
        return duke;
    }

    public void setDuke(String duke) {
        this.duke = duke;
    }

}
