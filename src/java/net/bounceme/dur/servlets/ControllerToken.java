package net.bounceme.dur.servlets;

import java.util.Enumeration;
import java.util.NoSuchElementException;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;

public class ControllerToken {

    private static Logger log = Logger.getLogger(ControllerToken.class.getName());

    private String myName = "someone";
    private String myId = "5232435";

    //  private String name = "nemo";
    //   private MyRoles role = MyRoles.USER;  //only users for now
    //   private String password = "abc";
    //   private boolean authenticated = false;
    //   private boolean attemptedLogin = false;
    //   private String greeting = "";
    //   private Map<String, MyRoles> mapOfUsers;  //needs its own wrapper, really...or something
    //   private String duke;
    public ControllerToken() {  //would like to make private
        log.severe("new token, default");
    }

    public void initRequest(HttpServletRequest req) {
        log.info("initRequest....");
        Enumeration<String> attributes = req.getAttributeNames();
        Enumeration<String> parameters = req.getParameterNames();
        try {
            traverse(attributes);
        } catch (NoSuchElementException nse) {
            log.info(nse.toString());
        }
        try {
            traverse(parameters);
        } catch (NoSuchElementException nse) {
            log.info(nse.toString());
        }

        log.info("...initRequest");
    }

    private void traverse(Enumeration<String> a) throws NoSuchElementException {
        String string = null;
        while (a.hasMoreElements()) {
            string = a.nextElement();
            log.info(string);
        }
    }

    public String getMyName() {
        return myName;
    }

    public String getMyId() {
        return myId;
    }

    @Override
    public String toString() {
        return "foo";
    }

}
