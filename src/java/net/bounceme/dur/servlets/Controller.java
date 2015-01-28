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
        //String name = request.getSession().getServletContext().getInitParameter("name");
        //  String name = request.getParameter("name");
        //  String authenticated = request.getParameter("authenticated");
        // String message = request.getParameter("message");
        String name = (String) request.getAttribute("name");
        boolean authenticated = (boolean) request.getAttribute("authenticated");
        String message = (String) request.getAttribute("message");
        log.info(name);
        log.info("auth" + authenticated);
        log.info(message);
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
