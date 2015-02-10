package net.bounceme.dur.webapp.filter;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.logging.Logger;
import javax.servlet.FilterConfig;
import javax.servlet.http.HttpServletRequest;
import net.bounceme.dur.webapp.MyToken;
import net.bounceme.dur.webapp.servlets.ControllerToken;
import net.bounceme.dur.webapp.servlets.MyRoles;

public class AuthenticationToken implements MyToken {

    private static Logger log = Logger.getLogger(AuthenticationToken.class.getName());
    private Map<String, MyRoles> mapOfUsers;
    private String duke = "duke..";
    private String login = "nemo";
    private String c = AuthenticationToken.class.getName();
    private boolean auth = false;
    private String greeting = "";
    private ControllerToken controllerToken = null;

    public AuthenticationToken() {  //would like to make private
        log.info(AuthenticationToken.class.getName() + "\tnew..");
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
        log.info(c + "\tinitRequest..");
        Enumeration<String> attributes = req.getAttributeNames();
        Enumeration<String> parameters = req.getParameterNames();
        try {
            traverse(attributes);
        } catch (NoSuchElementException nse) {
            log.warning(nse.toString());
        }
        try {
            traverse(parameters);
        } catch (NoSuchElementException nse) {
            log.warning(nse.toString());
        }
        login = req.getParameter("login");
        controllerToken = (ControllerToken) req.getAttribute("controllerToken");
        authenticate();
        log.info(c + "\t..initRequest");
    }

    private void traverse(Enumeration<String> a) throws NoSuchElementException {
        log.info(c + "\ttraverse..");
        String string = null;
        while (a.hasMoreElements()) {
            string = a.nextElement();
            log.info(string);
        }
        log.info(c + "\t..traverse");
    }

    public String getDuke() {
        return duke;
    }

    public void setDuke(String duke) {
        this.duke = duke;
    }

    private void authenticate() {
        log.info(c + "\tauthenticate..+++++++++++++++++++++++++++++++++++");
        log.info(mapOfUsers.toString());
        if (controllerToken == null) {
            log.info("null controller token in authenticate");
            auth = true;//false should be
            greeting = "";  //make null?
        } else {
            login = controllerToken.getLogin();
            auth = mapOfUsers.containsKey(login);
            if (auth) {
                greeting = "welcome " + login + "you've been authenticated";
            } else {
                greeting = "welcome " + login;
            }
        }
        log.info(greeting);
        log.info(c + "\t..authenticate*****************************************");
    }

    public String getGreeting() {
        return greeting;
    }

    @Override
    public String toString() {
        return c + "\t" + greeting;
    }

}
