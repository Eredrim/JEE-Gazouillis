package main.java.servlet;


import main.java.dao.MessageDAO;
import main.java.dao.PersonDAO;
import main.java.model.Message;
import main.java.model.Person;
import main.java.utils.Encrypt;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MyProfileServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        System.out.println("init: loading profile servlet");
        super.init();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
        /**
         * Forcing UTF-8 encoding for the parameters
         */
        try {
            req.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        Person person = (Person) req.getSession().getAttribute("connectedPerson");

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
        super.doGet(req, resp);
        Person person = (Person) req.getSession().getAttribute("connectedPerson");

        List<Message> publishedMessages = person.getPublishedMessages();
        List<Message> draftMessages = person.getDraftMessages();

        req.setAttribute("user", person);

        req.setAttribute("followerNumber", person.getFollowers().size());
        req.setAttribute("publishedMessageNumber", publishedMessages.size());
        req.setAttribute("followings", person.getFollows());

        req.setAttribute("draftMessages", draftMessages);
        req.setAttribute("publishedMessages", publishedMessages);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/MyProfile.jsp");
        dispatcher.forward(req, resp);
    }

    private void createMessage(HttpServletRequest req, Person person) {
        if (req.getParameter("messageContent") != null) {
            Message message = new Message();

            message.setContent(req.getParameter("messageContent"));
            message.setIsPublished(req.getParameter("isMessageDraft") == null);
            message.setPerson(person);
            message.setCreatedAt(new Date());
            message.setUpdatedAt(new Date());

            MessageDAO messageDAO = new MessageDAO();
            messageDAO.insert(message);
        }
    }

    private void updateMessage(HttpServletRequest req) {
        MessageDAO messageDAO = new MessageDAO();
        Message message = messageDAO.findById(Integer.parseInt(req.getParameter("idMessage")));

        message.setContent(req.getParameter("messageContent"));
        message.setIsPublished(req.getParameter("isMessageDraft") == null);
        message.setUpdatedAt(new Date());

        messageDAO.update(message);
    }

    private void updateProfile(HttpServletRequest req, Person person) {
        person.setUsername(req.getParameter("username"));
        person.setName(req.getParameter("name"));
        person.setFirstName(req.getParameter("firstName"));
        person.setCity(req.getParameter("city"));
        person.setMail(req.getParameter("mail"));

        if (req.getParameter("password") != null) {
            String password = Encrypt.encrypt(req.getParameter("password"));
            person.setPassword(password);
        }

        PersonDAO personDAO = new PersonDAO();
        personDAO.update(person);
    }
}
