/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import main.java.dao.PersonDAO;
import main.java.model.Person;

/**
 *
 * @author Gilles
 */
public class ConnexionServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        System.out.println("init: loading connexion servlet");
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("GET /connexion");

        try {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/connexion.jsp");
            dispatcher.forward(request, response);
        } catch (NumberFormatException | ServletException | IOException e) {
            System.err.println(e);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/static/404.html");
            dispatcher.forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{
            if(request.getParameter("inscription") != null){
                String pass = request.getParameter("formMDP");
                if(request.getParameter("formVerification").equals(pass)){
                                    String firstName = request.getParameter("formPrenom");
                String name = request.getParameter("formNom");
                String username = request.getParameter("formUsername");
                String email = request.getParameter("formEmail");
                Person p = new Person(firstName, name, email, username, pass);
                PersonDAO personDAO = new PersonDAO();
                personDAO.insert(p);
                }
            }
        }
        catch (Exception e){
            
        }
    }

}
