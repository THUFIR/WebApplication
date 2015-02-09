package net.bounceme.dur.servlets;

import java.util.Enumeration;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;

//make all static methods?

public class LogAttributesAndParameters {

    private static final Logger log = Logger.getLogger(LogAttributesAndParameters.class.getName());
    private String loggingClass;

    private LogAttributesAndParameters() {
    }

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

}
