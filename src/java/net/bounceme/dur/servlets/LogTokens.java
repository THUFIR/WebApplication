package net.bounceme.dur.servlets;

import net.bounceme.dur.filter.AuthenticationToken;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;

//make all static methods?
public class LogTokens {

    private static final Logger log = Logger.getLogger(LogTokens.class.getName());
    private String loggingClass;

    private LogTokens() {
    }

    public static void logFilterToken(HttpServletRequest request, String className) {
        log.info(className);
        AuthenticationToken token = (AuthenticationToken) request.getAttribute("filterToken");
        try {
            log.info(token.toString());
        } catch (NullPointerException npe) {
            log.info("null token in " + className);
        }
    }

    public static void logControllerToken(HttpServletRequest request, String className) {
        log.info(className);
        ControllerToken token = (ControllerToken) request.getAttribute("controllerToken");
        try {
            log.info(token.toString());
        } catch (NullPointerException npe) {
            log.info("null token in " + className);
        }
    }

    /*

     public LogAttributesAndParameters(HttpServletRequest request, String className) {
     log.info("\n************************");
     log.info("\n************************");
     log.info("\n************************");
     log.info("\n************************");
     log.info("logging for " + className);
     Enumeration<String> a = request.getAttributeNames();
     Enumeration<String> p = request.getParameterNames();

     String k, v = null;
     while (a.hasMoreElements()) {
     k = a.nextElement();
     try {
     v = request.getAttribute(k).toString();
     } catch (NullPointerException npe) {
     log.info(npe.toString());
     }
     log.info(className);
     log.info(k);
     log.info(v);
     }

     while (p.hasMoreElements()) {
     k = p.nextElement();
     v = request.getParameter(k);
     log.info(className);
     log.info(k);
     log.info(v);
     }

     this.loggingClass = className;
     }
     */
}
