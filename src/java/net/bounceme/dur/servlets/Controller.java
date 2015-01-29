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
        String name = request.getParameter("name");
        log.info("controller name is\t\t" + name);
      //  String user = (String) request.getAttribute("user");
      //  user = name; //?? bad idea
        request.setAttribute("name", name); //even worse
        boolean authenticated = (boolean) request.getAttribute("authenticated");
        String message = (String) request.getAttribute("message");
        String me = (String) request.getAttribute("me");
        log.info("controller user is\t\t" + message);
        log.info("controller authenticated is\t" + authenticated);
        log.info("controller message is\t\t" + message);
        log.info("controller me is\t\t" + me);
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
