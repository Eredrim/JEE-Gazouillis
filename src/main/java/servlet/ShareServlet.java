package main.java.servlet;

import java.io.IOException;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.dao.KeywordDAO;
import main.java.dao.MessageDAO;
import main.java.dao.PersonDAO;
import main.java.model.Keyword;
import main.java.model.Message;
import main.java.model.Person;

public class ShareServlet extends HttpServlet {

	@Override
	public void init() throws ServletException {
		System.out.println("init: loading share servlet");
		super.init();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("GET /share");
		KeywordDAO keywordDAO = new KeywordDAO();
		PersonDAO personDAO = new PersonDAO();
		Person personConnecte = (new PersonDAO()).findByUsername((String)req.getSession().getAttribute("connectedPerson"));

		int idMessage = Integer.parseInt(String.valueOf(req.getParameter("idGazouille")));
		MessageDAO messageDAO = new MessageDAO();
		Message message = messageDAO.findById(idMessage);
		message.addSharer(personConnecte);
		messageDAO.update(message);

		RequestDispatcher rd= req.getRequestDispatcher("/myProfile");
		rd.forward(req, resp);
	}
}