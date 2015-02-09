package net.bounceme.dur.servlets;

import net.bounceme.dur.filter.AuthenticationToken;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;

public class LogTokens {

    private static final Logger log = Logger.getLogger(LogTokens.class.getName());

    private LogTokens() {
    }

    public static void logFilterToken(HttpServletRequest request, String className) {
        log.info(LogTokens.class.getName() + "\tlogFilterToken..");
        log.info(className);
        AuthenticationToken token = (AuthenticationToken) request.getAttribute("filterToken");
        try {
            log.info(token.toString());
        } catch (NullPointerException npe) {
            log.info("null token in " + className);
        }
        log.info(LogTokens.class.getName() + "\t..logFilterToken");
    }

    public static void logControllerToken(HttpServletRequest request, String className) {
        log.info(LogTokens.class.getName() + "\tlogControllerToken..");
        log.info(className);
        ControllerToken token = (ControllerToken) request.getAttribute("controllerToken");
        try {
            log.info(token.toString());
        } catch (NullPointerException npe) {
            log.info("null token in " + className);
        }
        log.info(LogTokens.class.getName() + "\t..logControllerToken");
    }
}
