package net.bounceme.dur.servlets;

import java.io.IOException;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/controller")
public class Controller extends HttpServlet {

    private static final Logger log = Logger.getLogger(Controller.class.getName());

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.fine("controller processing request..");
        String name = (String) request.getAttribute("name");
        String myName = (String) request.getAttribute("myName");
        String myId = (String) request.getAttribute("myId");
        boolean authenticated = (Boolean) request.getAttribute("authenticated");
        String duke = (String) request.getAttribute("duke");
        log.info("controller name is\t\t" + name);
        log.info("controller myName is\t\t" + myName);
        log.info("controller id is\t\t" + myId);
        log.info("controller authenticated is\t" + authenticated);
        log.info("controller duke is\t\t" + duke);
        request.getRequestDispatcher("/WEB-INF/" + "login.jsp").forward(request, response);
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
