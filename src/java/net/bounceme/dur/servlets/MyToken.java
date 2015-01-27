package net.bounceme.dur.servlets;

import java.util.logging.Logger;

public class MyToken {//should probably be immutable...

    private static Logger log = Logger.getLogger(MyToken.class.getName());

    private String name = "nimo";
    private String role = "captain";
    private String password = "abc";
    private boolean authenticated = false;
    private boolean attemptedLogin = false;

    public MyToken() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }

    public boolean isAttemptedLogin() {
        return attemptedLogin;
    }

    public void setAttemptedLogin(boolean attemptedLogin) {
        this.attemptedLogin = attemptedLogin;
    }

    @Override
    public String toString() {
        return name + authenticated + attemptedLogin;
    }
}
