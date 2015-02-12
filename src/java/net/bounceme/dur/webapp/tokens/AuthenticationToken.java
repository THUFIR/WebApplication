package net.bounceme.dur.webapp.tokens;

import java.util.Map;
import java.util.logging.Logger;

public class AuthenticationToken implements MyToken {

    private static Logger log = Logger.getLogger(AuthenticationToken.class.getName());
    private Map<String, MyRoles> mapOfUsers;
    private String duke = "duke..";
    private String login = "nemo";
    private String cn = AuthenticationToken.class.getName();
    private boolean auth = false;
    private String greeting = "";
    // private ControllerToken controllerToken = null;
    private String image = "duke";

    public AuthenticationToken() {  //would like to make private
        log.info(AuthenticationToken.class.getName() + "\tnew..");
    }

    public Map<String, MyRoles> getMapOfUsers() {
        return mapOfUsers;
    }

    public void setMapOfUsers(Map<String, MyRoles> mapOfUsers) {
        this.mapOfUsers = mapOfUsers;
    }

    public String getDuke() {
        return duke;
    }

    public void setDuke(String duke) {
        this.duke = duke;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getCn() {
        return cn;
    }

    public void setCn(String cn) {
        this.cn = cn;
    }

    public boolean isAuth() {
        return auth;
    }

    public void setAuth(boolean auth) {
        this.auth = auth;
    }

    public String getGreeting() {
        return greeting;
    }

    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    @Override
    public String toString() {
        return duke;
    }

}
