package net.bounceme.dur.filter;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
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
import net.bounceme.dur.servlets.LogAttributesAndParameters;

public class AuthenticateFilter implements Filter {

    private static final Logger log = Logger.getLogger(AuthenticateFilter.class.getName());
    private FilterConfig filterConfig = null;
    private Map<String, String> mapOfUsers = new HashMap<>();

    public AuthenticateFilter() {
    }

    private void doBeforeProcessing(ServletRequest request, ServletResponse response) throws IOException, ServletException {
        log.fine("do before processing..");
    }

    private void doAfterProcessing(ServletRequest request, ServletResponse response) throws IOException, ServletException {
        log.fine("do after processing");  //add image here?
    }

    private void makeMap() {
        Enumeration<String> parmNamesEnum = filterConfig.getInitParameterNames();
        List<String> keys = Collections.list(parmNamesEnum);
        for (String s : keys) {
            mapOfUsers.put(s.toLowerCase(), filterConfig.getInitParameter(s));
        }
        log.fine(mapOfUsers.toString());
    }

    private void foo() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.fine("do filter");
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String name = (String) request.getAttribute("name");
        try {
            name = name.toLowerCase();
        } catch (NullPointerException npe) {
            log.warning("null name; no worries");
        }
        //   String message = mapOfUsers.containsValue(name) ? "hello " + name : "no " + name;
        String myName = request.getServletContext().getInitParameter("me");
        String myId = request.getServletContext().getInitParameter("id");
        boolean authenticated = false;
        String greeting = "";
        if (mapOfUsers.containsValue(name)) {
            authenticated = true;
            greeting = "welcome " + name + "you're authenticated";
        } else {
            greeting = "welcome " + name;
        }
        greeting = (name == null) ? null : greeting;
        //   message = authenticated ? "hello " + name : "";
        String duke = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getServletContext().getContextPath() + "/duke.gif";
        request.setAttribute("myName", myName);
        request.setAttribute("myId", myId);
        request.setAttribute("authenticated", authenticated);
        request.setAttribute("greeting", greeting);
        LogAttributesAndParameters logRequest = new LogAttributesAndParameters((HttpServletRequest) request, AuthenticateFilter.class.getName());
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
        log.fine("init");

        this.filterConfig = filterConfig;
        if (filterConfig != null) {
            log.fine("SessionCheckFilter:Initializing filter");
        } else {
            log.warning("null filterConfig");
        }
        makeMap();  //debatable  and maybe should be in init?  only should run once.
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
