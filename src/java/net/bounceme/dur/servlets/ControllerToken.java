package net.bounceme.dur.servlets;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import javax.servlet.FilterConfig;
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
