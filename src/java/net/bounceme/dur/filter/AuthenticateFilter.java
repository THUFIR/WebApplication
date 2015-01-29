package net.bounceme.dur.filter;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
            mapOfUsers.put(s, filterConfig.getInitParameter(s));
        }
        log.fine(mapOfUsers.toString());
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.fine("do filter");
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String uri = req.getRequestURI();
        log.fine("Requested Resource::" + uri);
        makeMap();  //debatable  and maybe should be in init?  only should run once.
        String name = (String) request.getAttribute("name");
        String message = mapOfUsers.containsValue(name) ? "hello " + name : "no " + name;
        String myName = request.getServletContext().getInitParameter("me");
        String myId = request.getServletContext().getInitParameter("id");
        String me = "my name is " + myName + " " + myId;
        boolean authenticated = mapOfUsers.containsValue(name) ? true : false;
        message = authenticated ? "hello " + name : "";
        log.info("filter user is\t\t\t" + message);
        log.info("filter authenticated is\t\t" + authenticated);
        log.info("filter message is\t\t" + message);
        request.setAttribute("me", me);
        request.setAttribute("authenticated", authenticated);
        request.setAttribute("message", message);
        chain.doFilter(request, response);
    }

    /*
     private void fileBytes(ServletResponse response) throws FileNotFoundException, IOException {
     String path = null;
     InputStream inputStream = new BufferedInputStream(new FileInputStream(new File(path)));
     OutputStream outputStream = response.getOutputStream();
     byte[] bytes = new byte[1024 * 2];
     int bytesRead = -1;
     while ((bytesRead = inputStream.read(bytes)) != -1) {
     outputStream.write(bytes, 0, bytesRead);
     }
     outputStream.flush();

     InputStream inputStream = null;
     try {
     inputStream = new BufferedInputStream(new FileInputStream(new File(path)));
     } catch (NullPointerException npe) {
     log.info(npe.toString());
     }
     OutputStream outputStream = response.getOutputStream();
     byte[] bytes = new byte[1024 * 2];
     int bytesRead = -1;
     try {
     while ((bytesRead = inputStream.read(bytes)) != -1) {
     outputStream.write(bytes, 0, bytesRead);
     }
     } catch (NullPointerException npe) {
     }
    
    
     String relativeWebPath = "WEB-INF/IMAGES/image1.jpg";
     String absoluteDiskPath = request.getServletContext().getRealPath(relativeWebPath);

     try {
     File file = new File(absoluteDiskPath);
     } catch (java.lang.NullPointerException npe) {
     log.info("no file");
     log.info(absoluteDiskPath);
     }
     String path = request.getServletContext().getRealPath(name + "gif");
     InputStream input = request.getServletContext().getResourceAsStream(relativeWebPath);
     InputStream inputStream = null;
     try {
     inputStream = new BufferedInputStream(new FileInputStream(new File(path)));
     } catch (NullPointerException npe) {
     log.info(npe.toString());
     }
     OutputStream outputStream = response.getOutputStream();
     byte[] bytes = new byte[1024 * 2];
     int bytesRead = -1;
     try {
     while ((bytesRead = inputStream.read(bytes)) != -1) {
     outputStream.write(bytes, 0, bytesRead);
     }
     } catch (NullPointerException npe) {
     }
     outputStream.flush();
     }
     */
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
