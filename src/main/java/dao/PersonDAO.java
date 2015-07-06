package main.java.dao;

import main.java.model.Person;
import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.Session;
import main.java.utils.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

public class PersonDAO {

    public void insert(Person person) {
        Transaction transaction = null;
        Session session         = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.save(person);
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

    public void update(Person person) {
        Transaction transaction = null;
        Session session         = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.update(person);
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

    public void delete(Person person) {
        Transaction transaction = null;
        Session session         = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.delete(person);
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

    public List<Person> findAll() {
        List<Person> persons    = new ArrayList<>();
        Session session         = HibernateUtil.getSessionFactory().openSession();

        try {
            persons = session.createQuery("from Person").list();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return persons;
    }

    public Person findById(Integer id) {
        Person person   = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String queryString = "from Person where id = :id";
            Query query = session.createQuery(queryString);
            query.setInteger("id", id);
            person = (Person) query.uniqueResult();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return person;
    }

    public Person findByUsername(String username) {
        Person person   = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String queryString = "from Person where username = :username";
            Query query = session.createQuery(queryString);
            query.setString("username", username);
            person = (Person) query.uniqueResult();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return person;
    }
}
