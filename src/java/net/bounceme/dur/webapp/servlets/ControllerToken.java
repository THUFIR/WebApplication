package net.bounceme.dur.webapp.servlets;

import java.util.Enumeration;
import java.util.NoSuchElementException;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import net.bounceme.dur.webapp.MyToken;

public class ControllerToken implements MyToken{

    private static Logger log = Logger.getLogger(ControllerToken.class.getName());

    private String myName = "someone";
    private String login = "nemo";
    private String myId = "5232435";

    public ControllerToken() {  //would like to make private
        log.info(ControllerToken.class.getName() + "\tnew..");
    }

    public void initRequest(HttpServletRequest req) {
        log.info(ControllerToken.class.getName() + "\tinitRequest..");
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
        log.info(ControllerToken.class.getName() + "\t..initRequest");
    }

    private void traverse(Enumeration<String> a) throws NoSuchElementException {
        log.info(ControllerToken.class.getName() + "\ttraverse..");

        String string = null;
        while (a.hasMoreElements()) {
            string = a.nextElement();
            log.info(string);
        }
        log.info(ControllerToken.class.getName() + "\t..traverse");
    }

    public String getMyName() {
        return myName;
    }

    public void setLogin(String login) {
        this.login = login.toLowerCase();
    }

    public String getLogin() {
        return login;
    }

    public String getMyId() {
        return myId;
    }

    @Override
    public String toString() {
        return ControllerToken.class.getName() + "\t\t" + login;
    }

}
