package net.bounceme.dur.webapp.filter;

import net.bounceme.dur.webapp.tokens.AuthenticationToken;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@WebFilter(
        filterName = "authenticationFilter",
        description = "authentication",
        urlPatterns = "/*",
        initParams = {
            @WebInitParam(name = "1", value = "bart"),
            @WebInitParam(name = "2", value = "homer")})

public class AuthenticationFilter implements Filter {

    private static final Logger log = Logger.getLogger(AuthenticationFilter.class.getName());
    private FilterConfig filterConfig = null;
    private final Map<String, String> mapOfUsers = new HashMap<>();
    private final AuthenticationToken token = new AuthenticationToken();
    private final String className = AuthenticationToken.class.getName();
    private Map<String, String> parameters = new HashMap<>();
    private Map<String, String> users = new HashMap<>();

    public AuthenticationFilter() {
    }

    private void doBeforeProcessing(ServletRequest request, ServletResponse response) throws IOException, ServletException {
        log.fine("do before processing..");  //init?
    }

    private void doAfterProcessing(ServletRequest request, ServletResponse response) throws IOException, ServletException {
        log.fine("do after processing");  //add image here?
    }

    private void initFilterParams() {
        log.info(className + "\tinitFilterParams..");
        Enumeration parameterNames = filterConfig.getInitParameterNames();
        users = new HashMap<>();  //?
        String name = null;
        String val = null;
        while (parameterNames.hasMoreElements()) {
            name = parameterNames.nextElement().toString();
            val = filterConfig.getInitParameter(name);
            users.put(name, val);
        }
        log.info(className + "\n" + users);
        log.info(className + "\t..initFilterParams");
    }

    private void initParams(HttpServletRequest request) {
        log.info(className + "\tinitParams..");
        parameters = new HashMap<>();  //?
        Enumeration<String> params = request.getParameterNames();
        String name = null;
        String val = null;
        while (params.hasMoreElements()) {
            name = params.nextElement();
            val = request.getParameter(name);
            parameters.put(name, val);
        }
        log.info(className + "\n" + parameters);
        log.info(className + "\t..initParams");
    }

    private void setToken(HttpServletRequest request) {
        initParams(request);
        String login = null;
        if (parameters.containsKey("login")) {
            login = parameters.get("login").toLowerCase();
            token.setLogin(login);
            if (users.containsValue(login)) {
                token.setGreeting("welcome " + login + " you've been authorized.");
            } else {
                token.setGreeting("welcome " + login);
            }
            HttpSession session = request.getSession(false);
            if (session != null) {
                session.setAttribute("authorization", token);
            }
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info(className + "do filter..");
        setToken((HttpServletRequest) request);
        chain.doFilter(request, response);
        log.info(className + "..do filter");
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
        initFilterParams();
    }

    @Override
    public String toString() {
        return token.toString();
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
