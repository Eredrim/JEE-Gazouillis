/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.servlet;

import java.io.IOException;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import main.java.dao.PersonDAO;
import main.java.model.Person;
import main.java.utils.Encrypt;

/**
 *
 * @author Gilles
 */
public class ConnectionServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        System.out.println("init: loading connection servlet");
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PersonDAO personDAO = new PersonDAO();

        System.out.println("GET /connection");

        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/Connection.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("POST /connection");
        request.setCharacterEncoding("UTF-8");

        boolean connected = false;

        if(request.getParameter("subscribe") != null) {
            connected = this.trySubscribe(request);
        }
        else if (request.getParameter("connection") != null) {
            connected = this.tryConnect(request);
        }

        if (connected) {
            response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/accueil") );
        }
        else {
            this.doGet(request, response);
        }
    }

    private boolean tryConnect(HttpServletRequest req) {
        String password = Encrypt.encrypt(req.getParameter("password"));

        PersonDAO personDAO = new PersonDAO();
        Person person = personDAO.findByUsername(req.getParameter("username"));

        if (person != null && person.getPassword().equals(password)) {
            req.getSession().setAttribute("connectedPerson", person.getUsername());
            return true;
        }
        return false;
    }

    private boolean trySubscribe(HttpServletRequest req) {
        String password = Encrypt.encrypt(req.getParameter("password"));

        Person person = new Person();
        person.setUsername(req.getParameter("username"));
        person.setName(req.getParameter("name"));
        person.setFirstName(req.getParameter("firstName"));
        person.setCity(req.getParameter("city"));
        person.setMail(req.getParameter("mail"));
        person.setPassword(password);

        PersonDAO personDAO = new PersonDAO();
        personDAO.insert(person);

        req.getSession().setAttribute("connectedPerson", person.getUsername());
        return true;
    }

}
