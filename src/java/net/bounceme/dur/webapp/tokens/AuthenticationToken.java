package net.bounceme.dur.webapp.tokens;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import javax.servlet.FilterConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AuthenticationToken implements MyToken {

    private static final Logger log = Logger.getLogger(AuthenticationToken.class.getName());
    private Map<String, MyRoles> mapOfUsers;
    private String duke = "duke..";
    private String login = "nemo";
    private String className = AuthenticationToken.class.getName();
    private boolean auth = false;
    private String greeting = "";
    private Map<String, String> users = new HashMap<>();
    private Map<String, String> parameters = new HashMap<>();
    private String contextPath;

    public AuthenticationToken() {  //would like to make private
        log.info(AuthenticationToken.class.getName() + "\tnew..");
    }

    public void initFilterConfig(FilterConfig filterConfig) {
        log.info(className + "\tinitFilterConfig..");
        Enumeration parameterNames = filterConfig.getInitParameterNames();
        users = new HashMap<>();  //?
        String name = null;
        String val = null;
        while (parameterNames.hasMoreElements()) {
            name = parameterNames.nextElement().toString();
            val = filterConfig.getInitParameter(name);
            users.put(name, val);
        }
        log.info(className + "\n" + users);
        log.info(className + "\t..initFilterConfig");
    }

    private void initParams(HttpServletRequest request) {
        log.info(className + "\tinitParams..");
        parameters = new HashMap<>();  //?
        Enumeration<String> params = request.getParameterNames();
        String name = null;
        String val = null;
        while (params.hasMoreElements()) {
            name = params.nextElement();
            val = request.getParameter(name);
            parameters.put(name, val);
        }
        log.info(className + "\n" + parameters);
        log.info(className + "\t..initParams");
    }

    public void setToken(HttpServletRequest request) {
        log.info(className + "\tsetToken..");
        initParams(request);
        String login = null;
        if (parameters.containsKey("login")) {
            login = parameters.get("login").toLowerCase();
            setLogin(login);
            if (users.containsValue(login)) {
                auth = true;
                setGreeting("welcome " + login + " you've been authorized.");
                String image = contextPath + "/" + login + ".gif";
            } else {
                auth = false;
                setGreeting("welcome " + login);
                String image = contextPath + "/duke.gif";
            }
        } else {
            auth = false;
            String image = contextPath + "/duke.gif";
        }
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.setAttribute("auth", this); //??
        }
        log.info(toString());
        log.info(className + "\t..setToken");
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
        return className;
    }

    public void setCn(String cn) {
        this.className = cn;
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

    @Override
    public String toString() {
        return duke;
    }

}
