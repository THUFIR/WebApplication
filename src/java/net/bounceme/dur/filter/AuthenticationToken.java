package net.bounceme.dur.filter;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import javax.servlet.FilterConfig;
import net.bounceme.dur.servlets.ControllerToken;
import net.bounceme.dur.servlets.MyRoles;

public class AuthenticationToken {

    private static Logger log = Logger.getLogger(AuthenticationToken.class.getName());
    private Map<String, MyRoles> mapOfUsers;
    private String duke = "duke..";

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

    public String getDuke() {
        return duke;
    }

}
