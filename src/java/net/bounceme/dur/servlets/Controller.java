package net.bounceme.dur.servlets;

import filter.PropertiesReader;
import java.io.IOException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/controller")
public class Controller extends HttpServlet {

    private static final Logger log = Logger.getLogger(Controller.class.getName());

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("controller processing request..");
        String myContextParam
                = request.getSession().getServletContext().getInitParameter("name");
        log.info(myContextParam);
        String jsp = dispatcherLogic(request.getSession());
        request.getRequestDispatcher("/WEB-INF/" + jsp).forward(request, response);
    }

    private String dispatcherLogic(HttpSession session) {
        Properties properties = PropertiesReader.getProps();
        MyToken token = (MyToken) session.getAttribute("token");
        if (token != null) {
            token.setAuthenticated(properties.containsValue(token.getName()));
        } else {
            token = new MyToken();
        }
        log.info(token.toString());
        session.setAttribute("token", token);
        if (token.isAuthenticated()) {
            return "success.jsp";
        } else {
            if (token.isAttemptedLogin()) {
                return "fail.jsp";
            } else {
                return "login.jsp";
            }
        }
    }

    private String dispatcherLogic0(HttpSession session) {
        Map<String, String> p = PropertiesReader.getPropsAsMap();
        Enumeration<String> names = session.getAttributeNames();
        for (String s : Collections.list(names)) {
            log.info(s);
        }
        MyToken t = (MyToken) session.getAttribute("token");
        for (String s : p.keySet()) {
            //  t.getName() = p.containsValue(s);
        }
        return "hello.jsp";  //always to hello page for now
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "controller";
    }
}
