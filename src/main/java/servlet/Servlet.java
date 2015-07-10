package main.java.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("connectedPerson") == null) {
            resp.sendRedirect(resp.encodeRedirectURL(req.getContextPath() + "/connection"));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("connectedPerson") == null) {
            resp.sendRedirect(resp.encodeRedirectURL(req.getContextPath() + "/connection"));
        }
    }
}
