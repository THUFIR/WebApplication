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
    private MyToken myToken = null;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.fine("controller processing request..");
        String name = request.getParameter("name");
        log.info("name:\t" + name + "\t\t" + Controller.class.getCanonicalName());
        myToken = (MyToken) request.getAttribute("myToken");
        if (myToken == null) {
            myToken = new MyToken();
        } else {
            log.info("processRequest:   new token");
        }
        myToken.setName(name);
        request.setAttribute("myToken", myToken);
        LogAttributesAndParameters logRequest = new LogAttributesAndParameters(request, Controller.class.getName());
        request.getRequestDispatcher("/WEB-INF/" + "login.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "controller";
    }
}
