package net.bounceme.dur.webapp.servlets;

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
    private ControllerToken token;// = new MyToken();
    private String className = Controller.class.getName();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info(className + "\tcontroller processing request..");

        
        
        /*
         String login = request.getParameter("login");
         String myName = request.getParameter("myName");
         String myId = request.getParameter("myId");
         log.info("login:\t" + login + "\t\t" + Controller.class.getCanonicalName());
         ControllerToken tempToken = (ControllerToken) request.getSession().getAttribute("controllerToken");
         if (tempToken == null) {
         token = new ControllerToken();
         } else {
         token = tempToken;
         token.initRequest(request);
         token.setLogin(login);
         token.setMyName(myName);
         token.setMyId(myId);
         }
         request.getSession(false).setAttribute("controller", token);
         LogTokens.logControllerToken(token, Controller.class.getName());
         */
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

    private String makeUniqueString() {
        return "jdsakl4246";
    }

}
