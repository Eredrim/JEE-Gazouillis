package main.java.servlet;

import java.io.IOException;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.dao.KeywordDAO;
import main.java.dao.PersonDAO;
import main.java.model.Keyword;
import main.java.model.Message;
import main.java.model.Person;

public class RechercheServlet extends HttpServlet {

	@Override
	public void init() throws ServletException {
		System.out.println("init: loading recherche servlet");
		super.init();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("GET /profile");
		KeywordDAO keywordDAO = new KeywordDAO();
		PersonDAO personDAO = new PersonDAO();

		String recherche = String.valueOf(req.getParameter("recherche"));
		Keyword keyword = keywordDAO.findByWord(recherche);
		List<Message> messageList;
		if(keyword != null){
			messageList = keyword.getMessages();
		}
		else{
			messageList = new ArrayList<>();
		}


		Person person = personDAO.findByUsername(recherche);

		req.setAttribute("messages", messageList);
		req.setAttribute("person", person);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/recherche.jsp");
		dispatcher.forward(req, resp);
	}
}