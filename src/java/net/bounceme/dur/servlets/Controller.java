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
        String jsp = dispatcherLogic(request.getSession());
        request.getRequestDispatcher("/WEB-INF/" + jsp).forward(request, response);
    }

    private String dispatcherLogic(HttpSession session) {
        Properties p = PropertiesReader.getProps();
        MyToken t = (MyToken) session.getAttribute("token");
        if (t!=null){
            t.setAuthenticated(p.containsValue(t.getName()));
        } else {
            t = new MyToken();
        }
        log.info(t.toString());
        session.setAttribute("token", t);
        if (t.isAuthenticated()) {
            return "success.jsp";
        } else {
            if (t.isAttemptedLogin()) {
                return "success.jsp";
            } else {
                return "success.jsp";
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
