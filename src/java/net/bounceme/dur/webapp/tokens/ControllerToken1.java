package net.bounceme.dur.webapp.tokens;

import java.util.Enumeration;
import java.util.NoSuchElementException;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import net.bounceme.dur.webapp.tokens.MyToken;

public class ControllerToken1 implements MyToken {

    private static Logger log = Logger.getLogger(ControllerToken1.class.getName());

    private String myName = "someone";
    private String myId = "5232435";
    private String login = "nemo";
    private final String className = ControllerToken1.class.getName();

    public ControllerToken1() {  //would like to make private
        log.info(ControllerToken1.class.getName() + "\tnew..");
    }

    public void initRequest(HttpServletRequest req) {
        log.info(className + "\tinitRequest..");
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
        myName = req.getParameter("myName");
        myId = req.getParameter("myId");
        login = req.getParameter("login");
        log.info(className + "\t..initRequest");
    }

    private void traverse(Enumeration<String> a) throws NoSuchElementException {
        log.info(className + "\ttraverse..");
        String string = null;
        while (a.hasMoreElements()) {
            string = a.nextElement();
            log.info(string);
        }
        log.info(className + "\t..traverse");
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
        return ControllerToken1.class.getName() + "\t\t" + myName + myId + login;
    }

    void setMyName(String myName) {
        this.myName = myName;
    }

    void setMyId(String myId) {
        this.myId = myId;
    }

}
