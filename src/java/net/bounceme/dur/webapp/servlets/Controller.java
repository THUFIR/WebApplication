package net.bounceme.dur.webapp.servlets;

import net.bounceme.dur.webapp.tokens.ControllerToken;
import java.io.IOException;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(
        name = "controller",
        description = "front controller",
        urlPatterns = "/controller",
        initParams = {
            @WebInitParam(name = "myName", value = "thufir"),
            @WebInitParam(name = "myId", value = "bjfdkl34363fjsl")})

public class Controller extends HttpServlet {

    private static final Logger log = Logger.getLogger(Controller.class.getName());
    private ControllerToken token = new ControllerToken();
    private String className = Controller.class.getName();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info(className + "\tcontroller processRequest..");
        String myName = getServletConfig().getInitParameter("myName");
        String myId = getServletConfig().getInitParameter("myId");
        token.setMyName(myName);
        token.setMyId(myId);
        request.setAttribute("controller", token);
        request.getRequestDispatcher("/WEB-INF/" + "login.jsp").forward(request, response);
        log.info(className + "\tcontroller ..processRequest");
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
