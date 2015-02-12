package net.bounceme.dur.webapp.filter;

import net.bounceme.dur.webapp.tokens.AuthenticationToken;
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
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;

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
    private final String className = AuthenticationFilter.class.getName();
    private Map<String, String> parameters = new HashMap<>();
    private Map<String, String> users = new HashMap<>();
    private boolean auth = false;
    private String contextPath;

    public AuthenticationFilter() {
    }

    private void doBeforeProcessing(ServletRequest request, ServletResponse response) throws IOException, ServletException {
        log.fine("do before processing..");  //init?
    }

    private void doAfterProcessing(ServletRequest request, ServletResponse response) throws IOException, ServletException {
        log.fine("do after processing");  //add image here?
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info(className + "do filter..");
        token.setToken((HttpServletRequest) request);
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
        log.info(className + "\tinit..");
        this.filterConfig = filterConfig;
        if (filterConfig != null) {
            log.fine("SessionCheckFilter:Initializing filter");
        } else {
            log.warning("null filterConfig");
        }
        String context = filterConfig.getServletContext().getServletContextName();
        String path = filterConfig.getServletContext().getContextPath();
        contextPath = "http://localhost:8080" + context + path;
        String duke = contextPath + "/duke.gif";
        token.setDuke(duke);
        token.initFilterConfig(filterConfig);
        log.info(token.toString());
        log.info(className + "\t..init");
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
