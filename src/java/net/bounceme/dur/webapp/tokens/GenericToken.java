package net.bounceme.dur.webapp.tokens;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import javax.servlet.FilterConfig;
import javax.servlet.http.HttpServletRequest;
import net.bounceme.dur.webapp.tokens.MyRoles;

public class GenericToken {

    private static Logger log = Logger.getLogger(GenericToken.class.getName());

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

    public GenericToken() {  //would like to make private
        log.severe("new token, default");
    }

    public void initFilterConfig(FilterConfig filterConfig) {
        mapOfUsers = new HashMap<>();
        Enumeration<String> initParams = filterConfig.getInitParameterNames();
        String paramName = null;
        String paramValue = null;
        while (initParams.hasMoreElements()) {
            paramName = initParams.nextElement();
            paramValue = filterConfig.getInitParameter(paramName);
            mapOfUsers.put(paramValue, MyRoles.USER);
        }
    }

    public void initRequest(HttpServletRequest req) {
        log.info("initRequest....");
        Enumeration<String> p = req.getParameterNames();
        Enumeration<String> a = req.getAttributeNames();
        String foo = null;
        while (a.hasMoreElements()) {
            foo = a.nextElement();
            log.info(foo);
        }
        while (p.hasMoreElements()) {
            foo = a.nextElement();
            log.info(foo);
        }
        log.info("...initRequest");
    }

    @Override
    public String toString() {
        configGreeting();
        return getName() + isAuthenticated() + isAttemptedLogin() + getGreeting() + mapOfUsers.toString();
    }

    public String getMyName() {
        return myName;
    }

    public void setMyName(String myName) {
        log.info(GenericToken.class.getName() + "\t trying to set name \t\t" + name);
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
        configGreeting();
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

    public void populateUsers(Enumeration<String> users) {
        mapOfUsers = new HashMap<>();
        while (users.hasMoreElements()) {
            mapOfUsers.put(users.nextElement().toLowerCase(), MyRoles.USER);  //everyone is a user..?
        }
        configGreeting();
    }

    public String getGreeting() {
        return greeting;
    }

    private void signIn() {
        if (mapOfUsers.containsValue(name)) {
            authenticated = true;
        } else {
            authenticated = false;
        }
    }

    private void configGreeting() {
        signIn();
        greeting = "";
        if (authenticated) {
            greeting = "welcome " + name + "you're authenticated";
        } else {
            greeting = "welcome " + name;
        }
        greeting = ((name == "") || (name == null) || (name.isEmpty())) ? "" : greeting;
    }

    public String getDuke() {
        return duke;
    }

    public void setDuke(String duke) {
        this.duke = duke;
    }

    private void populateMe() {

    }

}
