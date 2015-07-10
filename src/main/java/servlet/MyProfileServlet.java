package main.java.servlet;


import main.java.dao.MessageDAO;
import main.java.dao.PersonDAO;
import main.java.model.Message;
import main.java.model.Person;
import main.java.utils.HtmlEscape;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class MyProfileServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        System.out.println("init: loading profile servlet");
        super.init();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //TODO : corriger les problèmes d'accents (HTML)
        //TODO : faire toutes les vérifs de champ des deux côtés
        /**
         * Forcing UTF-8 encoding for the parameters
         */
        try {
            req.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        Person person = this.getConnectedUser(req.getSession().getAttribute("username"));

        if (req.getParameter("saveMessage") != null) {
            this.createMessage(req, person);
        }
        else if (req.getParameter("updateMessage") != null) {
            this.updateMessage(req);
        }
        else if (req.getParameter("updateProfile") != null) {
            this.updateProfile(req, person);
        }

        this.doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Person person = this.getConnectedUser(req.getSession().getAttribute("username"));

        List<Message> publishedMessages = new ArrayList<>();
        List<Message> draftMessages = new ArrayList<>();

        for (Message message : person.getMessages()) {
            if (message.getIsPublished()) {
                publishedMessages.add(message);
            }
            else {
                draftMessages.add(message);
            }
        }

        req.setAttribute("user", person);

        req.setAttribute("followerNumber", person.getFollowers().size());
        req.setAttribute("publishedMessageNumber", publishedMessages.size());
        req.setAttribute("followings", person.getFollows());

        req.setAttribute("draftMessages", draftMessages);
        req.setAttribute("publishedMessages", publishedMessages);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/MyProfile.jsp");
        dispatcher.forward(req, resp);
    }

    private Person getConnectedUser(Object username) {
        username = "axel";
        if (username == null) {
            //TODO : redirect when user not connected
        }

        PersonDAO personDAO = new PersonDAO();
        return personDAO.findByUsername((String) username);
    }

    private void createMessage(HttpServletRequest req, Person person) {
        if (req.getParameter("messageContent") != null) {
            Message message = new Message();

            message.setContent(req.getParameter("messageContent"));
            message.setIsPublished(req.getParameter("isMessageDraft") == null);
            message.setPerson(person);

            MessageDAO messageDAO = new MessageDAO();
            messageDAO.insert(message);
        }
    }

    private Person updateMessage(HttpServletRequest req) {
        MessageDAO messageDAO = new MessageDAO();
        Message message = messageDAO.findById(Integer.parseInt(req.getParameter("idMessage")));

        message.setContent(req.getParameter("messageContent"));
        message.setIsPublished(req.getParameter("isMessageDraft") == null);

        messageDAO.update(message);
        return message.getPerson();
    }

    private void updateProfile(HttpServletRequest req, Person person) {
        person.setUsername(req.getParameter("username"));
        person.setName(req.getParameter("name"));
        person.setFirstName(req.getParameter("firstName"));
        person.setCity(req.getParameter("city"));
        person.setMail(req.getParameter("mail"));

        if (req.getParameter("password") != null) {
            person.setPassword(req.getParameter("password"));
        }

        PersonDAO personDAO = new PersonDAO();
        personDAO.update(person);
    }
}
