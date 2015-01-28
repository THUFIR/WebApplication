package net.bounceme.dur.filter;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Properties;
import java.util.logging.Logger;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class AuthenticateFilter implements Filter {

    private static final Logger log = Logger.getLogger(AuthenticateFilter.class.getName());
    private FilterConfig filterConfig = null;

    public AuthenticateFilter() {
    }

    private void doBeforeProcessing(ServletRequest request, ServletResponse response) throws IOException, ServletException {
        log.info("do before processing..");
    }

    private void doAfterProcessing(ServletRequest request, ServletResponse response) throws IOException, ServletException {
        log.info("do after processing");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("do filter");
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
        log.info("init");

        this.filterConfig = filterConfig;
        if (filterConfig != null) {
            log.info("SessionCheckFilter:Initializing filter");
        } else {
            log.warning("null filterConfig");
        }
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
        log.info("send processing error");
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
