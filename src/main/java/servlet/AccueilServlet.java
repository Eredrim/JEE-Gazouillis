package main.java.servlet;

import java.io.IOException;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;

import main.java.dao.PersonDAO;
import main.java.dao.MessageDAO;
import main.java.model.Message;
import main.java.model.Person;

public class AccueilServlet extends Servlet {

	@Override
	public void init() throws ServletException {
		System.out.println("init: loading accueil servlet");
		super.init();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doGet(req, resp);
		System.out.println("GET /accueil");
		MessageDAO messageDAO = new MessageDAO();
		PersonDAO personDAO = new PersonDAO();

		List<Message> messageList = messageDAO.findAllPublished();
		List<Message> messagesMonde = new ArrayList<>();
		List<Message> messagesSuivi = new ArrayList<>();
		Person personConnecte = (new PersonDAO()).findByUsername((String)req.getSession().getAttribute("connectedPerson"));

		List<Person> follows = personConnecte.getFollows();
		//on charge les messages dans les listes
		for (Message message : messageList){
			Person author = message.getPerson();
			for(Person follow : follows){
				if(follow.getId().equals(author.getId())) {
					messagesSuivi.add(message);
				}
			}
			messagesMonde.add(message);
		}

		req.setAttribute("messagesMonde", messagesMonde);
		req.setAttribute("messagesSuivi", messagesSuivi);
		req.setAttribute("connectedPerson", personConnecte);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/accueil.jsp");
		dispatcher.forward(req, resp);
	}
}