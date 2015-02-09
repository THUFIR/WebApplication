package net.bounceme.dur.webapp.filter;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.bounceme.dur.webapp.servlets.ControllerToken;
import net.bounceme.dur.webapp.servlets.LogTokens;

public class AuthenticateFilter implements Filter {

    private static final Logger log = Logger.getLogger(AuthenticateFilter.class.getName());
    private FilterConfig filterConfig = null;
    private Map<String, String> mapOfUsers = new HashMap<>();
    private AuthenticationToken token;// = new MyToken();
    // private Enumeration<String> users = null;

    public AuthenticateFilter() {
    }

    private void doBeforeProcessing(ServletRequest request, ServletResponse response) throws IOException, ServletException {
        log.fine("do before processing..");
    }

    private void doAfterProcessing(ServletRequest request, ServletResponse response) throws IOException, ServletException {
        log.fine("do after processing");  //add image here?
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.fine("do filter");
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        AuthenticationToken tempToken = (AuthenticationToken) req.getAttribute("filterToken");
        if (tempToken == null) {
            token = new AuthenticationToken();
            token.initFilterConfig(filterConfig);
        } else {
            token = tempToken;
        }
        String duke = "http://" + req.getServerName() + ":" + req.getServerPort() + req.getServletContext().getContextPath() + "/duke.gif";
        token.initRequest(req);
        token.setDuke(duke);
        //token.authenticate();
      //  token.setDuke(duke);
        req.setAttribute("filterToken", token);
        LogTokens.logFilterToken(req, AuthenticateFilter.class.getName());
        chain.doFilter(request, response);
    }

    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    @Override
    public void destroy() {
    }

    @Override
    public void init(FilterConfig filterConfig) {
        log.info("init filter");
        this.filterConfig = filterConfig;
        if (filterConfig != null) {
            log.fine("SessionCheckFilter:Initializing filter");
        } else {
            log.warning("null filterConfig");
        }
        token = new AuthenticationToken();
        token.initFilterConfig(getFilterConfig());
    }

    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("SessionCheckFilter()");
        }
        StringBuilder sb = new StringBuilder("SessionCheckFilter(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }

    private void sendProcessingError(Throwable t, ServletResponse response) {
        log.fine("send processing error");
        String stackTrace = getStackTrace(t);

        if (stackTrace != null && !stackTrace.equals("")) {
            try {
                response.setContentType("text/html");
                try (PrintStream ps = new PrintStream(response.getOutputStream()); PrintWriter pw = new PrintWriter(ps)) {
                    pw.print("<html>\n<head>\n<title>Error</title>\n</head>\n<body>\n"); //NOI18N
                    pw.print("<h1>The resource did not process correctly</h1>\n<pre>\n");
                    pw.print(stackTrace);
                    pw.print("</pre></body>\n</html>"); //NOI18N
                }
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        } else {
            try {
                try (PrintStream ps = new PrintStream(response.getOutputStream())) {
                    t.printStackTrace(ps);
                }
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        }
    }

    public static String getStackTrace(Throwable t) {
        String stackTrace = null;
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            pw.close();
            sw.close();
            stackTrace = sw.getBuffer().toString();
        } catch (Exception ex) {
        }
        log.warning(stackTrace);
        return stackTrace;
    }

}