package net.bounceme.dur.webapp.tokens;

import java.util.logging.Logger;

public class ControllerToken implements MyToken {

    private static Logger log = Logger.getLogger(ControllerToken.class.getName());

    private String myName = "someone";
    private String myId = "5232435";

    private final String className = ControllerToken.class.getName();

    public ControllerToken() {  //would like to make private
        log.info(ControllerToken.class.getName() + "\tnew..");
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

}
