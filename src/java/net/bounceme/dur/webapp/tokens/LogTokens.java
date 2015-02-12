package net.bounceme.dur.webapp.tokens;

import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;

public class LogTokens {

    private static final Logger log = Logger.getLogger(LogTokens.class.getName());

    private LogTokens() {
    }

    public static void logFilterRequest(HttpServletRequest request, String className) {
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

    public static void logAuthenticateToken(AuthenticationToken token, String className) {
        log.info(LogTokens.class.getName() + "\tlogFilterToken..");
        try {
            log.info(token.toString());
        } catch (NullPointerException npe) {
            log.info("null token in " + className);
        }
        log.info(LogTokens.class.getName() + "\t..logFilterToken");
    }

    public static void logControllerToken(ControllerToken token, String className) {
        log.info(LogTokens.class.getName() + "\tlogControllerToken..");
        log.info(className);
        try {
            log.info(token.toString());
        } catch (NullPointerException npe) {
            log.info("null token in " + className);
        }
        log.info(LogTokens.class.getName() + "\t..logControllerToken");
    }
}
