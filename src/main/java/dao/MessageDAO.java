package main.java.dao;

import main.java.model.Message;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import main.java.utils.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

public class MessageDAO {

    public void insert(Message message) {
        Transaction transaction = null;
        Session session         = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.clear();
            session.save(message);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
    }

    public void update(Message message) {
        Transaction transaction = null;
        Session session         = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.clear();
            session.merge(message);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
    }

    public void delete(Message message) {
        Transaction transaction = null;
        Session session         = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.delete(message);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
    }

    public List<Message> findAll() {
        List<Message> messages  = new ArrayList<>();
        Session session         = HibernateUtil.getSessionFactory().openSession();
        try {
            messages = session.createQuery("from Message order by updatedAt DESC").list();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return messages;
    }

    public List<Message> findAllPublished() {
        List<Message> messages  = new ArrayList<>();
        Session session         = HibernateUtil.getSessionFactory().openSession();
        try {
            messages = session.createQuery("from Message where isPublished = true order by updatedAt DESC").list();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return messages;
    }

    public Message findById(Integer id) {
        Message message = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String queryString = "from Message where id = :id";
            Query query = session.createQuery(queryString);
            query.setInteger("id", id);
            message = (Message) query.uniqueResult();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return message;
    }
}
