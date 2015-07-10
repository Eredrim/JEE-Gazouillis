/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import main.java.dao.PersonDAO;
import main.java.model.Person;

/**
 *
 * @author Gilles
 */
public class ProfilServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getParameter("user") != null) {
            PersonDAO pDao = new PersonDAO();
            Person p = pDao.findByUsername(request.getParameter("user"));

            if (p != null) {
                request.setAttribute("profile", p);
                try {
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/profile.jsp");
                    dispatcher.forward(request, response);
                } catch (NumberFormatException | ServletException | IOException e) {
                    System.err.println(e);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/static/404.html");
                    dispatcher.forward(request, response);
                }
            } else {
                RequestDispatcher dispatcher = request.getRequestDispatcher("/static/404.html");
                dispatcher.forward(request, response);
            }
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/static/404.html");
            dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Person connectedPerson = (Person) session.getAttribute("connectedPerson");
        String idToFollow = request.getParameter("userToFollow");
        if (connectedPerson == null) {
            response.sendRedirect("connexion");
        } else if (idToFollow != null) {
            PersonDAO pDao = new PersonDAO();
            Person personToFollow = pDao.findById(Integer.parseInt(idToFollow));
            
            if(connectedPerson.isFollowing(personToFollow)){
                personToFollow.removeFollower(connectedPerson);
                connectedPerson.removeFollow(personToFollow);
            }
            else{
                connectedPerson.addFollow(personToFollow);
                personToFollow.addFollower(connectedPerson);
            }
            
            pDao.update(personToFollow);
            pDao.update(connectedPerson);

            this.doGet(request, response);
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/static/404.html");
            dispatcher.forward(request, response);
        }
    }

}
